package tests.ui;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.AccountOverviewPage;
import utils.ConfigReader;
public class AccountOverviewTest extends BaseTest {
    LoginPage loginPage;
    AccountOverviewPage overviewPage;

    @BeforeMethod
    public void setup() {
        // 🔹 Login trước mỗi test
        loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
        overviewPage = new AccountOverviewPage(driver);
        overviewPage.open();
    }

    @Test
    public void testAccountOverview() {
        // ✅ Verify đã vào đúng trang
        Assert.assertTrue(overviewPage.isAtOverviewPage(), "❌ Không vào được trang Accounts Overview!");

        // ✅ Verify có ít nhất 1 account
        int count = overviewPage.getAccountCount();
        Assert.assertTrue(count > 0, "❌ Không có tài khoản nào trong Accounts Overview!");

        System.out.println("✅ Found " + count + " accounts in overview page");
    }
}
