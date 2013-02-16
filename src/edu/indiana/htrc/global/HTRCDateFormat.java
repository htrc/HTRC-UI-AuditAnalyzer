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
# File:  HTRCDateFormat.java
# Description: TODO
#
# -----------------------------------------------------------------
# 
*/
package edu.indiana.htrc.global;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class HTRCDateFormat {
	
	//public static final DateFormat user_input_format = new SimpleDateFormat("MM/dd/yyyy");
	
	public static final DateFormat user_input_format = new SimpleDateFormat("yyyy-MM-dd");

	public static final DateFormat dataAPI_format = new SimpleDateFormat("dd MMM yyyy HH:mm:ss,SSS");
	
	public static final SimpleDateFormat format_utc = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"); // timeStamp in Solr4
	
	static {
		dataAPI_format.setTimeZone(TimeZone.getTimeZone( "GMT" ));
		format_utc.setTimeZone(TimeZone.getTimeZone("UTC"));
	}
}
