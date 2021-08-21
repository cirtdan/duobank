package tests;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ApplicationPage;

public class SourceOfDownPayment extends TestBase{

    //Verify that user is able to select Other type of Down Payment
    ApplicationPage page = new ApplicationPage();


    @Test
    public void testSourceOfIncome(){
        loginPage.login(testerEmail, testerPassword);
        page.mortgageApplicationButton.click();
        Assert.assertTrue(page.selectDownpaymentSource());






    }
}
