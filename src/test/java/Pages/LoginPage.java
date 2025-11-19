package Pages;

import Utilities.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends TestBase {

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "login2")
    private WebElement loginLink;

    @FindBy(id = "loginusername")
    private WebElement usernameField;

    @FindBy(id = "loginpassword")
    private WebElement passwordField;

    @FindBy(xpath = "//button[text()='Log in']")
    private WebElement loginButton;

    @FindBy(xpath = "//div[@id='logInModal']//button[text()='Close']")
    private WebElement closeButton;

    @FindBy(id = "nameofuser")
    private WebElement welcomeMessage;

    public void clickLoginLink() {
        click(loginLink);
    }

    public void enterUsername(String username) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(usernameField));
        sendKey(usernameField, username);
    }

    public void enterPassword(String password) {
        sendKey(passwordField, password);
    }

    public void clickLogin() {
        click(loginButton);
    }

    public void closeLoginModal() {
        click(closeButton);
    }

    public boolean isLoginModalDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            return wait.until(ExpectedConditions.visibilityOf(usernameField)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getAlertText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.alertIsPresent()).getText();
    }

    public void acceptAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent()).accept();
    }

    public boolean isUserLoggedIn(String username) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOf(welcomeMessage)).getText().contains(username);
        } catch (Exception e) {
            return false;
        }
    }
}
