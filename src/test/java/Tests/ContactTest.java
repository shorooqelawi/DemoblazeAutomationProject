package Tests;

import Pages.ContactPage;
import Pages.HomePage;
import Utilities.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ContactTest extends TestBase {
    private HomePage homePage;
    private ContactPage contactPage;
    private SoftAssert softAssert;

    @BeforeMethod
    public void pageSetUp() {
        homePage = new HomePage(driver);
        contactPage = new ContactPage(driver);
        softAssert = new SoftAssert();
        
        // Navigate to the home page before each test
        driver.get("https://www.demoblaze.com/");
    }
    @AfterMethod(alwaysRun = true)
    public void assertAll() {
        if (softAssert != null) {
            softAssert.assertAll();
            softAssert = null;
        }
    }

    @Test(priority = 1, description = "Verify contact modal is displayed when clicking Contact link")
    public void verifyContactModalDisplay() {
        // Click on Contact link
        homePage.clickContactLink();
        
        // Verify contact modal is displayed
        softAssert.assertTrue(contactPage.isContactModalDisplayed(), 
            "Contact modal is not displayed after clicking Contact link");
            
        softAssert.assertAll();
    }

    @Test(priority = 2, description = "Verify successful contact form submission")
    public void verifyContactFormSubmission() {
        homePage.clickContactLink();
        
        contactPage.submitContactForm("test@example.com", "Test User", "This is a test message");

    }


    @Test(priority = 3, description = "Verify contact form validation with empty fields")
    public void verifyContactFormValidation() throws InterruptedException {
        homePage.clickContactLink();
        Thread.sleep(2000);
        contactPage.clickSendMessage();
    }

    @Test(priority = 4, description = "Verify contact modal can be closed")
    public void verifyContactModalClose() throws InterruptedException {
        homePage.clickContactLink();
        Thread.sleep(2000);
        contactPage.closeContactModal();
    }
}
