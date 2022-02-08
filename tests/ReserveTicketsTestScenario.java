package finalProject.tests;

import finalProject.pageObjects.HomePage;
import finalProject.pageObjects.ProgramPage;
import finalProject.pageObjects.TicketingPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ReserveTicketsTestScenario {
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
     * Test ID: TC_007
     * Test Ticket Reservation Happy Path:
     * Test Description: Verify the reservation of movie tickets functionality
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
     * 10. From the available seat display, choose the seat number 1 in row 1 which will select two adjacent seats
     * 11. Assert that the row message below the seat display says '1'
     * 12. Assert that the seat message below the seat display says '1-2'
     * 13. Click the 'Rezervisati' button
     * 14. Click the checkbox next to the label saying
     * 'Klikom na ovo polje, izjavljujem da sam pročitao/la i prihvatam uslove korišćenja internet stranice.'
     * 15. Click the 'Potvrditi rezervaciju' button
     * 16. Wait for the reservation process to complete
     * 17. Assert that the headline says 'ZAHVALJUJEMO NA VAŠOJ REZERVACIJI!'
     *
     * Test Data:
     * Email address = xorera5340@host1s.com
     * Password = Pass123&%
     */

    @Test
    public void testReserveTicketsHappyPath() {
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
        ticketingPage.selectRow1Seats1And2();

        Assert.assertEquals(ticketingPage.getRowSelectedMessage(), "1", "The row number should be '1'");
        Assert.assertEquals(ticketingPage.getSeatsSelectedMessage(), "1-2", "Seat numbers should be '1-2'");

        ticketingPage.reserveSelectedTickets();

        Assert.assertEquals(ticketingPage.getConfirmationMessageText(), "ZAHVALJUJEMO NA VAŠOJ REZERVACIJI!",
                "There should be a reservation confirmation message saying 'ZAHVALJUJEMO NA VAŠOJ REZERVACIJI!'");
    }

    /**
     * Test ID: TC_008
     * Test Ticket Reservation Unhappy Path:
     * Test Description: Verify that the tickets cannot be reserved when no seat is selected
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
     * 10. Assert that the 'Rezervisati' button is not clickable
     *
     * Test Data:
     * Email address = xorera5340@host1s.com
     * Password = Pass123&%
     */

    @Test
    public void testReserveTicketsUnhappyPathNoSeatSelected() {
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
        Assert.assertFalse(ticketingPage.getReserveButtonBefore().isDisplayed(), "The 'Reserve' button should not be clickable.");
    }

    /**
     * Test ID: TC_009
     * Test Ticket Reservation Unhappy Path:
     * Test Description: Verify that the tickets cannot be reserved when the user is not logged in
     *
     * Test Steps:
     * 1. Go to https://www.cineplexx.rs
     * 2. Click the 'Omiljeni bioskop' dropdown at the top of the page
     * 3. Select 'Cineplexx Promenada' from the dropdown menu
     * 4. Click the 'OK' button next to the chosen cinema
     * 5. Hover over the 'FILMOVI' link in the navigation bar
     * 6. Click the 'REPERTOAR BIOSKOPA' link in the dropdown menu
     * 7. Select the option containing the expression 'Sutra' in the 'Datum' menu
     * 8. Click the first available projection of the first movie on the list
     * 9. From the available seat display, choose the seat number 4 in row 1 which will select two adjacent seats
     * 10. Assert that the row message below the seat display says '1'
     * 11. Assert that the seat message below the seat display says '4-5'
     * 12. Assert that the 'Rezervisati' button is not clickable
     */

    @Test
    public void testReserveTicketsUnhappyPathUserNotLoggedIn() {
        driver.get("https://www.cineplexx.rs/");
        driver.manage().window().maximize();

        homePage = new HomePage(driver);
        homePage.chooseFavoriteCinema();
        homePage.navigateToProgram();

        programPage = new ProgramPage(driver);
        programPage.selectFirstProjectionForTomorrow();

        ticketingPage = new TicketingPage(driver);
        ticketingPage.selectRow1Seats4And5();

        Assert.assertEquals(ticketingPage.getRowSelectedMessage(), "1", "The row number should be '1'");
        Assert.assertEquals(ticketingPage.getSeatsSelectedMessage(), "4-5", "Seat numbers should be '4-5'");
        Assert.assertEquals(ticketingPage.getErrorMessageReservationButton(),
                "Samo prijavljeni korisnici mogu rezervisati bioskopske ulaznice",
                "The 'Reserve' button should show the message 'Samo prijavljeni korisnici mogu rezervisati bioskopske ulaznice' when hovered.");
    }

    @AfterMethod
    public void closeBrowser() {
        driver.close();
    }
}
