import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ToDoListApp extends Application {
    TaskCollection model;
    ToDoListView view;

    public void start(Stage primaryStage) {
        model = new TaskCollection();
        view = new ToDoListView(model);

        primaryStage.setResizable(false);
        primaryStage.setTitle("TaskFlow");
        primaryStage.setScene(new Scene(view,800,900));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
