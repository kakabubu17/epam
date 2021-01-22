package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {

    protected WebDriver driver;
    protected WebDriverWait wait = new WebDriverWait(driver, 10);

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickElement(String element)
    {
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(element))));
        driver.findElement(By.xpath(element)).click();
    }

    public void sendText(String text, String element)
    {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(element))));
        driver.findElement(By.xpath(element)).clear();
        driver.findElement(By.xpath(element)).sendKeys(text);
    }

}
