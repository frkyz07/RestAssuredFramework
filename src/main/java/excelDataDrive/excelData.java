package excelDataDrive;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class excelData {

    public ArrayList<String> getData(String testCaseName) throws IOException {
        ArrayList<String> arrayList = new ArrayList<String>();
            FileInputStream fis = new FileInputStream("/Users/farukayaz/Downloads/data.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            int sheets = workbook.getNumberOfSheets();


            for (int i = 0; i < sheets; i++) {
                if (workbook.getSheetName(i).equalsIgnoreCase("testcase")) {
                    System.out.println(workbook.getSheetName(i));
                    XSSFSheet sheet = workbook.getSheetAt(i);
                    /* identify test cases' column by scanning the entire row */
                    Iterator<Row> rows = sheet.iterator();
                    Row firstRow = rows.next();
                    Iterator<Cell> firstCell = firstRow.cellIterator();
                    int k = 0;
                    int column = 0;

                    while (firstCell.hasNext()) {

                        Cell cellValue = firstCell.next();
                        if (cellValue.getStringCellValue().equalsIgnoreCase("TestCases")) {
                            //desired column
                            column = k;

                        }
                        k++;

                    }
                    System.out.println(column);

                    while (rows.hasNext()) {

                        Row r = rows.next();
                         //System.out.println(r.getCell(column).getStringCellValue());
                        if (r.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseName)) {

                            Iterator<Cell> cv = r.cellIterator();

                            while (cv.hasNext()) {

                                Cell c = cv.next();
                                if (c.getCellType()== CellType.STRING){
                                    arrayList.add(c.getStringCellValue());
                                }
                                else
                                {
                                    arrayList.add(NumberToTextConverter.toText(c.getNumericCellValue()));
                                }
                            }
                        }

                    }
                }


            }
        return arrayList;
    }
    public static void main(String[] args) throws IOException {

    }

}
