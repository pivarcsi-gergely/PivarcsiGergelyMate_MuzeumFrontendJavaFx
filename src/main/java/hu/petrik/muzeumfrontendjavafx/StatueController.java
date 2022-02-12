package hu.petrik.muzeumfrontendjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class StatueController {

    @FXML private Button szoborTorolBtn;
    @FXML private TableColumn colSzoborMag;
    @FXML private TableColumn colFestmnyKiallitott;
    @FXML private Button festmenyEditBtn;
    @FXML private TableColumn colSzoborTulaj;
    @FXML private Button festmenyTorolBtn;
    @FXML private TableColumn colSzoborAr;
    @FXML private Button festmenyUjBtn;
    @FXML private TableColumn colFestmenyCim;
    @FXML private TableView szoborTable;
    @FXML private TableView festmenyTable;
    @FXML private Button szoborEditBtn;
    @FXML private TableColumn colfestmenyEv;
    @FXML private Button szoborUjBtn;

    @FXML
    public void onEditSzoborClicked(ActionEvent actionEvent) {
    }

    @FXML
    public void onUjSzoborClicked(ActionEvent actionEvent) {
    }

    @FXML
    public void onSzoborTorolClicked(ActionEvent actionEvent) {
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