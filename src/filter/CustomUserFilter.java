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

import model.User;

/**
 *  Filter class for protect custom users private pages from illegal access.
 */

@WebFilter(urlPatterns= {"/customUser/*"},
dispatcherTypes = {
DispatcherType.REQUEST, 
DispatcherType.FORWARD, 
DispatcherType.INCLUDE, 
DispatcherType.ERROR},
servletNames="AdminShipService")
public class CustomUserFilter implements Filter {


	public CustomUserFilter() 
	{   
	}


	public void destroy() 
	{	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		User user = (User) req.getSession().getAttribute("user");
		if(user!=null && user.getRole().equals("custom")) 
		{
			chain.doFilter(request, response);
		}
		else if(user!=null && !user.getRole().equals("custom"))
		{
			req.getSession().setAttribute("errorMessage", "custom_error");				
			res.sendRedirect("http://localhost:8080/CruiseApp/auth?action=error");
		}
		else 
		{
			res.sendRedirect("http://localhost:8080/CruiseApp/auth?action=error");
		}
	}


	public void init(FilterConfig fConfig) throws ServletException 
	{	}

}