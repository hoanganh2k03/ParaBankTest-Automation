package tests.ui;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.TransferFundsPage;
import utils.ConfigReader;

public class TransferFundsTest extends BaseTest {
    LoginPage loginPage;
    TransferFundsPage transferPage;

    @BeforeMethod
    public void setup() {
        // 🔹 Login trước mỗi test
        loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

        // 🔹 Mở trang Transfer Funds
        transferPage = new TransferFundsPage(driver);
        transferPage.open();
    }

    @Test
    public void testTransferFunds() {
        transferPage.enterAmount("100");
        transferPage.selectFromAccount("12345");
        transferPage.selectToAccount("12456");
        transferPage.clickTransfer();

        // ✅ Verify kết quả
        Assert.assertTrue(transferPage.isTransferComplete(), "❌ Transfer chưa thành công!");
        Assert.assertEquals(transferPage.getAmountResult(), "$100.00", "❌ Số tiền sai!");
        Assert.assertEquals(transferPage.getFromAccountResult(), "14232", "❌ From Account sai!");
        Assert.assertEquals(transferPage.getToAccountResult(), "13455", "❌ To Account sai!");

        System.out.println("✅ Transfer thành công!");
    }
}
