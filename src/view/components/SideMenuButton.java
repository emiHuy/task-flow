package view.components;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class SideMenuButton extends Button {
    private boolean selected;
    private int fontSize;

    public SideMenuButton(String text, int fontSize) {
        super(text);
        this.fontSize = fontSize;
        selected = false;
        deselect();
        setTextFill(Color.rgb(190, 190, 190));
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
