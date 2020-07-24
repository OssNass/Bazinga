package org.sarc.bazinga.app;

import javafx.application.Preloader;

public class IntegerNotification implements Preloader.PreloaderNotification {
    private int integer;

    public IntegerNotification(int integer) {
        this.integer = integer;
    }

    public int getInteger() {
        return this.integer;
    }

    public void setInteger(int integer) {
        this.integer = integer;
    }
}
