package nnglebanov.LitecartLogin;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 05.09.2017.
 */
public class WindowsTest {
    private WebDriver driver;
    private WebDriverWait wait;
    @Before
    public void start()
    {
        driver=new ChromeDriver();
        wait=new WebDriverWait(driver,3);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    @Test
    public void test()
    {
        driver.get("http://localhost/litecart/admin");
        WebElement name = driver.findElement(By.cssSelector("input[name=username]"));
        WebElement password = driver.findElement(By.cssSelector("input[name=password]"));
        WebElement loginButton = driver.findElement(By.cssSelector("button[name=login]"));
        name.sendKeys("admin");
        password.sendKeys("admin");
        loginButton.click();
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        WebElement row=driver.findElement(By.cssSelector("tr.row"));
        row.findElement(By.cssSelector("td:nth-child(7)")).click();
        ArrayList<WebElement> links=(ArrayList<WebElement>)driver.findElements(By.cssSelector("i.fa.fa-external-link"));
        for(WebElement e : links)
        {
            String mainWindow=driver.getWindowHandle();
            Set<String> windowHandles=driver.getWindowHandles();
            e.click();
            String newWindow=wait.until(anyNewWindow(windowHandles));
            assert (newWindow!=null);
            driver.switchTo().window(newWindow).close();
            driver.switchTo().window(mainWindow);


        }

    }
    @After
    public void stop()
    {
        driver.quit();
    }

    public ExpectedCondition<String> anyNewWindow(Set<String> oldWindows)
    {
        return new ExpectedCondition<String>(){
            public String apply(WebDriver driver)
            {
                Set<String> handles=driver.getWindowHandles();
                handles.removeAll(oldWindows);
                return handles.size()>0 ? handles.iterator().next():null;
            }
        };
    }
}
