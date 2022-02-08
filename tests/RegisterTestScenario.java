package finalProject.tests;

import finalProject.pageObjects.HomePage;
import finalProject.pageObjects.RegisterPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class RegisterTestScenario {
    private WebDriver driver;
    private HomePage homePage;
    private RegisterPage registerPage;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Storage\\IT Bootcamp\\chromedriver_win32\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
    }

    /**
     * Test ID: TC_001
     * Test Login Happy Path:
     * Test Description: Verify the 'Register' functionality with valid input
     *
     * Test Steps:
     * 1. Go to https://www.cineplexx.rs
     * 2. Click the Prijava button in the top navigation bar
     * 3. Enter the nickname in the 'Nadimak' field
     * 4. Enter the email address in the 'E-Mail adresa' field
     * 5. Enter the password in the 'Lozinka' field
     * 6. Enter the same password in the 'Ponovite lozinku' field
     * 7. Click the radio button with the label 'Ženski'
     * 8. Enter the first name in the 'Ime' field
     * 9. Enter the last name in the 'Prezime' field
     * 10. Select the day of birth from the DD dropdown menu in the 'Datum rođenja' section
     * 11. Select the month of birth from the MM dropdown menu in the 'Datum rođenja' section
     * 12. Select the year of birth from the GGGG dropdown menu in the 'Datum rođenja' section
     * 13. Select 'Cineplexx Promenada' from the 'Omiljeni bioskop' dropdown
     * 14. Click the checkbox with the label
     * 'Klikom na ovo polje, izjavljujem da sam pročitao/la i prihvatam politiku privatnosti i
     * opšte uslove zaštite podataka o ličnosti i da pristajem na obradu podataka o ličnosti u skladu sa istom.'
     * 15. Click the checkbox with the label
     * 'Klikom na ovo polje, izjavljujem da sam pročitao/la i prihvatam uslove korišćenja internet stranice.'
     * 16. Click the 'REGISTRUJ SE' button
     * 17. Assert that the text on the confirmation website contains the line 'Uspešno ste se registrovali!'
     *
     * Test Data:
     * Nickname = usernick129
     * Email address = myemail129@email.com
     * Password = Pass123&%
     * First name = Jane
     * Last name = Doe
     * Day of birth = 04
     * Month of birth = 02
     * Year of birth = 1990
     */

    @Test
    public void testRegisterHappyPath() {
        driver.get("https://www.cineplexx.rs/");
        driver.manage().window().maximize();

        homePage = new HomePage(driver);
        homePage.clickRegisterButton();

        registerPage = new RegisterPage(driver);
        registerPage.register("usernick1222", "xorera5340@host1s.com", "Pass123&%", "female", "Jane", "Doe");
        String confirmationText = registerPage.getConfirmationMessageText();
        Assert.assertTrue(confirmationText.contains("Uspešno ste se registrovali!"), "The confimration message should include the following text: 'Uspešno ste se registrovali!'");
    }

    /**
     * Test ID: TC_002
     * Test Login Unhappy Path:
     * Test Description: Test the 'Register' functionality without the required nickname in the 'Nadimak' field
     *
     * Test Steps:
     * 1. Go to https://www.cineplexx.rs
     * 2. Click the 'Registracija' button in the top navigation bar
     * 3. Enter the email address in the 'E-Mail adresa' field
     * 4. Enter the password in the 'Lozinka' field
     * 5. Enter the same password in the 'Ponovite lozinku' field
     * 6. Click the radio button with the label 'Ženski'
     * 7. Enter the first name in the 'Ime' field
     * 8. Enter the last name in the 'Prezime' field
     * 9. Select the day of birth from the DD dropdown menu in the 'Datum rođenja' section
     * 10. Select the month of birth from the MM dropdown menu in the 'Datum rođenja' section
     * 11. Select the year of birth from the GGGG dropdown menu in the 'Datum rođenja' section
     * 12. Select 'Cineplexx Promenada' from the 'Omiljeni bioskop' dropdown
     * 13. Click the checkbox with the label
     * 'Klikom na ovo polje, izjavljujem da sam pročitao/la i prihvatam politiku privatnosti i
     * opšte uslove zaštite podataka o ličnosti i da pristajem na obradu podataka o ličnosti u skladu sa istom.'
     * 14. Click the checkbox with the label
     * 'Klikom na ovo polje, izjavljujem da sam pročitao/la i prihvatam uslove korišćenja internet stranice.'
     * 15. Click the 'REGISTRUJ SE' button
     * 16. Assert that the warning text 'MOLIMO DA UNESETE NADIMAK. (MIN. 3 ZNAKA)' appears next to the 'Nadimak' field
     *
     * Test Data:
     * Email address = myemail127@email.com
     * Password = Pass123&%
     * First name = Jane
     * Last name = Doe
     * Day of birth = 31
     * Month of birth = 12
     * Year of birth = 1990
     */

    @Test
    public void testRegisterUnhappyPathNoNickname() {
        driver.get("https://www.cineplexx.rs/");
        driver.manage().window().maximize();

        homePage = new HomePage(driver);
        homePage.clickRegisterButton();

        registerPage = new RegisterPage(driver);
        registerPage.register("myemail121@email.com", "Pass123&%", "female", "Jane", "Doe");
        String errorMessageText = registerPage.getNicknameErrorMessage();
        Assert.assertEquals(errorMessageText, "MOLIMO DA UNESETE NADIMAK. (MIN. 3 ZNAKA)", "The error message should say 'MOLIMO DA UNESETE NADIMAK. (MIN. 3 ZNAKA)'");
    }

    @AfterMethod
    public void closeBrowser() {
        driver.close();
    }

}
