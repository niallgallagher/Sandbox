
import java.io.*;
import java.util.ArrayList;

public class Encryption_Decryption {

    public class Main {

        private static final String realAlphabet =   "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        private static final String secureWord = "JURGEN";
        private static final String secureAlphabet = "JURGENABCDFHIKLMOPQSTVWXYZ";
        private static final String stringToConvert = "problem solving is great";
        private static final String myName = "Niall Gallagher";

        // My variables are declared below in the main method. They will be used in the encryption/ decryption process.
        public static void main(String[] args) {

            String convertedString = convertStringBeforeEncryption(stringToConvert);
            String convertedName = convertStringBeforeEncryption(myName);
            String encryptedString = encryptString(realAlphabet, secureAlphabet, convertedString);
            String encryptedName = encryptString(realAlphabet, secureAlphabet, convertedName);
            saveEncryptedDataToFile(secureWord, realAlphabet, secureAlphabet, encryptedString, encryptedName);
            saveDecryptedDataToFile();
        }
        //The string method below is replacing every space with no space and converting it to an upper case.
        private static String convertStringBeforeEncryption(String s) {
            return s.replace(" ", "").toUpperCase();
        }
        //**My method below created string arrays and converted them to character arrays.
        // Using the character array
        private static String encryptString(String regAlphabet, String secureAlphabet, String stringToConvert) {

            char[] stringToConvertArray = stringToConvert.toCharArray();
            char[] regularAlphabetArray = regAlphabet.toCharArray();
            char[] secureAlphabetArray = secureAlphabet.toCharArray();

            StringBuffer sb = new StringBuffer();

            for(char c: stringToConvertArray) {
                for(int i=0; i < regularAlphabetArray.length;i++) {
                    if(regularAlphabetArray[i] == c) {
                        sb.append(secureAlphabetArray[i]);
                    }
                }
            }
            return sb.toString();
        }

        private static String decryptString(String regAlphabet, String secureAlphabet, String stringToConvert) {

            char[] stringToConvertArray = stringToConvert.toCharArray();
            char[] regularAlphabetArray = regAlphabet.toCharArray();
            char[] secureAlphabetArray = secureAlphabet.toCharArray();

            StringBuffer sb = new StringBuffer();

            for (char c : stringToConvertArray) {
                for (int i = 0; i < secureAlphabetArray.length; i++) {
                    if (secureAlphabetArray[i] == c) {
                        sb.append(regularAlphabetArray[i]);
                    }
                }
            }
            return sb.toString();
        }
        // I saved my alphabet encryption onto a document file to show my results.
        private static void saveEncryptedDataToFile(String s1, String s2, String s3, String s4, String s5) {

            StringBuffer inputBuffer = new StringBuffer();
            inputBuffer.append(s1);
            inputBuffer.append(System.lineSeparator());
            inputBuffer.append(s2);
            inputBuffer.append(System.lineSeparator());
            inputBuffer.append(s3);
            inputBuffer.append(System.lineSeparator());
            inputBuffer.append(s4);
            inputBuffer.append(System.lineSeparator());
            inputBuffer.append(s5);

            try {
                FileOutputStream fileOut = new FileOutputStream("encrypted.txt");
                fileOut.write(inputBuffer.toString().getBytes());
                fileOut.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Finished writing encrypted data to file");
        }
        // I saved my alphabet decryption onto a document file to show my results.
        private static void saveDecryptedDataToFile() {

            ArrayList<String> stringArray = new ArrayList<>();
            try {
                BufferedReader file = new BufferedReader(new FileReader("encrypted.txt"));
                StringBuffer inputBuffer = new StringBuffer();
                String line;

                while ((line = file.readLine()) != null) {
                    stringArray.add(line);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            StringBuffer inputBuffer = new StringBuffer();
            String decryptedString = decryptString(stringArray.get(1), stringArray.get(2), stringArray.get(3));
            String decryptedName = decryptString(stringArray.get(1), stringArray.get(2), stringArray.get(4));

            inputBuffer.append(decryptedString);
            inputBuffer.append(System.lineSeparator());
            inputBuffer.append(decryptedName);

            try {
                FileOutputStream fileOut = new FileOutputStream("decrypted.txt");
                fileOut.write(inputBuffer.toString().getBytes());
                fileOut.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Finished writing decrypted data to file");
        }
    }
}
