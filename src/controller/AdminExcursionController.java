package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DAOExcursion;
import DAO.DAOShipExc;
import filter.SessionLocaleFilter;
import model.Excursion;
import model.ShipExcursion;

/**
 * This class-controller provide admins CRUD operations with Excursions.
 */

@WebServlet("/admin/adminExcursion")
public class AdminExcursionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String CREATE_EXC = "createExcursion.jsp";
	private static String LIST_EXC = "excursionsAdmin.jsp";
	private static String EDIT_EXC = "editExcursion.jsp";
	private static String SHIP_EXC = "shipExcCreate.jsp";
    private DAOExcursion daoExc;
    private DAOShipExc daoSE;   
    private ApplicationService service;
    boolean checkExcursion;
    

    public AdminExcursionController() {
        super();
        daoExc = new DAOExcursion();
        daoSE = new DAOShipExc();
        service = new ApplicationService();
        checkExcursion = false;
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		String locale = (String)request.getSession().getAttribute("sessionLocale");
		String action =  request.getParameter("action");		
		if(action.equalsIgnoreCase("create")) 
		{									
			request.getRequestDispatcher(CREATE_EXC).forward(request, response);
		}
		else if(action.equals("edit")) 
		{			
			String excursionID = request.getParameter("excursionID");
			Excursion excursion = daoExc.getByID(excursionID,locale);			
			request.setAttribute("exc", excursion);			
			request.getRequestDispatcher(EDIT_EXC).forward(request, response);
		}
		else if(action.equals("delete")) 
		{			
			String excursionID = request.getParameter("excursionID");
			try {
				daoExc.delete(excursionID);
			} catch (SQLException e) {
				SessionLocaleFilter.hidenError = false;
				request.getSession().setAttribute("error", "wrong_trans");
				response.sendRedirect("?action=delete&sessionLocale=" + locale);			
			}
			request.getRequestDispatcher(LIST_EXC).forward(request, response);
			
		}
		else if(action.equals("list"))
		{							
			request.getRequestDispatcher(LIST_EXC).forward(request, response);
			request.getSession().setAttribute("error", "null");
		}else if(action.equals("createShipExc")) 
		{	
			service.excShipListByPages(request, locale);			
			request.getRequestDispatcher(SHIP_EXC).forward(request, response);
			request.getSession().setAttribute("error", "null");	
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
				List<Excursion> excs = daoExc.getAll(locale);
				for(Excursion e : excs) 
				{
					if(e.getExcursionID().equals(request.getParameter("excursionID"))) 
					{
						checkExcursion = true;
					}
				}
				if(checkExcursion) 
				{
					SessionLocaleFilter.hidenError = false;
					request.getSession().setAttribute("error", "same_exc");
					response.sendRedirect("?action=create&sessionLocale=" + locale);	
				}
				else 
				{
					service.makeExcursion(request, locale);
					request.getSession().setAttribute("error","null"); 					
					response.sendRedirect(request.getContextPath() + "/admin/adminExcursion?action=list");		
				}				
			}catch (NullPointerException |NumberFormatException  e) 
			{
				SessionLocaleFilter.hidenError = false;
				request.getSession().setAttribute("error", "invalid_data");
				request.getRequestDispatcher(CREATE_EXC).forward(request, response);		
			} catch (SQLException e) {
				SessionLocaleFilter.hidenError = false;
				request.getSession().setAttribute("error", "wrong_trans");
				response.sendRedirect("?action=create&sessionLocale=" + locale);	
			}
			
		}	
		else if(action.equals("edit")) 
		{	
			try 
			{			
			service.updateExcursion(request);
			}catch (NullPointerException | NumberFormatException  e) 
			{
				String excID = request.getParameter("excursionID");				
				Excursion excEx = daoExc.getByID(excID,locale);	
				SessionLocaleFilter.hidenError = false;
				request.getSession().setAttribute("error", "invalid_data");
			    request.setAttribute("exc",excEx);
				request.getRequestDispatcher(EDIT_EXC).forward(request, response);			
			} catch (SQLException e) {
				SessionLocaleFilter.hidenError = false;
				request.getSession().setAttribute("error", "wrong_trans");
				response.sendRedirect("?action=edit&sessionLocale=" + locale);	
			}
			request.getSession().setAttribute("error","null"); 
			response.sendRedirect(request.getContextPath() + "/admin/adminExcursion?action=list");		
		}
		else if(action.equals("createShipExc")) 
		{			
			ShipExcursion se = new ShipExcursion();
			se.setExcursionID(request.getParameter("excursionID"));			
			se.setShipID(request.getParameter("shipID"));			
			try {
				daoSE.add(se, locale);
			} catch (SQLException e) {
				SessionLocaleFilter.hidenError = false;
				request.getSession().setAttribute("error", "wrong_trans");
				response.sendRedirect("?action=list&sessionLocale=" + locale);			
			}
			request.getSession().setAttribute("error","null"); 
			response.sendRedirect(request.getContextPath() + "/admin/adminExcursion?action=list");		
		}
		else if(action.equals("deleteShipExc")) 
		{
			System.out.println(request.getParameter("excursionID") + " " + request.getParameter("shipID"));
			try 
			{
				daoSE.delete(request.getParameter("excursionID"),request.getParameter("shipID"));
			}catch(SQLException e) 
			{
				SessionLocaleFilter.hidenError = false;
				request.getSession().setAttribute("error", "wrong_trans");
				response.sendRedirect("?action=createShipExc&sessionLocale=" + locale);		
			}
			request.getSession().setAttribute("error","null"); 
			response.sendRedirect(request.getContextPath() + "/admin/adminExcursion?action=list");		
		}
	}
	
	

	

	

}
