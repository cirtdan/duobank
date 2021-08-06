package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.github.javafaker.Faker;
import com.sun.javafx.tools.ant.CSSToBinTask;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.SeleniumUtils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestBase {

    protected WebDriver driver;

    Faker fake = new Faker();

    String firstName = fake.name().firstName();
    String lastName = fake.name().lastName();
    String email = fake.internet().emailAddress();
    String pass = fake.internet().password();

    String alternativeEmail = fake.internet().emailAddress();

    protected static ExtentReports reporter;
    protected static ExtentSparkReporter htmlReporter;
    protected static ExtentTest logger;

    @BeforeSuite
    public void setupReport(){

        reporter = new ExtentReports();
        String path = System.getProperty("user.dir") + "/test-output/extentReports/index.html";
        htmlReporter = new ExtentSparkReporter(path);
        htmlReporter.config().setReportName("DUOBANK AUTOMATION TEST");

        reporter.attachReporter(htmlReporter);

        // Configuration settings
        reporter.setSystemInfo("Tester", "Rafael Azizov");
        reporter.setSystemInfo("Environment", "TEST_ENV");
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

        //Driver.quitDriver();
    }

    @AfterSuite
    public void tearDownReport(){
        reporter.flush();
    }

}
