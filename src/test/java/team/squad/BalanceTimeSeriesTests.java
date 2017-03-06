package team.squad;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import team.squad.accounts.Account;
import team.squad.accounts.BalanceTimeSeries;
import team.squad.accounts.RecurringTransaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mkulima on 3/5/17.
 */
public class BalanceTimeSeriesTests {

    BalanceTimeSeries timeSeries;
    Account ac;
    RecurringTransaction debit0DollarPerMonth;
    RecurringTransaction debit1DollarPerMonth;
    RecurringTransaction credit1DollarPerMonth;
    RecurringTransaction debit10DollarPerMonth;

    RecurringTransaction credit0DollarPerMonth;
    RecurringTransaction credit10DollarPerMonth;
    RecurringTransaction debit20DollarPerMonth;
    RecurringTransaction credit20DollarPerMonth;


    @Before
    public void setUp(){
        timeSeries = new BalanceTimeSeries();
        debit0DollarPerMonth = new RecurringTransaction(-0L, 12);
        debit1DollarPerMonth = new RecurringTransaction(-100L, 12);
        debit10DollarPerMonth = new RecurringTransaction(-1000L, 12);
        debit20DollarPerMonth = new RecurringTransaction(-2000L, 12);

        credit0DollarPerMonth = new RecurringTransaction(0L, 12);
        credit1DollarPerMonth = new RecurringTransaction(100L, 12);
        credit10DollarPerMonth = new RecurringTransaction(1000L, 12);
        credit20DollarPerMonth = new RecurringTransaction(2000L, 12);


    }

    @Test
    public void shouldMakeBaselineHistory(){
        ac = new Account(100L, 0.1, 0L, credit20DollarPerMonth);
        List<long[]> history = timeSeries.makeBaselineHistory(ac, 12);
        Assert.assertEquals(100L, history.get(0)[1]);
    }

    @Test
    public void shouldMakeRunningBalanceForMultipleRecurringTransaction() {
        ac = new Account(100000L, 0.1, 0L, debit10DollarPerMonth);
        List<long[]> balanceHist = timeSeries.makeBalanceHistory(ac,13);
        System.out.println(timeSeries.historyToString(balanceHist));
        Assert.assertEquals(88000L, balanceHist.get(12)[1]);
    }

    @Test
    public void shouldReturnAverageBalanceEvenWithDummyRecurringTransaction() {
        ac = new Account(10000L, 0.1, 0L, credit0DollarPerMonth);
        Assert.assertEquals(10000L, timeSeries.getAverageBalance(ac,12));
    }

    @Test
    public void shouldReturnAverageBalanceForSingleRecurringTransaction() {
        Account ac5 = new Account(10000L, 0.1, 0L, credit20DollarPerMonth);
        Assert.assertEquals(21000L, timeSeries.getAverageBalance(ac5,12));
    }

    @Test
    public void shouldReturnMaxBalanceForSingleRecurringTransactions() {
        Account ac2 = new Account(10000L, 0.1, 0L, credit20DollarPerMonth);
        Assert.assertEquals(32000L, timeSeries.getMaxBalance(ac2,12));
    }

    @Test
    public void shouldReturnMaxBalanceForMultipleRecurringTransactions() {
        Account ac3 = new Account(10000L, 0.1, 0L, credit20DollarPerMonth);
        List<RecurringTransaction> transactionList = new ArrayList<>(2);
        transactionList.add(credit20DollarPerMonth);
        transactionList.add(credit1DollarPerMonth);
        transactionList.add(debit20DollarPerMonth);
        ac3.setRecurringTransactions(transactionList);
        Assert.assertEquals(13200L, timeSeries.getMaxBalance(ac3,12));
    }

    @Test
    public void shouldReturnMinBalanceForSingleRecurringTransactions() {
        Account ac4 = new Account(10000L, 0.1, 0L, credit20DollarPerMonth);
        Assert.assertEquals(10000L, timeSeries.getMinBalance(ac4,12));
    }

    @Test
    public void shouldReturnMinBalanceForMultipleRecurringTransactions() {
        Account ac5 = new Account(10000L, 0.1, 0L, credit20DollarPerMonth);
        List<RecurringTransaction> transactionList = new ArrayList<>(2);
        transactionList.add(credit20DollarPerMonth);
        transactionList.add(credit1DollarPerMonth);
        transactionList.add(debit20DollarPerMonth);
        ac5.setRecurringTransactions(transactionList);
        Assert.assertEquals(12100L, timeSeries.getMinBalance(ac5,12));
    }

}
