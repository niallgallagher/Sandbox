/*
package com.ljmu.niallgallagher.security;

import javax.swing.*;
import java.io.*;

import java.util.Scanner;
public class NumberFileReader{

    static String fileName = "myNumberFile.txt";

    public NumberFileReader(){}

    public static void readNumbersOnFIle(String[] args) throws IOException {
        Scanner fileScan = new Scanner(new File(fileName));
        StringBuffer sb;
        String securityNumber = GenerateSecurityNumber.getSecurityNumber(sb.toString());
        boolean securityNumberIsValid = false;
        while(fileScan.hasNext()) {
            //Every 10 digit number that is divisible by 10 append the Word "Valid" ELse append "Invalid".
            try {

//                    for(securityNumber%10) {
//                            if(securityNumber%10) {
//                                sb.append(("Valid"));
//                            }
//                }
            } catch (NumberFormatException nfe) {
                sb.append(("Invalid"));
            }
        }
        private static void readNumbersOnFile(String roomNumber, String emailAddress) throws IOException {
            String fileName = "MyNumberFile.txt";
            String operation = "reserve";
            String roomNum = String.valueOf(roomNumber);
            String oldLine = "";
            String newLine = "";
            String fileDataString = "";

            if(fileData.size() > 0) {
                for(String lineToReplace: fileData.keySet()) {
                    oldLine = lineToReplace;
                    fileDataString = fileData.get(lineToReplace);
                }
            } else {
                System.out.println("No rooms available with those criteria. Please try again!");
                ReserveRoom.reserveARoom();
            }


            newLine = oldLine.replace("free", emailAddress);
            String newFileData = fileDataString.replaceAll(oldLine, newLine);
            FileWriter writer = new FileWriter(fileName);
            writer.append(newFileData);
            writer.flush();
            writer.close();

        }
    }







    }
}
}*/
