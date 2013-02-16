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
# File:  HTRCLogConfig.java
# Description: TODO
#
# -----------------------------------------------------------------
# 
*/
package edu.indiana.htrc.global;

import util.ConfigManager;

public class HTRCLogConfig {

	public static final String AUDIT_LOG_HOME; //"/hathitrustmnt/silvermaple/AUDIT-LOGS/";
	public static final String SOLR_PROXY_LOG_HOME; //="/home/hathitrust/solr/ToVM_Solr_related/test/apache-tomcat-6.0.35/bin/proxy_logs/";
	
	static{
		AUDIT_LOG_HOME = ConfigManager.getProperty("AUDIT_LOG_HOME");
		SOLR_PROXY_LOG_HOME = ConfigManager.getProperty("SOLR_PROXY_LOG_HOME");
	}
}
