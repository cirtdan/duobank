package uitests;

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
    ApplicationPage appPage = new ApplicationPage();

    Faker fake = new Faker();

    protected String firstName = fake.name().firstName();
    protected String lastName = fake.name().lastName();
    protected String email = fake.internet().emailAddress();
    protected String pass = fake.internet().password();
    String dateOfBirth = "05/05/1985";
    String ssn = "123-45-6789";
    String cellNumber = "121-100-0101";
    String homeNumber = "222-100-0101";
    String emptySpace = " ";

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

    String expectedWelcomingMessage = "Welcome Back, Automation Testers!";
    String expectedSignUpMessage = "Sign Up";
    String emailExistedMessageExpected = "This email already used";

    String monthlyRentalPaymentExpectedErrorMessage = "THIS FIELD IS REQUIRED.";

    String loanApplicationPageTitle = "Loan Application";

    String realtorInfo = fake.name().fullName() + ", " + fake.internet().emailAddress();
    String paymentSourceAnotherType = "Other type of Down Payment";

    String unrealDateOfBirth = "01/01/2222";
    String married = "Married";
    String single = "Single";

    String purposeOfLoan = "Purchase A Home";
    String estimatedPurchasePrice = "" + (500000 + (int)(Math.random() * 500000));
    String downPaymentAmount = "" + (100000 + (int)(Math.random() * 400000));
    String expectedDownPaymentPercentage = "" + (Integer.parseInt(downPaymentAmount) * 100 / Integer.parseInt(estimatedPurchasePrice));
    String expectedLoanAmount = "" + (Integer.parseInt(estimatedPurchasePrice) - Integer.parseInt(downPaymentAmount));
    String monthlyRentalPayment = "2000";
    String employerName = "Quality Auto LLC";
    String jobPosition = "CFO";
    String jobCity = "Manassas";
    int jobState = 1 + (int)(Math.random() * 2);
    String jobStartDate = "01/01/2020";
    String grossMonthlyIncome = "10000";
    String monthlyOvertime = "3000";
    String monthlyBonuses = "2000";
    String monthlyCommissions = "1500";
    String monthlyDividents = "1200";
    String totalMonthlyIncomeExpected = ""+(Integer.parseInt(grossMonthlyIncome) +
            Integer.parseInt(monthlyOvertime) + Integer.parseInt(monthlyBonuses) +
            Integer.parseInt(monthlyCommissions) + Integer.parseInt(monthlyDividents)) + " $";

    String additionalIncomeAmount = "" + (100 + (int)(Math.random() * 4900));
    int incomeSource = 1 + (int)(Math.random() * 2);

    String preApprovalDetailsPageTextExpected = "PREAPPROVAL DETAILS";
    String expensesPageTextExpected = "EXPENSES";
    String personalInformationPageTextExpected = "PERSONAL INFORMATION";
    String employmentAndIncomePageTextExpected = "EMPLOYMENT AND INCOME";
    String creditReportPageTextExpected = "CREDIT REPORT";
    String eConsentPageTextExpected = "ECONSENT";
    String summaryPageTextExpected = "SUMMARY";
    String duotechTeamPageTitle = "IDSLBD - Top Website Design and Development Company in Bangladesh";

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
