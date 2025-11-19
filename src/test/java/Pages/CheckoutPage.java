package Pages;

import Utilities.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage extends TestBase {

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "name")
    private WebElement nameInput;

    @FindBy(id = "country")
    private WebElement countryInput;

    @FindBy(id = "city")
    private WebElement cityInput;

    @FindBy(id = "card")
    private WebElement cardInput;

    @FindBy(id = "month")
    private WebElement monthInput;

    @FindBy(id = "year")
    private WebElement yearInput;

    @FindBy(xpath = "//button[contains(text(),'Purchase')]")
    private WebElement purchaseButton;

    @FindBy(xpath = "//div[contains(@class,'sweet-alert')]//h2")
    private WebElement purchaseConfirmation;

    @FindBy(xpath = "//p[@class='lead text-muted ']")
    private WebElement orderDetails;

    @FindBy(xpath = "//button[contains(text(),'OK')]")
    private WebElement okButton;

    @FindBy(xpath = "//h5[@id='orderModalLabel']")
    private WebElement orderModalTitle;

    public void enterName(String name) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(nameInput)).sendKeys(name);
        } catch (Exception e) {
            throw new RuntimeException("Failed to enter name: " + e.getMessage(), e);
        }
    }

    public void enterCountry(String country) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(countryInput)).sendKeys(country);
        } catch (Exception e) {
            throw new RuntimeException("Failed to enter country: " + e.getMessage(), e);
        }
    }

    public void enterCity(String city) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(cityInput)).sendKeys(city);
        } catch (Exception e) {
            throw new RuntimeException("Failed to enter city: " + e.getMessage(), e);
        }
    }

    public void enterCard(String card) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(cardInput)).sendKeys(card);
        } catch (Exception e) {
            throw new RuntimeException("Failed to enter card: " + e.getMessage(), e);
        }
    }

    public void enterMonth(String month) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(monthInput)).sendKeys(month);
        } catch (Exception e) {
            throw new RuntimeException("Failed to enter month: " + e.getMessage(), e);
        }
    }

    public void enterYear(String year) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(yearInput)).sendKeys(year);
        } catch (Exception e) {
            throw new RuntimeException("Failed to enter year: " + e.getMessage(), e);
        }
    }

    public void clickPurchase() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(purchaseButton)).click();
        } catch (Exception e) {
            throw new RuntimeException("Failed to click purchase: " + e.getMessage(), e);
        }
    }

    public void fillOrderForm(String name, String country, String city, String card, String month, String year) {
        enterName(name);
        enterCountry(country);
        enterCity(city);
        enterCard(card);
        enterMonth(month);
        enterYear(year);
    }

    public boolean isPurchaseSuccessful() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOf(purchaseConfirmation))
                    .getText().toLowerCase().contains("thank you");
        } catch (Exception e) {
            return false;
        }
    }

    public String getOrderDetails() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOf(orderDetails)).getText();
        } catch (Exception e) {
            return "Order details not found";
        }
    }

    public void closeConfirmation() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(okButton)).click();
        } catch (Exception e) {
            throw new RuntimeException("Failed to close confirmation: " + e.getMessage(), e);
        }
    }

    public boolean isOrderModalDisplayed() {
        try {
            return orderModalTitle.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}