package tests;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MortgageApplicationTests extends TestBase {

    @Test
    public void mortgageApplicationHealthCheck(){

        loginPage.login(testerEmail, testerPassword);
        appPage.mortgageApplicationButton.click();
        logger.info("Navigating to the Mortgage Application page and checking the page URL is expected");
        Assert.assertEquals(mortgageAppUrl, driver.getCurrentUrl());

    }


    @Test
    public void preapprovalDetailsPageTest() {

        loginPage.login(testerEmail, testerPassword);
        appPage.mortgageApplicationButton.click();
        logger.info("Logging in, clicking the Mortgage Application Button and checking if " +
                "I am on the PreApproval Details page");
        Assert.assertEquals(appPage.preApprovalDetailsPageText.getText(), preApprovalDetailsPageTextExpected);
    }

    @Test
    public void preapprovalDetailsPositiveTest() {

        loginPage.login(testerEmail, testerPassword);
        logger.info("Filling the application on the PreApproval Details page using proper information, " +
                "clicking Next button and checking if we passed the step");
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
        Assert.assertEquals(appPage.personalInformationPageText.getText(), personalInformationPageTextExpected);

    }
    @Test
    public void preapprovalDetailsTestCheckingCalculatedLoanAmount() {

        loginPage.login(testerEmail, testerPassword);
        appPage.mortgageApplicationButton.click();
        appPage.realtorInfoField.sendKeys(realtorInfo);
        appPage.workingWithLoanOfficerNO.click();
        appPage.purposeOfLoanButton.click();
        appPage.purposeOfLoanField.sendKeys(purposeOfLoan, Keys.ENTER);
        appPage.estimatedPurchasePriceField.sendKeys(estimatedPurchasePrice);
        appPage.downPaymentAmountField.sendKeys(downPaymentAmount);
        String calculatedLoanAmount = appPage.calculatedLoanAmountValue.getAttribute("value");
        logger.info("Filling the application on the PreApproval Details page using proper information and checking " +
                "if calculated loan amount equals to expected loan amount");
        Assert.assertEquals(calculatedLoanAmount, expectedLoanAmount);
    }

    @Test (groups = {"negative"}) // BUG
    public void preapprovalDetailsNegativeTestWithWrongRealtorInfo() {

        loginPage.login(testerEmail, testerPassword);
        appPage.mortgageApplicationButton.click();
        logger.info("Filling the application on the PreApproval Details using EMPTY SPACE as a realtor information, " +
                "clicking Next button and checking if we passes the step");
        appPage.realtorInfoField.sendKeys(emptySpace);
        //appPage.workingWithLoanOfficerYES.click(); // ARE YOU WORKING WITH A REALTOR? - YES
        appPage.workingWithLoanOfficerNO.click(); // ARE YOU WORKING WITH A REALTOR? - NO

        appPage.purposeOfLoanButton.click();
        appPage.purposeOfLoanField.sendKeys(purposeOfLoan, Keys.ENTER);
        appPage.estimatedPurchasePriceField.sendKeys(estimatedPurchasePrice);
        appPage.downPaymentAmountField.sendKeys(downPaymentAmount);
        String calculatedLoanAmount = appPage.calculatedLoanAmountValue.getAttribute("value");
        Assert.assertEquals(calculatedLoanAmount, expectedLoanAmount);

        appPage.downPaymentSourceButton.click();
        appPage.downPaymentSourceField.sendKeys(paymentSourceAnotherType, Keys.ENTER);
        appPage.nextButton.click();
        Assert.assertEquals(appPage.personalInformationPageText.getText(), personalInformationPageTextExpected);
        logger.info(bug);
    }

    @Test
    public void personalInformationPageTest() {

        preapprovalDetailsPositiveTest();
        logger.info("Clicking Next button on the PreApproval Details page and checking if I passed to the Personal Information page");
        Assert.assertEquals(appPage.personalInformationPageText.getText(), personalInformationPageTextExpected);
    }

    @Test
    public void personalInformationPositiveTest() {

        preapprovalDetailsPositiveTest();

        appPage.firstNameField.sendKeys(firstName);
        appPage.lastNameField.sendKeys(lastName);
        appPage.emailField.sendKeys(email);
        appPage.dateOfBirthField.sendKeys(dateOfBirth);
        appPage.ssnField.sendKeys(ssn);
        appPage.maritalStatusButton.click();
        appPage.maritalStatusField.sendKeys(married, Keys.ENTER);
        appPage.cellPhoneField.sendKeys(cellNumber);
        appPage.homePhoneField.sendKeys(homeNumber);
        appPage.nextButton.click();
        logger.info("Adding proper credentials on the Personal Information page, clicking next button " +
                "and checking if we passed to the next page.");
        Assert.assertEquals(appPage.expensesPageText.getText(), expensesPageTextExpected);
    }

    @Test (groups = {"negative"}) // BUG
    public void personalInformationNegativeTestWithEmptyName() {

        preapprovalDetailsPositiveTest();
        logger.info("Adding First Name and Last Name using ONLY EMPTY SPACE on the Personal Information page, " +
                "clicking next button and checking if we passed the step");
        appPage.firstNameField.sendKeys(emptySpace);
        appPage.lastNameField.sendKeys(emptySpace);
        appPage.emailField.sendKeys(email);
        appPage.dateOfBirthField.sendKeys(dateOfBirth);
        appPage.ssnField.sendKeys(ssn);
        appPage.maritalStatusButton.click();
        appPage.maritalStatusField.sendKeys(married, Keys.ENTER);
        appPage.cellPhoneField.sendKeys(cellNumber);
        appPage.homePhoneField.sendKeys(homeNumber);
        Assert.assertEquals(appPage.lastNameField.getText(), "");
        appPage.nextButton.click();
        Assert.assertEquals(appPage.expensesPageText.getText(), expensesPageTextExpected);
        logger.info(bug);

    }
    @Test (groups = {"negative"}) // BUG
    public void personalInformationNegativeTestWithWrongDateOfBirth() {

        preapprovalDetailsPositiveTest();
        logger.info("Adding 01/01/2222 as a DATE OF BIRTH on the Personal Information page, clicking next button " +
                "and checking if we passed the step");
        appPage.firstNameField.sendKeys(firstName);
        appPage.lastNameField.sendKeys(lastName);
        appPage.emailField.sendKeys(email);
        appPage.dateOfBirthField.sendKeys(unrealDateOfBirth);
        appPage.ssnField.sendKeys(ssn);
        appPage.maritalStatusButton.click();
        appPage.maritalStatusField.sendKeys(married, Keys.ENTER);
        appPage.cellPhoneField.sendKeys(cellNumber);
        appPage.homePhoneField.sendKeys(homeNumber);
        appPage.nextButton.click();
        Assert.assertEquals(appPage.expensesPageText.getText(), expensesPageTextExpected);
        logger.info(bug);

    }
    @Test (groups = {"negative"}) // BUG
    public void personalInformationNegativeTestWithWrongSSN() {

        preapprovalDetailsPositiveTest();
        logger.info("Adding 1 as an SSN number on the Personal Information page, clicking next button " +
                "and checking if we passed the step");
        appPage.firstNameField.sendKeys(firstName);
        appPage.lastNameField.sendKeys(lastName);
        appPage.emailField.sendKeys(email);
        appPage.dateOfBirthField.sendKeys(dateOfBirth);
        appPage.ssnField.sendKeys("1");
        appPage.maritalStatusButton.click();
        appPage.maritalStatusField.sendKeys(married, Keys.ENTER);
        appPage.cellPhoneField.sendKeys(cellNumber);
        appPage.homePhoneField.sendKeys(homeNumber);
        appPage.nextButton.click();
        Assert.assertEquals(appPage.expensesPageText.getText(), expensesPageTextExpected);
        logger.info(bug);

    }
    @Test (groups = {"negative"}) // BUG
    public void personalInformationTestForASingleCustomer() {

        preapprovalDetailsPositiveTest();
        logger.info("Adding Single as a Marital Status on the Personal Information page and checking if it is accepted");
        appPage.firstNameField.sendKeys(firstName);
        appPage.lastNameField.sendKeys(lastName);
        appPage.emailField.sendKeys(email);
        appPage.dateOfBirthField.sendKeys(dateOfBirth);
        appPage.ssnField.sendKeys(ssn);
        appPage.maritalStatusButton.click();
        appPage.maritalStatusField.sendKeys(single, Keys.ENTER);
        appPage.cellPhoneField.sendKeys(cellNumber);
        appPage.homePhoneField.sendKeys(homeNumber);
        Assert.assertNotEquals(appPage.maritalStatusField.getText(), single);
        logger.info(bug);

    }

    @Test (groups = {"negative"}) // BUG
    public void personalInformationTestWithWrongCellNumber() {

        preapprovalDetailsPositiveTest();
        logger.info("Adding 1 as a cell number on Personal Information page, clicking Next button " +
                "and verifying if I passed to the next page.");
        appPage.firstNameField.sendKeys(firstName);
        appPage.lastNameField.sendKeys(lastName);
        appPage.emailField.sendKeys(email);
        appPage.dateOfBirthField.sendKeys(dateOfBirth);
        appPage.ssnField.sendKeys(ssn);
        appPage.maritalStatusButton.click();
        appPage.maritalStatusField.sendKeys(married, Keys.ENTER);
        appPage.cellPhoneField.sendKeys("1");
        appPage.homePhoneField.sendKeys(homeNumber);
        appPage.nextButton.click();
        Assert.assertEquals(appPage.expensesPageText.getText(), expensesPageTextExpected);
        logger.info(bug);

    }
    @Test (groups = {"negative"}) // BUG
    public void personalInformationTestWithWrongEmailFormat() {

        preapprovalDetailsPositiveTest();
        appPage.firstNameField.sendKeys(firstName);
        appPage.lastNameField.sendKeys(lastName);
        logger.info("Filling the application on Personal Information page by using a WRONG EMAIL FORMAT (=@h.com), " +
                "clicking Next button and verifying if I passed to the next page.");
        appPage.emailField.sendKeys(wrongEmailFormat3);
        appPage.dateOfBirthField.sendKeys(dateOfBirth);
        appPage.ssnField.sendKeys(ssn);
        appPage.maritalStatusButton.click();
        appPage.maritalStatusField.sendKeys(married, Keys.ENTER);
        appPage.cellPhoneField.sendKeys(cellNumber);
        appPage.homePhoneField.sendKeys(homeNumber);
        appPage.nextButton.click();
        Assert.assertEquals(appPage.expensesPageText.getText(), expensesPageTextExpected);
        logger.info(bug);

    }
    @Test (groups = {"negative"}) // BUG
    public void personalInformationTestNoClickingPrivatePolicy() {

        preapprovalDetailsPositiveTest();

        appPage.firstNameField.sendKeys(firstName);
        appPage.lastNameField.sendKeys(lastName);
        appPage.emailField.sendKeys(email);
        appPage.dateOfBirthField.sendKeys(dateOfBirth);
        appPage.ssnField.sendKeys(ssn);
        appPage.maritalStatusButton.click();
        appPage.maritalStatusField.sendKeys(married, Keys.ENTER);
        appPage.cellPhoneField.sendKeys(cellNumber);
        appPage.homePhoneField.sendKeys(homeNumber);
        logger.info("Adding personal information and NOT ACCEPTING PRIVACY POLICY, clicking next button " +
                "and verifying if we passed the step");
        appPage.privatePolicyCheckBox.click(); // I/We have NOT read and accepted the terms of the Privacy Policy
        appPage.nextButton.click();
        Assert.assertEquals(appPage.expensesPageText.getText(), expensesPageTextExpected);
        logger.info(bug);

    }

    @Test (groups = {"negative"}) // BUG
    public void personalInformationTestAndClickPrivacyPolicyLink() {

        preapprovalDetailsPositiveTest();
        logger.info("Clicking on Privacy Policy link on Personal Information page");
        appPage.privatePolicyLink.click(); // 404 Not Found
        logger.info(bug + " - 404 Not Found");
    }
    @Test
    public void expensesPositiveTest() {

        personalInformationPositiveTest();
        logger.info("Adding monthly rental payment amount on Expenses page, clicking next button " +
                "and verifying if we passed the step");
        appPage.monthlyRentalPaymentField.sendKeys(monthlyRentalPayment);
        appPage.nextButton.click();
        Assert.assertEquals(appPage.employmentAndIncomePageText.getText(), employmentAndIncomePageTextExpected);


    }
    @Test
    public void expensesPageTestCheckingErrorText() {

        personalInformationPositiveTest();
        logger.info("No adding monthly rental payment amount on Expenses page, clicking next button and " +
                "checking an error message is expected");
        appPage.monthlyRentalPaymentSign.click();
        appPage.nextButton.click();
        String actualErrorMessage = appPage.monthlyRentalPaymentErrorMessage.getText();
        Assert.assertEquals(actualErrorMessage, monthlyRentalPaymentExpectedErrorMessage);

    }
    @Test
    public void employmentAndIncomePositiveTest() {

        expensesPositiveTest();
        logger.info("Filling the application with proper credentials, " +
                "clicking Next button and verifying if we passed the step");
        appPage.employerNameField.sendKeys(employerName);
        appPage.jobPositionField.sendKeys(jobPosition);
        appPage.jobCityField.sendKeys(jobCity);

        Select select = new Select(appPage.stateSelect);
        select.selectByIndex(jobState);
        appPage.jobStartDateField.sendKeys(jobStartDate);
        appPage.grossMonthlyIncomeField.sendKeys(grossMonthlyIncome);
        appPage.monthlyOvertimeField.sendKeys(monthlyOvertime);
        appPage.monthlyBonusesField.sendKeys(monthlyBonuses);
        appPage.monthlyCommissionsField.sendKeys(monthlyCommissions);
        appPage.monthlyDividentsField.sendKeys(monthlyDividents);

        new Select(appPage.additionalIncomeSourceField1);
        select.selectByIndex(incomeSource);
        appPage.additionalIncomeAmountField1.sendKeys(additionalIncomeAmount);

        new Select(appPage.additionalIncomeSourceField2);
        select.selectByIndex(incomeSource);
        appPage.additionalIncomeAmountField2.sendKeys(additionalIncomeAmount);

        new Select(appPage.additionalIncomeSourceField3);
        select.selectByIndex(incomeSource);
        appPage.additionalIncomeAmountField3.sendKeys(additionalIncomeAmount);

        appPage.nextButton.click();
        Assert.assertEquals(appPage.creditReportPageText.getText(), creditReportPageTextExpected);
    }

    @Test
    public void employmentAndIncomeTestCheckingMonthlyIncomeCalculation() {

        expensesPositiveTest();
        logger.info("Filling the application with proper credentials, " +
                "and checking if the Borrower Total Monthly Income calculation is correct");
        appPage.employerNameField.sendKeys(employerName);
        appPage.jobPositionField.sendKeys(jobPosition);
        appPage.jobCityField.sendKeys(jobCity);

        Select select = new Select(appPage.stateSelect);
        select.selectByIndex(jobState);
        appPage.jobStartDateField.sendKeys(jobStartDate);
        appPage.grossMonthlyIncomeField.sendKeys(grossMonthlyIncome);
        appPage.monthlyOvertimeField.sendKeys(monthlyOvertime);
        appPage.monthlyBonusesField.sendKeys(monthlyBonuses);
        appPage.monthlyCommissionsField.sendKeys(monthlyCommissions);
        appPage.monthlyDividentsField.sendKeys(monthlyDividents);

        Assert.assertEquals(appPage.totalMonthlyIncome.getText(), totalMonthlyIncomeExpected);

    }



    @Test (groups = {"negative"}) // BUG
    public void employmentAndIncomeTestWithNoEmployerName() {

        expensesPositiveTest();
        logger.info("Filling the application and adding an EMPTY SPACE as an Employer Name " +
                "clicking Next button and verifying if we passed the step");
        appPage.employerNameField.sendKeys(emptySpace);
        appPage.jobPositionField.sendKeys(jobPosition);
        appPage.jobCityField.sendKeys(jobCity);

        Select select = new Select(appPage.stateSelect);
        select.selectByIndex(jobState);
        appPage.jobStartDateField.sendKeys(jobStartDate);
        appPage.grossMonthlyIncomeField.sendKeys(grossMonthlyIncome);
        appPage.monthlyOvertimeField.sendKeys(monthlyOvertime);
        appPage.monthlyBonusesField.sendKeys(monthlyBonuses);
        appPage.monthlyCommissionsField.sendKeys(monthlyCommissions);
        appPage.monthlyDividentsField.sendKeys(monthlyDividents);

        appPage.nextButton.click();
        Assert.assertEquals(appPage.creditReportPageText.getText(), creditReportPageTextExpected);
        logger.info(bug);

    }
    @Test (groups = {"negative"}) // BUG
    public void employmentAndIncomeTestWithNegativeMonthlyIncome() {

        expensesPositiveTest();
        appPage.employerNameField.sendKeys(employerName);
        appPage.jobPositionField.sendKeys(jobPosition);
        appPage.jobCityField.sendKeys(jobCity);

        Select select = new Select(appPage.stateSelect);
        select.selectByIndex(jobState);
        appPage.jobStartDateField.sendKeys(jobStartDate);
        logger.info("Filling the application and adding a NEGATIVE NUMBER as a gross monthly income " +
                "clicking Next button and verifying if we passed the step");
        appPage.grossMonthlyIncomeField.sendKeys("-3");
        appPage.monthlyOvertimeField.sendKeys(monthlyOvertime);
        appPage.monthlyBonusesField.sendKeys(monthlyBonuses);
        appPage.monthlyCommissionsField.sendKeys(monthlyCommissions);
        appPage.monthlyDividentsField.sendKeys(monthlyDividents);

        appPage.nextButton.click();
        Assert.assertEquals(appPage.creditReportPageText.getText(), creditReportPageTextExpected);
        logger.info(bug);

    }
    @Test (groups = {"negative"}) // BUG
    public void employmentAndIncomeTestWithNegativeMonthlyOvertime() {

        expensesPositiveTest();
        appPage.employerNameField.sendKeys(employerName);
        appPage.jobPositionField.sendKeys(jobPosition);
        appPage.jobCityField.sendKeys(jobCity);

        Select select = new Select(appPage.stateSelect);
        select.selectByIndex(jobState);
        appPage.jobStartDateField.sendKeys(jobStartDate);
        logger.info("Filling the application and adding a NEGATIVE NUMBER as a monthly overtime amount " +
                "clicking Next button and verifying if we passed the step");
        appPage.grossMonthlyIncomeField.sendKeys(grossMonthlyIncome);
        appPage.monthlyOvertimeField.sendKeys("-3000");
        appPage.monthlyBonusesField.sendKeys(monthlyBonuses);
        appPage.monthlyCommissionsField.sendKeys(monthlyCommissions);
        appPage.monthlyDividentsField.sendKeys(monthlyDividents);

        appPage.nextButton.click();
        Assert.assertEquals(appPage.creditReportPageText.getText(), creditReportPageTextExpected);
        logger.info(bug);

    }
    @Test (groups = {"negative"}) // BUG
    public void employmentAndIncomeTestWithNegativeMonthlyBonuses() {

        expensesPositiveTest();
        appPage.employerNameField.sendKeys(employerName);
        appPage.jobPositionField.sendKeys(jobPosition);
        appPage.jobCityField.sendKeys(jobCity);

        Select select = new Select(appPage.stateSelect);
        select.selectByIndex(jobState);
        appPage.jobStartDateField.sendKeys(jobStartDate);
        logger.info("Filling the application and adding a NEGATIVE NUMBER as a monthly bonuses amount " +
                "clicking Next button and verifying if we passed the step");
        appPage.grossMonthlyIncomeField.sendKeys(grossMonthlyIncome);
        appPage.monthlyOvertimeField.sendKeys(monthlyOvertime);
        appPage.monthlyBonusesField.sendKeys("-2000");
        appPage.monthlyCommissionsField.sendKeys(monthlyCommissions);
        appPage.monthlyDividentsField.sendKeys(monthlyDividents);

        appPage.nextButton.click();
        Assert.assertEquals(appPage.creditReportPageText.getText(), creditReportPageTextExpected);
        logger.info(bug);

    }

    @Test (groups = {"negative"}) // BUG
    public void employmentAndIncomeTestWithNegativeMonthlyCommissions() {

        expensesPositiveTest();
        appPage.employerNameField.sendKeys(employerName);
        appPage.jobPositionField.sendKeys(jobPosition);
        appPage.jobCityField.sendKeys(jobCity);

        Select select = new Select(appPage.stateSelect);
        select.selectByIndex(jobState);
        appPage.jobStartDateField.sendKeys(jobStartDate);
        logger.info("Filling the application and adding a NEGATIVE NUMBER as a monthly commissions amount " +
                "clicking Next button and verifying if we passed the step");
        appPage.grossMonthlyIncomeField.sendKeys(grossMonthlyIncome);
        appPage.monthlyOvertimeField.sendKeys(monthlyOvertime);
        appPage.monthlyBonusesField.sendKeys(monthlyBonuses);
        appPage.monthlyCommissionsField.sendKeys("-2000");
        appPage.monthlyDividentsField.sendKeys(monthlyDividents);

        appPage.nextButton.click();
        Assert.assertEquals(appPage.creditReportPageText.getText(), creditReportPageTextExpected);
        logger.info(bug);

    }

    @Test (groups = {"negative"}) // BUG
    public void employmentAndIncomeTestWithNegativeMonthlyDividends() {

        expensesPositiveTest();
        appPage.employerNameField.sendKeys(employerName);
        appPage.jobPositionField.sendKeys(jobPosition);
        appPage.jobCityField.sendKeys(jobCity);

        Select select = new Select(appPage.stateSelect);
        select.selectByIndex(jobState);
        appPage.jobStartDateField.sendKeys(jobStartDate);
        logger.info("Filling the application and adding a NEGATIVE NUMBER as a monthly dividends amount, " +
                "clicking Next button and verifying if we passed the step");
        appPage.grossMonthlyIncomeField.sendKeys(grossMonthlyIncome);
        appPage.monthlyOvertimeField.sendKeys(monthlyOvertime);
        appPage.monthlyBonusesField.sendKeys(monthlyBonuses);
        appPage.monthlyCommissionsField.sendKeys(monthlyCommissions);
        appPage.monthlyDividentsField.sendKeys("-100000000000");

        appPage.nextButton.click();
        Assert.assertEquals(appPage.creditReportPageText.getText(), creditReportPageTextExpected);
        logger.info(bug);

    }
    @Test
    public void creditReportPositiveTest() {

        employmentAndIncomePositiveTest();
        logger.info("Heading to Credit Report page and verifying the the PAGE TEXT is expected " +
                "and clicking Next button");
        Assert.assertEquals(appPage.creditReportPageText.getText(), creditReportPageTextExpected);
        appPage.nextButton.click();

    }

    @Test
    public void eConsentPageTest(){

        creditReportPositiveTest();
        logger.info("Heading to eConsent page and verifying the the PAGE TEXT is expected");
        Assert.assertEquals(appPage.eConsentPageText.getText(), eConsentPageTextExpected);
    }
    @Test
    public void eConsentPositiveTest(){

        creditReportPositiveTest();
        logger.info("Signing eConsent by using proper credentials, clicking Next button " +
                "and verifying if we passed the step");
        appPage.eConsentFirstNameField.sendKeys(firstName);
        appPage.eConsentLastNameField.sendKeys(lastName);
        appPage.eConsentEmailField.sendKeys(email);
        appPage.agreeButton.click();
        //appPage.doNotAgreeButton.click();
        appPage.nextButton.click();
        Assert.assertEquals(appPage.summaryPageText.getText(), summaryPageTextExpected);
    }
    @Test (groups = {"negative"}) // BUG
    public void eConsentPositiveTestWithWrongFirstName(){

        creditReportPositiveTest();
        logger.info("Signing eConsent by using an EMPTY SPACE as a FIRST NAME, clicking Next button " +
                "and verifying if we passed the step");
        appPage.eConsentFirstNameField.sendKeys(emptySpace);
        appPage.eConsentLastNameField.sendKeys(lastName);
        appPage.eConsentEmailField.sendKeys(email);
        appPage.agreeButton.click();
        appPage.nextButton.click();
        Assert.assertEquals(appPage.summaryPageText.getText(), summaryPageTextExpected);
        logger.info(bug);

    }
    @Test (groups = {"negative"}) // BUG
    public void eConsentPositiveTestWithWrongLastName(){

        creditReportPositiveTest();
        logger.info("Signing eConsent by using an EMPTY SPACE as a LAST NAME, clicking Next button " +
                "and verifying if we passed the step");
        appPage.eConsentFirstNameField.sendKeys(firstName);
        appPage.eConsentLastNameField.sendKeys(emptySpace);
        appPage.eConsentEmailField.sendKeys(email);
        appPage.agreeButton.click();
        appPage.nextButton.click();
        Assert.assertEquals(appPage.summaryPageText.getText(), summaryPageTextExpected);
        logger.info(bug);

    }
    @Test (groups = {"negative"}) // BUG
    public void eConsentPositiveTestWithWrongEmailFormat(){

        creditReportPositiveTest();
        logger.info("Signing eConsent by using a WRONG EMAIL FORMAT (=@h.com), " +
                "clicking Next button and verifying if we passed the step");
        appPage.eConsentFirstNameField.sendKeys(firstName);
        appPage.eConsentLastNameField.sendKeys(lastName);
        appPage.eConsentEmailField.sendKeys(wrongEmailFormat3);
        appPage.agreeButton.click();
        appPage.nextButton.click();
        Assert.assertEquals(appPage.summaryPageText.getText(), summaryPageTextExpected);
        logger.info(bug);
    }

    @Test
    public void summaryPageTest(){

        eConsentPositiveTest();
        logger.info("Heading to SUMMARY page and verifying the the PAGE TEXT is expected " +
                "and then clicking Save button");
        Assert.assertEquals(appPage.summaryPageText.getText(), summaryPageTextExpected);
        appPage.saveButton.click();
        loginPage.actualUsernameButton.click();
        appPage.LogOutButton.click();

    }
}