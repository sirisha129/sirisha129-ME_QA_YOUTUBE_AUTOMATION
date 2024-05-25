package demo;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.*;

public class TestCases {

    static ChromeDriver driver;
    static wrapperMethods utility;
    static JavascriptExecutor js;

    @BeforeSuite(alwaysRun = true)
    public static void driverSetUp() {
        System.out.println("Initializing : TestCases");
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        utility = new wrapperMethods(driver);
        js = (JavascriptExecutor) driver;
    }

    @Test(priority = 1, enabled = true)

    public void testOn_YouTube_About() throws InterruptedException {
        System.out.println("Test case 1: Go to YouTube.com, Assert the URL and Click on About and print the message .");
        try {
            utility.navigateTo("https://www.YouTube.com/");

            // Asserts the URL to be "YouTube.com"
            Assert.assertEquals(driver.getCurrentUrl(), "https://www.youtube.com/", "URL does not match");
            utility.scrollBy(0, 1000);
            // Click on about in the sidebar
            WebElement aboutClick = utility.findElement(By.xpath("//a[contains(text(), 'About')]"));
            utility.click(aboutClick);

            utility.scrollBy(0, 500);
            // Header message on About page
            WebElement aboutMessage_H1 = driver.findElement(By.xpath("//*[@id='content']//descendant::h1"));
            // paragraph message on About Page
            WebElement aboutMessage_P = driver.findElement(By.xpath("//*[@id='content']//descendant::p[1]"));

            String actual_Message = aboutMessage_H1.getText() + "  " + aboutMessage_P.getText();
            // print the About message
            System.out.println(" About Page Displayed Message : " + actual_Message);
            System.out.println("End Test case: Asserted the URL and Printed About Message");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test case failed due to exception: " + e.getMessage());
        }

    }

    @Test(priority = 2, enabled = true)

    public void testOn_YouTube_Films() throws InterruptedException {
        System.out.println("Test case 2: Go to YouTube.com,Films Tab,TopSelling Section and apply softAsserts.");
        try {
            SoftAssert softAssert = new SoftAssert();

            utility.navigateTo("https://www.YouTube.com/");

            utility.scrollBy(0, 600);
            Actions actions = new Actions(driver);
            // click on Films Tab
            WebElement films_Tab = driver.findElement(By.xpath("//yt-formatted-string[text()='Movies']"));
            actions.moveToElement(films_Tab).perform();
            utility.click(films_Tab);

            utility.scrollBy(0, 600);

            WebElement topSelling_nextButton = driver
                    .findElement(By.xpath("(//div[@id='right-arrow']//descendant::button[@aria-label='Next'])"));
            for (int i = 0; i < 3; i++) {
                Thread.sleep(1000);
                utility.click(topSelling_nextButton);
                Thread.sleep(1000);
            }

            List<WebElement> topSellingItems_LastScroll = topSelling_nextButton
                    .findElements(By.xpath("(//*[@id='items']/ytd-grid-movie-renderer)"));
            WebElement last_Item = topSellingItems_LastScroll.get(topSellingItems_LastScroll.size() - 1);

            WebElement movie_Type = last_Item.findElement(By.xpath("(//span[contains(text(),'Comedy ')])[3]"));
            String text_Movie = movie_Type.getText();

            softAssert.assertTrue(text_Movie.contains("Comedy") || text_Movie.contains("Animation"),
                    "The movie is neither 'Comedy' nor 'Animation'");

            WebElement genere_Type = last_Item
                    .findElement(By.xpath("(//*[@id=\"items\"]//ytd-badge-supported-renderer/div[2]/p)[16]"));
            String text_Genere = genere_Type.getText();

            softAssert.assertTrue(text_Genere.contains("A"), "The movie is not marked as 'A' ");
            softAssert.assertAll();

            System.out.println("End Test case: Movies tab assertions done successfully");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test case failed due to exception: " + e.getMessage());
        }

    }

    @Test(priority = 3, enabled = true)

    public void testOn_YouTube_Music() throws InterruptedException {
        System.out.println("Test case 3: Go to YouTube.com,Music Tab,in 1st Section and apply softAsserts.");
        try {
            SoftAssert softAssert = new SoftAssert();

            utility.navigateTo("https://www.YouTube.com/");
            utility.scrollBy(0, 800);

            Actions actions = new Actions(driver);
            // click on Music Tab

            WebElement music_Tab = driver.findElement(By.xpath("(//yt-formatted-string[text()='Music'])"));
            actions.moveToElement(music_Tab).perform();
            utility.click(music_Tab);

            // scroll to reach till 1st section list
            utility.scrollBy(0, 500);

            WebElement section1_nextButton = driver
                    .findElement(By.xpath("(//div[@id='right-arrow']//descendant::button[@aria-label='Next'])"));
            for (int i = 0; i < 3; i++) {
                Thread.sleep(1000);
                utility.click(section1_nextButton);
                Thread.sleep(1000);
            }

            // Print the name of the playlist
            WebElement playlistName = driver.findElement(By.xpath("//h3[contains(text(),'Bollywood Dance Hitlist')]"));
            System.out.println("Playlist Name: " + playlistName.getText());

            // Soft Assert on whether the number of tracks listed is <= 50
            List<WebElement> trackCounts = driver.findElements(
                    By.xpath("(//p[contains(text(),'The choicest')]//following::p[contains(text(),'50 tra')])[1]"));
            for (WebElement trackCount : trackCounts) {
                int numTracks = Integer.parseInt(trackCount.getText().split(" ")[0]);
                softAssert.assertTrue(numTracks <= 50, "Number of tracks is more than 50");
            }

            softAssert.assertAll();

            System.out.println("End Test case: Music tab assertions applied successfully");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test case failed due to exception: " + e.getMessage());
        }

    }

