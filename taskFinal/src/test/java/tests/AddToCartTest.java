package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CartPage;
import pages.ItemPage;
import pages.MainPage;

/**
 * Created by Administrator on 16.09.2017.
 */
public class AddToCartTest {
    private WebDriver driver;
    private WebDriverWait wait;
    @Before
    public void start()
    {
        driver=new ChromeDriver();
        wait=new WebDriverWait(driver,3);
    }
    @Test
    public void test() throws InterruptedException
    {
        MainPage mainPage=new MainPage(driver,wait);
        ItemPage itemPage=new ItemPage(driver,wait);
        CartPage cartPage=new CartPage(driver,wait);

        for(int i=0;i<3;i++) {
            mainPage.open();
            int numOfItemsInCart=mainPage.getNumsOfItemInCart();
            mainPage.openFirstItem();
            itemPage.setSize();
            itemPage.addItemToCart();
            int quantity = itemPage.getQuatity();
            itemPage.waitUntilCartRefresh(numOfItemsInCart, quantity);
        }
        cartPage.open();
        cartPage.removeItems();

    }
    @After
    public void stop()
    {
        driver.quit();
    }

}
