package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.io.File;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;


import DAO.DAOUser;
import filter.SessionLocaleFilter;
import model.User;
import service.Matcher;
import service.PropertyFactory;


/**
 * This class register new users for cruise app.
 */

@WebServlet("/register")
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String UPLOAD_DIRECTORY = "C:/Users/tempadmin2/git/github-bitbucket-gitlab-CruiseApp/CruiseApp/WebContent/images";
	private final String UPLOAD_DIRECTORY_MUSIC = "C:/Users/tempadmin2/git/github-bitbucket-gitlab-CruiseApp/CruiseApp/WebContent/music";
	private static String REGISTER = "/view/registrationForm.jsp";	
	private static String USER_EN = "/customUser/userEN.jsp";
	private static String USER_UA = "/customUser/userUA.jsp";
	private ApplicationService service;
	private DAOUser daoUser;
	boolean checkUser;
	Matcher match;
	private static final Logger log = Logger.getLogger(RegistrationController.class);	
	private static Properties props,images = null;
	SessionLocaleFilter slf = new SessionLocaleFilter();
	public static String fileName,parameter,filePath =" ";
	public static Map<String,String> fields;
	public static List<FileItem> multiparts;
	private File temp;
	
	
	
    public RegistrationController() 
    {    	
        super();               
        service = new ApplicationService();
        daoUser = new DAOUser();  
        match = new Matcher();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{				
		request.getSession().setAttribute("message", "null");
		request.getSession().setAttribute("errorMessage", "null");
		request.getSession().setAttribute("error", null);		
		request.getRequestDispatcher(REGISTER).forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	request.getSession().setAttribute("message", "null");
	request.getSession().setAttribute("errorMessage", "null");
	request.getSession().setAttribute("error", "null");	
	checkUser=true;		
	try 
	{
		multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
	} catch (FileUploadException e1) 
	{		
		e1.printStackTrace();
	}
	getFields(multiparts,request);			
	if(getParameter("action").equals("addmp3")) 
	{			
		request.getSession().setAttribute("mp3", "music/" + getFile(multiparts,request).getName());
		System.out.println("mp3:"  +   request.getSession().getAttribute("mp3"));			
		if(request.getSession().getAttribute("sessionLocale").equals("ua"))
		{
			request.getRequestDispatcher(USER_UA).forward(request,response);
		}else 
		{
			request.getRequestDispatcher(USER_EN).forward(request,response);
		}
	}
	else 
	{	
	try
		{
		try {
		//	multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
		//	getFields(multiparts,request);			
			List<User> users = daoUser.getAll((String) request.getSession().getAttribute("sessionLocale")); 
			for(User u : users) 
			{
				System.out.println(getParameter("login") + ": " + u.getLogin());
				if(u.getLogin().equals(getParameter("login"))) 
				{					
					checkUser = false;
				}
			}
			if(checkUser)
			{						
				File temp = uploadFile(multiparts,request,getParameter("login"));		
				log.info(getParameter("telephon"));
				System.out.println(getParameter("telephon"));
				match.validateField(Matcher.phonePattern, getParameter("telephon"));				
				match.validateField(Matcher.enPattern, getParameter("name_en"));
				match.validateField(Matcher.enPattern, getParameter("surname_en"));
				match.validateField(Matcher.uaPattern, getParameter("name_ua"));
				match.validateField(Matcher.uaPattern, getParameter("surname_ua"));
				match.validateField(Matcher.cashPattern, getParameter("cash"));
				match.validateField(Matcher.emailPattern, getParameter("email"));				
				service.makeUser(request,temp);				
				request.getSession().setAttribute("action", "message");
				request.getSession().setAttribute("message", "user_registr");				
				response.sendRedirect(request.getContextPath() + "/auth");						
			}
			else if(getParameter("action").equals("editUser")) 
			{						
				temp = uploadFile(multiparts,request,getParameter("login"));				
				log.info(fileName + " " + (String)request.getSession().getAttribute("photoName"));
				log.info(temp.getAbsolutePath());
				match.validateField(Matcher.phonePattern, getParameter("telephon"));				
				match.validateField(Matcher.enPattern, getParameter("name_en"));
				match.validateField(Matcher.enPattern, getParameter("surname_en"));
				match.validateField(Matcher.uaPattern, getParameter("name_ua"));
				match.validateField(Matcher.uaPattern, getParameter("surname_ua"));
				match.validateField(Matcher.cashPattern, getParameter("cash"));
				match.validateField(Matcher.emailPattern, getParameter("email"));				
				service.makeUser(request,temp);		
				SessionLocaleFilter.hidenMessage = false;
				request.getSession().setAttribute("message", "user_edit");						
				response.sendRedirect("/CruiseApp/customUser?action=editUser&sessionLocale=" + request.getSession().getAttribute("sessionLocale"));					
			}else 
			{
				SessionLocaleFilter.hidenError = false;	
				request.getSession().setAttribute("error", "user_login");		   
				request.getRequestDispatcher(REGISTER).forward(request, response);			
				request.getSession().setAttribute("error", "null");	
				log.info("Warning!Trying to add user with same name");
			}
			}catch (SQLException e) 
			{
				SessionLocaleFilter.hidenError = false;	
				request.getSession().setAttribute("error", "wrong_trans");		   
				request.getRequestDispatcher(REGISTER).forward(request, response);	
				request.getSession().setAttribute("error", "null");	
				log.info("Warning! Problem with sql transactions during adding a new user");
			}
		}catch ( NumberFormatException | IOException |FileUploadException e) 
		{				
			e.printStackTrace();
			log.info("Warning! Invalid input data during adding a new user " + e.getClass());
			props = PropertyFactory.getPropsLoc((String)request.getSession().getAttribute("sessionLocale"));
			SessionLocaleFilter.hidenError = false;		
			request.getSession().setAttribute("error", "null");
			request.getSession().setAttribute("errorM", props.getProperty("invalid_data") + ": " + e.getMessage());	
			if(getParameter("action").equals("editUser")) 
			{				
				response.sendRedirect("/CruiseApp/customUser?action=editUser&sessionLocale=" + request.getSession().getAttribute("sessionLocale"));
			}
			else
			{
				request.getRequestDispatcher(REGISTER).forward(request, response);	
			}
			request.getSession().setAttribute("error", "null");				
		} 
	}
	}
	
	public File uploadFile(List<FileItem> multiparts,HttpServletRequest req, String id) throws  FileUploadException
	{				
			try 
			{				
				for(FileItem item : multiparts) 
				{						
					if(!item.isFormField()) 
					{
						fileName = new File(item.getName()).getName();		
						log.info(fileName + "/" + req.getSession().getAttribute("photoName") + !(fileName.equals("")));						
						if(!fileName.equals(""))
						{
							item.write(new File(UPLOAD_DIRECTORY + File.separator + fileName));	
						}
					}
				}									
			}catch(Exception e) 
			{
				System.out.println(e);
				log.info(e.getMessage());
				throw new FileUploadException("File not upload!" + " " );
			}				
		log.info(fileName);
		if(fileName == null) 
		{
			images = PropertyFactory.getPropsImages();
			fileName =(String) images.get(getParameter("login"));
			return new File(UPLOAD_DIRECTORY + File.separator + fileName);
		}
		if(!fileName.equals(""))
		{
			images = PropertyFactory.getPropsImages();
			images.put(getParameter("login"),fileName);			
			return new File(UPLOAD_DIRECTORY + File.separator + fileName);			
		}
		else
		{
			images = PropertyFactory.getPropsImages();
			images.put(getParameter("login"),req.getSession().getAttribute("photoName"));
			File f = new File(UPLOAD_DIRECTORY + File.separator +  req.getSession().getAttribute("photoName"));			
			return f;
		}
	}	
	
	
	public static String getParameter(String fieldName) 
	{
		parameter = " ";
		for(String key: fields.keySet()) 
		{			
			if(key.equals(fieldName)) 
			{
				parameter = fields.get(key).toString();				
			}
		}
		return parameter;
		
		
	}	
	
	public static Map<String,String> getFields(List<FileItem> multiparts, HttpServletRequest req)
	{
		fields = new HashMap<String,String>();							
			try 
			{								
				for(FileItem item : multiparts) 
				{						
					if((item.isFormField()) && (item.getSize()<100))
					{
						fields.put(item.getFieldName(), item.getString("UTF-8"));					
					}
				}				
			}catch(Exception e) 
			{			
				log.info(e);
			}				
		return fields;
	}
	
	public File getFile(List<FileItem> multiparts,HttpServletRequest req) 
	{
		for(FileItem item : multiparts) 
		{
			if(!item.isFormField()) 
			{
				fileName = new File(item.getName()).getName();	
				if(!fileName.equals(""))
				{
				try {
						 item.write(new File(UPLOAD_DIRECTORY_MUSIC + File.separator + fileName));
					} catch (Exception e)
					{						
						e.printStackTrace();
					}	
				}
			}
		}
		return new File(UPLOAD_DIRECTORY_MUSIC + File.separator + fileName);
		
	}

}
