import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class HotelRoomTax {

	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// I set my two taxes as double's one with the standard 20% the other with 0 to allow use specification.
		
		double defaultTax = 20.0;
		double tax = 0;
		
		//I set up my Loop here to allow the programme to interpret the answers from the user.
		System.out.println("-- Room Tax System ---");
		Scanner getAnswerScan = new Scanner (System.in); 
		boolean isValidOption = false;
		
		while(!isValidOption) {
			System.out.print("Do you wish to use the defualt tax of " + defaultTax + "% [Y]es or [N]o: " );
			String answer = getAnswerScan.nextLine();
			
			if (answer.equalsIgnoreCase("Y") || (answer.equalsIgnoreCase("Yes"))) {
				tax = defaultTax;
				isValidOption = true;
				
			}
			else if (answer.equalsIgnoreCase("N") || (answer.equalsIgnoreCase("No"))){
				Scanner getTaxValueScan = new Scanner (System.in);
				System.out.print("What tax value should I use? ");
				tax = getTaxValueScan.nextDouble();
				isValidOption = true;
			}
			else {
				System.out.print("Please choose a valid value [Y]es or [N]o");
			}			
		}
		// The file reader was set up in this part of my code to allow  the programme to read the text from "HotelRoomDetails.txt".
		
		String fileName = "rooms.txt";
		File roomDetails = new File(fileName);
		FileReader roomDetailsFileReader = new FileReader(roomDetails);
		Scanner scanner = new Scanner(roomDetailsFileReader);
		
		//I set my variables in the code below:
		
		String roomType;
		int numberOfRooms;
		double preTaxCost;
		double costPerRoom;
		double fullPreTaxRoomValue = 0;
		double totalRoomTax;
		double totalTax = 0;
		double roomsCost;
		double totalFullValue = 0;
		
		// a while scanner was used to make sure that the file reader's values were imported into the programme.
		
		while(scanner.hasNext()) {
			roomType = scanner.next();
			numberOfRooms = scanner.nextInt();
			costPerRoom = scanner.nextDouble();
			
			//My Calculations are in the code below:
			preTaxCost = costPerRoom * numberOfRooms;
			fullPreTaxRoomValue += preTaxCost;
			totalRoomTax = (preTaxCost/100)*tax; 
            totalTax += totalRoomTax;
            roomsCost = preTaxCost + totalTax;
            totalFullValue += roomsCost;
            
       // finally we use the output the values to the user using the calculations and quoted text.
            System.out.printf("\nThere are %s %s rooms booked ", numberOfRooms, roomType); 
            System.out.printf("with a PreTax cost of £%.2f and a total cost of £%.2f", preTaxCost, roomsCost); 
         }

         System.out.printf("\n\nThe total revenue for these rooms pre tax is £%.2f", fullPreTaxRoomValue); 
         System.out.printf("\nThe total value of room including tax is £%.2f and that includes the full tax of £%.2f", totalFullValue, totalTax); 
     }
            
		
}


