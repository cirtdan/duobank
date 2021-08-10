package tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.CSVReader;
import java.io.IOException;

public class SignUpTests extends TestBase {

    @Test (dataProvider = "getData")
    public void positiveSignUpWithMockData(String firstName, String lastName, String email, String password) {

        signup.signUp(firstName, lastName, email, password);
        logger.info("Signing Up and verifying the URS is expected");
        new WebDriverWait(driver, 5).until(ExpectedConditions.urlToBe(loginUrl));
        Assert.assertEquals(loginUrl, driver.getCurrentUrl());
    }
    @Test (groups = {"smoke"})
    public void positiveSignUpWithFaker() {

        signup.signUp(firstName, lastName, email, pass);
        logger.info("Signing Up and verifying the URS is expected");
        new WebDriverWait(driver, 5).until(ExpectedConditions.urlToBe(loginUrl));
        Assert.assertEquals(loginUrl, driver.getCurrentUrl());
    }

    @Test (groups = {"smoke"})
    public void negativeSignUpWithWrongEmail() {

        signup.signUp(firstName, lastName, wrongEmailFormat, pass);
        logger.info("Signing Up using wrong email format and verifying the URL is expected");
        Assert.assertNotEquals(loginUrl, driver.getCurrentUrl());
    }

    @Test (groups = {"smoke"}) // BUG
    public void negativeSignUpWithEmptyFirstName() {

        signup.signUp(" ", lastName, email, pass);
        logger.info("Signing Up using EMPTY SPACE as a user First Name, clicking Sign Up button and " +
                "verifying if we passed the step");
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(signup.actualWelcomingMessage));
        Assert.assertEquals(loginUrl, driver.getCurrentUrl());
        logger.info("THERE IS A BUG");
    }

    @Test (groups = {"smoke"}) // BUG
    public void negativeSignUpWithEmptyLastName() {

        signup.signUp(firstName, " ", email, pass);
        logger.info("Signing Up using EMPTY SPACE as a user Last Name, clicking Sign Up button and " +
                "verifying if we passed the step");
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(signup.actualWelcomingMessage));
        Assert.assertEquals(loginUrl, driver.getCurrentUrl());
        logger.info("THERE IS A BUG");
    }

    @Test (groups = {"smoke"}) // BUG
    public void negativeSignUpWithCharactersInsteadOfEmail() {

        signup.signUp(firstName, lastName, "=@h45", pass);
        logger.info("Signing Up using random characters (=@h) as an Email, clicking Sign Up button and " +
                "verifying if we passed the step");
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(signup.actualWelcomingMessage));
        Assert.assertEquals(loginUrl, driver.getCurrentUrl());
        logger.info("THERE IS A BUG");
    }

    @Test (groups = {"smoke"})
    public void SignUpWithUnusualNameAndWrongEmailFormat() {

        signup.signUp(elonSonsName, lastName, wrongEmailFormat2, pass);
        logger.info("Signing Up using unusual name and wrong email format and verifying the MESSAGE is expected");
        Assert.assertNotEquals(loginUrl, driver.getCurrentUrl());
    }

    @Test (groups = {"smoke"}) // BUG
    public void SignUpWithUnsafePassword() {

        signup.signUp(firstName, lastName, alternativeEmail, signup.unsafePassword()); // alternativeEmail used to sign up
        logger.info("Signing Up using UNSAFE PASSWORD and checking if we passed to the next page, verifying the WELCOMING MESSAGE is expected");
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(signup.actualWelcomingMessage));
        Assert.assertEquals(signup.actualWelcomingMessage.getText(), expectedWelcomingMessage);
        logger.info("THERE IS A BUG");
    }

    @Test (groups = {"smoke"})
    public void SignUpWithExistingUser() {

        signup.signUp(firstName, lastName, testerEmail, testerPassword); // existed user email
        logger.info("Signing Up using existing customer's email and password and verifying the ERROR MESSAGE (This email already used) is expected");
        Assert.assertEquals(signup.emailExistedMessageActual.getText(), emailExistedMessageExpected);
    }

    @DataProvider
    public Object [][] getData() throws IOException {

        return CSVReader.readData("MOCK_DATA.csv");

    }
}
