package tests;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MortgageApplicationTests extends TestBase {

    @Test
    public void preapprovalDetailsPositiveTest() {

        loginPage.login(testerEmail, testerPassword);
        appPage.mortgageApplicationButton.click();
        appPage.realtorInfoField.sendKeys(realtorInfo);
        //appPage.workingWithLoanOfficerYES.click(); // ARE YOU WORKING WITH A REALTOR? - YES
        appPage.workingWithLoanOfficerNO.click(); // ARE YOU WORKING WITH A REALTOR? - NO

        appPage.purposeOfLoanButton.click();
        appPage.purposeOfLoanField.sendKeys(purposeOfLoan, Keys.ENTER);
        appPage.estimatedPurchasePriceField.sendKeys(estimatedPurchasePrice);
        appPage.downPaymentAmountField.sendKeys(downPaymentAmount);
        appPage.downPaymentPercentageField.clear();
        appPage.downPaymentPercentageField.sendKeys(downPaymentPercentage);
        String calculatedLoanAmount = appPage.calculatedLoanAmountValue.getAttribute("value");
        Assert.assertEquals(calculatedLoanAmount, expectedLoanAmount);

        appPage.downPaymentSourceButton.click();
        appPage.downPaymentSourceField.sendKeys("Other type of Down Payment", Keys.ENTER);
        appPage.nextButton.click();

    }
    @Test // BAG
    public void preapprovalDetailsNegativeTestWithWrongRealtorInfo() {

        loginPage.login(testerEmail, testerPassword);
        appPage.mortgageApplicationButton.click();
        logger.info("Filling the application using EMPTY SPACE as a realtor information");
        appPage.realtorInfoField.sendKeys(" ");
        //appPage.workingWithLoanOfficerYES.click(); // ARE YOU WORKING WITH A REALTOR? - YES
        appPage.workingWithLoanOfficerNO.click(); // ARE YOU WORKING WITH A REALTOR? - NO

        appPage.purposeOfLoanButton.click();
        appPage.purposeOfLoanField.sendKeys(purposeOfLoan, Keys.ENTER);
        appPage.estimatedPurchasePriceField.sendKeys(estimatedPurchasePrice);
        appPage.downPaymentAmountField.sendKeys(downPaymentAmount);
        appPage.downPaymentPercentageField.clear();
        appPage.downPaymentPercentageField.sendKeys(downPaymentPercentage);
        String calculatedLoanAmount = appPage.calculatedLoanAmountValue.getAttribute("value");
        Assert.assertEquals(calculatedLoanAmount, expectedLoanAmount);

        appPage.downPaymentSourceButton.click();
        appPage.downPaymentSourceField.sendKeys("Other type of Down Payment", Keys.ENTER);
        appPage.nextButton.click();

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
        appPage.maritalStatusField.sendKeys("Married", Keys.ENTER);
        appPage.cellPhoneField.sendKeys(cellNumber);
        appPage.homePhoneField.sendKeys(homeNumber);
        appPage.nextButton.click();
    }

    @Test // BAG
    public void personalInformationNegativeTestWithEmptyName() {

        preapprovalDetailsPositiveTest();
        logger.info("Adding First Name and Last Name using ONLY EMPTY SPACE");
        appPage.firstNameField.sendKeys(" ");
        appPage.lastNameField.sendKeys(" ");
        appPage.emailField.sendKeys(email);
        appPage.dateOfBirthField.sendKeys(dateOfBirth);
        appPage.ssnField.sendKeys(ssn);
        appPage.maritalStatusButton.click();
        appPage.maritalStatusField.sendKeys("Married", Keys.ENTER);
        appPage.cellPhoneField.sendKeys(cellNumber);
        appPage.homePhoneField.sendKeys(homeNumber);
        Assert.assertEquals(appPage.lastNameField.getText(), "");
        appPage.nextButton.click();

    }
    @Test // BAG
    public void personalInformationNegativeTestWithWrongDateOfBirth() {

        preapprovalDetailsPositiveTest();
        logger.info("Adding 01/01/2222 as a DATE OF BIRTH");
        appPage.firstNameField.sendKeys(firstName);
        appPage.lastNameField.sendKeys(lastName);
        appPage.emailField.sendKeys(email);
        appPage.dateOfBirthField.sendKeys("01/01/2222");
        appPage.ssnField.sendKeys(ssn);
        appPage.maritalStatusButton.click();
        appPage.maritalStatusField.sendKeys("Married", Keys.ENTER);
        appPage.cellPhoneField.sendKeys(cellNumber);
        appPage.homePhoneField.sendKeys(homeNumber);
        appPage.nextButton.click();

    }
    @Test // BAG
    public void personalInformationNegativeTestWithWrongSSN() {

        preapprovalDetailsPositiveTest();
        logger.info("Adding SSN number using 1");
        appPage.firstNameField.sendKeys(firstName);
        appPage.lastNameField.sendKeys(lastName);
        appPage.emailField.sendKeys(email);
        appPage.dateOfBirthField.sendKeys(dateOfBirth);
        appPage.ssnField.sendKeys("1");
        appPage.maritalStatusButton.click();
        appPage.maritalStatusField.sendKeys("Married", Keys.ENTER);
        appPage.cellPhoneField.sendKeys(cellNumber);
        appPage.homePhoneField.sendKeys(homeNumber);
        appPage.nextButton.click();

    }
    @Test // BAG
    public void personalInformationTestForASingleCustomer() {

        preapprovalDetailsPositiveTest();
        logger.info("Adding Single as a Marital Status");
        appPage.firstNameField.sendKeys(firstName);
        appPage.lastNameField.sendKeys(lastName);
        appPage.emailField.sendKeys(email);
        appPage.dateOfBirthField.sendKeys(dateOfBirth);
        appPage.ssnField.sendKeys(ssn);
        appPage.maritalStatusButton.click();
        appPage.maritalStatusField.sendKeys("Single", Keys.ENTER);
        appPage.cellPhoneField.sendKeys(cellNumber);
        appPage.homePhoneField.sendKeys(homeNumber);
        appPage.nextButton.click();

    }
    @Test // BAG
    public void personalInformationTestWithWrongCellNumber() {

        preapprovalDetailsPositiveTest();
        logger.info("Adding 1 as a sell number");
        appPage.firstNameField.sendKeys(firstName);
        appPage.lastNameField.sendKeys(lastName);
        appPage.emailField.sendKeys(email);
        appPage.dateOfBirthField.sendKeys(dateOfBirth);
        appPage.ssnField.sendKeys(ssn);
        appPage.maritalStatusButton.click();
        appPage.maritalStatusField.sendKeys("Married", Keys.ENTER);
        appPage.cellPhoneField.sendKeys("1");
        appPage.homePhoneField.sendKeys(homeNumber);
        appPage.nextButton.click();

    }
    @Test
    public void expensesPositiveTest() {

        personalInformationPositiveTest();
        logger.info("Adding monthly rental payment amount and clicking next button");
        appPage.monthlyRentalPaymentField.sendKeys(monthlyRentalPayment);
        appPage.nextButton.click();

    }
    @Test
    public void expensesTestCheckingErrorText() {

        personalInformationPositiveTest();
        logger.info("No adding monthly rental payment amount, clicking next button and checking an error message");
        appPage.monthlyRentalPaymentSign.click();
        appPage.nextButton.click();
        String actualErrorMessage = appPage.monthlyRentalPaymentErrorMessage.getText();
        String expectedErrorMessage = "THIS FIELD IS REQUIRED.";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);

    }
    @Test
    public void employmentAndIncomePositiveTest() {

        expensesPositiveTest();
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
    }
    @Test // BAG
    public void employmentAndIncomeTestWithNoEmployerName() {

        expensesPositiveTest();
        logger.info("Filling the application and adding an EMPTY SPACE as an Employer Name");
        appPage.employerNameField.sendKeys(" ");
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
        appPage.nextButton.click();
    }
    @Test // BAG
    public void employmentAndIncomeTestWithNegativeMonthlyIncome() {

        expensesPositiveTest();
        appPage.employerNameField.sendKeys(employerName);
        appPage.jobPositionField.sendKeys(jobPosition);
        appPage.jobCityField.sendKeys(jobCity);

        Select select = new Select(appPage.stateSelect);
        select.selectByIndex(jobState);
        appPage.jobStartDateField.sendKeys(jobStartDate);
        logger.info("Filling the application and adding a NEGATIVE NUMBER as a gross monthly income");
        appPage.grossMonthlyIncomeField.sendKeys("-3");
        appPage.monthlyOvertimeField.sendKeys(monthlyOvertime);
        appPage.monthlyBonusesField.sendKeys(monthlyBonuses);
        appPage.monthlyCommissionsField.sendKeys(monthlyCommissions);
        appPage.monthlyDividentsField.sendKeys(monthlyDividents);

        Assert.assertNotEquals(appPage.totalMonthlyIncome.getText(), totalMonthlyIncomeExpected);
        appPage.nextButton.click();
    }
    @Test // BAG
    public void employmentAndIncomeTestWithNegativeMonthlyOvertime() {

        expensesPositiveTest();
        appPage.employerNameField.sendKeys(employerName);
        appPage.jobPositionField.sendKeys(jobPosition);
        appPage.jobCityField.sendKeys(jobCity);

        Select select = new Select(appPage.stateSelect);
        select.selectByIndex(jobState);
        appPage.jobStartDateField.sendKeys(jobStartDate);
        logger.info("Filling the application and adding a NEGATIVE NUMBER as a monthly overtime amount");
        appPage.grossMonthlyIncomeField.sendKeys(grossMonthlyIncome);
        appPage.monthlyOvertimeField.sendKeys("-3000");
        appPage.monthlyBonusesField.sendKeys(monthlyBonuses);
        appPage.monthlyCommissionsField.sendKeys(monthlyCommissions);
        appPage.monthlyDividentsField.sendKeys(monthlyDividents);

        Assert.assertNotEquals(appPage.totalMonthlyIncome.getText(), totalMonthlyIncomeExpected);
        appPage.nextButton.click();
    }
    @Test // BAG
    public void employmentAndIncomeTestWithNegativeMonthlyBonuses() {

        expensesPositiveTest();
        appPage.employerNameField.sendKeys(employerName);
        appPage.jobPositionField.sendKeys(jobPosition);
        appPage.jobCityField.sendKeys(jobCity);

        Select select = new Select(appPage.stateSelect);
        select.selectByIndex(jobState);
        appPage.jobStartDateField.sendKeys(jobStartDate);
        logger.info("Filling the application and adding a NEGATIVE NUMBER as a monthly bonuses amount");
        appPage.grossMonthlyIncomeField.sendKeys(grossMonthlyIncome);
        appPage.monthlyOvertimeField.sendKeys(monthlyOvertime);
        appPage.monthlyBonusesField.sendKeys("-2000");
        appPage.monthlyCommissionsField.sendKeys(monthlyCommissions);
        appPage.monthlyDividentsField.sendKeys(monthlyDividents);

        Assert.assertNotEquals(appPage.totalMonthlyIncome.getText(), totalMonthlyIncomeExpected);
        appPage.nextButton.click();
    }

    @Test // BAG
    public void employmentAndIncomeTestWithNegativeMonthlyCommissions() {

        expensesPositiveTest();
        appPage.employerNameField.sendKeys(employerName);
        appPage.jobPositionField.sendKeys(jobPosition);
        appPage.jobCityField.sendKeys(jobCity);

        Select select = new Select(appPage.stateSelect);
        select.selectByIndex(jobState);
        appPage.jobStartDateField.sendKeys(jobStartDate);
        logger.info("Filling the application and adding a NEGATIVE NUMBER as a monthly commissions amount");
        appPage.grossMonthlyIncomeField.sendKeys(grossMonthlyIncome);
        appPage.monthlyOvertimeField.sendKeys(monthlyOvertime);
        appPage.monthlyBonusesField.sendKeys(monthlyBonuses);
        appPage.monthlyCommissionsField.sendKeys("-2000");
        appPage.monthlyDividentsField.sendKeys(monthlyDividents);

        Assert.assertNotEquals(appPage.totalMonthlyIncome.getText(), totalMonthlyIncomeExpected);
        appPage.nextButton.click();
    }

    @Test // BAG
    public void employmentAndIncomeTestWithNegativeMonthlyDividends() {

        expensesPositiveTest();
        appPage.employerNameField.sendKeys(employerName);
        appPage.jobPositionField.sendKeys(jobPosition);
        appPage.jobCityField.sendKeys(jobCity);

        Select select = new Select(appPage.stateSelect);
        select.selectByIndex(jobState);
        appPage.jobStartDateField.sendKeys(jobStartDate);
        logger.info("Filling the application and adding a NEGATIVE NUMBER as a monthly dividends amount");
        appPage.grossMonthlyIncomeField.sendKeys(grossMonthlyIncome);
        appPage.monthlyOvertimeField.sendKeys(monthlyOvertime);
        appPage.monthlyBonusesField.sendKeys(monthlyBonuses);
        appPage.monthlyCommissionsField.sendKeys(monthlyCommissions);
        appPage.monthlyDividentsField.sendKeys("-100000000000");

        Assert.assertNotEquals(appPage.totalMonthlyIncome.getText(), totalMonthlyIncomeExpected);
        appPage.nextButton.click();
    }
    @Test
    public void creditReportPositiveTest() {

        employmentAndIncomePositiveTest();
        appPage.nextButton.click();

    }
    @Test
    public void eConsentPositiveTest(){

        creditReportPositiveTest();
        appPage.eConsentFirstNameField.sendKeys(firstName);
        appPage.eConsentLastNameField.sendKeys(lastName);
        appPage.eConsentEmailField.sendKeys(email);
        //appPage.agreeButton.click();
        appPage.doNotAgreeButton.click();
        appPage.nextButton.click();
    }

    @Test
    public void summaryPositiveTest(){

        eConsentPositiveTest();
        appPage.saveButton.click();


    }

}