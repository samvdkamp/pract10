package hotel.userinterface;

import hotel.model.Boeking;
import hotel.model.Hotel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;

public class HotelOverzichtController {
    @FXML private Label hotelnaamLabel;
    @FXML private ListView boekingenListView;
    @FXML private DatePicker overzichtDatePicker;

    private Hotel hotel = Hotel.getHotel();

    public void initialize() {
        hotelnaamLabel.setText("Boekingen hotel " + hotel.getNaam());
        overzichtDatePicker.setValue(LocalDate.now());
        toonBoekingen();
    }

    public void toonVorigeDag(ActionEvent actionEvent) {
        LocalDate dagEerder = overzichtDatePicker.getValue().minusDays(1);
        overzichtDatePicker.setValue(dagEerder);
    }

    public void toonVolgendeDag(ActionEvent actionEvent) {
        LocalDate dagLater = overzichtDatePicker.getValue().plusDays(1);
        overzichtDatePicker.setValue(dagLater);
    }

    public void nieuweBoeking(ActionEvent actionEvent) throws Exception {
        System.out.println("nieuweBoeking() is nog niet geïmplementeerd!");

        FXMLLoader ldr = new FXMLLoader(getClass().getResource("Boekingen.fxml"));
        Parent root = ldr.load();
        Stage nieuwScherm = new Stage();
        nieuwScherm.setScene(new Scene(root));
        nieuwScherm.initModality(Modality.APPLICATION_MODAL);
        nieuwScherm.showAndWait();
        initialize();
    }

    public void toonBoekingen() {
        ObservableList<String> boekingen = FXCollections.observableArrayList();
        LocalDate localDate = overzichtDatePicker.getValue();

        for(Boeking boeking : hotel.getBoekingen()){
            if(localDate.equals(boeking.getAankomstDatum())){
                boekingen.add("Van: " + boeking.getAankomstDatum() + " ,tot: " + boeking.getVertrekDatum() + " , kamer: " + boeking.getKamer().getKamerNummer() + " , naam: " + boeking.getBoeker().getNaam() +"\n" );
            }
        }


        boekingenListView.setItems(boekingen);
    }
}