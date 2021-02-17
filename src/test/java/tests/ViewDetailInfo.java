package tests;

import PageObjects.EventListPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

public class ViewDetailInfo extends TestBase {
    private static Logger logger = LogManager.getLogger(ViewFutureEventsTest.class);

    EventListPage eventListPage;

    @Test
    public void test() throws InterruptedException, ParseException {
        eventListPage = openUpcomingEvents();
        Assertions.assertEquals(true, eventListPage.checkEventsDate(true));
        eventListPage.eventClick();

        logger.info("test5 is OK");
    }

}
