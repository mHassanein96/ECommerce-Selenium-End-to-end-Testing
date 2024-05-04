package stepDefinition;

import Pages.HomepageWebElements;
import Pages.LoginPageWebElements;
import Pages.RegisterPageWebElements;
import Pages.RegistrationResultWebElements;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

/**
 * This class contains step definitions for the Registration feature of the application.
 * It uses Selenium WebDriver for browser interactions and TestNG for assertions.
 */
public class RegistrationStepDefinition {
    WebDriver driver = null;
    RegisterPageWebElements regWE;
    HomepageWebElements homeWE;
    RegistrationResultWebElements regResWE;
    LoginPageWebElements logWE;
    SoftAssert softAssert;

    /**
     * This method initializes the browser and sets up the necessary web elements.
     */
    @Given("User opens the browser_Registration feature")
    public void init_driver (){
        //Creating Driver
        String firfoxPath = System.getProperty("user.dir") + "\\src\\main\\resources\\geckodriver.exe";
        System.setProperty("webDriver.gecko.driver",firfoxPath);

        driver = new FirefoxDriver();
        regWE = new RegisterPageWebElements(driver);
        homeWE = new HomepageWebElements(driver);
        regResWE = new RegistrationResultWebElements(driver);
        logWE = new LoginPageWebElements(driver);
        softAssert = new SoftAssert();
    }

    /**
     * This method navigates to the specified website.
     * @param Website The URL of the website to navigate to.
     */
    @Given("User navigates to the website \"(.*)\" Registration feature$")
    public void user_navigates_to_the_Website (String Website) throws InterruptedException {
        driver.navigate().to(Website);
        driver.manage().window().maximize();
        String currentWebsite = driver.getCurrentUrl();
        Assert.assertTrue(currentWebsite.contains(Website),"Website isn't right");
        Thread.sleep(1000);
    }

    /**
     * This method clicks on the register button on the home page.
     */
    @And("Clicks on register button from the homepage")
    public void clicks_on_the_register_button () throws InterruptedException {
        homeWE.regWE().click();
        Thread.sleep(1000);
    }

    /**
     * This method selects the gender on the registration form.
     */
    @When("User selects the gender")
    public void user_selects_the_gender () {
        regWE.genderWE().click();
    }

    /**
     * This method enters the provided first name into the first name field on the registration form.
     * @param firstName The first name to enter.
     */
    @And("enters first name \"(.*)\"$")
    public void enters_first_name (String firstName) {
        regWE.firstNameWE().clear();
        regWE.firstNameWE().sendKeys(firstName);
    }

    /**
     * This method enters the provided last name into the last name field on the registration form.
     * @param lastName The last name to enter.
     */
    @And("enters last name \"(.*)\"$")
    public void enters_last_name (String lastName) {
        regWE.lastNameWE().clear();
        regWE.lastNameWE().sendKeys(lastName);
    }

    /**
     * This method enters the provided date of birth into the date of birth field on the registration form.
     * @param day The day of birth to enter.
     * @param month The month of birth to enter.
     * @param year The year of birth to enter.
     */
    @And("enters date of birth \"(.*)\" \"(.*)\" \"(.*)\"$")
    public void enters_date_of_birth (String day, String month, String year) {
        Select drpDay = new Select(regWE.day());
        drpDay.selectByValue(day);
        Select drpMonth = new Select(regWE.month());
        drpMonth.selectByVisibleText(month);
        Select drpYear = new Select(regWE.year());
        drpYear.selectByValue(year);
    }

    /**
     * This method enters the provided email into the email field on the registration form.
     * @param email The email to enter.
     */
    @And("enters a valid email \"(.*)\"$")
    public void enters_valid_email (String email) {
        regWE.emailWE().clear();
        regWE.emailWE().sendKeys(email);
    }

    /**
     * This method enters the provided password into the password field on the registration form.
     * @param password The password to enter.
     */
    @And("enters a password \"(.*)\"$")
    public void enter_password (String password) {
        regWE.passwordWE().clear();
        regWE.passwordWE().sendKeys(password);
    }

    /**
     * This method enters the provided password into the confirm password field on the registration form.
     * @param password The password to enter.
     */
    @And("enters the same password again \"(.*)\"$")
    public void re_enter_password (String password) {
        regWE.confirmPasswordWE().clear();
        regWE.confirmPasswordWE().sendKeys(password);
    }

    /**
     * This method clicks on the register button on the registration form.
     */
    @And("click register")
    public void click_register () throws InterruptedException {
        regWE.regButton().click();
        Thread.sleep(3000);
    }

    /**
     * This method asserts that the registration was successful by checking for the presence of a success message.
     * @param msg The expected success message.
     */
    @Then("User should see a success message \"(.*)\"$")
    public void succsess_message (String msg) {
        softAssert.assertTrue(regResWE.resultMSG().getText().contains(msg),"Expected result doesn't equal the actual result");
    }

    /**
     * This method asserts that the success message is displayed in the correct color.
     * @param expectedHex The expected color of the success message in hexadecimal format.
     */
    @And("the color should be green hex code= \"(.*)\"$")
    public void succsess_msg_color (String expectedHex){
        SoftAssert softAssert = new SoftAssert();
        String color = regResWE.resultMSG().getCssValue("color");
        String actualHex = Color.fromString(color).asHex();
        softAssert.assertTrue(actualHex.contains(expectedHex));
        softAssert.assertAll();
    }

    /**
     * This method closes the browser and asserts all soft assertions.
     */
    @And("Closes the browser_Registration feature")
    public void close_browser () throws InterruptedException {
        Thread.sleep(1000);

        softAssert.assertAll();
        driver.quit();
    }
}