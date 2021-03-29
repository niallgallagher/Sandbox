package com.ljmu.niallgallagher.security;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        //System.out.println("How many numbers do you want to generate? ");
    /*    Scanner scan = new Scanner(System.in);
        int numberOfNums = scan.nextInt();
        Random random = new Random();
        StringBuffer sb;

        for(int i=0;i<numberOfNums;i++) {
            sb = new StringBuffer();
            for(int j=0;j<10;j++) {
                sb.append(random.nextInt(9));
            }
            String securityNumber = GenerateSecurityNumber.getSecurityNumber(sb.toString());
            System.out.println("Writing Number to file.......");

            NumberFileWriter.writeNumbersToFile(securityNumber);
        */

        MyFileReader.readMyFile();
    }
}
