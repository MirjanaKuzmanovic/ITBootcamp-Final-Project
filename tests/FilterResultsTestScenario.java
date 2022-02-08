package finalProject.tests;

import finalProject.pageObjects.HomePage;
import finalProject.pageObjects.ProgramPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class FilterResultsTestScenario {
    private WebDriver driver;
    private HomePage homePage;
    private ProgramPage programPage;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Storage\\IT Bootcamp\\chromedriver_win32\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
    }

    /**
     * Test ID: TC_014
     * Test Filter Results Happy Path:
     * Test Description: Verify the genre filter functionality on a program page
     *
     * Test Steps:
     * 1. Go to https://www.cineplexx.rs
     * 2. Click the 'Prijava' button in the top navigation bar
     * 3. Enter the valid email address in the 'E-Mail adresa' field of the dropdown
     * 4. Enter the valid password in the 'Lozinka' field of the dropdown
     * 5. Click the 'Prijava' button
     * 6. Hover over the 'FILMOVI' link in the navigation bar
     * 7. Click the 'REPERTOAR BIOSKOPA' link in the dropdown menu
     * 8. Select the option containing the expression 'Sutra' in the 'Datum' menu
     * 9. Select the 'drama' option from the 'Žanr' menu
     * 10. Assert that all results contain the 'drama' genre
     *
     * Test Data:
     * Email address = xorera5340@host1s.com
     * Password = Pass123&%
     */

    @Test
    public void testGenreFilterHappyPath() {
        driver.get("https://www.cineplexx.rs/");
        driver.manage().window().maximize();

        homePage = new HomePage(driver);
        homePage.logIn("xorera5340@host1s.com", "Pass123&%");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.textToBePresentInElement(homePage.getFirstNameNavigation(), "Jane"));

        homePage.navigateToProgram();

        programPage = new ProgramPage(driver);
        programPage.selectDateTomorrow();
        programPage.selectGenreDrama();

        Assert.assertTrue(programPage.movieDescriptionsContainDrama(), "All movies should contain the genre 'drama'");
    }


    @AfterMethod
    public void closeBrowser() {
        driver.close();
    }

}
