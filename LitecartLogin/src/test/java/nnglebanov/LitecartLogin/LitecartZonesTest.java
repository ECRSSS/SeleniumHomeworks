package nnglebanov.LitecartLogin;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by Administrator on 26.08.2017.
 */
public class LitecartZonesTest {
    private WebDriver driver;

    @Before
    public void start()
    {
        driver=new ChromeDriver();
    }
    @Test
    public void test() throws InterruptedException
    {
        driver.get("http://localhost/litecart/admin");
        WebElement name = driver.findElement(By.cssSelector("input[name=username]"));
        WebElement password = driver.findElement(By.cssSelector("input[name=password]"));
        WebElement loginButton = driver.findElement(By.cssSelector("button[name=login]"));
        name.sendKeys("admin");
        password.sendKeys("admin");
        loginButton.click();



        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        ArrayList<WebElement> elementsZone=(ArrayList<WebElement>) driver.findElements(By.cssSelector("table.dataTable tr.row"));
        int numOfZones=elementsZone.size();
        //System.out.println(numOfZones);
        for(int i=1;i<=numOfZones;i++)
        {
            driver.findElement(By.cssSelector("tr.row:nth-child("+(i+1)+")")).findElement(By.cssSelector("td:nth-child(3) a")).click();
            Thread.sleep(1000);
            WebElement zoneTable=driver.findElement(By.cssSelector("table#table-zones.dataTable"));
            ArrayList<WebElement> zonesElements=(ArrayList<WebElement>) zoneTable.findElements(By.cssSelector("tr:not(.header)"));
            zonesElements.remove(zonesElements.size()-1);
            ArrayList<String> unsortedZonesString=new ArrayList<>();
            for(WebElement e:zonesElements)
            {
                unsortedZonesString.add(e.findElement(By.cssSelector("td:nth-child(3)>select>option[selected=selected]")).getText());
            }
            //unsortedZonesString.forEach(a->System.out.println(a));
            ArrayList<String> sortedZonesString=(ArrayList<String>) unsortedZonesString.clone();
            sortedZonesString.sort(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            });
            assert (unsortedZonesString.equals(sortedZonesString));

            driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        }
    }
    @After
    public void stop()
    {
      driver.quit();
    }
}
