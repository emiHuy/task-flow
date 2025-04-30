import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

public class SideMenu extends Pane {
    public static final double WIDTH = 225;
    private TaskCollection model;
    private Label dateLabel;
    private CustomButton todayButton;
    private CustomButton tasksButton;
    private CustomButton addTaskButton;
    private CustomButton overdueTasksButton;
    private CategoryPane categoryPane;
    private LocalDate currentDate;
    private ComboBox sortTypeComboBox;
    private CheckBox highCheckBox;
    private CheckBox mediumCheckBox;
    private CheckBox lowCheckBox;

    public SideMenu(TaskCollection model) {
        this.model = model;
        currentDate = LocalDate.now();

        setStyle("-fx-background-color: rgb(30,30,30);");

        Label title = new Label("TaskFlow");
        title.relocate(10, 10);
        title.setTextFill(Color.rgb(90,90,90));
        title.setStyle("-fx-font-size: 14px;" +
                "-fx-font-family: helvetica;");

        dateLabel = new Label("" + LocalDate.now());
        dateLabel.relocate(145,10);
        dateLabel.setTextFill(Color.rgb(90,90,90));
        dateLabel.setStyle("-fx-font-size: 14px;" +
                "-fx-font-family: helvetica;");

        addTaskButton = new CustomButton("+ Task", 16);
        addTaskButton.setPrefSize(200, 40);
        addTaskButton.relocate(12, 60);
        addTaskButton.setTextFill(Color.rgb(190,190,190));

        todayButton = new CustomButton("Today's Tasks", 16);
        todayButton.setPrefSize(200, 40);
        todayButton.relocate(12, 100);
        todayButton.setTextFill(Color.rgb(190,190,190));

        tasksButton = new CustomButton("All tasks", 16);
        tasksButton.setPrefSize(200, 40);
        tasksButton.relocate(12, 140);
        tasksButton.setTextFill(Color.rgb(190,190,190));

        overdueTasksButton = new CustomButton("Overdue Tasks", 16);
        overdueTasksButton.setPrefSize(200, 40);
        overdueTasksButton.relocate(12, 180);
        overdueTasksButton.setTextFill(Color.rgb(190,190,190));

        String[] sortTypes = {"priority", "date"};
        Label sortTypeLabel = new Label("Sort by: ");
        sortTypeLabel.relocate(20,270);
        sortTypeLabel.setTextFill(Color.rgb(190,190,190));
        sortTypeLabel.setStyle("-fx-font-size: 15px; -fx-font-family: helvetica; -fx-background-color: transparent;");

        sortTypeComboBox = new ComboBox(FXCollections.observableArrayList(sortTypes));
        sortTypeComboBox.relocate(90, 270);
        sortTypeComboBox.setPrefSize(100, 20);
        sortTypeComboBox.setEditable(false);
        sortTypeComboBox.getSelectionModel().select(1);

        Label filterLabel = new Label("Filter:");
        filterLabel.relocate(20,320);
        filterLabel.setTextFill(Color.rgb(190,190,190));
        filterLabel.setStyle("-fx-font-size: 15px; -fx-font-family: helvetica; -fx-background-color: transparent;");

        highCheckBox = new CheckBox("High Priority");
        highCheckBox.relocate(20, 350);
        highCheckBox.setTextFill(Color.rgb(190,190,190));
        highCheckBox.setStyle("-fx-font-size: 15px; -fx-font-family: helvetica;");
        highCheckBox.setSelected(true);

        mediumCheckBox = new CheckBox("Medium Priority");
        mediumCheckBox.relocate(20, 380);
        mediumCheckBox.setTextFill(Color.rgb(190,190,190));
        mediumCheckBox.setStyle("-fx-font-size: 15px; -fx-font-family: helvetica;");
        mediumCheckBox.setSelected(true);

        lowCheckBox = new CheckBox("Low Priority");
        lowCheckBox.relocate(20, 410);
        lowCheckBox.setTextFill(Color.rgb(190,190,190));
        lowCheckBox.setStyle("-fx-font-size: 15px; -fx-font-family: helvetica;");
        lowCheckBox.setSelected(true);

        categoryPane = new CategoryPane(model);
        categoryPane.setPrefSize(WIDTH, 420);
        categoryPane.relocate(0, 480);
        categoryPane.setMaxHeight(420);

        getChildren().addAll(title, dateLabel, addTaskButton, todayButton, tasksButton, overdueTasksButton, sortTypeLabel, sortTypeComboBox,
                filterLabel, highCheckBox, mediumCheckBox, lowCheckBox, categoryPane);
    }

    public Label getDateLabel() {
        return dateLabel;
    }
    public CustomButton getTodayButton() {
        return todayButton;
    }
    public CustomButton getTasksButton() {
        return tasksButton;
    }
    public CustomButton getAddTaskButton() {
        return addTaskButton;
    }
    public ComboBox getSortTypeComboBox() {
        return sortTypeComboBox;
    }
    public CategoryPane getCategoryPane() {
        return categoryPane;
    }
    public CustomButton getOverdueTasksButton() {
        return overdueTasksButton;
    }
    public LocalDate getCurrentDate() {
        return currentDate;
    }
    public CheckBox getHighCheckBox() {
        return highCheckBox;
    }
    public CheckBox getMediumCheckBox() {
        return mediumCheckBox;
    }
    public CheckBox getLowCheckBox() {
        return lowCheckBox;
    }

    public CheckBox[] getFilters() {
        return new CheckBox[] {highCheckBox, mediumCheckBox, lowCheckBox};
    }

    public ArrayList<CustomButton> getAllButtons() {
        ArrayList<CustomButton> customButtons = new ArrayList<>();
        customButtons.add(todayButton);
        customButtons.add(tasksButton);
        customButtons.add(addTaskButton);
        customButtons.add(overdueTasksButton);
        customButtons.add(categoryPane.getAddCategoryButton());
        customButtons.addAll(categoryPane.getCategoryButtons());
        return customButtons;
    }

    public void disableFilters(boolean disable) {
        highCheckBox.setDisable(disable);
        mediumCheckBox.setDisable(disable);
        lowCheckBox.setDisable(disable);
    }
}
