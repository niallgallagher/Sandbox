# libraries
library(shiny)
library(DT)
library(ggplot2)
library(dplyr)  # For data manipulation
library(lubridate)  # For date manipulation
library(tidyverse)

# Load the dataset
data <- read.csv('/Users/niallgallagher/crystal_palace/cb_dataset_sample.csv')

data <- data %>%
  pivot_longer(
    cols = 12:28,  # Columns 12 to 28 for Statistic and Value
    names_to = "Statistic",  # Name these columns as "Statistic"
    values_to = "Value"  # The corresponding values as "Value"
  ) 

# Calculate percentiles
data <- data %>%
  group_by(Statistic) %>%
  mutate(Percentile = percent_rank(Value) * 100) %>%
  ungroup()

data <- data %>% 
  mutate(stat=case_when(Statistic == "defesnive_duels_P90"|
                          Statistic == "duel_won_percentage" ~ "Defense",
                        Statistic == "aerialduels_P90"|
                          Statistic == "aerials_success_rate" ~ "Aerial",
                        Statistic == "shots_blocked_P90"|
                          Statistic == "padj_interceptions" ~ "Positioning",
                        Statistic == "progressive_runs_P90"|
                          Statistic == "accelerations_P90" ~ "Ball Carrying",
                        Statistic == "accurate_passing_pct"|
                          Statistic == "forward_passing_p90"|
                          Statistic == "final_third_passing_pct"|
                          Statistic == "progressivepasses_p90"|
                          Statistic == "progressivepasses_pct" ~ "Passing",
                        TRUE ~ "Attacking"))


data <- data %>%
  mutate(Statistic = recode(Statistic,
                             "defesnive_duels_P90"= "Defensive Duels P90",
                            "duel_won_percentage"= "Duel Won %",
                             "aerialduels_P90"= "Aerial Duels P90",
                             "aerials_success_rate"= "Aerial Success %",
                             "shots_blocked_P90"= "Shots Blocked P90",
                             "padj_interceptions"= "Padj Interceptions",
                             "progressive_runs_P90"= "Progressive Runs P90",
                             "accelerations_P90"= "Accelerations P90",
                             "accurate_passing_pct"= "Accurate Passing %",
                             "forward_passing_p90"= "Forward Passing P90",
                             "final_third_passing_pct"= "Final Third Passing %",
                             "progressivepasses_p90"= "Progressive Passes P90",
                             "progressivepasses_pct" = "Progressive Passes %",
                             "shotsp90" = "Shots P90",
                             "xgp90" = "xG P90",
                             "goalp90" = "Goal P90",
                            "headedshotsp90" = "Head Shots P90"
                            ))

# Convert birthdate to Date type and extract year
data$birthdate <- as.Date(data$birthdate)
data$birth_year <- year(data$birthdate)

# Extract year from season_name
data$season_year <- as.numeric(sub(".*(\\d{4}).*", "\\1", data$season_name))

# Calculate age based on season year and birth year
data$age <- data$season_year - data$birth_year

# Round numeric columns to 2 decimal places
data <- data %>% mutate(across(where(is.numeric), round, 2))

# Columns to display in the table (excluding height)
display_columns <- c("player_name", "team_name", "season_name", "mins_played", "age", "def_ability_score",         
                     "positioning_score", "ballcarrying_score", "ballplaying_score",
                     "attack_score", "cbrating")

# Custom column names for display
custom_colnames <- c(
  "Player", "Team", "Season", "Mins Played", "Age", "Defensive Ability", 
  "Positioning", "Ball Carrying", "Ball Playing",
  "Attack", "CB Rating"
)

