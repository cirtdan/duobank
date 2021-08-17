package utilities;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;

    public class TestApache {

        public static void main(String[] args) throws IOException {
            FileInputStream fis = new FileInputStream("MOCK_DATA2.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet("Sheet1");
            XSSFRow headerRow  =  sheet.getRow(0);
            XSSFCell cell = headerRow.getCell(0);
//        int noOfColumns = headerRow.getPhysicalNumberOfCells();
            int noOfColumns = headerRow.getLastCellNum();
            int noOfRows = sheet.getPhysicalNumberOfRows();
            System.out.println(noOfColumns);
            System.out.println(noOfRows);
            for (int i = 0; i < noOfRows; i++) {
                for (int j = 0; j < noOfColumns; j++) {
                    System.out.print(sheet.getRow(i).getCell(j) + " ");
                }
                System.out.println();
            }
        }
    }
