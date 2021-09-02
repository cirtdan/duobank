package dbtests;

import com.github.javafaker.Faker;
import org.apache.commons.codec.digest.DigestUtils;
import org.testng.annotations.Test;
import pages.ApplicationPage;
import pages.LoginPage;
import pages.SignUpPage;
import uitests.TestBase;
import utilities.DBUtility;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

public class DataMappingTests_Rafael extends TestBase {

    @Test (groups = {"sprint_3"})
    public void verifyUserSignUpFlowFromUIToDatabase_Faker() {

        logger.info("Create a user's First Name, Last Name, Email and Password using fake data");
        Faker fake = new Faker();
        String expectedFirstName = fake.name().firstName();
        String expectedLastName = fake.name().lastName();
        String expectedEmail = fake.internet().emailAddress();
        String expectedPassword = fake.internet().password();
        logger.info("Get encrypted version on the Password");
        String md5hash = DigestUtils.md5Hex(expectedPassword);

        logger.info("SignUp to the website");
        new SignUpPage().signUp(expectedFirstName, expectedLastName, expectedEmail, expectedPassword);

        logger.info("Connect to database");
        DBUtility.createConnection();

        logger.info("Send query to retrieve the information about the user");
        String query = "select * from loan.tbl_user where email='"+expectedEmail+"'";

        logger.info("Store the user information into the list of maps");
        List<Map<String, Object>> listOfMaps = DBUtility.getQueryResultListOfMaps(query);

        logger.info("Retrieve the user information needed");
        Map<String, Object> map = listOfMaps.get(0);
        System.out.println(map);

        logger.info("Compare the information that was entered through UI with the Database info");
        assertEquals(map.get("first_name"), expectedFirstName);
        assertEquals(map.get("last_name"), expectedLastName);
        assertEquals(map.get("email"), expectedEmail);
        assertEquals(map.get("password"), md5hash);
    }

    @Test (groups = {"sprint_3"})
    public void verifyUserSignUpFlowFromDatabaseToUI_Faker() throws SQLException {

        logger.info("Connect to database");
        DBUtility.createConnection();

        logger.info("Create a user with the details using Faker");
        Faker fake = new Faker();
        String expectedFirstName = fake.name().firstName();
        String expectedLastName = fake.name().lastName();
        String expectedEmail = fake.internet().emailAddress();
        String expectedPassword = fake.internet().password();
        logger.info("Get encrypted version on the Password");
        String md5hash = DigestUtils.md5Hex(expectedPassword);

        logger.info("Send query to retrieve the information about the user");
        String query = "insert into loan.tbl_user (email, password, first_name, last_name, phone, image, type, " +
                "created_at, modified_at, zone_id, church_id, country_id, active) values " +
                "('"+expectedEmail+"', '"+md5hash+"', '"+expectedFirstName+"', '"+expectedLastName+"', '2129000909', " +
                "'assets/images/profile-pics/head', '1', '1', '1', '1', '1', '1', '1')";

        DBUtility.updateQuery(query);

        logger.info("Verify the user creation on the UI by logging in with the fake credentials");

        logger.info("Login and verify the data is passed");
        LoginPage loginPage = new LoginPage();
        loginPage.login(expectedEmail, expectedPassword);
        assertEquals(dashboardUrl, driver.getCurrentUrl());

//        //Delete the row that was just created
//        String deleteQuery = "DELETE from loan.tbl_user where username = '"+expectedEmail+"'";
//        DBUtility.updateQuery(deleteQuery);


    }
    @Test (groups = {"sprint_3"})
    public void updateUserInfoInDbAndVerifyOnWebsite() throws SQLException {

        logger.info("Connect to database");
        DBUtility.createConnection();

        logger.info("Create a user with the details using Faker");
        Faker fake = new Faker();
        String expectedFirstName = fake.name().firstName();
        String expectedLastName = fake.name().lastName();
        String expectedEmail = fake.internet().emailAddress();
        String expectedPassword = fake.internet().password();
        logger.info("Get encrypted version on the Password");
        String md5hash = DigestUtils.md5Hex(expectedPassword);

        logger.info("Send query to update the user information");
        String query = "update loan.tbl_user set first_name = '"+expectedFirstName+"', last_name='"+expectedLastName+"', " +
                "email='"+expectedEmail+"', password='"+md5hash+"' where id='3333'";
        DBUtility.updateQuery(query);

        logger.info("Login and get the Username");
        new LoginPage().login(expectedEmail, expectedPassword);

        String actualUsername = new ApplicationPage().usernameButton.getText();
        String expectedUserName = expectedFirstName + " " + expectedLastName;

        logger.info("Compare the Username that was entered through Database with the Username on the website");
        assertEquals(actualUsername, expectedUserName);

    }

    @Test (groups = {"sprint_3"})
    public void verifyUserSignUpFlowFromUIToDatabase_EmptySpace() {

        logger.info("Create a user's First Name, Last Name, Email and Password using fake data");
        Faker fake = new Faker();
        String expectedFirstName = " ";
        String expectedLastName = fake.name().lastName();
        String expectedEmail = fake.internet().emailAddress();
        String expectedPassword = fake.internet().password();
        logger.info("Get encrypted version on the Password");
        String md5hash = DigestUtils.md5Hex(expectedPassword);

        logger.info("SignUp to the website");
        new SignUpPage().signUp(expectedFirstName, expectedLastName, expectedEmail, expectedPassword);

        logger.info("Connect to database");
        DBUtility.createConnection();

        logger.info("Send query to retrieve the information about the user");
        String query = "select * from loan.tbl_user where email='"+expectedEmail+"'";

        logger.info("Store the user information into the list of maps");
        List<Map<String, Object>> listOfMaps = DBUtility.getQueryResultListOfMaps(query);

        logger.info("Retrieve the user information needed");
        Map<String, Object> map = listOfMaps.get(0);
        System.out.println(map);

        logger.info("Compare the information that was entered through UI with the Database info");
        assertEquals(map.get("first_name"), expectedFirstName);
        assertEquals(map.get("last_name"), expectedLastName);
        assertEquals(map.get("email"), expectedEmail);
        assertEquals(map.get("password"), md5hash);
    }

}
