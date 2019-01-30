import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class DropdownTest {
    static WebDriver driver;

    @BeforeMethod
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\WebDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://shop.pragmatic.bg/admin/");
    }

    @Test
    public void testDropdownOrderStatus() {

        WebElement username = driver.findElement(By.cssSelector("input#input-username"));
        WebElement password = driver.findElement(By.cssSelector("input#input-password"));

        username.sendKeys("admin");
        password.sendKeys("parola123!");

        WebElement button = driver.findElement(By.cssSelector("button[type=\"submit\"]"));
        button.click();

        WebElement salesLink = driver.findElement(By.cssSelector("li#menu-sale a"));
        salesLink.click();

        WebElement ordersLink = driver.findElement(By.cssSelector("ul#collapse26 a"));
        ordersLink.click();


        WebElement dropdown = driver.findElement(By.id("input-order-status"));
        Select ordersStatus = new Select(dropdown);
        assertFalse(ordersStatus.isMultiple());


        System.out.println("========================");
        System.out.println(ordersStatus.getOptions().toArray());


        assertEquals(ordersStatus.getOptions().size(), 16);

        List<String> exp_options = Arrays.asList(new String[]{"","Missing Orders", "Canceled", "Canceled Reversal", "Chargeback",
                "Completed", "Denied", "Expired", "Failed", "Pending", "Processed", "Processing", "Refunded", "Reversed", "Shipped", "Voided"});
        List<String> act_options = new ArrayList<>();

        List<WebElement> allOptions = ordersStatus.getOptions();

        for (WebElement option : allOptions) {
            act_options.add(option.getText());

            assertEquals(act_options.toArray(), exp_options.toArray());

        }
    }

//    @AfterMethod
//    public void ShutDown() {
//        driver.quit();
//    }
}

