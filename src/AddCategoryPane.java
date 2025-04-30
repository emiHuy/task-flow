import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class AddCategoryPane extends Pane implements ResettablePane {
    private TextField categoryTextField;
    private Label successLabel;
    private Button createCategoryButton;

    public AddCategoryPane() {
        Label header = new Label("Add Category");
        header.relocate(210, 250);
        header.setStyle("-fx-font-size: 25px; -fx-font-family: helvetica;");

        Label categoryLabel = new Label("Category Name:");
        categoryLabel.relocate(150,330);
        categoryLabel.setStyle("-fx-font-size: 18px; -fx-font-family: helvetica;");

        categoryTextField = new TextField();
        categoryTextField.relocate(290, 330);
        categoryTextField.setStyle("-fx-font-size: 16px; -fx-font-family: helvetica;");
        categoryTextField.setPrefSize(135, 20);

        createCategoryButton = new Button("Create New Category");
        createCategoryButton.relocate(187.5, 400);
        createCategoryButton.setStyle("-fx-font-size: 17px; -fx-font-family: helvetica;");
        createCategoryButton.setPrefSize(200, 30);

        successLabel = new Label();
        successLabel.relocate(200, 500);
        successLabel.setStyle("-fx-font-size: 16px; -fx-font-family: helvetica;");
        successLabel.setVisible(false);

        setStyle("-fx-background-color: rgb(220,220,220)");
        getChildren().addAll(header, categoryLabel, categoryTextField, createCategoryButton, successLabel);
    }

    public TextField getCategoryTextField() {
        return categoryTextField;
    }

    public Button getCreateCategoryButton() {
        return createCategoryButton;
    }

    public void reset() {
        categoryTextField.setText("");
        successLabel.setVisible(false);
    }

    public void displayOutcome(boolean isSuccessful) {
        if (isSuccessful) {
            successLabel.setText("New task created.");
            successLabel.setTextFill(Color.rgb(0,155,0));
            successLabel.relocate(230,500);
            successLabel.setVisible(true);
        } else {
            successLabel.setText("All fields must be filled and no duplicates are allowed.");
            successLabel.setTextFill(Color.rgb(185,0,0));
            successLabel.relocate(100,500);
            successLabel.setVisible(true);
        }
    }
}
