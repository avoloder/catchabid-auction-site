package at.ac.ase.e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Represents Base Catchabid Page
 * with Navbar and Footer
 */
public class CatchabidBasePage extends PageObject {

    @FindBy(id = "navbar-main")
    private WebElement navbar;

    @FindBy(xpath = "//footer")
    private WebElement footerCopyrightLink;

    public CatchabidBasePage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return getDriver().getTitle();
    }

    public WebElement getNavbar() {
        return navbar;
    }

    public WebElement getFooterCopyrightLink() {
        return footerCopyrightLink;
    }

    public WebElement getUsername(String username){
        return getDriver().findElement(By.xpath("//nav//button[contains(text(),'" + username + "')]"));
    }

    public WebElement getLoginButton() {
        return getDriver().findElement(By.xpath("//a[contains(text(),'Login')]"));
    }

    public WebElement getRegisterButton() {
        return getDriver().findElement(By.xpath("//a[contains(text(),'Register')]"));
    }

    public AuctionsListArea getAuctionsListArea() {
        return initPage(AuctionsListArea.class);
    }

    public AuctionFiltersArea getAuctionsFilterArea() {
        return initPage(AuctionFiltersArea.class);
    }

    public void clickLoginButton() {
        getLoginButton().click();
    }

    public void clickRegisterButton(){
        getRegisterButton().click();
    }
}
