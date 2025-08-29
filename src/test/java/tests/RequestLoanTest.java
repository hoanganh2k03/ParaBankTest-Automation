package tests;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

public class RequestLoanTest {
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
    public void openRequestLoan() {
        // Vào menu Request Loan
        driver.findElement(By.linkText("Request Loan")).click();
    }

    @Test
    public void testRequestLoanSuccess() throws InterruptedException {
        // Nhập số tiền vay
        driver.findElement(By.id("amount")).clear();
        driver.findElement(By.id("amount")).sendKeys("100");

        // Nhập down payment
        driver.findElement(By.id("downPayment")).clear();
        driver.findElement(By.id("downPayment")).sendKeys("50");

        // Chọn tài khoản nguồn (from account)
        WebElement fromAcc = driver.findElement(By.cssSelector("select#fromAccountId"));
        Select select = new Select(fromAcc);
        String sourceAcc = select.getOptions().get(3).getText();
        System.out.println("Using source account: " + sourceAcc);

        // Bấm Apply Now
        driver.findElement(By.cssSelector("input[value='Apply Now']")).click();
Thread.sleep(5000);
        // Verify có tiêu đề "Loan Request Processed"
        WebElement title = driver.findElement(By.tagName("h1"));
        Assert.assertEquals(title.getText(), "Loan Request Processed", "❌ Không thấy Loan Request Processed!");

        // Lấy Loan ID và Status
        WebElement loanId = driver.findElement(By.id("loanRequestId"));
        WebElement status = driver.findElement(By.id("loanStatus"));

        Assert.assertTrue(loanId.isDisplayed(), "❌ Loan ID chưa hiển thị!");
        Assert.assertTrue(status.isDisplayed(), "❌ Loan Status chưa hiển thị!");

        System.out.println("✅ Loan Request thành công - ID: " + loanId.getText() + " | Status: " + status.getText());
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
