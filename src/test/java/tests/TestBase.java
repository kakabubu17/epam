package tests;

import PageObjects.EventListPage;
import PageObjects.MainPage;
import main.WebDriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;

public class TestBase {

    protected String url = "https://events.epam.com/";
    protected static WebDriver driver;
    private static Logger logger = LogManager.getLogger(ViewFutureEventsTest.class);

    MainPage mainPage;
    EventListPage eventListPage;

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

    protected EventListPage openUpcomingEvents() throws InterruptedException {

        mainPage = openMainPage();
        eventListPage = mainPage.openEventsPage();
        logger.info("open events page");
        Thread.sleep(5000); //убрать
        eventListPage.upcomingEventsClick();
        logger.info("open upcoming events");
        return eventListPage;
    }
    protected EventListPage openPastEvents() throws InterruptedException {

        mainPage = openMainPage();
        eventListPage = openEventPage();
        eventListPage.openPastEvents();
        logger.info("open past events");
        return eventListPage;
    }

    protected MainPage openMainPage() {
        mainPage = new MainPage(driver);
        mainPage.open(url);
        logger.info("open page");
        return mainPage;
    }

    protected EventListPage openEventPage() throws InterruptedException {

        eventListPage = mainPage.openEventsPage();
        logger.info("open events page");
        Thread.sleep(5000); //убрать
        return eventListPage;
    }



    @AfterAll
    public static void close()
    {
        if (driver != null) driver.quit();
        logger.info("close driver");
    }

}
