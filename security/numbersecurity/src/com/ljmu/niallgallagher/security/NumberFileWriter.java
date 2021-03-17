package com.ljmu.niallgallagher.security;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class NumberFileWriter {

    static String fileName = "myNumberFile.txt";
    static File myNumberFile = new File(fileName);
    static FileWriter fw;

    public NumberFileWriter() {}

    public static void writeNumbersToFile(String number) throws IOException {
        try{
            fw = new FileWriter(fileName);
            fw.write(number);
        } catch (IOException ioe) {
            System.out.print("ISsue writing to file: " + ioe);
        } finally {
            fw.close();
        }
    }
}
