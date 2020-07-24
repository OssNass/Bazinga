package org.sarc.asthma.processor;

import io.github.ossnass.fx.ContollerType;
import io.github.ossnass.fx.ControllerInfo;
import io.github.ossnass.fx.settings.StringSetting;
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

    }

    public void onStageShowUser() {


    }

}
