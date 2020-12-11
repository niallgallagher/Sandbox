/**
 * 
 */
package com.ljmu.ng.hotelbooking;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;

/**
 * @author niall
 *
 *The class below was used to create the booking system for the program.
 *
 */
public class ReserveRoom {
	
	public ReserveRoom() {}
	
	public static void reserveARoom() throws IOException {

		System.out.println("You have selected, 'Reserve a Room' ");
		
// The code below asks whether the user wants a balcony using a 'while' loop.
		String balconyScanAnswer = "";
		boolean isValidBalconyAnswer = false;
		Scanner balconyScan = new Scanner(System.in);
		while(!isValidBalconyAnswer) {
			System.out.print("Do you need a balcony? Y/N: ");
			balconyScanAnswer = balconyScan.next();
			System.out.print("You chose [" + balconyScanAnswer + "]");
			if(Utils.inputChecker(balconyScanAnswer, "YesOrNo")) {
				isValidBalconyAnswer = true;
			}
		}		
//Like the code above we do the same but we ask the user whether they want a lounge.	
		String loungeScanAnswer = ""; 
		boolean isValidLoungeAnswer = false; 
		Scanner loungeScan = new Scanner(System.in); 
		while(!isValidLoungeAnswer) {
		  System.out.print("Do you need a Lounge? Y/N: ");
		  loungeScanAnswer = loungeScan.next();
		  System.out.print("You chose [" + loungeScanAnswer + "]");

		  if(Utils.inputChecker(loungeScanAnswer, "YesOrNo")) {
			  isValidLoungeAnswer = true; 
		  } 
		}
// The system shows all the available rooms.	
		System.out.println("These are the available rooms for reservation\n");
		ViewReservations.getAllFreeRooms();

//The room is going to be booked by the user using their email address.
//The Scanner will read the user input and the while loop will show the available rooms.
		boolean isValidRoom = false;
		String roomChoice = "";
		Scanner roomChoiceScanner = new Scanner(System.in);

		while(!isValidRoom) {
			System.out.print("Please choose the room you want to reserve: ");
			roomChoice = roomChoiceScanner.next();
			if(Utils.inputChecker(roomChoice, "RoomNum")) {
				isValidRoom = true;
			}
		}
		
		System.out.print("Email Address for Reservation: ");
		Scanner emailAddressScanner = new Scanner(System.in);
		String emailAddress = emailAddressScanner.next();
		
		reserveRoom(roomChoice, emailAddress);

		balconyScan.close();
		loungeScan.close();
		roomChoiceScanner.close();
		emailAddressScanner.close();

	}
	
	private static void reserveRoom(String roomNumber, String emailAddress) throws IOException {
		String fileName = "rooms.txt";
		Map<String,String> fileData = FileOperations.convertFileForReservation(fileName, roomNumber); //this replaces the full file reader/buffer operations and moves to the FileOperations class
		String oldLine = "";
		String newLine = "";
		String fileDataString = "";
		
		for(String lineToReplace: fileData.keySet()) {
			oldLine = lineToReplace;
			fileDataString = fileData.get(lineToReplace);
		}
//*************************************
		
		newLine = oldLine.replace("free", emailAddress);
	
		//You need to be take the old line and just replace the word 'free' with the email address to created the new line
		//and then replace the full old line with the new line		
        String newFileData = fileDataString.replaceAll(oldLine, newLine);
        
        FileWriter writer = new FileWriter(fileName);
        System.out.println("new room: "+ fileData);
        writer.append(newFileData);
        writer.flush();
        
       System.out.println("Room " + roomNumber + " has been reserved for " + emailAddress);
	}
}
