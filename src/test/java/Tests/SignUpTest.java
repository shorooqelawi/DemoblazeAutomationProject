package Tests;

import Pages.SignUpPage;
import Utilities.TestBase;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class SignUpTest extends TestBase {

    private SignUpPage signUpPage;
    private SoftAssert softAssert;

    @BeforeMethod
    public void pageSetUp() {
        signUpPage = new SignUpPage(driver);
        softAssert = new SoftAssert();
    }

    @Test(priority = 1, description = "Verify that user can open sign up modal")
    public void verifySignUpModalOpens() {
        signUpPage.clickSignUpLink();
        softAssert.assertTrue(signUpPage.isSignUpModalDisplayed(), "Sign up modal is not displayed");
        signUpPage.closeSignUpModal();
    }

    @Test(priority = 2, description = "Verify successful user registration")
    public void verifySuccessfulSignUp() {
        String username = "testuser_" + System.currentTimeMillis();
        String password = "Test@123";

        signUpPage.clickSignUpLink();
        signUpPage.enterUsername(username);
        signUpPage.enterPassword(password);
        signUpPage.clickSignUp();

        String alertMessage = signUpPage.getAlertText();
        softAssert.assertTrue(alertMessage.contains("Sign up successful"), "Sign up was not successful");
        signUpPage.acceptAlert();
    }

    @Test(priority = 3, description = "Verify error when username is empty")
    public void verifyEmptyUsernameValidation() {
        signUpPage.clickSignUpLink();
        signUpPage.enterUsername("");
        signUpPage.enterPassword("Test@123");
        signUpPage.clickSignUp();

        String alertMessage = signUpPage.getAlertText();
        softAssert.assertTrue(alertMessage.contains("Please fill out Username and Password"), "Empty username validation failed");
        signUpPage.acceptAlert();
    }

    @Test(priority = 4, description = "Verify error when password is empty")
    public void verifyEmptyPasswordValidation() {
        signUpPage.clickSignUpLink();
        signUpPage.enterUsername("testuser_" + System.currentTimeMillis());
        signUpPage.enterPassword("");
        signUpPage.clickSignUp();

        String alertMessage = signUpPage.getAlertText();
        softAssert.assertTrue(alertMessage.contains("Please fill out Username and Password"), "Empty password validation failed");
        signUpPage.acceptAlert();
    }

    @Test(priority = 5, description = "Fail Test for the report ")
    public void failTest() {

        signUpPage.enterUsername("nonexistentuser");

        signUpPage.clickSignUp();


    }

    @AfterMethod(alwaysRun = true)
    public void assertAll() {
        if (softAssert != null) {
            softAssert.assertAll();
            softAssert = null;
        }
    }

}
