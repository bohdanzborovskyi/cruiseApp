package model;

/**
 *  Entity class for relation users-excursions.
 */


public class UserExcursion 
{
	private String login;
	private String excursionID;
	private int count;
	
	public UserExcursion(String login, String excursionID, int count) {
		super();
		this.login = login;
		this.excursionID = excursionID;
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
	public String getExcursionID() {
		return excursionID;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public void setExcursionID(String excursionID) {
		this.excursionID = excursionID;
	}
	public UserExcursion() 
	{
		
	}

}
