package pl.coderslab;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pl.coderslab.model.StoreCategoryPage;
import pl.coderslab.model.StorePage;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class StoreTest {

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
        this.driver.get("https://mystore-testlab.coderslab.pl/");

    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void checkFiltersOnPrices() throws InterruptedException {
        StorePage storePage = new StorePage(driver);
        storePage.openClothes();

        StoreCategoryPage storeCategoryPage = new StoreCategoryPage(driver);
        Double[] priceValuesMinMax = storeCategoryPage.selectAnyPriceFilterAndGetValues();

        List<Double> pricesFiltered = storeCategoryPage.getPricesOfListedProducts();

        for (Double priceFiltered : pricesFiltered) {
            assertTrue(priceFiltered > priceValuesMinMax[0]);
            assertTrue(priceFiltered < priceValuesMinMax[1]);
        }
    }

}
