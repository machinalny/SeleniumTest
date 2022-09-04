package pl.coderslab.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import pl.coderslab.dto.Gender;
import pl.coderslab.dto.RoomRow;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HotelQuickOrderPage {

    @FindBy(className = "table_body")
    private List<WebElement> roomRows;

    @FindBy(xpath = "/html//form[@id='advanced-payment']/div[1]//input[@name='payment_type']")
    private WebElement fullPaymentRadioBox;

    @FindBy(xpath = "/html//form[@id='advanced-payment']/div[2]//input[@name='payment_type']")
    private WebElement partialPaymentRadioBox;

    @FindBy(id = "id_gender1")
    private WebElement mrTitleRadioInput;

    @FindBy(id = "id_gender2")
    private WebElement mrsTitleRadioInput;

    @FindBy(id = "customer_firstname")
    private WebElement firstNameTextInput;

    @FindBy(id = "customer_lastname")
    private WebElement lastNameTextInput;

    @FindBy(id = "email")
    private WebElement emailTextInput;

    @FindBy(id = "passwd")
    private WebElement passwordTextInput;

    @FindBy(id = "days")
    private WebElement dayOfBirthDropdown;

    @FindBy(id = "months")
    private WebElement monthOfBirthDropdown;

    @FindBy(id = "years")
    private WebElement yearOfBirthDropdown;

    @FindBy(id = "newsletter")
    private WebElement newsletterCheckboxInput;

    @FindBy(id = "optin")
    private WebElement specialOfferCheckboxInput;

    @FindBy(id = "firstname")
    private WebElement addressFirstNameTextInput;

    @FindBy(id = "lastname")
    private WebElement addressLastNameTextInput;

    @FindBy(id = "company")
    private WebElement addressCompanyTextInput;

    @FindBy(id = "address1")
    private WebElement addressTextInput;

    @FindBy(id = "city")
    private WebElement addressCityTextInput;

    @FindBy(id = "id_state")
    private WebElement addressStateDropdown;

    @FindBy(id = "postcode")
    private WebElement addressPostCodeTextInput;

    @FindBy(id = "id_country")
    private WebElement addressCountryDropdown;

    @FindBy(id = "other")
    private WebElement additionalInformationTextInput;

    @FindBy(id = "phone")
    private WebElement homePhoneTextInput;

    @FindBy(id = "phone_mobile")
    private WebElement mobilePhoneTextInput;

    @FindBy(id = "submitAccount")
    private WebElement saveButton;

    @FindBy(xpath = "/html//input[@id='cgv']")
    private WebElement agreeToConditionsCheckBox;

    @FindBy(className = "bankwire")
    private WebElement payByBankWireButton;

    @FindBy(className = "cheque")
    private WebElement payByCheckButton;


    public HotelQuickOrderPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


    public boolean isThereRoomInBasket() {
        return roomRows.size() > 0;
    }

    private RoomRow mapRoomRow(WebElement rowElement) {
        RoomRow roomRow = new RoomRow();
        roomRow.setRoomName(rowElement.findElement(By.cssSelector("td:nth-of-type(2) > p")).getText());
        roomRow.setRoomCapacity(rowElement.findElement(By.cssSelector("td:nth-of-type(3) > p")).getText());
        roomRow.setUnitPrice(rowElement.findElement(By.cssSelector("td:nth-of-type(4) > p")).getText());
        roomRow.setNumberOfRooms(rowElement.findElement(By.cssSelector("td:nth-of-type(5) > p")).getText());
        roomRow.setCheckInDate(rowElement.findElement(By.cssSelector("td:nth-of-type(6) > p")).getText());
        roomRow.setCheckOutDate(rowElement.findElement(By.cssSelector("td:nth-of-type(7) > p")).getText());
        roomRow.setTotalPrice(rowElement.findElement(By.cssSelector("td:nth-of-type(9) > p")).getText());
        return roomRow;

    }

    public List<RoomRow> getRoomsInBasket() {
        List<RoomRow> roomRowsMapped = new ArrayList<>();
        for (WebElement roomRow: roomRows){
            RoomRow roomRowMapped = this.mapRoomRow(roomRow);
            roomRowsMapped.add(roomRowMapped);
        }
        return roomRowsMapped;
    }


    public void orderRoomWithNewCustomer(String email,
                                         String password,
                                         String firstName,
                                         String lastName,
                                         Gender gender,
                                         LocalDate dateOfBirth,
                                         String address,
                                         String postalCode,
                                         String city,
                                         String mobilePhone) {
//        switch (gender) {
//            case MALE -> mrTitleRadioInput.click();
//            case FEMALE -> mrsTitleRadioInput.click();
//        }

        if (gender == Gender.MALE){
            mrTitleRadioInput.click();
        }
        if (gender == Gender.FEMALE){
            mrsTitleRadioInput.click();
        }

        emailTextInput.clear();
        emailTextInput.sendKeys(email);

        passwordTextInput.clear();
        passwordTextInput.sendKeys(password);

        firstNameTextInput.clear();
        firstNameTextInput.sendKeys(firstName);

        lastNameTextInput.clear();
        lastNameTextInput.sendKeys(lastName);

        Select dayOfBirthSelect = new Select(dayOfBirthDropdown);
        dayOfBirthSelect.selectByValue(String.valueOf(dateOfBirth.getDayOfMonth()));
        Select monthOfBirthSelect = new Select(monthOfBirthDropdown);
        monthOfBirthSelect.selectByValue(String.valueOf(dateOfBirth.getMonthValue()));
        Select yearOfBirthSelect = new Select(yearOfBirthDropdown);
        yearOfBirthSelect.selectByValue(String.valueOf(dateOfBirth.getYear()));

        addressTextInput.clear();
        addressTextInput.sendKeys(address);

        addressPostCodeTextInput.clear();
        addressPostCodeTextInput.sendKeys(postalCode);

        addressCityTextInput.clear();
        addressCityTextInput.sendKeys(city);

        mobilePhoneTextInput.clear();
        mobilePhoneTextInput.sendKeys(mobilePhone);

        agreeToConditionsCheckBox.click();

        saveButton.click();

        payByBankWireButton.click();
    }

}
