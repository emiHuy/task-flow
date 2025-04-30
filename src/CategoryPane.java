import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CategoryPane extends Pane {
    private TaskCollection model;
    private CustomButton addCategoryButton;
    private Pane categoryListPane;
    private ArrayList<CustomButton> categoryButtons;

    public CategoryPane(TaskCollection model) {
        this.model = model;

        Label categoriesLabel = new Label("Categories:");
        categoriesLabel.relocate(20,0);
        categoriesLabel.setTextFill(Color.rgb(180,180,180));
        categoriesLabel.setStyle("-fx-font-size: 16px;" +
                "-fx-font-family: helvetica;" +
                "-fx-background-color: transparent;");

        addCategoryButton = new CustomButton("+ Category", 15);
        addCategoryButton.setPrefSize(190, 30);
        addCategoryButton.relocate(15,30);
        addCategoryButton.setTextFill(Color.rgb(180,180,180));

        categoryListPane = new Pane();
        categoryListPane.setStyle("-fx-background-color: rgb(30,30,30);");

        ScrollPane categoryListScrollPane = new ScrollPane();
        categoryListScrollPane.setContent(categoryListPane);
        categoryListScrollPane.setPrefSize(225,370);
        categoryListScrollPane.relocate(0, 60);
        categoryListScrollPane.setFitToWidth(true);
        categoryListScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        categoryListScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        categoryListScrollPane.setStyle("-fx-background-color: rgb(30,30,30); -fx-background: rgb(30,30,30);");

        categoryButtons = new ArrayList<CustomButton>();

        update();

        setStyle("-fx-background-color: rgb(30,30,30);");
        getChildren().addAll(categoriesLabel, addCategoryButton, categoryListScrollPane);
    }

    public CustomButton getAddCategoryButton() {
        return addCategoryButton;
    }

    public ArrayList<CustomButton> getCategoryButtons() {
        return categoryButtons;
    }

    public void update() {
        categoryListPane.getChildren().removeIf(node -> node instanceof CustomButton && ((CustomButton)node).getText() != "+ Category");
        double y = 0;
        for (String c: model.getCategories()) {
            CustomButton b = new CustomButton(c, 15);
            b.setPrefSize(190,30);
            b.relocate(15, y);
            b.setTextFill(Color.rgb(180,180,180));
            categoryListPane.getChildren().add(b);
            categoryButtons.add(b);
            y += 30;
        }
    }
}
