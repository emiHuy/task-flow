import javafx.scene.control.Button;

public class CustomButton extends Button {
    boolean selected;
    int fontSize;

    public CustomButton(String text, int fontSize) {
        super(text);
        this.fontSize = fontSize;
        selected = false;
        deselect();
    }

    public boolean isSelected() {
        return selected;
    }

    public void select() {
        setStyle("-fx-font-size: " + fontSize + "px; -fx-font-family: helvetica; -fx-background-color: rgb(50,50,50); -fx-alignment: center_left;");
        selected = true;
    }

    public void deselect() {
        setStyle("-fx-font-size: " + fontSize + "px; -fx-font-family: helvetica; -fx-background-color: transparent; -fx-alignment: center_left;");
        selected = false;
    }
}
