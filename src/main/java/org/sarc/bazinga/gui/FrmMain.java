package org.sarc.bazinga.gui;

import io.github.ossnass.fx.ContollerType;
import io.github.ossnass.fx.ControllerInfo;
import io.github.ossnass.fx.SimpleController;

@ControllerInfo(Type = ContollerType.SINGLE_INSTANCE_ON_STARTUP, Id = "FrmMain",
        Icon = "icons/stage/bazinga.png", FXMLFile = "fxmls/FrmMain.fxml")
public class FrmMain extends SimpleController {
    @Override
    protected void userInit() {

    }
}
