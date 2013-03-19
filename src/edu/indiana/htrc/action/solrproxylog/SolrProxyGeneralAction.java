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
# File:  SolrProxyGeneralAction.java
# Description: TODO
#
# -----------------------------------------------------------------
# 
*/
package edu.indiana.htrc.action.solrproxylog;

import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;

import util.Solr4Connector;
import util.Utility;

import com.opensymphony.xwork2.ActionSupport;

public class SolrProxyGeneralAction  extends ActionSupport{

//	private Map<String, Object> session;
//	Map<String, Integer> dateMap;
	protected String display;
	protected String errorPage;
	
	protected String[] fieldName;
	protected String from;
	Map<String, Integer> ipMap;
	Map<String, Integer> responseStatusMap;
	protected String sourceIP;
	Map<String, Integer> timeIntervalMap;
	protected String to;
	protected String XForward_For;
	Map<String, Integer> XForward_ForMap;
	
	/**
	 * get X-Forwarded-For map
	 * @return X-Forwarded-For map that maps X-Forwarded-For ip address to access count
	 */
	public Map<String, Integer> getXForward_ForMap() {
		return XForward_ForMap;
	}

	/**
	 * set X-Forwarded-For map
	 * @param xForward_ForMap map that maps X-Forwarded-For ip address to access count
	 */
	public void setXForward_ForMap(Map<String, Integer> xForward_ForMap) {
		XForward_ForMap = xForward_ForMap;
	}

	/**
	 * get X-Forwarded-For(user specified in text field) 
	 * @return X-Forwarded-For ip address
	 */
	public String getXForward_For() {
		return XForward_For;
	}

	/**
	 * set xForward_For
	 * @param xForward_For xForward_For ip address
	 */
	public void setXForward_For(String xForward_For) {
		XForward_For = xForward_For;
	}

	public String execute() {
		
		setErrorPage("/pages/SolrProxy.jsp");
		if (!Utility.validateInputDateOrder(from, to)) {
			return ERROR;
		}
		
			setDisplay("/pages/solrProxyAllResult.jsp");
		
			String input_start_UTC = Utility.convertDate2UTC(from);
			String input_end_UTC = Utility.convertDate2UTC(to);
	
			Solr4Connector connector = new Solr4Connector();
			SolrServer solr_server = connector.connect();
	
			SolrQuery query = new SolrQuery();
			String queryStr = "timeStamp:[" + input_start_UTC + " TO "
					+ input_end_UTC + "]"; // " AND " + "userName:" + userName;
			
			if(sourceIP != null){
				queryStr = queryStr +" AND " + "sourceIP:" + sourceIP;
			}
			if(XForward_For != null){
				queryStr = queryStr +" AND " + "XForward_For:" + XForward_For;
			}
			
			queryStr = queryStr + " AND " + "logName:" + "SOLR-PROXY-Log";
			query.setQuery(queryStr).setRows(0).setFacet(true)
					.set("facet.mincount", 1).set("facet.limit", 18)
					.set("facet.sort", "count").set("facet.date", "timeStamp")
					.set("f.timeStamp.facet.date.start", input_start_UTC)
					.set("f.timeStamp.facet.date.end", input_end_UTC)
					.set("f.timeStamp.facet.date.gap", "+1DAY")
					.set("f.timeStamp.facet.mincount", 0).set("facet.field", fieldName); 
	
			QueryResponse query_response = null;
	
			try {
				// logger.debug("solr_server == null is " + solr_server == null);
				query_response = solr_server.query(query);
	
			} catch (SolrServerException e) {
				// logger.debug("solr_server == null is " + solr_server == null);
				e.printStackTrace();
			}
	
			// ///////////////////generate charts/////////////////////////////////////////
			List<FacetField> facet_field_list = query_response.getFacetFields();
	
			for (int i = 0; i < facet_field_list.size(); i++) {
	
				FacetField facet_field = facet_field_list.get(i);
				String field_name = facet_field.getName();
				
				Map<String, Integer> map = Utility.getFacetFieldAsMap(facet_field);
	
				if(field_name.equals("date")){
					FacetField facet_date = query_response.getFacetDate("timeStamp"); 
					
					Map<String, Integer> UTC_detail_time_map = Utility.getFacetFieldAsMap(facet_date);
					Map<String, Integer> UTC_YMD_map = Utility.convertMapFromUTCDetail2UTCYMD(UTC_detail_time_map);
					
					setTimeIntervalMap(UTC_YMD_map);
					//setDateMap(map);
				}else if(field_name.equals("sourceIP")){
					setIpMap(map);
				}else if(field_name.equals("responseStatus")){
					setResponseStatusMap(map);
				}else if(field_name.equals("XForward_For")){
					setXForward_ForMap(map);
				}
			}
			connector.disconnect();
			return SUCCESS;
		}
	
	/*public Map<String, Integer> getDateMap() {
		return dateMap;
	}*/
	
	/**
	 * get display page
	 * @return the display jsp page
	 */
	public String getDisplay() {
		return display;
	}

	/**
	 * get field names that need to be facet searched
	 * @return field names in a array
	 */
	public String[] getFieldName() {
		return fieldName;
	}
	
	/**
	 * get start time
	 * @return start time
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * get ip map
	 * @return ip map that maps source IP to access count
	 */
	public Map<String, Integer> getIpMap() {
		return ipMap;
	}

	/**
	 * get response status map
	 * @return response status map that maps response status to access count
	 */
	public Map<String, Integer> getResponseStatusMap() {
		return responseStatusMap;
	}

	/**
	 * get source IP
	 * @return source IP
	 */
	public String getSourceIP() {
		return sourceIP;
	}

	/**
	 * get time interval map
	 * @return time interval map that maps each time interval to its access counts in that interval
	 */
	public Map<String, Integer> getTimeIntervalMap() {
		return timeIntervalMap;
	}

	/**
	 * get end time
	 * @return end time
	 */
	public String getTo() {
		return to;
	}

	
	/*public void setDateMap(Map<String, Integer> dateMap) {
		this.dateMap = dateMap;
	}*/

	/**
	 * set display page
	 * 
	 * @param display the display page
	 */
	public void setDisplay(String display) {
		this.display = display;
	}

	/**
	 * set field names to facet on
	 * 
	 * @param fieldName a array that contains filed names to facet on
	 */
	public void setFieldName(String[] fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * set start time
	 * @param from start time
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * set ip map
	 * 
	 * @param ipMap ip map that maps source IP to access count from this IP
	 */
	public void setIpMap(Map<String, Integer> ipMap) {
		this.ipMap = ipMap;
	}

	/**
	 * get response status map
	 * 
	 * @param responseStatusMap response status map that maps each response status to its corresponding access count 
	 */
	public void setResponseStatusMap(Map<String, Integer> responseStatusMap) {
		this.responseStatusMap = responseStatusMap;
	}

	/**
	 * set source IP
	 * 
	 * @param sourceIP source IP
	 */
	public void setSourceIP(String sourceIP) {
		this.sourceIP = sourceIP;
	}

	/**
	 * set time interval map 
	 * 
	 * @param timeIntervalMap time interval map that maps each time interval to the access count during this interval
	 */
	public void setTimeIntervalMap(Map<String, Integer> timeIntervalMap) {
		this.timeIntervalMap = timeIntervalMap;
	}

	/**
	 * set end time
	 * @param to end time
	 */
	public void setTo(String to) {
		this.to = to;
	}
	
	/**
	 * get error page for error handling
	 * @return error page
	 */
	public String getErrorPage() {
		return errorPage;
	}

	/**
	 * set error page for error handling
	 * @param errorPage error page
	 */
	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}

}
