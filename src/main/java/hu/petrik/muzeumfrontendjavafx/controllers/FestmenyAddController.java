package hu.petrik.muzeumfrontendjavafx.controllers;

import hu.petrik.muzeumfrontendjavafx.Controller;
import hu.petrik.muzeumfrontendjavafx.api.MuzeumApi;
import hu.petrik.muzeumfrontendjavafx.classes.Festmeny;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

public class FestmenyAddController extends Controller {
    @FXML private TextField inputCim;
    @FXML private Spinner<Integer> inputEv;
    @FXML private CheckBox inputCheckBox;

    @FXML
    public void onHozzaadButtonClick(ActionEvent actionEvent) {
        boolean checkboxBool;

        checkboxBool = inputCheckBox.isSelected();
        String cim = inputCim.getText().trim();
        int ev;

        if (cim.isEmpty()) {
            alert("Cím megadása kötelező.");
            return;
        }
        try {
            ev = inputEv.getValue();
        } catch (NullPointerException e) {
            alert("Év kiválasztása kötelező.");
            return;
        } catch (Exception e) {
            alert("Az év csak 1 és 3000 közötti szám lehet.");
            return;
        }

        try {
            Festmeny ujFestmeny = new Festmeny(0, cim, ev, checkboxBool);
            Festmeny letrehozott = MuzeumApi.festmenyHozzaadasa(ujFestmeny);
            if (letrehozott != null) {
                alert("Festmény hozzáadása sikeres!");
                this.stage.close();
            } else {
                alert("Festmény hozzáadása sikertelen!");
            }
        } catch (Exception e) {
            hibaKiir(e);
        }
    }
}
