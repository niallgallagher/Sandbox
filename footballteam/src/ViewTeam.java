import java.util.Scanner;

public class ViewTeam {

    public ViewTeam() {}

    public static void viewFootballTeams() {
        System.out.println("\n**** View Football Teams ****");

        Scanner choice = new Scanner(System.in);

        System.out.println("\nWhat do you want to view?\n\n1. All Clubs\n2. Main Menu");
        int ans = choice.nextInt();
        switch(ans) {
            case 1 : {
               // getAllRoomDetails();
                break;
            }

            case 2 : {
                Menu.GenerateMenu();
                break;
            }

    }
}

}
