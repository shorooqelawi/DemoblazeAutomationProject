package Pages;

import Utilities.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage extends TestBase {

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//tr[@class='success']")
    private List<WebElement> cartItems;

    @FindBy(xpath = "//button[contains(text(),'Place Order')]")
    private WebElement placeOrderButton;

    @FindBy(id = "totalp")
    private WebElement totalPrice;

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

    @FindBy(xpath = "//button[contains(text(),'Delete')]")
    private List<WebElement> deleteButtons;

    public void clickPlaceOrder() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton)).click();
        } catch (Exception e) {
            throw new RuntimeException("Failed to click place order: " + e.getMessage(), e);
        }
    }

    public void fillOrderForm(String name, String country, String city, String card, String month, String year) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            wait.until(ExpectedConditions.visibilityOf(nameInput)).sendKeys(name);
            countryInput.sendKeys(country);
            cityInput.sendKeys(city);
            cardInput.sendKeys(card);
            monthInput.sendKeys(month);
            yearInput.sendKeys(year);

        } catch (Exception e) {
            throw new RuntimeException("Failed to fill order form: " + e.getMessage(), e);
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

    public int getCartItemsCount() {
        return cartItems.size();
    }

    public String getTotalPrice() {
        try {
            return totalPrice.getText();
        } catch (Exception e) {
            return "0";
        }
    }

    public void deleteItemFromCart(int index) {
        try {
            if (index < deleteButtons.size()) {
                deleteButtons.get(index).click();
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete item from cart: " + e.getMessage(), e);
        }
    }

    public boolean isProductInCart(String productName) {
        try {
            for (WebElement item : cartItems) {
                if (item.getText().contains(productName)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}