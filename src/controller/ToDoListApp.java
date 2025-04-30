package controller;

import model.Task;
import model.TaskCollection;
import view.ToDoListView;
import view.components.SideMenuButton;
import view.components.TaskCheckBox;
import view.panes.AddTaskPane;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.time.LocalDate;

public class ToDoListApp extends Application {
    private TaskCollection model;
    private ToDoListView view;

    public void start(Stage primaryStage) {
        model = TaskCollection.readData();
        view = new ToDoListView(model);

        view.update(view.getSideMenu().getTodayButton());

        primaryStage.setResizable(false);
        primaryStage.setTitle("TaskFlow");
        primaryStage.setScene(new Scene(view,800,900));
        primaryStage.show();

        // link event handlers to components
        linkCategoryButtonEventHandler();
        linkFilterEventHandler();
        linkCheckBoxEventHandler();

        view.getSideMenu().getAddTaskButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {handleSideMenuButton(actionEvent);}
        });
        view.getSideMenu().getTodayButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {handleSideMenuButton(actionEvent);}
        });
        view.getSideMenu().getAllTasksButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {handleSideMenuButton(actionEvent);}
        });
        view.getSideMenu().getOverdueTasksButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {handleSideMenuButton(actionEvent);}
        });
        view.getSideMenu().getSortTypeComboBox().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {handleFiltersAndSorting();}
        });
        view.getSideMenu().getCategoryPane().getAddCategoryButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {handleSideMenuButton(actionEvent);}
        });
        view.getAddTaskPane().getCreateTaskButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {handleCreateTaskButton();}
        });
        view.getAddCategoryPane().getCreateCategoryButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {handleCreateCategoryButton();}
        });
        view.getTaskPane().getDeleteCategoryButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {handleDeleteCategoryButton();}
        });
        primaryStage.setOnCloseRequest(event -> {model.writeData();});
    }

    private void linkFilterEventHandler() {
        for (CheckBox c: view.getSideMenu().getFilters()) {
            c.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent actionEvent) {handleFiltersAndSorting();}
            });
        }
    }
    private void linkCategoryButtonEventHandler() {
        for (SideMenuButton c: view.getSideMenu().getCategoryPane().getCategoryButtons()) {
            c.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent actionEvent) {handleSideMenuButton(actionEvent);}
            });
        }
    }
    private void linkCheckBoxEventHandler() {
        for (CheckBox c: view.getTaskPane().getCheckBoxes()) {
            c.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent actionEvent) {handleCheckBox(actionEvent);}});
        }
    }

    private void handleFiltersAndSorting() {
        view.update(null);
        linkCheckBoxEventHandler();
    }

    private void handleCheckBox(ActionEvent actionEvent) {
        Task t = ((TaskCheckBox)actionEvent.getSource()).getReference();
        model.completeTask(t);
        view.update(null);
        linkCheckBoxEventHandler();
    }

    private void handleSideMenuButton(ActionEvent actionEvent) {
        view.update((SideMenuButton)actionEvent.getSource());
        linkCheckBoxEventHandler();
    }

    private void handleCreateTaskButton() {
        AddTaskPane pane = view.getAddTaskPane();
        String action = pane.getActionTextField().getText();
        int priority = pane.getPriorityComboBox().getSelectionModel().getSelectedIndex() + 1;
        LocalDate date = pane.getDatePicker().getValue();
        String category = (String)pane.getCategoryComboBox().getSelectionModel().getSelectedItem();
        if (!action.equals("") && model.addTask(new Task(action,priority,date,category))) {
            view.getAddTaskPane().reset();
            view.getAddTaskPane().displayOutcome(true);
        } else {
            view.getAddTaskPane().displayOutcome(false);
        }
    }

    private void handleCreateCategoryButton() {
        String category = view.getAddCategoryPane().getCategoryTextField().getText();
        if (!category.equals("") && model.addCategory(category)) {
            view.getAddCategoryPane().reset();
            view.getAddCategoryPane().displayOutcome(true);
            view.getAddTaskPane().updateCategoryOptions();
            view.getSideMenu().getCategoryPane().update();
            linkCategoryButtonEventHandler();
        } else {
            view.getAddCategoryPane().displayOutcome(false);
        }
    }

    private void handleDeleteCategoryButton() {
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Helvetica", Font.PLAIN, 14)));
        int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this category? \n All tasks in this category will be deleted.","Category Deletion Confirmation",JOptionPane.YES_NO_OPTION);
        if (confirmation == 0) {
            model.deleteCategory(view.getCurrentSelection().getText());
            view.update(view.getSideMenu().getAllTasksButton());
            view.getAddTaskPane().updateCategoryOptions();
            view.getSideMenu().getCategoryPane().update();
            linkCheckBoxEventHandler();
            linkCategoryButtonEventHandler();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}