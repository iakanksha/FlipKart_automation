package demo.WrapperActions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WrapperActionsClass {
    public static void sendKeysWrapper(WebDriver driver, By locator, String textToEnter){
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            WebElement textBox = driver.findElement(locator);

            // Actions actions = new Actions(driver);
            // actions.moveToElement(textBox).click().keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.DELETE).sendKeys(textToEnter).build().perform();
            
            textBox.sendKeys(textToEnter);
            textBox.sendKeys(Keys.ENTER);
        }catch (Exception e) {
            System.out.println("Exception Occured! " + e.getMessage());
        }

    }

    public static void clickElementWrapper(WebDriver driver, By locator){
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            WebElement button = driver.findElement(locator);
            button.click();
        }catch (Exception e) {
            System.out.println("Exception Occured! " + e.getMessage());
        }
    }

    public static List<WebElement> listOfElements(WebDriver driver, By locator){
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            List<WebElement> elements = driver.findElements(locator);
            return elements;
        }catch (Exception e) {
            System.out.println("Exception Occured! " + e.getMessage());
        }
        return null;

    }
}
