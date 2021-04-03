package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.DispatcherType;

import org.apache.log4j.Logger;


/**
 *  Filter class for changing locale.
 */

@WebFilter(urlPatterns="/*",dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
public class SessionLocaleFilter implements Filter {
	
	Logger log = Logger.getLogger(SessionLocaleFilter.class);
	 
	public void destroy() {	}
	public static boolean hidenError = true;
	public static boolean hidenMessage = true;

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{				
		HttpServletRequest req = (HttpServletRequest) request;
		if(hidenError)
		{
			req.getSession().setAttribute("hiddenError",true);
		}
		else 
		{
			req.getSession().setAttribute("hiddenError",false);
			hidenError = true;
		}
		
		if(hidenMessage)
		{
			req.getSession().setAttribute("hiddenMessage",true);
		}
		else 
		{
			req.getSession().setAttribute("hiddenMessage",false);
			hidenMessage = true;
		}
		
	    
		if (req.getParameter("sessionLocale") != null) {
	        req.getSession().setAttribute("sessionLocale", req.getParameter("sessionLocale"));		            
	    }    	    
		chain.doFilter(request, response);
	}

	
	public void init(FilterConfig fConfig) throws ServletException 	{	}
	
	
	
	

}
