package com.ljmu.niallgallagher.security;

import java.io.*;
import java.util.Scanner;

public class MyFileReader {

    private static File myFile = new File("myNumberFile.txt");

    public MyFileReader() {}

    public static void readMyFile() throws FileNotFoundException {
        Scanner sc = new Scanner(myFile);

        while(sc.hasNext()) {
            System.out.println(sc.next());
        }
    }
}
