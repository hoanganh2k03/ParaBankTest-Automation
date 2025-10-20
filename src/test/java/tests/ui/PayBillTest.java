package tests.ui;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.PayBillPage;
import utils.ConfigReader;

public class PayBillTest extends BaseTest {
    LoginPage loginPage;
    PayBillPage payBillPage;
    String accountId;

    @BeforeMethod
    public void setup() {
        loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

        payBillPage = new PayBillPage(driver);
        payBillPage.open();

        accountId = ConfigReader.getProperty("accountId");
    }

    @Test
    public void testPayBill() {
        payBillPage.fillPayeeInfo(
                "Electric Company",
                "123 Main St",
                "New York",
                "NY",
                "10001",
                "1234567890"
        );

        payBillPage.enterAccountInfo("555666", "555666");
        payBillPage.enterAmount("200");
        payBillPage.selectFromAccount(accountId);
        payBillPage.submitPayment();

        String confirmMsg = payBillPage.getConfirmMessage();
        Assert.assertEquals(confirmMsg, "Bill Payment Complete", "❌ Thanh toán thất bại!");

        String accConfirm = payBillPage.getConfirmedAccount();
        Assert.assertTrue(accConfirm.contains(accountId), "❌ Không đúng account nguồn!");

        System.out.println("✅ Bill Pay thành công từ account " + accountId);
    }
}
