package pl.coderslab.cucumber.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class HotelSteps {

    private WebDriver driver;

    @Given("an open browser with {}")
    public void openBrowser(String url) {
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver.exe");
        //        Otworz przegladarke
        this.driver = new ChromeDriver();
        //        Jesli test nie przechodzi poprawnie, to pewnie za wolno laduje sie strona -> Dodaj czekanie.
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        this.driver.get(url);
    }

    @When("new user register")
    public void newUserRegister() {
        String email = new Random().nextInt(100000000) + "TEA26@test.com";

        WebElement signInButton = this.driver.findElement(By.className("user_login"));
        signInButton.click(); // Kliknij na przycisk SignIn

        WebElement registerEmailInput = this.driver.findElement(By.id("email_create"));
        registerEmailInput.clear(); // Wyczysc pole tekstowe przed wpisaniem
        registerEmailInput.sendKeys(email);
        registerEmailInput.submit(); // Potwierdz email do rejestracji

        // Wyszukaj podstawowe elementy do zalozenia uzytkownika
        WebElement customerFirstNameInput = this.driver.findElement(By.id("customer_firstname"));
        WebElement customerLastNameInput = this.driver.findElement(By.id("customer_lastname"));
//        WebElement customerEmail = driver.findElement(By.id("email"));
        WebElement customerPassword = this.driver.findElement(By.id("passwd"));

        // Wypelnij brakujace informacje o uzytkowniku
        customerFirstNameInput.clear();
        customerFirstNameInput.sendKeys("Janusz");
        customerLastNameInput.clear();
        customerLastNameInput.sendKeys("Januszewski");

        customerPassword.clear();
        customerPassword.sendKeys("12345");

        // Zaloz konto
        WebElement submitButton = this.driver.findElement(By.id("submitAccount"));
        submitButton.click();
    }

    @When("a user with {} and {} is registered")
    public void newUserRegister(String name, String lastName) {
        String email = new Random().nextInt(100000000) + "TEA26@test.com";

        WebElement signInButton = this.driver.findElement(By.className("user_login"));
        signInButton.click(); // Kliknij na przycisk SignIn

        WebElement registerEmailInput = this.driver.findElement(By.id("email_create"));
        registerEmailInput.clear(); // Wyczysc pole tekstowe przed wpisaniem
        registerEmailInput.sendKeys(email);
        registerEmailInput.submit(); // Potwierdz email do rejestracji

        // Wyszukaj podstawowe elementy do zalozenia uzytkownika
        WebElement customerFirstNameInput = this.driver.findElement(By.id("customer_firstname"));
        WebElement customerLastNameInput = this.driver.findElement(By.id("customer_lastname"));
//        WebElement customerEmail = driver.findElement(By.id("email"));
        WebElement customerPassword = this.driver.findElement(By.id("passwd"));

        // Wypelnij brakujace informacje o uzytkowniku
        customerFirstNameInput.clear();
        customerFirstNameInput.sendKeys(name);
        customerLastNameInput.clear();
        customerLastNameInput.sendKeys(lastName);

        customerPassword.clear();
        customerPassword.sendKeys("12345");

        // Zaloz konto
        WebElement submitButton = this.driver.findElement(By.id("submitAccount"));
        submitButton.click();
    }

    @Then("an account is created")
    public void anAccountIsCreated() {
        String expectedAlertText = "Your account has been created.";

        // Sprawdz czy konto poprawnie zalozone
        WebElement successAlertField = this.driver.findElement(By.className("alert-success"));

        // Pobierz informacje o poprawnym zalozeniu konta
        String alertText = successAlertField.getText();

        // Zweryfikuj komunikat.
        assertEquals(expectedAlertText, alertText);
    }

    @And("close browser")
    public void closeBrowser() {
        driver.quit();
    }
}
