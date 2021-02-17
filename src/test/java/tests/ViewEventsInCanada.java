package tests;

import PageObjects.EventListPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

public class ViewEventsInCanada extends TestBase {

    private static Logger logger = LogManager.getLogger(ViewFutureEventsTest.class);

    EventListPage eventListPage1;

    @Test
    public void test() throws InterruptedException, ParseException {
        eventListPage = openPastEvents();
        eventListPage.locationClick();
        eventListPage.setCountryFilter("Canada");
        Assertions.assertEquals(eventListPage.eventsTabCount(), eventListPage.eventsCount());
        Assertions.assertEquals(true, eventListPage.checkEventsDate(false));
        logger.info("test4 is OK");
    }
}
