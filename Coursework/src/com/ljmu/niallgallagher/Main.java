package com.ljmu.niallgallagher;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
       LocalDate now = LocalDate.now();

        
    }

    static void sayHelloWorldThrice() {
        System.out.println("Hello World Kay");
        System.out.println("Hello World Kay");
    }
    static void jim(){

        System.out.println("I've created my first variable.");
        System.out.println("I've created my first method");
        System.out.println("I've created my first loop");
        System.out.println("I'm excited to learn Java");
    }

    static void printNumbers(int n){
        for (int i= 1; i<= n; i++){
            System.out.println(i);
        }
    }

    static void printSquareOfNumbers(int q){
            for (int i=1; i<=q; i++){
                System.out.println(i*i);
        }

        }

    static void addSum(int firstNumber,int secondNumber, int thirdNumber){
                    int sum = firstNumber + secondNumber + thirdNumber;
                    System.out.println(sum);
    }

    static void angleCalculator(){
                    int angleA = 60;
                    int angleB = 40;
                    int angleC;

                    angleC = 180 - (angleA + angleB);
                    System.out.print("Angle C is equal to " + angleC);
    }


}
