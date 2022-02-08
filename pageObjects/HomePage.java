package finalProject.pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;

    @FindBy(xpath = "//*[@id='root']/div[3]/div/div/div/ul/li[2]")
    WebElement myTicketsNavigationButton;

    @FindBy(xpath = "//*[@id='root']/div[3]/div/div/div/ul/li[2]/a")
    WebElement registerButton;

    @FindBy(xpath = "//*[@id='root']/div[3]/div/div/div/ul/li[3]")
    WebElement loginNavigationButton;

    @FindBy(xpath = "//*[@id='root']/div[3]/div/div/div/ul/li[3]/div/div/form/div[2]/input")
    WebElement emailInput;

    @FindBy(xpath = "//*[@id='root']/div[3]/div/div/div/ul/li[3]/div/div/form/div[3]/input")
    WebElement passwordInput;

    @FindBy(xpath = "//*[@id='root']/div[3]/div/div/div/ul/li[3]/div/div/form/button")
    WebElement logInConfirmationButton;

    @FindBy(xpath = "//*[@id='root']/div[3]/div/div/div/ul/li[3]/a")
    WebElement firstNameNavigation;

    @FindBy(xpath = "//*[@id='root']/div[3]/div/div/div/ul/li[3]/div/div/form/div[1]")
    WebElement unsuccessfulLoginError;

    @FindBy(xpath = "//*[@id='main-nav']/div[1]/ul/li[1]")
    WebElement filmoviLinkNavigationBar;

    @FindBy(xpath = "//*[@id='main-nav']/div[1]/ul/li[1]/div/div/ul/li[1]")
    WebElement repertoarBioskopaLink;

    @FindBy(xpath = "//*[@id='root']/div[2]/div/form/div/div/a")
    WebElement favoriteCinemaSelect;

    @FindBy(xpath = "//*[@id='root']/div[2]/div/div/div/div/div/div/div/ul/li[7]")
    WebElement favoriteCinemaCineplexxPromenada;

    @FindBy(xpath = "//*[@id='root']/div[2]/div/form/div/a")
    WebElement favoriteCinemaOKButton;

    @FindBy(xpath = "//*[@id='root']/div[2]")
    WebElement favoriteCinemaSection;

    @FindBy(xpath = "//*[@id='main-nav']/div[1]/form/div/input[1]")
    WebElement searchBar;

    @FindBy(xpath = "//*[@id='main-nav']/div[1]/form/div/input[2]")
    WebElement searchSubmitButton;

    @FindBy(xpath = "//*[@id='root']/div[4]/div/div[5]/div[2]/div/div/div/div[1]")
    WebElement searchResult;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickRegisterButton() {
        registerButton.click();
    }

    public void logIn(String email, String password) {
        loginNavigationButton.click();
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        logInConfirmationButton.click();
    }

    public String getFirstNameNavigationText() {
        System.out.println(firstNameNavigation.getText());
        return firstNameNavigation.getText();
    }

    public  WebElement getFirstNameNavigation() {
        return firstNameNavigation;
    }

    public WebElement getUnsuccessfulLoginError() {
        return unsuccessfulLoginError;
    }

    public void navigateToProgram() {
        Actions actions = new Actions(driver);
        actions.moveToElement(filmoviLinkNavigationBar).build().perform();
        actions.pause(Duration.ofSeconds(1)).build().perform();
        repertoarBioskopaLink.click();
    }

    public void chooseFavoriteCinema() {
        favoriteCinemaSelect.click();
        favoriteCinemaCineplexxPromenada.click();
        favoriteCinemaOKButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until((ExpectedConditions.invisibilityOf(favoriteCinemaSection)));
    }

    public void performSearch(String searchText) {
        searchBar.sendKeys(searchText);
        searchBar.sendKeys(Keys.ENTER);
    }

    public String getSearchResultText() {
        return searchResult.getText();
    }

    public void clickSearchButton() {
        searchSubmitButton.click();
    }

    public void clickMyTicketsNavigationButton() {
        myTicketsNavigationButton.click();
    }
}
