package org.sarc.asthma.pluginarch;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;

public class TaskContainer {
    private static TaskContainer container;
    private ObservableList<Task> tasks;

    private TaskContainer() {

    }

    public static TaskContainer getInstance() {
        if (container == null)
            container = new TaskContainer();
        return container;
    }

    public ObservableList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ObservableList<Task> tasks) {
        this.tasks = tasks;
    }
}
