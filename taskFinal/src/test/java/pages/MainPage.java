package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Administrator on 16.09.2017.
 */
public class MainPage extends Page{
    public MainPage(WebDriver driver, WebDriverWait wait){super(driver,wait);}
    //открытие главной страницы
    public void open(){driver.get("http://localhost/litecart/en/");}
    //сколько элементов в корзине на текущий момент
    public int getNumsOfItemInCart()
    {
        return Integer.parseInt(driver.findElement(By.cssSelector("div#cart span.quantity")).getText());
    }
    //открыть первый элемент в списке товаров
    public void openFirstItem()
    {
        driver.findElement(By.cssSelector("ul.listing-wrapper.products li")).click();
    }




}
