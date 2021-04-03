package service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.regex.Pattern;
import javax.servlet.http.Part; 
import org.apache.log4j.Logger;

import controller.RegistrationController;

/**
 *  Checking class for check input values in registration form page.
 */

public class Matcher {

	public static String enPattern = "[A-Za-z]+";
	public static String uaPattern = "[йЙцЦуУкКеЕнНгГшШщЩзЗхХїЇєЄжЖдДлЛоОрРпПаАвВіІфФяЯчЧсСмМиИтТьЬбБюЮҐґ]+";
	public static String phonePattern = "\\d{3}-\\d{3}-\\d{2}-\\d{2}";
	public static String cashPattern = "[1-9][0-9]+";
	public static String emailPattern = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
	
	
	public void validateField(String pattern, String strToValidate) throws IOException 
	{		
		if(!Pattern.matches(pattern, strToValidate)) 
		{						
			throw new IOException(strToValidate + "  " + pattern);
		}
	}
	
	
}
