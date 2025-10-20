package tests.ui;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ConfigReader;

public class LoginTest extends BaseTest {

    @Test
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password")); // account mặc định của Parabank

        String title = loginPage.getWelcomeMessage();
        Assert.assertTrue(title.contains("Accounts Overview"), "❌ Login thất bại!");
        System.out.println("✅ Login thành công!");
    }

    @Test
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("wrongUser", "wrongPass");

        String error = loginPage.getErrorMessage();
        Assert.assertTrue(error.contains("The username and password could not be verified"),
                "❌ Không hiện thông báo lỗi!");
        System.out.println("✅ Thông báo lỗi login hiển thị đúng.");
    }
}
