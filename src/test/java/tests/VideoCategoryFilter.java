package tests;

import PageObjects.EventListPage;
import PageObjects.MainPage;
import PageObjects.VideoPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

public class VideoCategoryFilter extends TestBase {

    private static Logger logger = LogManager.getLogger(ViewFutureEventsTest.class);

    VideoPage videoPage;
    MainPage mainPage;

    @Test
    public void test() {

        mainPage = openMainPage();
        videoPage = mainPage.openVideoPage();
        videoPage.moreFiltersClick();
        // videoPage.setFilter("Category", "Testing"); нельзя нажать на родительский Testing
        videoPage.setFilter("Location", "Belarus");
        videoPage.setLanguageFilter("ENGLISH");

        logger.info("test6 is OK");
    }

}
