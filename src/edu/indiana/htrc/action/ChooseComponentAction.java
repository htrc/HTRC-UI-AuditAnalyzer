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
# File:  ChooseComponentAction.java
# Description: TODO
#
# -----------------------------------------------------------------
# 
*/
package edu.indiana.htrc.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import edu.indiana.htrc.global.HTRCComponent;

public class ChooseComponentAction extends ActionSupport implements SessionAware{

	/**
	 * @param args
	 */
	private Map<String, Object> session;
	private int component;
	
	private String page;
	
	//private String[] 
	
	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public int getComponent() {
		return component;
	}

	public void setComponent(int component) {
		this.component = component;
	}

	public String  execute() {
		
		session.put("testsession", 1);
		
		if(component == HTRCComponent.DATA_API){
			page = "/pages/DataAPI.jsp";
		}else if(component == HTRCComponent.SOLR_PROXY){
			page = "/pages/SolrProxy.jsp";
		}else if(component == HTRCComponent.OAUTH2){
			page = "/pages/Oauth2.jsp";
		}else if(component == HTRCComponent.AGENT){
			page = "/pages/Agent.jsp";
		}else page = "/index.jsp";
		
		return SUCCESS;
		

	}

	@Override
	public void setSession(Map<String, Object> session) {

		this.session = session;
	}

}
