package model;

/**
 *  Entity class for relation users-ships.
 */


public class UserShip 
{
	private String login;
	private String shipID;
	private int count;
	
	public UserShip(String login, String shipID, int count) {
		super();
		this.login = login;
		this.shipID = shipID;
		this.count = count;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	

	public String getLogin() {
		return login;
	}

	public String getShipID() {
		return shipID;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setShipID(String shipID) {
		this.shipID = shipID;
	}

	public UserShip() 
	{
	
	}

}
