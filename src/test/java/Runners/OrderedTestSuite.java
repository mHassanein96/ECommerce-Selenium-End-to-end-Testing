package Runners;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        RegistrationTest.class,
        LoginTest.class,
        SearchTest.class,
        WishlistTest.class,
        CartTest.class
})
public class OrderedTestSuite {
}