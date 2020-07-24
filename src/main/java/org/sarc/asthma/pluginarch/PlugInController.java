package org.sarc.asthma.pluginarch;

import io.github.ossnass.fx.SimpleController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public abstract class PlugInController extends SimpleController {
    protected PlugIn plugIn;

    @FXML
    private Button addTask;

    protected PlugIn getPlugIn() {
        return plugIn;
    }

    protected void setPlugIn(PlugIn plugIn) {
        this.plugIn = plugIn;
    }

    @FXML
    void btnAddTaskClick(ActionEvent event) {
        TaskContainer.getInstance().getTasks().add(plugIn.getTask());
    }

}
