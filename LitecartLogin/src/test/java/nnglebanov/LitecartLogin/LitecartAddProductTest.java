package nnglebanov.LitecartLogin;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Administrator on 29.08.2017.
 */
public class LitecartAddProductTest {
    private WebDriver driver;
    private WebDriverWait webDriverWait;
    @Before
    public void start()
    {
        driver=new ChromeDriver();
        webDriverWait=new WebDriverWait(driver,2);
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
        driver.findElement(By.cssSelector("li#app-:nth-child(2)")).click();
        driver.findElement(By.cssSelector("td#content div a:last-child")).click();
        //driver.findElement(By.cssSelector("input[name='purchase_price']")).sendKeys("300");
        //Select selectPurchaseCode=new Select(driver.findElement(By.cssSelector("select[name='purchase_price_currency_code'")));
        //selectPurchaseCode.selectByIndex(1);
        driver.findElement(By.cssSelector("input[name='status']")).click();
        driver.findElement(By.cssSelector("input[name='name[en]']")).sendKeys("Test Product");
        driver.findElements(By.cssSelector("input[type='checkbox']")).forEach(a->{if(!a.isSelected()){a.click();}});
        driver.findElement(By.cssSelector("input[name='quantity']")).sendKeys("5");
        JavascriptExecutor javascriptExecutor=(JavascriptExecutor) driver;

        driver.findElement(By.cssSelector("input[name='date_valid_from']")).click();

        javascriptExecutor.executeScript();



        Thread.sleep(4000);

    }
    @After
    public void stop()
    {
        driver.quit();
    }
}
