package controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DAOShip;
import filter.SessionLocaleFilter;
import model.Ship;

/**
 * This class-controller provide admins CRUD operations with Ships.
 */


@WebServlet("/admin/adminShip")
public class AdminShipController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String CREATE_SHIP = "createShip.jsp";
	private static String LIST_SHIP = "shipsAdmin.jsp";
	private static String EDIT_SHIP = "editShip.jsp";
    private DAOShip daoShip;
    private ApplicationService service;
    boolean checkShip;
   

    public AdminShipController() {
        super();
        daoShip = new DAOShip();
        service = new ApplicationService();
        checkShip = false;        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{		
		String action =  request.getParameter("action");		
		if(action.equalsIgnoreCase("create")) 
		{									
			request.getRequestDispatcher(CREATE_SHIP).forward(request, response);
		}
		else if(action.equals("edit")) 
		{			
			String shipID = request.getParameter("shipID");
			Ship ship = daoShip.getByID(shipID,(String) request.getSession().getAttribute("sessionLocale"));			
			request.setAttribute("ship", ship);			
			request.getRequestDispatcher(EDIT_SHIP).forward(request, response);
		}
		else if(action.equals("delete")) 
		{			
			String shipID = request.getParameter("shipID");
			daoShip.delete(shipID);
			request.getRequestDispatcher(LIST_SHIP).forward(request, response);
			
		}
		else if(action.equals("list"))
		{							
			request.getRequestDispatcher(LIST_SHIP).forward(request, response);
			request.getSession().setAttribute("error", "null");
		}
		else if(action.equals("listBy")) 
		{
			request.setAttribute("ships", daoShip.getAllOrder(request.getParameter("sessionLocale"),request.getParameter("orderBy")));
			request.getRequestDispatcher(LIST_SHIP).forward(request, response);
			
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{				
		String action = request.getParameter("action");
		String locale = (String)request.getSession().getAttribute("sessionLocale");
		if(action.equals("create")) 
		{	
			try 
			{	
				List<Ship> ships = daoShip.getAll(locale);
				for(Ship s : ships) 
				{
					if(s.getShipID().equals(request.getParameter("shipID")))
							{
								checkShip = true;
							}
				}
				if(checkShip) 
				{
					SessionLocaleFilter.hidenError = false;
					request.getSession().setAttribute("error", "same_ship");
					request.getRequestDispatcher(CREATE_SHIP).forward(request, response);
				}
				else 
				{
					service.makeShip(request, locale);
					request.getSession().setAttribute("error", "null"); 
					response.sendRedirect(request.getContextPath() + "/admin/adminShip?action=list");
				}
				
			}catch (NullPointerException |NumberFormatException |ParseException e) 
			{
				SessionLocaleFilter.hidenError = false;
				request.getSession().setAttribute("error", "invalid_data");
				request.getRequestDispatcher(CREATE_SHIP).forward(request, response);		
			}			
		}	
		else if(action.equals("edit")) 
		{	
			try 
			{			
				service.updateShip(request);
			}catch (NullPointerException | NumberFormatException | ParseException  e) 
			{
				String shipID = request.getParameter("shipID");				
				Ship shipExc = daoShip.getByID(shipID,(String) request.getSession().getAttribute("sessionLocale"));	
				request.getSession().setAttribute("error", "invalid_data");
			    request.setAttribute("ship",shipExc);
				request.getRequestDispatcher(EDIT_SHIP).forward(request, response);			
			} 
			request.getSession().setAttribute("error","null");  		
			response.sendRedirect(request.getContextPath() + "/admin/adminShip?action=list&sessionLocale=" + request.getSession().getAttribute("sessionLocale"));
		}			
		
	}


	


	

}
