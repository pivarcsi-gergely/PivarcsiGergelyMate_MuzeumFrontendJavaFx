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
    }

    @FXML
    public void onUjSzoborClicked(ActionEvent actionEvent) {
        try {
            Controller hozzaadas = ujAblak("hozzaad-szobor-view.fxml", "Szobor hozzáadása", 320, 400);
            hozzaadas.getStage().setOnCloseRequest(event -> szoborFeltolt());
            hozzaadas.getStage().show();
        } catch (Exception e) {
            hibaKiir(e);
        }
    }

    @FXML
    public void onTorolSzoborClicked(ActionEvent actionEvent) {
    }

    @FXML
    public void onFestmenyTorolClicked(ActionEvent actionEvent) {
    }

    @FXML
    public void onUjFestmenyClicked(ActionEvent actionEvent) {
        try {
            Controller hozzaadas = ujAblak("hozzaad-festmeny-view.fxml", "Festmény hozzáadása", 320, 400);
            hozzaadas.getStage().setOnCloseRequest(event -> festmenyFeltolt());
            hozzaadas.getStage().show();
        } catch (Exception e) {
            hibaKiir(e);
        }
    }

    @FXML
    public void onEditFestmenyClicked(ActionEvent actionEvent) {
    }

    private void festmenyFeltolt() {
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