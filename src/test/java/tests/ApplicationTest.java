package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ApplicationTest extends TestBase{

    @Test
    public void verifyPageTitle(){

        loginPage.login(testerEmail, testerPassword);
        logger.info("Verifying Loan Application page title");
        Assert.assertTrue(driver.getTitle().equals(loanApplicationPageTitle));

    }
    @Test
    public void clickMortgageAppAndVerifyUrl(){

        loginPage.login(testerEmail, testerPassword);
        appPage.mortgageApplicationButton.click();
        logger.info("Verifying Mortgage Application Page URL");
        Assert.assertTrue(driver.getCurrentUrl().equals(mortgageAppUrl));

    }
    @Test
    public void clickApplicationListAndVerifyUrl(){

        loginPage.login(testerEmail, testerPassword);
        appPage.appListButton.click();
        logger.info("Verifying Application List Page URL");
        Assert.assertTrue(driver.getCurrentUrl().equals(appListUrl));

    }
}
