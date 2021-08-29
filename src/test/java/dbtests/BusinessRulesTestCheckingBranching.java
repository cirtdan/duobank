package dbtests;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.LoginPage;

import uitests.TestBase;
import utilities.DBUtility;
import utilities.SeleniumUtils;

import java.sql.SQLException;
import java.util.*;

public class BusinessRulesTestCheckingBranching extends TestBase {


    @Test
    public void verifyAlbumsTableColumnNames(){

        // This is a comment by the owner of branch sprint 3

        // Thus is my code to create merge conflict

        List<String> expectedColumnNames = Arrays.asList("id", "title", "artist" , "genre", "artworkPath");

        List<String> actualColumnNames = DBUtility.getColumnNames("select * from albums limit 1");

        Assert.assertEquals(actualColumnNames, expectedColumnNames);

    }

    @Test
    public void verifyUSersTableColumnNames(){

        List<String> expectedColumnNames = Arrays.asList("id", "username", "firstName" , "lastName", "email", "password", "signUpDate", "profilePic");

        List<String> actualColumnNames = DBUtility.getColumnNames("select * from users limit 1");

        Assert.assertEquals(actualColumnNames, expectedColumnNames);

    }

    @Test
    public void verifyTheExpectedGenresList(){

        List<String> expectedGenres = Arrays.asList("rap",
                "pop",
                "techno" ,
                "rnb" ,
                "house",
                "classical" ,
                "jazz" ,
                "electronic" ,
                "dance" ,
                "reggae" ,
                "reggaeton");


        List<List<Object>> list = DBUtility.getQueryResultAsListOfLists("select name from genres");

        System.out.println(list);

        List<String> actualGenres =  new ArrayList<>();
        for (List<Object> row : list) {
           actualGenres.add((String) (row.get(0)));
        }

        Assert.assertEquals(actualGenres, expectedGenres);


    }


    @Test
    public void verifyPlaylistNameSupportsUnicodeCharsAndVerifyUpdateOnUI() throws SQLException {


        String expectedName = "あおい";
        String quesry = "UPDATE playlists SET name='"+expectedName+"' where id='3'";
        // Send update query to update the playlist name to non-english char
        DBUtility.updateQuery(quesry);

        // Send retrieve query to verify that the playlistName has been updated correctly

       Map<String, Object> map = DBUtility.getQueryResultListOfMaps("select * from playlists where id='3'").get(0);

        String actualNamefromDb = (String)(map.get("name"));

        Assert.assertEquals(actualNamefromDb, expectedName);

        // Verify the playlist creation on the UI


        new LoginPage().login("duotech", "duotech");
//        new BrowsePage().yourMusicLink.click();
//
//        List<String> allPlayLists = SeleniumUtils.getElementsText(new PlaylistsPage().allPlaylistsList);
//
//        Assert.assertTrue(allPlayLists.contains(expectedName));


    }

//    @Test (expectedExceptions = MysqlDataTruncation.class, expectedExceptionsMessageRegExp = "Data truncation: Out of range value for column 'plays' at row 1")
//    public void verifyPlaysFieldRange(){

    @Test ()
    public void verifyPlaysFieldRange(){

        String query = "update songs set plays='3000000000' where title='El Amante'";

        try{
            DBUtility.updateQuery(query);
            Assert.assertTrue(false);
        }catch(Exception e){
            Assert.assertTrue(true);
        }



    }


    @Test (expectedExceptions = MysqlDataTruncation.class, expectedExceptionsMessageRegExp = "Data truncation: Out of range value for column 'plays' at row 1")
    public void verifyPlaysFieldRangewithExpectedExceptions() throws SQLException {

        String query = "update songs set plays='-325642364632652346354325432' where title='El Amante'";
        DBUtility.updateQuery(query);

    }

    @Test
    public void verifyNoDuplicateUsernames(){


        List<List<Object>> lisOflists = DBUtility.getQueryResultAsListOfLists("select username from users");




        List<String> usernames = new ArrayList<>();


        for (List<Object> lisOflist : lisOflists) {
            usernames.add((String)(lisOflist.get(0)));
        }

        Collections.sort(usernames);

        boolean noDuplicate = true;
        for (int i = 0; i < usernames.size()-1; i++) {
            if(usernames.get(i).equals(usernames.get(i+1))){
                noDuplicate = false;
            }
        }

        Assert.assertTrue(noDuplicate);
    }

    //select email, count(*) from users group by email having count(*)>1

    @Test
    public void verifyNoDuplicateEmails(){

        List<List<Object>> lisOflists = DBUtility.getQueryResultAsListOfLists("select email, count(*) from users group by email having count(*)>1");

        // If the list is empty test passes


        Assert.assertTrue(lisOflists.isEmpty(), "The list is not empty, its size is "  + lisOflists.size());
    }




}
