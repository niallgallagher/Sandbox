package com.ljmu.niallgallagher;

import java.io.*;
import java.util.Random;

public class Main {

    // The main method is declaring the random original number and the verification number as a string.
    public static void main(String[] args) {

        String secureNumber = "";
        String myNumber = getRandomNumber();

        try {
            secureNumber = getUpdatedNumber(myNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            writeNumberToFile(secureNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    // The random number method is generating a random original number and appending it to a string buffer
    private static String getRandomNumber() {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int j = 0; j < 10; j++) {
            sb.append(random.nextInt(9));
        }
        return sb.toString();
    }
    //The getSecurityDigit method is performing my calculations to generate the security digit
    public static int getSecurityDigit(String num) {

        int runningTotal = 0;
        boolean isSecure = false;
        int securityDigit = 0;
        int[] numArray = new int[num.length()];

        for (int i = 0; i < num.length(); i++){
            //Extract each digit from string, convert to an int (subtracting 0 from char) and store in the int array
            numArray[i] = num.charAt(i) - '0';
        }

        for (int i=1;i<numArray.length;i++) {
            if(numArray.length == 11) {
                securityDigit = numArray[numArray.length - 1];
                isSecure = true;
            }
            int val = numArray[i] * 2;
            if(val > 9) {
                val = val-9;
            }
            runningTotal += numArray[i-1] + val;
            i++;
        }
        if(isSecure) {
            runningTotal += securityDigit;
        }
        return (runningTotal * 9)%10;
    }
    //Teh getUpdated number is concatenating my verification number with the original number.
    public static String getUpdatedNumber(String num) throws IOException {
        return num.concat(String.valueOf(getSecurityDigit(num)));
    }
    // The writeNumberToFile method is writing my new security number onto a file.
    private static void writeNumberToFile(String num) throws IOException {
        FileOutputStream fileOut = new FileOutputStream("numbergenerated.txt");
        fileOut.write(num.getBytes());
        fileOut.close();
    }

}



