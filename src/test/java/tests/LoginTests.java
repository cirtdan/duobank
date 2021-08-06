package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ApplicationPage;
import pages.LoginPage;
import utilities.ConfigReader;
import utilities.SeleniumUtils;

public class LoginTests extends TestBase{

    @Test (groups = {"smoke"})
    public void appHealthCheck(){


        logger.info("Navigating to the main page");
        Assert.assertTrue(driver.getCurrentUrl().equals(ConfigReader.getProperty("url")));

    }

    @Test (priority=1, groups = {"smoke"})
    public void positiveLogin() {

        LoginPage loginPage = new LoginPage();
        logger.info("Entering Email, Password and clicking login button and verifying the url is expected");

        loginPage.login(testerEmail, testerPassword);
        Assert.assertTrue(driver.getCurrentUrl().equals(dashboardUrl));

    }

    @Test (groups = {"smoke"})
    public void negativeLogin() {

        LoginPage loginPage = new LoginPage();
        logger.info("Entering wrongEmail, Password and clicking login button and verifying the url is expected");
        loginPage.login(email, pass);
        Assert.assertFalse(driver.getCurrentUrl().equals(dashboardUrl));

    }

    @Test (priority=2, groups = {"smoke"})
    public void loginAndVerifyUsername(){

        LoginPage loginPage = new LoginPage();

        loginPage.login(testerEmail, testerPassword);
        Assert.assertEquals(loginPage.actualUsernameButton.getText(), expectedUsername);
    }

    @Test (groups = {"smoke"})
    public void loginAndVerifyUrl(){

        LoginPage loginPage = new LoginPage();

        loginPage.login(testerEmail, testerPassword);
        Assert.assertTrue(driver.getCurrentUrl().equals(dashboardUrl));
    }
}

