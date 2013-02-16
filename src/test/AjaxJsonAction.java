package test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class AjaxJsonAction extends ActionSupport implements SessionAware{

	private Map<String, Object> session;
	
	private double xyz;
	
	 public double getXyz() {
		return xyz;
	}

	public void setXyz(double xyz) {
		this.xyz = xyz;
	}

	private InputStream inputStream;
	 
	    public InputStream getInputStream() {
	        return inputStream;
	    }

	    public String execute() throws Exception {
	    	double x = Math.random();
	    	
	    	setXyz(x);
	    	session.put("random", x);
	    	String string = "" + x;
	        inputStream = new ByteArrayInputStream(string.getBytes("UTF-8"));
	        return SUCCESS;
	    }
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;		
	}
	public  Map<String, Object> getSession(Map<String, Object> session) {
		return this.session;		
	}
	
}
