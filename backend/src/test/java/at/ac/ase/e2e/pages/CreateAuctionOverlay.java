package at.ac.ase.e2e.pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class CreateAuctionOverlay extends PageObject {


    @FindBy(id = "inputName")
    private WebElement itemNameTextField;

    @FindBy(id = "inputCategory")
    private WebElement categoryDropdown;

    @FindBy(name = "dp")
    private WebElement startDatePicker;

    @FindBy(xpath = "(//input[@type='text'])[4]")
    private WebElement startDateHour;

    @FindBy(xpath = "(//input[@type='text'])[5]")
    private WebElement startDateMinute;

    @FindBy(xpath = "(//input[@name='dp'])[2]")
    private WebElement endDatePicker;

    @FindBy(xpath = "(//input[@type='text'])[6]")
    private WebElement endDateHour;

    @FindBy(xpath = "(//input[@type='text'])[7]")
    private WebElement endDateMinute;

    @FindBy(id = "inputCountry")
    private WebElement countryTextField;

    @FindBy(id = "inputCity")
    private WebElement cityTextField;

    @FindBy(id = "inputAddress")
    private WebElement addressTextField;

    @FindBy(id = "inputHouse")
    private WebElement houseNumberTextField;

    @FindBy(id = "inputPrice")
    private WebElement priceTextField;

    @FindBy(id = "inputDescription")
    private WebElement descriptionTextField;

    @FindBy(id = "customFile")
    private WebElement customFilePicker;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveAuctionButton;

    public CreateAuctionOverlay(WebDriver driver) {
        super(driver);
    }

    public void createAuction(String itemName, String category, Date startDate,
        Date endDate, String country, String address, String houseNumber, String city,
        Long minimalPrice, String description, String imagePath) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat hourFormat = new SimpleDateFormat("HH");
        DateFormat minuteFormat = new SimpleDateFormat("mm");

        System.out.println(imagePath);

        itemNameTextField.sendKeys(itemName);

        Select categorySelect = new Select(categoryDropdown);
        categorySelect.selectByValue(category.toUpperCase());

        startDatePicker.sendKeys(dateFormat.format(startDate));
        startDateHour.sendKeys(hourFormat.format(startDate));
        startDateMinute.sendKeys(minuteFormat.format(startDate));

        endDatePicker.sendKeys(dateFormat.format(endDate));
        endDateHour.sendKeys(hourFormat.format(endDate));
        endDateMinute.sendKeys(minuteFormat.format(endDate));

        countryTextField.sendKeys(country);
        addressTextField.sendKeys(address);
        houseNumberTextField.sendKeys(houseNumber);
        cityTextField.sendKeys(city);
        priceTextField.sendKeys(minimalPrice.toString());
        descriptionTextField.sendKeys(description);
        customFilePicker.sendKeys(imagePath);

        saveAuctionButton.click();
    }

}
