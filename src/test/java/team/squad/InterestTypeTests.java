package team.squad;

import org.junit.Before;
import team.squad.interest.InterestType;

/**
 * @author John A. Squier
 * add your name when you work on this file
 */
public class InterestTypeTests {

    InterestType simple, complex;

    @Before
    public void setup() {
        simple = InterestType.SIMPLE;
        complex = InterestType.COMPLEX;
    }
}