package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class PayBillPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By nameField = By.name("payee.name");
    private By addressField = By.name("payee.address.street");
    private By cityField = By.name("payee.address.city");
    private By stateField = By.name("payee.address.state");
    private By zipField = By.name("payee.address.zipCode");
    private By phoneField = By.name("payee.phoneNumber");

    private By accountField = By.name("payee.accountNumber");
    private By verifyAccountField = By.name("verifyAccount");
    private By amountField = By.name("amount");
    private By fromAccountSelect = By.name("fromAccountId");
    private By submitButton = By.cssSelector("input[value='Send Payment']");

    private By confirmMessage = By.cssSelector("#billpayResult h1");
    private By confirmedAccount = By.cssSelector("#rightPanel p #fromAccountId");

    public PayBillPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.findElement(By.linkText("Bill Pay")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));
    }

    public void fillPayeeInfo(String name, String street, String city, String state, String zip, String phone) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(addressField).sendKeys(street);
        driver.findElement(cityField).sendKeys(city);
        driver.findElement(stateField).sendKeys(state);
        driver.findElement(zipField).sendKeys(zip);
        driver.findElement(phoneField).sendKeys(phone);
    }

    public void enterAccountInfo(String acc, String verify) {
        driver.findElement(accountField).sendKeys(acc);
        driver.findElement(verifyAccountField).sendKeys(verify);
    }

    public void enterAmount(String amount) {
        driver.findElement(amountField).sendKeys(amount);
    }

    public void selectFromAccount(String accId) {
        WebElement fromAcc = driver.findElement(fromAccountSelect);
        fromAcc.sendKeys(accId);
    }

    public void submitPayment() {
    	WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(submitButton));
        Actions actions = new Actions(driver);
        actions.moveToElement(button).click().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmMessage));
    }

    public String getConfirmMessage() {
        return driver.findElement(confirmMessage).getText();
    }

    public String getConfirmedAccount() {
        return driver.findElement(confirmedAccount).getText();
    }
}
