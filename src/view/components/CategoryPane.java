package view.components;

import model.TaskCollection;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import java.util.ArrayList;

public class CategoryPane extends Pane {
    private TaskCollection model;
    private SideMenuButton addCategoryButton;
    private Pane categoryListPane;
    private ArrayList<SideMenuButton> categoryButtons;

    public CategoryPane(TaskCollection model) {
        this.model = model;

        // create pane label
        Label categoriesLabel = new Label("Categories:");
        categoriesLabel.relocate(20,0);
        categoriesLabel.setTextFill(Color.rgb(190,190,190));
        categoriesLabel.setStyle("-fx-font-size: 16px; -fx-font-family: helvetica; -fx-background-color: transparent;");

        // create add category button
        addCategoryButton = new SideMenuButton("+ Category", 15);
        addCategoryButton.setPrefSize(190, 30);
        addCategoryButton.relocate(15,30);

        // create pane containing task category buttons
        categoryListPane = new Pane();
        categoryListPane.setStyle("-fx-background-color: rgb(30,30,30); -fx-background: rgb(30,30,30);");

        // create scroll pane to allow scrolling of categoryListPane as needed (if there are many category buttons)
        ScrollPane categoryListScrollPane = new ScrollPane();
        categoryListScrollPane.relocate(0, 60);
        categoryListScrollPane.setContent(categoryListPane);
        categoryListScrollPane.setPrefSize(225,370);
        categoryListScrollPane.setFitToWidth(true);
        categoryListScrollPane.setStyle("-fx-background-color: rgb(30,30,30); -fx-background: rgb(30,30,30);");

        categoryButtons = new ArrayList<SideMenuButton>();

        update();
        setStyle("-fx-background-color: rgb(30,30,30);");
        getChildren().addAll(categoriesLabel, addCategoryButton, categoryListScrollPane);
    }

    public SideMenuButton getAddCategoryButton() {
        return addCategoryButton;
    }
    public ArrayList<SideMenuButton> getCategoryButtons() {
        return categoryButtons;
    }

    // updates category buttons
    public void update() {
        categoryListPane.getChildren().clear();
        categoryButtons.clear();
        int y = 0;
        for (String c: model.getCategories()) {
            SideMenuButton b = new SideMenuButton(c, 15);
            b.relocate(15, y);
            b.setPrefSize(190,30);
            categoryListPane.getChildren().add(b);
            categoryButtons.add(b);
            y += 30;
        }
    }
}