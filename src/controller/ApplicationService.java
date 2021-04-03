package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import DAO.DAOExcursion;
import DAO.DAOShip;
import DAO.DAOShipExc;
import DAO.DAOUExc;
import DAO.DAOUShip;
import DAO.DAOUser;
import filter.SessionLocaleFilter;
import model.Excursion;
import model.Ship;
import model.ShipExcursion;
import model.User;
import model.UserExcursion;
import model.UserShip;
import service.MailSender;

/**
 * This service-class consist methods for working and manipulate with projects entities.
 */

public class ApplicationService 
{
	private DAOUser daoUser;
	private DAOExcursion daoExc;
	private DAOShip daoShip;
	private DAOUShip daoUShip;
	private DAOUExc daoUExc;
	private DAOShipExc daoSE;	
	Logger log = Logger.getLogger(ApplicationService.class);
	

	public ApplicationService() 
	{	
		daoExc = new DAOExcursion();
		daoUser = new DAOUser();
		daoShip = new DAOShip(); 
		daoUShip = new DAOUShip();
		daoUExc = new DAOUExc();
		daoSE = new DAOShipExc();
	}
	
	public void makeUser(HttpServletRequest request, File temp) throws SQLException, IOException {		
		String locale = (String) request.getSession().getAttribute("sessionLocale");
		User user = new User();
		RegistrationController.getFields(RegistrationController.multiparts,request);		
		user.setLogin(RegistrationController.getParameter("login"));
		user.setPassword(RegistrationController.getParameter("password"));
		user.setName(RegistrationController.getParameter("name_en"));
		user.setSurname(RegistrationController.getParameter("surname_en"));
		user.setTelefon(RegistrationController.getParameter("telephon"));
		user.setRole("custom");		
		user.setCash(Integer.parseInt(RegistrationController.getParameter("cash")));
		user.setEmail(RegistrationController.getParameter("email"));	
		user.setPhoto(temp);
		if(RegistrationController.getParameter("action").equals("editUser")) 
		{
			log.info("user update");
			daoUser.update(user, "en");
		}
		else 
		{
			daoUser.add(user, "en");
		}
		user.setLogin(RegistrationController.getParameter("login"));
		user.setPassword(RegistrationController.getParameter("password"));		
		user.setName(RegistrationController.getParameter("name_ua"));
		user.setSurname(RegistrationController.getParameter("surname_ua"));
		user.setTelefon(RegistrationController.getParameter("telephon"));
		user.setRole("custom");		
		user.setCash(Integer.parseInt(RegistrationController.getParameter("cash")));
		user.setEmail(RegistrationController.getParameter("email"));
		user.setPhoto(temp);
		if(RegistrationController.getParameter("action").equals("editUser")) 
		{
			log.info("user update");
			daoUser.update(user, "ua");
		}
		else
		{
			daoUser.add(user, "ua");
			MailSender.sendMessage(RegistrationController.getParameter("email"),MailSender.text);
		}		
			request.getSession().setAttribute("user", daoUser.getByID(user.getLogin(), locale));		
	}
	
	
	
	public void makeExcursion(HttpServletRequest request, String locale) throws UnsupportedEncodingException, SQLException {
		Excursion excursion = new Excursion();
		excursion.setExcursionID(request.getParameter("excursionID"));
		excursion.setDescription(request.getParameter("description"));
		excursion.setCity(request.getParameter("city"));
		excursion.setPrice(Integer.parseInt(request.getParameter("price")));
		daoExc.add(excursion, locale);
	}
	
	public void updateExcursion(HttpServletRequest request) throws UnsupportedEncodingException, SQLException {
		Excursion excursion = new Excursion();
		String localeex = (String)request.getSession().getAttribute("sessionLocale");
		excursion.setExcursionID(request.getParameter("excursionID"));
		excursion.setDescription(request.getParameter("description"));
		excursion.setCity(request.getParameter("city"));
		excursion.setPrice(Integer.parseInt(request.getParameter("price")));
		daoExc.update(excursion, localeex);
	}
	
