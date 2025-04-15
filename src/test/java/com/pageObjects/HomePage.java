package com.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import com.utilities.ElementUtil;
import com.utilities.Log;

public class HomePage {

	private WebDriver driver;
	private ElementUtil util;

	// Locators
	private By logoImage = By.xpath("//div//a[@class='loh-logo']");
	private By ariaLabel = By.xpath("//a[@aria-label='Go to homepage']");
	private By loginBtn = By.xpath("//header//div[@class='loh-header-wrap']//a[@id='ctl00_BodyMain_SignINButton']");
	private By joinNowBtn = By.xpath("//div//button[@id='btnJoin']");
	private By emailError = By.xpath("//div//span[@id='ctl00_BodyMain_JoinSlice_JoinForm_ctl04_ErrorMessageLabel']");
	private By passwordError = By.xpath("//div//span[@id='ctl00_BodyMain_JoinSlice_JoinForm_ctl05_ErrorMessageLabel']");
	private By loginNavigationTitle = By.xpath("//div//span[@id='ctl00_GeckoOneColPrimary_Login_lblSalutationMsg']");
	private By emailInput=By.xpath("//div//input[@id='emailInput']");
	private By passwordInput=By.xpath("//div//input[@id='passwordInput']");
	private By emailLoginInput=By.xpath("//div//input[@id='txtEmail']");
	private By passwordLoginInput=By.xpath("//div//input[@id='loginPasswordInput']");
	private By Login=By.xpath("//a//button[@id='Loginbtn']");
	private By titleAfterLoggingIn=By.xpath("//div[@class='grey']//p[contains(text(), 'Apply Cash Back savings as you shop with just one click.')]");
	private By titleAfterJoinnow=By.xpath("//div[@class='gecko-single-container no-overflow acct-overview-box']//span[normalize-space()='Account Overview']");
	// Constructor
	public HomePage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(this.driver);
	}

	public WebElement getLogo() {
        return driver.findElement(logoImage);
    }

    // Method to get the aria-label of the logo
    public String getLogoAriaLabel() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement logoElement = wait.until(ExpectedConditions.visibilityOfElementLocated(ariaLabel));
        
        // Retrieve the aria-label attribute
        return logoElement.getAttribute("aria-label");    }

    // Method to get the background image URL of the logo
    public String getLogoBackgroundImage() {
        WebElement logo = driver.findElement(By.cssSelector(".loh-logo"));
        return logo.getCssValue("background-image");
    }
	// Method to check if Login button is visible
	public boolean isLoginBtnVisible() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(loginBtn));
		return loginButton.isDisplayed();
	}

	// Method to check if Join Now button is visible
	public boolean isJoinNowBtnVisible() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement joinNowButton = wait.until(ExpectedConditions.visibilityOfElementLocated(joinNowBtn));
		return joinNowButton.isDisplayed();
	}
	public void clickJoinNowBtn() {
		driver.findElement(joinNowBtn).click();
	}
	public String getEmailErrorText() {
	    return driver.findElement(emailError).getText();
	}

	// Get error text from password field
	public String getPasswordErrorText() {
	    return driver.findElement(passwordError).getText();
	}
	public boolean isCaptchaPresent() {
	    try {
	        return driver.findElements(By.xpath("//iframe[contains(@src,'recaptcha')]")).size() > 0;
	    } catch (Exception e) {
	        return false;
	    }
	}  
	public void clickLoginNowBtn() {
			driver.findElement(loginBtn).click();
	}
	
	public String getLoginTitle() {
	    return driver.findElement(loginNavigationTitle).getText();
	}
	public void signUpBtn() {
		driver.findElement(emailInput).sendKeys("qaautomation@mailinator.com");
		driver.findElement(passwordInput).sendKeys("Testing@123");
		driver.findElement(joinNowBtn).click();
	}
	public void submitLogin() {
		driver.findElement(emailLoginInput).sendKeys("saranyasridhar06@gmail.com");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordLoginInput));

		passwordField.sendKeys("Hayati@123");
		driver.findElement(Login).click();
		
	}
	public String getAfterLoggingInTitle() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(titleAfterLoggingIn));
		return titleElement.getText();
	}
	public String getAfterJoinowTitle() {
		return driver.findElement(titleAfterJoinnow).getText();
	}
}
