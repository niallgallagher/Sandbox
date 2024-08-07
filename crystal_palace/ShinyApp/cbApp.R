#############################
### Task: Crystal Palace Data Task
### Name: Niall Gallagher
### Date: 2024-05-18
#############################

# libraries
library(shiny)
library(DT)
library(ggplot2)

# UI
ui <- navbarPage(
  title = div(
    img(src = "logo.png", height = "30px", style = "vertical-align: middle; margin-right: 10px;"), 
    "My Shiny App"
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
      }
      .navbar-default .navbar-nav > li > a {
        color: white !important;
      }
      /* Sidebar customization */
      .well {
        background-color: red !important;
        border: none !important;
        height: 75vh !important; /* Set sidebar height to 100% of the viewport height */
        overflow-y: auto; /* Enable scrolling if content overflows */
      }
      .well .form-group label {
        color: white !important;
      }
      .well .form-group input, .well .form-group select {
        color: black !important;
      }
    "))
  ),
  
  # Sidebar layout with input and output definitions
  sidebarLayout(
    sidebarPanel(
      # Input for selecting a Player
      selectizeInput("selectedName", "Select Individual:",
                     choices = c("Alice", "Bob", "Charlie", "David", "Ella"),
                     multiple = TRUE, options = list(placeholder = 'Select an individual')),
      selectizeInput("occupation", "Select Occupation(s):",
                     choices = c("Engineer", "Doctor", "Artist", "Lawyer", "Teacher"),
                     multiple = TRUE, options = list(placeholder = 'Select occupations')),
      sliderInput("ageRange", "Select Age Range:",
                  min = 20, max = 50, value = c(25, 45), step = 1)
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
        tabPanel("Settings", "Adjust your settings here.")
      )
    )
  )
)

# Define the server logic
server <- function(input, output, session) {
  # Sample data with multiple columns
  data <- data.frame(
    Name = c("Alice", "Bob", "Charlie", "David", "Ella"),
    Age = c(25, 30, 35, 40, 45),
    Occupation = c("Engineer", "Doctor", "Artist", "Lawyer", "Teacher"),
    Location = c("New York", "Los Angeles", "Chicago", "Houston", "Phoenix"),
    stringsAsFactors = FALSE
  )
  
  # Reactive expression to filter data
  filteredData <- reactive({
    subset_data <- data
    
    # Filter by age range
    if (!is.null(input$ageRange)) {
      subset_data <- subset(subset_data, Age >= input$ageRange[1] & Age <= input$ageRange[2])
    }
    
    # Filter by selected name
    if (!is.null(input$selectedName) && length(input$selectedName) > 0) {
      subset_data <- subset(subset_data, Name %in% input$selectedName)
    }
    
    # Filter by selected occupations
    if (length(input$occupation) > 0) {
      subset_data <- subset(subset_data, Occupation %in% input$occupation)
    }
    
    return(subset_data)
  })
  
  # Render the interactive table in the Home tab
  output$results <- renderDT({
    datatable(filteredData(), options = list(pageLength = 5, autoWidth = TRUE))
  })
  
  # Render the bar chart in the Data tab
  output$ageBarChart <- renderPlot({
    # Create a bar chart of the Age of individuals
    ggplot(filteredData(), aes(x = Name, y = Age, fill = Occupation)) +
      geom_bar(stat = "identity") +
      labs(title = "Age of Individuals by Name", x = "Name", y = "Age") +
      theme_minimal() +
      scale_fill_brewer(palette = "Set3")
  })
}

# Run the app
shinyApp(ui = ui, server = server)
