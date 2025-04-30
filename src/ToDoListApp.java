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
        view.updateSideMenu(view.getSideMenu().getTodayButton());
        view.updateTaskView();
        createCategoryButtonEventHandler();

        primaryStage.setResizable(false);
        primaryStage.setTitle("TaskFlow");
        primaryStage.setScene(new Scene(view,800,900));
        primaryStage.show();

        createFilterEventHandler();
        createCheckBoxEventHandler();

        view.getSideMenu().getAddTaskButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {handleAddTaskButton();}
        });
        view.getSideMenu().getTodayButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {handleTodayTasksButton();}
        });
        view.getSideMenu().getTasksButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {handleTasksButton();}
        });
        view.getSideMenu().getOverdueTasksButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {handleOverdueTasksButton();}
        });

        view.getAddTaskPane().getCreateTaskButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {handleCreateTaskButton();}
        });
        view.getSideMenu().getSortTypeComboBox().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {handleSortTypeComboBox();}
        });
        view.getSideMenu().getCategoryPane().getAddCategoryButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {handleAddCategoryButton();}
        });
        view.getAddCategoryPane().getCreateCategoryButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {handleCreateCategoryButton();}
        });
    }

    public void createFilterEventHandler() {
        for (CheckBox c: view.getSideMenu().getFilters()) {
            c.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent actionEvent) {handleFilter();}
            });
        }
    }

    public void handleFilter() {
        view.update(null);
        createCheckBoxEventHandler();
    }

    public void handleCreateCategoryButton() {
        String category = view.getAddCategoryPane().getCategoryTextField().getText();
        if (!category.equals("") && !category.equals("+ Category") && model.addCategory(category)) {
            view.getAddCategoryPane().reset();
            view.getAddCategoryPane().displayOutcome(true);
            view.getAddTaskPane().updateCategoryOptions();
            view.getSideMenu().getCategoryPane().update();
            createCategoryButtonEventHandler();
        } else {
            view.getAddCategoryPane().displayOutcome(false);
        }
    }

    public void handleSortTypeComboBox() {
        view.update(null);
        createCheckBoxEventHandler();
    }

    public void createCheckBoxEventHandler() {
        for (CheckBox c: view.getTaskPane().getCheckBoxes()) {
            c.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent actionEvent) {handleCheckBox(actionEvent);}});
        }
    }

    public void createCategoryButtonEventHandler() {
        for (CustomButton c: view.getSideMenu().getCategoryPane().getCategoryButtons()) {
            c.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent actionEvent) {handleCategoryButton(actionEvent);}
            });
        }
    }

    public void handleCategoryButton(ActionEvent actionEvent) {
        view.hidePane(view.getAddTaskPane());
        view.hidePane(view.getAddCategoryPane());
        CustomButton b = (CustomButton) actionEvent.getSource();
        view.update(b);
        createCheckBoxEventHandler();
    }

    public void handleCheckBox(ActionEvent actionEvent) {
        Task t = ((TaskCheckBox)actionEvent.getSource()).getReference();
        model.completeTask(t);
        view.update(null);
        createCheckBoxEventHandler();
    }

    public void handleAddTaskButton() {
        view.hidePane(view.getAddCategoryPane());
        view.updateSideMenu(view.getSideMenu().getAddTaskButton());
        view.addTask();
    }

    public void handleAddCategoryButton() {
        view.hidePane(view.getAddTaskPane());
        view.updateSideMenu(view.getSideMenu().getCategoryPane().getAddCategoryButton());
        view.addCategory();
    }

    public void handleTodayTasksButton() {
        view.hidePane(view.getAddCategoryPane());
        view.hidePane(view.getAddTaskPane());
        view.updateSideMenu(view.getSideMenu().getTodayButton());
        view.updateTaskView();
        createCheckBoxEventHandler();
    }

    public void handleTasksButton() {
        view.hidePane(view.getAddCategoryPane());
        view.hidePane(view.getAddTaskPane());
        view.updateSideMenu(view.getSideMenu().getTasksButton());
        view.updateTaskView();
        createCheckBoxEventHandler();
    }

    public void handleOverdueTasksButton() {
        view.hidePane(view.getAddCategoryPane());
        view.hidePane(view.getAddTaskPane());
        view.updateSideMenu(view.getSideMenu().getOverdueTasksButton());
        view.updateTaskView();
        createCheckBoxEventHandler();
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

    public static void main(String[] args) {
        launch();
    }
}
