package pl.coderslab.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HotelSearchRoomResultsPage {

    private final WebDriver driver;

    @FindBy(xpath = "//div[@id='category_data_cont']/div/div[@class='row']")
    private List<WebElement> availableRooms;

    @FindBy(className = "ajax_add_to_cart_button")
    private List<WebElement> bookRoomButtons;

    @FindBy(xpath = "/html//div[@id='layer_cart']//a[@title='Proceed to checkout']/span")
    private WebElement proceedToCheckoutButton;

    public HotelSearchRoomResultsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public Integer getAvailableRoomNumber() {
        return availableRooms.size();
    }

    public void bookAnyRoom() {
        WebElement firstRoomFromList = availableRooms.get(0);
        firstRoomFromList.findElement(By.className("ajax_add_to_cart_button")).click();
        bookRoomButtons.get(0).click();
        proceedToCheckoutButton.click();
    }
}