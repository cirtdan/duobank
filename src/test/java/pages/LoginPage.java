package pages;

import jdk.nashorn.internal.runtime.regexp.joni.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.ConfigReader;

public class LoginPage extends  PageBase{


    @FindBy(xpath = "//input[@name='email']")
    public WebElement emailField;

    @FindBy(xpath = "//input[@name='password']")
    public WebElement passwordField;

    @FindBy(xpath = "//button[@name='login']")
    public WebElement loginButton;

    @FindBy(xpath = "//a[.='Sign up']")
    public WebElement signUpLink;

    @FindBy(xpath = "//span[.='Mickey Mouse']")
    public WebElement actualUsernameButton;



    public void login(String username, String pass){
        emailField.sendKeys(username);
       passwordField.sendKeys(pass);
        loginButton.click();
    }
}
