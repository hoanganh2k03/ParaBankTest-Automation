package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegisterPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // 🔹 Locators
    private By firstNameField = By.id("customer.firstName");
    private By lastNameField = By.id("customer.lastName");
    private By addressField = By.id("customer.address.street");
    private By cityField = By.id("customer.address.city");
    private By stateField = By.id("customer.address.state");
    private By zipField = By.id("customer.address.zipCode");
    private By phoneField = By.id("customer.phoneNumber");
    private By ssnField = By.id("customer.ssn");
    private By usernameField = By.id("customer.username");
    private By passwordField = By.id("customer.password");
    private By confirmField = By.id("repeatedPassword");
    private By registerButton = By.xpath("//input[@value='Register']");
    private By successMessage = By.xpath("//h1[contains(text(),'Welcome')]");

    // 🔹 Constructor
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // 🔹 Actions
    public void open() {
        driver.get("https://parabank.parasoft.com/parabank/register.htm");
    }

    public void fillForm(String firstName, String lastName, String address, String city,
                         String state, String zip, String phone, String ssn,
                         String username, String password) {

        type(firstNameField, firstName);
        type(lastNameField, lastName);
        type(addressField, address);
        type(cityField, city);
        type(stateField, state);
        type(zipField, zip);
        type(phoneField, phone);
        type(ssnField, ssn);
        type(usernameField, username);
        type(passwordField, password);
        type(confirmField, password);
    }

    public void clickRegister() throws InterruptedException {
        safeClick(registerButton);
    }

    public boolean isRegistered() {
        WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        return msg.isDisplayed();
    }

    // 🔹 Helper methods
    private void type(By locator, String text) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        el.clear();
        el.sendKeys(text);
    }

    private void safeClick(By locator) throws InterruptedException {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
        Thread.sleep(500);
        el.click();    
    }
}
