package UILoadTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class SessionRunnable implements Runnable{
    private ChromeDriver driver;
    private WebDriverWait wait;
    private int count;
    private final String userName;
    private final String sessionUrl;
    public SessionRunnable(String sessionUrl, String userName){
        this.userName = userName;
        this.sessionUrl= sessionUrl;
    }
    @Override
    public void run() {
        // initialize chrome webdriver
        ChromeOptions options = new BrowserOptions().getOptions(true);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);

        // Waiting settings
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 20);
        // Open page
        driver.navigate().to(sessionUrl);

        //Input name
//        WebElement nameFiled = findElementByid("_b_ahm-zky-pbt-xjq_join_name");
//        nameFiled.sendKeys(userName);
//        nameFiled.sendKeys(Keys.ENTER);

//        driver.findElement(By.id("_b_ahm-zky-pbt-xjq_join_name")).click();
        driver.findElement(By.className("join-form")).click();
        {
            WebElement element = driver.findElement(By.className("join-form"));
            Actions builder = new Actions(driver);
            builder.doubleClick(element).perform();
        }
        System.out.println(sessionUrl+ " ->  "+userName);
        driver.findElement(By.className("join-form")).sendKeys(userName);
        findElementByid("room-join").submit();


        // Choose Session Voice Option
        //findElementByCssSelector(".icon-bbb-unmute").click();

        // Click Ok that you are hearing a voice
        //findElementByClass("iIZhHC").click();



//        findElementByCssSelector(".icon-bbb-thumbs_up").click();
//        findElementByid("message-input").click();
//
//        {
//            WebElement element = findElementByCssSelector(".icon-bbb-send");
//            Actions builder = new Actions(driver);
//            builder.moveToElement(element).perform();
//        }

//        findElementByid("message-input").sendKeys("Welcome");
//        findElementByCssSelector(".icon-bbb-send").click();
//        {
//            WebElement element = driver.findElement(By.tagName("body"));
//            Actions builder = new Actions(driver);
//            builder.moveToElement(element, 0, 0).perform();
//        }
//        findElementByCssSelector("*[data-test=\"chatUserMessageText\"]").click();
//        {
//            WebElement element = findElementByCssSelector("*[data-test=\"chatUserMessageText\"]");
//            Actions builder = new Actions(driver);
//            builder.doubleClick(element).perform();
//        }

//        // Choose Session Voice Option
//        WebElement voiceOption = findElementByClass("clmvZk");
//        voiceOption.click();
//
//        // Click Ok that you are hearing a voice
//        WebElement okOption = findElementByClass("iIZhHC");
//        okOption.click();
//
//        //UnMute Mic
//        WebElement unMute = findElementByClass("icon-bbb-mute");
//        unMute.click();
}


        private WebElement findElementByClass(String className){
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(className)));
            return driver.findElement(By.className(className));
        }
        private WebElement findElementByid(String id){
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
            return driver.findElement(By.id(id));
        }
   private WebElement findElementByCssSelector(String cssSelector){
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector)));
            return driver.findElement(By.cssSelector(cssSelector));
        }


}
