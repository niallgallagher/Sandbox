/**
 * 
 */
package com.ljmu.ng.hotelbooking;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author niall
 * The class below was used to help create different operations for the program.
 */
public class FileOperations {

//The variables were declared below to read the file 'rooms.txt'.
	private static String fileName = "rooms.txt";
	private static File file;
	public static FileReader fileReader;
	public static Scanner fileScanner;
	
// The array below was created to separate the columns on the word document.
	private static List<HotelRoom> hotelRooms = new ArrayList<>();
	private static boolean hasBalcony;
	private static boolean hasLounge;
	private static boolean isReserved;

	private static final String FREE = "free";
	
	public FileOperations() {}
//**********************************	
	public static List<HotelRoom> getHotelRoomList() {
		try {
			fileReader = new FileReader(fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//*********************************
		fileScanner = new Scanner(fileReader);
		
		while(fileScanner.hasNext()) {
			//System.out.println(fileScanner.nextLine());
			String[] line = fileScanner.nextLine().split(" ");
			System.out.print("This is the line: " + line[0]);
			int roomNum = Integer.valueOf(line[0]);
			String roomType = line[1];
			double roomPrice = Double.valueOf(line[2]);
			if(line[3].equals("true")) { hasBalcony = true; } else { hasBalcony = false; }
			if(line[4].equals("true")) { hasLounge = true; } else { hasLounge = false; }
			String reservationDetail = line[5];
			if(reservationDetail.equalsIgnoreCase(FREE)) { isReserved = false; } else { isReserved = true; }

			HotelRoom hotelRoom = new HotelRoom(roomNum, roomType, hasLounge, hasBalcony, roomPrice, isReserved, reservationDetail);
			hotelRooms.add(hotelRoom);
		}		
		
		return hotelRooms;
	}
	
	public static Map<String,String> convertFileForReservation(String fileName, String roomNum) throws FileNotFoundException {
		Map<String, String> fileContentsMap = new HashMap<>();
		String fileString = "";
		String lineToReplace = "";
		Scanner sc = new Scanner(new File(fileName));
        //instantiating the StringBuffer class
        StringBuffer buffer = new StringBuffer();
        //Reading lines of the file and appending them to StringBuffer
        while (sc.hasNextLine()) {
        	String line = sc.nextLine();
        	String[] lineArray = line.split(" ");
        	buffer.append(line + System.lineSeparator());
        	if(lineArray[0].equals(roomNum)) {
        		lineToReplace = line;
        	}
        }
        fileString = buffer.toString();
        fileContentsMap.put(lineToReplace, fileString);
		return fileContentsMap;
	}

	public static Map<String,String> convertFileForCancellation(String fileName, String emailAddress) throws FileNotFoundException {

		return null;
	}
}
