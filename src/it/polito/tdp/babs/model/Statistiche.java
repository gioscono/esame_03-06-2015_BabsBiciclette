package it.polito.tdp.babs.model;

public class Statistiche {
	
	private int numPick;
	private int numDrop;
	
	public Statistiche(int numeroPickMancati, int numeroDropMancati) {
		this.numPick = numeroPickMancati;
		this.numDrop = numeroDropMancati;
		
	}

	public int getNumPick() {
		return numPick;
	}

	public int getNumDrop() {
		return numDrop;
	}
	
	
	
	
	

}
