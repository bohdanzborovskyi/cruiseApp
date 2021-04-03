package controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import DAO.DAOExcursion;
import DAO.DAOShip;
import DAO.DAOUExc;
import DAO.DAOUShip;
import DAO.DAOUser;
import filter.SessionLocaleFilter;
import model.Excursion;
import model.Ship;
import model.User;
import model.UserExcursion;
import model.UserShip;


/**
 *  This class-controller offer to customer users all services for interact with cruise firm.
 */

@WebServlet("/customUser")
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String LIST_SHIP = "customUser/ships.jsp";
	private static String LIST_EXCURSIONS = "customUser/excursions.jsp";
	private static String LIST_USER_EXCURSIONS = "customUser/userExcursions.jsp";
	private static String LIST_USER_SHIPS = "customUser/userShips.jsp";
	private static String AVAILABLE_LIST_EXCURSIONS = "customUser/availableExcursions.jsp";
	private static String USER_EDIT_EN = "customUser/userEN.jsp";
	private static String USER_EDIT_UA = "customUser/userUA.jsp";
	private DAOShip daoShip;
	private  DAOUser daoUser;
	private ApplicationService service;
	private static final Logger log = Logger.getLogger(CustomerController.class);
	


	private DAOExcursion daoExc;
	
    public CustomerController() 
    {
        super();
        daoShip = new DAOShip();        
        daoUser = new DAOUser();     
        daoExc = new DAOExcursion();
        service = new ApplicationService();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		User u = (User)request.getSession().getAttribute("user");		
		String login = u.getLogin();		
		String locale = (String) request.getSession().getAttribute("sessionLocale");			
		User cash = daoUser.getByID(u.getLogin(),locale);
		request.getSession().setAttribute("cash",cash.getCash());			
		String action =  request.getParameter("action");
		
		if(action.equals("listShips")) 
		{	
			hideError(!request.getSession().getAttribute("error").equals("null"));
			request.getRequestDispatcher(LIST_SHIP).forward(request, response);
			request.getSession().setAttribute("error", "null");	
		}
		else if(action.equals("listBy")) 
		{				
			request.setAttribute("ships", daoShip.getAllOrder(locale,request.getParameter("orderBy")));
			request.getRequestDispatcher(LIST_SHIP).forward(request, response);
						
		}else if(action.equals("listUserBy")) 
		{				
			request.setAttribute("userShips", daoShip.getAllUser(locale,request.getParameter("orderBy"),login));
			request.getRequestDispatcher(LIST_USER_SHIPS).forward(request, response);
						
		}else if(action.equals("listExcursions")) 
		{	
			hideError(!request.getSession().getAttribute("error").equals("null"));			
			service.excListByPages(request, locale);
			request.getRequestDispatcher( LIST_EXCURSIONS).forward(request, response);		
			request.getSession().setAttribute("error", "null");	
		}
		else if(action.equals("availableListExcursions")) 
		{				
			List<Excursion> excursions = new ArrayList<>();
			List<String> ids = daoShip.getAllUserShipID(login);
			for(String id : ids) 
			{
				List<Excursion> tempexcs = daoExc.getAllAvailable(locale, id);
				for(Excursion excurs : tempexcs) 
				{
					if(!excursions.contains(excurs)) 
					{
						excursions.add(excurs);
					}
				}
			}			
			request.setAttribute("availableExcursions", excursions);
			request.getRequestDispatcher(AVAILABLE_LIST_EXCURSIONS).forward(request, response);			
		}
		else if(action.equals("buyShip")) 
		{			
			try {				
					service.buyShip(request, response, login, locale);
					log.info("Ship was bought");
				} catch (SQLException e) 
				{
					SessionLocaleFilter.hidenError = false;
					request.getSession().setAttribute("error", "wrong_trans");
					response.sendRedirect("?action=buyShip&sessionLocale=" + locale);	
					log.info("Problem with buying ship");
				}
		}
		else if(action.equals("buyExcursion")) 
		{				
			try {				
				service.buyExcursion(request, response, login, locale);				
			} catch (SQLException e) {						
				request.getSession().setAttribute("error", "wrong_trans");
				response.sendRedirect("?action=buyExcursion&sessionLocale=" + locale);	
				log.info("Problem with buying excursion");
			}
		}
		else if(action.equals("userListExcursions")) 
		{				
			hideMessage(!request.getSession().getAttribute("message").equals("null"));
			request.setAttribute("userExcursions", daoExc.getAllUser(locale, login));			
			request.getRequestDispatcher(LIST_USER_EXCURSIONS).forward(request, response);				
			request.getSession().setAttribute("message", "null");	
		}
		else if(action.equals("userListShips")) 
		{				
			hideMessage(!request.getSession().getAttribute("message").equals("null"));
			request.setAttribute("userShips",daoShip.getAllUser(locale,"capacity",login));
			request.getRequestDispatcher(LIST_USER_SHIPS).forward(request, response);	
			request.getSession().setAttribute("message", "null");	
		}
		else if(action.equals("revokeShip")) 
		{				
			try {		
				request.getSession().setAttribute("message", "revoke_ship");
				service.revokeShip(request, response, u, login, locale);
				log.info("Ship was revoked");
			} catch (SQLException e) 
			{
				log.info("Problem with revoking a ship");
				SessionLocaleFilter.hidenError = false;
				request.getSession().setAttribute("error", "wrong_trans");
				response.sendRedirect("?action=revokeShip&sessionLocale=" + locale);			
			}
		}
		else if(action.equals("revokeExcursion")) 
		{				
			try {
				SessionLocaleFilter.hidenMessage = false;
				request.getSession().setAttribute("message", "revoke_exc");
				service.revokeExcursion(request, response, u, login, locale);
				log.info("Excursion was revoked");
			} catch (SQLException e) {
				SessionLocaleFilter.hidenError = false;
				log.info("Problem with revoking an excursion");
				request.getSession().setAttribute("error", "wrong_trans");
				response.sendRedirect("?action=revokeExcursion&sessionLocale=" + locale);			
			}
		}
		else if(action.equals("download")) 
		{			
			File file = daoUser.getPhoto(u.getLogin());
			Logger log = Logger.getLogger(CustomerController.class);
			log.info(file.exists());			
			try(BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));ServletOutputStream stream = response.getOutputStream();) 
			{				
				response.setContentType("application/msword");
				response.setDateHeader("Expires", 0);
				response.addHeader("Content-Disposition", "attachment; filename=" + file.getName());
				response.setContentLength((int)file.length());				
				int readBytes = 0;
				while((readBytes = buf.read())!=-1)
					stream.write(readBytes);				
			}
		}
		else if(action.equals("editUser")) 
		{				
			if(locale.equals("ua")) 
			{
				request.getSession().setAttribute("user", daoUser.getByID(login, "ua"));
				request.setAttribute("name", daoUser.getByID(login, "en").getName());
				request.setAttribute("surname", daoUser.getByID(login, "en").getSurname());
				request.getRequestDispatcher(USER_EDIT_UA).include(request, response);
			}
			else 
			{
				request.getSession().setAttribute("user", daoUser.getByID(login, "en"));
				request.setAttribute("name", daoUser.getByID(login, "ua").getName());
				request.setAttribute("surname", daoUser.getByID(login, "ua").getSurname());
				request.getRequestDispatcher(USER_EDIT_EN).include(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{			
		String action =  request.getParameter("action");
		String locale= request.getParameter("sessionLocale");	
		if(action.equals("addCash")) 
		{
			try 
			{					
			User user;
			String login = service.addCash(request);
			log.info("Money was added");
			if(request.getParameter("page").equals("ship"))
			{				
				user = daoUser.getByID(login,locale);
				request.getSession().setAttribute("cash", user.getCash());
				response.sendRedirect("?action=listShips&sessionLocale=" + locale);
			}else if(request.getParameter("page").equals("exc"))
			{
				user = daoUser.getByID(login, locale);
				request.getSession().setAttribute("cash", user.getCash());
				response.sendRedirect("?action=listExcursions&sessionLocale=" + locale);

			}
			}catch(NumberFormatException e) 
			{	
				log.info("Problem with adding money");
				if(request.getParameter("page").equals("ship"))
				{
				SessionLocaleFilter.hidenError = false;
				request.getSession().setAttribute("error", "invalid_data");
				request.getRequestDispatcher(LIST_SHIP).forward(request, response);
				}
				else if(request.getParameter("page").equals("exc"))
				{
					SessionLocaleFilter.hidenError = false;
				request.getSession().setAttribute("error", "invalid_data");
				request.getRequestDispatcher(LIST_EXCURSIONS).forward(request, response);
				}
			} catch (SQLException e) 
			{
				SessionLocaleFilter.hidenError = false;
				request.getSession().setAttribute("error", "wrong_trans");
				response.sendRedirect("?action=listShips&sessionLocale=" + locale);			
			}
		}
		else if(action.equals("editUser")) 
		{
			
		}
	}
	
	public void hideError(boolean bool) 
	{
		if(bool) 
		{
			SessionLocaleFilter.hidenError = false;	
		}
	}
	
	public void hideMessage(boolean bool) 
	{
		if(bool)
		{
		SessionLocaleFilter.hidenMessage = false;
		}
	}
	


	

}
