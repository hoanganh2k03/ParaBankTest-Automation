package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class TransferFundsPage {
    WebDriver driver;
    WebDriverWait wait;

    // ✅ Locators
    private By amountField = By.id("amount");
    private By fromAccountDropdown = By.id("fromAccountId");
    private By toAccountDropdown = By.id("toAccountId");
    private By transferButton = By.cssSelector("input[value='Transfer']");
    private By titleResult = By.cssSelector("#showResult h1.title");

    private By amountResult = By.id("amountResult");
    private By fromAccountResult = By.id("fromAccountIdResult");
    private By toAccountResult = By.id("toAccountIdResult");

    public TransferFundsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // 🔹 Mở trang Transfer Funds
    public void open() {
        driver.findElement(By.linkText("Transfer Funds")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(amountField));
    }

    // 🔹 Nhập số tiền
    public void enterAmount(String amount) {
        WebElement field = wait.until(ExpectedConditions.elementToBeClickable(amountField));
        field.clear();
        field.sendKeys(amount);
    }

    // 🔹 Chọn From Account
    public void selectFromAccount(String account) {
        Select select = new Select(driver.findElement(fromAccountDropdown));
        select.selectByVisibleText(account);
    }

    // 🔹 Chọn To Account
    public void selectToAccount(String account) {
        Select select = new Select(driver.findElement(toAccountDropdown));
        select.selectByVisibleText(account);
    }

    // 🔹 Click Transfer
    public void clickTransfer() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(transferButton));
        Actions actions = new Actions(driver);
        actions.moveToElement(button).click().perform();
    }

    // 🔹 Verify trang Transfer Complete
    public boolean isTransferComplete() {
        try {
            WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(titleResult));
            return title.getText().equalsIgnoreCase("Transfer Complete!");
        } catch (TimeoutException e) {
            return false;
        }
    }

    // 🔹 Lấy thông tin kết quả
    public String getAmountResult() {
        return driver.findElement(amountResult).getText();
    }

    public String getFromAccountResult() {
        return driver.findElement(fromAccountResult).getText();
    }

    public String getToAccountResult() {
        return driver.findElement(toAccountResult).getText();
    }
}
