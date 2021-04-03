package DAO;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;

import model.Excursion;
import model.User;
import service.DBConnection;

/**
 *  DAO class for working with Excursions database.
 */

public class DAOExcursion implements DAOcommand<Excursion>
{
	private Connection connection = null;	
	
	public DAOExcursion() 
	{
		connection = DBConnection.getConnection();
		
	}
	
	@Override
	public void add(Excursion t, String locale) throws SQLException 
	{
		if(locale.equalsIgnoreCase("ua"))
		{
		try 
		{
			PreparedStatement preparedStatement = connection.prepareStatement("insert into excursionua (excursionID,city,description,price) values (?,?,?,?)");
			connection.setAutoCommit(false);
			preparedStatement.setString(1, t.getExcursionID());
			preparedStatement.setString(2, t.getCity());
			preparedStatement.setString(3, t.getDescription());
			preparedStatement.setInt(4, t.getPrice());					
			preparedStatement.executeUpdate();
			connection.commit();
		}catch (SQLException e)
		{
			e.printStackTrace();
			connection.rollback();
		}
		}
		else if(locale.equalsIgnoreCase("en"))
		{
		try 
		{
			PreparedStatement preparedStatement = connection.prepareStatement("insert into excursionen (excursionID,city,description,price) values (?,?,?,?)");
			connection.setAutoCommit(false);
			preparedStatement.setString(1, t.getExcursionID());
			preparedStatement.setString(2, t.getCity());
			preparedStatement.setString(3, t.getDescription());
			preparedStatement.setInt(4, t.getPrice());					
			preparedStatement.executeUpdate();
			connection.commit();
		}catch (SQLException e)
		{
			e.printStackTrace();
			connection.rollback();
		}
		}
	}

	@Override
	public void delete(String excursionID) throws SQLException 
	{
		try 
		{
			PreparedStatement preparedStatement = connection.prepareStatement("delete from excursionua where excursionID=?");
			connection.setAutoCommit(false);
			preparedStatement.setString(1,excursionID);
			preparedStatement.executeUpdate();			
			connection.commit();
		}catch(SQLException e) 
		{
			e.printStackTrace();
			connection.rollback();
		}
		try 
		{
			PreparedStatement preparedStatement = connection.prepareStatement("delete from excursionen where excursionID=?");
			connection.setAutoCommit(false);
			preparedStatement.setString(1,excursionID);
			preparedStatement.executeUpdate();	
			connection.commit();
		}catch(SQLException e) 
		{
			e.printStackTrace();
			connection.rollback();
		}
	}

