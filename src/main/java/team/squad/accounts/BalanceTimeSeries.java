package team.squad.accounts;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mkulima on 3/4/17.
 */
public class BalanceTimeSeries {


    /**
     * @param ac the account whose baseline history is to be generated
     * @param numOccurrences basically number of x-axis ticks
     **/
    public List<long[]> makeBaselineHistory(Account ac, int numOccurrences){
        int ticks = numOccurrences;
        ArrayList<long[]> baselineHistory = new ArrayList<>(ticks);
        for (int i=0; i<ticks; i++ ) baselineHistory.add(new long[]{i, ac.getBalance()});
        return baselineHistory;
    }

    /**
     * @param ac Account to make history of
     * @param numRecurrences num of occurrences/recurrences
     * @return
     */
    public List<long[]> makeBalanceHistory(Account ac, int numRecurrences){
        List<long[]> history = makeBaselineHistory(ac,numRecurrences);
        long runningBal = ac.getBalance();
        List<RecurringTransaction> transactionList = ac.getRecurringTransactions();

        for(int y=0; y<numRecurrences; y++){
            for(int z=0; z<transactionList.size(); z++){
                history.set(y, new long[]{y, runningBal});
                runningBal += transactionList.get(z).getAmount();
            }
        }
        return history;
    }

    /**
     * @param ac  Account whose average balance is to calculated
     * @param numRecurrences number of recurring transactions over which to get the average balance
     * @return average balance
     */
    public long getAverageBalance(Account ac, int numRecurrences){
        List<long[]> balanceHist = makeBalanceHistory(ac, numRecurrences);
        long avgBalance = ( balanceHist.get(0)[1] + balanceHist.get(balanceHist.size()-1)[1] ) / 2;
        return avgBalance;
    }

    /**
     * @param ac  Account whose average balance is to calculated
     * @param numRecurrences number of recurring transactions over which to get the maximum balance
     * @return maximum balance over the number of occurrences
     */
    public long getMaxBalance(Account ac, int numRecurrences){
        long ind1 = getFirstAndLast(ac, numRecurrences)[0];
        long lastInd = getFirstAndLast(ac, numRecurrences)[1];
        long maxBalance = lastInd>ind1 ? lastInd : ind1;
        return maxBalance;
    }

    /**
     * @param ac  Account whose average balance is to calculated
     * @param numRecurrences number of recurring transactions over which to get the average balance
     * @return minimum balance over the number of occurrences
     */
    public long getMinBalance(Account ac, int numRecurrences){
        long[] firstNLast = getFirstAndLast(ac,numRecurrences);
        long maxBalance = firstNLast[0]<firstNLast[1] ? firstNLast[0] : firstNLast[1];
        return maxBalance;
    }

    /**
     * @param ac Account whose starting and ending balances are to be found
     * @param numRecurrences number of recurring transactions over which to get the starting and ending balance
     * @return returns an array with beginning balance in index 0 and ending balance in index 1;
     */
    private long[] getFirstAndLast(Account ac, int numRecurrences){
        List<long[]> balanceHist = makeBalanceHistory(ac, numRecurrences);
        long ind1 = balanceHist.get(0)[1];
        long lastInd = balanceHist.get(balanceHist.size()-1)[1];
        return new long[]{ind1, lastInd};
    }



    public String historyToString(List<long[]> history){
        String toReturn = "";
        for (long[] l : history) toReturn += Arrays.toString(l) + "\n";
        return toReturn;
    }




}
