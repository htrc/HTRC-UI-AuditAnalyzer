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
# File:  DataAPIGeneralAction.java
# Description: TODO
#
# -----------------------------------------------------------------
# 
*/
package edu.indiana.htrc.action.auditlog;

import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.struts2.interceptor.SessionAware;

import util.Solr4Connector;
import util.Utility;

import com.opensymphony.xwork2.ActionSupport;

// This action is for Data API static analysis statistic of the user-entered timeframe
// DataAPIsourceIPAnalysisAction, DataAPIUserAnalysisAction and DataAPIGeneralAnalysisAction all extend this class.
public class DataAPIGeneralAction extends ActionSupport implements SessionAware{
	private Map<String, Object> session;
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
	Map<String, Integer> userMap;
	protected String userName;
	
	public String execute() {
		if (session.get("testsession") != null) {
			int x = (Integer) session.get("testsession");
			session.put("testsession", ++x);
		}

		setErrorPage("/pages/DataAPI.jsp");
		if (!Utility.validateInputDateOrder(from, to)) {
			return ERROR;
		}

		setDisplay("/pages/dataAPIAllResult.jsp");
		String input_start_UTC = Utility.convertDate2UTC(from);
		String input_end_UTC = Utility.convertDate2UTC(to);

		Solr4Connector connector = new Solr4Connector();
		SolrServer solr_server = connector.connect();

		SolrQuery query = new SolrQuery();
		String queryStr = "timeStamp:[" + input_start_UTC + " TO "
				+ input_end_UTC + "]"; // " AND " + "userName:" + userName;
		if (userName != null) {
			queryStr = queryStr + " AND " + "userName:" + userName;
		}
		if (sourceIP != null) {
			queryStr = queryStr + " AND " + "sourceIP:" + sourceIP;
		}

		queryStr = queryStr + " AND " + "logName:" + "AUDIT-Log";
		query.setQuery(queryStr).setRows(0).setFacet(true)
				.set("facet.mincount", 1).set("facet.limit", 18)
				.set("facet.sort", "count").set("facet.date", "timeStamp")
				.set("f.timeStamp.facet.date.start", input_start_UTC)
				.set("f.timeStamp.facet.date.end", input_end_UTC)
				.set("f.timeStamp.facet.date.gap", "+1DAY")
				.set("f.timeStamp.facet.mincount", 0)
				.set("facet.field", fieldName);

		QueryResponse query_response = null;

		try {
			// logger.debug("solr_server == null is " + solr_server == null);
			query_response = solr_server.query(query);

		} catch (SolrServerException e) {
			// logger.debug("solr_server == null is " + solr_server == null);
			e.printStackTrace();
		}

		// ///////////////////generate
		// charts/////////////////////////////////////////
		List<FacetField> facet_field_list = query_response.getFacetFields();

		for (int i = 0; i < facet_field_list.size(); i++) {

			FacetField facet_field = facet_field_list.get(i);
			String field_name = facet_field.getName();

			Map<String, Integer> map = Utility.getFacetFieldAsMap(facet_field);

			if (field_name.equals("date")) {
				FacetField facet_date = query_response
						.getFacetDate("timeStamp");

				Map<String, Integer> UTC_detail_time_map = Utility
						.getFacetFieldAsMap(facet_date);
				Map<String, Integer> UTC_YMD_map = Utility
						.convertMapFromUTCDetail2UTCYMD(UTC_detail_time_map);

				setTimeIntervalMap(UTC_YMD_map);
				// setDateMap(map);
			} else if (field_name.equals("sourceIP")) {
				setIpMap(map);
			} else if (field_name.equals("responseStatus")) {
				setResponseStatusMap(map);
			} else if (field_name.equals("userName")) {
				setUserMap(map);
			}
		}
		connector.disconnect();
		return SUCCESS;
	}
	
	/*public Map<String, Integer> getDateMap() {
		return dateMap;
	}*/
	/**
	 * 
	 * @return display page
	 */
	public String getDisplay() {
		return display;
	}

	/**
	 * get all field names from check list
	 * @return field names as an array
	 */
	public String[] getFieldName() {
		return fieldName;
	}
	
	/**
	 * get start date string
	 * @return start date string
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * get source IP to access count map 
	 * @return source IP to access count map 
	 */
	public Map<String, Integer> getIpMap() {
		return ipMap;
	}

	/**
	 * 
	 * @return map that maps response status to access count
	 */
	public Map<String, Integer> getResponseStatusMap() {
		return responseStatusMap;
	}

	/**
	 * 
	 * @return source IP
	 */
	public String getSourceIP() {
		return sourceIP;
	}

	/**
	 * 
	 * @return timeIntervalMap that maps time intervals to access counts
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

	/**
	 * get user map
	 * @return user map that maps user names to access counts
	 */
	public Map<String, Integer> getUserMap() {
		return userMap;
	}

	/**
	 *  
	 * @return username
	 */
	public String getUserName() {
		return userName;
	}

	/*public void setDateMap(Map<String, Integer> dateMap) {
		this.dateMap = dateMap;
	}*/

	/**
	 * set display jsp page
	 * @param display the display jsp page
	 */
	public void setDisplay(String display) {
		this.display = display;
	}

	/**
	 * set field names to fieldName array to facet query solr4
	 * @param fieldName the array that contains all wanted field names
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
	 * set ipMap
	 * 
	 * @param ipMap that maps source IP to access count
	 */
	public void setIpMap(Map<String, Integer> ipMap) {
		this.ipMap = ipMap;
	}

	/**
	 * set responseStatusMap
	 * @param responseStatusMap that maps response status to access count
	 */
	public void setResponseStatusMap(Map<String, Integer> responseStatusMap) {
		this.responseStatusMap = responseStatusMap;
	}

	/**
	 * set source IP 
	 * @param sourceIP where the request come from
	 */
	public void setSourceIP(String sourceIP) {
		this.sourceIP = sourceIP;
	}

	/**
	 * set timeIntervalMap
	 * @param timeIntervalMap that maps time intervals to access counts
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
	 * set user map
	 * @param userMap that maps user name to access count
	 */
	public void setUserMap(Map<String, Integer> userMap) {
		this.userMap = userMap;
	}

	/**
	 * set user name
	 * @param userName user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * get jsp page for error handling
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
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
		
	}
}
