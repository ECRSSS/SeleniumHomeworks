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
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 23.08.2017.
 */
public class LitecartLoginTest {

    private WebDriver driver;

    @Before
    public void start()
    {
        driver=new ChromeDriver();
       // driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }
    @Test
    public void test() throws InterruptedException
    {
        //Авторизация
        driver.get("http://localhost/litecart/admin");
        WebElement name=driver.findElement(By.cssSelector("input[name=username]"));
        WebElement password=driver.findElement(By.cssSelector("input[name=password]"));
        WebElement loginButton=driver.findElement(By.cssSelector("button[name=login]"));
        name.sendKeys("admin");
        password.sendKeys("admin");
        loginButton.click();

        //Перебор элементов главного меню

        int numOfMenuItems=getNumOfItemsMainMenu();
        for(int i=1;i<numOfMenuItems;i++)
        {
            //клик по элементу главного меню
            WebElement boxAppsMenu = driver.findElement(By.cssSelector("#box-apps-menu"));
            WebElement menuElement=takeMainMenuElement(boxAppsMenu,i);
            menuElement.click();
            //проверка наличия заголовка
            driver.findElement(By.cssSelector("h1"));

            boxAppsMenu = driver.findElement(By.cssSelector("#box-apps-menu"));
            int numOfSubMenuItems=takeMainMenuElement(boxAppsMenu,i).findElements(By.cssSelector("li")).size();
            //System.out.println("Количество элементов подменю: "+numOfSubMenuItems);

            //перебор элементов подменю
                for(int j=2;j<=numOfSubMenuItems;j++) {
                    boxAppsMenu = driver.findElement(By.cssSelector("#box-apps-menu"));
                    menuElement = takeMainMenuElement(boxAppsMenu, i);
                    menuElement.findElement(By.cssSelector("li:nth-child(" + j + ")")).click();
                    driver.findElement(By.cssSelector("h1"));
                }
        }

    }
    @After
    public void stop()
    {
        driver.quit();
    }


    //Функция возвращает количество элементов главного меню
    private int getNumOfItemsMainMenu()
    {
        WebElement boxAppsMenu = driver.findElement(By.cssSelector("#box-apps-menu"));
        List<WebElement> items=boxAppsMenu.findElements(By.cssSelector("#app-"));
        System.out.println("Количество элементов меню: "+items.size());
        return items.size();
    }

    //возвращает элемент меню по позиции
    private WebElement takeMainMenuElement(WebElement boxAppsMenu,int elementPosition)
    {
        return boxAppsMenu.findElement(By.cssSelector("li#app-:nth-child("+elementPosition+")"));
    }


}
