package team.squad;

import org.junit.Before;
import team.squad.accounts.RecurringTransaction;

/**
 * @author John A. Squier
 * add your name when you work on this file.
 */
public class RecurringTransactionTests {

    RecurringTransaction recurringTransaction;

    @Before
    public void setup() {
        recurringTransaction = new RecurringTransaction(500L, 365);
    }
}
