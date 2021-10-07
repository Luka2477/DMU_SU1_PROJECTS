package model;

import javafx.scene.control.TextField;

public class NumericTextField extends TextField {
    public NumericTextField() {
        super();
        NumericTextField finalThis = this;
        this.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                finalThis.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }
}
