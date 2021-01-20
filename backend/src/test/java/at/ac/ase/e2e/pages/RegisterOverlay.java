package at.ac.ase.e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterOverlay extends PageObject{

    public RegisterOverlay(WebDriver driver) {
        super(driver);
    }

    public String getOverlayTitle(){
        return getDriver().findElement(By.id("title")).getText();
    }

    public void insertFirstName(String firstName){
        getDriver().findElement(By.name("firstName")).sendKeys(firstName);
    }

    public void insertLastName(String lastName){
        getDriver().findElement(By.name("lastName")).sendKeys(lastName);
    }

    public void insertEmail(String email) {
        getDriver().findElement(By.name("email")).sendKeys(email);
    }

    public void insertPassword(String password) {
        WebElement passwordField;
        if(getDriver().findElements(By.name("pass3")).stream().count()!=0){
            passwordField = getDriver().findElement(By.name("pass3"));
        }else{
            passwordField = getDriver().findElement(By.name("pass"));
        }
        passwordField.sendKeys(password);
    }

    public void insertRepeatPassword(String password) {
        WebElement passwordField;
        if(getDriver().findElements(By.name("pass4")).stream().count()!=0){
            passwordField = getDriver().findElement(By.name("pass4"));
        }else{
            passwordField = getDriver().findElement(By.name("pass2"));
        }
        passwordField.sendKeys(password);
    }

    public void insertCountry(String country) {
        getDriver().findElement(By.name("country")).sendKeys(country);
    }

    public void insertCity(String city) {
        getDriver().findElement(By.name("city")).sendKeys(city);
    }

    public void insertStreet(String street) {
        getDriver().findElement(By.name("street")).sendKeys(street);
    }

    public void insertHouseNumber(int houseNumber) {
        getDriver().findElement(By.name("houseNr")).sendKeys(String.valueOf(houseNumber));
    }

    public void insertAreaCode(int areaCode) {
        getDriver().findElement(By.name("areaCode")).sendKeys(String.valueOf(areaCode));
    }

    public void insertPhoneNumber(String phoneNumber) {
        getDriver().findElement(By.name("phoneNumber")).sendKeys(phoneNumber);
    }

    public void clickAgreeWithTermsAndConditions(){
        getDriver().findElement(By.name("terms")).click();
    }

    public void clickNextButton(){
        getDriver().findElement(By.xpath("//button[contains(text(),'Next')]")).click();
    }
}
