package Pages;

import Utilities.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignUpPage extends TestBase {

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "signin2")
    private WebElement signUpLink;

    @FindBy(id = "sign-username")
    private WebElement usernameField;

    @FindBy(id = "sign-password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[text()='Sign up']")
    private WebElement signUpButton;

    @FindBy(xpath = "//div[@id='signInModal']//button[text()='Close']")
    private WebElement closeButton;

    public void clickSignUpLink() {
        click(signUpLink);
    }

    public void enterUsername(String username) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(usernameField)).sendKeys(username);
    }

    public void enterPassword(String password) {
        sendKey(passwordField,password);
    }

    public void clickSignUp() {
        click(signUpButton);
    }

    public void closeSignUpModal() {
        click(closeButton);
    }

    public boolean isSignUpModalDisplayed() {
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
}
