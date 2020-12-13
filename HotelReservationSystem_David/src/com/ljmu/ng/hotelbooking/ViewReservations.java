package com.ljmu.ng.hotelbooking;

import java.util.List;
import java.util.Scanner;

/**
 * @author niall
 *
 *The class below was used to create the 'view reservations' for the program.
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

		// I used a switch option to give the user variety in whether they want to view reserved rooms, empty rooms or all rooms.
		// I allowed the user an option to view their booking by entering their email address.
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
			break;
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

	//The 'getAllRoomDetails' method below gets all the rooms from the file 'rooms.txt' using an array 'hotelRooms'.
	//The array can be viewed more clearly in the class 'FileOperations.java'
	public static void getAllRoomDetails() {
		System.out.println("You have selected 'View All Rooms': ");
		List<HotelRoom> hotelRooms = FileOperations.getHotelRoomList();
		System.out.print("Room No.   Type          Balcony?        Lounge?       Price        Reservered?\n");
		for(HotelRoom h: hotelRooms) {
			System.out.print(h.roomNum + "        " + h.roomType + "        " + h.balcony + "         " + h.lounge + "           " + h.price + "          " + h.isBooked + "\n");
		}

	}
	// The for loop runs through the hotel rooms and prints out the rooms that are "booked".
	private static void getAllReservedRoomDetails() {
		System.out.println("This is the list of all rooms currently reserved.\n");
		List<HotelRoom> hotelRooms = FileOperations.getHotelRoomList();
		System.out.print("Room No.   Type          Balcony?        Lounge?       Price        Reservered?\n");
		for (HotelRoom h : hotelRooms) {
			if (h.isBooked) {
				System.out.print(h.roomNum + "        " + h.roomType + "        " + h.balcony + "         " + h.lounge + "           " + h.price + "          " + h.isBooked + "\n");
			}
		}
	}
	// The for loop runs through the hotel rooms and prints out the rooms that are "free".
	public static void getAllFreeRooms() {
		System.out.println("This is the list of all rooms currently free.\n");
		List<HotelRoom> hotelRooms = FileOperations.getHotelRoomList();
		System.out.print("Room No.   Type          Balcony?        Lounge?       Price\n");
		for (HotelRoom h : hotelRooms) {
			if (!h.isBooked) {
				System.out.print(h.roomNum + "        " + h.roomType + "        " + h.balcony + "         " + h.lounge + "           " + h.price +"\n");
			}
		}
	}
	public static void getAllFreeRoomsMatchingCriteria(String type, boolean balcony, boolean lounge) {
		String balconyString = Boolean.toString(balcony);
		String loungeString = Boolean.toString(lounge);

		System.out.println("The is a list of all currently free rooms\n");
		List<HotelRoom> hotelRooms = FileOperations.getHotelRoomList();
		System.out.print("Room No.   Type          Balcony?        Lounge?       Price\n");
		for (HotelRoom h : hotelRooms) {
			if (!h.isBooked && h.roomType.equalsIgnoreCase(type) && Boolean.toString(h.balcony).equals(balconyString) 
					&& Boolean.toString(h.lounge).equals(loungeString)) {

				System.out.print(h.roomNum + "        " + h.roomType + "        " + h.balcony + "         " + h.lounge + "           " + h.price +"\n");
			}
		}
	}

	//The method below gets the booked rooms via email address and prints out the rooms.
	public static String getRevervationsByEmailAddress(String emailAddress) {

		List<HotelRoom> hotelRooms = FileOperations.getHotelRoomList();
		String roomNum = "";

		for(HotelRoom hotelRoom: hotelRooms) {
			if(hotelRoom.reservationDetail.equalsIgnoreCase(emailAddress)) {
				roomNum = String.valueOf(hotelRoom.roomNum);
				System.out.print("\nReservation Details for " + emailAddress + "\n" + hotelRoom.toString() + "]\n");
				foundReservation = true;
			}
		}
		//The system will allow user to try again if they make a mistake with typing their email address.
		if(!foundReservation) {
			System.out.println("No reservation found for " + emailAddress);
		}

		return roomNum;
	}
}
