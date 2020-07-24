package org.sarc.bazinga.gui;

import io.github.ossnass.fx.ContollerType;
import io.github.ossnass.fx.ControllerInfo;
import io.github.ossnass.fx.SimpleController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import org.controlsfx.control.TaskProgressView;
import org.sarc.asthma.pluginarch.PlugIn;
import org.sarc.asthma.pluginarch.PlugInLoader;
import org.sarc.asthma.pluginarch.TaskContainer;

import java.io.IOException;

@ControllerInfo(Type = ContollerType.SINGLE_INSTANCE_ON_STARTUP, Id = "FrmMain",
        Icon = "icons/stage/bazinga.png", FXMLFile = "fxmls/FrmMain.fxml")
public class FrmMain extends SimpleController {
    @FXML
    private ComboBox<String> cmbTasks;

    @FXML
    private Button btnNewTask;

    @FXML
    private TabPane tPTasks;

    @FXML
    private TaskProgressView tPView;

    @FXML
    void btnNewTaskClick(ActionEvent event) {
        PlugIn p = PlugInLoader.getInstance().getPlugIn(cmbTasks.getSelectionModel().getSelectedItem());
        Tab t = new Tab(p.getName());
        tPTasks.getTabs().add(t);
        t.setId(p.getControllerID());
        t.setClosable(true);
        try {
            t.setContent(p.getController().getRoot());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void userInit() {
        TaskContainer.getInstance().setTasks(tPView.getTasks());
        setBinds();
    }

    private void setBinds() {
        btnNewTask.disableProperty().bind(cmbTasks.getSelectionModel().selectedItemProperty().isNull());
    }

    public void onStageShowUser() {
        cmbTasks.getItems().addAll(PlugInLoader.getInstance().getNames());
    }
}
