package stepDefinition;

import Pages.HomepageWebElements;
import Pages.LoginPageWebElements;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.asserts.SoftAssert;


public class LoginPageStepDefinition {
    WebDriver driver = null;
    HomepageWebElements homeWE;
    LoginPageWebElements logWE;
    SoftAssert soft;

    @Given("User opens the browser_Login feature")
    public void init_driver (){
        //Creating Driver
        String firfoxPath = System.getProperty("user.dir") + "\\src\\main\\resources\\geckodriver.exe";
        System.setProperty("webDriver.gecko.driver",firfoxPath);

        driver = new FirefoxDriver();
        homeWE = new HomepageWebElements(driver);
        logWE = new LoginPageWebElements(driver);
        soft = new SoftAssert();
    }
    @Given("User navigates to the website \"(.*)\" LoginFeature$")
    public void user_navigates_to_the_Website (String Website) throws InterruptedException {
        driver.navigate().to(Website);
        driver.manage().window().maximize();
        Thread.sleep(1000);
    }
    @And("click on the login button")
    public void click_login_button () throws InterruptedException {
        homeWE.loginWE().click();
        Thread.sleep(1000);
    }
    @When("Login with email \"(.*)\"$")
    public void Login_email (String email){
        logWE.emailWB().clear();
        logWE.emailWB().sendKeys(email);
    }
    @And("Login with password \"(.*)\"$")
    public void Login_password (String password){
        logWE.passwordWB().clear();
        logWE.passwordWB().sendKeys(password);
    }
    @Then("Click on log in")
    public void Click_on_login () throws InterruptedException {
        logWE.loginButtonWB().click();
        Thread.sleep(3000);
    }
    @And("assert positive scenario")
    public void assert_positive (){
        soft.assertTrue(driver.findElement(By.cssSelector("div[class=\"header-links-wrapper\"]")).getText().contains("Log out"),"Logout button was not found");
        soft.assertTrue(driver.findElement(By.cssSelector("div[class=\"header-links-wrapper\"]")).getText().contains("My account"),"My account button was not found");
    }
    @And ("assert negative scenario \"(.*)\" \"(.*)\"$")
    public void assert_negative (String actualMsg , String hexCode){
        soft.assertTrue(logWE.failureMsgWB().getText().contains(actualMsg));
        String color = logWE.failureMsgWB().getCssValue("color");
        String actualHex = Color.fromString(color).asHex();
        soft.assertTrue(hexCode.contains(actualHex));
    }
    @And("Closes the browser_Login feature")
    public void close_browser () throws InterruptedException {
        Thread.sleep(1000);

        driver.quit();
        soft.assertAll();
    }
}
