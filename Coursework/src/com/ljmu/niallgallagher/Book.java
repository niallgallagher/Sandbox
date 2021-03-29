package com.ljmu.niallgallagher;

public class Book {

        public static void main(String[] args) {
        BookRunner artofComputerProgramming = new BookRunner(6);
            BookRunner  effectiveJava = new BookRunner(5);
            BookRunner cleanCode = new BookRunner(4);


           // artofComputerProgramming.copies(6);
          //  System.out.println(artofComputerProgramming.getCopies());

           // effectiveJava.copies(5);
          //  cleanCode.copies(4);

          // int artofComputerProgrammingCopies = artofComputerProgramming.getCopies();
           // artofComputerProgrammingCopies = artofComputerProgrammingCopies + 100;
           // artofComputerProgramming.copies(artofComputerProgrammingCopies);

          //  System.out.println(artofComputerProgrammingCopies);

            effectiveJava.increaseCopies(100);
            effectiveJava.decreaseCopies(50);

            cleanCode.increaseCopies(100);
            cleanCode.decreaseCopies(50);
            System.out.println(artofComputerProgramming.getCopies());
            System.out.println(effectiveJava.getCopies());
            System.out.println(cleanCode.getCopies());
        }

}
