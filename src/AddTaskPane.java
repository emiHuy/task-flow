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
    private Label successLabel;

    public AddTaskPane(TaskCollection model) {
        this.model = model;

        Label header = new Label("Add Task");
        header.relocate(235, 170);
        header.setStyle("-fx-font-size: 25px; -fx-font-family: helvetica;");

        Label actionLabel = new Label("Task:");
        actionLabel.relocate(150,250);
        actionLabel.setStyle("-fx-font-size: 18px; -fx-font-family: helvetica;");

        actionTextField = new TextField();
        actionTextField.relocate(200,250);
        actionTextField.setPrefSize(225,20);
        actionTextField.setStyle("-fx-font-size: 16px; -fx-font-family: helvetica;");

        Label priorityLabel = new Label("Priority Level:");
        priorityLabel.relocate(150,315);
        priorityLabel.setStyle("-fx-font-size: 18px; -fx-font-family: helvetica;");

        String[] priorities = {"High", "Medium", "Low"};
        priorityComboBox = new ComboBox(FXCollections.observableArrayList(priorities));
        priorityComboBox.relocate(275, 315);
        priorityComboBox.setPrefSize(150,20);
        priorityComboBox.setStyle("-fx-font-size: 16px; -fx-font-family: helvetica;");
        priorityComboBox.setEditable(false);

        Label dateLabel = new Label("Date:");
        dateLabel.relocate(150,380);
        dateLabel.setStyle("-fx-font-size: 18px; -fx-font-family: helvetica;");

        datePicker = new DatePicker();
        datePicker.relocate(200,380);
        datePicker.setPrefSize(225,20);
        datePicker.setStyle("-fx-font-size: 16px; -fx-font-family: helvetica;");
        datePicker.setEditable(false);
//        datePicker.setDayCellFactory(picker -> new DateCell() {
//            public void updateItem(LocalDate date, boolean empty) {
//                super.updateItem(date, empty);
//                setDisable(empty || date.compareTo(LocalDate.now()) < 0 );
//            }
//        });

        Label categoryLabel = new Label("Category:");
        categoryLabel.relocate(150,445);
        categoryLabel.setStyle("-fx-font-size: 18px; -fx-font-family: helvetica;");

        categoryComboBox = new ComboBox(FXCollections.observableArrayList(model.getCategories()));
        categoryComboBox.relocate(240,445);
        categoryComboBox.setStyle("-fx-font-size: 16px; -fx-font-family: helvetica;");
        categoryComboBox.setPrefSize(185,20);
        categoryComboBox.setEditable(false);

        createTaskButton = new Button("Create New Task");
        createTaskButton.relocate(200, 550);
        createTaskButton.setStyle("-fx-font-size: 17px; -fx-font-family: helvetica;");
        createTaskButton.setPrefSize(175, 30);

        successLabel = new Label();
        successLabel.relocate(200, 650);
        successLabel.setStyle("-fx-font-size: 16px; -fx-font-family: helvetica;");
        successLabel.setVisible(false);

        setStyle("-fx-background-color: rgb(220,220,220)");
        getChildren().addAll(header, actionLabel, actionTextField, priorityLabel, priorityComboBox, dateLabel, datePicker,
                categoryLabel, categoryComboBox, createTaskButton, successLabel);
    }

    public void reset() {
        actionTextField.setText("");
        successLabel.setVisible(false);
        priorityComboBox.getSelectionModel().select(0);
        datePicker.setValue(LocalDate.now());
        categoryComboBox.getSelectionModel().select("Miscellaneous");
    }

    public void displayOutcome(boolean isSuccessful) {
        if (isSuccessful) {
            successLabel.setText("New task created.");
            successLabel.setTextFill(Color.rgb(0,155,0));
            successLabel.relocate(225,650);
            successLabel.setVisible(true);
        } else {
            successLabel.setText("All fields must be filled and no duplicates are allowed.");
            successLabel.setTextFill(Color.rgb(185,0,0));
            successLabel.relocate(75,650);
            successLabel.setVisible(true);
        }
    }

    public void updateCategoryOptions() {
        categoryComboBox.setItems(FXCollections.observableArrayList(model.getCategories()));
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
}
