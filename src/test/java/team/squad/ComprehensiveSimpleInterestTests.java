package team.squad;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import team.squad.accounts.Account;
import team.squad.accounts.RecurringTransaction;
import team.squad.interest.CalculationRule;
import team.squad.interest.InterestCalculator;
import team.squad.interest.InterestType;

/**
 * Created by mkulima on 3/4/17.
 */
public class ComprehensiveSimpleInterestTests {

    InterestCalculator simpleInterestor;
    Account ac;
    RecurringTransaction debit1DollarPerMonth;
    RecurringTransaction credit1DollarPerMonth;
    RecurringTransaction debit10DollarPerMonth;
    RecurringTransaction credit10DollarPerMonth;
    RecurringTransaction debit20DollarPerMonth;
    RecurringTransaction credit20DollarPerMonth;


    @Before
    public void setUp() throws Exception {
        debit1DollarPerMonth = new RecurringTransaction(-100L, 12);
        debit10DollarPerMonth = new RecurringTransaction(-1000L, 12);
        debit20DollarPerMonth = new RecurringTransaction(-2000L, 12);
        credit1DollarPerMonth = new RecurringTransaction(100L, 12);
        credit10DollarPerMonth = new RecurringTransaction(1000L, 12);
        credit20DollarPerMonth = new RecurringTransaction(2000L, 12);

    }

    // CalculationRule: NONE
    @Test
    public void calcSimpleInterestNonZeroBalanceAndNoRMB(){
        ac = new Account(100000L, 0.1, 0L, debit10DollarPerMonth);
        simpleInterestor = new InterestCalculator(ac, 365, InterestType.SIMPLE, CalculationRule.NONE);
        long actualInt = simpleInterestor.getInterestAmount();
        Assert.assertEquals(10000L, actualInt);
    }

    @Test
    public void calcSimpleInterestNonZeroBalanceAndAboveRMB(){
        ac = new Account(100000L, 0.1, 0L, debit10DollarPerMonth);
        simpleInterestor = new InterestCalculator(ac, 365, InterestType.SIMPLE, CalculationRule.NONE);
        long actualInt = simpleInterestor.getInterestAmount();
        Assert.assertEquals(10000L, actualInt);
    }

    @Test
    public void calcSimpleInterestWithZeroBalanceAndNoRMB(){
        ac = new Account(0L, 0.1, 0L, debit10DollarPerMonth);
        simpleInterestor = new InterestCalculator(ac, 365, InterestType.SIMPLE, CalculationRule.NONE);
        long actualInt = simpleInterestor.getInterestAmount();
        Assert.assertEquals(0L, actualInt);
    }

    @Test
    public void calcSimpleInterestWithZeroBalanceAndBelowRMB() {
        ac = new Account(0L, 0.1, 500000L, debit10DollarPerMonth);
        simpleInterestor = new InterestCalculator(ac, 365, InterestType.SIMPLE, CalculationRule.NONE);
        long actualInt = simpleInterestor.getInterestAmount();
        Assert.assertEquals(0L, actualInt);
    }

    @Test // TODO this needs changed, we are gonna get a penalty when overdrawn
    public void calcSimpleInterestWithNegativeBalanceAndNoRMB() {
        ac = new Account(-100L, 0.1, 0L, debit10DollarPerMonth);
        simpleInterestor = new InterestCalculator(ac, 365, InterestType.SIMPLE, CalculationRule.NONE);
        long actualInt = simpleInterestor.getInterestAmount();
        Assert.assertEquals(0L, actualInt);
    }

    @Test
    public void calcSimpleInterestWithNegativeBalanceAndBelowRMB() {
        ac = new Account(-100L, 0.1, 50000L, debit10DollarPerMonth);
        simpleInterestor = new InterestCalculator(ac, 365, InterestType.SIMPLE, CalculationRule.NONE);
        long actualInt = simpleInterestor.getInterestAmount();
        Assert.assertEquals(0L, actualInt);
    }

    @Test
    public void calcSimpleInterestNonZeroBalanceWithDeductionsThatDoNotExceedInterest() {
        ac = new Account(100000L, 0.1, 0L, debit10DollarPerMonth);
        simpleInterestor = new InterestCalculator(ac, 365, InterestType.SIMPLE, CalculationRule.NONE);
        long actualInt = simpleInterestor.getInterestAmount();
        Assert.assertEquals(10000L, actualInt);
    }

