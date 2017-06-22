package it.polito.tdp.babs.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.babs.db.BabsDAO;

public class Model {
	
	private BabsDAO dao;
	private List<Station> stazioni;
	private Map<Integer, Station> mappaStazioni;
	
	
	
	public Model (){
		dao = new BabsDAO();
	}
	
	public List<Station> getAllStation(){
		
		if(stazioni == null){
			mappaStazioni = new HashMap<>();
			stazioni = dao.getAllStations();
			for(Station s : stazioni){
				mappaStazioni.put(s.getStationID(), s);			
			}
		}
		return stazioni;
	}
	
	public List<StazPickDrop> getAllPickDrop(LocalDate data){
		
		List<StazPickDrop> ris = new ArrayList<>();
		this.getAllStation();
		for(Station s : stazioni){
			int picks = dao.getNumberPicks(data, s.getStationID());
			int drops = dao.getNumberDrops(data, s.getStationID());
			ris.add(new StazPickDrop(s, picks, drops));
		}
		Collections.sort(ris);
		return ris;
	}

	public Statistiche avviaSimulazione(double k, LocalDate date) {

		this.getAllStation();
		//CREO UNA MAPPA DOVE TENGO CONTO IL NUMERO DI BICI IN OGNI STAZIONE
		Map<Station, Integer> mappaBici = new HashMap<>();
		for(Station s : stazioni){
			//ogni stazione contiene il k% delle sue postazioni bici 
			mappaBici.put(s, (int) (s.getDockCount()*k));
		}
		List<Trip> trips = new ArrayList<Trip>( dao.getAllTripsDate(date));
		
		Simulazione sim = new Simulazione(mappaBici, mappaStazioni);
		
		sim.inizializzaSimulazione(trips);
		
		return sim.run();
		
		
	}

}
