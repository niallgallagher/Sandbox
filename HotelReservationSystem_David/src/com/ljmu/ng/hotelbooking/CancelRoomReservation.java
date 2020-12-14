/**
 * 
 */
package com.ljmu.ng.hotelbooking;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

/**
 * @author niall
 *
 *The class below was used to create the canceling system for the program.
 */
public class CancelRoomReservation {
	private static final String YES = "Y";
	private static final String NO = "N";

	public CancelRoomReservation() {}


	public static void cancelRoomReservation() throws IOException {

		//The program reads out instructions for the user to follow, 
		//The user responds with their email to cancel the reservation.
		System.out.println("**** Cancel Room ****");

		System.out.print("Email address to look up: ");
		Scanner emailAddressScanner = new Scanner(System.in);
		String emailAddress = emailAddressScanner.next();

		String roomNum = ViewReservations.getRevervationsByEmailAddress(emailAddress);
		System.out.print("Room number to cancel: " + roomNum);

		//The system confirms that the user wants to cancel their reservation.
		//The 'if/else' statement is used to act on the users response which is read through the scanner.
		System.out.print("\n\nAre you sure you want to cancel reservation? Y/N: ");
		Scanner cancelReservationScanner = new Scanner(System.in);
		String choice = cancelReservationScanner.next();
		if(choice.equalsIgnoreCase(YES)) {
			cancelReservation(emailAddress, roomNum);
		} else if (choice.equalsIgnoreCase(NO)) {
			Menu.GenerateMenu();
		} else {
			System.out.println("Please choose a valid option, Y or N");
		}
		cancelReservationScanner.close();
		emailAddressScanner.close();

	}

	//The method is replacing the word 'free' to the users email address in the file document.
	private static void cancelReservation(String emailAddress, String roomNum) throws IOException {
		String fileName = "rooms.txt";
		String operation = "cancel";
		Map<String,String> fileData = FileOperations.convertFile(fileName, roomNum, emailAddress, operation); //this replaces the full file reader/buffer operations and moves to the FileOperations class
		String oldLine = "";
		String newLine = "";
		String fileDataString = "";

		for(String lineToReplace: fileData.keySet()) {
			System.out.println("Line to replace[" + lineToReplace + "]");
			oldLine = lineToReplace;
			fileDataString = fileData.get(lineToReplace);
		}

		newLine = oldLine.replace(emailAddress,"free");


		String newFileData = fileDataString.replaceAll(oldLine, newLine);

		FileWriter writer = new FileWriter(fileName);
		System.out.println("new room: "+ fileData);
		writer.append(newFileData);
		writer.flush();
		writer.close();
	}
}
