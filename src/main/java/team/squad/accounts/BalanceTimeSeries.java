package team.squad.accounts;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mkulima on 3/4/17.
 */
public class BalanceTimeSeries {

    // considering using JFreeChart to plot account history.
    // This is an extra feature.


    /**
     * @param ac the account whose baseline history is to be generated
     * @param intervalInDays the intervals (in days) over which to show balance history
     * @param periodInDays the total period over which to show the balance history
     **/
    public List<long[]> makeBaselineHistory(Account ac, int intervalInDays, int periodInDays){
        int ticks = periodInDays/intervalInDays;
        ArrayList<long[]> baselineHistory = new ArrayList<>(ticks);
        for (int i=0; i<ticks; i++ ) baselineHistory.add(new long[]{i, ac.getBalance()});
        return baselineHistory;
    }

    /**
     * @param r Recurring transaction to make history of
     * @param totalNumOccurrences Total number of monthly occurences per year e.g 12 for one year, 60 for 5 years
     * **/
    public List<long[]> makeRecurringHistory(RecurringTransaction r, int totalNumOccurrences){
        int ticks = totalNumOccurrences;
        ArrayList<long[]> recurringHist = new ArrayList<>(ticks);
        for (int i = 0; i < ticks; i++) recurringHist.add(new long[]{i, r.getAmount()});
        return recurringHist;
    }

    /**
     *
     * @param ac Account to make time hostory of
     * @param r  Recurring transaction
     * @param totalNumOccurrences Total number of monthly occurences per year e.g 12 for one year, 60 for 5 years
     * @return
     */
    public List<long[]> makeHistSingleRecurTransaction(Account ac, RecurringTransaction r, int totalNumOccurrences){
        List<long[]> history = makeRecurringHistory(r, totalNumOccurrences);
        long runningBalance = ac.getBalance();
        for (int x=0; x<totalNumOccurrences; x++) {
            history.set(x, new long[]{x, runningBalance});
            runningBalance += r.getAmount();
        }
        return history;
    }




    public String historyToString(List<long[]> history){
        String toReturn = "";
        for (long[] l : history) toReturn += Arrays.toString(l) + "\n";
        return toReturn;
    }





}
