package tests;

import PageObjects.EventsPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

public class ValidateDateOfUpcomingEvents extends TestBase{

    private static Logger logger = LogManager.getLogger(ViewFutureEventsTest.class);

    EventsPage eventsPage;

    @Test
    public void test() throws InterruptedException, ParseException {
        eventsPage = openEventsPage();

        Assertions.assertEquals(true, eventsPage.upcomingEventsIsUpcoming());
        //TODO: сделать проверку дат This week, но таких мероприятий не было
        logger.info("test3 is OK");
    }
}
