package dbtests;

import com.github.javafaker.Faker;
import org.apache.commons.codec.digest.DigestUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SignUpPage;
import uitests.TestBase;
import utilities.CSVReader;
import utilities.ConfigReader;
import utilities.DBUtility;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SignUpTestsDb_Rena extends TestBase {


    //signup a new user thru UI
    @Test (groups = {"smoke","sprint_2"})
    public void verifyUserSignUpThroughDatabase() {


        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();

        SignUpPage signup = new SignUpPage();
        signup.signUp(firstName, lastName, email, password);

        DBUtility.createConnection();
        String query = "select *from tbl_user where email='"+email+"'";
        System.out.println(query);
        logger.info("Store the user information into the list of maps");
        List<Map<String, Object>> listOfMaps = DBUtility.getQueryResultListOfMaps(query);

        logger.info("Retrieve the user information needed");
        Map<String, Object> map = listOfMaps.get(0);
        System.out.println(map);

        String actualEmail = (String) map.get("email");
        System.out.println(actualEmail);
        Assert.assertEquals(actualEmail, email);

//        String actualPassword = (String) map.get("password");
//        logger.info("Get encrypted version on the Password");
//        String md5hash = DigestUtils.md5Hex(password);
//        System.out.println(actualPassword);
//        Assert.assertEquals(actualPassword, password);

        String actualFirstName = (String) map.get("first_name");
        System.out.println(actualFirstName);
        Assert.assertEquals(actualFirstName, firstName);

        String actualLastName = (String) map.get("last_name");
        System.out.println(actualLastName);
        Assert.assertEquals(actualLastName, lastName);






    }







}
