package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ApplicationListTest extends TestBase{

    @Test
    public void appPageHealthCheck(){

        loginPage.login(testerEmail, testerPassword);
        appPage.appListButton.click();
        logger.info("Navigating to the Application List page and checking the page URL is expected");
        Assert.assertEquals(appListUrl, driver.getCurrentUrl());

    }

    @Test
    public void loanButtonCheck(){ // BUG

        loginPage.login(testerEmail, testerPassword);
        appPage.appListButton.click();
        appPage.loanButton.click();
        logger.info("Navigating to the Application List page, clicking the Loan button and " +
                "checking if the page URL is changed");
        Assert.assertEquals(appListUrl, driver.getCurrentUrl());
        logger.info("THERE IS A BUG");

    }

    @Test
    public void homeButtonCheck(){ // BUG

        loginPage.login(testerEmail, testerPassword);
        appPage.appListButton.click();
        appPage.homeButton.click();
        logger.info("Navigating to the Application List page, clicking the Loan button and " +
                "checking if the page URL is changed");
        Assert.assertEquals(appListUrl, driver.getCurrentUrl());
        logger.info("THERE IS A BUG");

    }

}
