package it.polito.tdp.babs.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Evento implements Comparable<Evento>{
	
	public enum EventType{
		PICK,
		DROP;
	}
	private LocalDateTime data;
	private EventType type;
	private Trip trip;
	public Evento(LocalDateTime localDateTime, EventType type, Trip trip) {
		super();
		this.data = localDateTime;
		this.type = type;
		this.trip = trip;
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public EventType getType() {
		return type;
	}
	public void setType(EventType type) {
		this.type = type;
	}
	public Trip getTrip() {
		return trip;
	}
	public void setTrip(Trip trip) {
		this.trip = trip;
	}
	@Override
	public int compareTo(Evento o) {
		// TODO Auto-generated method stub
		return this.getData().compareTo(o.getData());
	}
	
	

}
