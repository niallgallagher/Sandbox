package com.ljmu.niallgallagher;

public class SwitchExercisesRunner {

    public static void main(String[] args) {

        System.out.println( determineNameOfDay(1));
        System.out.println(determineNameOfMonth(2));
        System.out.println(isWeekday(1));
    }

    private static String determineNameOfDay(int dayNumber) {

    String result = "";

        switch (dayNumber)
        {
            case 0 : result = "Sunday";
            break;

            case 1 : result = "Monday";
                break;

            case 2 : result = "Tuesday";
                break;

            case 3 : result = "Wednesday";
                break;

            case 4 : result = "Thursday";
                break;

            case 5 : result = "Friday";
                break;

            case 6 : result = "Saturday";
                break;
        }

        return result;
    }

    private static String determineNameOfMonth(int monthNumber) {

        String resultMonth = "";

        switch (monthNumber)
        {
            case 1 : resultMonth = "January";
                break;

            case 2 : resultMonth = "February";
                break;

            case 3 : resultMonth = "March";
                break;

            case 4 : resultMonth = "April";
                break;

            case 5 : resultMonth = "May";
                break;

            case 6 : resultMonth = "June";
                break;

            case 7 : resultMonth = "July";
                break;

            case 8 : resultMonth = "Auguest";
                break;

            case 9 : resultMonth = "September";
                break;

            case 10: resultMonth = "October";
                break;

            case 11 : resultMonth = "November";
                break;

            case 12 : resultMonth = "December";
                break;
        }

        return resultMonth;
    }

    public static boolean isWeekday(int dayNumber){
        switch (dayNumber){
            case 0 : return false;
            case 1 : return true;
            case 2 : return true;
            case 3 : return true;
            case 4 : return true;
            case 5 : return true;
            case 6 : return false;

        }
        return false;
    }



}
