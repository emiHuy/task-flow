package view.panes;

import model.TaskCollection;
import view.interfaces.ResettablePane;

import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import java.time.LocalDate;

public class AddTaskPane extends Pane implements ResettablePane {
    private TaskCollection model;
    private TextField actionTextField;
    private ComboBox priorityComboBox;
    private DatePicker datePicker;
    private ComboBox categoryComboBox;
    private Button createTaskButton;
    private Label outcomeLabel;

    public AddTaskPane(TaskCollection model) {
        this.model = model;

        // header
        createLabel("Add Task", 235, 50, 25);

        // components for task action input
        createLabel("Task:", 150, 130, 18);
        actionTextField = new TextField();
        actionTextField.relocate(200,130);
        actionTextField.setPrefSize(225,20);
        actionTextField.setStyle("-fx-font-size: 16px; -fx-font-family: helvetica;");

        // components for task priority level input
        createLabel("Priority Level:", 150, 180, 18);
        String[] priorities = {"High", "Medium", "Low"};
        priorityComboBox = new ComboBox(FXCollections.observableArrayList(priorities));
        setComboBox(priorityComboBox, 265, 180, 160);

        // components for task date input
        createLabel("Date:", 150, 230, 18);
        datePicker = new DatePicker();
        datePicker.relocate(200,230);
        datePicker.setPrefSize(225,20);
        datePicker.setStyle("-fx-font-size: 16px; -fx-font-family: helvetica;");
        datePicker.setEditable(false);
        datePicker.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.compareTo(LocalDate.now()) < 0 );
            }
        });

        // components for task category input
        createLabel("Category:", 150, 280, 18);
        categoryComboBox = new ComboBox(FXCollections.observableArrayList(model.getCategories()));
        setComboBox(categoryComboBox, 240, 280, 185);

        // button for creating task
        createTaskButton = new Button("Create New Task");
        createTaskButton.relocate(200, 360);
        createTaskButton.setStyle("-fx-font-size: 17px; -fx-font-family: helvetica;");
        createTaskButton.setPrefSize(175, 30);

        outcomeLabel = createLabel("", 200, 440, 16);
        outcomeLabel.setVisible(false);

        setStyle("-fx-background-color: rgb(220,220,220)");
        getChildren().addAll(actionTextField, priorityComboBox, datePicker, categoryComboBox, createTaskButton);
    }

    public TextField getActionTextField() {
        return actionTextField;
    }
    public ComboBox getPriorityComboBox() {
        return priorityComboBox;
    }
    public DatePicker getDatePicker() {
        return datePicker;
    }
    public ComboBox getCategoryComboBox() {
        return categoryComboBox;
    }
    public Button getCreateTaskButton() {
        return createTaskButton;
    }

    public void reset() {
        actionTextField.setText("");
        outcomeLabel.setVisible(false);
        priorityComboBox.getSelectionModel().select(0);
        datePicker.setValue(LocalDate.now());
        categoryComboBox.getSelectionModel().select(0);
    }

    public void displayOutcome(boolean isSuccessful) {
        if (isSuccessful) {
            modifyLabel(outcomeLabel, "New task created.", 225, Color.rgb(0,155,0));
        } else {
            modifyLabel(outcomeLabel, "All fields must be filled and no duplicates are allowed.", 100, Color.rgb(185,0,0));
        }
    }

    public void updateCategoryOptions() {
        categoryComboBox.setItems(FXCollections.observableArrayList(model.getCategories()));
    }

    private Label createLabel(String text, int x, int y, int fontSize) {
        Label l = new Label(text);
        l.relocate(x,y);
        l.setStyle("-fx-font-size: " + fontSize + "px; -fx-font-family: helvetica;");
        getChildren().add(l);
        return l;
    }

    private void modifyLabel(Label label, String text, int x, Color color) {
        label.setText(text);
        label.relocate(x, 440);
        label.setTextFill(color);
        label.setVisible(true);
    }

    private void setComboBox(ComboBox comboBox, int x, int y, int width) {
        comboBox.relocate(x,y);
        comboBox.setPrefSize(width,20);
        comboBox.setStyle("-fx-font-size: 16px; -fx-font-family: helvetica;");
        comboBox.setEditable(false);
    }
}