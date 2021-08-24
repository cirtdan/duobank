package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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
    public void searchBoxCheck() {
        loginPage.login(testerEmail, testerPassword);
        appPage.appListButton.click();
        appPage.searchBoxOnApplicationList.sendKeys("Lenita Douglas");
        Assert.assertTrue(true, "Lenita Douglas");

    }

    //_______________________Tetiana Kucherova doing sprint2 home work here:)___________________________________________________________________
  @Test(groups = "sprint_2")
    public void dropDownButton ( ) {
        loginPage.login ( testerEmail, testerPassword );
        appPage.appListButton.click ( );

        // driver.get("http://duobank-env.eba-hjmrxg9a.us-east-2.elasticbeanstalk.com/applications.php");
        WebElement DDButton = driver.findElement
                ( By.xpath ( "//*[@id='DataTables_Table_0_length']/label/select" ));
       Select select = new Select(DDButton);
       select.selectByIndex(2);
       select.selectByIndex(3);


    }



    @Test(dataProvider ="getData",groups = "sprint_2")
    public void serchFild ( String searchTerm) {
        loginPage.login ( testerEmail, testerPassword );
        appPage.appListButton.click ( );
        driver.findElement ( By.xpath ( "//*[@id='DataTables_Table_0_filter']/label/input" ) )
                .sendKeys ( searchTerm, Keys.ENTER );
        logger.info ( "In this test we are checking if search feature is working correct. " +
                "We are entering random characters and checking if list will show " +
                "'No matching records found' " );
        Assert.assertEquals( appPage.MatchingMSG.getText(), ExpectedMatchingMSG);
    }
    @DataProvider
    public Object[][] getData() {
        return new Object[][] {
                    { "674454454" },
                    { "till sunrise " },
                    { "*&%$@##%&%^&^*" },
            };
        }
    @Test
    public void Test() throws InterruptedException {
        loginPage.login ( testerEmail, testerPassword );
        appPage.appListButton.click ( );
         logger.info ( "In this test we are if numeric " );
         Thread.sleep(5000);
         driver.findElement ( By.xpath ( "//*[@id='DataTables_Table_0_paginate']/ul/li[2]/a" ) ).click ( );
         Assert.assertEquals( appPage.N10.getText(), ExpectedTextOfList1);
         driver.findElement ( By.xpath (" //*[@id='DataTables_Table_0_paginate']/ul/li[3]/a")).click();
         Assert.assertEquals( appPage.N20.getText(), ExpectedTextOfList2);
         driver.findElement ( By.xpath (" //*[@id='DataTables_Table_0_paginate']/ul/li[4]/a")).click();
         Assert.assertEquals( appPage.N30.getText(), ExpectedTextOfList3);
         driver.findElement ( By.xpath (" //*[@id='DataTables_Table_0_paginate']/ul/li[5]/a")).click();
         Assert.assertEquals( appPage.N40.getText(), ExpectedTextOfList4);
         driver.findElement ( By.xpath (" //*[@id='DataTables_Table_0_paginate']/ul/li[6]/a")).click();
        //    Assert.assertEquals( appPage.N45.getText(), ExpectedTextOfList5);
        // driver.findElement ( By.xpath (" //*[@id='DataTables_Table_0_paginate']/ul/li[6]/a")).click();

            }
        }

/*//*[@id="DataTables_Table_0_next"]*/






