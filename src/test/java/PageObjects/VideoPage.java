package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class VideoPage extends AbstractPage {

    private String lang = "//div[contains(@class, 'language')]/span";
    private String topics = "//div[contains(@class, 'topic small')]/label";
    private String address = "//div[contains(@class, 'location')]/span";

    public VideoPage (WebDriver driver) { super(driver); }

    public boolean checkAddress(String filter) {

        if (driver.findElement(By.xpath(address)).getText().contains(filter))
            return true;

        else return false;

    }

    public boolean checkLang(String filter) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(lang))));
        if (driver.findElement(By.xpath(lang)).getText().contains(filter))
            return true;

        else return false;
    }

    public boolean checkTopic(String filter) {

        List<WebElement> elems = driver.findElements(By.xpath(topics));
        for (WebElement elem: elems )
        {
            if (elem.getText().equals(filter))
                return true;
        }
        return false;
    }
}
