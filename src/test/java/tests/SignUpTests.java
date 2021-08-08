package tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.CSVReader;

import java.io.IOException;

public class SignUpTests extends TestBase {

    @Test (dataProvider = "getData", groups = {"smoke"})
    public void positiveSignUp(String firstName, String lastName, String email, String password) {

        signup.signUp(firstName, lastName, email, password);
        logger.info("Signing Up and verifying the URS is expected");
        new WebDriverWait(driver, 5).until(ExpectedConditions.urlToBe(loginUrl));
        Assert.assertTrue(driver.getCurrentUrl().equals(loginUrl));
    }

    @Test (groups = {"smoke"})
    public void negativeSignUpWithWrongEmail() {

        signup.signUp(firstName, lastName, wrongEmailFormat, pass);
        logger.info("Signing Up using wrong email format and verifying the MESSAGE is expected");
        Assert.assertNotEquals(signup.actualWelcomingMessage.getText(), expectedWelcomingMessage);
    }

    @Test (groups = {"smoke"})
    public void SignUpWithUnusualNameAndWrongEmailFormat() {

        signup.signUp(elonSonsName, lastName, wrongEmailFormat2, pass);
        logger.info("Signing Up using unusual name and wrong email format and verifying the MESSAGE is expected");
        Assert.assertNotEquals(signup.actualWelcomingMessage.getText(), expectedWelcomingMessage);
    }

    @Test (groups = {"smoke"})
    public void SignUpWithUnsafePassword() {

        signup.signUp(firstName, lastName, alternativeEmail, signup.unsafePassword()); // alternativeEmail used to sign up
        logger.info("Signing Up using wrong email format and verifying the MESSAGE is expected");
        Assert.assertEquals(signup.actualWelcomingMessage.getText(), expectedWelcomingMessage);
    }

    @Test (groups = {"smoke"})
    public void SignUpWithExistingUser() {

        signup.signUp(firstName, lastName, testerEmail, testerPassword); // existed user email
        logger.info("Signing Up using existing customer's email and password and verifying the MESSAGES are expected");
        Assert.assertNotEquals(signup.actualWelcomingMessage.getText(), expectedWelcomingMessage);
        Assert.assertEquals(signup.emailExistedMessageActual.getText(), emailExistedMessageExpected);
    }

    @DataProvider
    public Object [][] getData() throws IOException {

        return CSVReader.readData("MOCK_DATA.csv");

    }
}
