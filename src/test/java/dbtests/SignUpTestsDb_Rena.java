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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SignUpTestsDb_Rena extends TestBase {


    //signup a new user thru UI
    @Test
    public void verifyUserSignUpThroughDatabase() {


        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();

        SignUpPage signup = new SignUpPage();
        signup.signUp(firstName, lastName, email, password);

        DBUtility.createConnection();
        String query = "select *from tbl_user where email='" + email + "'";
        System.out.println(query);
        logger.info("Store the user information into the list of maps");
        List<Map<String, Object>> listOfMaps = DBUtility.getQueryResultListOfMaps(query);

        logger.info("Retrieve the user information needed");
        Map<String, Object> map = listOfMaps.get(0);
        System.out.println(map);

        String actualEmail = (String) map.get("email");
        System.out.println(actualEmail);
        Assert.assertEquals(actualEmail, email);

        String actualPassword = (String) map.get("password");
        logger.info("Get encrypted version on the Password");
        String md5hash = DigestUtils.md5Hex(password);
        System.out.println(actualPassword);
        Assert.assertEquals(actualPassword, md5hash);

        String actualFirstName = (String) map.get("first_name");
        logger.info("Get the first_name information");
        System.out.println(actualFirstName);
        Assert.assertEquals(actualFirstName, firstName);

        String actualLastName = (String) map.get("last_name");
        logger.info("Get the last_name information");
        System.out.println(actualLastName);
        Assert.assertEquals(actualLastName, lastName);

        DBUtility.close();
    }


    @Test
    public void verifyColumnNamesOnUserTable() {

        DBUtility.createConnection();
        logger.info("Store expected column names");
        List<String> expectedColumnName = Arrays.asList("id", "email", "password",
                "first_name", "last_name", "phone", "image", "type", "created_at", "modified_at", "zone_id",
                "church_id", "country_id", "active");

        logger.info("Retrieving actual column names from SQL");
        List<String> actualColumnNames = DBUtility.getColumnNames("select *from tbl_user");

        Assert.assertEquals(expectedColumnName, actualColumnNames);
        DBUtility.close();
    }


    @Test
    public void verifySQLUpdates() {
        DBUtility.createConnection();
        logger.info("Making update and verify updates");
        String expectedFirstName = "Rena";
        String query = "update tbl_user set first_name = '"+expectedFirstName+"' where email='rena_mammadzada@hotmail.com'";
        DBUtility.updateQuery(query);

        String query1 = "select *from tbl_user where email='rena_mammadzada@hotmail.com'";
        List<Map<String, Object>> listOfMaps = DBUtility.getQueryResultListOfMaps(query1);

        logger.info("Retrieve updated information needed");
        Map<String, Object> map = listOfMaps.get(0);
        System.out.println(map);
        Assert.assertEquals(map.get("first_name"),expectedFirstName);

        DBUtility.close();

    }


}
