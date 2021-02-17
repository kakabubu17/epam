package tests;

import PageObjects.EventsPage;
import PageObjects.MainPage;
import main.WebDriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.text.ParseException;

public class TestBase {

    protected String url = "https://events.epam.com/";
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

    protected EventsPage openUpcomingEvents() throws InterruptedException {

        mainPage = openMainPage();
        eventsPage = mainPage.openEventsPage();
        logger.info("open events page");
        Thread.sleep(5000); //убрать
        eventsPage.upcomingEventsClick();
        logger.info("open upcoming events");
        return eventsPage;
    }
    protected EventsPage openPastEvents() throws InterruptedException {

        mainPage = openMainPage();
        eventsPage = openEventPage();
        eventsPage.openPastEvents();
        logger.info("open past events");
        return eventsPage;
    }

    protected MainPage openMainPage() {
        mainPage = new MainPage(driver);
        mainPage.open(url);
        logger.info("open page");
        return mainPage;
    }

    protected EventsPage openEventPage() throws InterruptedException {

        eventsPage = mainPage.openEventsPage();
        logger.info("open events page");
        Thread.sleep(5000); //убрать
        return eventsPage;
    }



    @AfterAll
    public static void close()
    {
        if (driver != null) driver.quit();
        logger.info("close driver");
    }

}
