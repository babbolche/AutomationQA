import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ActionsCheck {

    static WebDriver driver;

    @BeforeMethod
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\WebDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://pragmatic.bg/automation/lecture13/Config.html");
    }

    @Test
    public void MultipleSelect() {

        Select colors = new Select(driver.findElement(By.name("color")));

        assertTrue(colors.isMultiple());

        Actions builder = new Actions(driver);

        builder
                .keyDown(Keys.CONTROL)
                .click(driver.findElement(By.cssSelector("select[name='color'] option[value='rd']")))
                .click(driver.findElement(By.cssSelector("select[name='color'] option[value='sl']")))
                .keyUp(Keys.CONTROL)
                .perform();

        assertEquals(colors.getAllSelectedOptions().size(), 2);

        List<String> exp_sel_options = Arrays.asList(new String[]{ "Red", "Silver"});
        List<String> act_sel_options = new ArrayList<String>();

        for (WebElement option : colors.getAllSelectedOptions())
            act_sel_options.add(option.getText());

        assertEquals(act_sel_options, exp_sel_options);


    }

    @AfterMethod
    public void ShutDown() {
        driver.quit();
    }
}