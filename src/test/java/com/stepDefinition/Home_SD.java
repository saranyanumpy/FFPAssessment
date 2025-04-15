package com.stepDefinition;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.Hooks.TestContext;
import com.pageObjects.HomePage;
import com.utilities.ReadConfig;
import com.utilities.Log;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Home_SD {
    WebDriver driver;
    TestContext context;
    ReadConfig readConfig;
    HomePage homePage;
    String actualErrMsg;
    List<String> actualErrMsgList;
    SoftAssert softAssert;

    // Create the logger instance
    private static final Logger logger = LogManager.getLogger(Home_SD.class);

    public Home_SD(TestContext Context) {
        this.context = Context;
        this.driver = context.getDriver();
        this.readConfig = new ReadConfig();
        softAssert = new SoftAssert();
    }

    @Given("I launch the application")
    public void i_launch_the_application() {
        String browserName = readConfig.getbrowser();
        driver = context.getDriverFactory().initialiseBrowser(browserName);
        homePage = new HomePage(driver);
        
        // Add logging
        logger.info("Launching the application on browser: " + browserName);
    }

    @Then("The logo should be displayed")
    public void theLogoShouldBeDisplayed() {
        logger.info("Checking if the logo is displayed");
        Assert.assertTrue(homePage.getLogo().isDisplayed(), "Logo should be visible on homepage");
    }

    @Then("The logo should have the aria-label {string}")
    public void theLogoShouldHaveTheAriaLabel(String expectedAriaLabel) {
        logger.info("Checking the aria-label of the logo");
        Assert.assertEquals(homePage.getLogoAriaLabel(), expectedAriaLabel, "Aria-label should match");
    }

    @Then("The logo background image should contain {string}")
    public void theLogoBackgroundImageShouldContain(String expectedImageUrl) {
        logger.info("Validating the logo background image URL");
        String actualLogoUrl = homePage.getLogoBackgroundImage();
        String cleanedUrl = actualLogoUrl.replace("url(\"", "").replace("\")", "");
        logger.debug("Cleaned logo URL: " + cleanedUrl);
        
        Assert.assertTrue(cleanedUrl.contains(expectedImageUrl),
            "Expected logo URL to contain '" + expectedImageUrl + "', but was: " + cleanedUrl);
    }

    @Then("Login and Join Now buttons should be visible")
    public void login_and_join_now_buttons_should_be_visible() {
        logger.info("Checking if Login and Join Now buttons are visible");
        Assert.assertTrue(homePage.isJoinNowBtnVisible(), "Join Now button should be visible");
        Assert.assertTrue(homePage.isLoginBtnVisible(), "Login button should be visible");
    }

    @When("I click on the Join Now button without entering email and password")
    public void i_click_on_the_join_now_button_without_entering_email_and_password() {
        logger.info("Clicking the Join Now button without email and password");
        homePage.clickJoinNowBtn();
    }

    @Then("The email field should display the error {string}")
    public void the_email_field_should_display_the_error(String expectedEmailError) {
        if (homePage.isCaptchaPresent()) {
            logger.info("CAPTCHA detected. Skipping email error validation.");
            return;
        }
        logger.info("Validating email field error message");
        String actualError = homePage.getEmailErrorText();
        Assert.assertEquals(actualError, expectedEmailError);
    }

    @Then("The password field should display the error {string}")
    public void the_password_field_should_display_the_error(String expectedPasswordError) {
        if (homePage.isCaptchaPresent()) {
            logger.info("CAPTCHA detected. Skipping password error validation.");
            return;
        }
        logger.info("Validating password field error message");
        String actualError = homePage.getPasswordErrorText();
        Assert.assertEquals(actualError, expectedPasswordError);
    }

    @When("I click on the Join Now button by entering email and password and title should be {string}")
    public void i_click_on_the_join_now_button_by_entering_email_and_password(String expectedTitle) throws InterruptedException {
        logger.info("Clicking Join Now button with email and password");
        homePage.signUpBtn();
        Thread.sleep(5000);
        String actualTitle = homePage.getAfterJoinowTitle();
        logger.info("Verifying page title after Join Now: " + actualTitle);
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @When("I click the login button and it should redirect with page title should be {string}")
    public void i_click_the_login_buttonit_should_redirect_with_page_title_should_be(String expectedTitle) {
        logger.info("Clicking the Login button and verifying page title");
        homePage.clickLoginNowBtn();
        String actualTitle = homePage.getLoginTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Then("I click on the Login button with positive Email and Password as input and validating the page title as {string}")
    public void i_click_on_the_login_button_with_positive_email_and_password_as_input_and_validating_the_page_title_as(String expectedTitle) {
        logger.info("Logging in with valid credentials and verifying the page title");
        homePage.submitLogin();
        String actualTitle = homePage.getAfterLoggingInTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
        logger.info("Successfully logged in with title: " + actualTitle);
    }
 

}
