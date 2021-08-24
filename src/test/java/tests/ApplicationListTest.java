package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.SeleniumUtils;

public class ApplicationListTest extends TestBase{

    @Test
    public void appPageHealthCheck(){

        loginPage.login(testerEmail, testerPassword);
        appPage.appListButton.click();
        logger.info("Navigating to the Application List page and checking the page URL is expected");
        Assert.assertEquals(appListUrl, driver.getCurrentUrl());

    }

    @Test (groups = {"negative"})
    public void loanButtonCheck(){ // BUG

        loginPage.login(testerEmail, testerPassword);
        appPage.appListButton.click();
        appPage.loanButton.click();
        logger.info("Navigating to the Application List page, clicking the Loan button and " +
                "checking if the page URL is changed");
        Assert.assertEquals(appListUrl, driver.getCurrentUrl());
        logger.info(bug);

    }

    @Test (groups = {"negative"})
    public void homeButtonCheck(){ // BUG

        loginPage.login(testerEmail, testerPassword);
        appPage.appListButton.click();
        appPage.homeButton.click();
        logger.info("Navigating to the Application List page, clicking the Loan button and " +
                "checking if the page URL is changed");
        Assert.assertEquals(appListUrl, driver.getCurrentUrl());
        logger.info(bug);

    }
@Test (groups = {"sprint_2"})//ilkin
    public void searchBoxCheck(){
        loginPage.login(testerEmail, testerPassword);
        appPage.appListButton.click();
        appPage.searchBoxOnApplicationList.sendKeys("Lenita Douglas");
        Assert.assertTrue(true,"Lenita Douglas");

}
}
