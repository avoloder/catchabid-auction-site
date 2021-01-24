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

    @FindBy(css = ".dropdown-toggle")
    private WebElement dropdownToggleButton;

    @FindBy(linkText = "Create an auction post")
    private WebElement creatAuctionButton;

    @FindBy(css = ".toast-message")
    private WebElement toastMsg;

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

    public WebElement getMyProfile() {
        return getDriver().findElement(By.id("my-profile"));
    }

    public WebElement getLogout() {
        return getDriver().findElement(By.id("logout"));
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

    public void clickMyProfileButton() {
        getMyProfile().click();
    }

    public void clickLogoutButton() {
        getLogout().click();
    }

    public WebElement getDropdownToggleButton() {
        return dropdownToggleButton;
    }

    public void clickOnCreateAuctionButton() {
        creatAuctionButton.click();
    }

    public WebElement getToastMsg() {
        return toastMsg;
    }
}
