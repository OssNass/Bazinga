package org.sarc.bazinga.app;

import io.github.ossnass.fx.ControlMaster;
import io.github.ossnass.fx.LanguageKeys;
import javafx.application.Application;
import javafx.stage.Stage;



public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        ControlMaster.getControlMaster().initControlMaster("ar-sy.lang");
    }

    public static void main(String [] args){
        launch(args);
    }
}
