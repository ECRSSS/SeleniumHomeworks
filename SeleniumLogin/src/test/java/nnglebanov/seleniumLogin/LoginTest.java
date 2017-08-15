package nnglebanov.seleniumLogin;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by Administrator on 15.08.2017.
 */
public class LoginTest {
    private WebDriver driver;
    private WebDriverWait webDriverWait;

    @Before
    public void start()
    {
        driver=new ChromeDriver();
        webDriverWait=new WebDriverWait(driver,10);

    }
    @Test
    public void test()
    {
        driver.navigate().to("http://localhost/litecart/admin/");
        webDriverWait.until(titleIs("My Store"));
        driver.findElement(By.name("username")).sendKeys("test");
        driver.findElement(By.name("password")).sendKeys("test");
        driver.findElement(By.name("login")).click();
    }
    @After
    public void stop()
    {
        driver.quit();
        driver=null;
    }

}
