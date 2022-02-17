package hu.petrik.muzeumfrontendjavafx.controllers;

import hu.petrik.muzeumfrontendjavafx.Controller;
import hu.petrik.muzeumfrontendjavafx.api.MuzeumApi;
import hu.petrik.muzeumfrontendjavafx.classes.Festmeny;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

import java.io.IOException;

public class FestmenyEditController extends Controller {
    @FXML private TextField inputCim;
    @FXML private CheckBox inputCheckBox;
    @FXML private Spinner<Integer> inputEv;

    private Festmeny modositando;

    public Festmeny getModositando() {
        return modositando;
    }

    public void setModositando(Festmeny modositando) {
        this.modositando = modositando;
        ertekekBeallitasa();
    }


    private void ertekekBeallitasa() {
        inputCim.setText(modositando.getTitle());
        inputCheckBox.setSelected(modositando.isOn_display());
        inputEv.getValueFactory().setValue(modositando.getYear());
    }

    @FXML
    public void onModositButtonClick(ActionEvent actionEvent) {
        String cim = inputCim.getText().trim();
        boolean kiallitvaE = inputCheckBox.isSelected();
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
            alert("A hossz csak 1 és 3000 közötti szám lehet.");
            return;
        }

        modositando.setTitle(cim);
        modositando.setOn_display(kiallitvaE);
        modositando.setYear(ev);

        try {
            Festmeny modositott = MuzeumApi.festmenyModositasa(modositando);
            if (modositott != null) {
                alertWait("Sikeres módosítás");
                this.stage.close();
            } else {
                alert("Sikertelen módosítás");
            }
        } catch (IOException e) {
            hibaKiir(e);
        }
    }
}
