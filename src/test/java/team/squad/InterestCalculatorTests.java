package team.squad;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import team.squad.accounts.Account;
import team.squad.accounts.PastTransaction;
import team.squad.accounts.RecurringTransaction;
import team.squad.interest.CalculationRule;
import team.squad.interest.InterestCalculator;
import team.squad.interest.InterestType;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;


/**
 * @author John A. Squier
 * add your name when you work on this file.
 */
public class InterestCalculatorTests {

    InterestCalculator simpleInterestCalculator, complexInterestCalculator;
    Account account;
    List<PastTransaction> pastTransactionList;
    PastTransaction item1, item2, item3;
    @Before
    public void setup() {
        account = new Account();
        account.setAccountType("savings");
        account.setInterestRate(.10); // 10% APY in Interest Rate Monthly
        account.setOverDraftPenalty(0L); // when under $0.00 no interest
        account.setRecurringTransactions(null);
        account.setAccountHistory(null);

        simpleInterestCalculator = new InterestCalculator();
        simpleInterestCalculator.setInterestType(InterestType.SIMPLE);
        simpleInterestCalculator.setCalculationRule(CalculationRule.NONE);
        simpleInterestCalculator.setInterval(365);
        simpleInterestCalculator.setAccount(account);

        complexInterestCalculator = new InterestCalculator();
        complexInterestCalculator.setAccount(account);
        complexInterestCalculator.setInterval(365);
        complexInterestCalculator.setFrequency(12); // monthly
        complexInterestCalculator.setInterestType(InterestType.COMPLEX);
        complexInterestCalculator.setCalculationRule(CalculationRule.NONE);
        complexInterestCalculator.setNumDaysForRule(0);

        pastTransactionList = new ArrayList<>();
        item1 = new PastTransaction(-100L, new Date(2017,03,22));
        item2 = new PastTransaction(500L, new Date(2017, 03, 23));
        item3 = new PastTransaction(-230L, new Date(2017, 03, 24));
        pastTransactionList.add(item1);
        pastTransactionList.add(item2);
        pastTransactionList.add(item3);
    }

