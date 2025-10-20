package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UpdateContactInfoPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By streetInput = By.id("customer.address.street");
    private By updateButton = By.cssSelector("input[value='Update Profile']");
    private By successMessage = By.cssSelector("#rightPanel p");
    private By firstNameInput = By.id("customer.firstName");
    public UpdateContactInfoPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void open() {
        driver.findElement(By.linkText("Update Contact Info")).click();
        waitForInputValue(firstNameInput);
    }

    public void updateStreet(String newStreet) {
        driver.findElement(streetInput).clear();
        driver.findElement(streetInput).sendKeys(newStreet);
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(updateButton)); 
        Actions actions = new Actions(driver);
        actions.moveToElement(button).click().perform();
    }

    public String getSuccessMessage() {
    	WebElement successmsg= wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        return successmsg.getText();
    }
    private void waitForInputValue(By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        wait.until(driver -> !element.getAttribute("value").trim().isEmpty());
    }
}
