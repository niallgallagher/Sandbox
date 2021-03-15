package com.ljmu.niallgallagher;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        //int[] numArray = createArrayFromNumber(3388766325L);

        //System.out.println("Value from generateSecurityNumbers: "+ generateSecuritynumbers());
        //Long myNum = generateRandomNumber();
        //System.out.println("MyRandom Number is " + generateRandomNumber());
        String number = "4624652837";
        char[] myNumberArray = number.toCharArray();
        for(int i=0;i<myNumberArray.length;i++) {
            System.out.println(myNumberArray[i]);
        }

        for(int i=1;i<=myNumberArray.length;i++) {
            System.out.println(myNumberArray[i]);
            int val = Integer.valueOf(i)*2;
            System.out.print(val);
            i++;
        }

    }

    private static long generateSecuritynumbers() {
        return 6666666;
    }

    private static String generateRandomNumber() {
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i <= 9; i++) {
            int rndNum = r.nextInt(10);
            sb.append(rndNum);
           // System.out.println(rndNum);
        }
        //System.out.println("Random Long: " + sb.toString());
        return sb.toString();
    }

    private static int[] createArrayFromNumber(Long num){

        String temp = Long.toString(num);
        int[] numbers = new int[temp.length()];
        for (int i = 0; i < temp.length(); i++) {
            numbers[i] = temp.charAt(i) - '0';
            System.out.println(numbers[i]);
        }

        return numbers;
    }
}
