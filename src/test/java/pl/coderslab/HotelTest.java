package pl.coderslab;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pl.coderslab.dto.Gender;
import pl.coderslab.model.*;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HotelTest {

    private final static String PERMANENT_LOGIN = "mytest@cl-test.com";
    private final static String PERMANENT_PASSWORD = "mytestPassword";
    private WebDriver driver;

    @Before
    public void setUp() {
        //        Ustaw gdzie jest chromedriver -> STEROWNIK
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver.exe");
        //        Otworz przegladarke
        this.driver = new ChromeDriver();
        //        Jesli test nie przechodzi poprawnie, to pewnie za wolno laduje sie strona -> Dodaj czekanie.
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        this.driver.get("https://hotel-testlab.coderslab.pl/en/");

    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testCreateNewUser() {

        String email = new Random().nextInt(100000000) + "TEA26@test.com";
        String expectedAlertText = "Your account has been created.";

        HotelMainPage hotelMainPage = new HotelMainPage(this.driver);
        hotelMainPage.clickSignIn();

        HotelLoginPage hotelLoginPage = new HotelLoginPage(this.driver);
        hotelLoginPage.createAnAccountWithEmail(email);

        HotelCreateAnAccountPage hotelCreateAnAccountPage = new HotelCreateAnAccountPage(this.driver);
        hotelCreateAnAccountPage.fillFormAndSubmit("Janusz", "Januszewski", "12345");

        HotelMyAccountPage hotelMyAccountPage = new HotelMyAccountPage(driver);
        String alertText = hotelMyAccountPage.getAlertText();
        assertEquals(expectedAlertText, alertText);

        String pageTitle = hotelMyAccountPage.getPageTitle();
        assertEquals("My account - MyBooking", pageTitle);

    }

    @Test
    public void testSearchAnyHotel() {
        HotelMainPage hotelMainPage = new HotelMainPage(this.driver);
        hotelMainPage.clickSignIn();

        HotelLoginPage hotelLoginPage = new HotelLoginPage(this.driver);
        hotelLoginPage.signInWithCredentials(PERMANENT_LOGIN, PERMANENT_PASSWORD);

        HotelMyAccountPage hotelMyAccountPage = new HotelMyAccountPage(this.driver);
        hotelMyAccountPage.goToHomePage();

        hotelMainPage.searchForHotelRoomsBetweenDates("The Hotel Prime", "22-09-2022", "29-09-2022");

        HotelSearchRoomResultsPage searchRoomResultsPage = new HotelSearchRoomResultsPage(this.driver);
        assertTrue(searchRoomResultsPage.getAvailableRoomNumber() > 0);

    }

    @Test
    public void testBookRoomInHotel() {
        String email = new Random().nextInt(100000000) + "TEA26@test.com";

        HotelMainPage hotelMainPage = new HotelMainPage(driver);
        hotelMainPage.searchForHotelRoomsBetweenDates("The Hotel Prime", "22-09-2022", "29-09-2022");
        HotelSearchRoomResultsPage searchRoomResultsPage = new HotelSearchRoomResultsPage(this.driver);
        searchRoomResultsPage.bookAnyRoom();

        HotelQuickOrderPage quickOrderPage = new HotelQuickOrderPage(driver);
        assertTrue(quickOrderPage.isThereRoomInBasket());
        System.out.println(quickOrderPage.getRoomsInBasket());
        quickOrderPage.orderRoomWithNewCustomer(email, "passwd", "Jan", "Kowalski", Gender.FEMALE, LocalDate.of(1992, 11, 7), "Ulica", "12345", "Januszowo", "12345667");

        HotelOrderSummaryPage orderSummaryPage = new HotelOrderSummaryPage(driver);
        orderSummaryPage.confirmOrder();
        HotelOrderConfirmationPage orderConfirmationPage = new HotelOrderConfirmationPage(driver);
        assertEquals("Your order on MyBooking is complete.", orderConfirmationPage.getAlertInformation().strip());
    }


}
