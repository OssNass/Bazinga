package org.sarc.asthma.processor;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.sarc.asthma.pluginarch.PlugIn;
import org.sarc.asthma.pluginarch.PlugInInfo;
import org.sarc.bazinga.app.LogManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;

@PlugInInfo("SI")
public class SonbolaImporter extends PlugIn<Void> {
    private final SimpleStringProperty fileName;
    private final ObservableList<String> sheets;
    private Workbook sonbola;
    private Sheet data;
    private final int[] indecies = new int[COLUMN_INDECIES.INDEX_ALL.length];

    public SonbolaImporter() {
        super();
        fileName = new SimpleStringProperty();
        fileName.addListener(this::workbookChanged);
        sheets = FXCollections.observableArrayList(new ArrayList<>());
    }

    private void workbookChanged(ObservableValue<? extends String> item, String oldValue, String newValue) {
        if (sonbola != null) {
            try {
                sheets.clear();
                data = null;
                sonbola.close();
            } catch (IOException e) {
                LogManager.addLog(Level.WARNING, e, null);
            }
        }
        try {
            sonbola = new XSSFWorkbook(newValue);
            for (int i = 0; i < sonbola.getNumberOfSheets(); i++) {
                sheets.add(sonbola.getSheetName(i));
            }
        } catch (IOException e) {
            LogManager.addLog(Level.WARNING, e, null);
        }

    }

    public int[] setSheet(String sheetName) {
        this.data = null;
        if (sheetName == null)
            return COLUMN_INDECIES.INDEX_ALL;
        Sheet sheet = sonbola.getSheet(sheetName);
        ArrayList<Integer> resultList = new ArrayList<>();
        Row row = sheet.getRow(0);
        if (row == null)
            return COLUMN_INDECIES.INDEX_ALL;
        int numOfColumns = row.getLastCellNum() + 1;
        for (int i = 0; i < COLUMN_INDECIES.INDEX_ALL.length; i++) {
            boolean missing = true;
            for (int colIndex = 0; colIndex < numOfColumns; colIndex++) {
                Cell cell = row.getCell(colIndex);
                if (cell != null && cell.getStringCellValue().equals(COLUMN_INDECIES.COLUMNS_HEADER[i])) {
                    indecies[i] = colIndex;
                    missing = false;
                }
            }
            if (missing) {
                resultList.add(i);
            }
        }
        if (resultList.size() == 0) {
            this.data = sheet;
            return null;
        }
        return resultList.stream().mapToInt(i -> i).toArray();
    }

    public String getFileName() {
        return fileName.get();
    }

    public void setFileName(String fileName) {
        this.fileName.set(fileName);
    }

    public ObservableList<String> getSheets() {
        return sheets;
    }

    public StringProperty fileNameProperty() {
        return fileName;
    }

    @Override
    public String getControllerID() {
        return "SIController";
    }

    @Override
    public Task<Void> getTask() {
        return null;
    }
}
