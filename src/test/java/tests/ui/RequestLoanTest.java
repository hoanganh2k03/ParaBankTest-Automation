package tests.ui;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.RequestLoanPage;
import utils.ConfigReader;

public class RequestLoanTest extends BaseTest {
    LoginPage loginPage;
    RequestLoanPage loanPage;

    @BeforeMethod
    public void setup() {
        loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
        loanPage = new RequestLoanPage(driver);
        loanPage.open();
    }

    @Test
    public void testRequestLoanSuccess() {
        loanPage.requestLoan("100", "50");
        Assert.assertEquals(loanPage.getTitle(), "Loan Request Processed", "❌ Không thấy Loan Request Processed!");
        Assert.assertFalse(loanPage.getLoanId().isEmpty(), "❌ Loan ID chưa hiển thị!");
        Assert.assertFalse(loanPage.getLoanStatus().isEmpty(), "❌ Loan Status chưa hiển thị!");
        System.out.println("✅ Loan Request thành công - ID: " + loanPage.getLoanId() + " | Status: " + loanPage.getLoanStatus());
    }
}
