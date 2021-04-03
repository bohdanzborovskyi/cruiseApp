package filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import model.User;


/**
 *  Filter class for protect admin pages from illegal access.
 */

@WebFilter(urlPatterns= {"/admin/*"},
		dispatcherTypes = {
		DispatcherType.REQUEST, 
		DispatcherType.FORWARD, 
		DispatcherType.INCLUDE, 
		DispatcherType.ERROR},
		servletNames="AdminShipService")
public class AdminFilter implements Filter {

  
	private static String AUTH = "/view/authForm.jsp";
    public AdminFilter() 
    {    }


	public void destroy() 
	{	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		User user = (User) req.getSession().getAttribute("user");		
		if(user!=null && user.getRole().equals("admin")) 
		{
			chain.doFilter(request, response);
		}		
		else 
		{		
			SessionLocaleFilter.hidenError = false;
			req.getSession().setAttribute("errorMessage", "admin_error");	
			req.getSession().setAttribute("action", "error");		
			req.getRequestDispatcher(AUTH).forward(req, res);
		}
	}


	public void init(FilterConfig fConfig) throws ServletException 
	{	}

}
