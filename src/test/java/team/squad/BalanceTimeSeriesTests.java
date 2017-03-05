package team.squad;

import org.junit.Before;
import org.junit.Test;
import team.squad.accounts.Account;
import team.squad.accounts.BalanceTimeSeries;
import team.squad.accounts.RecurringTransaction;

import java.util.List;

/**
 * Created by mkulima on 3/5/17.
 */
public class BalanceTimeSeriesTests {

    BalanceTimeSeries timeSeries;
    Account ac;
    RecurringTransaction debit1DollarPerMonth;
    RecurringTransaction credit1DollarPerMonth;
    RecurringTransaction debit10DollarPerMonth;
    RecurringTransaction credit10DollarPerMonth;
    RecurringTransaction debit20DollarPerMonth;
    RecurringTransaction credit20DollarPerMonth;


    @Before
    public void setUp(){
        timeSeries = new BalanceTimeSeries();
        debit1DollarPerMonth = new RecurringTransaction(-100L, 12);
        debit10DollarPerMonth = new RecurringTransaction(-1000L, 12);
        debit20DollarPerMonth = new RecurringTransaction(-2000L, 12);
        credit1DollarPerMonth = new RecurringTransaction(100L, 12);
        credit10DollarPerMonth = new RecurringTransaction(1000L, 12);
        credit20DollarPerMonth = new RecurringTransaction(2000L, 12);


    }

    @Test
    public void shouldMakeBaselineHistory(){
        ac = new Account(-10000L, 0.1, 0L, credit1DollarPerMonth);
        List<long[]> history = timeSeries.makeBaselineHistory(ac, 30, 360);
        System.out.println(timeSeries.historyToString(history));
    }

    @Test
    public void shouldMakeRecurringTransactionHistory(){
        List<long[]> recurringHist = timeSeries.makeRecurringHistory(debit20DollarPerMonth, 24);
        System.out.println(timeSeries.historyToString(recurringHist));
    }

    @Test
    public void shouldMakeRunningBalanceForSingleRecurringTransaction() {
        ac = new Account(-10000L, 0.1, 0L, credit20DollarPerMonth);
        List<long[]> balanceHist = timeSeries.makeHistSingleRecurTransaction(ac, ac.getRecurringTransactions().get(0),12);
        System.out.println(timeSeries.historyToString(balanceHist));
    }
}
