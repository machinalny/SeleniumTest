package pl.coderslab.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HotelLoginPage {

    private WebDriver driver;

    private By registerEmailInputSelector = By.id("email_create");

    private By signInEmailInputSelector = By.id("email");

    private By signInPasswordInputSelector = By.id("passwd");

    private By signInButtonSelector = By.id("SubmitLogin");

    public HotelLoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void createAnAccountWithEmail(String email){
        WebElement registerEmailInput = this.driver.findElement(registerEmailInputSelector);
        registerEmailInput.clear();
        registerEmailInput.sendKeys(email);
        registerEmailInput.submit();
    }


    public void signInWithCredentials(String email, String password) {
        WebElement signInEmailAddressInput = this.driver.findElement(signInEmailInputSelector);
        signInEmailAddressInput.clear();
        signInEmailAddressInput.sendKeys(email);

        WebElement signInPasswordInput = this.driver.findElement(signInPasswordInputSelector);
        signInPasswordInput.clear();
        signInPasswordInput.sendKeys(password);

        WebElement signInButton = this.driver.findElement(signInButtonSelector);
        signInButton.click();


    }
}
