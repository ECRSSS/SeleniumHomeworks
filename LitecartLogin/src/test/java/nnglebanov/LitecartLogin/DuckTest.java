package nnglebanov.LitecartLogin;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 26.08.2017.
 */
public class DuckTest {
    private WebDriver driver;
    @Before
    public void start()
    {
        driver=new FirefoxDriver();
    }
    @Test
    public void test()
    {
        driver.get("http://localhost/litecart");
        WebElement item=driver.findElement(By.cssSelector("div#box-campaigns.box li"));
        String itemName=item.findElement(By.cssSelector("div.name")).getText();
        WebElement regularPrice=item.findElement(By.cssSelector("s.regular-price"));
        WebElement campaignPrice=item.findElement(By.cssSelector("strong.campaign-price"));
        String regularMainPagePrice=regularPrice.getText();
        String campaignMainPagePrice=campaignPrice.getText();

        //Проверка зачеркнутого текста на обычной цене(главная страница)
        assert (regularPrice.getCssValue("text-decoration").contains("line-through"));
        String colorRegular=regularPrice.getCssValue("color");
        System.out.println(colorRegular);
        ArrayList<Integer> colorsRGB=new ArrayList<>();
        colorsRGB=getDigitsFromString(colorRegular);
        //проверка того, что цвет серый на обычной цене(главная страница)
        assert(colorsRGB.get(0)==colorsRGB.get(1) && colorsRGB.get(1)==colorsRGB.get(2));
        String colorCampaign=campaignPrice.getCssValue("color");
        colorsRGB=getDigitsFromString(colorCampaign);
        //проверка красного цвета на цене по скидке(главная страница)
        assert (colorsRGB.get(1)==0 && colorsRGB.get(2)==0);
        //проверка того, что у скидочной цены жирный шрифт(главная страница)
        assert (campaignPrice.getCssValue("font-weight").contains("bold")||campaignPrice.getCssValue("font-weight").equals("900"));
        String fontRegular=regularPrice.getCssValue("font-size");
        String fontCampaign=campaignPrice.getCssValue("font-size");
        fontRegular=fontRegular.substring(0,fontRegular.length()-2);
        fontCampaign=fontCampaign.substring(0,fontCampaign.length()-2);
        double fontRegularInt=Double.parseDouble(fontRegular);
        double fontCampaignInt=Double.parseDouble(fontCampaign);

        System.out.println(fontRegularInt);
        System.out.println(fontCampaignInt);
        //проверка того, что шрифт обычной цены меньше по размеру, чем шрифт скидочной цены(главная страница)
        assert (fontRegularInt<fontCampaignInt);

        item.click();

        WebElement priceRegularPage=driver.findElement(By.cssSelector("s.regular-price"));
        WebElement priceCampaignPage=driver.findElement(By.cssSelector("strong.campaign-price"));
        WebElement duckNamePage=driver.findElement(By.cssSelector("h1.title"));
        //проверка соответствия имен на главной странице и странице товара
        assert(duckNamePage.getText().equals(itemName));
        //проверка соответствия цен на главной странице и странице товара
        assert (priceRegularPage.getText().equals(regularMainPagePrice));
        assert (priceCampaignPage.getText().equals(campaignMainPagePrice));
        //Проверка зачеркнутого текста на обычной цене(страница товара)
        assert (priceRegularPage.getCssValue("text-decoration").contains("line-through"));
        colorRegular=priceRegularPage.getCssValue("color");
        colorsRGB=getDigitsFromString(colorRegular);
        //проверка того, что цвет серый на обычной цене(страница товара)
        assert(colorsRGB.get(0)==colorsRGB.get(1) && colorsRGB.get(1)==colorsRGB.get(2));

        colorCampaign=priceCampaignPage.getCssValue("color");
        colorsRGB=getDigitsFromString(colorCampaign);
        //проверка красного цвета на цене по скидке(страница товара)
        assert (colorsRGB.get(1)==0 && colorsRGB.get(2)==0);
        //проверка того, что у скидочной цены жирный шрифт(страница товара)
        assert (priceCampaignPage.getCssValue("font-weight").contains("bold")||Integer.parseInt(priceCampaignPage.getCssValue("font-weight"))>=700);
        fontRegular=priceRegularPage.getCssValue("font-size");
        fontCampaign=priceCampaignPage.getCssValue("font-size");
        fontRegular=fontRegular.substring(0,fontRegular.length()-2);
        fontCampaign=fontCampaign.substring(0,fontCampaign.length()-2);
        fontRegularInt=Double.parseDouble(fontRegular);
        fontCampaignInt=Double.parseDouble(fontCampaign);
        //проверка того, что шрифт скидочной цены больше, чем шрифт обычной(страница товара)
        assert (fontCampaignInt>fontRegularInt);

    }
    @After
    public void stop()
    {driver.quit();}

    private ArrayList<Integer> getDigitsFromString(String color)
    {
        ArrayList<Integer> colorsArr=new ArrayList<>();
        Pattern p = Pattern.compile("(\\d)+");
        Matcher m = p.matcher(color);
        while(m.find()) {
            colorsArr.add(Integer.parseInt(color.substring(m.start(),m.end())));
        }
        if(colorsArr.size()>3)
        colorsArr.remove(colorsArr.size()-1);
        colorsArr.forEach(a->System.out.println(a));
        return colorsArr;
    }
}