    @Test
    public void calcSimpleInterestNonZeroBalanceWithDeductionsThatDoExceedInterestEarned() {
        ac = new Account(100000L, 0.1, 0L, debit20DollarPerMonth);
        simpleInterestor = new InterestCalculator(ac, 365, InterestType.SIMPLE, CalculationRule.NONE);
        long actualInt = simpleInterestor.getInterestAmount();
        Assert.assertEquals(10000L, actualInt);
    }

    @Test
    public void calcSimpleInterestNonZeroBalanceAndBelowRMBWithCreditsThatDoNotBringBalanceAboveRMB() {
        ac = new Account(10000L, 0.1, 20000L, credit1DollarPerMonth);
        simpleInterestor = new InterestCalculator(ac, 365, InterestType.SIMPLE, CalculationRule.NONE);
        long actualInt = simpleInterestor.getInterestAmount();
        Assert.assertEquals(0L, actualInt);
    }

    @Test
    public void calcSimpleInterestOverdrawnWithCreditsThatDoNotBringBalanceAboveZERO() {
        ac = new Account(-10000L, 0.1, 0L, credit1DollarPerMonth);
        simpleInterestor = new InterestCalculator(ac, 365, InterestType.SIMPLE, CalculationRule.NONE);
        long actualInt = simpleInterestor.getInterestAmount();
        Assert.assertEquals(0L, actualInt);
    }

    @Test
    public void calcSimpleInterestOverdrawnWithCreditsThatBringBalanceAboveZERO() {
        ac = new Account(-100L, 0.1, 0L, credit20DollarPerMonth);
        simpleInterestor = new InterestCalculator(ac, 365, InterestType.SIMPLE, CalculationRule.NONE);
        long actualInt = simpleInterestor.getInterestAmount();
        Assert.assertEquals(0L, actualInt);
    }


    // CalculationRule: AVERAGE
    @Test
    public void calcSimpleInterestNonZeroBalanceAndNoRMB_AVG(){
        ac = new Account(100000L, 0.1, 0L, debit10DollarPerMonth);
        simpleInterestor = new InterestCalculator(ac, 365, InterestType.SIMPLE, CalculationRule.AVERAGE);
        long actualInt = simpleInterestor.getInterestAmount();
        Assert.assertEquals(9400L, actualInt);
    }

    @Test
    public void calcSimpleInterestNonZeroBalanceAndAboveRMB_AVG(){
        ac = new Account(100000L, 0.1, 0L, debit10DollarPerMonth);
        simpleInterestor = new InterestCalculator(ac, 365, InterestType.SIMPLE, CalculationRule.AVERAGE);
        long actualInt = simpleInterestor.getInterestAmount();
        Assert.assertEquals(9400L, actualInt);
    }

    @Test
    public void calcSimpleInterestWithZeroBalanceAndNoRMB_AVG(){
        ac = new Account(0L, 0.1, 0L, debit10DollarPerMonth);
        simpleInterestor = new InterestCalculator(ac, 365, InterestType.SIMPLE, CalculationRule.AVERAGE);
        long actualInt = simpleInterestor.getInterestAmount();
        Assert.assertEquals(0L, actualInt);
    }

    @Test
    public void calcSimpleInterestWithZeroBalanceAndBelowRMB_AVG() {
        ac = new Account(0L, 0.1, 500000L, debit10DollarPerMonth);
        simpleInterestor = new InterestCalculator(ac, 365, InterestType.SIMPLE, CalculationRule.AVERAGE);
        long actualInt = simpleInterestor.getInterestAmount();
        Assert.assertEquals(0L, actualInt);
    }

    @Test // TODO this needs changed, we are gonna get a penalty when overdrawn
    public void calcSimpleInterestWithNegativeBalanceAndNoRMB_AVG() {
        ac = new Account(-100L, 0.1, 0L, debit10DollarPerMonth);
        simpleInterestor = new InterestCalculator(ac, 365, InterestType.SIMPLE, CalculationRule.AVERAGE);
        long actualInt = simpleInterestor.getInterestAmount();
        Assert.assertEquals(0L, actualInt);
    }

