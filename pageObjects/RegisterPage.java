package finalProject.pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
    private WebDriver driver;

    @FindBy(id = "nickname")
    WebElement nicknameInput;

    @FindBy(id = "email")
    WebElement emailInput;

    @FindBy(id = "password")
    WebElement passwordInput;

    @FindBy(id = "repeatpassword")
    WebElement repeatPasswordInput;

    @FindBy(xpath = "//*[@id='register-form']/div[2]/div/div[1]/div[1]/div[1]/label")
    WebElement femaleRadioButton;

    @FindBy(id = "male")
    WebElement maleRadioButton;

    @FindBy(id = "firstname")
    WebElement firstNameInput;

    @FindBy(id = "lastname")
    WebElement lastNameInput;

    @FindBy(xpath = "//div[@id='s2id_autogen1']")
    WebElement dayOfBirthSelect;

    @FindBy(xpath = "//*[@id='select2-drop']/ul/li[4]")
    WebElement dayOfBirthOption4;

    @FindBy(xpath = "//div[@id='s2id_autogen3']")
    WebElement monthOfBirthSelect;

    @FindBy(xpath = "//*[@id='select2-drop']/ul/li[12]")
    WebElement monthOfBirthOption12;

    @FindBy(xpath = "//div[@id='s2id_autogen5']")
    WebElement yearOfBirthSelect;

    @FindBy(xpath = "//*[@id='select2-drop']/ul/li[30]")
    WebElement yearOfBirthOption1990;

    @FindBy(id = "s2id_favoriteCinema1")
    WebElement favoriteCinemaSelect;

    @FindBy(xpath = "//*[@id='select2-drop']/ul/li[7]")
    WebElement favoriteCinemaCineplexxPromenada;

    @FindBy(xpath = "//*[@id='register-form']/div[5]/div/div/div[1]/label")
    WebElement acceptPrivacyPolicyCheckbox;

    @FindBy(xpath = "//*[@id='register-form']/div[5]/div/div/div[2]/label")
    WebElement acceptTermsAndConditionCheckbox;

    @FindBy(xpath = "//*[@id='register-form']/div[5]/div/div/button")
    WebElement registerButton;

    @FindBy(xpath = "//*[@id='root']/div[4]/div/div[4]/div/p")
    WebElement confirmationMessage;

    @FindBy(xpath = "//*[@id='register-form']/div[1]/div/div/div[1]/span")
    WebElement nicknameErrorMessage;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void register(String nickname, String email, String password, String gender, String firstName,
                         String lastName) {
        nicknameInput.sendKeys(nickname);
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        repeatPasswordInput.sendKeys(password);

        if (gender.equalsIgnoreCase("female")) femaleRadioButton.click();
        else maleRadioButton.click();

        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);

        dayOfBirthSelect.click();
        dayOfBirthOption4.click();

        monthOfBirthSelect.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", monthOfBirthOption12);
        monthOfBirthOption12.click();

        yearOfBirthSelect.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", yearOfBirthOption1990);
        yearOfBirthOption1990.click();

        favoriteCinemaSelect.click();
        favoriteCinemaCineplexxPromenada.click();

        acceptPrivacyPolicyCheckbox.click();
        acceptTermsAndConditionCheckbox.click();
        registerButton.click();
    }

    public void register(String email, String password, String gender, String firstName,
                         String lastName) {
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        repeatPasswordInput.sendKeys(password);

        if (gender.equalsIgnoreCase("female")) femaleRadioButton.click();
        else maleRadioButton.click();

        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);

        dayOfBirthSelect.click();
        dayOfBirthOption4.click();

        monthOfBirthSelect.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", monthOfBirthOption12);
        monthOfBirthOption12.click();

        yearOfBirthSelect.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", yearOfBirthOption1990);
        yearOfBirthOption1990.click();

        favoriteCinemaSelect.click();
        favoriteCinemaCineplexxPromenada.click();

        acceptPrivacyPolicyCheckbox.click();
        acceptTermsAndConditionCheckbox.click();
        registerButton.click();
    }

    public String getConfirmationMessageText() {
        return confirmationMessage.getText();
    }

    public String getNicknameErrorMessage() {
        return nicknameErrorMessage.getText();
    }


}
