import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class HotelTax {

    public static void main (String[] args) throws FileNotFoundException { // You need to throw any potential exception that can happen in your programme

        double defaultTax = 13.0;  //setting the default value for your tax
        double tax = 0.0; // initialize the actual tax value that will be used in the calculations

        Scanner getAnswerScan = new Scanner(System.in); // creating a scanner object that will accept input from the manager
        boolean isValidOption = false; //used to check if the value given is valid

        while(!isValidOption) { //If the manager chooses the wrong option it will continue to loop till a correct response is given
            System.out.print("Do you wish to use the default tax of " + defaultTax + "%? [Y]es/[N]o: "); // Asking the manager which tax he wants to use
            String answer = getAnswerScan.nextLine(); // assign the response to the String variable answer
            if(answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("Yes")) { //Check if the response matches each option for Yes
                tax = defaultTax; //if Yes is the answer, assign the default value to variable tax
                isValidOption = true; // set this to true so the loop will end
            } else if (answer.equalsIgnoreCase("N") || answer.equalsIgnoreCase("No")) { //Check if the response matches all options for No
                Scanner getTaxValueScan = new Scanner(System.in); //Create scanner object to take tax value from manager
                System.out.print("What tax value should I use? "); // Ask the question, how much tax
                tax = getTaxValueScan.nextDouble(); // get this value from the scanner
                isValidOption = true; // set this to true to end the loop
            } else {
                System.out.print("Please choose a valid value [Y]es or [N]o\n"); //if Yes or No not the response, tell them and this will kick off another round
            }
        }

        String fileName = "HotelRoomDetails.txt"; // Assign your filename to a variable
        File hotelDetailsFile = new File(fileName); //Create the File object, using fileName as its file name and location
        FileReader hotelDetailsFileReader = new FileReader(hotelDetailsFile); //Create the FileReader object and assign the File object to it
        Scanner scanner = new Scanner(hotelDetailsFileReader); //Create a Scanner object and assign the FileReader object to it

        String roomType; // Variable to hold the room type
        int numberOfRooms; // Variable to hold the number of rooms booked
        double preTaxCost; // How much those rooms cost before tax
        double costPerRoom; // Cost per room type including tax
        double fullPreTaxRoomValue = 0; // Running value of all rooms pre tax
        double totalRoomTax; //Total tax per room type
        double totalTax = 0; // Running total of all tax
        double roomsCost; // Full value including tax per room type
        double totalFullValue = 0; // Running total of all room costs including tax

        while(scanner.hasNext()) {
            roomType = scanner.next();
            numberOfRooms = scanner.nextInt();
            costPerRoom = scanner.nextDouble();

            //room type, number of bookings, room price, income before tax and tax
            //running totals for the income before tax and tax should

            preTaxCost = costPerRoom * numberOfRooms; // Pre tax cost for this room type for all bookings
            fullPreTaxRoomValue += preTaxCost; // Add value to running cost
            totalRoomTax = (preTaxCost/100)*tax; // Full tax for this room type
            totalTax += totalRoomTax; // Adding this to running total of tax
            roomsCost = preTaxCost + totalTax; // Total cost including tax fo this room type
            totalFullValue += roomsCost; // Add to running total of full rooms value
            
            System.out.printf("\nThere are %s %s rooms booked ", numberOfRooms, roomType); // Print out the information fpr this room type
           System.out.printf("with a PreTax cost of £%.2f and a total cost of £%.2f", preTaxCost, roomsCost); // As above
        }

        System.out.printf("\n\nThe total revenue for these rooms pre tax is £%.2f", fullPreTaxRoomValue); // Print out full values
        System.out.printf("\nThe total value of room including tax is £%.2f and that includes the full tax of £%.2f", totalFullValue, totalTax); // As above
    }
}