    @Test(priority = 4, enabled = true)

    public void testOn_YouTube_News() throws InterruptedException {

        System.out.println("Test case 4: Go to YouTube.com,News Tab,in 1st Section and apply softAsserts.");

        try {
            utility.navigateTo("https://www.YouTube.com/");
            Thread.sleep(5000);

            Actions actions = new Actions(driver);
            // click on Music Tab
            WebElement news_Tab = driver.findElement(By.xpath("//yt-formatted-string[text()='News']"));
            actions.moveToElement(news_Tab).perform();
            utility.click(news_Tab);

            // scroll to reach till 1st section list
            utility.scrollBy(0, 500);
            // Print the title of the Latest News Posts
            WebElement title_Latest = driver
                    .findElement(By.xpath("//span[@id='title'][contains(text(),'Latest news posts')]"));
            System.out.println("Title printed as : " + title_Latest.getText());

            // first 3 contents of "Latest News Post"
            List<WebElement> body_Contents = driver.findElements(
                    By.xpath("//div[@id='content']//ytd-post-renderer//following-sibling::div//*[@id='body']/div[1]"));
            for (int i = 0; i < 3; i++) {
                for (WebElement body : body_Contents) {
                    String content = body.getText();
                    System.out.println("Content of the " + (i + 1) + " News:" + content);
                    break;
                }

            }
            // first 3 contents of "Latest News Post"
            int totalLikes = 0;
            List<WebElement> likes_Total = driver.findElements(By.xpath("//span[@id='vote-count-middle']"));
            for (int i = 0; i < 3; i++) {
                WebElement totalLikesElement = likes_Total.get(i);
                String likesText = totalLikesElement.getText();
    
                // Convert likesText to a numeric value
                int likesCount = 0;
                if (likesText.endsWith("K")) {
                    likesCount = (int) (Double.parseDouble(likesText.replace("K", "")) * 1000);
                } else {
                    likesCount = Integer.parseInt(likesText);
                }
    
                totalLikes += likesCount;

            }
            System.out.println("Total Likes on the first 3 News Posts: " + totalLikes);

            System.out.println("End Test case: News tab contents printed successfully");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test case failed due to exception: " + e.getMessage());
        }

    }
    // Search for each of the items given in this excel sheet (Read them using
    // Apache POI), and keep scrolling till the sum of each video’s views reach 10
    // Cr.

    @Test(priority = 5, enabled = true, dataProvider = "searchNames", dataProviderClass = ExcelDataProvider.class)

    public void testOn_YouTube_ExcelSheetCount(String searchName) throws InterruptedException {

        System.out.println("Test case 5: Go to YouTube.com, read and print excel data.");
        utility.navigateTo("https://www.youtube.com");
    
        try {
            // Search an item
            WebElement searchbar = driver.findElement(By.name("search_query"));
            utility.sendKeys(searchbar, searchName);
    
            WebElement searchIcon = driver.findElement(By.id("search-icon-legacy"));
            utility.click(searchIcon);
    
            boolean noMoreResults = false;
            long totalViews = 0;
    
            // 10 Crores = 100,000,000 in numeric
            while (totalViews < 100000000 && !noMoreResults) {
                List<WebElement> viewsOverall = driver.findElements(By.xpath("//div[@id='metadata-line']/span[1]"));
    
                for (WebElement element : viewsOverall) {
                    String viewsText = element.getText();
                    if (viewsText.contains("views")) {
                        long viewsCount = utility.k_M_Views(viewsText);
                        totalViews += viewsCount;

                        js.executeScript("window.scrollBy(0, 1000);");
                        Thread.sleep(2000);
                        
                        if (totalViews >= 100000000) {
                            break;
                        }
                    }
                }
               
            }
           
               /*  // Check if the 'No more results' element is visible
                try {
                    WebElement noMoreResultsElement = driver.findElement(By.xpath("//*[contains(text(),'No more results')]"));
                    if (noMoreResultsElement.isDisplayed()) {
                        noMoreResults = true;
                    }
                } catch (NoSuchElementException e) {
                    // Element not found, continue scrolling
                    js.executeScript("window.scrollBy(0, 1000);");
                    Thread.sleep(2000);
                }*/
        
            System.out.println("Search Term: " + searchName + ", Total Views: " + totalViews);

            Assert.assertTrue(totalViews >= 100000000, "Total views did not reach 10 Crores.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        System.out.println("End Test case: searched 10CR views ");
    }


    @AfterSuite(alwaysRun = true)
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}