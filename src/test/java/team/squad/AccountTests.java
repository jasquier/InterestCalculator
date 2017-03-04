package team.squad;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import team.squad.accounts.Account;
import team.squad.accounts.RecurringTransaction;

import java.util.ArrayList;
import java.util.List;

/**
 * @author John A. Squier
 * add your name when you work on this file.
 */
public class AccountTests {

    private Account accountWithHistory;

    @Before
    public void setup() {
        accountWithHistory = new Account();
        accountWithHistory.setBalance(100000L); //$1000.00
        accountWithHistory.setAccountType("savings");
        accountWithHistory.setInterestRate(0.10); // 10% APY
        accountWithHistory.setOverDraftPenalty(0L); // when under $0.00 no interest
        accountWithHistory.setRequiredMinimumBalance(50000L); //$500.00 no interest when this hits
        accountWithHistory.setIsMinimumBalanceRequired(true);

        RecurringTransaction debit5DollarsPerDay = new RecurringTransaction(-500L, 365);
        RecurringTransaction credit100DollarsPerMonth = new RecurringTransaction(10000L, 12);
        RecurringTransaction credit5000DollarsPerYear = new RecurringTransaction(500000L, 1);

        List<RecurringTransaction> recurringTransactions =  new ArrayList<>();
        recurringTransactions.add(credit100DollarsPerMonth);
        recurringTransactions.add(debit5DollarsPerDay);
        recurringTransactions.add(credit5000DollarsPerYear);

        accountWithHistory.setRecurringTransactions(recurringTransactions);
    }

    @Test
    public void calculateLedgerBalanceTest() {
        Long expected = 95000L;

        accountWithHistory.calculateLedgerBalance(30);
        Long actual = accountWithHistory.getLedgerBalance();

        Assert.assertEquals("I expect the ledger balance to be 95000 cents or $950.00",
                expected, actual);
    }

    @Test
    public void calculateLedgerBalanceTest2() {
        Long expected = 537500L; // what should the expected ledger balance be?

        accountWithHistory.calculateLedgerBalance(365); // 30 days
        Long actual = accountWithHistory.getLedgerBalance();

        Assert.assertEquals("I expect the ledger balance to be 437500 cents or $4375.00",
                expected, actual);
    }

    @Test
    public void getAverageBalanceTest(){
        Account accountWithHistory2 = new Account();
        accountWithHistory2.setBalance(100000L); //$1000.00
        accountWithHistory2.setAccountType("savings");
        accountWithHistory2.setInterestRate(0.10); // 10% APY
        accountWithHistory2.setOverDraftPenalty(0L); // when under $0.00 no interest
        accountWithHistory2.setRequiredMinimumBalance(50000L); //$500.00 no interest when this hits
        accountWithHistory2.setIsMinimumBalanceRequired(true);

        RecurringTransaction credit100DollarsPerMonth = new RecurringTransaction(10000L, 12);
        RecurringTransaction debit50DollarsPerMonth = new RecurringTransaction(-5000L, 12);
        List<RecurringTransaction> recurringTransactions2 =  new ArrayList<>();
        recurringTransactions2.add(credit100DollarsPerMonth);
        recurringTransactions2.add(debit50DollarsPerMonth);
        accountWithHistory2.setRecurringTransactions(recurringTransactions2);

        long expectedAvgBalance = 130000L;
        long actualAvgBalance = accountWithHistory2.getAverageBalance();
        System.out.println("avg bal: " + actualAvgBalance);
        Assert.assertEquals(expectedAvgBalance, actualAvgBalance);
    }
}