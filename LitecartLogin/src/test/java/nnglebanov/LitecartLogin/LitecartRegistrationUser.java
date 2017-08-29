package nnglebanov.LitecartLogin;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

/**
 * Created by Administrator on 29.08.2017.
 */
public class LitecartRegistrationUser {
    private WebDriver driver;
    private WebDriverWait wait;
    @Before
    public void start()
    {
        driver=new ChromeDriver();
        wait=new WebDriverWait(driver,1);
    }
    @Test
    public void test() throws InterruptedException
    {
        driver.get("http://localhost/litecart/en/");
        WebElement createAccountLink=driver.findElement(By.cssSelector("td.account li:nth-child(3)"));
        createAccountLink.click();
        driver.findElement(By.cssSelector("input[name='firstname']")).sendKeys("test");
        driver.findElement(By.cssSelector("input[name='lastname']")).sendKeys("test");
        driver.findElement(By.cssSelector("input[name='address1']")).sendKeys("test");
        driver.findElement(By.cssSelector("input[name='postcode']")).sendKeys("12345");
        driver.findElement(By.cssSelector("input[name='city']")).sendKeys("test");
        driver.findElement(By.cssSelector("input[name='phone']")).sendKeys("12345");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("test");
        driver.findElement(By.cssSelector("input[name='confirmed_password']")).sendKeys("test");
        String res = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder email = new StringBuilder();
        Random rnd = new Random(System.currentTimeMillis());
        for(int i=0;i<9;i++)
        {
            email.append(res.charAt(rnd.nextInt(res.length())));
        }
        email.append("@gmail.com");
        System.out.println(email);
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys(email);

        Select selectCountry=new Select(driver.findElement(By.cssSelector("select[name='country_code']")));
        selectCountry.selectByValue("US");
        Select selectZone=new Select(driver.findElement(By.cssSelector("select[name='zone_code']")));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("select[name='zone_code']"))));
        selectZone.selectByIndex(rnd.nextInt(50));
        driver.findElement(By.cssSelector("button[name='create_account']")).click();
        //ожидание видимости элемента, доступного только залогиненному пользователю
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#box-account.box")));

        driver.findElement(By.cssSelector("td.account li:last-child a")).click();
        driver.findElement(By.cssSelector("td.account li:last-child a")).click();
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys(email);
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("test");
        driver.findElement(By.cssSelector("button[name='login']")).click();
        driver.findElement(By.cssSelector("td.account li:last-child a")).click();


    }
    @After
    public void stop()
    {
        driver.quit();
    }
}
