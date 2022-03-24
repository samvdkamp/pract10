package hotel.userinterface;

import hotel.model.Hotel;
import hotel.model.KamerType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class BoekingenController {
    @FXML private ComboBox<KamerType> Kamertype;
    @FXML private TextField Naam;
    @FXML private TextField Adres;
    @FXML private DatePicker Aankomst;
    @FXML private DatePicker Vertrek;


    private Hotel hotel = Hotel.getHotel();

    public void initialize() {
        ObservableList<KamerType> kamerTypes = FXCollections.observableArrayList();
        kamerTypes.addAll(hotel.getKamerTypen());
        Kamertype.setItems(kamerTypes);
    }

    public void reset(){
        Naam.clear();
        Adres.clear();
        Aankomst.getEditor().clear();
        Vertrek.getEditor().clear();
        Kamertype.setValue(null);
    }

    public void boek() throws Exception {
        LocalDate localDate = LocalDate.now();
        LocalDate vertrekDate = Vertrek.getValue();
        LocalDate aankomstDate = Aankomst.getValue();
        if(!Naam.getText().isEmpty()&&!Adres.getText().isEmpty()&&Vertrek.getValue() != null&&Aankomst.getValue() !=null&&!Kamertype.getSelectionModel().isEmpty()){
            if(aankomstDate.isAfter(localDate)&&vertrekDate.isAfter(aankomstDate)) {
                    hotel.voegBoekingToe(Aankomst.getValue(), Vertrek.getValue(), Naam.getText(), Adres.getText(), Kamertype.getValue());

            }
            else{
                String melding = "De data is ongeldig";
                Alert alert = new Alert(Alert.AlertType.INFORMATION, melding);
                alert.show();
            }
        }
        else{
            String melding2 = "U heeft niet alles ingevuld!";
            Alert alert = new Alert(Alert.AlertType.INFORMATION, melding2);
            alert.show();
        }
    }


}
