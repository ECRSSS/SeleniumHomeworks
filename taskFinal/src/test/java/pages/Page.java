package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Administrator on 16.09.2017.
 */
public class Page {
    protected WebDriver driver;
    protected WebDriverWait wait;
    Page(WebDriver driver,WebDriverWait wait){this.driver=driver;this.wait=wait;}
}
