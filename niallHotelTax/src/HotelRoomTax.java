import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class HotelRoomTax {

	/**
	 * A new room tax system is required to help managers of a hotel determine how much tax they must pay. 
	 * Every time the system runs, the tax rate may be specified by the managers or set to a suitable default which is 20%.
	 * The outcome will show the income and tax from the rooms and then will add the total from these rooms.
	 * @param args
	 * @throws FileNotFoundException
	 * 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// I set the standard tax at 20 which is the percentage of the standard tax.
		double tax = 20;
		
		System.out.println("-- Room Tax System ---");
		Scanner getAnswerScan = new Scanner (System.in); 
		boolean isValidOption = false;
		
		//I set up my Loop here to allow the application to interpret the answers from the user.
		while(!isValidOption) {
			System.out.print("Specify Custom Tax Rate? [Y|N]: " );
			String answer = getAnswerScan.nextLine();
			
			if (answer.equalsIgnoreCase("Y") || (answer.equalsIgnoreCase("Yes"))) {
				System.out.print("Specify Tax Rate (%) : ");
				Scanner getTaxValueScan = new Scanner (System.in);
				tax = getTaxValueScan.nextDouble();
			    isValidOption = true;
			    getTaxValueScan.close();
			} else if (answer.equalsIgnoreCase("N") || (answer.equalsIgnoreCase("No"))){
			    isValidOption = true;

			}
			else {
				System.out.print("Please choose a valid value [Y]es or [N]o");
			}			
		}
		// The file reader was set up in this part of my code to allow  the application to read the text from "rooms.txt".
		
		String fileName = "rooms.txt";
		File roomDetails = new File(fileName);
		FileReader roomDetailsFileReader = new FileReader(roomDetails);
		Scanner scanner = new Scanner(roomDetailsFileReader);
		
		//I set my variables in the code below:
		String roomType;
		int numberOfRooms;
		double preTaxCost = 0;
		double costPerRoom;
		double totalRoomTax = 0;
		double totalTax = 0;
		double totalIncome = 0;

		
		// A while loop for the scanner was used to make sure that the file reader's values were imported into the application.
		while(scanner.hasNext()) {
			roomType = scanner.next();
			numberOfRooms = scanner.nextInt();
			costPerRoom = scanner.nextDouble();
			
			//My Calculations are in the code below:
			preTaxCost = costPerRoom * numberOfRooms;
			totalIncome += preTaxCost;
			totalRoomTax = (preTaxCost/100)*tax; 
            totalTax += totalRoomTax;
            
         // Finally output the values to the user using the calculations and quoted text.
            System.out.printf("\nRoom Type: %s, Bookings: %s, Room Price: £%.2f, Income:£%.2f, Tax: £%.2f " ,roomType,numberOfRooms,costPerRoom, preTaxCost,totalRoomTax);
            
	}
		// The Scanner we used for the while loop was closed.
		scanner.close();
		
		//The Total Income and Tax is printed out below: 
		System.out.printf("\n\nTotal Income: £%.2f",totalIncome);
		System.out.printf("\nTotal Tax: £%.2f",totalTax);

  }
}           
		
	
