package nnglebanov.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


/**
 * Created by Administrator on 15.08.2017.
 */
public class FirstTest {
    private WebDriver driver;

    @Before
    public void start()
    {
        driver = new ChromeDriver();
    }
    @Test
    public void test()
    {
        driver.get("https://github.com/");
    }
    @After
    public void stop()
    {
        driver.quit();
        driver=null;
    }
}
