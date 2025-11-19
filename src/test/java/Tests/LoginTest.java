package Tests;

import Pages.LoginPage;
import Pages.SignUpPage;
import Utilities.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginTest extends TestBase {

    private LoginPage loginPage;
    private SignUpPage signUpPage;
    private SoftAssert softAssert;
    private String testUsername;
    private final String testPassword = "Test@123";

    @BeforeMethod
    public void pageSetUp() {
        loginPage = new LoginPage(driver);
        signUpPage = new SignUpPage(driver);
        softAssert = new SoftAssert();
        
        // Create a test user before login tests
        testUsername = "testuser_" + System.currentTimeMillis();
        signUpPage.clickSignUpLink();
        signUpPage.enterUsername(testUsername);
        signUpPage.enterPassword(testPassword);
        signUpPage.clickSignUp();
        signUpPage.acceptAlert();
        
        // Log out if needed (in case previous test didn't clean up properly)
        driver.navigate().refresh();
    }

    @Test(priority = 1, description = "Verify that user can open login modal")
    public void verifyLoginModalOpens() {
        loginPage.clickLoginLink();
        softAssert.assertTrue(loginPage.isLoginModalDisplayed(), "Login modal is not displayed");
        loginPage.closeLoginModal();
    }

    @Test(priority = 2, description = "Verify successful user login")
    public void verifySuccessfulLogin() {
        loginPage.clickLoginLink();
        loginPage.enterUsername(testUsername);
        loginPage.enterPassword(testPassword);
        loginPage.clickLogin();

        // Verify successful login by checking welcome message
        softAssert.assertTrue(loginPage.isUserLoggedIn(testUsername), "User is not logged in successfully");
    }

    @Test(priority = 3, description = "Verify error when username is empty")
    public void verifyEmptyUsernameValidation() {
        loginPage.clickLoginLink();
        loginPage.enterUsername("");
        loginPage.enterPassword(testPassword);
        loginPage.clickLogin();

        // The alert should appear with an error message
        String alertMessage = loginPage.getAlertText();
        softAssert.assertTrue(alertMessage.contains("Please fill out Username and Password"), "Empty username validation failed");
        loginPage.acceptAlert();
    }

    @Test(priority = 4, description = "Verify error when password is empty")
    public void verifyEmptyPasswordValidation() {
        loginPage.clickLoginLink();
        loginPage.enterUsername(testUsername);
        loginPage.enterPassword("");
        loginPage.clickLogin();

        // The alert should appear with an error message
        String alertMessage = loginPage.getAlertText();
        softAssert.assertTrue(alertMessage.contains("Please fill out Username and Password"), "Empty password validation failed");
        loginPage.acceptAlert();
    }

    @Test(priority = 5, description = "Verify error when invalid credentials are used")
    public void verifyInvalidCredentials() {
        loginPage.clickLoginLink();
        loginPage.enterUsername("nonexistentuser");
        loginPage.enterPassword("wrongpassword");
        loginPage.clickLogin();

        // The alert should appear with an error message
        String alertMessage = loginPage.getAlertText();
        softAssert.assertTrue(alertMessage.contains("User does not exist"), "Invalid credentials validation failed");
        loginPage.acceptAlert();
    }

    @AfterMethod(alwaysRun = true)
    public void assertAll() {
        if (softAssert != null) {
            softAssert.assertAll();
            softAssert = null;
        }
    }
}
