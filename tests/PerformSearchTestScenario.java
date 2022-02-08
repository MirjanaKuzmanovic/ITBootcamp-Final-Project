package finalProject.tests;

import finalProject.pageObjects.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class PerformSearchTestScenario {
    private WebDriver driver;
    private HomePage homePage;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Storage\\IT Bootcamp\\chromedriver_win32\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
    }

    /**
     * Test ID: TC_012
     * Test Perform Search Happy Path:
     * Test Description: Verify the functionality of the search bar
     *
     * Test Steps:
     * 1. Go to https://www.cineplexx.rs
     * 2. Enter the search text into the search bar
     * 3. Press the enter key
     * 4. Assert that there is an article title with the text 'ONLINE KUPOVINA JE STIGLA U CINEPLEXX!'
     *
     * Test Data:
     * Search text = 'online kupovina je stigla u cineplexx'
     */

    @Test
    public void testPerformSearchHappyPath() {
        driver.get("https://www.cineplexx.rs/");
        driver.manage().window().maximize();

        homePage = new HomePage(driver);
        homePage.performSearch("online kupovina je stigla u cineplexx");
        Assert.assertTrue(homePage.getSearchResultText().contains("ONLINE KUPOVINA JE STIGLA U CINEPLEXX!"),
                "The search result should include the text 'ONLINE KUPOVINA JE STIGLA U CINEPLEXX!'");
    }

    /**
     * Test ID: TC_013
     * Test Perform Search Unhappy Path:
     * Test Description: Verify that there are no changes on the website after performing a search with no parameters
     *
     * Test Steps:
     * 1. Go to https://www.cineplexx.rs
     * 2. Click the search confirmation button
     * 3. Assert that the home page is still open
     */

    @Test
    public void testPerformSearchUnhappyPathNoParameters() {
        driver.get("https://www.cineplexx.rs/");
        driver.manage().window().maximize();

        homePage = new HomePage(driver);
        homePage.clickSearchButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.cineplexx.rs/",
                "The current URL should be 'https://www.cineplexx.rs/'");
    }

    @AfterMethod
    public void closeBrowser() {
        driver.close();
    }

}
