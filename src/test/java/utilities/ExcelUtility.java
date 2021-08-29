package utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtility {




    private XSSFWorkbook workBook;
    private XSSFSheet sheet;
    private String path;


    public ExcelUtility(String path, String sheetName) {
        this.path = path;
        try {
            FileInputStream excelFile = new FileInputStream(path);
            workBook = new XSSFWorkbook(excelFile);
            sheet = workBook.getSheet(sheetName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String getCellData(int rowNum, int colNum) {
        XSSFCell cell;
        try {
            cell = sheet.getRow(rowNum).getCell(colNum);
            return cell.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    public int rowCount() {
        return sheet.getPhysicalNumberOfRows();
    }
    public int columnCount() {
        return sheet.getRow(0).getLastCellNum();
    }
    public String[][] getDataAs2DArray() {
        String[][] table = new String[rowCount()][columnCount()];
        for (int i = 0; i <rowCount(); i++) {
            for (int j = 0; j < columnCount(); j++) {
                String value = getCellData(i, j);
                table[i][j] = value;
            }
        }
        return table;
    }
    public List<Map<String, String>> getDataAsMap() {
        List<String> columnNames = getColumnNames(); // gets the names of columns
        List<Map<String, String>> data = new ArrayList<>();
        for (int i = 1; i < rowCount(); i++) {
            XSSFRow row = sheet.getRow(i); //Gets each row
            Map<String, String> rowMap = new LinkedHashMap<String, String>();
            //We are mapping each column name with each cell in a row
            for (Cell cell : row) {
                int columnIndex = cell.getColumnIndex();
                rowMap.put(columnNames.get(columnIndex), cell.toString());
            }
            data.add(rowMap);
        }
        return data;
    }
    public List<String> getColumnNames() {
        List<String> columns = new ArrayList<>();
        for (Cell cell : sheet.getRow(0)) {
            columns.add(cell.toString());
        }
        return columns;
    }
    public void setCellData(String value, int rowNum, int colNum) {
        XSSFCell cell;
        XSSFRow row;
        try {
            row = sheet.getRow(rowNum);
            cell = row.getCell(colNum);
            if (cell == null) {
                cell = row.createCell(colNum);
                cell.setCellValue(value);
            } else {
                cell.setCellValue(value);
            }
            FileOutputStream outputFile = new FileOutputStream(path);
            workBook.write(outputFile);
            outputFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setCellData(String value, String columnName, int row) {
        int column = getColumnNames().indexOf(columnName);
        setCellData(value, row, column);
    }
}