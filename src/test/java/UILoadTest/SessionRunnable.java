package UILoadTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class SessionRunnable implements Runnable {
    private final String userName;
    private final String sessionUrl;

    public SessionRunnable(String sessionUrl, String userName) {
        this.userName = userName;
        this.sessionUrl = sessionUrl;
    }

    @Override
    public void run() {
        // initialize chrome webdriver
        ChromeOptions options = new BrowserOptions().getOptions(true);
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver(options);

        // Waiting settings
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        // Open page
        driver.navigate().to(sessionUrl);

        // Input name
        String nameFiledClassLocator = "join-form";
        System.out.println("Session: " + sessionUrl.substring(sessionUrl.indexOf('-')) + " ->  " + userName);
        driver.findElement(By.className(nameFiledClassLocator)).click();
        driver.findElement(By.className(nameFiledClassLocator)).sendKeys(userName);

        // Click Join
        driver.findElement(By.id("room-join")).submit();

        // Choose Session Voice Option
        String unMuteMicCssLocator = ".icon-bbb-unmute";
        driver.findElement(By.cssSelector(unMuteMicCssLocator)).click();

        // Click Ok that you are hearing a voice
        String confirmVoiceCssLocator = ".icon-bbb-thumbs_up";
        driver.findElement(By.cssSelector(confirmVoiceCssLocator)).click();
    }

}
