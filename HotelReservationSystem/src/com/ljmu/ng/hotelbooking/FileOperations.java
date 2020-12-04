/**
 * 
 */
package com.ljmu.ng.hotelbooking;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author niall
 *
 */
public class FileOperations {
	
	private static String fileName = "rooms.txt";
	private static File file;
	public static FileReader fileReader;
	public static Scanner fileScanner;
	
	private static List<HotelRoom> hotelRooms = new ArrayList<>();
	private static boolean hasBalcony;
	private static boolean hasLounge;
	private static boolean isReserved;
	
	public FileOperations() {}
	
	public static List<HotelRoom> getHotelRoomList() {
		try {
			fileReader = new FileReader(fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		fileScanner = new Scanner(fileReader);
		
		while(fileScanner.hasNext()) {
			//System.out.println(fileScanner.nextLine());
			String[] line = fileScanner.nextLine().split(" ");
			int roomNum = Integer.valueOf(line[0]);
			String roomType = line[1];
			double roomPrice = Double.valueOf(line[2]);
			if(line[3].equals("true")) { hasBalcony = true; } else { hasBalcony = false; }
			if(line[4].equals("true")) { hasLounge = true; } else { hasLounge = false; }
			String reservationDetail = line[5];
			if(reservationDetail.equalsIgnoreCase("free")) { isReserved = false; } else { isReserved = true; }

			HotelRoom hotelRoom = new HotelRoom(roomNum, roomType, hasLounge, hasBalcony, roomPrice, isReserved, reservationDetail);
			hotelRooms.add(hotelRoom);
		}		
		
		return hotelRooms;
	}

	public static void updateFile(String reservationDetails) throws IOException {

		FileWriter fileWriter = new FileWriter(fileName);

	}

}
