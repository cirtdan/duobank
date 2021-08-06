package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;

public class SignUpPage extends PageBase {


    @FindBy(xpath = "//input[@name='first_name']")
    public WebElement firstNameField;

    @FindBy(xpath = "//input[@name='last_name']")
    public WebElement lastNameField;

    @FindBy(xpath = "//input[@name='email']")
    public WebElement emailField;

    @FindBy(xpath = "//input[@name='password']")
    public WebElement passwordField;

    @FindBy(xpath = "//button[@name='register']")
    public WebElement signUpButtonField;

    @FindBy(xpath = "//h4")
    public WebElement actualWelcomingMessage;

    @FindBy(xpath = "//span[.='This email already used']")
    public WebElement emailExistedMessageActual;


    public void signUp(String firstname, String lastname, String email, String pass) {

        LoginPage loginPage = new LoginPage();

        loginPage.signUpLink.click();
        firstNameField.sendKeys(firstname);
        lastNameField.sendKeys(lastname);
        emailField.sendKeys(email);
        passwordField.sendKeys(pass);
        signUpButtonField.click();
    }
    public String unsafePassword(){
        String unsafePassword = "";
        List<String> unsafePassList = Arrays.asList("0", "12345", "&", "$", "111", "?", "!");
        for (String s : unsafePassList) {
            unsafePassword = s;
        }
        return unsafePassword;
    }
}
