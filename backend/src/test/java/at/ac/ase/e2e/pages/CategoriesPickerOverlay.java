package at.ac.ase.e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class CategoriesPickerOverlay extends PageObject{

    public CategoriesPickerOverlay(WebDriver driver) {
        super(driver);
    }

    public String getOverlayTitle(){
        return getDriver().findElement(By.id("title")).getText();
    }

    public List<String> getDisplayedCategories () {
        return getDriver().findElements(By.name("category-label"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void clickCategory(String category) {
        getDriver().findElements(By.name("category-label"))
                .stream()
                .filter(e -> e.getText().equals(category))
                .map(e -> e.findElement(By.name("category-checkbox")))
                .findFirst()
                .ifPresent(WebElement::click);
    }

    public void clickRegisterButton() {
        getDriver().findElement(By.xpath("//button[contains(text(),'Register')]")).click();
    }

}
