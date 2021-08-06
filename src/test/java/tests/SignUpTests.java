package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SignUpPage;
import utilities.ConfigReader;

public class SignUpTests extends TestBase {

    @Test (groups = {"smoke"})
    public void positiveSignUp() {

        SignUpPage signup = new SignUpPage();
        signup.signUp(firstName, lastName, email, pass);

        new WebDriverWait(driver, 5).until(ExpectedConditions.urlToBe(loginUrl));
        Assert.assertTrue(driver.getCurrentUrl().equals(loginUrl));
    }

    @Test (groups = {"smoke"})
    public void negativeSignUpWithWrongEmail() {

        SignUpPage signup = new SignUpPage();
        String wrongEmailFormat = "@gmail.com";
        signup.signUp(firstName, lastName, wrongEmailFormat, pass);

        Assert.assertNotEquals(signup.actualWelcomingMessage.getText(), expectedWelcomingMessage);
    }

    @Test (groups = {"smoke"})
    public void SignUpWithUnusualNameAndWrongEmailFormat() {

        SignUpPage signup = new SignUpPage();
        String elonSonsName = "X Æ A-12,";
        String wrongEmailFormat = "XA-12@io-.com";
        signup.signUp(elonSonsName, lastName, wrongEmailFormat, pass);

        Assert.assertNotEquals(signup.actualWelcomingMessage.getText(), expectedWelcomingMessage);
    }

    @Test (groups = {"smoke"})
    public void SignUpWithUnsafePassword() {

        SignUpPage signup = new SignUpPage();
        signup.signUp(firstName, lastName, alternativeEmail, signup.unsafePassword()); // alternativeEmail used to sign up

        Assert.assertEquals(signup.actualWelcomingMessage.getText(), expectedWelcomingMessage);
    }

    @Test (groups = {"smoke"})
    public void SignUpWithExistingUser() {

        SignUpPage signup = new SignUpPage();
        signup.signUp(firstName, lastName, testerEmail, testerPassword); // existed user email

        Assert.assertNotEquals(signup.actualWelcomingMessage.getText(), expectedWelcomingMessage);
        Assert.assertEquals(signup.emailExistedMessageActual.getText(), emailExistedMessageExpected);
    }
}
