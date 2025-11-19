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
import java.util.Random;

public class ProductPage extends TestBase {

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "a.hrefch")
    private List<WebElement> productLinks;

    @FindBy(css = "div.product-content h2.name")
    private WebElement productName;

    @FindBy(css = "div.product-content h3.price-container")
    private WebElement productPrice;

    @FindBy(css = "a[onclick*='addToCart']")
    private WebElement addToCartLink;

    @FindBy(id = "nava")
    private WebElement homeLink;

    @FindBy(id = "cartur")
    private WebElement cartLink;

    public void SelectRandomProduct() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(driver -> !productLinks.isEmpty());

            if (productLinks.isEmpty()) {
                throw new RuntimeException("No products found on the page");
            }

            Random random = new Random();
            int randomIndex = random.nextInt(productLinks.size());
            productLinks.get(randomIndex).click();

        } catch (Exception e) {
            throw new RuntimeException("Failed to select random product: " + e.getMessage(), e);
        }
    }

    public void clickAddToCart() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.elementToBeClickable(addToCartLink));
            addToCartLink.click();

            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();

        } catch (Exception e) {
            throw new RuntimeException("Failed to add product to cart: " + e.getMessage(), e);
        }
    }

    public void navigateToCart() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(cartLink)).click();
        } catch (Exception e) {
            throw new RuntimeException("Failed to navigate to cart: " + e.getMessage(), e);
        }
    }

    public void navigateToHome() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(homeLink)).click();
        } catch (Exception e) {
            throw new RuntimeException("Failed to navigate to home: " + e.getMessage(), e);
        }
    }

    public boolean isProductNameDisplayed() {
        try {
            return productName.isDisplayed() && !productName.getText().trim().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isProductPriceDisplayed() {
        try {
            return productPrice.isDisplayed() && !productPrice.getText().trim().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isAddToCartButtonDisplayed() {
        try {
            return addToCartLink.isDisplayed() && addToCartLink.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public String getProductName() {
        return productName.getText().trim();
    }

    public String getProductPrice() {
        return productPrice.getText().trim();
    }

    public void waitForProductsToLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(driver -> productLinks.size() > 0);
    }
}