package team.squad;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author John A. Squier
 * add your name when you work on this file.
 */
public class AccountTests {

    private Account accountWithHistory;
    private Account accountWithoutHistory;

    @Before
    public void setup() {
        accountWithHistory = new Account();
        accountWithHistory.setBalance(100000L); //$1000.00
        accountWithHistory.setAccountType("savings"); // this doesn't matter does it?
        accountWithHistory.setInterestRate(0.10); // 10% APY
        accountWithHistory.setOverDraftPenalty(0L); // when under $0.00 no interest
        accountWithHistory.setRequiredMinimumBalance(50000L); //$500.00 no interest when this hits
        accountWithHistory.setIsMinimumBalanceRequired(true);

        RecurringTransaction debit = new RecurringTransaction();
        debit.setAmount(-500L);   // $5.00 a day taken out
        debit.setFrequency(Interval.DAILY);

        RecurringTransaction credit = new RecurringTransaction();
        credit.setAmount(10000L); // $100.00 a month added in
        credit.setFrequency(Interval.MONTHLY);

        RecurringTransaction credit2 = new RecurringTransaction();
        credit2.setAmount(500000L);
        credit2.setFrequency(Interval.ANNUALLY);

        List<RecurringTransaction> recurringTransactions =  new ArrayList<>();
        recurringTransactions.add(credit);
        recurringTransactions.add(debit);
        recurringTransactions.add(credit2);

        accountWithHistory.setRecurringTransactions(recurringTransactions);
    }

    @Test
    public void calculateLedgerBalanceTest() {
        Long expected = 95000L;

        accountWithHistory.calculateLedgerBalance(30);
        Long actual = accountWithHistory.getLedgerBalance();

        Assert.assertEquals("I expect the ledger balance to be 95000 or $950.00",
                expected, actual);
    }

    @Test
    public void calculateLedgerBalanceTestWithAnual() {
        Long expected = 537500L; // what should the expected ledger balance be?

        accountWithHistory.calculateLedgerBalance(365); // 30 days
        Long actual = accountWithHistory.getLedgerBalance();

        Assert.assertEquals("I expect the ledger balance to be 437500",
                expected, actual);
    }

    //@Test
    //public void CalculateSimple







}