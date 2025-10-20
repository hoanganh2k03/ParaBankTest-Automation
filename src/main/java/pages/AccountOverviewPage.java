package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AccountOverviewPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // 🔹 Locators
    private By title = By.cssSelector("h1.title");
    private By accountRows = By.cssSelector("table#accountTable tbody tr");
    private By accountsLink = By.linkText("Accounts Overview");
    private By accountLinks = By.cssSelector("table#accountTable tbody tr td a");
    // 🔹 Constructor
    public AccountOverviewPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // 🔹 Actions
    public void open() {
        wait.until(ExpectedConditions.elementToBeClickable(accountsLink)).click();
    }

    public boolean isAtOverviewPage() {
        WebElement titleEl = wait.until(ExpectedConditions.visibilityOfElementLocated(title));
        return titleEl.getText().equalsIgnoreCase("Accounts Overview");
    }

    public int getAccountCount() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(accountRows));
        List<WebElement> accounts = driver.findElements(accountRows);
        return accounts.size();
    }
    public String clickFirstAccount() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(accountLinks));
        List<WebElement> accounts = driver.findElements(accountLinks);
        if (accounts.isEmpty()) {
            throw new RuntimeException("❌ Không có tài khoản nào trong danh sách!");
        }
        Actions action = new Actions(driver);
        String accountNumber = accounts.get(0).getText();
        action.moveToElement(accounts.get(0)).click().perform();
        
        return accountNumber;
    }

}
