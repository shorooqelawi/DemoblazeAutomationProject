package Tests;

import Pages.CartPage;
import Pages.ProductPage;
import Utilities.TestBase;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class CartTest extends TestBase {
    private ProductPage productPage;
    private CartPage cartPage;
    private SoftAssert softAssert;
    private String addedProductName;

    @BeforeMethod
    public void testSetup() {
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        softAssert = new SoftAssert();

        // الانتقال للصفحة الرئيسية قبل كل@Test
        driver.get("https://www.demoblaze.com/");
    }

    @AfterMethod(alwaysRun = true)
    public void assertAll() {
        if (softAssert != null) {
            softAssert.assertAll();
            softAssert = null;
        }
    }

    @Test(priority = 1)
    public void testAddProductToCart() throws InterruptedException {
        System.out.println("=== Starting testAddProductToCart ===");

        productPage.SelectRandomProduct();
        Thread.sleep(2000);
        addedProductName = productPage.getProductName();
        Thread.sleep(2000);
        softAssert.assertFalse(addedProductName.isEmpty(), "Product name should not be empty");

        Thread.sleep(2000);
        productPage.clickAddToCart();
        Thread.sleep(2000);

        productPage.navigateToCart();
        Thread.sleep(2000);

        int cartItemsCount = cartPage.getCartItemsCount();
        softAssert.assertTrue(cartItemsCount > 0, "Cart should contain items. Found: " + cartItemsCount);

        String totalPrice = cartPage.getTotalPrice();
        softAssert.assertNotEquals(totalPrice, "0", "Total price should not be zero");

        boolean isProductInCart = cartPage.isProductInCart(addedProductName);
        softAssert.assertTrue(isProductInCart, "Selected product should be in cart");

        System.out.println("Product added to cart: " + addedProductName);
        System.out.println("Cart items count: " + cartItemsCount);
        System.out.println("Total price: " + totalPrice);
        System.out.println("=== Finished testAddProductToCart ===\n");

    }

    @Test(priority = 2)
    public void testRemoveProductFromCart() throws InterruptedException {
        System.out.println("=== Starting testRemoveProductFromCart ===");

        productPage.SelectRandomProduct();
        Thread.sleep(2000);
        addedProductName = productPage.getProductName();
        Thread.sleep(2000);
        softAssert.assertFalse(addedProductName.isEmpty(), "Product name should not be empty");

        Thread.sleep(2000);
        productPage.clickAddToCart();
        Thread.sleep(2000);

        productPage.navigateToCart();
        Thread.sleep(2000);
        System.out.println("Current URL: " + driver.getCurrentUrl());
        Thread.sleep(2000);
        int initialCartCount = cartPage.getCartItemsCount();
        System.out.println("Initial cart count: " + initialCartCount);
        Thread.sleep(2000);
        softAssert.assertTrue(initialCartCount > 0,
                "Cart should have items from previous test. Found: " + initialCartCount);

        if (initialCartCount > 0) {
            System.out.println("Cart contents before deletion:");
            String totalBefore = cartPage.getTotalPrice();
            System.out.println("Total before: " + totalBefore);
            System.out.println("Product in cart: " + addedProductName);
            Thread.sleep(2000);
            System.out.println("Deleting item from cart...");
            cartPage.deleteItemFromCart(0);
            Thread.sleep(3000);

            driver.navigate().refresh();
            Thread.sleep(2000);

            int finalCartCount = cartPage.getCartItemsCount();
            System.out.println("Final cart count: " + finalCartCount);
            Thread.sleep(2000);
            softAssert.assertTrue(finalCartCount < initialCartCount,
                    "Cart count should decrease after deletion. Before: " + initialCartCount + ", After: " + finalCartCount);

            String totalAfter = cartPage.getTotalPrice();
            System.out.println("Total after: " + totalAfter);

            System.out.println("Item removed from cart successfully");
            System.out.println("Initial count: " + initialCartCount + ", Final count: " + finalCartCount);
        }

        System.out.println("=== Finished testRemoveProductFromCart ===\n");
    }
}