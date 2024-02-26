package excelDataDrive;

import java.io.IOException;
import java.util.ArrayList;

public class testSample {

    public static void main(String[] args) throws IOException {

        excelData ed = new excelData();
        System.out.println(ed.getData("Add Profile"));
        ArrayList al = ed.getData("Purchase");
        System.out.println(al.get(0));

    }
}
