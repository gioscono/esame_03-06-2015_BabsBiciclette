package it.polito.tdp.babs.model;

public class StazPickDrop implements Comparable<StazPickDrop>{
	
	private Station station;
	private int picks;
	private int drops;
	public StazPickDrop(Station station, int picks, int drops) {
		super();
		this.station = station;
		this.picks = picks;
		this.drops = drops;
	}
	public Station getStation() {
		return station;
	}
	public void setStation(Station station) {
		this.station = station;
	}
	public int getPicks() {
		return picks;
	}
	public void setPicks(int picks) {
		this.picks = picks;
	}
	public int getDrops() {
		return drops;
	}
	public void setDrops(int drops) {
		this.drops = drops;
	}
	@Override
	public int compareTo(StazPickDrop o) {
		
		return Double.compare(this.getStation().getLat(),o.getStation().getLat());
	}
	
	

}
