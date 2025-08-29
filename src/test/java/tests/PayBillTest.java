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

public class PayBillTest {
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

        // Login
        driver.findElement(By.name("username")).sendKeys("uechi"); // thay tk của bạn
        driver.findElement(By.name("password")).sendKeys("demo"); // thay mk của bạn
        driver.findElement(By.cssSelector("input[value='Log In']")).click();
    }

    @BeforeMethod
    public void openBillPay() {
        driver.findElement(By.linkText("Bill Pay")).click();
    }

    @Test
    public void testPayBillFromAccount18228() throws InterruptedException {
        // Nhập thông tin người nhận bill
        driver.findElement(By.name("payee.name")).sendKeys("Electric Company");
        driver.findElement(By.name("payee.address.street")).sendKeys("123 Main St");
        driver.findElement(By.name("payee.address.city")).sendKeys("New York");
        driver.findElement(By.name("payee.address.state")).sendKeys("NY");
        driver.findElement(By.name("payee.address.zipCode")).sendKeys("10001");
        driver.findElement(By.name("payee.phoneNumber")).sendKeys("1234567890");

        // Nhập tài khoản người nhận (giả định bất kỳ số nào, vd: 555666)
        driver.findElement(By.name("payee.accountNumber")).sendKeys("555666");
        driver.findElement(By.name("verifyAccount")).sendKeys("555666");

        // Nhập số tiền
        driver.findElement(By.name("amount")).sendKeys("200");

        // Chọn account nguồn = 18228
        WebElement fromAcc = driver.findElement(By.name("fromAccountId"));
        fromAcc.sendKeys("54321");
        
        // Submit
        driver.findElement(By.cssSelector("input[value='Send Payment']")).click();
        Thread.sleep(1000);
        // Verify confirm message
        WebElement confirmMsg = driver.findElement(By.cssSelector("#billpayResult h1"));
        Assert.assertEquals(confirmMsg.getText(), "Bill Payment Complete", "❌ Thanh toán thất bại!");

        // Verify số tài khoản nguồn hiển thị trong message
        WebElement accConfirm = driver.findElement(By.cssSelector("#rightPanel p #fromAccountId"));
        Assert.assertTrue(accConfirm.getText().contains("54321"), "❌ Không đúng account nguồn!");

        System.out.println("✅ Bill Pay thành công từ account 18228");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
