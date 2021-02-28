package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class VideoListPage extends AbstractPage {

    public VideoListPage (WebDriver driver) { super(driver); }

    private String moreFilters = "//span[text()='More Filters']";
    private String filter = "//span[text()='%1']/..";
    private String filterInput = filter + "/following-sibling::div/div[contains(@class, 'search')]/input";
    private String filterCheckbox = "//label[@data-value='%1']";
    private String language = "//label[@data-value='%1']";
    private String refElems = "//div[@class='evnt-talks-row']//a";

    private String filterLang;
    private String filterAddress;
    private String filterTopic;

    VideoPage videoPage;
    ArrayList<String> tabs;

    public VideoListPage moreFiltersClick() throws InterruptedException {
        Thread.sleep(2000);
        //waitingForLoader();
        clickElementByXpath(moreFilters);
        return this;
    }

    public VideoListPage setFilter(String filterName, String value) {
        clickElementByXpath(filter.replace("%1", filterName));
        sendText(value, filterInput.replace("%1", filterName));
        clickElementByXpath(filterCheckbox.replace("%1", value));
        return this;
    }

    public VideoListPage setLanguageFilter(String value) throws InterruptedException {
        clickElementByXpath(filter.replace("%1", "Language"));
        clickElementByXpath(language.replace("%1", value));
        filterLang = value;
        Thread.sleep(2000);
        //waitingForLoader();
        return this;
    }

    public void waitingForLoader() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(loader))));
        new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath(loader))));
    }

    public boolean checkVideoLinks() throws InterruptedException {
        List<String> refList = new ArrayList<String>();
        List<WebElement> elems = driver.findElements(By.xpath(refElems));
        boolean result = false;
        int i=0;
        for (WebElement elem: elems) {
            refList.add(elem.getAttribute("href"));
            System.out.println(refList.size() + " " + i);
            ((JavascriptExecutor)driver).executeScript("window.open()");
            tabs = new ArrayList<String>(driver.getWindowHandles());
            videoPage = openVideoPage(refList.get(i));
            result = checkData();
            driver.close();
            tabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(0));
            i++;
        }

        return result;
    }

    public VideoPage openVideoPage(String ref) {
        driver.switchTo().window(tabs.get(1));
        driver.get(ref);
        return new VideoPage(driver);
    }

    public boolean checkData() throws InterruptedException {
        Thread.sleep(5000);
        if (videoPage.checkAddress("Belarus") &&
                videoPage.checkLang(filterLang) &&
                videoPage.checkTopic("QA"))
                return true;

        else return false;
    }








}
