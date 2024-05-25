package demo;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class wrapperMethods {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    public wrapperMethods(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
    }

    public void click(WebElement element) {
        try {
            
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            Thread.sleep(2500);
            
        } catch (Exception e) {
            System.out.println("Exception occurred while clicking: " + e.getMessage());
        }
    }

    public void sendKeys(WebElement element, String keysToSend) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOf(element));
            element.sendKeys(keysToSend);
        } catch (Exception e) {
            System.out.println("Exception occurred while sending keys: " + e.getMessage());
        }
    }

    public WebElement findElement(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));           
            return element;
        } catch (Exception e) {
            System.out.println("Exception occurred while finding element: " + locator + " - " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void navigateTo(String url) {
        try {
            driver.get(url);
            Thread.sleep(5000);
            //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
            //wait.until(ExpectedConditions.urlToBe("https://www.youtube.com/"));
        } catch (Exception e) {
            System.out.println("Exception occurred while navigating to the URL: " + e.getMessage());
        }
    }

    public void scrollBy(int x, int y) {
        try {
            js.executeScript("window.scrollBy(arguments[0], arguments[1]);", x, y);
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Exception occurred while scrolling: " + e.getMessage());
        }
    }

    public void scrollIntoView(WebElement element) {
        try {
            js.executeScript("arguments[0].scrollIntoView(true);", element);
            System.out.println("Scrolled into view: " + element);
        } catch (Exception e) {
            System.out.println("Exception occurred while scrolling into view: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public long k_M_Views(String viewsText) {

        // Removes "views" suffix if any
        viewsText = viewsText.replace(" views", "");
        // Remove commas if any
        viewsText = viewsText.replace(",", "");
        // Trim any befor and after whitespace
        viewsText = viewsText.trim();


        long viewsCount = 0;
    
        if (viewsText.endsWith("K")) {
            viewsText = viewsText.replace("K", "");
            //K means thousands(1000)
            viewsCount = (long) (Double.parseDouble(viewsText) * 1000);
        } else if (viewsText.endsWith("M")) {
            viewsText = viewsText.replace("M", "");
            //M means millions(1000000)
            viewsCount = (long) (Double.parseDouble(viewsText) * 1000000);
        } else {
            viewsCount = Long.parseLong(viewsText);
        }
    
        return viewsCount;
    }
}