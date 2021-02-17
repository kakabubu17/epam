package tests;
import PageObjects.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

import java.text.ParseException;

public class ViewFutureEventsTest extends TestBase {

    private static Logger logger = LogManager.getLogger(ViewFutureEventsTest.class);

    @Test
    public void test() throws InterruptedException, ParseException {
        EventListPage eventsPage = openUpcomingEvents();

        Assertions.assertEquals(true, eventsPage.checkEventsDate(true));
        Assertions.assertEquals(eventsPage.eventsTabCount(), eventsPage.eventsCount());
        logger.info("test1 is OK");
    }

}
