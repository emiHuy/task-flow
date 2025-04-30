package view;

import model.TaskCollection;
import view.components.CategoryPane;
import view.components.SideMenuButton;

import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import java.time.LocalDate;
import java.util.ArrayList;

public class SideMenu extends Pane {
    private SideMenuButton addTaskButton;
    private SideMenuButton todayButton;
    private SideMenuButton allTasksButton;
    private SideMenuButton overdueTasksButton;
    private ComboBox sortTypeComboBox;
    private CheckBox highCheckBox;
    private CheckBox mediumCheckBox;
    private CheckBox lowCheckBox;
    private CategoryPane categoryPane;

    public SideMenu(TaskCollection model) {
        // top of side menu: title of app and current date
        createLabel("TaskFlow", 10, 10, Color.rgb(90,90,90), 14);
        createLabel("" + LocalDate.now(), 145, 10, Color.rgb(90,90,90), 14);

        // menu buttons
        addTaskButton = createButton("+ Task", 60);
        todayButton = createButton("Today's Tasks", 100);
        allTasksButton = createButton("All Tasks", 140);
        overdueTasksButton = createButton("Overdue Tasks", 180);

        // components for sort type input
        String[] sortTypes = {"priority", "date"};
        createLabel("Sort by: ", 20, 270, Color.rgb(190,190,190), 15);
        sortTypeComboBox = new ComboBox(FXCollections.observableArrayList(sortTypes));
        sortTypeComboBox.relocate(90, 270);
        sortTypeComboBox.setPrefSize(100, 20);
        sortTypeComboBox.setEditable(false);
        sortTypeComboBox.getSelectionModel().select(1);

        // components for filter input
        createLabel("Filter:", 20, 320, Color.rgb(190,190,190), 15);
        highCheckBox = createFilterCheckBox("High Priority", 350);
        mediumCheckBox = createFilterCheckBox("Medium Priority", 380);
        lowCheckBox = createFilterCheckBox("Low Priority", 410);

        // pane containing categories of tasks
        categoryPane = new CategoryPane(model);
        categoryPane.setPrefSize(225, 420);
        categoryPane.relocate(0, 480);
        categoryPane.setMaxHeight(420);

        setStyle("-fx-background-color: rgb(30,30,30);");
        getChildren().addAll(sortTypeComboBox, categoryPane);
    }

    public SideMenuButton getTodayButton() {
        return todayButton;
    }
    public SideMenuButton getAllTasksButton() {
        return allTasksButton;
    }
    public SideMenuButton getAddTaskButton() {
        return addTaskButton;
    }
    public ComboBox getSortTypeComboBox() {
        return sortTypeComboBox;
    }
    public CategoryPane getCategoryPane() {
        return categoryPane;
    }
    public SideMenuButton getOverdueTasksButton() {
        return overdueTasksButton;
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
    public ArrayList<SideMenuButton> getAllButtons() {
        ArrayList<SideMenuButton> sideMenuButtons = new ArrayList<>();
        sideMenuButtons.add(todayButton);
        sideMenuButtons.add(allTasksButton);
        sideMenuButtons.add(addTaskButton);
        sideMenuButtons.add(overdueTasksButton);
        sideMenuButtons.add(categoryPane.getAddCategoryButton());
        sideMenuButtons.addAll(categoryPane.getCategoryButtons());
        return sideMenuButtons;
    }

    public void disableFilters(boolean disable) {
        highCheckBox.setDisable(disable);
        mediumCheckBox.setDisable(disable);
        lowCheckBox.setDisable(disable);
    }

    private void createLabel(String text, int x, int y, Color color, int fontSize) {
        Label l = new Label(text);
        l.relocate(x,y);
        l.setTextFill(color);
        l.setStyle("-fx-font-size: " + fontSize + "px; -fx-font-family: helvetica;");
        getChildren().add(l);
    }

    private SideMenuButton createButton(String text, int y) {
        SideMenuButton b = new SideMenuButton(text,16);
        b.relocate(12,y);
        b.setPrefSize(200, 40);
        b.setTextFill(Color.rgb(190,190,190));
        getChildren().add(b);
        return b;
    }

    private CheckBox createFilterCheckBox(String text, int y) {
        CheckBox c = new CheckBox(text);
        c.relocate(20,y);
        c.setStyle("-fx-font-size: 15px; -fx-font-family: helvetica;");
        c.setTextFill(Color.rgb(190,190,190));
        c.setSelected(true);
        getChildren().add(c);
        return c;
    }
}