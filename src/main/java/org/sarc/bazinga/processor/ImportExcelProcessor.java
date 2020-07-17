package org.sarc.bazinga.processor;

import javafx.beans.property.SimpleIntegerProperty;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.sarc.bazinga.database.Family;

import java.io.IOException;

public class ImportExcelProcessor extends Processor<Void> {
    private final SimpleIntegerProperty rows;
    private XSSFWorkbook excel;
    private XSSFSheet sheet;

    public ImportExcelProcessor() {
        rows = new SimpleIntegerProperty(0);
    }

    public String[] setExcel(String filename) throws IOException {
        excel = new XSSFWorkbook(filename);
        String[] res = new String[excel.getNumberOfSheets()];
        for (int i = 0; i < res.length; i++) {
            res[i] = excel.getSheetName(i);
        }
        return res;
    }

    public void setSheet(String sheetName) {
        sheet = excel.getSheet(sheetName);
        rows.set(sheet.getLastRowNum() - 1);
    }

    private void processRow(Row row) {
        Family family = new Family();

    }

    @Override
    protected Void call() throws Exception {

        return null;
    }


}