    @Test
    public void calcSimpleInterestNonZeroBalanceAndNoRMB(){
        long expected = 10000L;
        account.setBalance(100000L); //$1000.00
        account.setRequiredMinimumBalance(0L);
        account.setIsMinimumBalanceRequired(false);

        long actual = simpleInterestCalculator.getInterestAmount();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void calcSimpleInterestNonZeroBalanceAndAboveRMB(){
        long expected = 10000L;
        account.setBalance(100000L); //$1000.00
        account.setRequiredMinimumBalance(0L);
        account.setIsMinimumBalanceRequired(true);

        long actual = simpleInterestCalculator.getInterestAmount();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void calcSimpleInterestNonZeroBalanceAndBelowRMB(){
        long expected = 0L;
        account.setBalance(100000L); //$1000.00
        account.setIsMinimumBalanceRequired(true);
        account.setRequiredMinimumBalance(500000L);

        long actual = simpleInterestCalculator.getInterestAmount();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void calcSimpleInterestWithZeroBalanceAndNoRMB() {
        long expected2 = 0L;
        account.setBalance(0L); // $0.00
        account.setIsMinimumBalanceRequired(false);
        account.setRequiredMinimumBalance(0L);

        long actual = simpleInterestCalculator.getInterestAmount();

        Assert.assertEquals(expected2, actual);
    }

    @Test
    public void calcSimpleInterestWithZeroBalanceAndBelowRMB() {
        long expected = 0L;
        account.setBalance(0L); // $0.00
        account.setIsMinimumBalanceRequired(true);
        account.setRequiredMinimumBalance(50000L); // $500.00

        long actual = simpleInterestCalculator.getInterestAmount();

        Assert.assertEquals(expected, actual);
    }

    @Test // TODO this needs changed, we are gonna get a penalty when overdrawn
    public void calcSimpleInterestWithNegativeBalanceAndNoRMB() {
        long expected = 0L;
        account.setBalance(-100L); // $-1.00
        account.setIsMinimumBalanceRequired(false);
        account.setRequiredMinimumBalance(0L);

        long actual = simpleInterestCalculator.getInterestAmount();

        Assert.assertEquals(expected ,actual);
    }

    @Test
    public void calcSimpleInterestWithNegativeBalanceAndBelowRMB() {
        long expected = 0L;
        account.setBalance(-100L); // $-1.00
        account.setIsMinimumBalanceRequired(true);
        account.setRequiredMinimumBalance(50000L); // $500.00

        long actual = simpleInterestCalculator.getInterestAmount();

        Assert.assertEquals(expected ,actual);
    }

    @Test
    public void calcComplexInterestWithNonZeroBalanceAndNoRMB() {
        long expected = 52356L; // TODO calculate this value
        account.setBalance(500000L); // $5000.00
        account.setIsMinimumBalanceRequired(false);
        account.setRequiredMinimumBalance(0L);

        long actual = complexInterestCalculator.getInterestAmount();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void calcComplexInterestWithNonZeroBalanceAndAboveRMB() {
        long expected = 52356; // TODO calculate this value
        account.setBalance(500000L); // $5000.00
        account.setIsMinimumBalanceRequired(true);
        account.setRequiredMinimumBalance(10000L);

        long actual = complexInterestCalculator.getInterestAmount();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void calcComplexInterestWithNonZeroBalanceAndBelowRMB() {
        long expected = 0L;
        account.setBalance(10000L); // $100.00
        account.setIsMinimumBalanceRequired(true);
        account.setRequiredMinimumBalance(50000L); // $500.00

        long actual = complexInterestCalculator.getInterestAmount();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void calcComplexInterestWithZeroBalanceAndNoRMB() {
        long expected = 0L;
        account.setBalance(0L);
        account.setIsMinimumBalanceRequired(false);
        account.setRequiredMinimumBalance(0L);

        long actual = complexInterestCalculator.getInterestAmount();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void calcComplexInterestWithZeroBalanceBelowRMB() {
        long expected = 0L;
        account.setBalance(1000L);
        account.setIsMinimumBalanceRequired(true);
        account.setRequiredMinimumBalance(10000L);

        long actual = complexInterestCalculator.getInterestAmount();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void calcComplexInterestNegativeBalanceNoRMB() {
        long expected = 0L;
        account.setBalance(-1000L);
        account.setIsMinimumBalanceRequired(false);
        account.setRequiredMinimumBalance(0L);

        long actual = complexInterestCalculator.getInterestAmount();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void calcComplexInterestNegativeBalanceWithRMB() {
        long expected = 0L;
        account.setBalance(-1000L);
        account.setIsMinimumBalanceRequired(true);
        account.setRequiredMinimumBalance(50000L);

        long actual = complexInterestCalculator.getInterestAmount();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void calcSimpleInterestNonZeroBalanceWithDeductionsThatDoNotExceedInterest() {
        long expected = -2L;
        account.setBalance(100000L);
        account.setIsMinimumBalanceRequired(false);
        account.setRequiredMinimumBalance(0L);
        simpleInterestCalculator.setCalculationRule(CalculationRule.AVERAGE);

        RecurringTransaction debit10DollarPerMonth = new RecurringTransaction(-1000L, 12);
        List<RecurringTransaction> recurringTransactions2 =  new ArrayList<>();
        recurringTransactions2.add(debit10DollarPerMonth);
        account.setRecurringTransactions(recurringTransactions2);

        long actual = simpleInterestCalculator.getInterestAmount();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void calcSimpleInterestNonZeroBalanceWithDeductionsThatDoExceedInterestEarned() {
        long expected = -2L;
        account.setBalance(100000L);
        account.setIsMinimumBalanceRequired(false);
        account.setRequiredMinimumBalance(0L);
        RecurringTransaction debit10DollarsPerMonth = new RecurringTransaction(-1000L, 12);
        List<RecurringTransaction> recurringTransactions =  new ArrayList<>();
        recurringTransactions.add(debit10DollarsPerMonth);
        account.setRecurringTransactions(recurringTransactions);

        long actual = simpleInterestCalculator.getInterestAmount();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void calcComplexInterestNonZeroBalanceWithDeductionsThatDoNotExceedInterest() {
        long expected = -200L;
        account.setBalance(1000000L);
        account.setIsMinimumBalanceRequired(false);
        account.setRequiredMinimumBalance(0L);
        RecurringTransaction debit1DollarPerMonth = new RecurringTransaction(-100L, 12);
        List<RecurringTransaction> recurringTransactions =  new ArrayList<>();
        recurringTransactions.add(debit1DollarPerMonth);
        account.setRecurringTransactions(recurringTransactions);

        long actual = complexInterestCalculator.getInterestAmount();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void calcComplexInterestNonZeroBalanceWithDeductionsThatDoExceedInterest() {
        long expected = -300L;
        account.setBalance(1000000L);
        account.setIsMinimumBalanceRequired(false);
        account.setRequiredMinimumBalance(0L);
        RecurringTransaction debit1000DollarsPerMonth = new RecurringTransaction(-100100L, 12);
        List<RecurringTransaction> recurringTransactions =  new ArrayList<>();
        recurringTransactions.add(debit1000DollarsPerMonth);
        account.setRecurringTransactions(recurringTransactions);

        long actual = complexInterestCalculator.getInterestAmount();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void calcSimpleInterestNonZeroBalanceAndBelowRMBWithCreditsThatDoNotBringBalanceAboveRMB() {
        long expected = 0L;
        account.setBalance(100L);
        account.setIsMinimumBalanceRequired(true);
        account.setRequiredMinimumBalance(1000000L);
        RecurringTransaction credit1DollarPerMonth = new RecurringTransaction(100L, 12);
        List<RecurringTransaction> recurringTransactions =  new ArrayList<>();
        recurringTransactions.add(credit1DollarPerMonth);
        account.setRecurringTransactions(recurringTransactions);

        long actual = simpleInterestCalculator.getInterestAmount();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void calcSimpleInterestNonZeroBalanceAndBelowRMBWithCreditsThatDoBringBalanceAboveRMB() {
        long expected = -2L;
        account.setBalance(900000L); // $9000.00
        account.setIsMinimumBalanceRequired(true);
        account.setRequiredMinimumBalance(1000000L); // $10,000
        RecurringTransaction credit500DollarsPerMonth = new RecurringTransaction(50000L, 12);
        List<RecurringTransaction> recurringTransactions =  new ArrayList<>();
        recurringTransactions.add(credit500DollarsPerMonth);
        account.setRecurringTransactions(recurringTransactions);

        long actual = simpleInterestCalculator.getInterestAmount();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void calcComplexInterestNonZeroBalanceAndBelowRMBWithCreditsThatDoNotBringBalanceAboveRMB() {
        long expected = -2L;
        account.setBalance(100L);
        account.setIsMinimumBalanceRequired(true);
        account.setRequiredMinimumBalance(1000000L);
        RecurringTransaction credit1DollarPerMonth = new RecurringTransaction(100L, 12);
        List<RecurringTransaction> recurringTransactions =  new ArrayList<>();
        recurringTransactions.add(credit1DollarPerMonth);
        account.setRecurringTransactions(recurringTransactions);

        long actual = complexInterestCalculator.getInterestAmount();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void calcComplexInterestNonZeroBalanceAndBelowRMBWithCreditsThatDoBringBalanceAboveRMB() {
        long expected = -2L;
        account.setBalance(950000L);
        account.setIsMinimumBalanceRequired(true);
        account.setRequiredMinimumBalance(1000000L);
        RecurringTransaction credit200DollarsPerMonth = new RecurringTransaction(20000L, 12);
        List<RecurringTransaction> recurringTransactions =  new ArrayList<>();
        recurringTransactions.add(credit200DollarsPerMonth);
        account.setRecurringTransactions(recurringTransactions);

        long actual = complexInterestCalculator.getInterestAmount();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void balanceMinTest(){
        long expected = 9730;
        account.setBalance(10000L);
        account.setAccountHistory(pastTransactionList);
        long actual = complexInterestCalculator.balanceMinimum();
        Assert.assertEquals(expected,actual);
    }
}