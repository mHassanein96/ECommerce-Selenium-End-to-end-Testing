package Runners;

import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * This class is used to run multiple test classes in a specific order.
 * It uses the JUnit framework for running the tests.
 * The order of the tests is determined by the order of the classes in the @Suite.SuiteClasses annotation.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        // The test for the Registration feature is run first.
        RegistrationTest.class,
        // The test for the Login feature is run second.
        LoginTest.class,
        // The test for the Search feature is run third.
        SearchTest.class,
        // The test for the Wishlist feature is run fourth.
        WishlistTest.class,
        // The test for the Cart feature is run last.
        CartTest.class
})
public class OrderedTestSuite {
    // This class does not contain any methods. Its purpose is to control the order of the test execution.
}