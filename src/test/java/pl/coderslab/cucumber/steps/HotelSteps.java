package pl.coderslab.cucumber.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pl.coderslab.model.HotelCreateAnAccountPage;
import pl.coderslab.model.HotelLoginPage;
import pl.coderslab.model.HotelMainPage;
import pl.coderslab.model.HotelMyAccountPage;

import java.time.Duration;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class HotelSteps {

    private WebDriver driver;

    @Given("an open browser with {}")
    public void openBrowser(String url){
        //        Ustaw gdzie jest chromedriver -> STEROWNIK
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver.exe");
        //        Otworz przegladarke
        this.driver = new ChromeDriver();
        //        Jesli test nie przechodzi poprawnie, to pewnie za wolno laduje sie strona -> Dodaj czekanie.
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        this.driver.get(url);
    }

    @When("new user register")
    public void newUserRegister(){
        String email = new Random().nextInt(100000000) + "TEA26@test.com";

        HotelMainPage hotelMainPage = new HotelMainPage(this.driver);
        hotelMainPage.clickSignIn();

        HotelLoginPage hotelLoginPage = new HotelLoginPage(this.driver);
        hotelLoginPage.createAnAccountWithEmail(email);

        HotelCreateAnAccountPage hotelCreateAnAccountPage = new HotelCreateAnAccountPage(this.driver);
        hotelCreateAnAccountPage.fillFormAndSubmit("Janusz", "Januszewski", "12345");
    }


    @When("a user with {} and {} is registered")
    public void newUserRegister(String name, String lastName) {
        String email = new Random().nextInt(100000000) + "TEA26@test.com";

        HotelMainPage hotelMainPage = new HotelMainPage(this.driver);
        hotelMainPage.clickSignIn();

        HotelLoginPage hotelLoginPage = new HotelLoginPage(this.driver);
        hotelLoginPage.createAnAccountWithEmail(email);

        HotelCreateAnAccountPage hotelCreateAnAccountPage = new HotelCreateAnAccountPage(this.driver);
        hotelCreateAnAccountPage.fillFormAndSubmit(name, lastName, "12345");
    }

    @Then("an account is created")
    public void accountIsCreated(){
        String expectedAlertText = "Your account has been created.";

        HotelMyAccountPage hotelMyAccountPage = new HotelMyAccountPage(driver);
        String alertText = hotelMyAccountPage.getAlertText();
        assertEquals(expectedAlertText, alertText);
    }

    @And("close browser")
    public void closeBrowser(){
        this.driver.quit();
    }

}
