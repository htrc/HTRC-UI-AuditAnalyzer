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
# File:  Solr4Connector.java
# Description: TODO
#
# -----------------------------------------------------------------
# 
*/
package util;

import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

public class Solr4Connector {

	private SolrServer solr_server;

	public SolrServer connect(){
				//server.set
		
		try
		{
			solr_server = new HttpSolrServer(ConfigManager.getProperty("SOLR4_EPR"));// make this configurable
		}catch(Exception e)
		{
			 e.printStackTrace();
		}
		
		return solr_server;
		
	/*	SolrQuery query = new SolrQuery();
		//String term_q = "timeStamp:[2012-09-07T01:20:37Z TO 2012-11-07T01:20:37.427Z]";
		String term_q = "*:*";
		query.setQuery(term_q );
		//query.setQueryType("sharding");
		//query.setRows(224918);
		//query.setRows(24918);
		//query.setStart(224918);
		query.setRows(50000);
		query.setFields("id");
		query.setFacet(b)
		
		try
		{
			QueryResponse query_response = solr_server.query(query);
			
			SolrDocumentList solr_doc_list = query_response.getResults();
			
			System.out.println(solr_doc_list.getNumFound()+ "hits found !!");
			
			Iterator<SolrDocument> iterator = solr_doc_list.iterator();
			
			while(iterator.hasNext())
			{
				SolrDocument temp_solr_doc = (SolrDocument)iterator.next();
				
				pw.println(temp_solr_doc.getFieldValue("id"));
				pw.flush();
				System.out.println(temp_solr_doc.getFieldValue("author"));
				System.out.println(temp_solr_doc.getFieldValue("title"));
				System.out.println(temp_solr_doc.getFieldValue("id"));
				System.out.println(temp_solr_doc.getFieldValue("rights"));
				System.out.println(temp_solr_doc.getFieldValue("date"));
				
				System.out.println("--------------------------------");
				
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}		*/
	}
	
	public void disconnect(){
		solr_server.shutdown();
	}

	
}
