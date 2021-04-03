package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import DAO.DAOUser;
import model.User;

/**
 *  JUnit test class for testing DAOUser class.
 */


public class DAOTest 
{
	DAOUser daoUser;
	
	@Before
    public void init() 
	{
		daoUser = new DAOUser(); 
	}
    @After
    public void tearDown()
    {
    	daoUser=null;
    }
    
    @Test
	public void testUserDAO() 
	{
		
		List<User> users = daoUser.getAll("en");
		for(User u : users) 
		{
			assertTrue(u instanceof User);
		}
	}
    @Test
	public void testUserData() 
	{
		List<User> users = daoUser.getAll("en");
		for(User u : users) 
		{
			assertEquals(u.getLogin().getClass(),String.class);			
			assertEquals(u.getRole().getClass(),String.class);
			assertEquals(u.getTelefon().getClass(),String.class);
		}
	}
    @Test
	public void testUserInsert() 
	{
		User user = new User("login","password", "name", "surname", "telephon","role", 999, "1432432@gmail.com");
		try {
			daoUser.add(user, "en");
		} catch (SQLException e) 
		{			
			e.printStackTrace();
		}
		User user1 = daoUser.getByID("login", "en");
		assertTrue(user.getLogin().equals(user1.getLogin()));
	}
    @Test
	public void testUserUpdate() 
	{
		User user = daoUser.getByID("login", "en");
		user.setCash(1000);
		try {
			daoUser.update(user, "en");
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		User user1 = daoUser.getByID("login", "en");
		assertEquals(1000,user1.getCash());
	}
	@Test
	public void testUserDelete() 
	{			
		try {			
			daoUser.delete("u");						
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		User s = daoUser.getByID("u", "en");
		assertEquals(null, daoUser.getByID("u","en").getLogin());		
	}
	

}
