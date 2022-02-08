package finalProject.tests;

import finalProject.pageObjects.HomePage;
import finalProject.pageObjects.ProgramPage;
import finalProject.pageObjects.TicketingPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class DeleteReservedTicketsTestScenario {
    private WebDriver driver;
    private HomePage homePage;
    private ProgramPage programPage;
    private TicketingPage ticketingPage;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Storage\\IT Bootcamp\\chromedriver_win32\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
    }

    /**
     * Test ID: TC_010
     * Test Delete Ticket Reservation Happy Path:
     * Test Description: Verify that a movie ticket reservation can be deleted
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
     * 9. Click the first available projection of the first movie on the list
     * 10. From the available seat display, choose the seat number 1 in row 2 which will select two adjacent seats
     * 11. Assert that the row message below the seat display says '1'
     * 12. Assert that the seat message below the seat display says '1-2'
     * 13. Click the 'Rezervisati' button
     * 14. Click the checkbox next to the label saying
     * 'Klikom na ovo polje, izjavljujem da sam pročitao/la i prihvatam uslove korišćenja internet stranice.'
     * 15. Click the 'Potvrditi rezervaciju' button
     * 16. Wait for the reservation process to complete
     * 17. Assert that the headline says 'ZAHVALJUJEMO NA VAŠOJ REZERVACIJI!'
     * 18. Click the 'Poništi rezervaciju' button at the bottom of the page
     * 19. Click the 'Da' button on the popup to confirm the cancellation
     * 20. Assert that there is a green message confirming the cancellation
     *
     * Test Data:
     * Email address = xorera5340@host1s.com
     * Password = Pass123&%
     */

    @Test
    public void testDeleteReservedTicketsHappyPath() {
        driver.get("https://www.cineplexx.rs/");
        driver.manage().window().maximize();

        homePage = new HomePage(driver);
        homePage.logIn("xorera5340@host1s.com", "Pass123&%");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.textToBePresentInElement(homePage.getFirstNameNavigation(), "Jane"));

        homePage.navigateToProgram();

        programPage = new ProgramPage(driver);
        programPage.selectFirstProjectionForTomorrow();

        ticketingPage = new TicketingPage(driver);
        ticketingPage.selectRow2Seats1And2();

        Assert.assertEquals(ticketingPage.getRowSelectedMessage(), "2", "The row number should be '2'");
        Assert.assertEquals(ticketingPage.getSeatsSelectedMessage(), "1-2", "Seat numbers should be '1-2'");

        ticketingPage.reserveSelectedTickets();

        Assert.assertEquals(ticketingPage.getConfirmationMessageText(), "ZAHVALJUJEMO NA VAŠOJ REZERVACIJI!",
                "There should be a reservation confirmation message saying 'ZAHVALJUJEMO NA VAŠOJ REZERVACIJI!'");

        ticketingPage.deleteReservedTicket();
        String hexCodeSuccess = Color.fromString(ticketingPage.getDeletionConfirmationMessageColor()).asHex();
        Assert.assertEquals(hexCodeSuccess, "#6ead08",
                "The color of the successful deletion message should be '#6ead08'");
    }

    /**
     * Test ID: TC_011
     * Test Delete Ticket Reservation Unhappy Path:
     * Test Description: Verify that a movie ticket reservation cannot be deleted without agreeing to terms of use
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
     * 9. Click the first available projection of the first movie on the list
     * 10. From the available seat display, choose the seat number 4 in row 2 which will select two adjacent seats
     * 11. Assert that the row message below the seat display says '2'
     * 12. Assert that the seat message below the seat display says '4-5'
     * 13. Click the 'Rezervisati' button
     * 14. Click the 'Potvrditi rezervaciju' button
     * 15. Assert that the color of the terms of service text is #d5171b
     *
     * Test Data:
     * Email address = xorera5340@host1s.com
     * Password = Pass123&%
     */

    @Test
    public void testDeleteReservedTicketsUnhappyPath() {
        driver.get("https://www.cineplexx.rs/");
        driver.manage().window().maximize();

        homePage = new HomePage(driver);
        homePage.logIn("xorera5340@host1s.com", "Pass123&%");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.textToBePresentInElement(homePage.getFirstNameNavigation(), "Jane"));

        homePage.navigateToProgram();

        programPage = new ProgramPage(driver);
        programPage.selectFirstProjectionForTomorrow();

        ticketingPage = new TicketingPage(driver);
        ticketingPage.selectRow2Seats4And5();

        Assert.assertEquals(ticketingPage.getRowSelectedMessage(), "2", "The row number should be '2'");
        Assert.assertEquals(ticketingPage.getSeatsSelectedMessage(), "4-5", "Seat numbers should be '4-5'");

        ticketingPage.reserveSelectedTicketsNoTerms();

        String tosError = Color.fromString(ticketingPage.getTermsAgreementColor()).asHex();
        Assert.assertEquals(tosError, "#d5171b",
                "The color of the terms of service text should be '#d5171b'");
    }

    @AfterMethod
    public void closeBrowser() {
        driver.close();
    }

}
