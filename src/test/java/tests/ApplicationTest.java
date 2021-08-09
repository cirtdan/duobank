package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ApplicationTest extends TestBase{

    @Test
    public void verifyPageTitle(){

        loginPage.login(testerEmail, testerPassword);
        logger.info("Verifying Loan Application page title");
        Assert.assertEquals(loanApplicationPageTitle, driver.getTitle());

    }
    @Test
    public void clickMortgageAppAndVerifyUrl(){

        loginPage.login(testerEmail, testerPassword);
        appPage.mortgageApplicationButton.click();
        logger.info("Verifying Mortgage Application Page URL");
        Assert.assertEquals(mortgageAppUrl, driver.getCurrentUrl());

    }
    @Test
    public void clickApplicationListAndVerifyUrl(){

        loginPage.login(testerEmail, testerPassword);
        appPage.appListButton.click();
        logger.info("Verifying Application List Page URL");
        Assert.assertEquals(appListUrl, driver.getCurrentUrl());

    }
    @Test
    public void logOutTest(){

        loginPage.login(testerEmail, testerPassword);
        loginPage.actualUsernameButton.click();
        appPage.LogOutButton.click();
        logger.info("Logging in, then clicking Log Out button and checking the URL is expected");
        Assert.assertEquals(driver.getCurrentUrl(), loginUrl);

    }
}
