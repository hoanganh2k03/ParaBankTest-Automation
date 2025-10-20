package tests.ui;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.FindTransactionsPage;
import pages.LoginPage;
import utils.ConfigReader;

public class FindTransactionsTest extends BaseTest {
    LoginPage loginPage;
    FindTransactionsPage findPage;

    String accountId;
    String transactionId;

    @BeforeMethod
    public void setup() {
        loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
        findPage = new FindTransactionsPage(driver);
        findPage.open();

        // ✅ Lấy từ file config
        accountId = ConfigReader.getProperty("accountId");
        transactionId = ConfigReader.getProperty("transactionId");
    }

    @Test
    public void testFindByTransactionId() {
        findPage.selectAccount(accountId);
        findPage.findByTransactionId(transactionId);
        String result = findPage.getResultText();
        Assert.assertTrue(result.contains(transactionId), "❌ Không tìm thấy Transaction ID!");
        System.out.println("✅ Tìm thấy Transaction ID: " + transactionId);
    }

    @Test
    public void testFindByDate() {
        findPage.selectAccount(accountId);
        findPage.findByDate("10-15-2025");
        String result = findPage.getResultText();
        Assert.assertTrue(result.contains("10-15-2025"), "❌ Không tìm thấy giao dịch ngày này!");
        System.out.println("✅ Tìm thấy giao dịch theo ngày!");
    }

    @Test
    public void testFindByDateRange() {
        findPage.selectAccount(accountId);
        findPage.findByDateRange("01-11-2025", "12-11-2025");
        String result = findPage.getResultText();
        Assert.assertTrue(result.length() > 0, "❌ Không có giao dịch trong khoảng ngày!");
        System.out.println("✅ Tìm thấy giao dịch trong khoảng ngày!");
    }

    @Test
    public void testFindByAmount() {
        findPage.selectAccount(accountId);
        findPage.findByAmount("100");
        String result = findPage.getResultText();
        Assert.assertTrue(result.contains("100"), "❌ Không tìm thấy giao dịch số tiền 100!");
        System.out.println("✅ Tìm thấy giao dịch số tiền 100!");
    }
}
