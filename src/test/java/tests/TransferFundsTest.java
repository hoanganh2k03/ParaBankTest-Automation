package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

public class TransferFundsTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://parabank.parasoft.com/parabank/index.htm");

        // Login trước
        driver.findElement(By.name("username")).sendKeys("uechi");  // đổi user của bạn
        driver.findElement(By.name("password")).sendKeys("demo");
        driver.findElement(By.cssSelector("input[value='Log In']")).click();
    }

    @Test
    public void testTransferFunds() throws InterruptedException {
        // Vào Transfer Funds
        driver.findElement(By.linkText("Transfer Funds")).click();
        Thread.sleep(1000);
        // Nhập số tiền
        driver.findElement(By.id("amount")).sendKeys("100");

        // Chọn From Account
        Select fromAcc = new Select(driver.findElement(By.id("fromAccountId")));
        fromAcc.selectByVisibleText("14232");

        // Chọn To Account
        Select toAcc = new Select(driver.findElement(By.id("toAccountId")));
        toAcc.selectByVisibleText("13455");
        // Click Transfer
        driver.findElement(By.cssSelector("input[value='Transfer']")).click();

        // Chờ chút cho load
        

        // Verify ra trang xác nhận
        WebElement title = driver.findElement(By.cssSelector("#showResult h1.title"));
        Thread.sleep(2000);
        Assert.assertEquals(title.getText(), "Transfer Complete!", "❌ Chưa chuyển thành công!");

        // Verify thông tin confirm
        WebElement amount = driver.findElement(By.id("amountResult"));
        WebElement fromAcc1 = driver.findElement(By.id("fromAccountIdResult"));
        WebElement toAcc1   = driver.findElement(By.id("toAccountIdResult"));
        Assert.assertEquals(amount.getText(), "$100.00", "❌ Số tiền không đúng!");
        Assert.assertEquals(fromAcc1.getText(), "14232", "❌ From Account sai!");
        Assert.assertEquals(toAcc1.getText(), "13455", "❌ To Account sai!");
        System.out.println("✅ Transfer thành công: ");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
