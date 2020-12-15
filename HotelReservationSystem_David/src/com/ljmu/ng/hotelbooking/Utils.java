package com.ljmu.ng.hotelbooking;

import java.util.List;

//The 'Utils' class was used 

public class Utils {

	private static final String YES = "Y";
	private static final String NO = "N";
	private static final String ROOMNUM = "RoomNum";
	private static final String YESNO = "YesOrNo";

	public Utils() {}


	//The boolean below is verifying the users response as valid. 
	public static boolean inputChecker(String input, String inputType) {
		switch(inputType) {

		case YESNO : {
			if(input.equalsIgnoreCase(YES) || input.equalsIgnoreCase(NO) ) {
				return true;
			} else {
				return false;					
			}
		}
		// I need this clarified with David.
		case ROOMNUM : {
			List<HotelRoom> hotelRooms = FileOperations.getHotelRoomList();
			for(HotelRoom hotelRoom : hotelRooms) {
				if(input.equals(String.valueOf(hotelRoom.roomNum))) {
					return true;
				}
			}
			return false;
		}		
		}
		return false;
	}
	//I need this clarified with David.
	public static boolean inputChecker(int input, String inputType) {

		switch(inputType) {

		case ROOMNUM : {
			List<HotelRoom> hotelRooms = FileOperations.getHotelRoomList();
			for(HotelRoom hotelRoom : hotelRooms) {
				if(input == hotelRoom.roomNum) {
					return true;
				}
			}
			return false;
		}


		}
		return true;
	}

}
