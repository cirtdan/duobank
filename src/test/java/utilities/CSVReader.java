package utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    public static Object[][] readData(String path) throws IOException {
        FileReader fr = new FileReader(path);
        List<String[]> list = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(fr);
        String line;
        int columns = 0;
        while((line = bufferedReader.readLine()) != null ){
            String[] split = line.split(",");
            columns = split.length;
            list.add(split);
        }
        int rows = list.size();
        Object[][] data = new Object[rows][columns];
        for (int i = 0; i < data.length; i++) {
            data[i] = list.get(i);
        }
        bufferedReader.close();

        return data;
    }
}
