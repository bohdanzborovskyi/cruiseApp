package model;

/**
 *  Entity class for relation ships-excursions.
 */


public class ShipExcursion 
{
	private String shipID;
	private String excursionID;
	
	
	public ShipExcursion() 
	{
	
	}


	public ShipExcursion(String shipID, String excursionID) {
		super();
		this.shipID = shipID;
		this.excursionID = excursionID;
	}


	public String getShipID() {
		return shipID;
	}


	public String getExcursionID() {
		return excursionID;
	}


	public void setShipID(String shipID) {
		this.shipID = shipID;
	}


	public void setExcursionID(String excursionID) {
		this.excursionID = excursionID;
	}

}
