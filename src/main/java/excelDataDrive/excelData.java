package excelDataDrive;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class excelData {

    public static void main(String[] args) throws IOException {

        FileInputStream fis = new FileInputStream("/Users/farukayaz/Downloads/data.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        int sheets = workbook.getNumberOfSheets();
        System.out.println(sheets);

        for (int i=0; i<sheets;i++)
        {
            if (workbook.getSheetName(i).equalsIgnoreCase("Sayfa1"))
            {
                System.out.println(workbook.getSheetName(i));
                XSSFSheet sheet = workbook.getSheetAt(i);
                /* identify test cases' column by scanning the entire row */
                Iterator<Row> rows = sheet.iterator();
                Row firstRow = rows.next();
                Iterator<Cell> firstCell = firstRow.cellIterator();
                int k = 0;
                int column=0;

                while (firstCell.hasNext()){

                    Cell cellValue = firstCell.next();
                    if(cellValue.getStringCellValue().equalsIgnoreCase("Testcases")){
                        //desired column
                        column=k;

                    }
                    k++;

                }
                System.out.println(column);
            }


        }



    }
}
