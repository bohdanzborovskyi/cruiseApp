package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import model.Excursion;
import model.UserExcursion;
import model.UserShip;
import service.DBConnection;

/**
 *  DAO class for working with Users-Ships database.
 */

public class DAOUShip implements DAOcommand<UserShip>
{

	private Connection connection = null;
	
	public DAOUShip() 
	{
		connection = DBConnection.getConnection();
	}

	@Override
	public void add(UserShip t, String locale) throws SQLException 
	{
			try 
			{
				PreparedStatement pre = connection.prepareStatement("insert into user_ships (login, shipID,count) values (?,?,?)");
				connection.setAutoCommit(false);
				pre.setString(1, t.getLogin());
				pre.setString(2, t.getShipID());
				pre.setInt(3, t.getCount());
				pre.executeUpdate();
				connection.commit();
			}catch (SQLException e)
			{
				connection.rollback();
				throw e;				
			}
	}

	
	public void delete(String shipID, String login) throws SQLException 
	{
		UserShip userShip = this.getByID(login,shipID);
		if(userShip.getCount()>1) 
		{
			userShip.setCount(userShip.getCount()-1);
			this.update(userShip, "en");
		}else 
		{
		try 
		{
			PreparedStatement pre = connection.prepareStatement("delete from user_ships where login=? and shipID=?");
			connection.setAutoCommit(false);
			pre.setString(1, login);
			pre.setString(2, shipID);
			pre.executeUpdate();
			connection.commit();
		}catch (SQLException e)
		{
			e.printStackTrace();
			connection.rollback();
		}	
		}
	}

	@Override
	public void update(UserShip t, String locale) throws SQLException 
	{
		try 
		{
			PreparedStatement pre = connection.prepareStatement("update user_ships set login=?, shipID=?, count=?  where login=? and shipID=?");
			connection.setAutoCommit(false);
			pre.setString(1, t.getLogin());
			pre.setString(2, t.getShipID());
			pre.setInt(3, t.getCount());
			pre.setString(4, t.getLogin());
			pre.setString(5, t.getShipID());
			pre.executeUpdate();
			connection.commit();
		}catch (SQLException e)
		{
			e.printStackTrace();
			connection.rollback();
		}
	}

	@Override
	public CopyOnWriteArrayList<UserShip> getAll(String locale) 
	{
		CopyOnWriteArrayList<UserShip> userShip = new CopyOnWriteArrayList<UserShip>();
        try {
            Statement statement = connection.createStatement();            
            ResultSet rs = statement.executeQuery("select * from user_ships");
            while (rs.next()) {
                UserShip uShip = this.createEntity();
                uShip.setShipID(rs.getString("shipID"));
                uShip.setLogin(rs.getString("login"));
                uShip.setCount(rs.getShort("count"));
                userShip.add(uShip);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userShip;
	}

	@Override
	public UserShip getByID(String login, String shipID) 
	{		
		UserShip uShip = this.createEntity();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from user_ships where login=? and shipID=?");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, shipID);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
            	uShip.setShipID(rs.getString("shipID"));
            	uShip.setLogin(rs.getString("login"));
            	uShip.setCount(rs.getInt("count"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return uShip;
	}

	@Override
	public void delete(String ID) {
				
	}

	@Override
	public UserShip createEntity() 
	{
		return new UserShip();
	}

}

