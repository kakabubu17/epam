package tests;
import PageObjects.*;
import main.WebDriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import java.text.ParseException;

public class ViewFutureEventsTest extends TestBase {
    protected static WebDriver driver;
    private static Logger logger = LogManager.getLogger(ViewFutureEventsTest.class);

    MainPage mainPage;
    EventsPage eventsPage;



    @Test
    public void test() throws InterruptedException, ParseException {
        mainPage = new MainPage(driver);
        mainPage.open(url);
        logger.info("open page");

        eventsPage = mainPage.openEventsPage();
        logger.info("open events page");
        Thread.sleep(5000); //убрать
        eventsPage.upcomingEventsClick();
        logger.info("open upcoming events");

        Assertions.assertEquals(true, eventsPage.upcomingEventsIsUpcoming());
        Assertions.assertEquals(eventsPage.upcomingEventsTabCount(), eventsPage.upcomingEventsCount());
    }


    @AfterAll
    public static void close()
    {
        if (driver != null) driver.quit();
        logger.info("close driver");
    }
}
