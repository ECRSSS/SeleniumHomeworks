package nnglebanov.LitecartLogin;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 12.09.2017.
 */
public class LogsTest {

    private WebDriver driver;

    @Before
    public void start()
    {
        driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }
    @Test
    public void test() throws InterruptedException
    {
        driver.get("http://localhost/litecart/admin");
        WebElement name = driver.findElement(By.cssSelector("input[name=username]"));
        WebElement password = driver.findElement(By.cssSelector("input[name=password]"));
        WebElement loginButton = driver.findElement(By.cssSelector("button[name=login]"));
        name.sendKeys("admin");
        password.sendKeys("admin");
        loginButton.click();
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        ArrayList<WebElement> elements=(ArrayList<WebElement>)driver.findElements(By.cssSelector("tr.row>td:nth-child(3)>a"));
        WebElement toRemove=null;
        for(int i=0;i<elements.size();i++) {
            driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
            elements=(ArrayList<WebElement>)driver.findElements(By.cssSelector("tr.row>td:nth-child(3)>a"));
            for (WebElement e : elements) {
                if (e.getText().equals("Subcategory")) {
                    toRemove = e;
                }
            }
            if (toRemove != null) {
                elements.remove(toRemove);
            }
            elements.get(i).click();
            assert (driver.manage().logs().get("browser").getAll().isEmpty());
            assert (driver.manage().logs().get("driver").getAll().isEmpty());
            assert (driver.manage().logs().get("client").getAll().isEmpty());


        }



    }

    @After
    public void stop()
    {
        driver.quit();
    }
}
