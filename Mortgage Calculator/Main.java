package com.codewithmosh;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {

    /**FIELDS -
     * - they are defined at the class level
     * - these are variables accessible to all methods in the class
     * */
    final static byte MONTHS_IN_YEAR = 12;
    final static byte PERCENT = 100;

    public static void main(String[] args) {

        System.out.println();

        int principal = (int) readNumber("Principal: ", 1000, 1_000_000);
        float annualInterest = (float) readNumber("Annual Interest rate: ", 1, 30);
        byte years = (byte) readNumber("Period (Years): ", 1, 30);


        /** when refactoring we look for
         *
         * - repetitive lines in the code
         * - and lines that are highly related
         *
         * */

        printMortage(principal, annualInterest, years);

        printPaymentSchedule(principal, annualInterest, years);
    }

    private static void printMortage(int principal, float annualInterest, byte years) {
        double mortgage = calculateMortage(principal, annualInterest, years);
        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("--------");
        System.out.println("Mortgage: " + mortgageFormatted);
    }

    private static void printPaymentSchedule(int principal, float annualInterest, byte years) {
        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("----------------");
        for (short month = 1; month <= years * MONTHS_IN_YEAR; month++){
            double balance = calculateBalance(principal, annualInterest, years, month);
            System.out.println(NumberFormat.getCurrencyInstance().format(balance));
        }
    }

    /**
     * now all the code pertaining to calculating mortage is in this method
     */
    public static double calculateMortage(int principal,
                                          float annualInterest,
                                          byte years) {

        float monthlyInterest = annualInterest / PERCENT / MONTHS_IN_YEAR;
        short numberOfPayments = (short) (years * MONTHS_IN_YEAR);

        double mortgage = principal
                * (monthlyInterest * Math.pow(1 + monthlyInterest, numberOfPayments))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);
        return mortgage;
    }

    public static double calculateBalance(int principal,
                                          float annualInterest,
                                          byte years,
                                          short numberOfPaymentsMade) {

        float monthlyInterest = annualInterest / PERCENT / MONTHS_IN_YEAR;
        short numberOfPayments = (short) (years * MONTHS_IN_YEAR);

        double balance = principal
                * (Math.pow(1 + monthlyInterest, numberOfPayments) - Math.pow(1 + monthlyInterest, numberOfPaymentsMade))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);

        return balance;

    }


    /**
     * here we transferred all of the prompt information so that one prompt is dynamic
     */
    public static double readNumber(String prompt, double min, double max) {
        Scanner scanner = new Scanner(System.in);
        double value;
        while (true) {
            System.out.print(prompt);
            value = scanner.nextFloat();
            if (value >= min && value <= max) {
                break;
            }
            System.out.println("Enter a value between" + min + "and " + max);
        }
        return value;
    }

}

