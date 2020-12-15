/**
 * 
 */
package com.ljmu.ng.hotelbooking;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author niall
 *My hotel reservation program is split into 7 classes to help complete 
 *multiple repeatable tasks to ensure that the program runs efficiently.
 *
 *The class below was used to create the menu system for the program.
 */
public class Menu {

	public Menu() {}

	//The method "GenerateMenu" is declared below to help develop an interactive menu for the user.
	public static void GenerateMenu() {
		System.out.print("\n--Room Booking System--");
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

			choice = userChoice.next().toLowerCase();

			//The 'if/else statement' is used to ensure the user places a correct input.		
			if(choice.equals("1") || choice.equals("2") || choice.equals("3")
					|| choice.equals("q")) {
				isValidChoice = true;
			} else {
				System.out.print("Please choose a Valid option!");
			}
		}
		//The "switch" function was used so that the users input would go straight to the method that they requested.
		switch (choice) {
		case "1" : {
			try {
				ReserveRoom.reserveARoom();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		}

		case "2" : {
			try {
				CancelRoomReservation.cancelRoomReservation();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		}

		case "3" : {
			ViewReservations.viewReservations();
			break;
		}

		case "q" : {
			System.out.print("\nGoodbye!");
			break;
		}

		default : {
			break;	
		}

		}
	}
}
