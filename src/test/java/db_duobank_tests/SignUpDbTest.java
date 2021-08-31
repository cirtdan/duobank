package db_duobank_tests;

import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SignUpPage;
import tests.TestBase;
import utilities.DBUtility;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SignUpDbTest extends TestBase {


    @Test
    public void verifyClientSFirstAndLastName(){

        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastname = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String pass= faker.internet().password();

        SignUpPage signUp = new SignUpPage();

        signUp.signUp(firstName,lastname,email,pass);

        DBUtility.createConnection();

        String query = "select * from tbl_user Where first_name='"+firstName+"'and last_name='"+lastname+"'";

        logger.info("Saving new clients info into list of Map");

        List<Map<String, Object>> listOfMaps=DBUtility.getQueryResultListOfMaps(query);

        logger.info("getting the stored info from Database");
        Map<String,Object> map = listOfMaps.get(0);
        String actualFirstName= (String) map.get("first_name");
        String actualLastName= (String) map.get("last_name");
        logger.info("verify if the first name and last name matches");
        Assert.assertEquals(actualFirstName,firstName);
        Assert.assertEquals(actualLastName,lastname);



    }
    @Test
    public void verifyColumnNamesExist(){
        DBUtility.createConnection();
        logger.info("Adding expected column names");
        List<String> expectedcolumnNames = Arrays.asList("id", "email", "password","first_name","last_name","phone","image","type",
                "created_at", "modified_at","zone_id","church_id", "country_id","active");

        logger.info("return expected column names from SQL");
        List<String> actualColumnNames= DBUtility.getColumnNames("Select * From tbl_user");
        logger.info("Verify Expected with Actual column names");
        Assert.assertEquals(actualColumnNames, expectedcolumnNames);

        DBUtility.close();

    }
    @Test
    public void VerifyManualyAddedUser(){
       DBUtility.createConnection();

        String query =

    }




}
