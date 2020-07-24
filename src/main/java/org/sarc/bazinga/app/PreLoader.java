package org.sarc.bazinga.app;

import io.github.ossnass.fx.ResourceManager;
import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.MalformedURLException;

public class PreLoader extends Preloader {
    private Scene scene;
    private ProgressBar pbWork;
    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.sizeToScene();
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setAlwaysOnTop(true);
        this.stage = stage;
        stage.show();
    }

    public void init() {
        ImageView splash = null;
        try {
            splash = new ImageView(ResourceManager.getURL("images/splash.png").toExternalForm());
            Pane root = new VBox();
            pbWork = new ProgressBar();
            root.getChildren().addAll(splash, pbWork);
            pbWork.setMaxWidth(Double.MAX_VALUE);
            scene = new Scene(root);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void handleApplicationNotification(Preloader.PreloaderNotification info) {
        int index = ((IntegerNotification) info).getInteger();
        pbWork.setProgress(index / 100.0);
    }


    public void handleStateChangeNotification(Preloader.StateChangeNotification info) {
        if (info.getType() == Preloader.StateChangeNotification.Type.BEFORE_START)
            this.stage.close();

    }
}
