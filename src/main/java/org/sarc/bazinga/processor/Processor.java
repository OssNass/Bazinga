package org.sarc.bazinga.processor;

import io.github.ossnass.fx.SimpleController;
import javafx.concurrent.Task;

public abstract class Processor<V> extends Task<V> {
    protected SimpleController configurator;

    public SimpleController getConfigurator() {
        return configurator;
    }

}
