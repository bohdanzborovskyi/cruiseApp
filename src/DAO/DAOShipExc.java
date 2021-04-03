package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import model.ShipExcursion;
import model.UserExcursion;
import service.DBConnection;

/**
 *  DAO class for working with Ships-Excursions database.
 */

public class DAOShipExc implements DAOcommand<ShipExcursion>
{
	private Connection connection = null;

	public DAOShipExc() 
	{
		connection = DBConnection.getConnection();
	}

	@Override
	public void add(ShipExcursion t, String locale) throws SQLException
	{
		try 
		{
			PreparedStatement pre = connection.prepareStatement("insert into ship_excursion (shipID, excursionID) values (?,?)");
			connection.setAutoCommit(false);
			pre.setString(1, t.getShipID());
			pre.setString(2, t.getExcursionID());			
			pre.executeUpdate();
			connection.commit();
		}catch (SQLException e)
		{
			e.printStackTrace();
			connection.rollback();
		}		
	}

	
	public void delete(String excursionID, String shipID) throws SQLException 
	{
		try {
			PreparedStatement pre = connection.prepareStatement("delete from ship_excursion where shipID=? and excursionID=?");
			connection.setAutoCommit(false);
			pre.setString(1, shipID);
			pre.setString(2, excursionID);
			pre.executeUpdate();
			connection.commit();
		} catch (SQLException e) 
		{			
			e.printStackTrace();
			connection.rollback();
		}
		
		
	}

	@Override
	public void update(ShipExcursion t, String locale) {		
	}

	@Override
	public CopyOnWriteArrayList<ShipExcursion> getAll(String locale) 
	{
		CopyOnWriteArrayList<ShipExcursion> shipExc = new CopyOnWriteArrayList<ShipExcursion>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from ship_excursion");
            while (rs.next()) {
                ShipExcursion shExc = this.createEntity();
                shExc.setExcursionID(rs.getString("excursionID"));
                shExc.setShipID(rs.getString("shipID"));               
                shipExc.add(shExc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shipExc;
	}

	@Override
	public ShipExcursion getByID(String id, String locale) 
	{
		ShipExcursion shExc = this.createEntity();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from ship_excursion where shipID=?");
            preparedStatement.setString(1, id);           
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
            	shExc.setExcursionID(rs.getString("excursionID"));
            	shExc.setShipID(rs.getString("shipID"));            	
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shExc;
	}

	@Override
	public ShipExcursion createEntity() 
	{
		return new ShipExcursion();
	}

	@Override
	public void delete(String ID) throws SQLException {
		
	}

	public CopyOnWriteArrayList<ShipExcursion> getAllByPage(String locale, int page) 
	{
		CopyOnWriteArrayList<ShipExcursion> shipExc = new CopyOnWriteArrayList<ShipExcursion>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from ship_excursion limit 5 offset " + (page-1)*5);
            while (rs.next()) {
                ShipExcursion shExc = this.createEntity();
                shExc.setExcursionID(rs.getString("excursionID"));
                shExc.setShipID(rs.getString("shipID"));               
                shipExc.add(shExc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shipExc;
	}
	
}
