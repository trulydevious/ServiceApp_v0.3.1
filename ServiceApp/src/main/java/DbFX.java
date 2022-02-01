import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ib.EmployeeAccounts;
import ib.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DbFX {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnClient;

    @FXML
    private Button btnServiceUser;

    @FXML
    private Button btnRegister;

    @FXML
    private PasswordField edtPassword;

    @FXML
    private TextField edtUser;

    @FXML
    private TextField edtClient;

    @FXML
    void edtClientID(ActionEvent event) {

    }

    @FXML
    void edtPassword(ActionEvent event) {

    }

    @FXML
    void btnRegisterClicked(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/dbFXNewClient.fxml"));
        Parent root = loader.load();

        Stage fourthStage = new Stage();
        Scene scene = new Scene(root,675,525);
        fourthStage.setScene(scene);
        fourthStage.show();

        Stage stage = (Stage) btnClient.getScene().getWindow();
        stage.close();
    }

    @FXML
    void LoginClient(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/dbFXClient.fxml"));
        Parent root = loader.load();
        DbFXClient scene2Controller = loader.getController();
        scene2Controller.transferMessage(edtClient.getText());

        Stage thirdStage = new Stage();
        Scene scene = new Scene(root,675,525);
        thirdStage.setScene(scene);
        thirdStage.show();

        Stage stage = (Stage) btnClient.getScene().getWindow();
        stage.close();
    }

    @FXML
    void LoginService(ActionEvent event) throws IOException{
        EmployeeAccounts employee = new EmployeeAccounts();
        String login = edtUser.getText().toString();
        String password = edtPassword.getText().toString();
        if (login.equals(employee.getLogin()) && password.equals(employee.getPassword())) {
            Stage secondStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/dbFXServer.fxml"));
            Scene scene = new Scene(root, 675, 525);
            secondStage.setScene(scene);
            secondStage.show();
        }
        Stage stage = (Stage) btnServiceUser.getScene().getWindow();
        stage.close();

    }

    @FXML
    void initialize() {
        assert btnClient != null : "fx:id=\"btnClient\" was not injected: check your FXML file 'dbFX.fxml'.";
        assert btnServiceUser != null : "fx:id=\"btnServiceUser\" was not injected: check your FXML file 'dbFX.fxml'.";
        assert btnRegister != null : "fx:id=\"btnRegister\" was not injected: check your FXML file 'dbFX.fxml'.";
        assert edtPassword != null : "fx:id=\"edtPassword\" was not injected: check your FXML file 'dbFX.fxml'.";
        assert edtUser != null : "fx:id=\"edtUser\" was not injected: check your FXML file 'dbFX.fxml'.";
        assert edtClient != null : "fx:id=\"edtClient\" was not injected: check your FXML file 'dbFX.fxml'.";
    }

}
