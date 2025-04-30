import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

import java.time.LocalDate;

public class ToDoListApp extends Application {
    TaskCollection model;
    ToDoListView view;

    public void start(Stage primaryStage) {
        model = new TaskCollection();
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
            public void handle(ActionEvent actionEvent) {handleAddTaskButton();}
        });
        view.getSideMenu().getTodayButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {handleTodayTasksButton();}
        });
        view.getSideMenu().getAllTasksButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {handleAllTasksButton();}
        });
        view.getSideMenu().getOverdueTasksButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {handleOverdueTasksButton();}
        });
        view.getSideMenu().getSortTypeComboBox().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {handleFiltersAndSorting();}
        });
        view.getSideMenu().getCategoryPane().getAddCategoryButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {handleAddCategoryButton();}
        });
        view.getAddTaskPane().getCreateTaskButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {handleCreateTaskButton();}
        });
        view.getAddCategoryPane().getCreateCategoryButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {handleCreateCategoryButton();}
        });
    }

    public void linkFilterEventHandler() {
        for (CheckBox c: view.getSideMenu().getFilters()) {
            c.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent actionEvent) {handleFiltersAndSorting();}
            });
        }
    }
    public void linkCategoryButtonEventHandler() {
        for (SideMenuButton c: view.getSideMenu().getCategoryPane().getCategoryButtons()) {
            c.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent actionEvent) {handleCategoryButton(actionEvent);}
            });
        }
    }
    public void linkCheckBoxEventHandler() {
        for (CheckBox c: view.getTaskPane().getCheckBoxes()) {
            c.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent actionEvent) {handleCheckBox(actionEvent);}});
        }
    }

    public void handleAddTaskButton() {
        view.hidePane(view.getAddCategoryPane());
        view.update(view.getSideMenu().getAddTaskButton());
        view.getAddTaskPane().setVisible(true);
    }

    public void handleTodayTasksButton() {
        view.hidePane(view.getAddCategoryPane());
        view.hidePane(view.getAddTaskPane());
        view.update(view.getSideMenu().getTodayButton());
        linkCheckBoxEventHandler();
    }

    public void handleAllTasksButton() {
        view.hidePane(view.getAddCategoryPane());
        view.hidePane(view.getAddTaskPane());
        view.update(view.getSideMenu().getAllTasksButton());
        linkCheckBoxEventHandler();
    }

    public void handleOverdueTasksButton() {
        view.hidePane(view.getAddCategoryPane());
        view.hidePane(view.getAddTaskPane());
        view.update(view.getSideMenu().getOverdueTasksButton());
        linkCheckBoxEventHandler();
    }

    public void handleAddCategoryButton() {
        view.hidePane(view.getAddTaskPane());
        view.update(view.getSideMenu().getCategoryPane().getAddCategoryButton());
        view.getAddCategoryPane().setVisible(true);
    }

    public void handleCategoryButton(ActionEvent actionEvent) {
        view.hidePane(view.getAddTaskPane());
        view.hidePane(view.getAddCategoryPane());
        SideMenuButton b = (SideMenuButton) actionEvent.getSource();
        view.update(b);
        linkCheckBoxEventHandler();
    }

    public void handleFiltersAndSorting() {
        view.update(null);
        linkCheckBoxEventHandler();
    }

    public void handleCheckBox(ActionEvent actionEvent) {
        Task t = ((TaskCheckBox)actionEvent.getSource()).getReference();
        model.completeTask(t);
        view.update(null);
        linkCheckBoxEventHandler();
    }

    public void handleCreateTaskButton() {
        AddTaskPane pane = view.getAddTaskPane();
        String action = pane.getActionTextField().getText();
        int priority = pane.getPriorityComboBox().getSelectionModel().getSelectedIndex() + 1;
        LocalDate date = pane.getDatePicker().getValue();
        String category = (String)pane.getCategoryComboBox().getSelectionModel().getSelectedItem();
        if (!action.equals("") && model.addTask(new Task(action, priority, date, category))) {
            view.getAddTaskPane().reset();
            view.getAddTaskPane().displayOutcome(true);
        } else {
            view.getAddTaskPane().displayOutcome(false);
        }
    }

    public void handleCreateCategoryButton() {
        String category = view.getAddCategoryPane().getCategoryTextField().getText();
        if (!category.equals("") && !category.equals("+ Category") && model.addCategory(category)) {
            view.getAddCategoryPane().reset();
            view.getAddCategoryPane().displayOutcome(true);
            view.getAddTaskPane().updateCategoryOptions();
            view.getSideMenu().getCategoryPane().update();
            linkCategoryButtonEventHandler();
        } else {
            view.getAddCategoryPane().displayOutcome(false);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}