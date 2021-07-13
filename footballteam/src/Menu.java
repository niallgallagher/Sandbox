import java.io.IOException;
import java.util.Scanner;

public class Menu {
    //The method "GenerateMenu" is declared below to help develop an interactive menu for the user.
    public static void GenerateMenu() {
        System.out.print("\n--Football Club Editor--");
        String choice = "";

        Scanner userChoice = new Scanner(System.in);
        boolean isValidChoice = false;

        while (!isValidChoice) {
            System.out.println("\n\n--Main Menu--");
            System.out.println("1 - View Teams");
            System.out.println("2 - Add Teams");
            System.out.println("3 - Delete Teams");
            System.out.println("4 - Edit Teams");
            System.out.println("Q - Quit ");
            System.out.print("Pick : ");

            choice = userChoice.next().toLowerCase();

            //The 'if/else statement' is used to ensure the user places a correct input.
            if (choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4")
                    || choice.equals("q")) {
                isValidChoice = true;
            } else {
                System.out.print("Please choose a Valid option!");
            }
        }
        //The "switch" function was used so that the users input would go straight to the method that they requested.
        switch (choice) {
            case "1" : {
                ViewTeam.viewFootballTeams();
                break;
            }

            case "2" : {
                try {
                    AddTeam.includeAFootballTeam();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }

            case "3" : {
                try {
                    DeleteTeam.deleteATeam();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }

            case "4" : {
                try {
                    EditTeam.editATeam();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }

            case "q" : {
                System.out.print("\nGoodbye!");
                break;
            }

            default : {
                break;
            }

        }
    }
}



