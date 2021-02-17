package tests;

import PageObjects.EventListPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.text.ParseException;

public class ViewEventCardTest extends TestBase{

    protected static WebDriver driver;
    private static Logger logger = LogManager.getLogger(ViewFutureEventsTest.class);


    @Test
    public void test() throws InterruptedException, ParseException {
        EventListPage eventsPage = openUpcomingEvents();

        Assertions.assertEquals(true, eventsPage.checkEventsDate(true));
        Assertions.assertEquals(true, eventsPage.placeIsDisplayed());
        Assertions.assertEquals(true, eventsPage.langIsDisplayed());
        Assertions.assertEquals(true, eventsPage.nameIsDisplayed());
        Assertions.assertEquals(true, eventsPage.dateIsDisplayed());
        Assertions.assertEquals(true, eventsPage.speakersIsDisplayed());
        Assertions.assertEquals(true, eventsPage.isElementsLocatedRight());
        logger.info("test2 is OK");

        //TODO: логировать проверки??
    }

}
