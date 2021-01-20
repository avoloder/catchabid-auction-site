package at.ac.ase.e2e.pages;

import org.apache.commons.lang.NotImplementedException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginOverlay extends PageObject {

    public LoginOverlay(WebDriver driver) {
        super(driver);
    }

    public String getOverlayTitle(){
        return getDriver().findElement(By.id("title")).getText();
    }

    public void insertEmail(String email) {
        getDriver().findElement(By.name("email")).sendKeys(email);
    }

    public void insertPassword(String password) {
        getDriver().findElement(By.name("password")).sendKeys(password);
    }

    public void clickLoginButton() {
        getDriver().findElement(By.xpath("//button[contains(text(),'Login')]")).click();
    }

    public CatchabidBasePage clickOutsideOverlayToCloseOverlay() {
        return initPage(CatchabidBasePage.class);
    }
}
