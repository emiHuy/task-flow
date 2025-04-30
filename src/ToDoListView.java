import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

public class ToDoListView extends Pane {
    private TaskCollection model;
    private ScrollPane taskScrollPane;
    private AddTaskPane addTaskPane;
    private TaskPane taskPane;
    private SideMenu sideMenu;
    private AddCategoryPane addCategoryPane;

    public ToDoListView(TaskCollection model) {
        this.model = model;
        addTaskPane = new AddTaskPane(model);
        addTaskPane.relocate(225,0);
        addTaskPane.setPrefSize(575, 900);
        addTaskPane.setDisable(true);
        addTaskPane.setVisible(false);
        addTaskPane.reset();

        addCategoryPane = new AddCategoryPane();
        addCategoryPane.relocate(225,0);
        addCategoryPane.setPrefSize(575, 900);
        addCategoryPane.setDisable(true);
        addCategoryPane.setVisible(false);

        sideMenu = new SideMenu(model);
        sideMenu.relocate(0,0);
        sideMenu.setPrefSize(225, 1080);
        updateButtons(null);

        taskPane = new TaskPane(model,this);
        taskPane.displayTodayTasks();

        taskScrollPane = new ScrollPane();
        taskScrollPane.setContent(taskPane);
        taskScrollPane.relocate(225, 0);
        taskScrollPane.setPrefSize(575,900);
        taskScrollPane.setFitToWidth(true);
        taskScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        taskScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        taskScrollPane.setStyle("-fx-background-color: rgb(220,220,220); -fx-background: rgb(220,220,220);");

        getChildren().addAll(sideMenu, taskScrollPane, addTaskPane, addCategoryPane);
    }

    public void addTask() {
        addTaskPane.setVisible(true);
        addTaskPane.setDisable(false);
        updateButtons(sideMenu.getAddTaskButton());
    }

    public void addCategory() {
        addCategoryPane.setVisible(true);
        addCategoryPane.setDisable(false);
        updateButtons(sideMenu.getCategoryPane().getAddCategoryButton());
    }

    public void hidePane(ResettablePane pane) {
        pane.reset();
        pane.setVisible(false);
    }

    public SideMenu getSideMenu() {
        return sideMenu;
    }
    public AddTaskPane getAddTaskPane() {
        return addTaskPane;
    }
    public TaskPane getTaskPane() {
        return taskPane;
    }

    public AddCategoryPane getAddCategoryPane() {
        return addCategoryPane;
    }

    private void updateButtons(Button selectedButton) {
        for (CustomButton b: sideMenu.getAllButtons()) {
            if (b.equals(selectedButton)) {
                b.select();
            } else {
                b.deselect();
            }
        }
    }

    public void updateSideMenu(Button selectedButton) {
        if (sideMenu.getTodayButton().equals(selectedButton)) {
            sideMenu.getSortTypeComboBox().setDisable(true);
        } else {
            sideMenu.getSortTypeComboBox().setDisable(false);
        }
        if (sideMenu.getSortTypeComboBox().getSelectionModel().getSelectedItem().equals("priority")) {
            sideMenu.disableFilters(false);
        } else {
            sideMenu.disableFilters(true);
        }
        if (selectedButton != null) {
            updateButtons(selectedButton);
        }
    }

    public void updateTaskView() {
        if (sideMenu.getTodayButton().isSelected()) {
            taskPane.displayTodayTasks();
        } else if (sideMenu.getTasksButton().isSelected()) {
            taskPane.displayTasks();
        } else if (sideMenu.getOverdueTasksButton().isSelected()) {
            taskPane.displayOverdueTasks();
        } else {
            for (CustomButton b: getSideMenu().getCategoryPane().getCategoryButtons()) {
                if (b.isSelected()) {
                    taskPane.displayCategoryTasks(b.getText());
                }
            }
        }
    }

    public void update(Button selectedButton) {
        updateSideMenu(selectedButton);
        updateTaskView();
    }
}
