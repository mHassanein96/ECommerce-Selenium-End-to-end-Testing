package stepDefinition;

import Pages.HomepageWebElements;
import Pages.SearchPageWebElements;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.asserts.SoftAssert;

import java.util.List;

/**
 * This class contains step definitions for the Search feature of the application.
 * It uses Selenium WebDriver for browser interactions and TestNG for assertions.
 */
public class SearchStepDefinition {

    WebDriver driver = null;
    SoftAssert soft;
    HomepageWebElements homeWE;
    SearchPageWebElements searchWE;

    /**
     * This method initializes the browser and sets up the necessary web elements.
     */
    @Given("the user opens the browser_Search function")
    public void init_browser (){
        //Creating Driver
        String firfoxPath = System.getProperty("user.dir") + "\\src\\main\\resources\\geckodriver.exe";
        System.setProperty("webDriver.gecko.driver",firfoxPath);

        driver = new FirefoxDriver();
        soft = new SoftAssert();
        homeWE = new HomepageWebElements(driver);
        searchWE = new SearchPageWebElements(driver);
    }

    /**
     * This method navigates to the specified website.
     * @param Website The URL of the website to navigate to.
     */
    @And("navigates to the site \"(.*)\"_Search function$")
    public void navigate_to_the_site (String Website) throws InterruptedException {
        driver.navigate().to(Website);
        driver.manage().window().maximize();
        Thread.sleep(1000);
    }

    /**
     * This method clicks on the search bar on the home page.
     */
    @And("click the search bar")
    public void click_search_bar (){
        homeWE.searchBarWE().click();
    }

    /**
     * This method enters the provided keyword into the search bar.
     * @param keyword The keyword to search for.
     */
    @Then("types \"(.*)\" in the search bar$")
    public void search_for_apple (String keyword) throws InterruptedException {
        Thread.sleep(1000);
        homeWE.searchBarWE().sendKeys(keyword);
    }

    /**
     * This method waits for the search suggestions to appear.
     */
    @And("waits till the suggestions appear")
    public void wait_for_suggestions() throws InterruptedException {
        Thread.sleep(2000);
        if (homeWE.suggGridWE().isDisplayed()){
            System.out.println("Suggestions are visible");
        }
        else{
            System.out.println("Suggestions are not visible");
        }
    }

    /**
     * This method clicks on the search button on the home page.
     */
    @And("clicks the search button")
    public void click_search_button (){
        homeWE.searchButtonWE().click();
    }

    /**
     * This method asserts that the products match the provided keyword.
     * @param keyword The keyword that the products should match.
     */
    @And("Assert that the products match the keyword \"(.*)\"$")
    public void assert_keyword (String keyword){
        System.out.println("The keyword is "+keyword);
        List<WebElement> childrenElements = searchWE.gridWB().findElements(By.cssSelector("div[class=\"product-item\"]"));
        for (WebElement childElement : childrenElements) {
            String attributeValue = childElement.getAttribute("data-productid");
            String itemTitle = childElement.getText();
            soft.assertTrue(itemTitle.contains(keyword),"Item with ID " + attributeValue + "Doesn't have the keyword " + keyword + " in it");
            if (itemTitle.contains(keyword)) {
                System.out.println("Item with ID " + attributeValue + " have the keyword " +keyword+ " in it");
            }
        }
    }

    /**
     * This method asserts that the products do not contain the provided keyword.
     * @param keyword The keyword that the products should not contain.
     */
    @And("Assert that the products doesn't contain the keyword \"(.*)\"$")
    public void assert_keyword_does_not_exist(String keyword){
        System.out.println("The keyword is "+keyword);
        List<WebElement> childrenElements = searchWE.gridWB().findElements(By.cssSelector("div[class=\"product-item\"]"));
        for (WebElement childElement : childrenElements) {
            String attributeValue = childElement.getAttribute("data-productid");
            String itemTitle = childElement.getText();
            soft.assertFalse(itemTitle.contains(keyword),"Item with ID " + attributeValue + "Doesn't have the keyword " + keyword + " in it");
            if (!itemTitle.contains(keyword)) {
                System.out.println("Item with ID " + attributeValue + " doesn't have the keyword " +keyword+ " in it");
            }
        }
    }

    /**
     * This method closes the browser and asserts all soft assertions.
     */
    @Then("closes the browser_Search function")
    public void close_browser () throws InterruptedException {
        Thread.sleep(1000);
        soft.assertAll();
        driver.quit();
    }

    /**
     * This method asserts that the search suggestions match the provided keyword.
     * @param keyword The keyword that the search suggestions should match.
     */
    @Then("Assert that the suggestions match the keyword \"(.*)\"$")
    public void assert_suggestion (String keyword) {
        System.out.println("The keyword is "+keyword);
        List<WebElement> childrenElements = homeWE.suggGridWE().findElements(By.cssSelector("li[class=\"ui-menu-item\"]"));
        for (WebElement childElement : childrenElements) {
            String itemTitle = childElement.getText();
            soft.assertTrue(itemTitle.contains(keyword));
            if (itemTitle.contains(keyword)) {
                System.out.println("Items match the keyword " +keyword);
            }
        }
    }
}