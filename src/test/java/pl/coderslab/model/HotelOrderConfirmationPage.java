package pl.coderslab.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HotelOrderConfirmationPage {

    @FindBy(className = "alert-success")
    private WebElement successAlert;

    public HotelOrderConfirmationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String getAlertInformation(){
        return successAlert.getText();
    }
}