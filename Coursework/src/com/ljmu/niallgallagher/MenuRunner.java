package com.ljmu.niallgallagher;

import java.util.Scanner;


public class MenuRunner {

    public static void main(String[] args) {

        Scanner kay = new Scanner (System.in);

        System.out.println("Please Enter A Number: " );
        int number1 = kay.nextInt();

        System.out.println("Please Enter A Number2: " );
        int number2 = kay.nextInt();

        System.out.println(" Menu: " );
        System.out.println(" 1 - Add: " );
        System.out.println(" 2 - Subtract: " );
        System.out.println(" 3 - Multiply: " );
        System.out.println(" 4 - Divide: " );

        System.out.println(" Enter Choice " );
        int userChoice = kay.nextInt();

        System.out.println("Your Number1 Is "  + number1);
        System.out.println("Your Number2 Is "  + number2);
        System.out.println("Choice: "  + userChoice);

        if (userChoice == 1){
            System.out.println(" Number1 plus Number2: " + (number1 + number2));
        }
        if (userChoice == 2){
            System.out.println(" Number1 minus Number2: " + (number1 - number2));
        }
        if (userChoice == 3){
            System.out.println(" Number1 multiply Number2: " + (number1 * number2));
        }
        if (userChoice == 4){
            System.out.println(" Number1 divide Number2: " + (number1 / number2));
        }
    }

    }


