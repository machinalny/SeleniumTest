package pl.coderslab.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HotelCreateAnAccountPage {

    private WebDriver driver;
    @FindBy(id = "customer_firstname")
    private WebElement customerFirstNameInput;

    @FindBy(id = "customer_lastname")
    private WebElement customerLastNameInput;

    @FindBy(id = "passwd")
    private WebElement customerPassword;

    @FindBy(id = "submitAccount")
    private WebElement submitButton;

    public HotelCreateAnAccountPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void fillFormAndSubmit(String name, String lastName, String password) {
        customerFirstNameInput.clear();
        customerFirstNameInput.sendKeys(name);
        customerLastNameInput.clear();
        customerLastNameInput.sendKeys(lastName);

        customerPassword.clear();
        customerPassword.sendKeys(password);

        submitButton.click();
    }

}
