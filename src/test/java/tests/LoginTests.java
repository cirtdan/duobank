package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utilities.ConfigReader;
import utilities.SeleniumUtils;

public class LoginTests extends TestBase{

    @Test
    public void appHealthCheck(){

        Assert.assertTrue(driver.getCurrentUrl().equals(ConfigReader.getProperty("url")));

    }

    @Test (groups = {"smoke_test"})
    public void positiveLogin() {

        LoginPage loginPage = new LoginPage();

        loginPage.login(email, pass);
        Assert.assertTrue(driver.getCurrentUrl().equals("http://duobank-env.eba-hjmrxg9a.us-east-2.elasticbeanstalk.com/dashboard.php"));

    }

}

