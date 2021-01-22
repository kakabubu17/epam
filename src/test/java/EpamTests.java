import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.gen5.api.AfterAll;
import org.junit.gen5.api.BeforeAll;
import org.openqa.selenium.WebDriver;

public class EpamTests {
    protected static WebDriver driver;

    private Logger logger = LogManager.getLogger(EpamTests.class);

    @BeforeAll
    public void setUp()
    {
        String opt = "headless";
        //opt = System.getProperty("option").trim().toLowerCase();
        driver = WebDriverFactory.create("chrome", null);
        //WebDriverFactory.create(System.getProperty("browser").trim().toLowerCase(), opt);
        driver.manage().window().maximize();
        logger.info("driver initialized");

    }


    @AfterAll
    public void close()
    {
        if (driver != null) driver.quit();
        logger.info("close driver");
    }
}
