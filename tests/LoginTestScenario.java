package finalProject.tests;

import finalProject.pageObjects.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTestScenario {
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
     * Test ID: TC_003
     * Test Login Happy Path:
     * Test Description: Verify the login is successful with a valid email address and a valid password
     *
     * Test Steps:
     * 1. Go to https://www.cineplexx.rs
     * 2. Click the 'Prijava' button in the top navigation bar
     * 3. Enter the valid email address in the 'E-Mail adresa' field of the dropdown
     * 4. Enter the valid password in the 'Lozinka' field of the dropdown
     * 5. Click the 'Prijava' button
     * 6. Assert that the first name of the user is visible in the top right corner of the website
     *
     * Test Data:
     * Email address = xorera5340@host1s.com
     * Password = Pass123&%
     */

    @Test
    public void testLoginHappyPath() {
        driver.get("https://www.cineplexx.rs/");
        driver.manage().window().maximize();

        homePage = new HomePage(driver);
        homePage.logIn("xorera5340@host1s.com", "Pass123&%");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.textToBePresentInElement(homePage.getFirstNameNavigation(), "Jane"));

        String firstName = homePage.getFirstNameNavigationText();
        Assert.assertEquals(firstName, "Jane", "The name 'Jane' should be displayed");
    }

    /**
     * Test ID: TC_004
     * Test Login Unhappy Path:
     * Test Description: Verify the login is unsuccessful with an invalid email address and a valid password
     *
     * Test Steps:
     * 1. Go to https://www.cineplexx.rs
     * 2. Click the 'Prijava' button in the top navigation bar
     * 3. Enter the invalid email address in the 'E-Mail adresa' field of the dropdown
     * 4. Enter the valid password in the 'Lozinka' field of the dropdown
     * 5. Click the 'Prijava' button
     * 6. Assert that there is an error message above the 'E-mail adresa' label saying 'Neispravan e-mail i/ili lozinka.'
     *
     * Test Data:
     * Email address = testemail@email.com
     * Password = Pass123&%
     */

    @Test
    public void testLoginUnhappyPathInvalidEmail() {
        driver.get("https://www.cineplexx.rs/");
        driver.manage().window().maximize();

        homePage = new HomePage(driver);
        homePage.logIn("testemail@email.com", "Pass123&%");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(homePage.getUnsuccessfulLoginError()));

        Assert.assertTrue(homePage.getUnsuccessfulLoginError().isDisplayed(),
                "There should be an error message saying 'Neispravan e-mail i/ili lozinka.' above the email input field.");
    }

    /**
     * Test ID: TC_005
     * Test Login Unhappy Path:
     * Test Description: Verify the login is unsuccessful with a valid email address and an invalid password
     *
     * Test Steps:
     * 1. Go to https://www.cineplexx.rs
     * 2. Click the 'Prijava' button in the top navigation bar
     * 3. Enter the valid email address in the 'E-Mail adresa' field of the dropdown
     * 4. Enter the invalid password in the 'Lozinka' field of the dropdown
     * 5. Click the 'Prijava' button
     * 6. Assert that there is an error message above the 'E-mail adresa' label saying 'Neispravan e-mail i/ili lozinka.'
     *
     * Test Data:
     * Email address = xorera5340@host1s.com
     * Password = password
     */

    @Test
    public void testLoginUnhappyPathInvalidPassword() {
        driver.get("https://www.cineplexx.rs/");
        driver.manage().window().maximize();

        homePage = new HomePage(driver);
        homePage.logIn("xorera5340@host1s.com", "password");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(homePage.getUnsuccessfulLoginError()));

        Assert.assertTrue(homePage.getUnsuccessfulLoginError().isDisplayed(),
                "There should be an error message saying 'Neispravan e-mail i/ili lozinka.' above the email input field");
    }

    /**
     * Test ID: TC_006
     * Test Login Unhappy Path:
     * Test Description: Verify the login is unsuccessful with a valid email address and an invalid password
     *
     * Test Steps:
     * 1. Go to https://www.cineplexx.rs
     * 2. Click the 'Prijava' button in the top navigation bar
     * 3. Enter the invalid email address in the 'E-Mail adresa' field of the dropdown
     * 4. Enter the invalid password in the 'Lozinka' field of the dropdown
     * 5. Click the 'Prijava' button
     * 6. Assert that there is an error message above the 'E-mail adresa' label saying 'Neispravan e-mail i/ili lozinka.'
     *
     * Test Data:
     * Email address = testemail@email.com
     * Password = password
     */

    @Test
    public void testLoginUnhappyPathInvalidEmailInvalidPassword() {
        driver.get("https://www.cineplexx.rs/");
        driver.manage().window().maximize();

        homePage = new HomePage(driver);
        homePage.logIn("testemail@email.com", "password");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(homePage.getUnsuccessfulLoginError()));

        Assert.assertTrue(homePage.getUnsuccessfulLoginError().isDisplayed(),
                "There should be an error message saying 'Neispravan e-mail i/ili lozinka.' above the email input field");
    }


    @AfterMethod
    public void closeBrowser() {
        driver.close();
    }

}
