import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class LoginTest {

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
        String usernameValue = username.getAttribute("value");
        String passwordValue = password.getAttribute("value");

        WebElement button = driver.findElement(By.cssSelector("button[type=\"submit\"]"));
        button.click();

        WebElement element = driver.findElement(By.xpath("//*[@id='content']//h1"));
        String value = element.getText();
        assertEquals(value, "Dashboard");

    }

    @AfterMethod
    public void ShutDown() {
        driver.quit();
    }
}
