package pl.coderslab.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HotelSearchRoomResultsPage {

    private final WebDriver driver;

    @FindBy(xpath = "//div[@id='category_data_cont']/div/div[@class='row']")
    private List<WebElement> availableRooms;

    public HotelSearchRoomResultsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public Integer getAvailableRoomNumber() {
        return availableRooms.size();
    }

}