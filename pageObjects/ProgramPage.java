package finalProject.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProgramPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//*[@id='s2id_autogen11']")
    WebElement dateSelect;

    @FindBy(xpath = "//*[@id='select2-drop']/ul/li[2]")
    WebElement tomorrowSelect;

    @FindBy(xpath = "//body/div[@id='root']/div[4]/div[1]/div[5]/form[1]/div[4]/div[1]/a[1]")
    WebElement genreSelect;

    @FindBy(xpath = "//*[@id='select2-drop']/ul/li[10]")
    WebElement dramaSelect;

    @FindBy(xpath = "//*[@id='root']/div[4]/div/div[5]/div[2]/div[3]/div[1]/div/div[2]/div/div")
    WebElement firstProjection;

    public ProgramPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectFirstProjectionForTomorrow() {
        dateSelect.click();
        tomorrowSelect.click();
        Actions actions = new Actions(driver);
        actions.pause(Duration.ofSeconds(1)).build().perform();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstProjection);
        firstProjection.click();
    }

    public void selectDateTomorrow() {
        dateSelect.click();
        tomorrowSelect.click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='root']/div[4]/div/div[5]/div[2]/div[3]/div[1]/div/div[1]/div/h2/a")));
    }

    public void selectGenreDrama() {
        genreSelect.click();
        dramaSelect.click();
        Actions actions = new Actions(driver);
        actions.pause(Duration.ofSeconds(2)).build().perform();
    }

    public boolean movieDescriptionsContainDrama() {
        boolean containsDrama = true;
        List<WebElement> movieDescriptions = driver.findElements(By.className("starBoxSmall"));
        for(WebElement we : movieDescriptions) {
            if (!we.getText().contains("drama")) {
                containsDrama = false;
                break;
            }
        }
        return containsDrama;
    }

}
