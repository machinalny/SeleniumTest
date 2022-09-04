package pl.coderslab.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class StoreCategoryPage extends StorePage {

    @FindBy(xpath = "//div[@id='search_filters']/section")
    private List<WebElement> filterSections;


    @FindBy(className = "price")
    private List<WebElement> actualPriceTexts;

    public StoreCategoryPage(WebDriver driver) {
        super(driver);
    }

    public Double[] selectAnyPriceFilterAndGetValues() {
        Double[] pricesRange = new Double[2];
        List<WebElement> priceFilters = getFiltersFromSection("Price");

        WebElement priceFilter = priceFilters.get(0);
        String pricesRangeClean = priceFilter.getText().replace("$", "").replace("€", "").strip();
        String[] pricesElement = pricesRangeClean.split(" ");
        String priceFrom = pricesElement[0];
        String priceTo = pricesElement[2];

        pricesRange[0] = Double.parseDouble(priceFrom);
        pricesRange[1] = Double.parseDouble(priceTo);

        priceFilter.click();

        return pricesRange;
    }

    private List<WebElement> getFiltersFromSection(String section) {
        List<WebElement> filters = new ArrayList<>();
        for (WebElement filterSection : filterSections) {
            if (filterSection.getText().contains(section)) {
                filters = filterSection.findElements(By.xpath(".//ul//a"));
            }
        }

        return filters;
    }


    public List<Double> getPricesOfListedProducts() throws InterruptedException {
        sleep(5000); // Czekaj 5 sekund
        List<Double> actualPrices = new ArrayList<>();
        for (WebElement actualPriceText : actualPriceTexts) {
            if (actualPriceText.isDisplayed()) {
                String priceValue = actualPriceText.getText().replace("$", "").replace("€", "");
                actualPrices.add(Double.parseDouble(priceValue));
            }
        }
        return actualPrices;
    }

}
