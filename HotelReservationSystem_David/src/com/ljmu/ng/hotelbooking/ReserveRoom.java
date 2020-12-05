/**
 * 
 */
package com.ljmu.ng.hotelbooking;

import java.util.Scanner;

/**
 * @author niall
 *
 */
public class ReserveRoom {
	
	public ReserveRoom() {}
	
	public static void reserveARoom() {

		System.out.println("You have selected, 'Reserve a Room");

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
	
	private static void reserveRoom(int roomNumber, String emailAddress) {
		//Reserve the room by reading the line for the room you are booking
		//Use a fileWriter to replace that line with a new line that now contains
		//the email address instead of the work free
	}

}
