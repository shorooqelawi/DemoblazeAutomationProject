package Pages;

import Utilities.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AboutPage extends TestBase {

    public AboutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "videoModalLabel")
    private WebElement aboutUsModalTitle;

    @FindBy(xpath = "//div[@id='videoModal']//button[contains(@class, 'btn-secondary')]")
    private WebElement aboutUsCloseButton;

    @FindBy(xpath = "(//span[@class='vjs-icon-placeholder'])[1]")
    private WebElement aboutUsPlayButton;

    @FindBy(xpath = "//div[@id='videoModal']//div[contains(@class, 'modal-body')]//p")
    private WebElement aboutUsContent;

    @FindBy(xpath = "//div[@id='videoModal']//video")
    private WebElement videoPlayer;

    @FindBy(xpath = "(//span[@class='vjs-icon-placeholder'])[1]")
    private WebElement playButton;

    @FindBy(xpath = "//div[@id='videoModal']//button[@title='Pause']")
    private WebElement pauseButton;

    public boolean isAboutUsModalDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            wait.until(ExpectedConditions.visibilityOf(aboutUsModalTitle));
            return aboutUsModalTitle.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void closeAboutUsModal() {
        click(aboutUsCloseButton);
    }

    public void clickPlayButton() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            if (driver.findElements(By.tagName("iframe")).size() > 0) {
                driver.switchTo().frame(0);
            }

            WebElement playBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(@class, 'vjs-play-control') or contains(@class, 'vjs-big-play-button')]")));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", playBtn);
            playBtn.click();

            if (driver.findElements(By.tagName("iframe")).size() > 0) {
                driver.switchTo().defaultContent();
            }

            Thread.sleep(1000);

        } catch (Exception e) {
            throw new RuntimeException("Failed to click play button: " + e.getMessage());
        }
    }

    public void clickPauseButton() {
        click(pauseButton);
    }

    public boolean isVideoPlayerDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(videoPlayer));
            return videoPlayer.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isVideoPlaying() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(pauseButton));
            return pauseButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getAboutUsContent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(aboutUsContent));
            return aboutUsContent.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public String getVideoPlayerUrl() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(videoPlayer));
            return videoPlayer.getAttribute("src");
        } catch (Exception e) {
            return "";
        }
    }




}