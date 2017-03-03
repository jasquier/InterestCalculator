package team.squad;

import org.junit.Before;

import java.util.Date;

/**
 * @author John A. Squier
 * add your name when you work on this file.
 * TODO remove deprecated date usage
 */
public class PastTransactionTests {

    PastTransaction pastTransaction;

    @Before
    public void setup() {
        pastTransaction = new PastTransaction(10000L, new Date(2016, 1, 1));
    }
}
