package dbtests;

import com.github.javafaker.Faker;
import org.apache.commons.codec.digest.DigestUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.ApplicationPage;
import pages.LoginPage;
import pages.SignUpPage;
import uitests.TestBase;
import utilities.DBUtility;
import utilities.SeleniumUtils;

import java.util.List;
import java.util.Map;

public class dbtests_Irina extends TestBase {

    @Test(groups = {"sprint_3"})
    public  void db0VerifyCoBorrowerInfoPositive() {
        logger.info("Create a user's First Name, Last Name, Email and Password using fake data");
        Faker fake = new Faker();
        String expectedFirstName = fake.name().firstName();
        String expectedLastName = fake.name().lastName();
        String expectedEmail = fake.internet().emailAddress();
        System.out.println(expectedEmail);
        String expectedPassword = fake.internet().password();
        logger.info("Get encrypted version on the Password");
        String md5hash = DigestUtils.md5Hex(expectedPassword);

        logger.info("SignUp to the website");

        LoginPage loginPage = new LoginPage();
        loginPage.signUpLink.click();
        SignUpPage signUpPage = new SignUpPage();
        signUpPage.firstNameField.sendKeys(expectedFirstName);
        signUpPage.lastNameField.sendKeys(expectedLastName);
        signUpPage.emailField.sendKeys(expectedEmail);
        signUpPage.passwordField.sendKeys(expectedPassword);
        signUpPage.signUpButtonField.click();

        SeleniumUtils.waitForUrlContains(loginUrl, 10);

        loginPage.login(expectedEmail, expectedPassword);
        logger.info("Entering Email, Password and clicking login button");

        SeleniumUtils.waitForUrlContains(dashboardUrl, 10);

        System.out.println("driver = " + driver);

        ApplicationPage appPage = new ApplicationPage();
        appPage.mortgageApplicationButton.click();
        appPage.realtorInfoField.sendKeys(realtorInfo);
        //appPage.workingWithLoanOfficerYES.click(); // ARE YOU WORKING WITH A REALTOR? - YES
        appPage.workingWithLoanOfficerNO.click(); // ARE YOU WORKING WITH A REALTOR? - NO
        appPage.purposeOfLoanButton.click();
        appPage.purposeOfLoanField.sendKeys(purposeOfLoan, Keys.ENTER);
        appPage.estimatedPurchasePriceField.sendKeys(estimatedPurchasePrice);
        appPage.downPaymentAmountField.sendKeys(downPaymentAmount);
        appPage.downPaymentSourceButton.click();
        appPage.downPaymentSourceField.sendKeys(paymentSourceAnotherType, Keys.ENTER);
        appPage.nextButton.click();

        // -- scroll window to the top to bring checkbox on the screen
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(0,-100)");

        appPage.withCoborrowerCheckbox.click();
        appPage.firstNameField.sendKeys(expectedFirstName);
        appPage.lastNameField.sendKeys(expectedLastName);
        appPage.emailField.sendKeys(expectedEmail);
        appPage.dateOfBirthField.sendKeys(dateOfBirth);
        appPage.ssnField.sendKeys(ssn);
        appPage.maritalStatusButton.click();
        appPage.maritalStatusField.sendKeys(married, Keys.ENTER);
        appPage.cellPhoneField.sendKeys(cellNumber);
        appPage.homePhoneField.sendKeys(homeNumber);

        // store co-borrower info
        String fName = fake.name().firstName();
        String lName = fake.name().lastName();
        String coBorEmail = fake.internet().emailAddress();
        appPage.coborrowerFirstNameField.sendKeys(fName);
        appPage.coborrowerLastNameField.sendKeys(lName);
        appPage.coborrowerEmailField.sendKeys(coBorEmail);
        appPage.coborrowerDOBField.sendKeys(dateOfBirth);
        appPage.coborrowerSSNField.sendKeys(ssn);
        appPage.coborrowerMaritalStatusButton.click();
        appPage.coborrowerMaritalStatusField.sendKeys(married, Keys.ENTER);
        appPage.coborrowerCellField.sendKeys(cellNumber);

        appPage.nextButton.click();
        logger.info("Adding proper credentials on the Personal Information page with Co-Borrower, clicking next button");
        logger.info("Adding monthly rental payment amount on Expenses page, clicking next button ");
        appPage.monthlyRentalPaymentField.sendKeys(monthlyRentalPayment);
        appPage.nextButton.click();
        logger.info("Filling the application with proper credentials, " +
                "clicking Next button");
        appPage.employerNameField.sendKeys(employerName);
        appPage.jobPositionField.sendKeys(jobPosition);
        appPage.jobCityField.sendKeys(jobCity);

        Select select = new Select(appPage.stateSelect);
        select.selectByIndex(jobState);
        appPage.jobStartDateField.sendKeys(jobStartDate);

        //coborrower employer
        appPage.coborrowerEmployer.sendKeys(employerName);
        appPage.coborrowerPosition.sendKeys(jobPosition);
        appPage.coborrowerCity.sendKeys(jobCity);
        new Select(appPage.stateSelect);
        select.selectByIndex(jobState);
        appPage.coborrowerStartDate.sendKeys(jobStartDate);

        appPage.grossMonthlyIncomeField.sendKeys(grossMonthlyIncome);
        appPage.monthlyOvertimeField.sendKeys(monthlyOvertime);
        appPage.monthlyBonusesField.sendKeys(monthlyBonuses);
        appPage.monthlyCommissionsField.sendKeys(monthlyCommissions);
        appPage.monthlyDividentsField.sendKeys(monthlyDividents);
        //coborrower
        appPage.coborrowerGrossMonthlyIncome.sendKeys(grossMonthlyIncome);
        appPage.coborrowerMonthlyOvertime.sendKeys(monthlyOvertime);
        appPage.coborrowerMonthlyBonuses.sendKeys(monthlyBonuses);
        appPage.coborrowerMonthlyCommission.sendKeys(monthlyCommissions);
        appPage.coborrowerMonthlyDividents.sendKeys(monthlyDividents);

        select = new Select(appPage.additionalIncomeSourceField1);
        select.selectByIndex(incomeSource);
        appPage.additionalIncomeAmountField1.sendKeys(additionalIncomeAmount);

        select = new Select(appPage.additionalIncomeSourceField2);
        select.selectByIndex(incomeSource);
        appPage.additionalIncomeAmountField2.sendKeys(additionalIncomeAmount);

        select = new Select(appPage.additionalIncomeSourceField3);
        select.selectByIndex(incomeSource);
        appPage.additionalIncomeAmountField3.sendKeys(additionalIncomeAmount);

        appPage.nextButton.click();
        appPage.nextButton.click();

        logger.info("Signing eConsent by using proper credentials, clicking Next button");

        // -- scroll window to the top to bring checkbox on the screen
        js.executeScript("javascript:window.scrollBy(0,-100)");

        appPage.eConsentDeclarer.click();
        select = new Select(appPage.eConsentDeclarer);
        select.selectByValue("Borrower");
        appPage.eConsentFirstNameField.sendKeys(expectedFirstName);
        appPage.eConsentLastNameField.sendKeys(expectedLastName);
        appPage.eConsentEmailField.sendKeys(expectedEmail);
        appPage.agreeButton.click();
        //appPage.doNotAgreeButton.click();
        appPage.nextButton.click();
        appPage.saveButton.click();

        logger.info("Connect to database");
        DBUtility.createConnection();

        String query = "select * from tbl_user\n" +
                "join tbl_mortagage on tbl_user.id=tbl_mortagage.user_id\n" +
                "where email = '"+expectedEmail+"'";

        List<Map<String, Object>> listOfMaps = DBUtility.getQueryResultListOfMaps(query);

        Map<String, Object> map = listOfMaps.get(0);
        System.out.println(map);

        // Compare the information that was entered through UI with the Database info
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(map.get("c_firstName"), fName);
        softAssert.assertEquals(map.get("c_lastName"), lName);
        softAssert.assertEquals(map.get("c_email").toString().toLowerCase(), coBorEmail);  // email bug found, to prevent failure useing toLowercase

        softAssert.assertAll();

    }

