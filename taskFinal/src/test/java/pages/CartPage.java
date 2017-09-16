package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by Administrator on 16.09.2017.
 */
public class CartPage extends Page {
    public CartPage(WebDriver driver, WebDriverWait wait){super(driver,wait);}
//открыть корзину
  public void open(){driver.get("http://localhost/litecart/en/checkout");}
  //удалить все элементы из корзины
    public void removeItems()
    {
        while(driver.findElements(By.cssSelector("button[name='remove_cart_item']")).size()>0) {
            WebElement div = driver.findElement(By.cssSelector("div#order_confirmation-wrapper"));
            WebElement tr=div.findElement(By.cssSelector("tr:not(.header)"));
            driver.findElement(By.cssSelector("button[name='remove_cart_item']")).click();
            wait.until(ExpectedConditions.stalenessOf(tr));
        }
    }
}
