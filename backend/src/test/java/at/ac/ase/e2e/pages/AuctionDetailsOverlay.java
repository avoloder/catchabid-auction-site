package at.ac.ase.e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AuctionDetailsOverlay extends PageObject{

    public AuctionDetailsOverlay(WebDriver driver) {
        super(driver);
    }

    public String getOverlayTitle(){
        return getDriver().findElement(By.id("title")).getText();
    }

    public String getAuctionCategory(){
        return getDriver().findElement(By.id("auction-category")).getText();
    }

    public String getAuctionStatus(){
        return getDriver().findElement(By.id("auction-status")).getText();
    }

    public String getCreatorName(){
        return getDriver().findElement(By.id("creator-name")).getText();
    }

    public String getAuctionAddress(){
        return getDriver().findElement(By.id("auction-address")).getText();
    }

    public String getAuctionMinPrice(){
        return getDriver().findElement(By.id("min-price")).getText();
    }

    public void exitDetailView(){
        getDriver().findElement(By.id("close-button")).click();
    }

    public void clickPlaceABidButton(){
        getDriver().findElement(By.xpath("//a[contains(text(),'Place a bid')]")).click();
    }

}
