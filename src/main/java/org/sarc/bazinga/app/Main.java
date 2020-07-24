package org.sarc.bazinga.app;

import io.github.ossnass.fx.ControlMaster;
import io.github.ossnass.fx.SimpleController;
import javafx.application.Application;
import javafx.stage.Stage;
import org.sarc.asthma.pluginarch.PlugInLoader;


public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        ControlMaster.getControlMaster().initControlMaster("ar-sy.lang");
        PlugInLoader pil = PlugInLoader.getInstance();
        pil.loadPlugIns();
        SimpleController frmMain = ControlMaster.getControlMaster().getController("FrmMain");
        frmMain.setStage(stage);
        stage.show();
    }

    public static void main(String [] args){
        launch(args);
    }
}
