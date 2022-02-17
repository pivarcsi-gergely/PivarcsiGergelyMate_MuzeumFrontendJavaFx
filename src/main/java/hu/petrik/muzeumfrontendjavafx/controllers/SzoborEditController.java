package hu.petrik.muzeumfrontendjavafx.controllers;

import hu.petrik.muzeumfrontendjavafx.Controller;
import hu.petrik.muzeumfrontendjavafx.api.MuzeumApi;
import hu.petrik.muzeumfrontendjavafx.classes.Festmeny;
import hu.petrik.muzeumfrontendjavafx.classes.Szobor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SzoborEditController extends Controller {
    @FXML
    private Spinner<Integer> inputMagassag;
    @FXML
    private TextField inputEmber;
    @FXML
    private Spinner<Integer> inputAr;

    private Szobor modositando;

    public Szobor getModositando() {
        return modositando;
    }

    public void setModositando(Szobor modositando) {
        this.modositando = modositando;
        ertekekBeallitasa();
    }

    private void ertekekBeallitasa() {
        inputEmber.setText(modositando.getPerson());
        inputMagassag.getValueFactory().setValue(modositando.getHeight());
        inputAr.getValueFactory().setValue(modositando.getPrice());
    }

    @FXML
    public void onModositButtonClick(ActionEvent actionEvent) {
        String tulaj = inputEmber.getText().trim();
        int magassag;
        int ar;
        if (tulaj.isEmpty()) {
            alert("Tulajdonos megadása kötelező.");
            return;
        }
        try {
            magassag = inputMagassag.getValue();
        } catch (NullPointerException e) {
            alert("Magasság kiválasztása kötelező.");
            return;
        } catch (Exception e) {
            alert("A magasság csak 1 és 200 cm közötti érték lehet.");
            return;
        }
        try {
            ar = inputAr.getValue();
        } catch (NullPointerException e) {
            alert("Ár kiválasztása kötelező.");
            return;
        } catch (Exception e) {
            alert("Az ár csak 100 és 5000 Ft közötti pénzérték lehet.");
            return;
        }

        modositando.setPerson(tulaj);
        modositando.setHeight(magassag);
        modositando.setPrice(ar);

        try {
            Szobor modositott = MuzeumApi.szoborModositasa(modositando);
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
