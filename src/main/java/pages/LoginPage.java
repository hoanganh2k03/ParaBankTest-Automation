package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    // --- Locators ---
    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton = By.xpath("//input[@value='Log In']");
    private By errorMessage = By.cssSelector("#rightPanel p"); // khi login fail
    private By welcomeMessage = By.cssSelector("h1.title");

    // --- Constructor ---
    public LoginPage(WebDriver driver) {
        this.driver = driver;   
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // --- Actions ---
    public void enterUsername(String username) {
        WebElement user = driver.findElement(usernameField);
        user.clear();
        user.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement pass = driver.findElement(passwordField);
        pass.clear();
        pass.sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    // --- Get Text ---
    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }

    public String getWelcomeMessage() {
    	return wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeMessage)).getText();
    }
}
