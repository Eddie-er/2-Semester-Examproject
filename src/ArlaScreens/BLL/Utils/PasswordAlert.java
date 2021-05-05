package ArlaScreens.BLL.Utils;

import ArlaScreens.GUI.Controller.EditUserViewController;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;

import java.util.Optional;


public class PasswordAlert extends EditUserViewController{


    public static void display() {
       // EditUserViewController editUserViewController = new EditUserViewController();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setHeight(200);
        alert.setWidth(300);
        alert.setResizable(false);
        alert.setTitle("Bekræft sletning");
        alert.setHeaderText("Er du sikker på at du vil slette kodeordet?");
        alert.setContentText("Kodeordet vil blive sat til : banan69");
        ButtonType confirm = new ButtonType("Bekræft");
        ButtonType cancel = new ButtonType("Fortryd", (ButtonBar.ButtonData.CANCEL_CLOSE));
        alert.getButtonTypes().setAll(confirm, cancel);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == confirm) {
            resetPassword = true;
        }
    }
}