package Pages;

import Utilities.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends TestBase {

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "Home")
    private WebElement homeLink;

    @FindBy(linkText = "Contact")
    private WebElement contactLink;

    @FindBy(linkText = "About us")
    private WebElement aboutUsLink;

    @FindBy(id = "cartur")
    private WebElement cartLink;

    @FindBy(id = "login2")
    private WebElement loginLink;

    @FindBy(id = "signin2")
    private WebElement signUpLink;

    @FindBy(id = "nameofuser")
    private WebElement userNameLabel;

    public void click(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            throw new RuntimeException("Failed to click element: " + e.getMessage(), e);
        }
    }

    public void navigateToHome() {
        click(homeLink);
    }

    public void navigateToCart() {
        click(cartLink);
    }

    public void load() {
        navigateToHome();
    }

    public boolean isUserLoggedIn() {
        try {
            return userNameLabel.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getLoggedInUserName() {
        try {
            return userNameLabel.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public void clickContactLink() {
        click(contactLink);
    }

    public void clickAboutUsLink() {
        click(aboutUsLink);
    }

    public void clickLoginLink() {
        click(loginLink);
    }

    public void clickSignUpLink() {
        click(signUpLink);
    }

}