    @Test
    public void calcSimpleInterestWithNegativeBalanceAndBelowRMB_AVG() {
        ac = new Account(-100L, 0.1, 50000L, debit10DollarPerMonth);
        simpleInterestor = new InterestCalculator(ac, 365, InterestType.SIMPLE, CalculationRule.AVERAGE);
        long actualInt = simpleInterestor.getInterestAmount();
        Assert.assertEquals(0L, actualInt);
    }

    @Test
    public void calcSimpleInterestNonZeroBalanceWithDeductionsThatDoNotExceedInterest_AVG() {
        ac = new Account(100000L, 0.1, 0L, debit10DollarPerMonth);
        simpleInterestor = new InterestCalculator(ac, 365, InterestType.SIMPLE, CalculationRule.AVERAGE);
        long actualInt = simpleInterestor.getInterestAmount();
        Assert.assertEquals(9400L, actualInt);
    }

    @Test
    public void calcSimpleInterestNonZeroBalanceWithDeductionsThatDoExceedInterestEarned_AVG() {
        ac = new Account(100000L, 0.1, 0L, debit20DollarPerMonth);
        simpleInterestor = new InterestCalculator(ac, 365, InterestType.SIMPLE, CalculationRule.AVERAGE);
        long actualInt = simpleInterestor.getInterestAmount();
        Assert.assertEquals(8800L, actualInt);
    }

    @Test
    public void calcSimpleInterestNonZeroBalanceAndBelowRMBWithCreditsThatDoNotBringBalanceAboveRMB_AVG() {
        ac = new Account(10000L, 0.1, 20000L, credit20DollarPerMonth);
        simpleInterestor = new InterestCalculator(ac, 365, InterestType.SIMPLE, CalculationRule.AVERAGE);
        long actualInt = simpleInterestor.getInterestAmount();
        Assert.assertEquals(2200L, actualInt);
    }

    @Test
    public void calcSimpleInterestOverdrawnWithCreditsThatDoNotBringBalanceAboveZERO_AVG() {
        ac = new Account(-10000L, 0.1, 0L, credit1DollarPerMonth);
        simpleInterestor = new InterestCalculator(ac, 365, InterestType.SIMPLE, CalculationRule.AVERAGE);
        long actualInt = simpleInterestor.getInterestAmount();
        Assert.assertEquals(0L, actualInt);
    }

    @Test
    public void calcSimpleInterestOverdrawnWithCreditsThatBringBalanceAboveZERO_AVG() {
        ac = new Account(-100L, 0.1, 0L, credit20DollarPerMonth);
        simpleInterestor = new InterestCalculator(ac, 365, InterestType.SIMPLE, CalculationRule.AVERAGE);
        long actualInt = simpleInterestor.getInterestAmount();
        Assert.assertEquals(1190L, actualInt);
    }


    // CalculationRule: MAX
    @Test
    public void calcSimpleInterestNonZeroBalanceAndNoRMB_MAX(){
        ac = new Account(100000L, 0.1, 0L, debit10DollarPerMonth);
        simpleInterestor = new InterestCalculator(ac, 365, InterestType.SIMPLE, CalculationRule.MAXIMUM);
        long actualInt = simpleInterestor.getInterestAmount();
        Assert.assertEquals(10000L, actualInt);
    }

    @Test
    public void calcSimpleInterestNonZeroBalanceAndAboveRMB_MAX(){
        ac = new Account(100000L, 0.1, 0L, debit10DollarPerMonth);
        simpleInterestor = new InterestCalculator(ac, 365, InterestType.SIMPLE, CalculationRule.MAXIMUM);
        long actualInt = simpleInterestor.getInterestAmount();
        Assert.assertEquals(10000L, actualInt);
    }

    @Test
    public void calcSimpleInterestWithZeroBalanceAndNoRMB_MAX(){
        ac = new Account(0L, 0.1, 0L, debit10DollarPerMonth);
        simpleInterestor = new InterestCalculator(ac, 365, InterestType.SIMPLE, CalculationRule.MAXIMUM);
        long actualInt = simpleInterestor.getInterestAmount();
        Assert.assertEquals(0L, actualInt);
    }

    @Test
    public void calcSimpleInterestWithZeroBalanceAndBelowRMB_MAX() {
        ac = new Account(0L, 0.1, 500000L, debit10DollarPerMonth);
        simpleInterestor = new InterestCalculator(ac, 365, InterestType.SIMPLE, CalculationRule.MAXIMUM);
        long actualInt = simpleInterestor.getInterestAmount();
        Assert.assertEquals(0L, actualInt);
    }

