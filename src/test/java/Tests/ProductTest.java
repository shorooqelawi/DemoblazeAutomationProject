package Tests;

import Pages.ProductPage;
import Utilities.TestBase;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class ProductTest extends TestBase {
    private ProductPage productPage;
    private SoftAssert softAssert;

    @BeforeMethod
    public void testSetup() {
        productPage = new ProductPage(driver);
        softAssert = new SoftAssert();
    }

    @AfterMethod(alwaysRun = true)
    public void assertAll() {
        if (softAssert != null) {
            softAssert.assertAll();
            softAssert = null;
        }
    }

    @Test
    public void SelectRandomProduct() throws InterruptedException {
        String initialUrl = driver.getCurrentUrl();
        String initialTitle = driver.getTitle();

        productPage.SelectRandomProduct();

        String currentUrl = driver.getCurrentUrl();
        String currentTitle = driver.getTitle();

        Assert.assertNotEquals(currentUrl, initialUrl, "URL should change after product selection");

        Thread.sleep(2000);
        Assert.assertTrue(productPage.isProductPriceDisplayed(),
                "Product price should be visible");
        Thread.sleep(2000);
        Assert.assertTrue(productPage.isAddToCartButtonDisplayed(),
                "Add to cart button should be visible and enabled");
        Thread.sleep(2000);
        String productName = productPage.getProductName();
        String productPrice = productPage.getProductPrice();
        Thread.sleep(2000);
        Assert.assertFalse(productName.isEmpty(), "Product name should not be empty");
        Assert.assertFalse(productPrice.isEmpty(), "Product price should not be empty");
        Assert.assertTrue(productPrice.contains("$") || productPrice.matches(".*\\d+.*"),
                "Product price should contain currency or numbers");
        Thread.sleep(2000);
        System.out.println("Selected product: " + productName);
        System.out.println("Product price: " + productPrice);
        System.out.println("Product selection test completed successfully!");
    }
}