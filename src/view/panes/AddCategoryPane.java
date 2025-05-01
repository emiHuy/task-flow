package view.panes;

import view.interfaces.ResettablePane;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class AddCategoryPane extends Pane implements ResettablePane {
    private TextField categoryTextField;
    private Button createCategoryButton;
    private Label outcomeLabel;

    public AddCategoryPane() {
        // header
        createLabel("Add Category", 210, 50, 25);

        // components for category input
        createLabel("Category: ", 150, 120, 18);
        categoryTextField = new TextField();
        categoryTextField.relocate(240, 120);
        categoryTextField.setPrefSize(185, 20);
        categoryTextField.setStyle("-fx-font-size: 16px; -fx-font-family: helvetica;");

        // button for creating new category
        createCategoryButton = new Button("Create New Category");
        createCategoryButton.relocate(187.5, 190);
        createCategoryButton.setPrefSize(200, 30);
        createCategoryButton.setStyle("-fx-font-size: 17px; -fx-font-family: helvetica;");

        outcomeLabel = createLabel("", 200, 260, 16);
        outcomeLabel.setVisible(false);

        setStyle("-fx-background-color: rgb(220,220,220)");
        getChildren().addAll(categoryTextField, createCategoryButton);
    }

    public TextField getCategoryTextField() {
        return categoryTextField;
    }
    public Button getCreateCategoryButton() {
        return createCategoryButton;
    }

    public void reset() {
        categoryTextField.setText("");
        outcomeLabel.setVisible(false);
    }

    public void displayOutcome(boolean isSuccessful) {
        if (isSuccessful) {
            modifyLabel(outcomeLabel, "New task created.", 230, Color.rgb(0,155,0));
        } else {
            modifyLabel(outcomeLabel, "All fields must be filled and no duplicates are allowed.", 100, Color.rgb(185,0,0));
        }
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
        label.relocate(x, 260);
        label.setTextFill(color);
        label.setVisible(true);
    }
}