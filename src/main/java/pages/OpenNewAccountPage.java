package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class OpenNewAccountPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By accountType = By.id("type");
    private By fromAccount = By.id("fromAccountId");
    private By openButton = By.cssSelector("input[type='button'][value='Open New Account']");
    private By titleSuccess = By.xpath("//h1[text()='Account Opened!']");
    private By newAccountId = By.id("newAccountId");

    public OpenNewAccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void open() {
        driver.get("https://parabank.parasoft.com/parabank/openaccount.htm");
    }

    public void selectAccountType(String type) {
     wait.until(ExpectedConditions.visibilityOfElementLocated(accountType));
        driver.findElement(accountType).sendKeys(type);
    }

    public String getSourceAccount() {
        return driver.findElement(fromAccount).getText();
    }
    public void clickOpenNewAccount() throws InterruptedException {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(openButton));   
        // Di chuyển chuột đến và click (tránh overlay)
        Actions actions = new Actions(driver);
        actions.moveToElement(button).click().perform();
    
        
    }

    public boolean isAccountOpened() {
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(titleSuccess));
        return title.isDisplayed();
    }

    public String getNewAccountId() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(newAccountId)).getText();
    }
}
