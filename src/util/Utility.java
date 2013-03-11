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
# File:  Utility.java
# Description: TODO
#
# -----------------------------------------------------------------
# 
*/
package util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;

import edu.indiana.htrc.global.HTRCDateFormat;

public class Utility {
	public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"); 
	public static Map<String, Integer> getGeneralCountInfo(File[] logs,
			int offset) {// offset indicates what info in this log line to
							// extract
		Map<String, Integer> error_count_map = new HashMap<String, Integer>();

		BufferedReader br = null;

		for (int i = 0; i < logs.length; i++) {
			try {
				br = new BufferedReader(new InputStreamReader(
						new FileInputStream(logs[i])));

				String line = null;

				while ((line = br.readLine()) != null) {
					// error_count_map.put(line.split("	")[4],
					// (error_count_map.get(line.split("	")[4]).intValue())++);
					String[] splits = line.split("	");
					String anomaly = null;
					if (splits.length >= offset) {
						anomaly = splits[offset];

						Integer count = error_count_map.get(anomaly);
						if (count == null) {
							error_count_map.put(anomaly, 1);
						} else {
							int c = count.intValue();
							error_count_map.put(anomaly, ++c);
						}
					}
				}

				br.close();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return error_count_map;
	}

	public static void printMap(Map<String, Integer> error_count_map,
			PrintWriter pw) {

		Set<String> keyset = error_count_map.keySet();

		java.util.Iterator<String> iter = keyset.iterator();

		while (iter.hasNext()) {
			String key = iter.next();
			pw.println(key + "=" + error_count_map.get(key));
			pw.flush();
		}
	}

	public static int countLineNum(File file) throws IOException {
	    InputStream is = new BufferedInputStream(new FileInputStream(file));
	    try {
	        byte[] c = new byte[1024];
	        int count = 0;
	        int readChars = 0;
	        boolean empty = true;
	        while ((readChars = is.read(c)) != -1) {
	            empty = false;
	            for (int i = 0; i < readChars; ++i) {
	                if (c[i] == '\n')
	                    ++count;
	            }
	        }
	        return (count == 0 && !empty) ? 1 : count;
	    } finally {
	        is.close();
	    }
	}

	public static String convertDate2UTC(String input_dateStr) {
		
		Date date = null;
		try {
			//System.out.println(input_dateStr);
			date = HTRCDateFormat.user_input_format.parse(input_dateStr);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		String utc_time = HTRCDateFormat.format_utc.format(date);
		
		return utc_time;
	}
	
	public static Map<String, Integer>getFacetFieldAsMap(FacetField facet_field){
	//	String field_name = facet_field.getName();
		List<Count> item = facet_field.getValues();
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		for (int j = 0; j < item.size(); j++) {
			String name = item.get(j).getName();
			long count = item.get(j).getCount();
			// System.out.println(name + " : " + count);
			map.put(name, (int) count);
		}
		
		return map;
	}

	public static Map<String, Integer> convertMapFromUTCDetail2UTCYMD(Map<String, Integer> UTC_detail_time_map) {
		
		Set<String> keyset = UTC_detail_time_map.keySet();
		Iterator<String> iter = keyset.iterator();
		Map<String, Integer> return_map = new LinkedHashMap<String, Integer>();
		while(iter.hasNext()){
			String key = iter.next();
			int value = UTC_detail_time_map.get(key);
			
			key = key.split("T")[0];
			
			return_map.put(key, value);
		}
		
		return return_map;
	}
	
	public static String exec(String line){
		
//String line = "wc -l " + "/home/hathitrust/solr/ToVM_Solr_related/test/apache-tomcat-6.0.35/bin/proxy_logs/logfile";
		
		CommandLine command = CommandLine.parse(line);
		
		DefaultExecutor executor = new DefaultExecutor();
		
		//int exitValue = executor.execute(command);
		
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        PumpStreamHandler pump_stream_handler = new PumpStreamHandler(stdout);
        
        executor.setStreamHandler(pump_stream_handler);
        
        int exitValue = 0;
		try {
			exitValue = executor.execute(command);
		} catch (ExecuteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
		// System.out.println(exitValue);
		
		// System.out.println(stdout.toString());
		
		return stdout.toString();
		
	}

	public static boolean validateInputDateOrder(String from, String to) {

		if(from.compareTo(to)>0){
			return false;
		}else
			return true;
	}
}
