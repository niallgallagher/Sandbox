package com.ljmu.niallgallagher;

import java.io.*;

public class Main {
    //My class variables are declared below.
    private static final String textFileName = "NiallGallagherCW1_Part_1_Plain_Text.txt";
    private static final String keyword = "SECURE";

    // The main method is calling the method "saveEncryptedDataToFile.
    public static void main(String[] args) {
        saveEncryptedDataToFile();
    }

    //The generateKey method is generating the secure key which the cipher will use to encrypt the text.
    static String generateKey(String str, String key) {

        int x = str.length();

        for (int i = 0; ; i++) {
            if (x == i)
                i = 0;
            if (key.length() == str.length())
                break;
            key += (key.charAt(i));
        }
        return key;
    }

    // The cipherText method is performing the encryption algorithm.
    static String cipherText(String str, String key) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            //The cipher is now able to read spaces, commas and full stops
            if (str.charAt(i) != ' ' && str.charAt(i) != ',' && str.charAt(i) != '.') {
                // converting in range 0-25
                int x = (str.charAt(i) + key.charAt(i)) % 26;
                //The cipher is now able to read both upper and lower case text
                if (Character.isUpperCase(str.charAt(i))) {
                    x += 'A';
                } else {
                    x += 'a';
                }
                // The encryption data is saved through the StringBuffer
                sb.append((char) (x));
            } else {
                sb.append(' ');
            }

        }
        return sb.toString();
    }

    // The saveEncryptedDataToFile method is reading my text file, applying the encryption cipher and printing the results in a separate text file.
    private static void saveEncryptedDataToFile() {
        System.out.println("Encrypting text......");

        StringBuffer inputBuffer = new StringBuffer();
        try (BufferedReader br = new BufferedReader(new FileReader(textFileName))) {
            String line;
            while ((line = br.readLine()) != null) {

                inputBuffer.append(line);
                inputBuffer.append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] originalTextArray = inputBuffer.toString().split(System.lineSeparator());
        System.out.println("Writing encrypted text to file......");

        //Writing input buffer to the encryption file.
        try {
            FileOutputStream fileOut = new FileOutputStream("NiallGallagherCW1_Part_1_Cipher_Text.txt");

            for (int i = 0; i < originalTextArray.length; i++) {
                String key = generateKey(originalTextArray[i], keyword);
                fileOut.write(cipherText(originalTextArray[i], key).getBytes());
                fileOut.write((System.lineSeparator().getBytes()));
            }
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Printing message to the user that the encryption is complete.
        System.out.println("Finished writing encrypted data to file");
    }

}