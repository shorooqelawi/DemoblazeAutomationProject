package Tests;

import Pages.AboutPage;
import Pages.HomePage;
import Utilities.TestBase;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.*;

public class AboutTest extends TestBase {
    private HomePage homePage;
    private AboutPage aboutPage;
    private SoftAssert softAssert;

    @BeforeMethod
    public void setUpTest() {
        // Initialize page objects and soft assertions before each test
        homePage = new HomePage(driver);
        aboutPage = new AboutPage(driver);
        softAssert = new SoftAssert();
    }

    @Test(priority = 1, description = "Verify About Us modal displays correctly")
    public void verifyAboutUsModalDisplay() {
        homePage.clickAboutUsLink();
        softAssert.assertTrue(aboutPage.isAboutUsModalDisplayed(), "About Us modal is not displayed");
        softAssert.assertTrue(aboutPage.isAboutUsModalDisplayed(), "About Us modal is not displayed");
    }
    
    @Test(priority = 2, description = "Verify video player functionality")
    public void verifyVideoPlayback() {

        homePage.clickAboutUsLink();
        aboutPage.clickPlayButton();

        softAssert.assertTrue(aboutPage.isAboutUsModalDisplayed(), "About Us modal is not displayed");
    }
    
    @Test(priority = 3, description = "Verify modal can be closed")
    public void verifyModalClose() {

        homePage.clickAboutUsLink();
        aboutPage.closeAboutUsModal();
        softAssert.assertTrue(aboutPage.isAboutUsModalDisplayed(), "About Us modal is not displayed");

    }





}
