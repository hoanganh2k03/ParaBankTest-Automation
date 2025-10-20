package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class FindTransactionsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By accountSelect = By.id("accountId");
    private By transactionIdInput = By.id("transactionId");
    private By transactionDateInput = By.id("transactionDate");
    private By fromDateInput = By.id("fromDate");
    private By toDateInput = By.id("toDate");
    private By amountInput = By.id("amount");
    private By findByIdBtn = By.id("findById");
    private By findByDateBtn = By.id("findByDate");
    private By findByDateRangeBtn = By.id("findByDateRange");
    private By findByAmountBtn = By.id("findByAmount");
    private By resultTable = By.id("transactionTable");

    // Constructor
    public FindTransactionsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.findElement(By.linkText("Find Transactions")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(accountSelect));
    }

    public void selectAccount(String accountId) {
        WebElement account = wait.until(ExpectedConditions.elementToBeClickable(accountSelect));
        account.sendKeys(accountId);
    }

    public void findByTransactionId(String id) {
        driver.findElement(transactionIdInput).sendKeys(id);
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(findByIdBtn));
        Actions actions = new Actions(driver);
        actions.moveToElement(button).click().perform();
    }

    public void findByDate(String date) {
        driver.findElement(transactionDateInput).sendKeys(date);
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(findByDateBtn));
        Actions actions = new Actions(driver);
        actions.moveToElement(button).click().perform();
    }

    public void findByDateRange(String from, String to) {
        driver.findElement(fromDateInput).sendKeys(from);
        driver.findElement(toDateInput).sendKeys(to);
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(findByDateRangeBtn));
        Actions actions = new Actions(driver);
        actions.moveToElement(button).click().perform();
    }

    public void findByAmount(String amount) {
        driver.findElement(amountInput).sendKeys(amount);
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(findByAmountBtn));
        Actions actions = new Actions(driver);
        actions.moveToElement(button).click().perform();
    }

    public String getResultText() {
        WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(resultTable));
        return table.getText();
    }
}
