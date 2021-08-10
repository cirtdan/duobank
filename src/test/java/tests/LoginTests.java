package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ApplicationPage;
import pages.LoginPage;
import utilities.ConfigReader;
import utilities.SeleniumUtils;

import java.util.concurrent.TimeUnit;

public class LoginTests extends TestBase{

    @Test (groups = {"smoke"})
    public void appHealthCheck(){

        logger.info("Navigating to the main page and checking main page URL");
        Assert.assertEquals(loginUrl, driver.getCurrentUrl());

    }

    @Test (priority=1, groups = {"smoke"})
    public void positiveLogin() {

        loginPage.login(testerEmail, testerPassword);
        logger.info("Entering Email, Password and clicking login button and verifying the URL is expected");
        Assert.assertEquals(dashboardUrl, driver.getCurrentUrl());

    }

    @Test (groups = {"smoke"}) // BUG
    public void negativeLoginWithRandomCharactersInsteadOfEmail() {

        loginPage.login("=@111", "1");
        logger.info("Entering random characters (=@111) as an Email, 1 as a Password, " +
                "clicking login button and verifying if we passed the step");
        Assert.assertEquals(dashboardUrl, driver.getCurrentUrl());
        logger.info("THERE IS A BUG");

    }

    @Test (groups = {"smoke"})
    public void negativeLoginWithRandomEmailAndPass() {

        loginPage.login(email, pass);
        logger.info("Entering random Email, Password and clicking login button and verifying the URL is expected");
        Assert.assertNotEquals(dashboardUrl, driver.getCurrentUrl());

    }

    @Test (priority=2, groups = {"smoke"})
    public void loginAndVerifyUsername(){

        loginPage.login(testerEmail, testerPassword);
        logger.info("Entering tester Email, Password, clicking login button and verifying the USER NAME is expected");
        Assert.assertEquals(loginPage.actualUsernameButton.getText(), expectedUsername);
    }

    @Test (groups = {"smoke"})
    public void loginAndVerifyUrl(){

        loginPage.login(testerEmail, testerPassword);
        logger.info("Entering tester Email, Password, clicking login button and verifying the URL is expected");
        Assert.assertEquals(dashboardUrl, driver.getCurrentUrl());
    }
}

