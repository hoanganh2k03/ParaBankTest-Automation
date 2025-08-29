package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountOverviewTest {
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

        // Login
        driver.findElement(By.name("username")).sendKeys("uechi");  // thay username của bạn
        driver.findElement(By.name("password")).sendKeys("demo");  // thay password của bạn
        driver.findElement(By.cssSelector("input[value='Log In']")).click();
    }

    @Test
    public void testAccountOverview() throws InterruptedException {
        // Bấm link "Accounts Overview"
        driver.findElement(By.linkText("Accounts Overview")).click();

        // Verify title trang
        WebElement title = driver.findElement(By.cssSelector("h1.title"));
        Assert.assertEquals(title.getText(), "Accounts Overview", "❌ Không vào được trang Accounts Overview!");

        // Lấy tất cả các account row trong bảng
        Thread.sleep(2000);
        List<WebElement> accounts = driver.findElements(By.cssSelector("table#accountTable tbody tr"));
        Assert.assertTrue(accounts.size() > 0, "❌ Không có tài khoản nào hiển thị trong Accounts Overview!");

        System.out.println("✅ Found " + accounts.size() + " accounts in overview page");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
