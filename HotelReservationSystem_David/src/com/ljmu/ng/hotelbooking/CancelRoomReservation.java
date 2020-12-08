/**
 * 
 */
package com.ljmu.ng.hotelbooking;

import java.util.Scanner;

/**
 * @author niall
 *
 *The class below was used to create the canceling system for the program.
 */
public class CancelRoomReservation {
//We declare the variables.
	private static final String YES = "Y";
	private static final String NO = "N";

	public CancelRoomReservation() {}
	
//The method "cancelRoomReservation" was declared below.
	public static void cancelRoomReservation() {
		
//The program reads out instructions for the user to follow, 
//The user responds with their email to cancel the reservation.
		
		System.out.println("You have selected, 'Cancel a Room'");

		System.out.print("Email address to look up: ");
		Scanner emailAddressScanner = new Scanner(System.in);
		String emailAddress = emailAddressScanner.next();
		emailAddressScanner.close();

		ViewReservations.getRevervationsByEmailAddress(emailAddress);

//The system confirms that the user wants to cancel their reservation.
//The 'if/else' statement is used to act on the users response which is read through the scanner.
		System.out.print("Are you sure you want to cancel reservation? Y/N: ");
		Scanner cancelReservationScanner = new Scanner(System.in);
		String choice = cancelReservationScanner.next();
		if(choice.equalsIgnoreCase(YES)) {
			cancelReservation(emailAddress);
		} else if (choice.equalsIgnoreCase(NO)) {
			Menu.GenerateMenu();
		} else {
			System.out.println("Please choose a valid option, Y or N");
		}
//We close the scanner to make sure that the scanner stops reading.		
		cancelReservationScanner.close();
	}
	
	private static void cancelReservation(String emailAddress) {
		
	}
}
