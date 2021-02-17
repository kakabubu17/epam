package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EventPage extends AbstractPage {

    public EventPage (WebDriver driver) { super(driver); }

    private String registrationBtn = "";
    private String dateTime = "";
    private String program = "";

    public boolean registrationBtnIsDisplayed() { return driver.findElement(By.xpath(registrationBtn)).isDisplayed();  }
    public boolean dateTimeIsDisplayed() { return driver.findElement(By.xpath(dateTime)).isDisplayed();  }
    public boolean programIsDisplayed() { return driver.findElement(By.xpath(program)).isDisplayed();  }



}
