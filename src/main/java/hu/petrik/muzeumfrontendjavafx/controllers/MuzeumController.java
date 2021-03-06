package hu.petrik.muzeumfrontendjavafx.controllers;

import hu.petrik.muzeumfrontendjavafx.Controller;
import hu.petrik.muzeumfrontendjavafx.api.MuzeumApi;
import hu.petrik.muzeumfrontendjavafx.classes.Festmeny;
import hu.petrik.muzeumfrontendjavafx.classes.Szobor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

public class MuzeumController extends Controller {

    @FXML private Button szoborEditBtn;
    @FXML private Button szoborUjBtn;
    @FXML private Button szoborTorolBtn;
    @FXML private TableView<Szobor> szoborTable;
    @FXML private TableColumn<Szobor, String> colSzoborTulaj;
    @FXML private TableColumn<Szobor, Integer> colSzoborMag;
    @FXML private TableColumn<Szobor, Integer> colSzoborAr;
    @FXML private Button festmenyEditBtn;
    @FXML private Button festmenyUjBtn;
    @FXML private Button festmenyTorolBtn;
    @FXML private TableView<Festmeny> festmenyTable;
    @FXML private TableColumn<Festmeny, String> colFestmenyCim;
    @FXML private TableColumn<Festmeny, Integer> colfestmenyEv;
    @FXML private TableColumn<Festmeny, String> colFestmenyKiallitott;

    public void initialize() {
        colSzoborTulaj.setCellValueFactory(new PropertyValueFactory<Szobor, String>("person"));
        colSzoborMag.setCellValueFactory(new PropertyValueFactory<>("height"));
        colSzoborAr.setCellValueFactory(new PropertyValueFactory<>("price"));
        szoborFeltolt();

        colFestmenyCim.setCellValueFactory(new PropertyValueFactory<>("title"));
        colfestmenyEv.setCellValueFactory(new PropertyValueFactory<>("year"));
        colFestmenyKiallitott.setCellValueFactory(new PropertyValueFactory<>("on_display"));
        festmenyFeltolt();
    }

    @FXML
    public void onEditSzoborClicked(ActionEvent actionEvent) {
        int selectedIndex = szoborTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            alert("A m??dos??t??shoz el??bb v??lasszon ki egy szobrot a t??bl??zatb??l!");
            return;
        }
        Szobor modositandoSzobor = szoborTable.getSelectionModel().getSelectedItem();
        try {
            SzoborEditController szoborModositas = (SzoborEditController) ujAblak("szobor-edit-view.fxml", "Szobor m??dos??t??sa", 320, 400);
            szoborModositas.setModositando(modositandoSzobor);
            szoborModositas.getStage().setResizable(false);
            szoborModositas.getStage().setOnHiding(event -> festmenyTable.refresh());
            szoborModositas.getStage().setOnCloseRequest(event -> festmenyFeltolt());
            szoborModositas.getStage().show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onUjSzoborClicked(ActionEvent actionEvent) {
        try {
            Controller hozzaadas = ujAblak("hozzaad-szobor-view.fxml", "Szobor hozz??ad??sa", 320, 400);
            hozzaadas.getStage().setResizable(false);
            hozzaadas.getStage().setOnCloseRequest(event -> szoborFeltolt());
            hozzaadas.getStage().show();
        } catch (Exception e) {
            hibaKiir(e);
        }
    }

    @FXML
    public void onTorolSzoborClicked(ActionEvent actionEvent) {
        int selectedIndex = szoborTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            alert("A t??rl??shez el??bb v??lasszon ki egy szobrot a t??bl??zatb??l!");
            return;
        }
        Szobor torlendoSzobor = szoborTable.getSelectionModel().getSelectedItem();
        if (!confirm("Biztos, hogy t??r??lni szeretn?? az al??bbi sz??m?? szobrot: " + torlendoSzobor.getId() + "?")) {
            return;
        } else {
            try {
                boolean sikeres = MuzeumApi.szoborTorlese(torlendoSzobor.getId());
                alert(sikeres ? "Sikeres t??rl??s" : "Sikertelen t??rl??s");
                szoborFeltolt();
            } catch (IOException e) {
                hibaKiir(e);
            }
        }
    }

    @FXML
    public void onEditFestmenyClicked(ActionEvent actionEvent) {
        int selectedIndex = festmenyTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            alert("A m??dos??t??shoz el??bb v??lasszon ki egy festm??nyt a t??bl??zatb??l!");
            return;
        }
        Festmeny modositandoFestmeny = festmenyTable.getSelectionModel().getSelectedItem();
        try {
            FestmenyEditController festmenyModositas = (FestmenyEditController) ujAblak("festmeny-edit-view.fxml", "Festm??ny m??dos??t??sa", 320, 400);
            festmenyModositas.setModositando(modositandoFestmeny);
            festmenyModositas.getStage().setResizable(false);
            festmenyModositas.getStage().setOnHiding(event -> festmenyTable.refresh());
            festmenyModositas.getStage().setOnCloseRequest(event -> festmenyFeltolt());
            festmenyModositas.getStage().show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onUjFestmenyClicked(ActionEvent actionEvent) {
        try {
            Controller hozzaadas = ujAblak("hozzaad-festmeny-view.fxml", "Festm??ny hozz??ad??sa", 320, 400);
            hozzaadas.getStage().setResizable(false);
            hozzaadas.getStage().setOnCloseRequest(event -> festmenyFeltolt());
            hozzaadas.getStage().show();
        } catch (Exception e) {
            hibaKiir(e);
        }
    }

    @FXML
    public void onFestmenyTorolClicked(ActionEvent actionEvent) {
        int selectedIndex = festmenyTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            alert("A t??rl??shez el??bb v??lasszon ki egy festm??nyt a t??bl??zatb??l!");
            return;
        }
        Festmeny torlendoFestmeny = festmenyTable.getSelectionModel().getSelectedItem();
        if (!confirm("Biztos, hogy t??r??lni szeretn?? az al??bbi c??m?? festm??nyt: " + torlendoFestmeny.getTitle() + "?")) {
            return;
        } else {
            try {
                boolean sikeres = MuzeumApi.festmenyTorlese(torlendoFestmeny.getId());
                alert(sikeres ? "Sikeres t??rl??s" : "Sikertelen t??rl??s");
                festmenyFeltolt();
            } catch (IOException e) {
                hibaKiir(e);
            }
        }
    }

    public void festmenyFeltolt() {
        try {
            List<Festmeny> festmenyLista = MuzeumApi.getFestmenyek();
            festmenyTable.getItems().clear();
            for (Festmeny festmeny: festmenyLista) {
                festmenyTable.getItems().add(festmeny);
            }
        }
        catch (IOException e){
            hibaKiir(e);
        }
    }

    private void szoborFeltolt() {
        try {
            List<Szobor> szoborLista = MuzeumApi.getSzobrok();
            szoborTable.getItems().clear();
            for (Szobor szobor: szoborLista) {
                szoborTable.getItems().add(szobor);
            }
        }
        catch (IOException e){
            hibaKiir(e);
        }
    }
}