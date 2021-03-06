/**
 * 
 */
package com.ljmu.ng.hotelbooking;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
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
		//Below I ask the users choice of room.
		System.out.println("\n***** Reserve Room *****");

		String roomTypeChoice = "";
		Scanner roomTypeScanner = new Scanner(System.in);
		boolean roomTypeChoiceIsValid = false;
		while(!roomTypeChoiceIsValid) {
			System.out.print("\nWhat room type do you want? (Single/Double/Suite): ");
			roomTypeChoice = roomTypeScanner.next();
			if(roomTypeChoice.equalsIgnoreCase("single")
					|| roomTypeChoice.equalsIgnoreCase("double")
					|| roomTypeChoice.equalsIgnoreCase("suite")) {
				roomTypeChoiceIsValid = true;
			}
		}

		// The code below asks whether the user wants a balcony using a 'while' loop.
		String balconyScanAnswer = "";
		boolean isValidBalconyAnswer = false;
		Scanner balconyScan = new Scanner(System.in);
		boolean isBalconyRequired = false;
		while(!isValidBalconyAnswer) {
			System.out.print("Do you need a balcony? Y/N: ");
			balconyScanAnswer = balconyScan.next();
			if(Utils.inputChecker(balconyScanAnswer, "YesOrNo")) {
				isValidBalconyAnswer = true;
			}
		}
		if(balconyScanAnswer.equalsIgnoreCase("Y")) { isBalconyRequired = true; }

		//Like the code above we do the same but we ask the user whether they want a lounge.   
		String loungeScanAnswer = ""; 
		boolean isValidLoungeAnswer = false; 
		Scanner loungeScan = new Scanner(System.in);
		boolean isLoungeRequired = false;
		while(!isValidLoungeAnswer) {
			System.out.print("Do you need a Lounge? Y/N: ");
			loungeScanAnswer = loungeScan.next();

			if(Utils.inputChecker(loungeScanAnswer, "YesOrNo")) {
				isValidLoungeAnswer = true; 
			} 
		}
		if(loungeScanAnswer.equalsIgnoreCase("Y")) { isLoungeRequired = true; }
		
		// The system shows all the available rooms.
		List<HotelRoom> availableRooms = ViewReservations.getAllFreeRoomsMatchingCriteria(roomTypeChoice, isBalconyRequired, isLoungeRequired);
		System.out.println("\nAvailable Rooms [" + availableRooms.size() + "]");
		if(!availableRooms.isEmpty()) {
			System.out.println("\nThese are the available rooms for reservation:\n");
			System.out.println("Room Number        Price");
			for(HotelRoom h: availableRooms) {
				System.out.println(" " + h.roomNum + "               " + "�"+ h.price);
			}
		} else {
			System.out.print("No rooms available matching your choice. Please choose again!\n\n");
			ReserveRoom.reserveARoom();
		}

		//The room is going to be booked by the user using their email address.
		//The Scanner will read the user input and the while loop will show the available rooms.
		boolean isValidRoom = false;
		String roomChoice = "";
		Scanner roomChoiceScanner = new Scanner(System.in);

		while(!isValidRoom) {
			System.out.print("\nPlease choose the room number you want to reserve: ");
			roomChoice = roomChoiceScanner.next();
			try {
				int roomNum = Integer.valueOf(roomChoice);
				for(HotelRoom h : availableRooms) {
					if(h.roomNum == roomNum) {
						isValidRoom = true;
					}
				}
			} catch (NumberFormatException nfe) {
				System.out.println("Please enter a valid numeric value for Room Number");
			}			
		}

		System.out.print("Email Address for Reservation: ");
		Scanner emailAddressScanner = new Scanner(System.in);
		String emailAddress = emailAddressScanner.next();

		/*balconyScan.close();
      loungeScan.close();
      roomChoiceScanner.close();
      emailAddressScanner.close();
      roomTypeScanner.close();*/

		reserveRoom(roomChoice, emailAddress);     

		Menu.GenerateMenu();

	}
	//The code below is deciding which line to print depending on whether the users is booking or canceling a room.
	//It'll print the line with the user's email address when the user is booking a room. 
	private static void reserveRoom(String roomNumber, String emailAddress) throws IOException {
		String fileName = "rooms.txt";
		String operation = "reserve";
		String roomNum = String.valueOf(roomNumber);
		Map<String,String> fileData = FileOperations.convertFile(fileName, roomNum, emailAddress, operation); 
		String oldLine = "";
		String newLine = "";
		String fileDataString = "";

		if(fileData.size() > 0) {
			for(String lineToReplace: fileData.keySet()) {
				oldLine = lineToReplace;
				fileDataString = fileData.get(lineToReplace);
			}
		} else {
			System.out.println("No rooms available with those criteria. Please try again!");
			ReserveRoom.reserveARoom();
		}


		newLine = oldLine.replace("free", emailAddress);
		String newFileData = fileDataString.replaceAll(oldLine, newLine);
		FileWriter writer = new FileWriter(fileName);
		writer.append(newFileData);
		writer.flush();
		writer.close();

		System.out.println("\nRoom " + roomNumber + " has been reserved for " + emailAddress);
	}
}