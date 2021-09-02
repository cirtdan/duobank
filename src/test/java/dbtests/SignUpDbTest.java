package dbtests;

import com.github.javafaker.Faker;
import org.apache.commons.codec.digest.DigestUtils;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SignUpPage;
import uitests.TestBase;
import uitests.TestBase;
import utilities.DBUtility;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SignUpDbTest extends TestBase {


    @Test(groups = {"Sprint_3","smoke_sp3"})
    public void verifyClientSFirstAndLastName() {

        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastname = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String pass = faker.internet().password();

        SignUpPage signUp = new SignUpPage();

        signUp.signUp(firstName, lastname, email, pass);

        DBUtility.createConnection();

        String query = "select * from tbl_user Where first_name='" + firstName + "'and last_name='" + lastname + "'";

        logger.info("Saving new clients info into list of Map");

        List<Map<String, Object>> listOfMaps = DBUtility.getQueryResultListOfMaps(query);

        logger.info("getting the stored info from Database");
        Map<String, Object> map = listOfMaps.get(0);
        String actualFirstName = (String) map.get("first_name");
        String actualLastName = (String) map.get("last_name");
        logger.info("verify if the first name and last name matches");
        assertEquals(actualFirstName, firstName);
        assertEquals(actualLastName, lastname);


    }

    @Test(groups = {"Sprint_3","smoke_sp3"})
    public void verifyColumnNamesExist() {
        DBUtility.createConnection();
        logger.info("Adding expected column names");
        List<String> expectedcolumnNames = Arrays.asList("id", "email", "password", "first_name", "last_name", "phone", "image", "type",
                "created_at", "modified_at", "zone_id", "church_id", "country_id", "active");

        logger.info("return expected column names from SQL");
        List<String> actualColumnNames = DBUtility.getColumnNames("Select * From tbl_user");
        logger.info("Verify Expected with Actual column names");
        assertEquals(actualColumnNames, expectedcolumnNames);

        DBUtility.close();

    }

    @Test(groups = {"smoke_sp3","smoke_sp3"})
    public void VerifyManualyAddedUser() throws SQLException {
        DBUtility.createConnection();
        logger.info("Added hard coded variable into database");
        String firstName_Exp = "Madina";
        String lastName_Exp = "Sirojeva";
        String email_Exp = "Madina.sirojeva@gmail.com";
        String password_Exp = "Madina2023";
        logger.info("Using Disgest utils from java to decrypt the password_Exp to Hash code");
        String decrypted_md5Hash_pass = DigestUtils.md5Hex(password_Exp);

        String query = "insert into loan.tbl_user (email, password, first_name, last_name, phone, image, type, " +
                "created_at, modified_at, zone_id, church_id, country_id, active) values " +
                "('" + email_Exp + "', '" + decrypted_md5Hash_pass + "', '" + firstName_Exp + "', '" + lastName_Exp + "', '7033407777', " +
                "'assets/images/profile-pics/head', '1', '1', '1', '1', '1', '1', '1')";

        DBUtility.updateQuery(query);
        logger.info("Verifying the manually added user will log into UI side using their info");

        LoginPage logOn = new LoginPage();
        logOn.login(email_Exp, password_Exp);
        assertEquals(driver.getCurrentUrl(), dashboardUrl);

    }

    @Test(groups = {"smoke_sp3","negative_sprint3"})
    public void VerifyManualyAddedUserWithNoLastName() throws SQLException {
        DBUtility.createConnection();
        logger.info("Added hard coded variable into database");
        String firstName_Exp = "Madina";
        String lastName_Exp = " ";
        String email_Exp = "Madina.sirojeva@gmail.com";
        String password_Exp = "Madina2023";
        logger.info("Using Disgest utils from java to decrypt the password_Exp to Hash code");
        String decrypted_md5Hash_pass = DigestUtils.md5Hex(password_Exp);

        String query = "insert into loan.tbl_user (email, password, first_name, last_name, phone, image, type, " +
                "created_at, modified_at, zone_id, church_id, country_id, active) values " +
                "('" + email_Exp + "', '" + decrypted_md5Hash_pass + "', '" + firstName_Exp + "', '" + lastName_Exp + "', '7033407777', " +
                "'assets/images/profile-pics/head', '1', '1', '1', '1', '1', '1', '1')";

        DBUtility.updateQuery(query);
        logger.info("Verifying the whther it is going to accept info without lastname");

        LoginPage logOn = new LoginPage();
        logOn.login(email_Exp, password_Exp);
        logger.info("confirm whether we are on dashboard url after login");
        assertEquals(driver.getCurrentUrl(), dashboardUrl);

    }

    @Test(groups = {"smoke_sp3","negative_sprint3"})
    public void VerifyManualyAddedUserWithoutemail() throws SQLException {
        DBUtility.createConnection();
        logger.info("Added hard coded variable into database");
        String firstName_Exp = "Madina";
        String lastName_Exp = "Sirojeva";
        String email_Exp = " ";
        String password_Exp = "Madina2023";
        logger.info("Using Disgest utils from java to decrypt the password_Exp to Hash code");
        String decrypted_md5Hash_pass = DigestUtils.md5Hex(password_Exp);

        String query = "insert into loan.tbl_user (email, password, first_name, last_name, phone, image, type, " +
                "created_at, modified_at, zone_id, church_id, country_id, active) values " +
                "('" + email_Exp + "', '" + decrypted_md5Hash_pass + "', '" + firstName_Exp + "', '" + lastName_Exp + "', '7033407777', " +
                "'assets/images/profile-pics/head', '1', '1', '1', '1', '1', '1', '1')";

        DBUtility.updateQuery(query);
        logger.info("Verifying the verifying if the it is going to log in with empty email");

        LoginPage logOn = new LoginPage();
        logOn.login(email_Exp, password_Exp);
        assertFalse(driver.getCurrentUrl().equals(dashboardUrl));

    }

}