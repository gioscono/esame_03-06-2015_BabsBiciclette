package it.polito.tdp.babs.model;

import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import it.polito.tdp.babs.model.Evento.EventType;

public class Simulazione {
	
	private Map<Station, Integer> mappaBici;
	private PriorityQueue<Evento> queue;
	private Map<Integer, Station> mappaStazioni;
	private int numeroPickMancati;
	private int numeroDropMancati;
	
	public Simulazione(Map<Station, Integer> mappaBici, Map<Integer, Station> mappaStazioni) {
		
		this.mappaBici = mappaBici;
		this.mappaStazioni = mappaStazioni;
		this.queue = new PriorityQueue<>();
		numeroPickMancati = 0;
		numeroDropMancati = 0;
	}

	public void inizializzaSimulazione(List<Trip> trips) {

		//aggiungo nella coda tutti i possibili eventi di PICK
		for(Trip t : trips){
			queue.add(new Evento(t.getStartDate(), EventType.PICK,t));
		}
		System.out.println(queue.size());
		
	}

	public Statistiche run() {

		while(!queue.isEmpty()){
			Evento e = queue.poll();
			
			switch(e.getType()){
			
			case PICK:
				int bici = mappaBici.get(mappaStazioni.get(e.getTrip().getStartStationID()));
				if(bici>0){
					//ci sono bici disponibili, ne prelevo una
					bici = bici -1;
					mappaBici.put(mappaStazioni.get(e.getTrip().getStartStationID()), bici);
					
					//genero il futuro DROP
					queue.add(new Evento(e.getTrip().getEndDate(), EventType.DROP, e.getTrip()));
					
				}else{
					numeroPickMancati ++;
				}
				break;
			case DROP:
				int capMax = mappaStazioni.get(e.getTrip().getEndStationID()).getDockCount();
				System.out.println();
				if(mappaBici.get(mappaStazioni.get(e.getTrip().getEndStationID()))>=capMax){
					numeroDropMancati ++;
				}else{
					int bicis = mappaBici.get(mappaStazioni.get(e.getTrip().getEndStationID()));
					bicis++;
					mappaBici.put(mappaStazioni.get(e.getTrip().getEndStationID()), bicis);
				}
				break;
			}
		}
		Statistiche s = new Statistiche(numeroPickMancati, numeroDropMancati);
		
		return s;
		
	}
	
	

}
