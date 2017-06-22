package it.polito.tdp.babs;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.babs.model.Model;
import it.polito.tdp.babs.model.Statistiche;
import it.polito.tdp.babs.model.StazPickDrop;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;

public class BabsController {

	private Model model;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker pickData;

    @FXML
    private Slider sliderK;

    @FXML
    private TextArea txtResult;

    @FXML
    void doContaTrip(ActionEvent event) {
    	
    	LocalDate data = pickData.getValue();
    	//System.out.println(data);
    	if(data==null){
    		txtResult.appendText("Errore: selezionare una data.\n");
    		return;
    	}
    	List<StazPickDrop> ris = model.getAllPickDrop(data);
    	//System.out.println(ris.size()+"\n");
    	for(StazPickDrop s : ris){
    		//System.out.println(s.getStation()+": "+s.getPicks()+"-"+s.getDrops()+"\n");
    		txtResult.appendText("Stazione s: "+s.getStation().getStationID()+"\n");
    		if(s.getPicks()==0 && s.getDrops()==0){
    			txtResult.appendText("Errore: in questa stazione non sono presenti trip.\n");
    		}else{
    			txtResult.appendText("Numero di picks: "+s.getPicks()+"\nNumero di drops: "+s.getDrops()+"\n");
    		}
    	}

    }

    @FXML
    void doSimula(ActionEvent event) {

    	LocalDate data = pickData.getValue();
    	if(data.getDayOfWeek() == DayOfWeek.SUNDAY ||data.getDayOfWeek() == DayOfWeek.SATURDAY){
    		txtResult.appendText("Errore: selezionare un giorno feriale.\n");
    		return;
    	}
    	double k = sliderK.getValue()/100.00;
    	Statistiche s = model.avviaSimulazione(k, data);
    	txtResult.appendText("Numero di pick mancati: "+s.getNumPick()+"\nNumero di drop mancati: "+s.getNumDrop()+"\n");
    	
    }

    @FXML
    void initialize() {
        assert pickData != null : "fx:id=\"pickData\" was not injected: check your FXML file 'Babs.fxml'.";
        assert sliderK != null : "fx:id=\"sliderK\" was not injected: check your FXML file 'Babs.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Babs.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		
	}
}
