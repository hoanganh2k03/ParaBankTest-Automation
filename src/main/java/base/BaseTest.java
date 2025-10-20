package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.UnsupportedCommandException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.*;
import utils.ConfigReader;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    protected WebDriver driver;

    @Parameters({"browser", "device"})
    @BeforeMethod
    public void setup(@Optional("chrome") String browser,
                      @Optional("desktop") String device) {

        String baseUrl = ConfigReader.getProperty("url_ui");
        int timeout = Integer.parseInt(ConfigReader.getProperty("timeout"));

        switch (browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions fOptions = new FirefoxOptions();

                if (device.equalsIgnoreCase("mobile")) {
                    // Firefox không có mobile emulation chính thức, 
                    // ta giả lập bằng cách đổi user-agent + viewport
                    fOptions.addPreference("general.useragent.override",
                            "Mozilla/5.0 (iPhone; CPU iPhone OS 15_0 like Mac OS X) " +
                            "AppleWebKit/605.1.15 (KHTML, like Gecko) " +
                            "Version/15.0 Mobile/15E148 Safari/604.1");
                    fOptions.addArguments("--width=375");
                    fOptions.addArguments("--height=812");
                }

                driver = new FirefoxDriver(fOptions);
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions eOptions = new EdgeOptions();

                if (device.equalsIgnoreCase("mobile")) {
                    Map<String, Object> mobileEmulation = new HashMap<>();
                    mobileEmulation.put("deviceName", "iPhone 13 Pro");
                    eOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
                }

                driver = new EdgeDriver(eOptions);
                break;

            default: // Chrome
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();

                Map<String, Object> prefs = new HashMap<>();
                prefs.put("profile.password_manager_enabled", false);
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_leak_detection", false);
                options.setExperimentalOption("prefs", prefs);

                options.addArguments("--disable-features=PasswordCheck");
                options.addArguments("--disable-save-password-bubble");
                options.addArguments("--disable-popup-blocking");

                if (device.equalsIgnoreCase("mobile")) {
                    Map<String, Object> mobileEmulation = new HashMap<>();
                    mobileEmulation.put("deviceName", "iPhone 13 Pro");
                    options.setExperimentalOption("mobileEmulation", mobileEmulation);
                }

                driver = new ChromeDriver(options);
        }

        try {
            driver.manage().window().maximize();
        } catch (UnsupportedCommandException e) {
            // Bỏ qua nếu browser không hỗ trợ maximize (mobile mode)
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
        driver.get(baseUrl);

        System.out.println("🚀 Running on Browser: " + browser + " | Device: " + device);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
