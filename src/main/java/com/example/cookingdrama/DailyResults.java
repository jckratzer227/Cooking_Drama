package com.example.cookingdrama;

public class DailyResults {
    static private int happyCustomers;
    static private int unhappyCustomers;

    public DailyResults () {};

    public DailyResults(int happyCustomers, int unhappyCustomers) {
        DailyResults.happyCustomers = happyCustomers;
        DailyResults.unhappyCustomers = unhappyCustomers;
    }

    public static void addHappyCustomers() {
        happyCustomers++;
    }

    public static int getHappyCustomers() {
        return happyCustomers;
    }

    public static void addUnhappyCustomers() {
        unhappyCustomers++;
    }

    public static int getUnhappyCustomers() {
        return unhappyCustomers;
    }

    public static void reset() {
        happyCustomers = 0;
        unhappyCustomers = 0;
    }
}
