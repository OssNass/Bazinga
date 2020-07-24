package org.sarc.asthma.pluginarch;

import io.github.ossnass.fx.ControlMaster;
import javafx.concurrent.Task;

import java.io.IOException;

public abstract class PlugIn<T> {
    protected String id;

    public PlugIn() {

    }

    void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return ControlMaster.getControlMaster().getLanguage().getString(id + ".name");
    }

    public String getDescription() {
        return ControlMaster.getControlMaster().getLanguage().getString(id + ".description");
    }

    public abstract String getControllerID();

    public abstract Task<T> getTask();

    public PlugInController getController() throws IOException {
        PlugInController res = (PlugInController) ControlMaster.getControlMaster().getController(getControllerID());
        res.setPlugIn(this);
        return res;
    }

    @Override
    public String toString() {
        return getName();
    }
}
