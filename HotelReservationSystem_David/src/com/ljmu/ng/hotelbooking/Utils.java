package com.ljmu.ng.hotelbooking;

import java.util.List;

public class Utils {
	
	private static final String YES = "Y";
	private static final String NO = "N";
	private static final String ROOMNUM = "RoomNum";
	
	public Utils() {}
	
	
	
	public static boolean inputChecker(String input, String inputType) {
		
		switch(inputType) {
		
			case "YesOrNo" : {
				if(input.equalsIgnoreCase(YES) || input.equalsIgnoreCase(NO) ) {
					return true;
				} else {
					return false;					
				}
			}
		
		
		}
		return true;
	}
	
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
