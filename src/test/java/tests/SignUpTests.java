package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SignUpPage;
import utilities.ConfigReader;

public class SignUpTests extends TestBase {

    @Test (groups = {"smoke_test"})
    public void positiveSignUp() {

        SignUpPage signup = new SignUpPage();
        signup.signUp(firstName, lastName, email, pass);

        new WebDriverWait(driver, 5).until(ExpectedConditions.urlToBe("http://duobank-env.eba-hjmrxg9a.us-east-2.elasticbeanstalk.com/index.php"));
        Assert.assertTrue(driver.getCurrentUrl().equals("http://duobank-env.eba-hjmrxg9a.us-east-2.elasticbeanstalk.com/index.php"));
    }

    @Test (groups = {"smoke_test"})
    public void negativeSignUpWithWrongEmail() {

        SignUpPage signup = new SignUpPage();
        String wrongEmailFormat = "@gmail.com";
        signup.signUp(firstName, lastName, wrongEmailFormat, pass);

        new WebDriverWait(driver, 5).until(ExpectedConditions.urlToBe("http://duobank-env.eba-hjmrxg9a.us-east-2.elasticbeanstalk.com/index.php"));
        Assert.assertTrue(driver.getCurrentUrl().equals("http://duobank-env.eba-hjmrxg9a.us-east-2.elasticbeanstalk.com/index.php"));
    }

    @Test (groups = {"smoke_test"})
    public void SignUpWithUnusualNameAndWrongEmailFormat() {

        SignUpPage signup = new SignUpPage();
        String elonSonsName = "X Æ A-12,";
        String wrongEmailFormat = "XA-12@io-.com";
        signup.signUp(elonSonsName, lastName, wrongEmailFormat, pass);

        new WebDriverWait(driver, 5).until(ExpectedConditions.urlToBe("http://duobank-env.eba-hjmrxg9a.us-east-2.elasticbeanstalk.com/index.php"));
        Assert.assertTrue(driver.getCurrentUrl().equals("http://duobank-env.eba-hjmrxg9a.us-east-2.elasticbeanstalk.com/index.php"));
    }

    @Test (groups = {"smoke_test"})
    public void SignUpWithUnsafePassword() {

        SignUpPage signup = new SignUpPage();
        signup.signUp(firstName, lastName, alternativeEmail, signup.unsafePassword()); // alternativeEmail used to sign up

        new WebDriverWait(driver, 5).until(ExpectedConditions.urlToBe("http://duobank-env.eba-hjmrxg9a.us-east-2.elasticbeanstalk.com/index.php"));
        Assert.assertTrue(driver.getCurrentUrl().equals("http://duobank-env.eba-hjmrxg9a.us-east-2.elasticbeanstalk.com/index.php"));
    }

}
