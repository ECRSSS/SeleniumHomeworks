package nnglebanov.LitecartLogin;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 01.09.2017.
 */
public class AddToCartTest {
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
    public void test() throws InterruptedException
    {
        for(int i=0;i<3;i++) {
            driver.get("http://localhost/litecart/en/");
            int numOfItems = Integer.parseInt(driver.findElement(By.cssSelector("div#cart span.quantity")).getText());

            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            driver.findElement(By.cssSelector("ul.listing-wrapper.products li")).click();
            if(driver.findElements(By.cssSelector("select[name='options[Size]']")).size()>0)
            {
                Select size=new Select(driver.findElement(By.cssSelector("select[name='options[Size]']")));
                size.selectByIndex(1);
            }
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            driver.findElement(By.cssSelector("button[name='add_cart_product']")).click();

            int quantityOfItems = Integer.parseInt(driver.findElement(By.cssSelector("input[name='quantity']")).getAttribute("value"));
            wait.until(d -> d.findElement(By.cssSelector("div#cart span.quantity")).getText().equals("" + (numOfItems + quantityOfItems)));
        }
            driver.get("http://localhost/litecart/en/");
            driver.findElement(By.cssSelector("div#cart a.link")).click();

         while(driver.findElements(By.cssSelector("button[name='remove_cart_item']")).size()>0) {
        WebElement div = driver.findElement(By.cssSelector("div#order_confirmation-wrapper"));
        WebElement tr=div.findElement(By.cssSelector("tr:not(.header)"));
        driver.findElement(By.cssSelector("button[name='remove_cart_item']")).click();
        wait.until(ExpectedConditions.stalenessOf(tr));
    }


    }
    @After
    public void stop()
    {
        driver.quit();
    }

}
