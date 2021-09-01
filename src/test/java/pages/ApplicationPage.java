package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ApplicationPage extends PageBase {

    @FindBy(xpath = "//span[.='Mickey Mouse']")
    public WebElement actualUsernameButton;

    @FindBy(xpath = "//span[.='Mortgage Application']")
    public WebElement mortgageApplicationButton;

    @FindBy(xpath = "//span[.='Application List']")
    public WebElement appListButton;

    @FindBy(xpath = "//input[@id='realtorinfo']")
    public WebElement realtorInfoField;

    @FindBy(xpath = "//label[.='Yes']")
    public WebElement workingWithLoanOfficerYES;

    @FindBy(xpath = "//label[@for='loanofficer2']")
    public WebElement workingWithLoanOfficerNO;

    @FindBy(xpath = "//span[.='Purchase a Home']")
    public WebElement purposeOfLoanButton;

    @FindBy(xpath = "//input[@class='select2-search__field']")
    public WebElement purposeOfLoanField;

    @FindBy(xpath = "//input[@id='estimatedprice']")
    public WebElement estimatedPurchasePriceField;

    @FindBy(xpath = "//input[@id='downpayment']")
    public WebElement downPaymentAmountField;

    @FindBy(xpath = "//input[@id='downpaymentpercentage']")
    public WebElement downPaymentPercentageField;

    @FindBy(xpath = "//input[@id='loanamount']")
    public WebElement calculatedLoanAmountValue;

    @FindBy(xpath = "//span[@role='textbox' and .='Checking/Savings (most recent bank statement)']")
    public WebElement downPaymentSourceButton;

    @FindBy(xpath = "//input[@class='select2-search__field']")
    public WebElement downPaymentSourceField;

    @FindBy(xpath = "//a[.='Next']")
    public WebElement nextButton;

    @FindBy(xpath = "//a[.='Previous']")
    public WebElement previousButton;

    @FindBy(xpath = "//label[@for='coborrower1']")
    public WebElement withCoborrowerCheckbox;

    @FindBy(xpath = "//input[@id='b_firstName']")
    public WebElement firstNameField;

    @FindBy(xpath = "//input[@id='b_lastName']")
    public WebElement lastNameField;

    @FindBy(xpath = "//input[@id='b_email']")
    public WebElement emailField;

    @FindBy(xpath = "//input[@id='b_dob']")
    public WebElement dateOfBirthField;

    @FindBy(xpath = "//input[@id='b_ssn']")
    public WebElement ssnField;

    @FindBy(xpath = "//span[@id='select2-b_marital-container']")
    public WebElement maritalStatusButton;

    @FindBy(xpath = "//input[@class='select2-search__field']")
    public WebElement maritalStatusField;

    @FindBy(xpath = "//input[@id='b_cell']")
    public WebElement cellPhoneField;

    @FindBy(xpath = "//input[@placeholder='Home Phone']")
    public WebElement homePhoneField;

    // region Co-Borrower
    @FindBy(xpath = "//input[@id='c_firstName']")
    public WebElement coborrowerFirstNameField;

    @FindBy(xpath = "//input[@id='c_lastName']")
    public WebElement coborrowerLastNameField;

    @FindBy(xpath = "//input[@id='c_email']")
    public WebElement coborrowerEmailField;

    @FindBy(xpath = "//input[@id='c_dob']")
    public WebElement coborrowerDOBField;

    @FindBy(xpath = "//input[@id='c_ssn']")
    public WebElement coborrowerSSNField;

    @FindBy(xpath = "//span[@id='select2-c_marital-container']")
    public WebElement coborrowerMaritalStatusButton;

    @FindBy(xpath = "//input[@class='select2-search__field']")
    public WebElement coborrowerMaritalStatusField;

    @FindBy(xpath = "//input[@id='c_cell']")
    public WebElement coborrowerCellField;

    @FindBy(xpath = "//input[@id='co-employername1']")
    public WebElement coborrowerEmployer;

    @FindBy(xpath = "//input[@id='co-position1']")
    public WebElement coborrowerPosition;

    @FindBy(xpath = "//input[@id='co-city']")
    public WebElement coborrowerCity;

    @FindBy(xpath = "//input[@id='co-state1']")
    public WebElement coborrowerState;

    @FindBy(xpath = "//input[@id='co-start_date1']")
    public WebElement coborrowerStartDate;

    @FindBy(xpath = "//input[@id='co-grossmonthlyincome']")
    public WebElement coborrowerGrossMonthlyIncome;

    @FindBy(xpath = "//input[@id='co-monthlyovertime']")
    public WebElement coborrowerMonthlyOvertime;

    @FindBy(xpath = "//input[@id='co-monthlybonuses']")
    public WebElement coborrowerMonthlyBonuses;

    @FindBy(xpath = "//input[@id='co-monthlycommission']")
    public WebElement coborrowerMonthlyCommission;

    @FindBy(xpath = "//input[@id='co-monthlydividents']")
    public WebElement coborrowerMonthlyDividents;





    // endregion

    @FindBy(xpath = "//label[@for='expense1']")
    public WebElement rentCheckbox;

    @FindBy(xpath = "//label[.='Own']")
    public WebElement ownCheckbox;

    @FindBy(xpath = "//input[@name='monthly_rental_payment']")
    public WebElement monthlyRentalPaymentField;

    @FindBy(xpath = "//input[@id='firtmortagagetotalpayment']")
    public WebElement firstMortgageTotalPaymentField;

    @FindBy(xpath = "//input[@id='employername1']")
    public WebElement employerNameField;

    @FindBy(xpath = "//input[@id='position1']")
    public WebElement jobPositionField;

    @FindBy(xpath = "//input[@id='city']")
    public WebElement jobCityField;

    @FindBy(xpath = "//select[@id='state1']")
    public WebElement stateSelect;

    @FindBy(xpath = "//input[@id='start_date1']")
    public WebElement jobStartDateField;

    @FindBy(xpath = "//input[@id='grossmonthlyincome']")
    public WebElement grossMonthlyIncomeField;

    @FindBy(xpath = "//input[@id='monthlyovertime']")
    public WebElement monthlyOvertimeField;

    @FindBy(xpath = "//input[@id='monthlybonuses']")
    public WebElement monthlyBonusesField;

    @FindBy(xpath = "//input[@id='monthlycommission']")
    public WebElement monthlyCommissionsField;

    @FindBy(xpath = "//input[@id='monthlydividents']")
    public WebElement monthlyDividentsField;

    @FindBy(xpath = "//lebel[.='Borrower Total Monthly Income']/..//span[contains(text(), ' $')]")
    public WebElement totalMonthlyIncome;

    @FindBy(xpath = "//select[@id='incomesource1']")
    public WebElement additionalIncomeSourceField1;

    @FindBy(xpath = "//select[@id='incomesource2']")
    public WebElement additionalIncomeSourceField2;

    @FindBy(xpath = "//select[@id='incomesource3']")
    public WebElement additionalIncomeSourceField3;

    @FindBy(xpath = "//input[@id='amount1']")
    public WebElement additionalIncomeAmountField1;

    @FindBy(xpath = "//input[@id='amount2']")
    public WebElement additionalIncomeAmountField2;

    @FindBy(xpath = "//input[@id='amount3']")
    public WebElement additionalIncomeAmountField3;

    @FindBy(xpath = "//input[@id='eConsentdeclarerFirstName']")
    public WebElement eConsentFirstNameField;

    @FindBy(xpath = "//input[@id='eConsentdeclarerLastName']")
    public WebElement eConsentLastNameField;

    @FindBy(xpath = "//input[@id='eConsentdeclarerEmail']")
    public WebElement eConsentEmailField;

    @FindBy(xpath = "//label[.='Agree']")
    public WebElement agreeButton;

    @FindBy(xpath = "//label[@for='dontagree']")
    public WebElement doNotAgreeButton;

    @FindBy(xpath = "//a[.='Save']")
    public WebElement saveButton;

    @FindBy(xpath = "//label[@id='monthlyrentalpayment-error']")
    public WebElement monthlyRentalPaymentErrorMessage;

    @FindBy(xpath = "//i[@title='Your current monthly rent payment.']")
    public WebElement monthlyRentalPaymentSign;

    @FindBy(xpath = "//label[@for='privacypolicy']")
    public WebElement privatePolicyCheckBox;

    @FindBy(xpath = "//a[.='Privacy Policy']")
    public WebElement privatePolicyLink;

    @FindBy(xpath = "//span[.='PreApproval Details']")
    public WebElement preApprovalDetailsPageText;

    @FindBy(xpath = "//span[.='Personal Information']")
    public WebElement personalInformationPageText;

    @FindBy(xpath = "//span[.='Expenses']")
    public WebElement expensesPageText;

    @FindBy(xpath = "//span[.='Employment and Income']")
    public WebElement employmentAndIncomePageText;

    @FindBy(xpath = "//span[.='Credit Report']")
    public WebElement creditReportPageText;

    @FindBy(xpath = "//select[@id='eConsentdeclarer']")
    public WebElement eConsentDeclarer;

    @FindBy(xpath = "//span[.='eConsent']")
    public WebElement eConsentPageText;

    @FindBy(xpath = "//span[.='Summary']")
    public WebElement summaryPageText;

    @FindBy(xpath = "//a[.='Loan']")
    public WebElement loanButton;

    @FindBy(xpath = "//i[@class='bx bx-home-alt']")
    public WebElement homeButton;

    @FindBy(xpath = "//a//i")
    public WebElement LogOutButton;

    @FindBy(xpath = "//a[.='Duotech Team']")
    public WebElement duotechTeamButton;

    @FindBy(xpath = "//a[@id='clear1']")
    public WebElement clear1Button;

    @FindBy(xpath = "//button[.='Yes!']")
    public WebElement alertYesButton;

    @FindBy(xpath = "//div[@class='Employer']/a")
    public WebElement employer1text;

    @FindBy(xpath = "//label[@for='expense2']")
    public WebElement ownButton;

    @FindBy(xpath = "(//button[.='Add Another Employer'])[1]")
    public WebElement addAnotherEmployerButton;
    @FindBy(xpath = "//a[.='Clear']")
    public WebElement clearButton;
    @FindBy(xpath = "//button[.='Cancel']")
    public WebElement cancelOnAlert;
    @FindBy(xpath = "//input[@type='search']")
    public WebElement searchBoxOnApplicationList;
    @FindBy(xpath = "//*[@id='DataTables_Table_0']/tbody/tr/td[2]")
    public WebElement checkLineOfList;
}
