package nnglebanov.LitecartLogin;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Administrator on 29.08.2017.
 */
public class LitecartAddProductTest {
    private WebDriver driver;
    private WebDriverWait webDriverWait;
    private Random rnd;
    @Before
    public void start()
    {
        driver=new ChromeDriver();
        webDriverWait=new WebDriverWait(driver,5);
        rnd=new Random(System.currentTimeMillis());

    }
    @Test
    public void test() throws InterruptedException
    {
        driver.manage().window().maximize();
        driver.get("http://localhost/litecart/admin");
        WebElement name = driver.findElement(By.cssSelector("input[name=username]"));
        WebElement password = driver.findElement(By.cssSelector("input[name=password]"));
        WebElement loginButton = driver.findElement(By.cssSelector("button[name=login]"));
        name.sendKeys("admin");
        password.sendKeys("admin");
        loginButton.click();
        driver.findElement(By.cssSelector("li#app-:nth-child(2)")).click();
        driver.findElement(By.cssSelector("td#content div a:last-child")).click();
        //driver.findElement(By.cssSelector("input[name='purchase_price']")).sendKeys("300");
        //Select selectPurchaseCode=new Select(driver.findElement(By.cssSelector("select[name='purchase_price_currency_code'")));
        //selectPurchaseCode.selectByIndex(1);

        //General
        driver.findElement(By.cssSelector("input[name='status']")).click();
        String productName="Test Product "+rnd.nextInt(1000);
        driver.findElement(By.cssSelector("input[name='name[en]']")).sendKeys(productName);
        driver.findElements(By.cssSelector("input[type='checkbox']")).forEach(a->{if(!a.isSelected()){a.click();}});
        driver.findElement(By.cssSelector("input[name='quantity']")).sendKeys(Integer.toString(rnd.nextInt(100)));
        driver.findElement(By.cssSelector("input[name='code']")).sendKeys(Integer.toString(rnd.nextInt(10000)));
        WebElement datepickerFrom = driver.findElement(By.cssSelector("input[name='date_valid_from']"));
        datepickerFrom.sendKeys(Keys.HOME+"30082017");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[name='date_valid_to']"))));
        WebElement datepickerTo = driver.findElement(By.cssSelector("input[name='date_valid_to']"));
        datepickerTo.sendKeys(Keys.HOME+"25092017");
        File picture=new File("./src/test/resources/box.jpg");
        driver.findElement(By.cssSelector("input[name='new_images[]']")).sendKeys(picture.getAbsolutePath());
        //Information
        driver.findElement(By.cssSelector("div.tabs li:nth-child(2) a")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("select[name='manufacturer_id']"))));
        Select manufacturer=new Select(driver.findElement(By.cssSelector("select[name='manufacturer_id']")));
        manufacturer.selectByIndex(1);
        driver.findElement(By.cssSelector("input[name='keywords']")).sendKeys("test");
        driver.findElement(By.cssSelector("input[name='short_description[en]']")).sendKeys("test");
        driver.findElement(By.cssSelector("div.trumbowyg-editor")).sendKeys("test test test");
        driver.findElement(By.cssSelector("input[name='head_title[en]']")).sendKeys("test"+rnd.nextInt(500));
        driver.findElement(By.cssSelector("input[name='meta_description[en]']")).sendKeys("test");

        //prices
        driver.findElement(By.cssSelector("div.tabs li:nth-child(4) a")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[name='purchase_price']")));
        driver.findElement(By.cssSelector("input[name='purchase_price']")).sendKeys(Integer.toString(rnd.nextInt(1000)));
        Select moneyType=new Select(driver.findElement(By.cssSelector("select[name='purchase_price_currency_code']")));
        moneyType.selectByIndex(1);
        driver.findElement(By.cssSelector("input[name='gross_prices[USD]']")).sendKeys(Integer.toString(rnd.nextInt(1000)));
        driver.findElement(By.cssSelector("input[name='gross_prices[EUR]']")).sendKeys(Integer.toString(rnd.nextInt(1000)));
        driver.findElement(By.cssSelector("button[name='save']")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form[name='catalog_form']")));
        ArrayList<WebElement> rows=(ArrayList<WebElement>) driver.findElements(By.cssSelector("form[name='catalog_form'] tbody tr.row"));
        boolean isFound=false;
        for(WebElement row : rows)
        {
            if(row.findElement(By.cssSelector("td:nth-child(3) a")).getText().equals(productName))
                isFound=true;
        }
        assert (isFound);



    }
    @After
    public void stop()
    {
        driver.quit();
    }
}
