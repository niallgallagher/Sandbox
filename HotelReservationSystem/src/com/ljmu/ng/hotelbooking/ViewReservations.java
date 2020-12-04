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
		System.out.println("You have selected, 'View Room Reservations'");
		
		Scanner emailScanner = new Scanner(System.in);
		System.out.print("Email address to look up: ");
		
		emailAddress = emailScanner.next();
		
		List<HotelRoom> hotelRooms = ReadMyFile.getHotelRoomList();
		
		System.out.println("Your List of hotel Rooms contains " + hotelRooms.size() + " rooms");
		
		for(HotelRoom hotelRoom: hotelRooms) {
			if(hotelRoom.reservationDetail.equalsIgnoreCase(emailAddress)) {
				System.out.print("Reservation Details for " + emailAddress + " " + hotelRoom.toString() + "]");
				foundReservation = true;
			}
		}
		
		if(!foundReservation) { 
			System.out.println("No reservation found for " + emailAddress); 
		}
		
		emailScanner.close();
	}
}
