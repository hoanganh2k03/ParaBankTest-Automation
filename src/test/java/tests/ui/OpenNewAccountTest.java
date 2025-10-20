package tests.ui;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.OpenNewAccountPage;
import utils.ConfigReader;
public class OpenNewAccountTest extends BaseTest {
    LoginPage loginPage;
    OpenNewAccountPage openPage;

    @BeforeMethod
    public void loginAndOpenPage() {
        // Login
        loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
        // Mở trang Open Account
        openPage = new OpenNewAccountPage(driver);
        openPage.open();
    }

    @Test(priority = 1)
    public void testOpenSavingsAccount() throws InterruptedException {
        openPage.selectAccountType("SAVINGS");
        String source = openPage.getSourceAccount();
        System.out.println("Source Account: " + source);
        openPage.clickOpenNewAccount();
        Assert.assertTrue(openPage.isAccountOpened(), "❌ Không mở được account mới!");
        System.out.println("✅ New Savings Account Created: " + openPage.getNewAccountId());
    }

    @Test(priority = 2)
    public void testOpenCheckingAccount() throws InterruptedException {
        openPage.selectAccountType("CHECKING");
        String source = openPage.getSourceAccount();
        System.out.println("Source Account: " + source);
        openPage.clickOpenNewAccount();
        Assert.assertTrue(openPage.isAccountOpened(), "❌ Không mở được account mới!");
        System.out.println("✅ New Checking Account Created: " + openPage.getNewAccountId());
    }
}
