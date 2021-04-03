package service;


import java.io.IOException;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.TagSupport;

public class Taglib extends TagSupport{
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private double percent;
		
		@Override
		  public int doStartTag() throws JspException {
		    try {
		      System.out.println(getPercent());
		      double perc = getPercent();
		      JspWriter out = pageContext.getOut();
		      if(perc<100) 
		      {
		    	  out.print("bg-danger");
		      }else 
		      {
		    	  out.print("bg-success");
		      }
		      
		    } 
		    catch (IOException e) {
		      e.printStackTrace();
		    }
		    return SKIP_BODY;
		  }
	
		public double getPercent() {
			return percent;
		}

		public void setPercent(double percent) {
			this.percent = percent;
		}

		
}
