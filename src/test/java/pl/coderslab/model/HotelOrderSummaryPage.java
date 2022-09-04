package pl.coderslab.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HotelOrderSummaryPage {

    @FindBy(xpath = "//p[@id='cart_navigation']//span")
    private WebElement confirmOrderButton;

    @FindBy(css = "htl-reservation-page-content")
    private List<WebElement> paymentConfirmationMessages;

    public HotelOrderSummaryPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void confirmOrder(){
        confirmOrderButton.click();
    }
}
