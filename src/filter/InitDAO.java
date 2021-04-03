package filter;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import DAO.DAOExcursion;
import DAO.DAOShip;
import DAO.DAOUser;
import model.User;
import service.PropertyFactory;

/**
 *  Filter class for setting character encoding and redirect all ships and excursions to pages.
 */

@WebFilter("/*")
public class InitDAO implements Filter {   
	 private final String UPLOAD_DIRECTORY = "C:/Users/tempadmin2/git/github-bitbucket-gitlab-CruiseApp/CruiseApp/WebContent/images";
	 @Override
	    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException 
	 {		
		 	Properties images = PropertyFactory.getPropsImages();
		 	HttpServletRequest req = (HttpServletRequest)request;
		    DAOShip daoShip = new DAOShip();	
		    DAOExcursion daoExc = new DAOExcursion();
		    DAOUser daoUser = new DAOUser();
		    User userTemp = (User)req.getSession().getAttribute("user");			   
		    if(userTemp!=null && userTemp.getRole().equals("custom")) 
		    {
		    	Logger log = Logger.getLogger(InitDAO.class);		    
		    	User user = daoUser.getByID(userTemp.getLogin(), (String)req.getSession().getAttribute("sessionLocale"));		    	
		    	File f = new File(UPLOAD_DIRECTORY + File.separator + images.getProperty(user.getLogin()));			    
		    	log.info(images.getProperty(user.getLogin()));
		    	log.info(f.getName()); 
		    	req.getSession().setAttribute("photoName", f.getName());
		    	req.getSession().setAttribute("photo", "images/" + f.getName());
		    	req.getSession().setAttribute("user", user);
		    }
		    
		    if(request.getParameter("sessionLocale")!=null)
		    {		  		    	
		    	request.setAttribute("ships", daoShip.getAll(request.getParameter("sessionLocale")));			    	
		    	request.setAttribute("excursions", daoExc.getAll(request.getParameter("sessionLocale")));
		    }else 
		    {
		    	request.setAttribute("excursions",daoExc.getAll("en"));
		    	request.setAttribute("ships",daoShip.getAll("en"));
		    }
	        chain.doFilter(request, response);
	    }

	@Override
	public void destroy() {
		
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException 
	{
				
	}

	

}
