package ib;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SceneController {
    @FXML
    private TextField display;

    public void transferMessage(String message) {
        display.setText(message);
    }
}
