package tests;

import PageObjects.EventsPage;
import PageObjects.MainPage;
import main.WebDriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.text.ParseException;

public class ViewEventCardTest extends TestBase{

    protected static WebDriver driver;
    private static Logger logger = LogManager.getLogger(ViewFutureEventsTest.class);

    MainPage mainPage;
    EventsPage eventsPage;

    @BeforeAll
    public static void setUp()
    {
        String opt = "headless";
        driver = WebDriverFactory.create("chrome", null);
        //opt = System.getProperty("option").trim().toLowerCase();
        //WebDriverFactory.create(System.getProperty("browser").trim().toLowerCase(), opt);
        driver.manage().window().maximize();
        logger.info("driver initialized");

    }

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
        Assertions.assertEquals(true, eventsPage.placeIsDisplayed());
        Assertions.assertEquals(true, eventsPage.langIsDisplayed());
        Assertions.assertEquals(true, eventsPage.nameIsDisplayed());
        Assertions.assertEquals(true, eventsPage.dateIsDisplayed());
        Assertions.assertEquals(true, eventsPage.speakersIsDisplayed());
        Assertions.assertEquals(true, eventsPage.isElementsLocatedRight());

        //TODO: логировать проверки??
    }

    @AfterAll
    public static void close()
    {
        if (driver != null) driver.quit();
        logger.info("close driver");
    }
}
