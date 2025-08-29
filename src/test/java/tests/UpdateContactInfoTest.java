package tests;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

public class UpdateContactInfoTest {
    WebDriver driver;

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
        driver.get("https://parabank.parasoft.com/parabank/index.htm");

        // Login trước
        driver.findElement(By.name("username")).sendKeys("uechi");
        driver.findElement(By.name("password")).sendKeys("demo");
        driver.findElement(By.cssSelector("input[value='Log In']")).click();
    }

    @BeforeMethod
    public void openUpdateInfo() {
        // Luôn mở trang Update Contact Info trước mỗi test
        driver.findElement(By.linkText("Update Contact Info")).click();
    }

    @Test
    public void testUpdateContactInfo() throws InterruptedException {
       
        driver.findElement(By.id("customer.address.street")).clear();
        driver.findElement(By.id("customer.address.street")).sendKeys("1431 Main Street");
        // Bấm Update
        driver.findElement(By.cssSelector("input[value='Update Profile']")).click();
Thread.sleep(15000);
        // Verify cập nhật thành công
        WebElement msg = driver.findElement(By.cssSelector("#rightPanel p"));
        Assert.assertTrue(msg.getText().contains("updated"), "❌ Không thấy thông báo cập nhật!");

        System.out.println("✅ Update Contact Info thành công: " + msg.getText());
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
