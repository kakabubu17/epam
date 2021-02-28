package tests;

import PageObjects.MainPage;
import PageObjects.VideoListPage;
import PageObjects.VideoPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VideoCategoryFilter extends TestBase {

    private static Logger logger = LogManager.getLogger(ViewFutureEventsTest.class);

    VideoListPage videoListPage;
    VideoPage videoPage;
    MainPage mainPage;

    @Test
    public void test() throws InterruptedException {

        mainPage = openMainPage();
        videoListPage = mainPage.openVideoListPage();
        videoListPage.moreFiltersClick();
        videoListPage.setFilter("Category", "QA"); //нельзя нажать на родительский Testing
        videoListPage.setFilter("Location", "Belarus");
        videoListPage.setLanguageFilter("ENGLISH");
        //videoListPage.checkVideoLinks();
        Assertions.assertEquals(true, videoListPage.checkVideoLinks());
        logger.info("test6 is OK");
    }

}
