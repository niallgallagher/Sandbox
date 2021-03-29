package com.ljmu.niallgallagher;

public class MyCarRunner {
    public static void main(String[] args) {
        MyChar myChar = new MyChar('c');

        System.out.println(myChar.isVowel ());

        System.out.println(myChar.isNumber());

         System.out.println(myChar.isAlphabet());

        MyChar.printLowerCaseAlphabets();

        MyChar.printUpperCaseAlphabets();
    }
}
