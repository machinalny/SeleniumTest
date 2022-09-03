package pl.coderslab.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HotelSearchRoomResultsPage {

    private final WebDriver driver;
    private final By availableRoomsSelector = By.xpath("//div[@id='category_data_cont']/div/div[@class='row']");

    public HotelSearchRoomResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public Integer getAvailableRoomSize() {
        List<WebElement> availableRooms = this.driver.findElements(availableRoomsSelector);
        return availableRooms.size();
    }

}