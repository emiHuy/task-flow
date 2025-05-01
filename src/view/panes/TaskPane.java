package view.panes;

import model.Task;
import model.TaskCollection;
import view.ToDoListView;
import view.components.TaskCheckBox;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskPane extends Pane {
    private TaskCollection model;
    private ToDoListView mainView;
    private Label header;
    private ArrayList<TaskCheckBox> checkBoxes;
    private Button deleteCategoryButton;

    public TaskPane(TaskCollection model, ToDoListView mainView) {
        this.model = model;
        this.mainView = mainView;

        // header
        header = new Label();
        header.relocate(20, 15);
        header.setStyle("-fx-font-size: 25px; -fx-font-family: helvetica;");

        checkBoxes = new ArrayList<TaskCheckBox>();

        deleteCategoryButton = new Button("Delete Category");
        deleteCategoryButton.relocate(405,15);
        deleteCategoryButton.setPrefSize(150,30);
        deleteCategoryButton.setStyle("-fx-font-size: 14px; -fx-font-family: helvetica;");
        deleteCategoryButton.setVisible(false);

        setStyle("-fx-background-color: rgb(220,220,220)");
        getChildren().addAll(header, deleteCategoryButton);
    }

    public ArrayList<TaskCheckBox> getCheckBoxes() {
        return checkBoxes;
    }
    public Button getDeleteCategoryButton() {
        return deleteCategoryButton;
    }

    public void displayOverdueTasks() {
        displayTasks("Overdue Tasks", model.getOverdueTasks(), "There are no overdue tasks.");
    }
    public void displayTodayTasks() {
        displayTasks("Today's Tasks", model.getTasksByDate(LocalDate.now()), "There are no tasks scheduled for today.");
    }
    public void displayCategoryTasks(String category) {
        displayTasks(category, model.getTasksByCategory(category), "There are no tasks in this category.");
        deleteCategoryButton.setVisible(true);
    }
    public void displayAllTasks() {
        displayTasks("All Tasks", model.getTasks(), "There are no tasks scheduled.");
    }

    public void displayTasks(String header, ArrayList<Task> taskList, String emptyMessage) {
        setUp(header);
        if (taskList.isEmpty()) {
            createLabel(emptyMessage, 17, 65, 0, false);
        }
        else if (!header.equals("Today's Tasks") && mainView.getSideMenu().getSortTypeComboBox().getSelectionModel().getSelectedItem().equals("date")) {
            displayTasksByDate(taskList);
        } else {
            displayTasksByPriority(taskList);
        }
    }

    private void displayTasksByDate(ArrayList<Task> taskList) {
        int y = 65;
        LocalDate previousDate = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        for (Task t: taskList) {
            if (!t.getDate().equals(previousDate)) {
                if (previousDate != null) {
                    y += 30;
                }
                y = createLabel(t.getDate().format(formatter), 20, y, 40, t.getDate().compareTo(LocalDate.now()) < 0);
            }
            y = createCheckBox(t, t.getDate().compareTo(LocalDate.now()) < 0, y);
            previousDate = t.getDate();
        }
    }

    private void displayTasksByPriority(ArrayList<Task> taskList) {
        int y = 65;
        if (mainView.getSideMenu().getHighCheckBox().isSelected()) {
            y = displayPriorityGroup("High",y,model.getTasksByPriority(taskList,1));
        }
        if (mainView.getSideMenu().getMediumCheckBox().isSelected()) {
            y = displayPriorityGroup("Medium",y,model.getTasksByPriority(taskList,2));
        }
        if (mainView.getSideMenu().getLowCheckBox().isSelected()) {
            displayPriorityGroup("Low",y,model.getTasksByPriority(taskList,3));
        }
    }

    private int displayPriorityGroup(String priority, int y, ArrayList<Task> tasksWithPriority) {
        y = createLabel(priority + " Priority", 20, y, 40, false);
        if (tasksWithPriority.isEmpty()) {
            y = createLabel("There are no tasks in this priority group.", 17, y, 30, false);
        }
        for (Task t: tasksWithPriority) {
            y = createCheckBox(t, t.getDate().compareTo(LocalDate.now()) < 0, y);
        }
        return y+30;
    }

    private void setUp(String headerText) {
        checkBoxes.clear();
        getChildren().removeIf(node -> node != header && !node.equals(deleteCategoryButton));
        header.setText(headerText);
        deleteCategoryButton.setVisible(false);
    }

    private int createLabel(String text, int fontSize, int y, int gap, boolean textFillRed) {
        Label l = new Label(text);
        l.relocate(20, y);
        if (textFillRed) {
            l.setStyle("-fx-font-size: " + fontSize +"px; -fx-font-family: helvetica; -fx-text-fill: rgb(240,0,0);");
        } else {
            l.setStyle("-fx-font-size: " + fontSize +"px; -fx-font-family: helvetica;");
        }
        getChildren().add(l);
        return y + gap;
    }

    private int createCheckBox(Task task, boolean isOverdue, int y) {
        TaskCheckBox checkBox = new TaskCheckBox(task.getAction(), task);
        if (isOverdue) {
            checkBox.setStyle("-fx-font-size: 17px; -fx-font-family: helvetica; -fx-color: rgb(250,250,255); -fx-text-fill: rgb(240,0,0);");
        } else {
            checkBox.setStyle("-fx-font-size: 17px; -fx-font-family: helvetica; -fx-color: rgb(250,250,255);");
        }
        checkBox.relocate(20, y);
        getChildren().add(checkBox);
        checkBoxes.add(checkBox);
        return y + 30;
    }
}