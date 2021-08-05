package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestBase {

    WebDriver driver;

//    String firstName = ConfigReader.getProperty("firstName");
//    String lastName = ConfigReader.getProperty("lastName");
//    String login = ConfigReader.getProperty("email");
//    String pass = ConfigReader.getProperty("pass");

    Faker fake = new Faker();

    String firstName = fake.name().firstName();
    String lastName = fake.name().lastName();
    String email = fake.internet().emailAddress();
    String pass = fake.internet().password();

    String alternativeEmail = fake.internet().emailAddress();


    @BeforeMethod (alwaysRun = true)
    @Parameters("browser")
    public void setupMethod(@Optional String browser){

        driver = Driver.getDriver(browser);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(ConfigReader.getProperty("url"));


    }


    @AfterMethod  (alwaysRun = true)
    public void tearDownMethod(){

        //Driver.quitDriver();
    }

}
