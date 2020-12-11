package com.ljmu.ng.hotelbooking;

import java.util.List;

//The 'Utils' class was used 

public class Utils {
	
	private static final String YES = "Y";
	private static final String NO = "N";
	private static final String ROOMNUM = "RoomNum";
	
	public Utils() {}
	
	
	
	public static boolean inputChecker(String input, String inputType) {
		System.out.println("You have entered the inputChecker method");		
		switch(inputType) {
		
			case "YesOrNo" : {
				if(input.equalsIgnoreCase(YES) || input.equalsIgnoreCase(NO) ) {
					System.out.println("You entered a valid value");		
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
