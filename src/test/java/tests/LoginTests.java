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


        logger.info("Navigating to the main page and checking main page URL");
        Assert.assertTrue(driver.getCurrentUrl().equals(loginUrl));

    }

    @Test (priority=1, groups = {"smoke"})
    public void positiveLogin() {

        loginPage.login(testerEmail, testerPassword);
        logger.info("Entering Email, Password and clicking login button and verifying the URL is expected");
        Assert.assertTrue(driver.getCurrentUrl().equals(dashboardUrl));

    }

    @Test (groups = {"smoke"})
    public void negativeLogin() {

        loginPage.login(email, pass);
        logger.info("Entering random Email, Password and clicking login button and verifying the URL is expected");
        Assert.assertFalse(driver.getCurrentUrl().equals(dashboardUrl));

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
        Assert.assertTrue(driver.getCurrentUrl().equals(dashboardUrl));
    }
}

