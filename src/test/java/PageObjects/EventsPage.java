package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    private String upcomingEventsCount = "//a[contains(@class, 'nav-link active')]/span[contains(@class, 'counter')]";
    private String upcomingEvents = "//div[@class='evnt-events-row']/div"; //лист событий
    private String upcomingEventsDate = "//span[@class='date']";
    private String eventPlace = "//*[contains(@class, 'event-details')]//p[@class='online']/span";
    private String eventLang = "//*[contains(@class, 'event-details')]//p[@class='language']/span";
    private String eventTitle = "//h1[contains(@title, '')]";
    private String eventSpeakers = "//div[@class='speakers-wrapper']";
    //TODO: информация о регистрации на событие ??

    public EventsPage upcomingEventsClick() {
        clickElement(upcomingEventsTab);
        return this;
    }

    public int upcomingEventsTabCount() {
        String count = driver.findElement(By.xpath(upcomingEventsCount)).getText();
        return Integer.parseInt(count);
    }

    public int upcomingEventsCount() {
        return driver.findElements(By.xpath(upcomingEvents)).size();
    }

    public boolean upcomingEventsIsUpcoming() throws ParseException {
        int upcomingEventsCount = upcomingEventsCount();
        List<WebElement> upcomingEventsList = driver.findElements(By.xpath(upcomingEventsDate));
        int i=0;
        for (WebElement element : upcomingEventsList)
        {
            if (isDateInFuture(element)) i += 1;
        }
        if (i == upcomingEventsCount)
            return true;
        else return false;
    }

    public boolean placeIsDisplayed() { return driver.findElement(By.xpath(eventPlace)).isDisplayed();  }
    public boolean langIsDisplayed() { return driver.findElement(By.xpath(eventLang)).isDisplayed(); }
    public boolean nameIsDisplayed() { return driver.findElement(By.xpath(eventTitle)).isDisplayed();  }
    public boolean dateIsDisplayed() { return driver.findElement(By.xpath(upcomingEventsDate)).isDisplayed(); }
    public boolean speakersIsDisplayed() { return driver.findElement(By.xpath(eventSpeakers)).isDisplayed(); }

    public Point locationElement(String xpath) { return driver.findElement(By.xpath(xpath)).getLocation(); }

    private boolean isDateInFuture(WebElement elem) throws ParseException {

        Date currentDate = java.util.Calendar.getInstance().getTime();
        LocalDate currentLocalDate = convertToLocalDate(currentDate);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("d MMM yyyy", Locale.ENGLISH);
        String dates = elem.getText();
        String[] eventDateRange = dates.split(" - "); //сплитим период ивента
        Date finalDate = dateFormatter.parse(eventDateRange[1]); //конвертируем стринг в дату
        LocalDate finalLocalDate = convertToLocalDate(finalDate);
        return finalLocalDate.isAfter(currentLocalDate);
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
                if (locationElement(eventTitle).getX() < locationElement(upcomingEventsDate).getX()
                        ||  locationElement(eventTitle).getY() < locationElement(upcomingEventsDate).getY())
                {
                    if (locationElement(upcomingEventsDate).getX() < locationElement(eventSpeakers).getX()
                            ||  locationElement(upcomingEventsDate).getY() < locationElement(eventSpeakers).getY())
                        return true;
                }
            }
        }
        return false;

    }


}