	public void makeShip(HttpServletRequest request, String locale)
			throws ParseException, UnsupportedEncodingException {
		Ship ship = new Ship();
		Logger.getLogger(ApplicationService.class).error(request.getParameter("departure"));
		ship.setCapacity(Integer.parseInt(request.getParameter("capacity")));
		ship.setCountPort(Integer.parseInt(request.getParameter("countPort")));			        
		Date departur = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("departure"));		
		ship.setDeparture(departur);			      
		ship.setDuration(request.getParameter("duration"));	     
		ship.setPrice(Integer.parseInt(request.getParameter("price")));
		ship.setRoute(request.getParameter("route"));
		ship.setServices(request.getParameter("services"));
		ship.setShipID(request.getParameter("shipID"));
		ship.setStaff(request.getParameter("staff"));
		ship.setType(request.getParameter("type"));
		daoShip.add(ship,locale);
	}
	
	public void updateShip(HttpServletRequest request) throws ParseException, UnsupportedEncodingException {
		Ship ship = new Ship();
		String localeed = (String)request.getSession().getAttribute("sessionLocale");
		ship.setCapacity(Integer.parseInt(request.getParameter("capacity")));
		ship.setCountPort(Integer.parseInt(request.getParameter("countPort")));					
		Date departur = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("departure"));
		ship.setDeparture(departur);			 
		ship.setDuration(request.getParameter("duration"));	     
		ship.setPrice(Integer.parseInt(request.getParameter("price")));
		ship.setRoute(request.getParameter("route"));
		ship.setServices(request.getParameter("services"));
		ship.setShipID(request.getParameter("shipID"));
		ship.setStaff(request.getParameter("staff"));
		ship.setType(request.getParameter("type"));		
		daoShip.update(ship,localeed);
	}
	
	public void excListByPages(HttpServletRequest request, String locale) {
		List<Excursion> exc = daoExc.getAll(locale);			
		List<Integer> pages = new ArrayList<>();			
		for(int i= 1;i<=Math.ceil(exc.size()/5)+1;i++) 
		{
			pages.add(i);
		}			
		request.setAttribute("pages", pages);			
		if(request.getParameter("page")!=null)
		{
			int page = Integer.parseInt(request.getParameter("page"));
			if(locale.equals("ua"))
			{
				request.setAttribute("excursions", daoExc.getAllByPage("ua",page));
			}else if (locale.equals("en")) 
			{
				request.setAttribute("excursions", daoExc.getAllByPage("en",page));
			}
		}
		else
		{			
			if(locale.equals("ua"))
			{
				request.setAttribute("excursions", daoExc.getAllByPage("ua",1));
			}else if (locale.equals("en")) 
			{
				request.setAttribute("excursions", daoExc.getAllByPage("en",1));
			}
		}
	}
	
	public void buyShip(HttpServletRequest request, HttpServletResponse response, String login, String locale)
			throws IOException, SQLException {
		User u;
		boolean act = true;			
		u = daoUser.getByID(login, locale);
		Ship ship = (Ship)daoShip.getByID(request.getParameter("shipID"),locale);		
		if(u.getCash()>ship.getPrice())
		{				
			List<UserShip> userShip = daoUShip.getAll(locale);
			for(UserShip us : userShip) 
			{
				if(us.getLogin().equals(u.getLogin()) && us.getShipID().equals(ship.getShipID())) 
				{						
					ship.setCapacity(ship.getCapacity()-1);
					daoShip.update(ship, locale);
					us.setCount(us.getCount()+1);						
					try {
						daoUShip.update(us,locale);
					} catch (SQLException e) 
					{
						request.getSession().setAttribute("error", "wrong_trans");
						response.sendRedirect("?action=listShips&sessionLocale=" + locale);
					}		
					if(locale.equals("en")) 
					{
						u.setCash(u.getCash()-ship.getPrice());
						daoUser.update(u,locale);
						request.getSession().setAttribute("cash", u.getCash());
						u = daoUser.getByID(login, "ua");
						ship = (Ship)daoShip.getByID(ship.getShipID(), "ua");
						u.setCash(u.getCash()-ship.getPrice());
						daoUser.update(u, "ua");
					}
					else if(locale.equals("ua"))
					{
						u.setCash(u.getCash()-ship.getPrice());
						daoUser.update(u, locale);
						request.getSession().setAttribute("cash", u.getCash());
						u = daoUser.getByID(login, "en");
						ship = (Ship)daoShip.getByID(ship.getShipID(), "en");
						u.setCash(u.getCash()-ship.getPrice());
						daoUser.update(u, "en");
					}						
					act = false;
				}
			}
			if(act)
			{
				UserShip uShip = new UserShip(u.getLogin(),request.getParameter("shipID"),1);
				ship.setCapacity(ship.getCapacity()-1);
				daoShip.update(ship, locale);					
				if(locale.equals("en")) 
				{
					u.setCash(u.getCash()-ship.getPrice());
					daoUser.update(u,locale);
					request.getSession().setAttribute("cash", u.getCash());
					Logger log = Logger.getLogger(ApplicationService.class);
					log.info(u.getCash());
					u = daoUser.getByID(login, "ua");
					ship = (Ship)daoShip.getByID(ship.getShipID(), "ua");
					u.setCash(u.getCash()-ship.getPrice());
					daoUser.update(u, "ua");
				}
				else if(locale.equals("ua"))
				{
					u.setCash(u.getCash()-ship.getPrice());
					daoUser.update(u, locale);
					request.getSession().setAttribute("cash", u.getCash());
					Logger log = Logger.getLogger(ApplicationService.class);
					log.info(u.getCash());
					u = daoUser.getByID(login, "en");
					ship = (Ship)daoShip.getByID(ship.getShipID(), "en");
					u.setCash(u.getCash()-ship.getPrice());
					daoUser.update(u, "en");
				}				
				daoUShip.add(uShip,locale);
					
			}			
			request.getSession().setAttribute("message", "buy_ship");			
			MailSender.sendMessage(u.getEmail(), MailSender.getBuyMessage(u.getName() + " " + u.getSurname()));
			response.sendRedirect("?action=userListShips&sessionLocale=" + locale);

		}else 
		{
			request.getSession().setAttribute("error", "no_money");
			response.sendRedirect("?action=listShips&sessionLocale=" + locale);
		}
		
	}
	
	public void buyExcursion(HttpServletRequest request, HttpServletResponse response, String login, String locale)
			throws IOException, SQLException {
		User u;
		boolean act = true;			
		u = daoUser.getByID(login, locale);
		Excursion exc = daoExc.getByID(request.getParameter("excursionID"), locale);
		List<UserExcursion> userExcursions = daoUExc.getAll(locale);
		if(u.getCash()>exc.getPrice())
		{
			for(UserExcursion ue : userExcursions) 
			{
				if(ue.getLogin().equals(u.getLogin()) && ue.getExcursionID().equals(exc.getExcursionID())) 
				{						
					ue.setCount(ue.getCount()+1);
					daoUExc.update(ue,locale);
					if(locale.equals("en")) 
					{
						u.setCash(u.getCash()-exc.getPrice());
						daoUser.update(u,locale);
						request.getSession().setAttribute("cash", u.getCash());
						u = daoUser.getByID(login, "ua");
						exc = daoExc.getByID(request.getParameter("excursionID"), "ua");
						u.setCash(u.getCash()-exc.getPrice());
						daoUser.update(u, "ua");
					}
					else if(locale.equals("ua"))
					{
						u.setCash(u.getCash()-exc.getPrice());
						daoUser.update(u, locale);
						request.getSession().setAttribute("cash", u.getCash());
						u = daoUser.getByID(login, "en");
						exc = daoExc.getByID(request.getParameter("excursionID"), "en");
						u.setCash(u.getCash()-exc.getPrice());
						daoUser.update(u, "en");
					}	
					act = false;
				}
			}
			if(act) 
			{
				UserExcursion uExc = new UserExcursion(u.getLogin(),request.getParameter("excursionID"),1);
				if(locale.equals("en")) 
				{
					u.setCash(u.getCash()-exc.getPrice());
					daoUser.update(u,locale);
					request.getSession().setAttribute("cash", u.getCash());
					u = daoUser.getByID(login, "ua");
					exc = daoExc.getByID(request.getParameter("excursionID"), "ua");
					u.setCash(u.getCash()-exc.getPrice());
					daoUser.update(u, "ua");
				}
				else if(locale.equals("ua"))
				{
					u.setCash(u.getCash()-exc.getPrice());
					daoUser.update(u,locale);
					request.getSession().setAttribute("cash", u.getCash());
					u = daoUser.getByID(login, "en");
					exc = daoExc.getByID(request.getParameter("excursionID"), "en");
					u.setCash(u.getCash()-exc.getPrice());
					daoUser.update(u, "en");
				}	
				daoUExc.add(uExc,locale);							
			}			
			request.getSession().setAttribute("message", "buy_exc");
			response.sendRedirect("?action=userListExcursions&sessionLocale=" + locale);
			SessionLocaleFilter.hidenMessage = false;
			
		}
		else 
		{			
			request.getSession().setAttribute("error", "no_money");
			response.sendRedirect("?action=listExcursions&sessionLocale=" + locale);
		}
	}
	
	public void revokeShip(HttpServletRequest request, HttpServletResponse response, User u, String login,	String locale) throws IOException, SQLException 
	{
		Ship ship = (Ship)daoShip.getByID(request.getParameter("shipID"), "en");			
		ship.setCapacity(ship.getCapacity()+1);
		daoShip.update(ship, "en");
		ship = (Ship)daoShip.getByID(request.getParameter("shipID"), "ua");
		ship.setCapacity(ship.getCapacity()+1);
		daoShip.update(ship, "ua");
		u = daoUser.getByID(u.getLogin(), locale);
		if(locale.equals("en")) 
		{
			ship = daoShip.getByID(request.getParameter("shipID"), locale);
			u.setCash(u.getCash()+ship.getPrice());
			daoUser.update(u,locale);
			request.getSession().setAttribute("cash", u.getCash());
			u = daoUser.getByID(login, "ua");
			ship = daoShip.getByID(request.getParameter("shipID"), "ua");
			u.setCash(u.getCash()+ship.getPrice());
			daoUser.update(u, "ua");
		}
		else if(locale.equals("ua"))
		{
			ship = daoShip.getByID(request.getParameter("shipID"), locale);
			u.setCash(u.getCash()+ship.getPrice());
			daoUser.update(u,locale);
			request.getSession().setAttribute("cash", u.getCash());
			u = daoUser.getByID(login, "en");
			ship = daoShip.getByID(request.getParameter("shipID"), "en");
			u.setCash(u.getCash()+ship.getPrice());
			daoUser.update(u, "en");
		}	
		try {
			daoUShip.delete(request.getParameter("shipID"),login);
		} catch (SQLException e) 
		{
			request.getSession().setAttribute("error", "wrong_trans");
			response.sendRedirect("?action=userListShips&sessionLocale=" + locale);			
		}		
		MailSender.sendMessage(u.getEmail(), MailSender.getAbortMessage(u.getName() + " " + u.getSurname()));
		response.sendRedirect("?action=userListShips&sessionLocale=" + locale);
	}
	
	public void revokeExcursion(HttpServletRequest request, HttpServletResponse response, User u, String login,
			String locale) throws IOException, SQLException {
		daoUExc.delete(request.getParameter("excursionID"),login);	
		u = daoUser.getByID(u.getLogin(), locale);
		if(locale.equals("en")) 
		{
			Excursion exc = daoExc.getByID(request.getParameter("excursionID"), locale);
			u.setCash(u.getCash()+exc.getPrice());
			daoUser.update(u,locale);
			request.getSession().setAttribute("cash", u.getCash());
			u = daoUser.getByID(login, "ua");
			exc = daoExc.getByID(request.getParameter("excursionID"), "ua");
			u.setCash(u.getCash()+exc.getPrice());
			daoUser.update(u, "ua");
		}
		else if(locale.equals("ua"))
		{
			Excursion exc = daoExc.getByID(request.getParameter("excursionID"), locale);
			u.setCash(u.getCash()+exc.getPrice());
			daoUser.update(u,locale);
			request.getSession().setAttribute("cash", u.getCash());
			u = daoUser.getByID(login, "en");
			exc = daoExc.getByID(request.getParameter("excursionID"), "en");
			u.setCash(u.getCash()+exc.getPrice());
			daoUser.update(u, "en");
		}	
		response.sendRedirect("?action=userListExcursions&sessionLocale=" + locale);
	}
	
	public String addCash(HttpServletRequest request) throws SQLException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		String login = user.getLogin();
		User userEN = daoUser.getByID(login, "en"); 
		int currentCash = userEN.getCash();
		currentCash=currentCash + Integer.parseInt(request.getParameter("cash"));
		userEN.setCash(currentCash);
		daoUser.update(userEN, "en");
		User userUA = daoUser.getByID(login, "ua"); 
		currentCash = userUA.getCash();
		currentCash=currentCash + (Integer.parseInt(request.getParameter("cash"))*30);
		userUA.setCash(currentCash);
		daoUser.update(userUA, "ua");
		return login;
	}
	
	public void excShipListByPages(HttpServletRequest request, String locale) {
		List<ShipExcursion> excShip = daoSE.getAll(locale);			
		List<Integer> pages = new ArrayList<>();			
		for(int i= 1;i<=Math.ceil(excShip.size()/5)+1;i++) 
		{
			pages.add(i);
		}			
		request.setAttribute("pages", pages);			
		if(request.getParameter("page")!=null)
		{
			int page = Integer.parseInt(request.getParameter("page"));
			if(locale.equals("ua"))
			{
				request.setAttribute("shipsExcursions", daoSE.getAllByPage("ua",page));
			}else if (locale.equals("en")) 
			{
				request.setAttribute("shipsExcursions", daoSE.getAllByPage("en",page));
			}
		}
		else
		{			
			if(locale.equals("ua"))
			{
				request.setAttribute("shipsExcursions", daoSE.getAllByPage("ua",1));
			}else if (locale.equals("en")) 
			{
				request.setAttribute("shipsExcursions", daoSE.getAllByPage("en",1));
			}
		}
	} 	

}
