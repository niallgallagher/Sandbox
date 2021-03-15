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

		System.out.println("These are the available rooms for reservation\n");
		ViewReservations.getAllFreeRooms();

		System.out.print("Please choose the room you want to reserve: ");
		Scanner roomChoiceScanner = new Scanner((System.in));
		int roomChoice = roomChoiceScanner.nextInt();
		System.out.print("Email Address for Reservation: ");
		Scanner emailAddressScanner = new Scanner(System.in);
		String emailAddress = emailAddressScanner.next();

		//Update file with email address for the reserved roonm

	}

}
