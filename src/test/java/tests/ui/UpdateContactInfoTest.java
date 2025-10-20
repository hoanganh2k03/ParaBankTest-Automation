package tests.ui;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.UpdateContactInfoPage;
import utils.ConfigReader;

public class UpdateContactInfoTest extends BaseTest {
    LoginPage loginPage;
    UpdateContactInfoPage updatePage;

    @BeforeMethod
    public void setup() {
        loginPage = new LoginPage(driver);
        updatePage = new UpdateContactInfoPage(driver);

        // Đăng nhập
        loginPage.login(
            ConfigReader.getProperty("username"),
            ConfigReader.getProperty("password")
        );

        // Mở trang Update Contact Info
        updatePage.open();
    }

    @Test
    public void testUpdateContactInfo() {
        String newAddress = "abc";

        // Cập nhật thông tin
        updatePage.updateStreet(newAddress);

        // Xác minh kết quả
        String msg = updatePage.getSuccessMessage();
        Assert.assertTrue(msg.contains("updated"), "❌ Không thấy thông báo cập nhật!");

        System.out.println("✅ Update Contact Info thành công: " + msg);
    }
}
