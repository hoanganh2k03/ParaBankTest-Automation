package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class FindTransactionsTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
    	ChromeOptions options = new ChromeOptions();

        // Tạo map prefs
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_leak_detection", false);
        prefs.put("autofill.profile_enabled", false);

     // 🚫 Tắt autofill thẻ credit card
        prefs.put("autofill.credit_card_enabled", false);
        // Gán prefs vào options
        options.setExperimentalOption("prefs", prefs);

        // Thêm một số flags để tắt popup mật khẩu
        options.addArguments("--disable-features=PasswordCheck");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-popup-blocking");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://parabank.parasoft.com/parabank/index.htm");

        // login
        driver.findElement(By.name("username")).sendKeys("uechi");
        driver.findElement(By.name("password")).sendKeys("demo");
        driver.findElement(By.cssSelector("input[value='Log In']")).click();

        // Vào trang Find Transactions
        
    }
    @BeforeMethod
    public void openFindTransacionId() {
    	driver.findElement(By.linkText("Find Transactions")).click();
    }
    @Test
    public void testFindByTransactionId() throws InterruptedException {
        // chọn account
        WebElement accountSelect = driver.findElement(By.id("accountId"));
        accountSelect.sendKeys("12345"); // thay bằng account thật có trong account overview

        driver.findElement(By.cssSelector("input#transactionId")).sendKeys("12145");
        driver.findElement(By.cssSelector("button[type='submit'][id='findById']")).click();
        Thread.sleep(1000);
        WebElement resultTable = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("transactionTable")));
        Assert.assertTrue(resultTable.getText().contains("12145"), "❌ Không tìm thấy Transaction ID!");
    }

    @Test
    public void testFindByDate() throws InterruptedException {
        WebElement accountSelect = driver.findElement(By.id("accountId"));
        accountSelect.sendKeys("13344");

        driver.findElement(By.cssSelector("input#transactionDate")).sendKeys("08-29-2025");
        driver.findElement(By.cssSelector("button[id='findByDate']")).click();

        WebElement resultTable = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("transactionTable")));
        Assert.assertTrue(resultTable.getText().contains("08-29-2025"), "❌ Không tìm thấy giao dịch ngày này!");
        Thread.sleep(2000);
    }

    @Test
    public void testFindByDateRange() {
        WebElement accountSelect = driver.findElement(By.id("accountId"));
        accountSelect.sendKeys("13344");

        driver.findElement(By.cssSelector("input#fromDate")).sendKeys("01-11-2025");
        driver.findElement(By.cssSelector("input#toDate")).sendKeys("12-11-2025");
        driver.findElement(By.cssSelector("button[id='findByDateRange']")).click();

        WebElement resultTable = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("transactionTable")));
        Assert.assertTrue(resultTable.getText().length() > 0, "❌ Không có giao dịch trong khoảng ngày!");
    }

    @Test
    public void testFindByAmount() {
        WebElement accountSelect = driver.findElement(By.id("accountId"));
        accountSelect.sendKeys("13344");

        driver.findElement(By.cssSelector("input#amount")).sendKeys("100");
        driver.findElement(By.cssSelector("button[id='findByAmount']")).click();

        WebElement resultTable = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("transactionTable")));
        Assert.assertTrue(resultTable.getText().contains("100"), "❌ Không tìm thấy giao dịch số tiền 100!");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
