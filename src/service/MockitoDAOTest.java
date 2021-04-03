package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.Test;
import org.mockito.Mockito;

import DAO.DAOUser;
import model.User;

/**
 *  Mockito test class for testing DAOUser class.
 */

public class MockitoDAOTest {

	DAOUser daoUser = Mockito.mock(DAOUser.class);
	
	
	@Test
	public void testUserDAO() 
	{
		//Checking of adding data	   
		User user = new User("login","password", "name", "surname", "telephon","role", 999, "eweqw@gmail.com" );
		try {
			daoUser.add(user, "en");
			Mockito.verify(daoUser).add(user, "en");
		} catch (SQLException e) 
		{			
			e.printStackTrace();
		}
		//Checking of getting data
		User user1 = daoUser.getByID("login", "en");
		Mockito.when(daoUser.getByID("login", "en")).thenReturn(user);
		Mockito.verify(daoUser,Mockito.atLeastOnce()).getByID("login", "en");
			
		//Testing of equivalence data
		List<User>list = new ArrayList<User>();
		Mockito.when(daoUser.getAll("en")).thenReturn((CopyOnWriteArrayList<User>) list);
		
		//Checking of updating data
		user.setCash(1000);
		try {
			daoUser.update(user, "en");
			Mockito.verify(daoUser).update(user,"en");
		} catch (SQLException e)
		{		
			e.printStackTrace();
		}
		
		//Checking with exception
		User u = new User();
		SQLException sql = new SQLException();
		try {
			Mockito.doThrow(sql).when(daoUser).add(u, "en");
		} catch (SQLException e) 
		{			
			e.printStackTrace();
		}
		
		//Checking of deleting data
		try {
			Mockito.verify(daoUser,Mockito.never()).delete("login");
		} catch (SQLException e)
		{			
			e.printStackTrace();
		}
		
	}

}
