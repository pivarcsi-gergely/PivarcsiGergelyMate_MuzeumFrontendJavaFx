package hu.petrik.muzeumfrontendjavafx;

import hu.petrik.muzeumfrontendjavafx.classes.Festmeny;
import hu.petrik.muzeumfrontendjavafx.classes.Szobor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MuzeumController {

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
    @FXML private TableColumn<Festmeny, String> colFestmnyKiallitott;

    @FXML
    public void onEditSzoborClicked(ActionEvent actionEvent) {
    }

    @FXML
    public void onUjSzoborClicked(ActionEvent actionEvent) {
    }

    @FXML
    public void onTorolSzoborClicked(ActionEvent actionEvent) {
    }

    @FXML
    public void onFestmenyTorolClicked(ActionEvent actionEvent) {
    }

    @FXML
    public void onUjFestmenyClicked(ActionEvent actionEvent) {
    }

    @FXML
    public void onEditFestmenyClicked(ActionEvent actionEvent) {
    }
}