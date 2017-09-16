package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 16.09.2017.
 */
public class ItemPage extends Page {
    public ItemPage(WebDriver driver, WebDriverWait wait){super(driver,wait);}

    //установить значение в Select, если он есть
    public void setSize()
    {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        if(driver.findElements(By.cssSelector("select[name='options[Size]']")).size()>0)
        {
            Select size=new Select(driver.findElement(By.cssSelector("select[name='options[Size]']")));
            size.selectByIndex(1);
        }
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }
    //Добавить товар в корзину
    public void addItemToCart()
    {
        driver.findElement(By.cssSelector("button[name='add_cart_product']")).click();
    }
    //подождать обновления корзины
    public void waitUntilCartRefresh(int numOfItems,int quantityOfItems)
    {
        wait.until(d -> d.findElement(By.cssSelector("div#cart span.quantity")).getText().equals("" + (numOfItems + quantityOfItems)));
    }
    //значение поля quantity
    public int getQuatity()
    {
        return Integer.parseInt(driver.findElement(By.cssSelector("input[name='quantity']")).getAttribute("value"));
    }

}
