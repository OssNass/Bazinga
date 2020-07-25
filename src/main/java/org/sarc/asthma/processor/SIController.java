package org.sarc.asthma.processor;

import io.github.ossnass.fx.ContollerType;
import io.github.ossnass.fx.ControllerInfo;
import io.github.ossnass.fx.QuickActions;
import io.github.ossnass.fx.settings.StringSetting;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import org.sarc.asthma.pluginarch.PlugInController;

import java.io.File;
import java.nio.file.Paths;

@ControllerInfo(Id = "SIController", FXMLFile = "fxmls/FrmIEP.fxml", Type = ContollerType.MULTIPLE_INSTANCE)
public class SIController extends PlugInController {
    private static final StringSetting lastLocation = new StringSetting("SI.LastLocation", System.getProperty("user.home"));
    private final String WRONG_SHEET = "SI.WRONG_SHEET";

    @FXML
    private TextField txtFileName;

    @FXML
    private Button btnBrowse;

    @FXML
    private ComboBox<String> cmbSheets;

    @FXML
    void btnBrowseClick(ActionEvent event) {
        FileChooser open = new FileChooser();
        open.setTitle(resources.getString("SI.ChooserTitle"));
        open.getExtensionFilters().add(new ExtensionFilter("Excel 2007 File", "*.xlsx"));
        File openFile = open.showOpenDialog(null);
        if (openFile != null) {
            txtFileName.setText(openFile.getAbsolutePath());
            ((SonbolaImporter) plugIn).setFileName(txtFileName.getText());
            cmbSheets.setItems(((SonbolaImporter) plugIn).getSheets());
            lastLocation.setData(Paths.get(openFile.toURI()).getParent().toString());
        }
    }

    @Override
    protected void userInit() {
        addTask.disableProperty().bind(txtFileName.textProperty().isEmpty().
                or(cmbSheets.getSelectionModel().selectedItemProperty().isNull()));
        cmbSheets.getSelectionModel().selectedItemProperty().addListener(this::sheetChanged);

    }

    private void sheetChanged(ObservableValue<? extends String> value, String oldVale, String newValue) {
        if (newValue != null && !newValue.isEmpty()) {
            int[] res = ((SonbolaImporter) plugIn).setSheet(newValue);
            if (res != null) {
                String message = String.format(resources.getString(WRONG_SHEET), newValue);
                String body = "";
                for (int i = 0; i < res.length; i += 3)
                    body += COLUMN_INDECIES.COLUMNS_HEADER[res[i]] +
                            ((i + 1 < res.length) ? " - " + COLUMN_INDECIES.COLUMNS_HEADER[res[i + 1]] : "") +
                            ((i + 2 < res.length) ? " - " + COLUMN_INDECIES.COLUMNS_HEADER[res[i + 2]] : "") +
                            "\n";
                QuickActions.showErrorMessage(null, message, body, this);
                cmbSheets.getSelectionModel().clearSelection();
            }

        }
    }

    public void onStageShowUser() {


    }

}
