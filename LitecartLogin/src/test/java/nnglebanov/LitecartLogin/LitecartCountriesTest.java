package nnglebanov.LitecartLogin;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringJoiner;

/**
 * Created by Administrator on 26.08.2017.
 */
public class LitecartCountriesTest {

    private WebDriver driver;

    @Before
    public void start()
    {
        driver=new ChromeDriver();
    }
    @Test
    public void test() throws InterruptedException {

        //Подпункт 1

        driver.get("http://localhost/litecart/admin");
        WebElement name = driver.findElement(By.cssSelector("input[name=username]"));
        WebElement password = driver.findElement(By.cssSelector("input[name=password]"));
        WebElement loginButton = driver.findElement(By.cssSelector("button[name=login]"));
        name.sendKeys("admin");
        password.sendKeys("admin");
        loginButton.click();
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");

        WebElement dataTable = driver.findElement(By.cssSelector("table.dataTable"));
        ArrayList<WebElement> rows = (ArrayList<WebElement>) dataTable.findElements(By.cssSelector("tr.row"));

        ArrayList<String> unsortedElements=new ArrayList<>();
        ArrayList<String> sortedElements=new ArrayList<>();
        ArrayList<String> countriesWithZones=new ArrayList<>();

        for (WebElement row : rows)
        {
            String countryName=row.findElement(By.cssSelector("td:nth-child(5)")).findElement(By.cssSelector("a")).getText();
            unsortedElements.add(countryName);
            int numOfZones=Integer.parseInt(row.findElement(By.cssSelector("td:nth-child(6)")).getText());
            if(numOfZones>0)
            {
                System.out.println(numOfZones);
                countriesWithZones.add(countryName);
            }
        }
        sortedElements=(ArrayList<String>) unsortedElements.clone();
        sortedElements.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {

                    return o1.compareTo(o2);
            }
        });
        sortedElements.forEach(a->System.out.println(a));
        assert (sortedElements.equals(unsortedElements));


        dataTable = driver.findElement(By.cssSelector("table.dataTable"));
        rows = (ArrayList<WebElement>) dataTable.findElements(By.cssSelector("tr.row"));
        continuelabel:for(String country : countriesWithZones)
        {
            countriesWithZones.forEach(a->System.out.println(a));

            dataTable = driver.findElement(By.cssSelector("table.dataTable"));
            rows = (ArrayList<WebElement>) dataTable.findElements(By.cssSelector("tr.row"));
            for(WebElement row:rows)
            {
                WebElement countryElement=row.findElement(By.cssSelector("td:nth-child(5)"));
                if(country.equals(countryElement.getText()))
                {
                    countryElement.findElement(By.cssSelector("a")).click();
                    ArrayList<WebElement> unsortedZones=new ArrayList<>();
                    unsortedZones=(ArrayList<WebElement>) driver.findElements(By.cssSelector("table#table-zones.dataTable tr:not(.header)>td:nth-child(3)"));

                    ArrayList<String> unsortedStringZones=new ArrayList<>();
                    unsortedZones.forEach(a->unsortedStringZones.add(a.getText()));
                    unsortedStringZones.remove("");
                    ArrayList<String> sortedStringZones=(ArrayList<String>) unsortedStringZones.clone();
                    System.out.println(sortedStringZones.size());
                    sortedStringZones.sort(new Comparator<String>() {
                        @Override
                        public int compare(String o1, String o2) {
                            return o1.compareTo(o2);
                        }
                    });
                    System.out.println("---------несортированные------");
                    unsortedStringZones.forEach(a->System.out.println(a));
                    System.out.println("---------сортированные------");
                    sortedStringZones.forEach(a->System.out.println(a));
                    assert (unsortedStringZones.equals(sortedStringZones));
                    driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
                    continue continuelabel;
                }
            }
        }

    }
    @After
    public void stop()
    {
        driver.quit();
    }
}
