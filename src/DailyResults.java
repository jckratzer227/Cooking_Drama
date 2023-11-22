package finalProject;

public class DailyResults {
    static private int happyCustomers;
    static private int unhappyCustomers;

    public DailyResults () {};

    public DailyResults(int happyCustomers, int unhappyCustomers) {
       DailyResults.happyCustomers = happyCustomers;
       DailyResults.unhappyCustomers = unhappyCustomers;
    }

    public void addHappyCustomers() {
        happyCustomers++;
    }

    public int getHappyCustomers() {
        return happyCustomers;
    }

    public void addUnhappyCustomers() {
        unhappyCustomers++;
    }

    public int getUnhappyCustomers() {
        return unhappyCustomers;
    }

    public void reset() {
        happyCustomers = 0;
        unhappyCustomers = 0;
    }

}
