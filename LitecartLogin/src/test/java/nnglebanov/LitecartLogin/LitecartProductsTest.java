package nnglebanov.LitecartLogin;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 24.08.2017.
 */
public class LitecartProductsTest {
    private WebDriver driver;


    @Before
    public void start()
    {
        driver=new ChromeDriver();
    }

    @Test
    public void test()
    {
        driver.get("http://localhost/litecart");
        ArrayList<WebElement> uls=(ArrayList<WebElement>) driver.findElements(By.cssSelector("ul.listing-wrapper.products"));
        uls.forEach(webElement -> System.out.println(webElement));
        for(WebElement ul:uls)
        {
           ArrayList<WebElement> lis=(ArrayList<WebElement>) ul.findElements(By.cssSelector("li"));
           for(WebElement li:lis)
           {
               List<WebElement> stickers=li.findElements(By.cssSelector("div.sticker"));
               System.out.println(li.findElement(By.cssSelector("a.link")).getAttribute("title")+" Количество стикеров: "+stickers.size());
               assert (stickers.size()==1);
           }
           System.out.println("-----------");
        }
    }


    @After
    public void stop()
    {
        driver.quit();
    }

}
