import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 07.09.2017.
 */
public class Bot {
    private WebDriver driver;
    private WebDriverWait wait;
    @Before
    public void start()
    {
        driver=new ChromeDriver();
        wait=new WebDriverWait(driver,5);
    }
    @Test
    public void test() throws InterruptedException
    {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //logins
       /* driver.get("https://vk.com");
        Thread.sleep(3500);
        driver.findElement(By.cssSelector("input#index_email")).sendKeys("+79772503075");
        driver.findElement(By.cssSelector("input#index_pass")).sendKeys("tarion"+ Keys.ENTER);
        Thread.sleep(3500);
        driver.get("https://ok.ru/");
        driver.findElement(By.cssSelector("input#field_email")).sendKeys("+79772503075");
        driver.findElement(By.cssSelector("input#field_password")).sendKeys("181096a"+ Keys.ENTER);
        Thread.sleep(3500);
        driver.get("https://www.youtube.com/");
        driver.findElement(By.cssSelector("yt-formatted-string#text.style-scope.ytd-button-renderer.style-brand")).click();
        driver.findElement(By.cssSelector("input#identifierId")).sendKeys("ngleb2017@gmail.com"+Keys.ENTER);
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("T6E573F12119BA61"+Keys.ENTER);
        */
       driver.get("https://vktarget.ru/");
       driver.findElement(By.cssSelector("input[data-login='email'][type='email']")).sendKeys("ngleb2017@gmail.com");
       driver.findElement(By.cssSelector("input[data-login='password'][type='password']")).sendKeys("181096"+Keys.ENTER);
       Thread.sleep(3500);







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
