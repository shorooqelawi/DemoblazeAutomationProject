package Tests;

import Pages.CartPage;
import Pages.CheckoutPage;
import Pages.ProductPage;
import Utilities.TestBase;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class CheckoutTest extends TestBase {
    private ProductPage productPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private SoftAssert softAssert;
    private String selectedProductName;
    private String selectedProductPrice;

    @BeforeMethod
    public void testSetup() {
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
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
    public void completeCheckoutProcess() throws InterruptedException {
        String initialUrl = driver.getCurrentUrl();

        productPage.SelectRandomProduct();

        String currentUrl = driver.getCurrentUrl();
        softAssert.assertNotEquals(currentUrl, initialUrl, "URL should change after product selection");

        Thread.sleep(2000);
        softAssert.assertTrue(productPage.isProductNameDisplayed(),
                "Product name should be displayed on product details page");
        Thread.sleep(2000);
        softAssert.assertTrue(productPage.isProductPriceDisplayed(),
                "Product price should be displayed");
        Thread.sleep(2000);

        selectedProductName = productPage.getProductName();
        selectedProductPrice = productPage.getProductPrice();

        softAssert.assertFalse(selectedProductName.isEmpty(), "Product name should not be empty");
        softAssert.assertFalse(selectedProductPrice.isEmpty(), "Product price should not be empty");

        System.out.println("Selected Product: " + selectedProductName);
        System.out.println("Product Price: " + selectedProductPrice);

        productPage.clickAddToCart();
        Thread.sleep(2000);
        System.out.println("Product added to cart successfully");

        productPage.navigateToCart();
        Thread.sleep(2000);

        String cartUrl = driver.getCurrentUrl();
        softAssert.assertTrue(cartUrl.contains("cart"),
                "Should be on cart page. Current URL: " + cartUrl);

        int cartItemsCount = cartPage.getCartItemsCount();
        softAssert.assertTrue(cartItemsCount > 0,
                "Cart should contain at least one item. Found: " + cartItemsCount);

        String totalPrice = cartPage.getTotalPrice();
        softAssert.assertNotEquals(totalPrice, "0",
                "Total price should not be zero. Found: " + totalPrice);

        System.out.println("Cart items count: " + cartItemsCount);
        System.out.println("Total price: " + totalPrice);

        cartPage.clickPlaceOrder();
        Thread.sleep(2000);
        System.out.println("Place order button clicked");

        softAssert.assertTrue(checkoutPage.isOrderModalDisplayed(),
                "Order modal should be displayed");

        checkoutPage.fillOrderForm("John Doe", "USA", "New York", "1234567890123456", "12", "2025");
        Thread.sleep(2000);
        System.out.println("Order form filled successfully");

        checkoutPage.clickPurchase();
        Thread.sleep(2000);
        System.out.println("Purchase button clicked");

        boolean isPurchaseSuccessful = checkoutPage.isPurchaseSuccessful();
        softAssert.assertTrue(isPurchaseSuccessful,
                "Purchase should be completed successfully");

        Thread.sleep(2000);

        String orderDetails = checkoutPage.getOrderDetails();
        softAssert.assertNotNull(orderDetails, "Order details should not be null");
        softAssert.assertFalse(orderDetails.isEmpty(), "Order details should not be empty");

        softAssert.assertTrue(orderDetails.contains("Amount"),
                "Order details should contain amount information");
        softAssert.assertTrue(orderDetails.contains("Card Number"),
                "Order details should contain card information");

        System.out.println("Purchase successful!");
        System.out.println("Order Details: " + orderDetails);

        checkoutPage.closeConfirmation();
        Thread.sleep(2002);
        System.out.println("Confirmation closed");

        String finalUrl = driver.getCurrentUrl();
        softAssert.assertTrue(finalUrl.contains("demoblaze"),
                "Should be on Demoblaze site after checkout. Current URL: " + finalUrl);

        System.out.println("Complete checkout process test passed successfully!");
    }

    @Test(dependsOnMethods = "completeCheckoutProcess")
    public void verifyOrderSummary() throws InterruptedException {
        System.out.println("Order Summary Verification:");
        System.out.println("Product: " + selectedProductName);
        System.out.println("Price: " + selectedProductPrice);

        softAssert.assertNotNull(selectedProductName, "Product name should be stored");
        softAssert.assertNotNull(selectedProductPrice, "Product price should be stored");

        Thread.sleep(1000);
    }
}