/*
#
# Copyright 2013 The Trustees of Indiana University
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either expressed or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#
# -----------------------------------------------------------------
#
# Project: HTRC-UI-AuditAnalyzer
# File:  AjaxJsonAction.java
# Description: TODO
#
# -----------------------------------------------------------------
# 
*/
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
