package tests.ui;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.AccountOverviewPage;
import pages.AccountDetailPage;
import utils.ConfigReader;

public class AccountDetailTest extends BaseTest {
    LoginPage loginPage;
    AccountOverviewPage overviewPage;
    AccountDetailPage detailPage;

    @BeforeMethod
    public void setup() {
        // 🔹 Login vào app
        loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
        overviewPage = new AccountOverviewPage(driver);
        overviewPage.open();
    }

    @Test
    public void testAccountDetail() throws InterruptedException {
        detailPage = new AccountDetailPage(driver);
        String accountNumber = overviewPage.clickFirstAccount();

        // ✅ Verify vào đúng trang Account Details
        Assert.assertTrue(detailPage.isAtDetailPage(), "❌ Không vào được trang Account Details!");

        // ✅ Verify thông tin account
        Assert.assertEquals(detailPage.getAccountId(), accountNumber, "❌ Account number không khớp!");
        Assert.assertFalse(detailPage.getAccountType().isEmpty(), "❌ Account Type không hiển thị!");
        Assert.assertFalse(detailPage.getBalance().isEmpty(), "❌ Balance không hiển thị!");

        System.out.println("✅ Account Detail loaded: " +
                accountNumber + " | Type: " + detailPage.getAccountType() + " | Balance: " + detailPage.getBalance());
    }
}