    @Test // TODO this needs changed, we are gonna get a penalty when overdrawn
    public void calcSimpleInterestWithNegativeBalanceAndNoRMB_MAX() {
        ac = new Account(-100L, 0.1, 0L, debit10DollarPerMonth);
        simpleInterestor = new InterestCalculator(ac, 365, InterestType.SIMPLE, CalculationRule.MAXIMUM);
        long actualInt = simpleInterestor.getInterestAmount();
        Assert.assertEquals(0L, actualInt);
    }

    @Test
    public void calcSimpleInterestWithNegativeBalanceAndBelowRMB_MAX() {
        ac = new Account(-100L, 0.1, 50000L, debit10DollarPerMonth);
        simpleInterestor = new InterestCalculator(ac, 365, InterestType.SIMPLE, CalculationRule.MAXIMUM);
        long actualInt = simpleInterestor.getInterestAmount();
        Assert.assertEquals(0L, actualInt);
    }

    @Test
    public void calcSimpleInterestNonZeroBalanceWithDeductionsThatDoNotExceedInterest_MAX() {
        ac = new Account(100000L, 0.1, 0L, debit10DollarPerMonth);
        simpleInterestor = new InterestCalculator(ac, 365, InterestType.SIMPLE, CalculationRule.MAXIMUM);
        long actualInt = simpleInterestor.getInterestAmount();
        Assert.assertEquals(10000L, actualInt);
    }

    @Test
    public void calcSimpleInterestNonZeroBalanceWithDeductionsThatDoExceedInterestEarned_MAX() {
        ac = new Account(100000L, 0.1, 0L, debit20DollarPerMonth);
        simpleInterestor = new InterestCalculator(ac, 365, InterestType.SIMPLE, CalculationRule.MAXIMUM);
        long actualInt = simpleInterestor.getInterestAmount();
        Assert.assertEquals(10000L, actualInt);
    }

    @Test
    public void calcSimpleInterestNonZeroBalanceAndBelowRMBWithCreditsThatDoNotBringBalanceAboveRMB_MAX() {
        ac = new Account(10000L, 0.1, 20000L, credit1DollarPerMonth);
        simpleInterestor = new InterestCalculator(ac, 365, InterestType.SIMPLE, CalculationRule.MAXIMUM);
        long actualInt = simpleInterestor.getInterestAmount();
        Assert.assertEquals(0L, actualInt);
    }

    @Test
    public void calcSimpleInterestOverdrawnWithCreditsThatDoNotBringBalanceAboveZERO_MAX() {
        ac = new Account(-10000L, 0.1, 0L, credit1DollarPerMonth);
        simpleInterestor = new InterestCalculator(ac, 365, InterestType.SIMPLE, CalculationRule.MAXIMUM);
        long actualInt = simpleInterestor.getInterestAmount();
        Assert.assertEquals(0L, actualInt);
    }

    @Test
    public void calcSimpleInterestOverdrawnWithCreditsThatBringBalanceAboveZERO_MAX() {
        ac = new Account(-100L, 0.1, 0L, credit20DollarPerMonth);
        simpleInterestor = new InterestCalculator(ac, 365, InterestType.SIMPLE, CalculationRule.MAXIMUM);
        long actualInt = simpleInterestor.getInterestAmount();
        Assert.assertEquals(2390L, actualInt);
    }

    // CalculationRule: MIN
    @Test
    public void calcSimpleInterestNonZeroBalanceAndNoRMB_MIN(){
        ac = new Account(100000L, 0.1, 0L, debit10DollarPerMonth);
        simpleInterestor = new InterestCalculator(ac, 365, InterestType.SIMPLE, CalculationRule.MAXIMUM);
        long actualInt = simpleInterestor.getInterestAmount();
        Assert.assertEquals(10000L, actualInt);
    }

    @Test
    public void calcSimpleInterestNonZeroBalanceAndAboveRMB_MIN(){
        ac = new Account(500000L, 0.1, 0L, debit10DollarPerMonth);
        simpleInterestor = new InterestCalculator(ac, 365, InterestType.SIMPLE, CalculationRule.MAXIMUM);
        long actualInt = simpleInterestor.getInterestAmount();
        Assert.assertEquals(50000L, actualInt);
    }

