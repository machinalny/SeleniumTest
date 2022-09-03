package pl.coderslab.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HotelMainPage {

    private WebDriver driver;

    public HotelMainPage(WebDriver driver){
        this.driver = driver;
    }

    public void clickSignIn(){
        WebElement signInButton = this.driver.findElement(By.className("user_login"));
        signInButton.click();
    }

    public void searchForHotelRoomsBetweenDates(String hotelName, String fromDate, String toDate) {
        WebElement selectHotelDropdown = this.driver.findElement(By.xpath("/html//form[@id='search_hotel_block_form']//button[@type='button']"));
        selectHotelDropdown.click();

//        WebElement hotel = this.driver.findElement(By.xpath("//form[@id='search_hotel_block_form']//ul[@class='dropdown-menu hotel_dropdown_ul']/li"));
//        hotel.click();

        List<WebElement> hotels = this.driver.findElements(By.xpath("//form[@id='search_hotel_block_form']//ul[@class='dropdown-menu hotel_dropdown_ul']"));
        for(WebElement hotel: hotels){
            if (hotel.getText().equals(hotelName)){
                hotel.click();
                break;
            }
        }

        WebElement checkInDateInput = this.driver.findElement(By.id("check_in_time"));
        WebElement checkOutDateInput = this.driver.findElement(By.id("check_out_time"));
        checkInDateInput.clear();
        checkInDateInput.sendKeys(fromDate);
        checkOutDateInput.clear();
        checkOutDateInput.sendKeys(toDate);

        WebElement searchNowButton = this.driver.findElement(By.id("search_room_submit"));
        searchNowButton.click();
    }
}
