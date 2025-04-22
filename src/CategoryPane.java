import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class CategoryPane extends Pane {
    TaskCollection model;
    Button addCategoryButton;

    public CategoryPane(TaskCollection model) {
        this.model = model;

        Label categoriesLabel = new Label("Categories:");
        categoriesLabel.relocate(20,0);
        categoriesLabel.setTextFill(Color.rgb(180,180,180));
        categoriesLabel.setStyle("-fx-font-size: 16px;" +
                "-fx-font-family: helvetica;" +
                "-fx-background-color: transparent;");

        double y = update();

        addCategoryButton = new Button("+ Category");
        addCategoryButton.setPrefSize(200, 30);
        addCategoryButton.relocate(15,y);
        addCategoryButton.setTextFill(Color.rgb(180,180,180));
        addCategoryButton.setStyle("-fx-font-size: 15px;" +
                "-fx-font-family: helvetica;" +
                "-fx-background-color: transparent;" +
                "-fx-alignment: center_left;");
        getChildren().addAll(categoriesLabel, addCategoryButton);
    }

    public double update() {
        double y = 30;
        for (String c: model.getCategories()) {
            Button b = new Button(c);
            b.setPrefSize(200,30);
            b.relocate(15, y);
            b.setTextFill(Color.rgb(180,180,180));
            b.setStyle("-fx-font-size: 15px;" +
                    "-fx-font-family: helvetica;" +
                    "-fx-background-color: transparent;" +
                    "-fx-alignment: center_left;");
            getChildren().add(b);
            y += 30;
        }
        return y;
    }
}
