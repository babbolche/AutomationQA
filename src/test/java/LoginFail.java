import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class LoginFail {

    static WebDriver driver;

    @BeforeMethod
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\WebDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://shop.pragmatic.bg/admin/");
    }

    @Test
    public void Login() {
        WebElement username = driver.findElement(By.cssSelector("input#input-username"));
        WebElement password = driver.findElement(By.cssSelector("input#input-password"));

        username.sendKeys("admin");
        password.sendKeys("parola123!");

        WebElement button = driver.findElement(By.cssSelector("button[type=\"submit\"]"));
        button.click();

        WebElement loginFail = driver.findElement(By.cssSelector("div[class=\"alert alert-danger alert-dismissible\"]"));
        loginFail.getText();

    }
}