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

public class LoginTest {
    WebDriver driver;

    @BeforeMethod
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
    }
    
    @Test
    public void testLogin() {
        driver.findElement(By.name("username")).sendKeys("uechi"); // thay user id thật
        driver.findElement(By.name("password")).sendKeys("demo"); // thay password thật
        driver.findElement(By.cssSelector("input[value='Log In']")).click();

        // Verify có chữ "Accounts Overview"
        WebElement title = driver.findElement(By.xpath("//h1[contains(text(),'Accounts Overview')]"));
        Assert.assertTrue(title.isDisplayed(), "❌ Login thất bại!");
    }
    @Test
    public void testLoginFail() throws InterruptedException {
        driver.findElement(By.name("username")).sendKeys("saiuser");
        driver.findElement(By.name("password")).sendKeys("saipass");
        driver.findElement(By.cssSelector("input[value='Log In']")).click();

        // Verify error message
        WebElement error = driver.findElement(By.cssSelector("p.error"));
        Assert.assertTrue(error.getText().contains("An internal error has occurred and has been logged"),
                "❌ Không hiện thông báo lỗi khi login sai!");
        Thread.sleep(1000);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
