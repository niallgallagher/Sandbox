/**
 * 
 */
package com.ljmu.ng.hotelbooking;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author niall
 *
 */
public class Menu {
	
	public Menu() {}
	
	public static void GenerateMenu() {
		System.out.print("--Room Booking System--");
		
		String choice = "";
		
		Scanner userChoice = new Scanner(System.in);
		boolean isValidChoice = false;
		
		while(!isValidChoice) {
			System.out.println("\n\n--Main Menu--");
			System.out.println("1 - Reserve Room");
			System.out.println("2 - Cancel Room ");
			System.out.println("3 - View Room Reservations");
			System.out.println("Q - Quit ");
			System.out.print("Pick : ");
			
			choice = userChoice.next();
			
			if(choice.equalsIgnoreCase("1") || choice.equalsIgnoreCase("2") || choice.equalsIgnoreCase("3")
					|| choice.equalsIgnoreCase("Q")) {
				isValidChoice = true;
			} else {
				System.out.print("Please choose a Valid option");
			}
		}
			
		switch (choice) {
			case "1" : {
				try {
					ReserveRoom.reserveARoom();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			
			case "2" : {
				CancelRoomReservation.cancelRoomReservation();
				break;
			}
			
			case "3" : {
				ViewReservations.viewReservations();
				break;
			}
			
			case "Q" : {
				System.out.print("Goodbye!");
				break;
			}
			
			default : {
				break;	
			}
			
		}
	}
}
