/**
 * 
 */
package com.ljmu.ng.hotelbooking;

import java.util.List;
import java.util.Scanner;

/**
 * @author niall
 *
 */
public class ViewReservations {	
	
	private static String emailAddress;
	private static boolean foundReservation = false;

	public ViewReservations() {}
	
	public static void viewReservations() {
		System.out.println("**** View Reservations ****");
		
		Scanner choice = new Scanner(System.in);

		System.out.println("What do you want to view?\n\n1. All Rooms\n2. Only Reserved Rooms\n3. Specific booking by Email Address\n\n ");
		int ans = choice.nextInt();

		switch(ans) {
			case 1 : {
				getAllRoomDetails();
				break;
			}

			case 2 : {
				getAllReservedRoomDetails();
				break;
			}

			case 3 : {
				System.out.print("Email address to look up: ");
				Scanner emailAddressScanner = new Scanner(System.in);
				emailAddress = emailAddressScanner.next();
				getRevervationsByEmailAddress(emailAddress);
				emailAddressScanner.close();
				break;
			}
		}
		choice.close();

	}

	private static void getAllRoomDetails() {
		System.out.println("You have called the getAllRoomDetails method");
	}

	private static void getAllReservedRoomDetails() {
		System.out.println("You have called the getAllReservedRoomDetails method");
	}


	private static void getRevervationsByEmailAddress(String emailAddress) {

		List<HotelRoom> hotelRooms = ReadMyFile.getHotelRoomList();

		for(HotelRoom hotelRoom: hotelRooms) {
			if(hotelRoom.reservationDetail.equalsIgnoreCase(emailAddress)) {
				System.out.print("Reservation Details for " + emailAddress + " " + hotelRoom.toString() + "]");
				foundReservation = true;
			}
		}

		if(!foundReservation) {
			System.out.println("No reservation found for " + emailAddress);
		}
	}
}
