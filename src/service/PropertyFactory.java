package service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertyFactory {
	
	private static Properties db = null;
	private static Properties loc = null;
	private static Properties images = null;
	private static InputStream inputStream = null;
	

	private  PropertyFactory() 
	{}
	
	public static Properties getPropsDB() 
	{
		if(db == null)
		{
		Properties db = new Properties();
		try 
		{		
        inputStream = DBConnection.class.getClassLoader().getResourceAsStream("/resources/db.properties");
        db.load(inputStream);        
		}catch(IOException e) 
		{
			e.printStackTrace();
		}
		return db;
		}
		else return db;
	}
	
	public static Properties getPropsLoc(String locale) 
	{
		if(loc == null)
		{
			Properties loc = new Properties();
			try 
			{
				if(locale.equals("en"))
				{
					inputStream = DBConnection.class.getClassLoader().getResourceAsStream("/resources/loc.properties");
				}else 
				{
					inputStream = DBConnection.class.getClassLoader().getResourceAsStream("/resources/loc_ua.properties");
				}			
			loc.load(inputStream);        
			}catch(IOException e) 
			{
				e.printStackTrace();
			}
			return loc;
		}else return loc;
	}
	
	public static Properties getPropsImages() 
	{
		if(images==null) 
		{
			images = new Properties();
			try 
			{
				 inputStream = DBConnection.class.getClassLoader().getResourceAsStream("/resources/images.properties");
				 images.load(inputStream);
			}catch(IOException e) 
			{
				e.printStackTrace();
			}	
			return images;
		}else  return images;		
	}

}
