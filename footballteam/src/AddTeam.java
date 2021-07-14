import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


public class AddTeam {

    private static FileWriter fWriter;
    private static BufferedWriter writer;
    private static FootballTeam footballTeam;
    private static List<FootballTeam> footballTeamList;
    private static String addFootballTeam;

    public static void includeAFootballTeam() throws IOException {
        do {
            addATeam();
            System.out.println("\nDo you want to add another football team? Yes or No: ");
            Scanner sc = new Scanner(System.in);
            addFootballTeam = sc.next();

        } while (addFootballTeam.equalsIgnoreCase("Yes"));

        if (addFootballTeam.equalsIgnoreCase("No")){
            footballClubWriter();
            System.out.println("Goodbye");
            Menu.GenerateMenu();

        }

    }

    static void addATeam() throws IOException {

            Scanner scan = new Scanner(System.in);

            //Below I ask the users choice of team.
            System.out.println("\n***** Add A Football Team *****");

            System.out.println("\nPlease Enter Club Name: ");
            String teamName = scan.nextLine();
            System.out.println("\nPlease Enter Stadium Name: ");
            String stadiumName = scan.nextLine();
            System.out.println("\nPlease Enter Stadium Capacity: ");
            int capacity = scan.nextInt();
            System.out.println("\nPlease Enter Team Nickname: ");
            String nickname = scan.next();

            footballTeam = new FootballTeam(teamName, stadiumName, capacity, nickname);
            footballTeam = (FootballTeam) footballTeamList;

        }

    /*private static void footballClubWriter() throws IOException {
        FileWriter fileWriter = new FileWriter("clubs.txt");
        for (FootballTeam str : footballTeamList) {
            fileWriter.write((str + System.lineSeparator()));
        }
        fileWriter.close();
    }*/
    private static void footballClubWriter() throws IOException {
        String oldLine = "";
        String newLine = "";
        String fileDataString = "";
        String fileName = "rooms.txt";

        newLine = oldLine.replace("", (CharSequence) footballTeamList);
        String newFileData = fileDataString.replaceAll(oldLine, newLine);
        FileWriter writer = new FileWriter(fileName);
        writer.append(newFileData);
        writer.flush();
        writer.close();

    }

       // System.out.println("Your team is saved");
          //  for(FootballTeam str: footballTeamList) {
           //     writer.write(str + System.lineSeparator());
         //   }
          //  writer.close();

   /* } while (!isEligible);

        System.out.println("Do you want to add another club? 1.yes 2.no: ");

        Scanner sc = new Scanner(System.in);
        int clubAnswer = sc.nextInt();

            if(clubAnswer == 1) {*/



      /*  String clubScanAnswer = "";
        boolean isValidLoungeAnswer = false;
        Scanner loungeScan = new Scanner(System.in);
        boolean isLoungeRequired = false;
        while(!inputC) {
            System.out.print("Do you want to add another club? Y/N: ");
            clubScanAnswer = clubScanAnswer.next();

            if(footballTeam.inputChecker(clubScanAnswer, "YesOrNo")) {
                isValidLoungeAnswer = true;
            }
        }
        if(loungeScanAnswer.equalsIgnoreCase("Y")) { isLoungeRequired = true; }*/

        /*try {
            fWriter = new FileWriter("clubs.txt");
            writer = new BufferedWriter(fWriter);
            writer.write();
            writer.newLine();
            writer.close();
        } catch (Exception e) {
            System.out.println("Error!");
        }*/
    }




