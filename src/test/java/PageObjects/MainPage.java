package PageObjects;

import org.openqa.selenium.WebDriver;

public class MainPage extends AbstractPage {

    private String eventsTab = "//header//a[text()='Events']";

    public MainPage (WebDriver driver) { super(driver); }

    public MainPage open(String url) {
        driver.get(url);
        return this;
    }

    public EventListPage openEventsPage()  {
        clickElement(eventsTab);
        return new EventListPage(driver);
    }
}
