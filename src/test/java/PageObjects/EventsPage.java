package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EventsPage extends AbstractPage {

    public EventsPage (WebDriver driver) { super(driver);}

    private String upcomingEventsTab = "//span[text()='Upcoming events']/..";
    private String pastEventsTab = "//span[text()='Past Events']/..";
    private String eventsCount = "//a[contains(@class, 'nav-link active')]/span[contains(@class, 'counter')]";
    private String eventsList = "//div[@class='evnt-events-row']/div"; //лист событий
    private String eventsDate = "//span[@class='date']";
    private String eventPlace = "//*[contains(@class, 'event-details')]//p[@class='online']/span";
    private String eventLang = "//*[contains(@class, 'event-details')]//p[@class='language']/span";
    private String eventTitle = "//h1[contains(@title, '')]";
    private String eventSpeakers = "//div[@class='speakers-wrapper']";
    private String location = "//span[text()='Location']/..";
    private String locationInput = location + "/following-sibling::div/div[contains(@class, 'search')]/input";
    private String locationCheckbox = "//label[@data-value='%1']";
    private String loader = "//div[@class='evnt-global-loader']";
    //TODO: информация о регистрации на событие ??

    public EventsPage upcomingEventsClick() {
        clickElement(upcomingEventsTab);
        return this;
    }

    public EventsPage openPastEvents() {
        clickElement(pastEventsTab);
        return this;

    }

    public int eventsTabCount() {
        String count = driver.findElement(By.xpath(eventsCount)).getText();
        return Integer.parseInt(count);
    }

    public int eventsCount() {
        return driver.findElements(By.xpath(eventsList)).size();
    }

   /* public boolean upcomingEventsIsUpcoming() throws ParseException {
        int upcomingEventsCount = eventsCount();
        List<WebElement> upcomingEventsList = driver.findElements(By.xpath(upcomingEventsDate));
        int i=0;
        for (WebElement element : upcomingEventsList)
        {
            if (isDateInFuture(element)) i += 1;
        }
        if (i == upcomingEventsCount)
            return true;
        else return false;
    } */

    public boolean checkEventsDate(boolean futureDate) throws ParseException {
        int eventsCount = eventsCount();
        List<WebElement> eventsList = driver.findElements(By.xpath(eventsDate));
        int i=0;
        for (WebElement element : eventsList)
        {
            if (checkDateEvents(element, futureDate)) i += 1;
        }
        if (i == eventsCount)
            return true;
        else return false;
    }

    public boolean placeIsDisplayed() { return driver.findElement(By.xpath(eventPlace)).isDisplayed();  }
    public boolean langIsDisplayed() { return driver.findElement(By.xpath(eventLang)).isDisplayed(); }
    public boolean nameIsDisplayed() { return driver.findElement(By.xpath(eventTitle)).isDisplayed();  }
    public boolean dateIsDisplayed() { return driver.findElement(By.xpath(eventsDate)).isDisplayed(); }
    public boolean speakersIsDisplayed() { return driver.findElement(By.xpath(eventSpeakers)).isDisplayed(); }

    public Point locationElement(String xpath) { return driver.findElement(By.xpath(xpath)).getLocation(); }

    private boolean checkDateEvents(WebElement elem, boolean futureDate) throws ParseException {

        LocalDate eventDate = parseEventDate(elem);
        Date currentDate = java.util.Calendar.getInstance().getTime();
        LocalDate currentLocalDate = convertToLocalDate(currentDate);
        if (futureDate == true) return eventDate.isAfter(currentLocalDate);
        else return eventDate.isBefore(currentLocalDate);
    }

    private LocalDate parseEventDate(WebElement elem) throws ParseException {

        SimpleDateFormat dateFormatter = new SimpleDateFormat("d MMM yyyy", Locale.ENGLISH);
        String dates = elem.getText();
        String[] eventDateRange = dates.split(" - "); //сплитим период ивента
        Date finalDate = dateFormatter.parse(eventDateRange[1]); //конвертируем стринг в дату
        LocalDate finalLocalDate = convertToLocalDate(finalDate);
        return finalLocalDate;
    }

    public LocalDate convertToLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }


    public boolean isElementsLocatedRight() {

        if (locationElement(eventPlace).getX() < locationElement(eventLang).getX()
                ||  locationElement(eventPlace).getY() < locationElement(eventLang).getY())
        {
            if (locationElement(eventLang).getX() < locationElement(eventTitle).getX()
                    ||  locationElement(eventLang).getY() < locationElement(eventTitle).getY())
            {
                if (locationElement(eventTitle).getX() < locationElement(eventsDate).getX()
                        ||  locationElement(eventTitle).getY() < locationElement(eventsDate).getY())
                {
                    if (locationElement(eventsDate).getX() < locationElement(eventSpeakers).getX()
                            ||  locationElement(eventsDate).getY() < locationElement(eventSpeakers).getY())
                        return true;
                }
            }
        }
        return false;

    }

    public EventsPage locationClick() {
        clickElement(location);
        return this;
    }

    public EventsPage setCountryFilter (String country) {
        clickElement(locationInput);
        sendText(country, locationInput);
        clickElement(locationCheckbox.replace("%1", country));
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(loader))));
        new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath(loader))));
        return this;
    }

}
