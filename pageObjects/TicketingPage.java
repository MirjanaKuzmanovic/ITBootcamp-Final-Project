package finalProject.pageObjects;

import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TicketingPage {
    private WebDriver driver;

    @FindBy(xpath = "//*[@id='ticketing']/div/div/section/main/div[2]/div/table/tr[1]/td[10]/div")
    WebElement row1Seat1;

    @FindBy(xpath = "//*[@id='ticketing']/div/div/section/main/div[2]/div/table/tr[1]/td[7]/div")
    WebElement row1Seat4;

    @FindBy(xpath = "//*[@id='ticketing']/div/div/section/main/div[2]/div/table/tr[2]/td[13]/div")
    WebElement row2Seat1;

    @FindBy(xpath = "//*[@id='ticketing']/div/div/section/main/div[2]/div/table/tr[2]/td[10]/div")
    WebElement row2Seat4;

    @FindBy(xpath = "//*[@id='ticketing']/div/div/section/footer/div[1]/div[1]/span/span[1]")
    WebElement rowSelectedMessage;

    @FindBy(xpath = "//*[@id='ticketing']/div/div/section/footer/div[1]/div[1]/span/span[2]")
    WebElement seatsSelectedMessage;

    @FindBy(xpath = "//*[@id='ticketing']/div/div/section/footer/div[2]/div[2]/div/button[1]")
    WebElement reserveButton;

    @FindBy(xpath = "//*[@id='ticketing']/div/div/section/footer/div[3]/div[2]/div/button[1]")
    WebElement reserveButtonNotLoggedIn;

    @FindBy(xpath = "//*[@id='ticketing']/div/div/section/footer/div/div[2]/div/button[1]")
    WebElement reserveButtonBefore;

    @FindBy(xpath = "//*[@id='ticketing']/div/div/section/main/div[3]/div[2]/label")
    WebElement termsAgreementCheckbox;

    @FindBy(xpath = "//*[@id='ticketing']/div/div/section/footer/div/div[2]/button")
    WebElement finalConfirmationButton;

    @FindBy(xpath = "//*[@id='ticketing']/div/div/section/main/div[2]/h2")
    WebElement confirmationMessage;

    @FindBy(xpath = "//*[@id='ticketing']/div/div/section/footer/button")
    WebElement deleteReservationButton;

    @FindBy(xpath = "//*[@id='ticketing']/div[2]/div/div/div[2]/button[2]")
    WebElement deleteReservationYesButton;

    @FindBy(xpath = "//*[@id='ticketing']/div[2]/div/div/div[2]/button[1]")
    WebElement deleteReservationNoButton;

    @FindBy(xpath = "//*[@id='my-tickets']/div[2]/div/div[1]")
    WebElement reservationSuccessfullyDeletedMessage;

    public TicketingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectRow1Seats1And2() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", row1Seat1);
        row1Seat1.click();
    }

    public void selectRow1Seats4And5() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", row1Seat4);
        row1Seat4.click();
    }

    public void selectRow2Seats1And2() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", row2Seat1);
        row2Seat1.click();
    }

    public void selectRow2Seats4And5() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", row2Seat4);
        row2Seat4.click();
    }

    public String getRowSelectedMessage() {
        return rowSelectedMessage.getText();
    }

    public String getSeatsSelectedMessage() {
        return seatsSelectedMessage.getText();
    }

    public void reserveSelectedTickets() {
        reserveButton.click();
        termsAgreementCheckbox.click();
        finalConfirmationButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(confirmationMessage));
    }

    public void reserveSelectedTicketsNoTerms() {
        reserveButton.click();
        finalConfirmationButton.click();
    }

    public String getConfirmationMessageText() {
        return confirmationMessage.getText();
    }

    public WebElement getReserveButtonBefore() {
        return reserveButtonBefore;
    }

    public String getErrorMessageReservationButton() {
        System.out.println(reserveButtonNotLoggedIn.getAttribute("data-tooltip"));
        return reserveButtonNotLoggedIn.getAttribute("data-tooltip");
    }

    public void deleteReservedTicket() {
        deleteReservationButton.click();
        deleteReservationYesButton.click();
    }

    public String getDeletionConfirmationMessageColor() {
        return reservationSuccessfullyDeletedMessage.getCssValue("color");
    }

    public String getTermsAgreementColor() {
        return termsAgreementCheckbox.getCssValue("color");
    }

}
