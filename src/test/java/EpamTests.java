import PageObjects.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

public class EpamTests {
    protected static WebDriver driver;
    private String url = "https://events.epam.com/";
    private static Logger logger = LogManager.getLogger(EpamTests.class);

    MainPage mainPage;
    EventsPage eventsPage;


    @BeforeAll
    public static void setUp()
    {
        String opt = "headless";
        //opt = System.getProperty("option").trim().toLowerCase();
        driver = WebDriverFactory.create("chrome", null);
        //WebDriverFactory.create(System.getProperty("browser").trim().toLowerCase(), opt);
        driver.manage().window().maximize();
        logger.info("driver initialized");

    }

    @Test
    public void test1() {
        mainPage = new MainPage(driver);
        mainPage.open(url);
        logger.info("open page");
        eventsPage = mainPage.openEventsPage();
        logger.info("open events page");
    }


    @AfterAll
    public static void close()
    {
        if (driver != null) driver.quit();
        logger.info("close driver");
    }
}
