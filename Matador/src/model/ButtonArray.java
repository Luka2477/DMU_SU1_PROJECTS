package model;

import javafx.scene.control.Button;

import java.util.ArrayList;

public class ButtonArray {
    private final ArrayList<Button> buttons;
    private final ArrayList<String> buttonNames;

    public ButtonArray() {
        this.buttons = new ArrayList<>();
        this.buttonNames = new ArrayList<>();
    }

    public Button get(String name) {
        return this.buttons.get(this.buttonNames.indexOf(name));
    }

    public void add(String name, Button button) {
        this.buttons.add(button);
        this.buttonNames.add(name);
    }

    public void removeAll() {
        for(int i=this.buttons.toArray().length - 1; i>=0; i--) {
            this.buttons.remove(i);
            this.buttonNames.remove(i);
        }
    }
}
