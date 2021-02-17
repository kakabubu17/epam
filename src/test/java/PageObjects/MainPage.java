package PageObjects;

import org.openqa.selenium.WebDriver;

public class MainPage extends AbstractPage {

    private String eventsTab = "//header//a[text()='Events']";
    private String videoTab = "//header//a[text()='Video']";

    public MainPage (WebDriver driver) { super(driver); }

    public MainPage open(String url) {
        driver.get(url);
        return this;
    }

    public EventListPage openEventsPage()  {
        clickElementByXpath(eventsTab);
        return new EventListPage(driver);
    }

    public VideoPage openVideoPage()  {
        clickElementByXpath(videoTab);
        return new VideoPage(driver);
    }
}
