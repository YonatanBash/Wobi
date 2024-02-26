package pageObjects;

import java.time.Duration;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    WebDriver driver;
    JavascriptExecutor js;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    public void fillText(WebElement el, String text) {
        waiting(300);
        js.executeScript("arguments[0].setAttribute('style', 'background-color:turquoise; border: 1px solid green;');",
                el);
        try {
            el.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
        el.sendKeys(text);
    }

    public void click(WebElement el) {
        waiting(500);
        js.executeScript("arguments[0].setAttribute('style', 'background-color: lightblue; border: 1px solid green;');", el);
        el.click();
    }

    public String getText(WebElement el) {
        waiting(300);
        js.executeScript("arguments[0].setAttribute('style', 'background-color: pink; border: 1px solid white;');",
                el);
        return el.getText();
    }

    public void waiting(long mil) {
        try {
            Thread.sleep(mil);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public void moveToElement(WebElement el) {
        Actions actions = new Actions(driver);
        actions.moveToElement(el).build().perform();
    }

    public void waitforElementToBeClickable(WebElement el) {
        WebDriverWait waitForClickable = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitForClickable.until(ExpectedConditions.elementToBeClickable(el));
    }

    public void waitForVisibilityOfElement(WebElement el) {
        WebDriverWait waitForVisiblity = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitForVisiblity.until(ExpectedConditions.visibilityOf(el));
    }

    public String getTitle() {
        return driver.getTitle();
    }

    protected void highlightElement(WebElement element, String color) {
        // keep the old style to change it back
        String originalStyle = element.getAttribute("style");
        String newStyle = "border: 1px solid " + color + ";" + originalStyle;
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Change the style
        js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '"
                + newStyle + "');},0);", element);

        // Change the style back after few Miliseconds
        js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '"
                + originalStyle + "');},400);", element);

    }

    public void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 500);");
    }

}
