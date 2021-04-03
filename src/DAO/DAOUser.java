package DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import model.User;
import service.DBConnection;
import service.PropertyFactory;

/**
 *  DAO class for working with Users database.
 */


public class DAOUser implements DAOcommand<User>
{
	private Connection connection = null;
	public static File photo;
	private final String UPLOAD_DIRECTORY = "C:/Users/tempadmin2/git/github-bitbucket-gitlab-CruiseApp/CruiseApp/WebContent/images";
	
	public DAOUser() 
	{
		connection = DBConnection.getConnection();
	}

	@Override
	public void add(User user, String locale) throws SQLException 
	{
		if(locale.equals("ua"))
		{
		try 
		{
			PreparedStatement preparedStatement = connection.prepareStatement("insert into usersua (login,password,name,surname,telephon,role,cash,email,photo) values (?,?,?,?,?,?,?,?,?)");			
			connection.setAutoCommit(false);
			preparedStatement.setString(1, user.getLogin());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getName());
			preparedStatement.setString(4, user.getSurname());
			preparedStatement.setString(5, user.getTelefon());
			preparedStatement.setString(6, user.getRole());
			preparedStatement.setLong(7, user.getCash());
			preparedStatement.setString(8, user.getEmail());
			FileInputStream fis = new FileInputStream(user.getPhoto());
			preparedStatement.setBlob(9,fis);				
			preparedStatement.executeUpdate();
			connection.commit();
		}catch (SQLException | FileNotFoundException e)
		{
			e.printStackTrace();
			connection.rollback();
		}
		}else if(locale.equals("en"))
		{
		try 
		{
			PreparedStatement preparedStatement = connection.prepareStatement("insert into usersen (login,password,name,surname,telephon,role,cash,email,photo) values (?,?,?,?,?,?,?,?,?)");			
			connection.setAutoCommit(false);
			preparedStatement.setString(1, user.getLogin());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getName());
			preparedStatement.setString(4, user.getSurname());
			preparedStatement.setString(5, user.getTelefon());	
			preparedStatement.setString(6, user.getRole());
			preparedStatement.setLong(7, user.getCash());
			preparedStatement.setString(8, user.getEmail());		
			FileInputStream fis = new FileInputStream(user.getPhoto());			
			preparedStatement.setBlob(9,fis);			
			preparedStatement.executeUpdate();
			connection.commit();
		}catch (SQLException | FileNotFoundException e)
		{
			e.printStackTrace();
			connection.rollback();
		}
		}
	}

	@Override
	public void delete(String login) throws SQLException 
	{
		try 
		{
			PreparedStatement preparedStatement = connection.prepareStatement("delete from usersua where login=?");			
			connection.setAutoCommit(false);
			preparedStatement.setString(1,login);
			preparedStatement.executeUpdate();	
			connection.commit();
		}catch(SQLException e) 
		{ 
			e.printStackTrace();
			connection.rollback();
		}
		try 
		{
			PreparedStatement preparedStatement = connection.prepareStatement("delete from usersen where login=?");			
			connection.setAutoCommit(false);
			preparedStatement.setString(1,login);
			preparedStatement.executeUpdate();		
			connection.commit();
		}catch(SQLException e) 
		{
			e.printStackTrace();
			connection.rollback();
		}
		
	}

	@Override
	public void update(User user, String locale) throws SQLException 
	{
		if(locale.equals("ua"))
		{
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("update usersua set login=?, password=?, name=?, surname=?, telephon=?, role=?, cash=?, email=?, photo=? where login=?");			
			connection.setAutoCommit(false);
			preparedStatement.setString(1, user.getLogin());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getName());
			preparedStatement.setString(4, user.getSurname());
			preparedStatement.setString(5, user.getTelefon());
			preparedStatement.setString(6, user.getRole());
			preparedStatement.setLong(7, user.getCash());			
			preparedStatement.setString(8, user.getEmail());
			FileInputStream fis = new FileInputStream(user.getPhoto());
			Logger log = Logger.getLogger(DAOUser.class);
			log.info(user.getPhoto().getName());
			preparedStatement.setBlob(9, fis);
			preparedStatement.setString(10, user.getLogin());
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException | FileNotFoundException e) 
		{			
			e.printStackTrace();
			connection.rollback();
		} 
		}
		else if(locale.equals("en")) 
		{
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("update usersen set login=?, password=?, name=?, surname=?, telephon=?, role=?, cash=?, email=?, photo=? where login=?");
			connection.setAutoCommit(false);
			preparedStatement.setString(1, user.getLogin());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getName());
			preparedStatement.setString(4, user.getSurname());
			preparedStatement.setString(5, user.getTelefon());
			preparedStatement.setString(6, user.getRole());
			preparedStatement.setLong(7, user.getCash());
			preparedStatement.setString(8, user.getEmail());	
			FileInputStream fis = new FileInputStream(user.getPhoto());	
			Logger log = Logger.getLogger(DAOUser.class);
			log.info(user.getPhoto().getName());
			preparedStatement.setBlob(9,fis);
			preparedStatement.setString(10, user.getLogin());
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException | FileNotFoundException e) 
		{			
			e.printStackTrace();
			connection.rollback();
		}
		}
	}

	@Override
	public CopyOnWriteArrayList<User> getAll(String locale) throws IOException
	{			
		CopyOnWriteArrayList<User> usersUA = new CopyOnWriteArrayList<User>();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from usersua");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User userUA = this.createEntity();
                userUA.setLogin(rs.getString("login"));
                userUA.setPassword(rs.getString("password"));
                userUA.setName(rs.getString("name"));
                userUA.setSurname(rs.getString("surname"));  
                userUA.setTelefon(rs.getString("telephon"));
                userUA.setRole(rs.getString("role"));
                userUA.setCash(rs.getInt("cash"));                
                userUA.setPhoto(getFile(rs.getBlob("photo"),rs.getString("login")));
                usersUA.add(userUA);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
        CopyOnWriteArrayList<User> usersEN = new CopyOnWriteArrayList<User>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from usersen");
            while (rs.next()) {
                User userEN = this.createEntity();
                userEN.setLogin(rs.getString("login"));
                userEN.setPassword(rs.getString("password"));
                userEN.setName(rs.getString("name"));
                userEN.setSurname(rs.getString("surname"));  
                userEN.setTelefon(rs.getString("telephon"));
                userEN.setRole(rs.getString("role"));
                userEN.setCash(rs.getInt("cash"));
                userEN.setPhoto(getFile(rs.getBlob("photo"),rs.getString("login")));
                usersEN.add(userEN);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usersEN;
	}

	@Override
	public User getByID(String id, String locale) throws IOException 
	{		
		 User userUA = this.createEntity();
	        try {
	            PreparedStatement preparedStatement = connection.prepareStatement("select * from usersua where login=?");
	            preparedStatement.setString(1, id);
	            ResultSet rs = preparedStatement.executeQuery();

	            if (rs.next()) {
	            	userUA.setLogin(rs.getString("login"));
	                userUA.setPassword(rs.getString("password"));
	                userUA.setName(rs.getString("name"));
	                userUA.setSurname(rs.getString("surname"));
	                userUA.setTelefon(rs.getString("telephon"));
	                userUA.setRole(rs.getString("role"));
	                userUA.setCash(rs.getInt("cash"));
	                userUA.setEmail(rs.getString("email"));
	                userUA.setPhoto(getFile(rs.getBlob("photo"),id));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	     User userEN = this.createEntity();
	        try {
	            PreparedStatement preparedStatement = connection.prepareStatement("select * from usersen where login=?");
	            preparedStatement.setString(1, id);
	            ResultSet rs = preparedStatement.executeQuery();

	            if (rs.next()) {
	            	userEN.setLogin(rs.getString("login"));
	                userEN.setPassword(rs.getString("password"));
	                userEN.setName(rs.getString("name"));
	                userEN.setSurname(rs.getString("surname"));
	                userEN.setTelefon(rs.getString("telephon"));
	                userEN.setRole(rs.getString("role"));
	                userEN.setCash(rs.getInt("cash"));
	                userEN.setEmail(rs.getString("email"));
	                userEN.setPhoto(getFile(rs.getBlob("photo"),id));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        if(locale.equals("ua")) 
	        {
	        	 return userUA;
	        }else 
	        {
	        	return userEN;
	        }

	       
	}
	
	public File getPhoto(String id) throws IOException 
	{	
		photo = null;
		try 
		{		
			PreparedStatement ps = connection.prepareStatement("select * from usersen where login=?");
			ps.setString(1,id);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
			Blob blob = rs.getBlob("photo");			
			photo = getFile(blob,id);				
			}
		}catch(SQLException e) 
		{
			e.printStackTrace();
		}
		return photo;
	}

	@Override
	public User createEntity()
	{
		return new User();
	}
	
	public File getFile(Blob blob,String id) throws  IOException, SQLException 
	{		
		Logger log =Logger.getLogger(DAOUser.class);	
		Properties images = PropertyFactory.getPropsImages();
		log.info(id + ":" + images.getProperty(id));
		File file = new File(UPLOAD_DIRECTORY + File.separator + images.getProperty(id));
		byte barr[] = blob.getBytes(1,(int) blob.length());
		FileOutputStream fout = new FileOutputStream(file);
		fout.write(barr);		
		fout.close();
		return file;
	}
}