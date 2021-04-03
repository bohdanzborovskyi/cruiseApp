package model;

import java.util.Date;

/**
 *  Entity class for ships.
 */


public class Ship 
{
	private String shipID;
	private int capacity;
	private String route;
	private int countPort;
	private String duration;
	private String staff;
	private String type;
	private int price;
	private String services;	
	private Date departure;	
	private int count;

	public Ship(String shipID, int capacity, String route, int countPort, String duration, String staff, String type,
			int price, String services, Date departure, int count) {
		super();
		this.shipID = shipID;
		this.capacity = capacity;
		this.route = route;
		this.countPort = countPort;
		this.duration = duration;
		this.staff = staff;
		this.type = type;
		this.price = price;
		this.services = services;
		this.departure = departure;
		this.count = count;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Ship() {}
	
	public Ship(String shipID, int capacity, String route, int countPort, String duration, String staff, String type,
			int price, String services, Date departure) {
		super();
		this.shipID = shipID;
		this.capacity = capacity;
		this.route = route;
		this.countPort = countPort;
		this.duration = duration;
		this.staff = staff;
		this.type = type;
		this.price = price;
		this.services = services;
		this.departure = departure;
	}
	
	public Date getDeparture() {
		return departure;
	}

	public void setDeparture(Date departure) {
		this.departure = departure;
	}
	
	public String getShipID() {
		return shipID;
	}
	public int getCapacity() {
		return capacity;
	}
	public String getRoute() {
		return route;
	}
	public int getCountPort() {
		return countPort;
	}
	public String getDuration() {
		return duration;
	}
	public String getStaff() {
		return staff;
	}
	public String getType() {
		return type;
	}
	public int getPrice() {
		return price;
	}
	public String getServices() {
		return services;
	}
	public void setShipID(String shipID) {
		this.shipID = shipID;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public void setRoute(String route) {
		this.route = route;
	}
	public void setCountPort(int countPort) {
		this.countPort = countPort;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public void setStaff(String staff) {
		this.staff = staff;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public void setServices(String services) {
		this.services = services;
	}
	

}