# UI
ui <- navbarPage(
  title = div(
    img(src = "logo.png", height = "50px", style = "vertical-align: middle; margin-right: 10px;"), 
    span("Central Defender", style = "font-size: 30px;")  # Increase the font size
  ),
  id = "nav",
  
  # navbar and sidebar
  tags$head(
    tags$style(HTML("
      /* Navbar customization */
      .navbar-default {
        background-color: blue !important;
        border-color: red !important;
      }
      .navbar-default .navbar-brand {
        color: white !important;
        display: flex;
        align-items: center;
        font-size: 24px !important; /* Increase navbar brand font size */
      }
      .navbar-default .navbar-nav > li > a {
        color: white !important;
        font-size: 18px !important; /* Increase navbar item font size */
      }
      /* Sidebar customization */
      .well {
        background-color: red !important;
        border: none !important;
        height: 100vh !important; /* Set sidebar height to 100% of the viewport height */
        width: 300px !important; /* Set sidebar width */
        overflow-y: auto; /* Enable scrolling if content overflows */
        padding: 15px; /* Adjust padding inside the sidebar */
        margin-right: 0 !important; /* Remove right margin */
      }
      .well .form-group label {
        color: white !important;
      }
      .well .form-group input, .well .form-group select {
        color: black !important;
      }
      /* Adjust main panel */
      .main-panel {
        padding-left: 0px !important; /* Remove left padding */
        margin-left: -10px !important; /* Adjust left margin */
      }
      /* Adjust table width and height */
      .dataTables_wrapper {
        width: 100% !important; /* Set table width to fill available space */
        height: calc(100vh - 200px) !important; /* Adjust the height of the table wrapper */
        overflow: auto !important; /* Enable scrolling */
        margin-left: 0 !important; /* Move the table to the left */
      }
      /* Adjust table width */
      table.dataTable {
        width: 100% !important; /* Set table width to fill available space */
      }
    "))
  ),
  
  # Sidebar layout with input and output definitions
  sidebarLayout(
    sidebarPanel(
      # Input for selecting a Player
      selectizeInput("player_name", "Select Player:",
                     choices = NULL,  # Set choices to NULL initially
                     multiple = TRUE, options = list(placeholder = 'Select a Player')),
      selectizeInput("competition_name", "Select Competition(s):",
                     choices = unique(data$competition_name),  # Set choices from dataset
                     multiple = TRUE, options = list(placeholder = 'Select competitions')),
      selectizeInput("season", "Select Season(s):",
                     choices = unique(data$season_name),  # Set choices from dataset
                     multiple = TRUE, options = list(placeholder = 'Select seasons')),
      sliderInput("ageRange", "Select Age Range:",
                  min = min(data$age), max = max(data$age), value = c(min(data$age), max(data$age)), step = 1),
      downloadButton("downloadRadarPlot", "Download Radar Chart"),  # Download button placed in the sidebar
      width = 4  # Adjust the width of the sidebar
    ),
    mainPanel(
      tabsetPanel(
        # Home Tab with Interactive Table
        tabPanel("Home",
                 DTOutput("results")  # Interactive table
        ),
        # Data Tab with Bar Chart
        tabPanel("Radar Plot", 
                 plotOutput("radarBarChart")  # Bar chart
        ),
        # Settings Tab for future settings
      ),
      width = 8,  # Adjust the width of the main panel
      class = "main-panel"  # Add class to main panel for styling
    )
  )
)

server <- function(input, output, session) {
  
  # Update selectizeInput choices for player_name
  observe({
    updateSelectizeInput(session, "player_name", choices = unique(data$player_name), server = TRUE)
  })
  
  # Reactive expression to filter data based on selected player(s)
  filteredDataByPlayer <- reactive({
    if (!is.null(input$player_name) && length(input$player_name) > 0) {
      return(subset(data, player_name %in% input$player_name))
    } else {
      return(data)
    }
  })
  
  # Update the competition_name choices based on selected player(s)
  observe({
    filtered_data <- filteredDataByPlayer()
    updateSelectizeInput(session, "competition_name", choices = unique(filtered_data$competition_name), server = TRUE)
  })
  
  # Reactive expression to filter data based on selected player(s) and competition(s)
  filteredDataByCompetition <- reactive({
    subset_data <- filteredDataByPlayer()
    
    if (!is.null(input$competition_name) && length(input$competition_name) > 0) {
      subset_data <- subset(subset_data, competition_name %in% input$competition_name)
    }
    
    return(subset_data)
  })
  
  # Update the season choices based on selected competition(s)
  observe({
    filtered_data <- filteredDataByCompetition()
    updateSelectizeInput(session, "season", choices = unique(filtered_data$season_name), server = TRUE)
  })
  
  # Reactive expression to further filter data based on all sidebar inputs
  filteredData <- reactive({
    subset_data <- filteredDataByCompetition()
    
    # Filter by age range
    if (!is.null(input$ageRange)) {
      subset_data <- subset(subset_data, age >= input$ageRange[1] & age <= input$ageRange[2])
    }
    
    # Filter by selected season
    if (!is.null(input$season) && length(input$season) > 0) {
      subset_data <- subset(subset_data, season_name %in% input$season)
    }
    
    return(subset_data)
  })
  
  # Create a separate reactive dataset for the table that ensures unique rows based on display_columns
  tableData <- reactive({
    filteredData() %>%
      distinct(across(all_of(display_columns)))
  })
  
  # Render the interactive table in the Home tab
  output$results <- renderDT({
    datatable(
      tableData(),  # Use the unique rows dataset for the table
      options = list(pageLength = 5, autoWidth = TRUE, scrollX = TRUE, scrollY = TRUE),
      colnames = custom_colnames,  # Use custom column names
      rownames = FALSE
    )
  })
  
  output$radarBarChart <- renderPlot({
    ratings_data <- filteredData()  # Use the full filtered dataset
    
    # Reorder Statistic to group by stat category and then by Statistic
    ratings_data <- ratings_data %>%
      arrange(stat, Statistic) %>%  # Arrange by category and statistic
      mutate(Statistic = factor(Statistic, levels = unique(Statistic)))  # Maintain order
    
    # Replace underscores with line breaks for better label formatting
    ratings_data$Statistic <- gsub(" ", "\n", ratings_data$Statistic)
    
    # Generate subheading based on user selections
    subheading <- paste(
      "Competition:", paste(input$competition_name, collapse = ", "), 
      "| Season:", paste(input$season, collapse = ", "), 
      "| Minutes Played:", unique(ratings_data$mins_played)
    )
    
    ggplot(ratings_data, aes(Statistic, Percentile)) + 
      geom_bar(aes(y = 100, fill = stat), stat = "identity", width = 1, colour = "white", alpha = 0.5) +
      geom_bar(stat = "identity", width = 1, aes(fill = stat), colour = "white") + 
      coord_polar(clip = "off") +  # Prevents clipping of elements outside the chart
      expand_limits(y = c(-50, 100)) +  # Zooms in by expanding the y limits
      geom_text(aes(label = round(Percentile, 2)), 
                position = position_stack(vjust = 0.5), 
                size = 4, color = "white", check_overlap = TRUE) +  # Increase text size
      scale_fill_manual(values = c("Defense" = "#FF9300", "Positioning" = "#008000", "Attacking" = "#D70232", "Aerial" = "blue", "Ball Carrying" = "purple", "Passing" = "navy")) +
      scale_y_continuous(limits = c(-10, 100), breaks = seq(0, 100, by = 20)) +  # Add more ticks
      labs(
        fill = "",
        caption = "Sample Data",
        title = ratings_data$player_name[1],
        subtitle = subheading  # Add dynamic subheading
      ) + 
      theme_minimal() +
      theme(
        legend.position = "top",
        legend.text = element_text(size = 16, face = "bold"),  # Increase size and bold for sub-headings
        legend.title = element_text(size = 16, face = "bold"),  # Increase size and bold for legend title if applicable
        axis.title.y = element_blank(),
        axis.title.x = element_blank(),
        axis.text.y = element_blank(),
        axis.text.x = element_text(size = 14, angle = 0, hjust = 1, face = "bold"),  # Increase label size and make bold
        text = element_text(family = "Arial"),
        plot.title = element_text(hjust = 0.5, size = 20, face = "bold"),  # Increase title size and make bold
        plot.subtitle = element_text(hjust = 0.5, size = 16, face = "bold"),  # Increase subtitle size and make bold
        plot.caption = element_text(hjust = 0.5, size = 14, face = "bold"),  # Increase caption size and make bold
        panel.grid.major = element_line(color = "gray", linetype = "dashed"),
        panel.grid.minor = element_blank()
      )
  }, height = 700, width = 700)  # Increase plot size in Shiny
  
  # Implement the download handler
  output$downloadRadarPlot <- downloadHandler(
    filename = function() {
      paste("radar_chart", Sys.Date(), ".png", sep = "")
    },
    content = function(file) {
      # Save the plot as a PNG file
      png(file, width = 700, height = 700)
      print(
        ggplot(filteredData(), aes(Statistic, Percentile)) + 
          geom_bar(aes(y = 100, fill = stat), stat = "identity", width = 1, colour = "white", alpha = 0.5) +
          geom_bar(stat = "identity", width = 1, aes(fill = stat), colour = "white") + 
          coord_polar(clip = "off") +  # Prevents clipping of elements outside the chart
          expand_limits(y = c(-50, 100)) +  # Zooms in by expanding the y limits
          geom_text(aes(label = round(Percentile, 2)), 
                    position = position_stack(vjust = 0.5), 
                    size = 4, color = "white", check_overlap = TRUE) +  # Increase text size
          scale_fill_manual(values = c("Defense" = "#FF9300", "Positioning" = "#008000", "Attacking" = "#D70232", "Aerial" = "blue", "Ball Carrying" = "purple", "Passing" = "navy")) +
          scale_y_continuous(limits = c(-10, 100), breaks = seq(0, 100, by = 20)) +  # Add more ticks
          labs(
            fill = "",
            caption = "Sample Data",
            title = filteredData()$player_name[1],
            subtitle = paste(
              "Competition:", paste(input$competition_name, collapse = ", "), 
              "| Season:", paste(input$season, collapse = ", "), 
              "| Minutes Played:", unique(filteredData()$mins_played)
            )  # Add dynamic subheading
          ) + 
          theme_minimal() +
          theme(
            legend.position = "top",
            legend.text = element_text(size = 16, face = "bold"),  # Increase size and bold for sub-headings
            legend.title = element_text(size = 16, face = "bold"),  # Increase size and bold for legend title if applicable
            axis.title.y = element_blank(),
            axis.title.x = element_blank(),
            axis.text.y = element_blank(),
            axis.text.x = element_text(size = 14, angle = 0, hjust = 1, face = "bold"),  # Increase label size and make bold
            text = element_text(family = "Arial"),
            plot.title = element_text(hjust = 0.5, size = 20, face = "bold"),  # Increase title size and make bold
            plot.subtitle = element_text(hjust = 0.5, size = 16, face = "bold"),  # Increase subtitle size and make bold
            plot.caption = element_text(hjust = 0.5, size = 14, face = "bold"),  # Increase caption size and make bold
            panel.grid.major = element_line(color = "gray", linetype = "dashed"),
            panel.grid.minor = element_blank()
          )
      )
      dev.off()
    }
  )
}

# Run the app
shinyApp(ui = ui, server = server)
