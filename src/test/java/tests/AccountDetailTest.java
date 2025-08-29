package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class AccountDetailTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://parabank.parasoft.com/parabank/index.htm");

        // Login
        driver.findElement(By.name("username")).sendKeys("uechi");  // đổi theo account của bạn
        driver.findElement(By.name("password")).sendKeys("demo");
        driver.findElement(By.cssSelector("input[value='Log In']")).click();
    }

    @Test
    public void testAccountDetail() throws InterruptedException {
    	Thread.sleep(1000);
        // Vào Accounts Overview
        driver.findElement(By.linkText("Accounts Overview")).click();

        // Lấy danh sách account
        Thread.sleep(1000);
        List<WebElement> accounts = driver.findElements(By.cssSelector("table#accountTable tbody tr td a"));
        Assert.assertTrue(accounts.size() > 0, "❌ Không có account nào trong Accounts Overview!");

        // Click vào account đầu tiên
        String accountNumber = accounts.get(0).getText();
        accounts.get(0).click();
        Thread.sleep(1000);
        // Verify title
        WebElement title = driver.findElement(By.cssSelector("h1.title"));
        Assert.assertEquals(title.getText(), "Account Details", "❌ Không vào được trang Account Details!");

        // Verify số tài khoản hiển thị đúng
        WebElement accountId = driver.findElement(By.id("accountId"));
        Assert.assertEquals(accountId.getText(), accountNumber, "❌ Account number không khớp!");

        // Verify Account Type
        WebElement accountType = driver.findElement(By.id("accountType"));
        Assert.assertTrue(accountType.isDisplayed(), "❌ Account Type không hiển thị!");

        // Verify Balance
        WebElement balance = driver.findElement(By.id("balance"));
        Assert.assertTrue(balance.isDisplayed(), "❌ Balance không hiển thị!");

        System.out.println("✅ Account Detail loaded: " + accountNumber + " | Type: " + accountType.getText() + " | Balance: " + balance.getText());
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
