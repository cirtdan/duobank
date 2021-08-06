package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.ConfigReader;

public class ApplicationPage {

    @FindBy(xpath = "//span[.='Mickey Mouse']")
    public WebElement actualUsernameButton;

}
