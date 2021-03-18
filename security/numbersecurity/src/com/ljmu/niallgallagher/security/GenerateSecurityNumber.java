package com.ljmu.niallgallagher.security;

import java.io.IOException;

public class GenerateSecurityNumber {

    public GenerateSecurityNumber() {}

    public static String getSecurityNumber(String num) throws IOException {

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
        int verNumber = (runningTotal * 9)%10;
        System.out.println("Verification number is " + verNumber);
        String myNewNum = num.concat(String.valueOf(verNumber));
        System.out.println("Ny num including verification: " + myNewNum);

        return myNewNum;
    }
}
