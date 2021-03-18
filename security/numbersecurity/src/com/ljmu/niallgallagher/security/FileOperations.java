package com.ljmu.niallgallagher.security;

import javax.xml.validation.Validator;
import java.io.*;
import java.util.Random;

public class FileOperations {

    private static String myNumberFileName = "myNumberFile.txt";
    private static String externalFileName = "1000numbers.txt";
    static FileWriter fw;
    private static final String VALID = "VALID";
    private static final String INVALID = "INVALID";
    private static final String SPACE = " ";

    public FileOperations() {
    }

    public static void writeRawNumbersToFile(int numberOfNums) throws IOException {

        StringBuffer sb = new StringBuffer();
        Random random = new Random();

        for (int i = 0; i < numberOfNums; i++) {
            sb = new StringBuffer();
            for (int j = 0; j < 10; j++) {
                sb.append(random.nextInt(9));
            }
            System.out.println("Writing Number to file [" + sb.toString() + "]");
            try {
                fw = new FileWriter(myNumberFileName, true);
                fw.write(sb.toString() + "\n");
            } catch (IOException ioe) {
                System.out.print("Issue writing to file: " + ioe);
            } finally {
                fw.close();
            }
        }
    }

    public static void addSecurityNumberToRawNumbers() {
        try {
            BufferedReader file = new BufferedReader(new FileReader(myNumberFileName));
            StringBuffer inputBuffer = new StringBuffer();
            String line;

            while ((line = file.readLine()) != null) {
                if(line.length() == 10) {
                    line = GenerateNumbers.getNewNumber(line);
                    inputBuffer.append(line);
                    inputBuffer.append(System.lineSeparator());
                } else {
                    inputBuffer.append(line + SPACE + INVALID);
                    inputBuffer.append(System.lineSeparator());
                }
            }
            file.close();

            FileOutputStream fileOut = new FileOutputStream(myNumberFileName);
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();

        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }

    }

    public static void verifyNumberFile() {
        int validCount = 0, invalidCount = 0;
        try {
            BufferedReader file = new BufferedReader(new FileReader(externalFileName));
            StringBuffer inputBuffer = new StringBuffer();
            String line;

            while ((line = file.readLine()) != null) {
                if(!line.toUpperCase().contains(VALID)) {
                    if(line.length() == 11) {
                        int securityDigit = Integer.valueOf(line.substring(line.length() - 1));
                        String unsecureNumber = line.substring(0, line.length() - 1);
                        int reEvaluatedSecurityNumber = GenerateNumbers.getSecurityNumber((unsecureNumber));
                        if(securityDigit == reEvaluatedSecurityNumber) {
                            inputBuffer.append(line + " VALID");
                            validCount ++;
                        } else {
                            inputBuffer.append(line + " INVALID");
                            invalidCount ++;
                        }
                    } else {
                        inputBuffer.append(line + " INVALID");
                        invalidCount ++;
                    }
                    inputBuffer.append(System.lineSeparator());
                } else {
                    inputBuffer.append(line);
                    inputBuffer.append(System.lineSeparator());
                    if(line.contains(INVALID)) {
                        invalidCount ++;
                    } else {
                        validCount ++;
                    }
                }
            }
            inputBuffer.append("Total Valid: " + validCount);
            inputBuffer.append(System.lineSeparator());
            inputBuffer.append("Total InValid: " + invalidCount);
            file.close();

            FileOutputStream fileOut = new FileOutputStream(externalFileName);
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();

        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }
    }
}
