package tests;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class OpenNewAccountTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
    	ChromeOptions options = new ChromeOptions();

        // Tạo map prefs
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_leak_detection", false);

        // Gán prefs vào options
        options.setExperimentalOption("prefs", prefs);

        // Thêm một số flags để tắt popup mật khẩu
        options.addArguments("--disable-features=PasswordCheck");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-popup-blocking");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://parabank.parasoft.com/parabank/index.htm");

        // Login trước
        driver.findElement(By.name("username")).sendKeys("uechi");  // thay bằng tk của bạn
        driver.findElement(By.name("password")).sendKeys("demo");  // thay bằng mk của bạn
        driver.findElement(By.cssSelector("input[value='Log In']")).click();
    }

    @BeforeMethod
    public void openAccountPage() {
        driver.get("https://parabank.parasoft.com/parabank/openaccount.htm");
    }

    @Test(priority = 1)
    public void testOpenSavingsAccount() throws InterruptedException {
        // Chọn loại account = Savings
        WebElement accountType = driver.findElement(By.id("type"));
        accountType.sendKeys("SAVINGS");

        // Chọn tài khoản nguồn
        WebElement fromAccount = driver.findElement(By.id("fromAccountId"));
        String sourceAccount = fromAccount.getText();
        System.out.println("Source Account: " + sourceAccount);
        // Bấm nút Open New Account
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("input[type='button'][value='Open New Account']")).click();
        

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement title = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Account Opened!']"))
        );
        // Verify ra trang "Account Opened!"
        System.out.println(title.getText());
        
        WebElement newAcc = driver.findElement(By.id("newAccountId"));
        Assert.assertTrue(newAcc.isDisplayed(), "❌ Account number chưa hiển thị!");

        System.out.println("✅ New Savings Account Created: " + newAcc.getText());
        Thread.sleep(2000);
    }

    @Test(priority = 2)
    public void testOpenCheckingAccount() throws InterruptedException {
        // Chọn loại account = Checking
        WebElement accountType = driver.findElement(By.id("type"));
        accountType.sendKeys("CHECKING");

        // Chọn tài khoản nguồn
        WebElement fromAccount = driver.findElement(By.id("fromAccountId"));
        String sourceAccount = fromAccount.getText();
        System.out.println("Source Account: " + sourceAccount);

        // Bấm nút Open New Account
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("input[type='button'][value='Open New Account']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement title = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Account Opened!']"))
            );
        // Verify ra trang "Account Opened!"
System.out.println(title.getText());
        
        WebElement newAcc = driver.findElement(By.id("newAccountId"));
        Assert.assertTrue(newAcc.isDisplayed(), "❌ Account number chưa hiển thị!");

        System.out.println("✅ New Checking Account Created: " + newAcc.getText());
        Thread.sleep(2000);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
