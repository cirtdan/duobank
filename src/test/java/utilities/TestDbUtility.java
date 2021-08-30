package utilities;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Map;

public class TestDbUtility {

    public static void main(String[] args) {

DBUtility.createConnection();
        System.out.println("Connection successful");


        List<List<Object>> queryResultAsListOfLists = DBUtility.getQueryResultAsListOfLists("select *from tbl_user limit 10");


        for (List<Object> eachrow : queryResultAsListOfLists) {
            System.out.println(eachrow);
        }

       //taking result by index. it starts from 0 as its a list
        System.out.println(queryResultAsListOfLists.get(0).get(3));


        //stroka 27/32 ispolzuetsya vmeste i v " " mojno vpisat luboy request, kotoriy mojno poluchit iz map-a
        List<Map<String, Object>> queryResultListOfMaps = DBUtility.getQueryResultListOfMaps("select *from tbl_user limit 10");

        //row 29 is an example that you can take one info only
        // List<Map<String, Object>> queryResultListOfMaps = DBUtility.getQueryResultListOfMaps("select first_name from tbl_user where first_name='Gulara'");
        //String represents column, Object - value(t.e.nazvanie stolbika budet priplusovana k stringe kajdoy info stroki
        for (Map<String, Object> eachrow : queryResultListOfMaps) {

            System.out.println(eachrow);

        }


        //nije zapis, kot budet rabotat s uje sozdannoy kartoy

        System.out.println(queryResultListOfMaps.get(0).get("email"));


        //verify column name
        System.out.println(DBUtility.getColumnNames("select *from tbl_user limit 10"));



        DBUtility.close();



        }

    }

