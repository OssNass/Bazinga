package org.sarc.asthma.processor;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.sarc.asthma.pluginarch.PlugIn;
import org.sarc.asthma.pluginarch.PlugInInfo;

import java.io.IOException;
import java.util.ArrayList;

@PlugInInfo("SI")
public class SonbolaImporter extends PlugIn<Void> {
    private SimpleStringProperty fileName;
    private ObservableList<String> sheets;
    private Workbook sonbola;
    private Sheet data;

    public SonbolaImporter() {
        super();
        fileName = new SimpleStringProperty();
        fileName.addListener(this::workbookChanged);
        sheets = FXCollections.observableArrayList(new ArrayList<>());
    }

    private void workbookChanged(ObservableValue<? extends String> item, String oldValue, String newValue) {
        System.out.println(newValue);
        if (sonbola != null) {
            try {
                sheets.clear();
                data = null;
                sonbola.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            sonbola = new XSSFWorkbook(newValue);
            for (int i = 0; i < sonbola.getNumberOfSheets(); i++) {
                sheets.add(sonbola.getSheetName(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

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
