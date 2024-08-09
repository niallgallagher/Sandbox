# libraries
library(shiny)
library(DT)
library(ggplot2)
library(dplyr)  # For data manipulation
library(lubridate)  # For date manipulation

# Load the dataset
data <- read.csv('/Users/niallgallagher/crystal_palace/cb_dataset_sample.csv')

# Print the column names to verify them
print(colnames(data))

# Select the required columns for filtering and display
selected_columns <- c(2, 5, 7:11, 47:53)  # Ensure to include all necessary columns
data <- data[, selected_columns]

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

# Note: Ensure these names match exactly with the dataset's column names
displayData <- data[, display_columns, drop = FALSE]

# UI
ui <- navbarPage(
  title = div(
    img(src = "logo.png", height = "50px", style = "vertical-align: middle; margin-right: 10px;"), 
    span("My Shiny App", style = "font-size: 30px;")  # Increase the font size
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
      width = 4  # Adjust the width of the sidebar
    ),
    mainPanel(
      tabsetPanel(
        # Home Tab with Interactive Table
        tabPanel("Home",
                 DTOutput("results")  # Interactive table
        ),
        # Data Tab with Bar Chart
        tabPanel("Data", 
                 plotOutput("ageBarChart")  # Bar chart
        ),
        # Settings Tab for future settings
        tabPanel("Radar Plot", "Compare and Download.")
      ),
      width = 8,  # Adjust the width of the main panel
      class = "main-panel"  # Add class to main panel for styling
    )
  )
)

# Define the server logic
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
  
  # Render the interactive table in the Home tab
  output$results <- renderDT({
    datatable(
      filteredData()[, display_columns, drop = FALSE], 
      options = list(pageLength = 5, autoWidth = TRUE, scrollX = TRUE, scrollY = TRUE),
      colnames = custom_colnames,  # Use custom column names
      rownames = FALSE
    )
  })
  
  # Render the bar chart in the Data tab
  output$ageBarChart <- renderPlot({
    # Create a bar chart of the Age of individuals
    ggplot(filteredData(), aes(x = player_name, y = age, fill = team_name)) +
      geom_bar(stat = "identity") +
      labs(title = "Age of Individuals by Name", x = "Name", y = "Age") +
      theme_minimal() +
      scale_fill_brewer(palette = "Set3")
  })
}

# Run the app
shinyApp(ui = ui, server = server)