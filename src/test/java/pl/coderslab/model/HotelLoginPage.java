package pl.coderslab.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HotelLoginPage {

    private WebDriver driver;

    @FindBy(id = "email_create")
    private WebElement registerEmailInput;
    @FindBy(id = "email")
    private WebElement signInEmailAddressInput;
    @FindBy(id = "passwd")
    private WebElement signInPasswordInput;
    @FindBy(id = "SubmitLogin")
    private WebElement signInButton;

    public HotelLoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void createAnAccountWithEmail(String email) {
        registerEmailInput.clear();
        registerEmailInput.sendKeys(email);
        registerEmailInput.submit();
    }


    public void signInWithCredentials(String email, String password) {
        signInEmailAddressInput.clear();
        signInEmailAddressInput.sendKeys(email);

        signInPasswordInput.clear();
        signInPasswordInput.sendKeys(password);

        signInButton.click();

    }
}
