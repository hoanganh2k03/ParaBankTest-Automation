package tests.ui;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.RegisterPage;

public class RegisterTest extends BaseTest {
    RegisterPage registerPage;

    @BeforeMethod
    public void setup() {
        registerPage = new RegisterPage(driver);
        registerPage.open();
    }

    @Test
    public void testSuccessfulRegistration() throws InterruptedException {
    

        registerPage.fillForm(
                "John",
                "Doe",
                "123 Main St",
                "Los Angeles",
                "CA",
                "90001",
                "1234567890",
                "123-45-6789",
                "uechi",
                "demo"
        );

        registerPage.clickRegister();

        Assert.assertTrue(registerPage.isRegistered(), "❌ Registration failed!");
        System.out.println("✅ Registered successfully with username: " + "uechi");
    }
}
