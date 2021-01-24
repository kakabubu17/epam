package PageObjects;

import org.openqa.selenium.By;
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



}
