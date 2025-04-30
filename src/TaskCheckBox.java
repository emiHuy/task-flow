import javafx.scene.control.CheckBox;

public class TaskCheckBox extends CheckBox {
    private Task reference;

    public TaskCheckBox(String text, Task reference) {
        super(text);
        this.reference = reference;
    }

    public Task getReference() {
        return reference;
    }
}
