package com.ljmu.niallgallagher.security;

import java.io.IOException;

public class GenerateSecurityNumber {

    public GenerateSecurityNumber() {}

    public static int getSecurityNumber(String num) throws IOException {
        // Do the code to generate the number and return the integer value

        int[] numArray = new int[num.length()];

        for (int i = 0; i < num.length(); i++){
            numArray[i] = num.charAt(i) - '0';
        }

        /*for (int i : numArray) {
            System.out.println(i);
        }*/

        int runningTotal = 0;

        for (int i=1;i<numArray.length;i++) {
            /*System.out.println("\n\nvalue " +  i + " = " + numArray[i-1]);
            int val1 = numArray[i-1];
            int val2 = numArray[i];
            System.out.println("val1=" + val1);
            System.out.println("val2=" + val2);
            System.out.print("numCharArray value[" + numArray[i] + "]\n");*/
            int val = numArray[i] * 2;
            //System.out.print("val1[" + val + "]\n");
            if(val > 9) {
                val = val-9;
                //System.out.print("val2[" + val + "]\n");
            }
            runningTotal += numArray[i-1] + val;
            //System.out.print("runningTotal in loop[" + runningTotal + "]\n");
            i++;
        }
        //System.out.println("runningTotal = " + runningTotal);
        int verNumber = (runningTotal * 9)%10;
        System.out.println("Verification number is " + verNumber);
        String myNewNum = num.concat(String.valueOf(verNumber));
        System.out.println("Ny num including verification: " + myNewNum);

        System.out.println("Writing Number to file.......");

        NumberFileWriter.writeNumbersToFile(myNewNum);
        return 0;
    }
}
