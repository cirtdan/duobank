package utilities;

import java.util.List;
import java.util.Map;

public class TestDbUtility {


    public static void main(String[] args) {


        DBUtility.createConnection();


        System.out.println("Connection successful");

//        List<List<Object>> queryResultAsListOfLists = DBUtility.getQueryResultAsListOfLists("select * from users limit 10");

//
//        for (List<Object> eachrow : queryResultAsListOfLists) {
//            System.out.println(eachrow);
//        }
//
//
//        System.out.println(queryResultAsListOfLists.get(1).get(4));


        List<Map<String, Object>> queryResultListOfMaps = DBUtility.getQueryResultListOfMaps("select firstName from users where firstName='Shelby'");

        for (Map<String, Object> eachRow : queryResultListOfMaps) {
            System.out.println(eachRow);
        }


//        System.out.println(queryResultListOfMaps.get(1).get("email"));


//        System.out.println(DBUtility.getColumnNames("select * from users limit 10"));

        DBUtility.close();


    }
}
