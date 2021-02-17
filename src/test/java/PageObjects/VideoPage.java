package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VideoPage extends AbstractPage {

    public VideoPage (WebDriver driver) { super(driver); }

    private String moreFilters = "//span[text()='More Filters']";
    private String filter = "//span[text()='%1']/..";
    private String filterInput = filter + "/following-sibling::div/div[contains(@class, 'search')]/input";
    private String filterCheckbox = "//label[@data-value='%1']";
    private String language = "//label[@data-value='%1']";

    public VideoPage moreFiltersClick() {
        waitingForLoader();
        clickElementByXpath(moreFilters);
        return this;
    }

    public VideoPage setFilter(String filterName, String value) {
        clickElementByXpath(filter.replace("%1", filterName));
        sendText(value, filterInput.replace("%1", filterName));
        clickElementByXpath(filterCheckbox.replace("%1", value));
        return this;
    }

    public VideoPage setLanguageFilter(String value) {
        clickElementByXpath(filter.replace("%1", "Language"));
        clickElementByXpath(language.replace("%1", value));
        waitingForLoader();
        return this;
    }

    public void waitingForLoader() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(loader))));
        new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath(loader))));
    }







}
