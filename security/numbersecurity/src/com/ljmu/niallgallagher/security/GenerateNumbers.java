package com.ljmu.niallgallagher.security;

import java.io.IOException;

public class GenerateNumbers {

    public GenerateNumbers() {}

    public static String getNewNumber(String num) throws IOException {
        return num.concat(String.valueOf(getSecurityNumber(num)));
    }

    public static int getSecurityNumber(String num) throws IOException {

        int[] numArray = new int[num.length()];

        for (int i = 0; i < num.length(); i++){
            numArray[i] = num.charAt(i) - '0';
        }

        int runningTotal = 0;

        for (int i=1;i<numArray.length;i++) {

            int val = numArray[i] * 2;
            if(val > 9) {
                val = val-9;
            }
            runningTotal += numArray[i-1] + val;
            i++;
        }
        int securityNumber = (runningTotal * 9)%10;

        return securityNumber;
    }
}