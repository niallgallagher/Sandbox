package com.ljmu.niallgallagher.security;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

<<<<<<< HEAD
        //System.out.println("How many numbers do you want to generate? ");
    /*    Scanner scan = new Scanner(System.in);
        int numberOfNums = scan.nextInt();
        Random random = new Random();
        StringBuffer sb;
=======
        System.out.println("What operation do you want to perform?\n");
        System.out.println("1. Generate random unsecure numbers and add to file");
        System.out.println("2. Update existing unsecure file with security digit");
        System.out.println("3. Validate existing secure file");
>>>>>>> 60026fb961f361bb660d97c559656a61d06a6920

        Scanner ans = new Scanner(System.in);
        String userChoice = ans.next();

<<<<<<< HEAD
            NumberFileWriter.writeNumbersToFile(securityNumber);
        */

        MyFileReader.readMyFile();
=======
        switch(userChoice) {
            case "1" :
                System.out.println("How many numbers do you want to generate? ");
                Scanner scan = new Scanner(System.in);
                FileOperations.writeRawNumbersToFile(scan.nextInt());
                break;
            case "2" :
                FileOperations.addSecurityNumberToRawNumbers();
                break;
            case "3" :
                FileOperations.verifyNumberFile();
                break;
        }
>>>>>>> 60026fb961f361bb660d97c559656a61d06a6920
    }
}
