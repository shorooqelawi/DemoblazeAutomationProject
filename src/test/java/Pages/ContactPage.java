package Pages;

import Utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ContactPage extends TestBase {

    public ContactPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Contact modal elements
    @FindBy(id = "exampleModalLabel")
    private WebElement contactModalTitle;

    @FindBy(id = "recipient-email")
    private WebElement contactEmailInput;

    @FindBy(id = "recipient-name")
    private WebElement contactNameInput;

    @FindBy(id = "message-text")
    private WebElement messageInput;

    @FindBy(xpath = "//button[contains(text(),'Send message')]")
    private WebElement sendMessageButton;

    @FindBy(xpath = "//div[@id='exampleModal']//button[contains(@class, 'btn-secondary')]")
    private WebElement closeButton;

    // Success message elements
    @FindBy(xpath = "//div[contains(@class, 'sweet-alert')]//h2")
    private WebElement successMessage;



    // Modal interaction methods
    public boolean isContactModalDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(contactModalTitle));
            return contactModalTitle.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void closeContactModal() {
        closeButton.click();
    }

    // Form interaction methods
    public void enterContactEmail(String email) {
        contactEmailInput.clear();
        contactEmailInput.sendKeys(email);
    }

    public void enterContactName(String name) {
        contactNameInput.clear();
        contactNameInput.sendKeys(name);
    }

    public void enterMessage(String message) {
        messageInput.clear();
        messageInput.sendKeys(message);
    }

    public void clickSendMessage() {
        sendMessageButton.click();
    }

    // Success message verification
    public boolean isSuccessMessageDisplayed() {
        return waitForSuccessMessage(15);
    }
    
    public boolean waitForSuccessMessage(int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            // Try with the current locator first
            try {
                wait.until(ExpectedConditions.visibilityOf(successMessage));
                return successMessage.isDisplayed();
            } catch (Exception e) {
                // If the first locator fails, try an alternative locator
                try {
                    WebElement altSuccessMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[contains(@class,'sweet-alert')]//h2[contains(text(),'Thanks')]")
                    ));
                    return altSuccessMessage.isDisplayed();
                } catch (Exception ex) {
                    return false;
                }
            }
        } catch (Exception e) {
            return false;
        }
    }

    public String getSuccessMessageText() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            // Try with the current locator first
            try {
                wait.until(ExpectedConditions.visibilityOf(successMessage));
                return successMessage.getText();
            } catch (Exception e) {
                // If the first locator fails, try an alternative locator
                try {
                    return wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[contains(@class,'sweet-alert')]//h2")
                    )).getText();
                } catch (Exception ex) {
                    return "";
                }
            }
        } catch (Exception e) {
            return "";
        }
    }

    // Complete contact form submission
    public void submitContactForm(String email, String name, String message) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        
        try {
            // Wait for email input to be visible and interactable
            wait.until(ExpectedConditions.visibilityOf(contactEmailInput));
            wait.until(ExpectedConditions.elementToBeClickable(contactEmailInput));
            
            // Clear and enter values with proper waits
            contactEmailInput.clear();
            contactEmailInput.sendKeys(email);
            
            wait.until(ExpectedConditions.visibilityOf(contactNameInput));
            contactNameInput.clear();
            contactNameInput.sendKeys(name);
            
            wait.until(ExpectedConditions.visibilityOf(messageInput));
            messageInput.clear();
            messageInput.sendKeys(message);
            
            // Scroll into view and click
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sendMessageButton);
            wait.until(ExpectedConditions.elementToBeClickable(sendMessageButton));
            
            // Add a small delay to ensure everything is ready
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            // Click the send button
            sendMessageButton.click();
            
            // Wait for the success message to appear
            wait.until(ExpectedConditions.alertIsPresent());
            
        } catch (Exception e) {
            System.out.println("Error during form submission: " + e.getMessage());
            throw e;
        }
    }
}
