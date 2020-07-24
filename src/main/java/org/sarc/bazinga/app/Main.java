package org.sarc.bazinga.app;

import io.github.ossnass.fx.ControlMaster;
import io.github.ossnass.fx.ResourceManager;
import io.github.ossnass.fx.SimpleController;
import io.github.ossnass.fx.settings.SettingsManager;
import javafx.application.Application;
import javafx.stage.Stage;
import org.sarc.asthma.pluginarch.PlugInLoader;

import java.nio.file.Paths;


public class Main extends Application {
    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        SimpleController frmMain = ControlMaster.getControlMaster().getController("FrmMain");
        frmMain.setStage(stage);
        stage.show();
    }

    @Override
    public void init() throws Exception {
        notifyPreloader(new IntegerNotification(0));
        SettingsManager.initSettingsManager(Paths.get(System.getProperty("user.home"), ".bazinga", "settings.prop").toString());
        notifyPreloader(new IntegerNotification(33));
        ControlMaster.getControlMaster().initControlMaster("ar-sy.lang");
        ControlMaster.getControlMaster().getCSSes().add(ResourceManager.getURL("css/style.css").toExternalForm());
        notifyPreloader(new IntegerNotification(66));
        PlugInLoader pil = PlugInLoader.getInstance();
        pil.loadPlugIns();
        notifyPreloader(new IntegerNotification(100));
    }
}
