import javafx.scene.layout.Pane;

public class ToDoListView extends Pane {
    TaskCollection model;
    SideMenu sideMenu;

    public ToDoListView(TaskCollection model) {
        this.model = model;
        sideMenu = new SideMenu(model);
        sideMenu.relocate(0,0);
        sideMenu.setPrefSize(225, 1080);
        getChildren().addAll(sideMenu);
    }
}
