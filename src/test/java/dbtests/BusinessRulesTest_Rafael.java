package dbtests;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import uitests.TestBase;
import utilities.DBUtility;
import java.sql.SQLException;
import java.util.*;

public class BusinessRulesTest_Rafael extends TestBase {


    @Test (groups = {"sprint_3"})
    public void verifyMortgageTableColumnNames(){

        logger.info("Connect to database");
        DBUtility.createConnection();

        logger.info("Create a list of expected Mortgage Column Names");
        List<String> expectedColumnNames = Arrays.asList("id", "realtor_status", "realtor_info", "loan_officer_status",
                "purpose_loan", "est_purchase_price", "down_payment", "down_payment_percent", "total_loan_amount",
                "src_down_payment", "add_fund_available", "apply_co_borrower", "b_firstName", "b_middleName",
                "b_lastName", "b_suffix", "b_email", "b_dob", "b_ssn", "b_marital", "b_cell", "b_home", "c_firstName",
                "c_middleName", "c_lastName", "c_suffix", "c_email", "c_dob", "c_ssn", "c_marital", "c_cell", "c_home",
                "rent_own_status", "monthly_rental_payment", "first_mortagage_total_payment", "employer_name",
                "position", "city", "state", "start_date", "end_date", "current_job", "co_employer_name",
                "co_position", "co_city", "co_state", "co_start_date", "co_end_date", "co_current_job",
                "gross_monthly_income", "monthly_over_time", "monthly_bonuses", "monthly_commision",
                "monthly_dividents", "c_gross_monthly_income", "c_monthly_over_time", "c_monthly_bonuses",
                "c_monthly_commision", "c_monthly_dividents", "add_belong", "income_source", "amount",
                "eConsent_declarer", "eConsent_declarer_FirstName", "eConsent_declarer_LastName",
                "eConsent_declarer_Email", "created_on", "modified_on", "loan_status", "user_id", "active");

        logger.info("Send query to retrieve the actual Mortgage column names");
        String query = "select * from loan.tbl_mortagage limit 1";
        List<String> actualColumnNames = DBUtility.getColumnNames(query);

        logger.info("Compare actual Mortgage column names with expected column names");
        assertEquals(actualColumnNames, expectedColumnNames);

    }

    @Test (groups = {"sprint_3"})
    public void verifyUsersTableColumnNames(){

        logger.info("Connect to database");
        DBUtility.createConnection();

        logger.info("Create a list of expected Users Column Names");
        List<String> expectedColumnNames = Arrays.asList("id", "email", "password", "first_name", "last_name", "phone",
                "image", "type", "created_at", "modified_at", "zone_id", "church_id", "country_id", "active");

        logger.info("Send query to retrieve the actual Users column names");
        String query = "select * from loan.tbl_user";
        List<String> actualColumnNames = DBUtility.getColumnNames(query);

        logger.info("Compare actual Users column names with expected column names");
        assertEquals(actualColumnNames, expectedColumnNames);

    }

    @Test (groups = {"sprint_3"})
    public void verifyUserNameSupportsUnicodeChars() throws SQLException {

        logger.info("Connect to database");
        DBUtility.createConnection();

        logger.info("Create First Name and Last Name with non-english characters");
        String expectedFirstName = "Эрнесто";
        String expectedLastName = "Че Гевара";
        logger.info("Send update query to update the user's First Name and Last Name to non-english characters");
        String query = "update loan.tbl_user set first_name='"+expectedFirstName+"', last_name='"
                +expectedLastName+"' where id='3434'";
        DBUtility.updateQuery(query);

        logger.info("Retrieve user's First Name and Last Name from the database");
        List<Map<String, Object>> listOfMaps = DBUtility.getQueryResultListOfMaps("select * from loan.tbl_user where id='3434'");
        Map<String, Object> map = listOfMaps.get(0);

        String actualFirstNameFromDb = (String)(map.get("first_name"));
        String actualLastNameFromDb = (String)(map.get("last_name"));

        logger.info("Verify that the user's First Name and Last Name with non-english characters was added to the database");
        assertEquals(actualFirstNameFromDb, expectedFirstName);
        assertEquals(actualLastNameFromDb, expectedLastName);

    }

    @Test (groups = {"sprint_3"})
    public void verifyLoanOfficerStatusRange(){

        logger.info("Connect to database");
        DBUtility.createConnection();

        logger.info("Adding the number that is better than int max value and check if the database accepts it");
        String query = "update loan.tbl_mortagage set loan_officer_status='5000000000' where id='520'";

        try{
            DBUtility.updateQuery(query);
            fail();
        }catch(Exception e){
            assertTrue(true);
        }
        logger.info("The database does not accept it");

    }


    @Test (expectedExceptions = MysqlDataTruncation.class, expectedExceptionsMessageRegExp =
            "Data truncation: Out of range value for column 'realtor_status' at row 1", groups = {"sprint_3"})
    public void verifyLoanOfficerStatusRangeWithVerifyingExceptionMessage() throws SQLException {

        logger.info("Connect to database");
        DBUtility.createConnection();
        logger.info("Send update query adding out of range value and verify the exception message");
        String query = "update loan.tbl_mortagage set realtor_status='-9476455352728929390466' where id='520'";
        DBUtility.updateQuery(query);

    }

    @Test (groups = {"sprint_3"}) // BUG
    public void verifyNoDuplicateEmails(){

        logger.info("Connect to database");
        DBUtility.createConnection();

        logger.info("Send query to retrieve the duplicate emails");
        String query = "select email, count(*) from loan.tbl_user group by email having count(*)>1";
        List<List<Object>> lisOfLists = DBUtility.getQueryResultAsListOfLists(query);

        logger.info("Verify if the list is empty, then test passes");
        assertTrue(lisOfLists.isEmpty(), "The list is not empty, its size is "  + lisOfLists.size());
    }
}
