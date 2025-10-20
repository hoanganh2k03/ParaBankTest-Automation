package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AccountDetailPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // 🔹 Locators
    private By accountLinks = By.cssSelector("table#accountTable tbody tr td a");
    private By title = By.cssSelector("h1.title");
    private By accountId = By.id("accountId");
    private By accountType = By.id("accountType");
    private By balance = By.id("balance");

    public AccountDetailPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // 🔹 Action: chọn account đầu tiên
  
    // 🔹 Verify đang ở trang Account Details
    public boolean isAtDetailPage() {
        WebElement titleEl = wait.until(ExpectedConditions.visibilityOfElementLocated(title));
        return titleEl.getText().equalsIgnoreCase("Account Details");
    }

    // 🔹 Getters
    public String getAccountId() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(driver -> {
            WebElement el = driver.findElement(accountId);
            String text = el.getText().trim();
            if (!text.isEmpty()) {
                return text; // khi có giá trị -> return
            }
            return null; // nếu rỗng -> tiếp tục chờ
        });
    }

    public String getAccountType() {
    	try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
        return wait.until(ExpectedConditions.visibilityOfElementLocated(accountType)).getText();
    }

    public String getBalance() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(balance)).getText();
    }
}