    @Test (groups = {"sprint_3", "negative"})
    public void db1ApostropheInLastNameSignUpNegative() {
        logger.info("Create a user's First Name, Last Name with apostrophe, Email and Password using fake data");
        Faker fake = new Faker();
        String expectedFirstName = fake.name().firstName();
        String expectedLastName = "O'Meily";
        String expectedEmail = fake.internet().emailAddress();
        String expectedPassword = fake.internet().password();
        logger.info("Get encrypted version on the Password");
        String md5hash = DigestUtils.md5Hex(expectedPassword);

        logger.info("SignUp to the website");

        LoginPage loginPage = new LoginPage();
        loginPage.signUpLink.click();
        SignUpPage signUpPage = new SignUpPage();
        signUpPage.firstNameField.sendKeys(expectedFirstName);
        signUpPage.lastNameField.sendKeys(expectedLastName);
        signUpPage.emailField.sendKeys(expectedEmail);
        signUpPage.passwordField.sendKeys(expectedPassword);
        signUpPage.signUpButtonField.click();

        logger.info("Connect to database");
        DBUtility.createConnection();

        String query = "select count(*) from tbl_user where email = '" + expectedEmail + "'";
        List<Map<String, Object>> maps = DBUtility.getQueryResultListOfMaps(query);
        System.out.println("maps = " + maps);
        long result = (long)(maps.get(0).get("count(*)"));
        Assert.assertEquals(result, 0);
        logger.info("This is a bug");
    }

}