    @Test
    public void calcSimpleInterestWithZeroBalanceAndNoRMB_MIN(){
        ac = new Account(0L, 0.1, 0L, debit10DollarPerMonth);
        simpleInterestor = new InterestCalculator(ac, 365, InterestType.SIMPLE, CalculationRule.MINIMUM);
        long actualInt = simpleInterestor.getInterestAmount();
        Assert.assertEquals(0L, actualInt);
    }

    @Test
    public void calcSimpleInterestWithZeroBalanceAndBelowRMB_MIN() {
        ac = new Account(0L, 0.1, 500000L, debit10DollarPerMonth);
        simpleInterestor = new InterestCalculator(ac, 365, InterestType.SIMPLE, CalculationRule.MINIMUM);
        long actualInt = simpleInterestor.getInterestAmount();
        Assert.assertEquals(0L, actualInt);
    }

    @Test // TODO this needs changed, we are gonna get a penalty when overdrawn
    public void calcSimpleInterestWithNegativeBalanceAndNoRMB_MIN() {
        ac = new Account(-100L, 0.1, 0L, debit10DollarPerMonth);
        simpleInterestor = new InterestCalculator(ac, 365, InterestType.SIMPLE, CalculationRule.MINIMUM);
        long actualInt = simpleInterestor.getInterestAmount();
        Assert.assertEquals(0L, actualInt);
    }

    @Test
    public void calcSimpleInterestWithNegativeBalanceAndBelowRMB_MIN() {
        ac = new Account(-100L, 0.1, 50000L, debit10DollarPerMonth);
        simpleInterestor = new InterestCalculator(ac, 365, InterestType.SIMPLE, CalculationRule.MINIMUM);
        long actualInt = simpleInterestor.getInterestAmount();
        Assert.assertEquals(0L, actualInt);
    }

    @Test
    public void calcSimpleInterestNonZeroBalanceWithDeductionsThatDoNotExceedInterest_MIN() {
        ac = new Account(100000L, 0.1, 0L, debit10DollarPerMonth);
        simpleInterestor = new InterestCalculator(ac, 365, InterestType.SIMPLE, CalculationRule.MINIMUM);
        long actualInt = simpleInterestor.getInterestAmount();
        Assert.assertEquals(8800L, actualInt);
    }

    @Test
    public void calcSimpleInterestNonZeroBalanceWithDeductionsThatDoExceedInterestEarned_MIN() {
        ac = new Account(100000L, 0.1, 0L, debit20DollarPerMonth);
        simpleInterestor = new InterestCalculator(ac, 365, InterestType.SIMPLE, CalculationRule.MINIMUM);
        long actualInt = simpleInterestor.getInterestAmount();
        Assert.assertEquals(7600L, actualInt);
    }

    @Test
    public void calcSimpleInterestNonZeroBalanceAndBelowRMBWithCreditsThatDoNotBringBalanceAboveRMB_MIN() {
        ac = new Account(10000L, 0.1, 20000L, credit1DollarPerMonth);
        simpleInterestor = new InterestCalculator(ac, 365, InterestType.SIMPLE, CalculationRule.MINIMUM);
        long actualInt = simpleInterestor.getInterestAmount();
        Assert.assertEquals(0L, actualInt);
    }

    @Test
    public void calcSimpleInterestOverdrawnWithCreditsThatDoNotBringBalanceAboveZERO_MIN() {
        ac = new Account(-10000L, 0.1, 0L, credit1DollarPerMonth);
        simpleInterestor = new InterestCalculator(ac, 365, InterestType.SIMPLE, CalculationRule.MINIMUM);
        long actualInt = simpleInterestor.getInterestAmount();
        Assert.assertEquals(0L, actualInt);
    }

    @Test
    public void calcSimpleInterestOverdrawnWithCreditsThatBringBalanceAboveZERO_MIN() {
        ac = new Account(-100L, 0.1, 0L, credit20DollarPerMonth);
        simpleInterestor = new InterestCalculator(ac, 365, InterestType.SIMPLE, CalculationRule.MINIMUM);
        long actualInt = simpleInterestor.getInterestAmount();
        Assert.assertEquals(0L, actualInt);
    }

}
