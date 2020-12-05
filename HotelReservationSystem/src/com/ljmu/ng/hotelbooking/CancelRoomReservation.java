/**
 * 
 */
package com.ljmu.ng.hotelbooking;

import java.util.Scanner;

/**
 * @author niall
 *
 */
public class CancelRoomReservation {

	private static final String YES = "Y";
	private static final String NO = "N";


	public CancelRoomReservation() {}
	
	public static void cancelRoomReservation() {

		System.out.println("You have selected, 'Cancel a Room'");

		System.out.print("Email address to look up: ");
		Scanner emailAddressScanner = new Scanner(System.in);
		String emailAddress = emailAddressScanner.next();
		emailAddressScanner.close();

		ViewReservations.getRevervationsByEmailAddress(emailAddress);

		System.out.print("Are you sure you want to cancel reservation? Y/N: ");
		Scanner cancelReservationScanner = new Scanner(System.in);
		String choice = cancelReservationScanner.next();
		if(choice.equalsIgnoreCase(YES)) {
			//cancel reservation
		} else if (choice.equalsIgnoreCase(NO)) {
			//Do nothing
		} else {
			System.out.println("Please choose a valid option, Y or N");
		}
	}
}
