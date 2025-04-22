import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.time.LocalDate;

public class SideMenu extends Pane {
    public static final double WIDTH = 225;
    TaskCollection model;
    Label dateLabel;
    Button todayButton;
    Button tasksButton;
    Button addTaskButton;
    CategoryPane categoryPane;
    DatePicker datePicker;
    LocalDate currentDate;

    public SideMenu(TaskCollection model) {
        this.model = model;
        currentDate = LocalDate.now();

        Rectangle background = new Rectangle(0,0, 225, 1080);
        background.setFill(Color.rgb(30,30,30));

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

        addTaskButton = new Button("+ Task");
        addTaskButton.setPrefSize(WIDTH, 40);
        addTaskButton.relocate(12, 60);
        addTaskButton.setTextFill(Color.rgb(190,190,190));
        addTaskButton.setStyle("-fx-font-size: 16px;" +
                "-fx-font-family: helvetica;" +
                "-fx-background-color: transparent;" +
                "-fx-alignment: center_left;");

        todayButton = new Button("Today's Tasks");
        todayButton.setPrefSize(WIDTH, 40);
        todayButton.relocate(12, 100);
        todayButton.setTextFill(Color.rgb(190,190,190));
        todayButton.setStyle("-fx-font-size: 16px;" +
                "-fx-font-family: helvetica;" +
                "-fx-background-color: transparent;" +
                "-fx-alignment: center_left;");

        tasksButton = new Button("All tasks");
        tasksButton.setPrefSize(WIDTH, 40);
        tasksButton.relocate(12, 140);
        tasksButton.setTextFill(Color.rgb(190,190,190));
        tasksButton.setStyle("-fx-font-size: 15px;" +
                "-fx-font-family: helvetica;" +
                "-fx-background-color: transparent;" +
                "-fx-alignment: center_left;");

        String[] sortTypes = {"priority", "date"};
        Label sortTypeLabel = new Label("Sort by: ");
        sortTypeLabel.relocate(20,230);
        sortTypeLabel.setTextFill(Color.rgb(190,190,190));
        sortTypeLabel.setStyle("-fx-font-size: 15px;" +
                "-fx-font-family: helvetica;" +
                "-fx-background-color: transparent;");

        ComboBox sortTypeComboBox = new ComboBox(FXCollections.observableArrayList(sortTypes));
        sortTypeComboBox.relocate(90, 230);
        sortTypeComboBox.setPrefSize(100, 20);

        Label filterLabel = new Label("Filter:");
        filterLabel.relocate(20,280);
        filterLabel.setTextFill(Color.rgb(190,190,190));
        filterLabel.setStyle("-fx-font-size: 15px;" +
                "-fx-font-family: helvetica;" +
                "-fx-background-color: transparent;");

        CheckBox highCheckBox = new CheckBox("High Priority");
        highCheckBox.relocate(20, 310);
        highCheckBox.setTextFill(Color.rgb(190,190,190));
        highCheckBox.setStyle("-fx-font-size: 15px;" +
                "-fx-font-family: helvetica;");
        CheckBox mediumCheckBox = new CheckBox("Medium Priority");
        mediumCheckBox.relocate(20, 340);
        mediumCheckBox.setTextFill(Color.rgb(190,190,190));
        mediumCheckBox.setStyle("-fx-font-size: 15px;" +
                "-fx-font-family: helvetica;");
        CheckBox lowCheckBox = new CheckBox("Low Priority");
        lowCheckBox.relocate(20, 370);
        lowCheckBox.setTextFill(Color.rgb(190,190,190));
        lowCheckBox.setStyle("-fx-font-size: 15px;" +
                "-fx-font-family: helvetica;");

        categoryPane = new CategoryPane(model);
        categoryPane.setPrefSize(WIDTH, 340);
        categoryPane.relocate(0, 440);
        categoryPane.setMaxHeight(340);

        Label datePickerLabel = new Label("Set date:");
        datePickerLabel.relocate(20,820);
        datePickerLabel.setTextFill(Color.rgb(190,190,190));
        datePickerLabel.setStyle("-fx-font-size: 15px;" +
                "-fx-font-family: helvetica;" +
                "-fx-background-color: transparent;");
        datePicker = new DatePicker();
        datePicker.setPrefSize(185,20);
        datePicker.relocate(20,850);
        datePicker.setValue(LocalDate.now());

        datePicker.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.compareTo(currentDate) < 0 );
            }
        });

        getChildren().addAll(background, title, dateLabel, addTaskButton, todayButton, tasksButton, sortTypeLabel, sortTypeComboBox,
                filterLabel, highCheckBox, mediumCheckBox, lowCheckBox, categoryPane, datePickerLabel, datePicker);
    }
}
