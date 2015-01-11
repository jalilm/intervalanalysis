package tests.unittests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BottomTest.class, IntervalTest.class, NegativeInfTest.class,
        PositiveInfTest.class, TestIntervalLattice.class, TopTest.class, TestLogicalOps.class })
public class UnitTests {

}
