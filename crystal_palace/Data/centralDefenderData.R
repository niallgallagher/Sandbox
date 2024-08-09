#############################
### Task: Crystal Palace Data Task
### Name: Niall Gallagher
### Date: 2024-05-18
#############################

#Libraries
library(dplyr)


#Read Dataset
data <- read.csv('/Users/niallgallagher/football_data/src_ra_data_sample.csv')

data <- data %>%
  mutate(player_id = paste(player_name, birthdate, sep = "_"))

#Breaking Down the columns distribution 
summary(data)
View(data)

#Filtering Data Based on Players who have played in the Central Defender
filtered_data <- data %>%
  group_by(player_id,season_name) %>%
  filter(sum(mins_played[src_detailed_position == "cb4" | src_detailed_position == "cbc3" | src_detailed_position == "cbo3"]) > 400) %>%
  ungroup()

filtered_data <- filtered_data %>%
  group_by(player_id, season_name) %>%
  filter(area_name %in% c("England", "Spain", "France","Germany","Italy") & divisionlevel == 1) %>%
  ungroup()

filtered_data <- filtered_data %>%
  group_by(player_id, season_name) %>%
  filter(src_detailed_position %in% c("cb4", "cbc3", "cbo3")) %>%
  ungroup()

filtered_data <- filtered_data %>%
  group_by(player_id, season_name) %>%  
  filter(mins_played == max(mins_played)) %>%  
  ungroup()  

#Select Relevant Metrics for A CB
cb_filtered_data <- filtered_data

cb_filtered_data$defesnive_duels_P90 <- c((cb_filtered_data$defensiveduels / cb_filtered_data$mins_played) * 90)

cb_filtered_data$duel_won_percentage <- c((cb_filtered_data$defensiveduelswon / cb_filtered_data$defensiveduels))

cb_filtered_data$aerialduels_P90 <- c((cb_filtered_data$aerialduels / cb_filtered_data$mins_played) * 90) 

cb_filtered_data$shots_blocked_P90 <- c((cb_filtered_data$shotsblocked / cb_filtered_data$mins_played) * 90) 

cb_filtered_data$progressive_runs_P90 <- c((cb_filtered_data$progressiverun / cb_filtered_data$mins_played) * 90) 

cb_filtered_data$accelerations_P90 <- c((cb_filtered_data$accelerations / cb_filtered_data$mins_played) * 90) 

cb_filtered_data$accurate_passing_pct <- c((cb_filtered_data$successfulpasses / cb_filtered_data$passes)) 

cb_filtered_data$forward_passing_p90 <- c((cb_filtered_data$forwardpasses / cb_filtered_data$mins_played) * 90) 

cb_filtered_data$final_third_passing_pct <- c((cb_filtered_data$successfulpassestofinalthird / cb_filtered_data$passestofinalthird)) 

cb_filtered_data$progressivepasses_p90 <- c((cb_filtered_data$progressivepasses / cb_filtered_data$mins_played) * 90) 

cb_filtered_data$progressivepasses_pct <- c((cb_filtered_data$successfulprogressivepasses / cb_filtered_data$progressivepasses)) 

cb_filtered_data$xgp90 <- c((cb_filtered_data$np_xg / cb_filtered_data$mins_played) * 90) 

cb_filtered_data$goalp90 <- c((cb_filtered_data$goals / cb_filtered_data$mins_played) * 90) 

cb_filtered_data$headedshotsp90 <- c((cb_filtered_data$headshots / cb_filtered_data$mins_played) * 90) 

cb_filtered_data$shotsp90 <- c((cb_filtered_data$shots / cb_filtered_data$mins_played) * 90) 

cb_filtered_data <- select(cb_filtered_data,108,1:10,109,110,111,106,112,95,113:123)

cb_filtered_data[is.na(cb_filtered_data)] <- 0

cb_filtered_data.z.score <- cb_filtered_data

cb_filtered_data.z.score <- cb_filtered_data.z.score %>%
  group_by(player_id,season_name) %>%
  filter(mins_played > 500) %>%
  ungroup()

scaled_df <- cb_filtered_data.z.score %>%
  mutate(
    across(
      where(is.numeric) & -c(4, 10), 
      ~ scale(.x)[, 1],              
      .names = "Z_{col}"             
    )
  )

#  Function to scale z-scores to a range of 0 to 100
scale_z_to_100 <- function(z) {
  # z-scores from -3 to 3 range mapped to 0 to 100
  z_min <- -3
  z_max <- 3
  scaled_value <- ((z - z_min) / (z_max - z_min)) * 100
  return(pmin(pmax(scaled_value, 0), 100))  
}

# Scale the z-scores to a 0-100 rating
df_scaled <- scaled_df %>%
  mutate(
    across(
      starts_with("Z_"),            
      scale_z_to_100,               
      .names = "Rating_{col}"       
    )
  )

# View the scaled dataset
head(df_scaled)

#Centre Back Z scores
cb_filtered_data <- select(df_scaled,1:28,47:64)
#Grouping and Assign Weights based on attributes of column Values
#Defense Score
cb_filtered_data$def_ability_score <- ((cb_filtered_data$Rating_Z_defesnive_duels_P90 * 0.35) + (cb_filtered_data$Rating_Z_duel_won_percentage * 0.65))

#Aerial Score
cb_filtered_data$aer_score <- ((cb_filtered_data$Rating_Z_aerialduels_P90 * 0.30) + (cb_filtered_data$Rating_Z_aerials_success_rate * 0.7))

#Ball playing (Passing)
cb_filtered_data$positioning_score <- ((cb_filtered_data$Rating_Z_shotsp90 * 0.5) + (cb_filtered_data$Rating_Z_padj_interceptions * 0.5))

cb_filtered_data$ballcarrying_score <- ((cb_filtered_data$Rating_Z_progressive_runs_P90 * 0.7) + (cb_filtered_data$Rating_Z_accelerations_P90 * 0.3))

cb_filtered_data$ballplaying_score <- ((cb_filtered_data$Rating_Z_accurate_passing_pct * 0.2) + (cb_filtered_data$Rating_Z_forward_passing_p90 * 0.15) +
                                       (cb_filtered_data$Rating_Z_final_third_passing_pct * 0.25) + (cb_filtered_data$Rating_Z_progressivepasses_p90 * 0.25) +
                                         (cb_filtered_data$Rating_Z_progressivepasses_pct * 0.15))

cb_filtered_data$attack_score <- ((cb_filtered_data$Rating_Z_xgp90 * 0.4) + (cb_filtered_data$Rating_Z_goalp90 * 0.15) +
                                       (cb_filtered_data$Rating_Z_headedshotsp90 * 0.3) + (cb_filtered_data$Rating_Z_shotsp90 * 0.15))

#CDM Rating Based on Group Attributes 
cb_filtered_data$cbrating <- ((cb_filtered_data$Rating_Z_mins_played * 0.175) + (cb_filtered_data$def_ability_score * 0.15) + (cb_filtered_data$aer_score * 0.15) + (cb_filtered_data$positioning_score * 0.10) + (cb_filtered_data$ballcarrying_score * 0.2)
                              + (cb_filtered_data$ballplaying_score * 0.2) + (cb_filtered_data$attack_score * 0.025))



write.csv(cb_filtered_data,"~/crystal_palace/cb_dataset_sample.csv", row.names = FALSE)
print(colnames(data))



data.2 <- read.csv('/Users/niallgallagher/crystal_palace/cb_dataset_sample.csv')

