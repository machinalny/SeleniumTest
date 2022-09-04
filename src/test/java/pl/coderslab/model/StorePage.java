package pl.coderslab.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StorePage {

    protected WebDriver driver;

    @FindBy(linkText = "CLOTHES")
    private WebElement clothesCategoryButton;

    public StorePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    public void openClothes(){
        clothesCategoryButton.click();
    }
}
