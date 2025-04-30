import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

public class ToDoListView extends Pane {
    private SideMenu sideMenu;
    private ScrollPane taskScrollPane;
    private TaskPane taskPane;
    private AddTaskPane addTaskPane;
    private AddCategoryPane addCategoryPane;
    private SideMenuButton currentSelection;

    public ToDoListView(TaskCollection model) {
        // side menu
        sideMenu = new SideMenu(model);
        setPane(sideMenu, 0, 225);

        // task and task scroll panes
        taskPane = new TaskPane(model,this);
        taskScrollPane = new ScrollPane();
        taskScrollPane.setContent(taskPane);
        taskScrollPane.relocate(225, 0);
        taskScrollPane.setPrefSize(575,900);
        taskScrollPane.setFitToWidth(true);
        taskScrollPane.setStyle("-fx-background-color: rgb(220,220,220); -fx-background: rgb(220,220,220);");

        // add task pane
        addTaskPane = new AddTaskPane(model);
        setPane(addTaskPane, 225, 575);
        addTaskPane.setVisible(false);
        addTaskPane.reset();

        // add category pane
        addCategoryPane = new AddCategoryPane();
        setPane(addCategoryPane, 225, 575);
        addCategoryPane.setVisible(false);

        currentSelection = sideMenu.getTodayButton();

        update(sideMenu.getTodayButton());
        getChildren().addAll(sideMenu, taskScrollPane, addTaskPane, addCategoryPane);
    }

    public SideMenu getSideMenu() {
        return sideMenu;
    }
    public TaskPane getTaskPane() {
        return taskPane;
    }
    public AddTaskPane getAddTaskPane() {
        return addTaskPane;
    }
    public AddCategoryPane getAddCategoryPane() {
        return addCategoryPane;
    }
    public SideMenuButton getCurrentSelection() {
        return currentSelection;
    }

    public void update(SideMenuButton selectedButton) {
        updateSideMenu(selectedButton);
        updateTaskPane();
    }

    private void updateSideMenu(SideMenuButton selectedButton) {
        if (selectedButton != null) {
            currentSelection.deselect();
            selectedButton.select();
            currentSelection = selectedButton;
        }

        if (sideMenu.getTodayButton().isSelected()) {
            sideMenu.getSortTypeComboBox().setDisable(true);
        } else {
            sideMenu.getSortTypeComboBox().setDisable(false);
        }

        if (sideMenu.getSortTypeComboBox().getSelectionModel().getSelectedItem().equals("priority")) {
            sideMenu.disableFilters(false);
        } else {
            sideMenu.disableFilters(true);
        }

        if (sideMenu.getAddTaskButton().isSelected()) {
            addTaskPane.setVisible(true);
        } else {
            hidePane(addTaskPane);
        }

        if (sideMenu.getCategoryPane().getAddCategoryButton().isSelected()) {
            addCategoryPane.setVisible(true);
        } else {
            hidePane(addCategoryPane);
        }
    }

    private void hidePane(ResettablePane pane) {
        pane.reset();
        pane.setVisible(false);
    }

    private void updateTaskPane() {
        if (sideMenu.getTodayButton().isSelected()) {
            taskPane.displayTodayTasks();
        } else if (sideMenu.getAllTasksButton().isSelected()) {
            taskPane.displayAllTasks();
        } else if (sideMenu.getOverdueTasksButton().isSelected()) {
            taskPane.displayOverdueTasks();
        } else {
            for (SideMenuButton b: getSideMenu().getCategoryPane().getCategoryButtons()) {
                if (b.isSelected()) {
                    taskPane.displayCategoryTasks(b.getText());
                }
            }
        }
    }

    private void setPane(Pane pane, int x, int width) {
        pane.relocate(x,0);
        pane.setPrefSize(width,900);
    }
}