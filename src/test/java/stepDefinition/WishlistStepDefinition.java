package stepDefinition;

import Pages.HomepageWebElements;
import Pages.ProductWebElements;
import Pages.WishlistWebElements;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

/**
 * This class contains step definitions for the Wishlist feature of the application.
 * It uses Selenium WebDriver for browser interactions and TestNG for assertions.
 */
public class WishlistStepDefinition {
    WebDriver driver = null;
    SoftAssert soft;
    HomepageWebElements homeWE;
    ProductWebElements prodWE;
    WishlistWebElements wishWE;

    /**
     * This method initializes the browser and sets up the necessary web elements.
     */
    @Given("the user opens the browser_Wishlist feature")
    public void init_browser (){
        String firfoxPath = System.getProperty("user.dir") + "\\src\\main\\resources\\geckodriver.exe";
        System.setProperty("webDriver.gecko.driver",firfoxPath);

        driver = new FirefoxDriver();
        soft = new SoftAssert();
        homeWE = new HomepageWebElements(driver);
        prodWE = new ProductWebElements(driver);
        wishWE = new WishlistWebElements(driver);
    }

    /**
     * This method navigates to the specified website.
     * @param Website The URL of the website to navigate to.
     */
    @And("navigates to the site \"(.*)\"_Wishlist function$")
    public void site_navigation (String Website) throws InterruptedException {
        driver.navigate().to(Website);
        driver.manage().window().maximize();
        Thread.sleep(1000);
    }

    /**
     * This method scrolls down the page until the specified product is visible.
     * @param product The product to scroll to.
     */
    @When("the user scrolls down the page until he sees the product \"(.*)\"$")
    public void scroll_down (String product) throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].scrollIntoView();", homeWE.productWE(product));
        Thread.sleep(2000);
    }

    /**
     * This method clicks on the specified product.
     * @param product The product to click on.
     */
    @Then("the user clicks on the product \"(.*)\"$")
    public void clicks_the_product (String product) throws InterruptedException {
        homeWE.productWE(product).click();
        Thread.sleep(1000);
    }

    /**
     * This method clicks on the add to wishlist button and checks the success message and its color.
     * @param successMSG The expected success message.
     * @param expectedHex The expected color of the success message in hexadecimal format.
     */
    @And("clicks on add to wishlist \"(.*)\" \"(.*)\"$")
    public void clicks_on_add_Wishlist (String successMSG,String expectedHex) throws InterruptedException {
        prodWE.addWishlistWE().click();
        Thread.sleep(2000);

        String displayedMSG = prodWE.notifBar().getText();
        String color = prodWE.notifBar().getCssValue("background-color");
        String actualHex = Color.fromString(color).asHex();

        soft.assertTrue(prodWE.notifBar().isDisplayed(),"The notification bar isn't displayed");
        soft.assertTrue(displayedMSG.contains(successMSG),"The notification bar doesn't have the expected message");
        soft.assertTrue(expectedHex.contains(actualHex),"The color of the notification bar is wrong");
        System.out.println(actualHex);
    }

    /**
     * This method clicks on the close button of the notification and checks the wishlist quantity.
     */
    @Then("the user clicks on the button X on the notification")
    public void clicks_on_x () {
        if (prodWE.closeNotifButtonWE().isDisplayed()){
            prodWE.closeNotifButtonWE().click();
        }
        Assert.assertTrue(prodWE.wishlistQTYWE().getText().contains("1"));
    }

    /**
     * This method navigates to the wishlist page and checks that the specified product is present.
     * @param product The product that should be present on the wishlist page.
     */
    @And("goes to the wishlist page \"(.*)\"$")
    public void go_to_wishlist (String product) throws InterruptedException {
        Thread.sleep(3000);
        prodWE.wishlistButtonWE().click();
        Thread.sleep(2000);
        soft.assertTrue(wishWE.productNameWE().getText().contains(product),"The wishlist got the wrong item");
    }
    /**
     * This method closes the browser and asserts all soft assertions.
     */
    @Then("closes the browser_Wishlist function")
    public void close_browser () throws InterruptedException {
        Thread.sleep(3000);
        soft.assertAll();
        driver.quit();
    }
}