	@Override
	public void update(Excursion t, String locale) throws SQLException 
	{
		if(locale.equalsIgnoreCase("ua"))
		{
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("update excursionua set excursionID=?, city=?, description=?, price=? where excursionID=?");
			connection.setAutoCommit(false);
			preparedStatement.setString(1, t.getExcursionID());
			preparedStatement.setString(2, t.getCity());
			preparedStatement.setString(3, t.getDescription());
			preparedStatement.setInt(4, t.getPrice());
			preparedStatement.setString(5, t.getExcursionID());					
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) 
		{			
			e.printStackTrace();
			connection.rollback();
		}
		}
		else if(locale.equalsIgnoreCase("en"))
		{
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("update excursionen set excursionID=?, city=?, description=?, price=? where excursionID=?");
			connection.setAutoCommit(false);
			preparedStatement.setString(1, t.getExcursionID());
			preparedStatement.setString(2, t.getCity());
			preparedStatement.setString(3, t.getDescription());
			preparedStatement.setInt(4, t.getPrice());
			preparedStatement.setString(5, t.getExcursionID());					
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) 
		{			
			e.printStackTrace();
			connection.rollback();
		}
		}
	}
	
	public CopyOnWriteArrayList<Excursion> getAllByPage(String locale, int page) 
	{		
		CopyOnWriteArrayList<Excursion> excursionsUA = new CopyOnWriteArrayList<Excursion>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from excursionua limit 5 offset " + (page-1)*5);
            while (rs.next()) {
                Excursion excUA = this.createEntity();
                excUA.setExcursionID(rs.getString("excursionID"));
                excUA.setCity(rs.getString("city"));
                excUA.setDescription(rs.getString("description"));
                excUA.setPrice(rs.getInt("price"));
                excursionsUA.add(excUA);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       
		
        CopyOnWriteArrayList<Excursion> excursionsEN = new CopyOnWriteArrayList<Excursion>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from excursionen limit 5 offset " + (page-1)*5);
            while (rs.next()) {
                Excursion excEN = this.createEntity();
                excEN.setExcursionID(rs.getString("excursionID"));
                excEN.setCity(rs.getString("city"));
                excEN.setDescription(rs.getString("description"));
                excEN.setPrice(rs.getInt("price"));
                excursionsEN.add(excEN);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(locale.equalsIgnoreCase("ua"))
        {
        	return excursionsUA;
        }else 
        {
        	return excursionsEN;
        }
        
	}

	@Override
	public Excursion getByID(String id, String locale)
	{
		Excursion exc;
		Excursion excUA = this.createEntity();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from excursionua where excursionID=?");
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
            	excUA.setExcursionID(rs.getString("excursionID"));
            	excUA.setCity(rs.getString("city"));
            	excUA.setDescription(rs.getString("description"));
            	excUA.setPrice(rs.getInt("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Excursion excEN = this.createEntity();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from excursionen where excursionID=?");
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
            	excEN.setExcursionID(rs.getString("excursionID"));
            	excEN.setCity(rs.getString("city"));
            	excEN.setDescription(rs.getString("description"));
            	excEN.setPrice(rs.getInt("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(locale.equalsIgnoreCase("ua"))
        {
        	exc = excUA;
        }else if(locale.equalsIgnoreCase("en")) 
        {
        	exc = excEN;
        }else 
        {
        	exc = excEN;
        }	  
        return exc;
	}

	public CopyOnWriteArrayList<Excursion> getAllUser(String locale, String login) 
	{		
		CopyOnWriteArrayList<Excursion> excursionsUA = new CopyOnWriteArrayList<Excursion>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from excursionua join user_excursions on excursionua.excursionID=user_excursions.excursionID where login=\"" + login + "\"");
            while (rs.next()) {
                Excursion excUA = this.createEntity();
                excUA.setExcursionID(rs.getString("excursionID"));
                excUA.setCity(rs.getString("city"));
                excUA.setDescription(rs.getString("description"));
                excUA.setPrice(rs.getInt("price"));
                excUA.setCount(rs.getInt("count"));
                excursionsUA.add(excUA);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       
		
        CopyOnWriteArrayList<Excursion> excursionsEN = new CopyOnWriteArrayList<Excursion>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from excursionen join  user_excursions on excursionen.excursionID=user_excursions.excursionID where login=\"" + login + "\"");
            while (rs.next()) {
                Excursion excEN = this.createEntity();
                excEN.setExcursionID(rs.getString("excursionID"));
                excEN.setCity(rs.getString("city"));
                excEN.setDescription(rs.getString("description"));
                excEN.setPrice(rs.getInt("price"));
                excEN.setCount(rs.getInt("count"));
                excursionsEN.add(excEN);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(locale.equalsIgnoreCase("ua"))
        {
        	return excursionsUA;
        }else 
        {
        	return excursionsEN;
        }
        
	}

	@Override
	public CopyOnWriteArrayList<Excursion> getAll(String locale) 
	{
		CopyOnWriteArrayList<Excursion> excursionsUA = new CopyOnWriteArrayList<Excursion>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from excursionua");
            while (rs.next()) {
                Excursion excUA = this.createEntity();
                excUA.setExcursionID(rs.getString("excursionID"));
                excUA.setCity(rs.getString("city"));
                excUA.setDescription(rs.getString("description"));
                excUA.setPrice(rs.getInt("price"));
                excursionsUA.add(excUA);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       
		
        CopyOnWriteArrayList<Excursion> excursionsEN = new CopyOnWriteArrayList<Excursion>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from excursionen");
            while (rs.next()) {
                Excursion excEN = this.createEntity();
                excEN.setExcursionID(rs.getString("excursionID"));
                excEN.setCity(rs.getString("city"));
                excEN.setDescription(rs.getString("description"));
                excEN.setPrice(rs.getInt("price"));
                excursionsEN.add(excEN);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(locale.equalsIgnoreCase("ua"))
        {
        	return excursionsUA;
        }else 
        {
        	return excursionsEN;
        }
	}
	
	public CopyOnWriteArrayList<Excursion> getAllAvailable(String locale, String id) 
	{		
		CopyOnWriteArrayList<Excursion> excursionsUA = new CopyOnWriteArrayList<Excursion>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from excursionua join ship_excursion on excursionua.excursionID=ship_excursion.excursionID where shipID=\"" + id + "\"");
            while (rs.next()) {
                Excursion excUA = this.createEntity();
                excUA.setExcursionID(rs.getString("excursionID"));
                excUA.setCity(rs.getString("city"));
                excUA.setDescription(rs.getString("description"));
                excUA.setPrice(rs.getInt("price"));               
                excursionsUA.add(excUA);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       
		
        CopyOnWriteArrayList<Excursion> excursionsEN = new CopyOnWriteArrayList<Excursion>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from excursionen join  ship_excursion on excursionen.excursionID=ship_excursion.excursionID where shipID=\"" + id + "\"");
            while (rs.next()) {
                Excursion excEN = this.createEntity();
                excEN.setExcursionID(rs.getString("excursionID"));
                excEN.setCity(rs.getString("city"));
                excEN.setDescription(rs.getString("description"));
                excEN.setPrice(rs.getInt("price"));                
                excursionsEN.add(excEN);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(locale.equalsIgnoreCase("ua"))
        {
        	return excursionsUA;
        }else 
        {
        	return excursionsEN;
        }
        
	}

	@Override
	public Excursion createEntity() 
	{
		return new Excursion();		
	}
	
}
