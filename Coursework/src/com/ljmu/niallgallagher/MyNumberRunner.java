package com.ljmu.niallgallagher;

public class MyNumberRunner {

    public static void main (String[] args) {
        MyNumber number = new MyNumber(12);

        number.isPrime();
        boolean isPrime = number.isPrime();
        System.out.println("is Prime "+ isPrime);

        int sum = number.sumUpToN();
        System.out.println("Sum up to N "+ sum);

        int divide = number.sumOfDivisors();
        System.out.println("Sum of Divisors " + divide);

        number.printANumberTriangle();

    }

    private static class MyNumber<n> {
        private int number;



        public MyNumber(int number) {
            this.number = number;

        }

        public boolean isPrime() {
            for (int i = 2; i <= number-1; i++)
                if (number % i == 0) {
            return  false;
                }

            return  true;
        }

        public int sumUpToN() {
            int sum = 0;

            for (int i = 1; i <= number; i++){

                sum = sum + i;
            }
            return sum;
        }

        public int sumOfDivisors() {
            int divide = 0;

            for (int i = 2;  i <= number - 1; i++){
                if (number % i == 0) {
                    divide = divide + i;
                }

            }
            return divide;
        }

        public void printANumberTriangle() {


                for (int i = 1 ; i <= number ; i++ ){
                for (int j = 1; j <= i; j++) {

                    System.out.print(j + " ");
                    //Incrementing the number value
                }
                System.out.println("");
                }

            }
    }


    }



