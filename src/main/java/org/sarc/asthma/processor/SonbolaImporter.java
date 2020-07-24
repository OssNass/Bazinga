package org.sarc.asthma.processor;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
    private final StringProperty fileName;
    private final ObservableList<String> sheets;
    private Workbook sonbola;
    private Sheet data;

    public SonbolaImporter() {
        super();
        fileName = new SimpleStringProperty();
        sheets = FXCollections.observableArrayList(new ArrayList<>());
    }

    public String getFileName() {
        return fileName.get();
    }

    public void setFileName(String fileName) throws IOException {
        this.fileName.set(fileName);
        if (sonbola != null) {
            try {
                sheets.clear();
                data = null;
                sonbola.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        sonbola = new XSSFWorkbook(fileName);
        for (int i = 0; i < sonbola.getNumberOfSheets(); i++) {
            sheets.add(sonbola.getSheetName(i));
        }
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
