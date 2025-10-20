package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class RequestLoanPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // ===== Locators =====
    private By amountField = By.id("amount");
    private By downPaymentField = By.id("downPayment");
    private By fromAccountSelect = By.cssSelector("select#fromAccountId");
    private By applyButton = By.cssSelector("input[value='Apply Now']");
    private By title = By.xpath("//div[@id='requestLoanResult']//h1");
    private By loanId = By.id("newAccountId");
    private By loanStatus = By.id("loanStatus");

    public RequestLoanPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ===== Actions =====
    public void open() {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Request Loan"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(amountField));
    }

    public void requestLoan(String amount, String downPayment) {
        WebElement amountInput = wait.until(ExpectedConditions.visibilityOfElementLocated(amountField));
        amountInput.clear();
        amountInput.sendKeys(amount);

        WebElement downPaymentInput = driver.findElement(downPaymentField);
        downPaymentInput.clear();
        downPaymentInput.sendKeys(downPayment);

        WebElement selectElement = driver.findElement(fromAccountSelect);
        Select select = new Select(selectElement);
        select.selectByIndex(0);

        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(applyButton));
        Actions actions = new Actions(driver);
        actions.moveToElement(button).click().perform();
    }

    // ===== Getters =====
    public String getTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(title)).getText();
    }

    public String getLoanId() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loanId)).getText();
    }

    public String getLoanStatus() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loanStatus)).getText();
    }
}
