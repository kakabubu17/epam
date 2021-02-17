package tests;

import PageObjects.EventsPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

public class ViewEventsInCanada extends TestBase {

    private static Logger logger = LogManager.getLogger(ViewFutureEventsTest.class);

    EventsPage eventsPage;

    @Test
    public void test() throws InterruptedException, ParseException {
        eventsPage = openPastEvents();
        eventsPage.locationClick();
        eventsPage.setCountryFilter("Canada");
        Assertions.assertEquals(eventsPage.eventsTabCount(), eventsPage.eventsCount());
        Assertions.assertEquals(true, eventsPage.checkEventsDate(false));
        logger.info("test4 is OK");
    }
}
