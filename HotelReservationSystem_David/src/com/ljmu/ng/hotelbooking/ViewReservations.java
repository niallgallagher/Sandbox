package com.ljmu.ng.hotelbooking;

import java.util.List;
import java.util.Scanner;

/**
 * @author niall
 *
 */
public class ViewReservations {

	private static boolean foundReservation = false;

	public ViewReservations() {}
	
	public static void viewReservations() {
		System.out.println("**** View Reservations ****");
		
		Scanner choice = new Scanner(System.in);

		System.out.println("What do you want to view?\n\n1. All Rooms\n2. Only Reserved Rooms\n3. Show all Free Rooms\n"
				+ "4. Specific booking by Email Address\n\n ");
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
			
			case 3: {
				getAllFreeRooms();
			}

			case 4 : {
				System.out.print("Email address to look up: ");
				Scanner emailAddressScanner = new Scanner(System.in);
				String emailAddress = emailAddressScanner.next();
				getRevervationsByEmailAddress(emailAddress);
				emailAddressScanner.close();
				break;
			}
		}
		choice.close();

	}

	public static void getAllRoomDetails() {
		System.out.println("You have called the getAllRoomDetails method");
		List<HotelRoom> hotelRooms = FileOperations.getHotelRoomList();
		System.out.print("Room No.   Type          Balcony?        Lounge?       Price        Reservered?\n");
		for(HotelRoom h: hotelRooms) {
			System.out.print(h.roomNum + "        " + h.roomType + "        " + h.balcony + "         " + h.lounge + "           " + h.price + "          " + h.isBooked + "\n");
		}

	}

	private static void getAllReservedRoomDetails() {
		System.out.println("The is a list of all currently reserved rooms\n");
		List<HotelRoom> hotelRooms = FileOperations.getHotelRoomList();
		System.out.print("Room No.   Type          Balcony?        Lounge?       Price        Reservered?\n");
		for (HotelRoom h : hotelRooms) {
			if (h.isBooked) {
				System.out.print(h.roomNum + "        " + h.roomType + "        " + h.balcony + "         " + h.lounge + "           " + h.price + "          " + h.isBooked + "\n");
			}
		}
	}

	public static void getAllFreeRooms() {
		System.out.println("The is a list of all currently free rooms\n");
		List<HotelRoom> hotelRooms = FileOperations.getHotelRoomList();
		System.out.print("Room No.   Type          Balcony?        Lounge?       Price\n");
		for (HotelRoom h : hotelRooms) {
			if (!h.isBooked) {
				System.out.print(h.roomNum + "        " + h.roomType + "        " + h.balcony + "         " + h.lounge + "           " + h.price +"\n");
			}
		}
	}


	public static void getRevervationsByEmailAddress(String emailAddress) {

		List<HotelRoom> hotelRooms = FileOperations.getHotelRoomList();

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
