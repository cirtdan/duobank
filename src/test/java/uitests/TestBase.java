package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.ApplicationPage;
import pages.LoginPage;
import pages.SignUpPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.SeleniumUtils;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class TestBase {

    protected WebDriver driver;

    LoginPage loginPage = new LoginPage();
    SignUpPage signup = new SignUpPage();
    protected ApplicationPage appPage = new ApplicationPage();

    Faker fake = new Faker();

    protected String firstName = fake.name().firstName();
    protected String lastName = fake.name().lastName();
    protected String email = fake.internet().emailAddress();
    protected String pass = fake.internet().password();
    protected String dateOfBirth = "05/05/1985";
    protected String ssn = "123-45-6789";
    protected String cellNumber = "121-100-0101";
    protected String homeNumber = "222-100-0101";
    protected String emptySpace = " ";

    protected String testerFirstName = ConfigReader.getProperty("firstName");
    protected String testerLastName = ConfigReader.getProperty("lastName");
    protected String testerEmail = ConfigReader.getProperty("email");
    protected String testerPassword = ConfigReader.getProperty("pass");

    String alternativeEmail = fake.internet().emailAddress();
    String wrongEmailFormat = "@gmail.com";
    String wrongEmailFormat2 = "XA-12@io-.com";
    String wrongEmailFormat3 = "=@h.com";
    String elonSonsName = "X Æ A-12,";

    String employer1ExpectedText = "Employer 1";

    String expectedUsername = ConfigReader.getProperty("firstName") + " " + ConfigReader.getProperty("lastName");

    protected String loginUrl = ConfigReader.getProperty("url");
    protected String dashboardUrl = ConfigReader.getProperty("dashboardUrl");
    protected String mortgageAppUrl = ConfigReader.getProperty("mortgageAppUrl");
    protected String appListUrl = ConfigReader.getProperty("appListUrl");

    protected String expectedWelcomingMessage = "Welcome Back, Automation Testers!";
    protected String expectedSignUpMessage = "Sign Up";
    protected String emailExistedMessageExpected = "This email already used";

    protected String monthlyRentalPaymentExpectedErrorMessage = "THIS FIELD IS REQUIRED.";

    protected String loanApplicationPageTitle = "Loan Application";

    protected String realtorInfo = fake.name().fullName() + ", " + fake.internet().emailAddress();
    protected String paymentSourceAnotherType = "Other type of Down Payment";

    protected String unrealDateOfBirth = "01/01/2222";
    protected String married = "Married";
    protected String single = "Single";

    protected String purposeOfLoan = "Purchase A Home";
    protected String estimatedPurchasePrice = "" + (500000 + (int)(Math.random() * 500000));
    protected String downPaymentAmount = "" + (100000 + (int)(Math.random() * 400000));
    protected String expectedDownPaymentPercentage = "" + (Integer.parseInt(downPaymentAmount) * 100 / Integer.parseInt(estimatedPurchasePrice));
    protected String expectedLoanAmount = "" + (Integer.parseInt(estimatedPurchasePrice) - Integer.parseInt(downPaymentAmount));
    protected String monthlyRentalPayment = "2000";
    protected String employerName = "Quality Auto LLC";
    protected String jobPosition = "CFO";
    protected String jobCity = "Manassas";
    protected int jobState = 1 + (int)(Math.random() * 2);
    protected String jobStartDate = "01/01/2020";
    protected String grossMonthlyIncome = "10000";
    protected String monthlyOvertime = "3000";
    protected String monthlyBonuses = "2000";
    protected String monthlyCommissions = "1500";
    protected String monthlyDividents = "1200";
    protected String totalMonthlyIncomeExpected = ""+(Integer.parseInt(grossMonthlyIncome) +
            Integer.parseInt(monthlyOvertime) + Integer.parseInt(monthlyBonuses) +
            Integer.parseInt(monthlyCommissions) + Integer.parseInt(monthlyDividents)) + " $";

    protected String additionalIncomeAmount = "" + (100 + (int)(Math.random() * 4900));
    protected int incomeSource = 1 + (int)(Math.random() * 2);

    protected String preApprovalDetailsPageTextExpected = "PREAPPROVAL DETAILS";
    protected String expensesPageTextExpected = "EXPENSES";
    protected String personalInformationPageTextExpected = "PERSONAL INFORMATION";
    protected String employmentAndIncomePageTextExpected = "EMPLOYMENT AND INCOME";
    protected String creditReportPageTextExpected = "CREDIT REPORT";
    protected String eConsentPageTextExpected = "ECONSENT";
    protected String summaryPageTextExpected = "SUMMARY";
    protected String duotechTeamPageTitle = "IDSLBD - Top Website Design and Development Company in Bangladesh";

    String bug = "THERE IS A BUG";

    protected static ExtentReports reporter;
    protected static ExtentSparkReporter htmlReporter;
    protected static ExtentTest logger;

    @BeforeSuite  (alwaysRun = true)
    public void setupReport(){

        reporter = new ExtentReports();
        String path = System.getProperty("user.dir") + "/test-output/extentReports/index.html";
        htmlReporter = new ExtentSparkReporter(path);
        htmlReporter.config().setReportName("DUOBANK AUTOMATION TEST");

        reporter.attachReporter(htmlReporter);

        // Configuration settings
        reporter.setSystemInfo("Tester", "Rafael Azizov");
        reporter.setSystemInfo("Environment", "DUOBANK APPLICATION");
        reporter.setSystemInfo("Browser", ConfigReader.getProperty("browser"));
    }

    @BeforeMethod (alwaysRun = true)
    @Parameters("browser")
    public void setupMethod(@Optional String browser, Method method){

        driver = Driver.getDriver(browser);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(ConfigReader.getProperty("url"));

        logger = reporter.createTest("TEST CASE: " + method.getName());


    }


    @AfterMethod  (alwaysRun = true)
    public void tearDownMethod(ITestResult result) {

        if(result.getStatus()==ITestResult.SUCCESS){
            logger.pass("Test " + result.getName() + " Passed");
        } else if(result.getStatus()==ITestResult.SKIP){
            logger.skip("Test " + result.getName() + " Skipped");
        } else if(result.getStatus()==ITestResult.FAILURE){
            logger.fail("Test " + result.getName() + " Failed");
            logger.fail("Exception: " + result.getThrowable());

            String path = SeleniumUtils.getScreenshot("failureScreenshot");
            logger.addScreenCaptureFromPath(path);
        }

        Driver.quitDriver();
    }

    @AfterSuite  (alwaysRun = true)
    public void tearDownReport(){
        reporter.flush();
    }


     //_______________________Tetiana Kucherova doing sprint2  home work here:P___________________________
    String ExpectedMatchingMSG ="No matching records found";

    String ExpectedTextOfList1 ="10";
    String ExpectedTextOfList2 ="20";
    String ExpectedTextOfList3 ="30";
    String ExpectedTextOfList4 ="40";
    String ExpectedTextOfList5 ="45";

}
