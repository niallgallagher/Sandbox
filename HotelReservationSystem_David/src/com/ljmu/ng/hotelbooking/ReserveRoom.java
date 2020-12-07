/**
 * 
 */
package com.ljmu.ng.hotelbooking;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;

/**
 * @author niall
 *
 */
public class ReserveRoom {
	
	public ReserveRoom() {}
	
	public static void reserveARoom() throws IOException {

		System.out.println("You have selected, 'Reserve a Room' ");

		String balconyScanAnswer = "";
		boolean isValidBalconyAnswer = false;
		Scanner balconyScan = new Scanner(System.in);
		while(!isValidBalconyAnswer) {
			System.out.print("Do you need a balcony? Y/N: ");
			balconyScanAnswer = balconyScan.next();
			if(Utils.inputChecker(balconyScanAnswer, "YesOrNo")) {
				isValidBalconyAnswer = true;
			}
		}
		balconyScan.close();

		String loungeScanAnswer = ""; 
		boolean isValidLoungeAnswer = false; 
		Scanner loungeScan = new Scanner(System.in); 
		while(!isValidLoungeAnswer) {
		  System.out.print("Do you need a Lounge? Y/N: ");
		  loungeScanAnswer = loungeScan.next(); 
		  if(Utils.inputChecker(loungeScanAnswer, "YesOrNo")) {
			  isValidLoungeAnswer = true; 
		  } 
		}
		loungeScan.close();
	
		System.out.println("These are the available rooms for reservation\n");
		ViewReservations.getAllFreeRooms();

		
		boolean isValidRoom = false;
		int roomChoice = 0;
		while(!isValidRoom) {
			System.out.print("Please choose the room you want to reserve: ");
			Scanner roomChoiceScanner = new Scanner(System.in);
			roomChoice = roomChoiceScanner.nextInt();
			if(Utils.inputChecker(roomChoice, "RoomNum")) {
				isValidRoom = true;
			}
			roomChoiceScanner.close();
		}
		
		System.out.print("Email Address for Reservation: ");
		Scanner emailAddressScanner = new Scanner(System.in);
		String emailAddress = emailAddressScanner.next();
		
		reserveRoom(roomChoice, emailAddress);



	}
	
	private static void reserveRoom(int roomNumber, String emailAddress) throws IOException {
		String fileName = "rooms.txt";
		Map<String,String> fileData = FileOperations.convertFiletoString(fileName, roomNumber); //this replaces the full file reader/buffer operations and moves to the FileOperations class
		String oldLine = "";
		String newLine = "";
		String fileDataString = "";
		
		for(String lineToReplace: fileData.keySet()) {
			oldLine = lineToReplace;
			fileDataString = fileData.get(lineToReplace);
		}
        
		//You need to be take the old line and just replace the word 'free' with the email address to created the new line
		//and then replace the full old line with the new line
		
		
		
        String newFileData = fileDataString.replaceAll(oldLine, newLine);
        
        FileWriter writer = new FileWriter(fileName);
        System.out.println("new room: "+ fileData);
        writer.append(newFileData);
        writer.flush();
	}
}
