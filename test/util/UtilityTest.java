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
# Project: SF-HTRC-UI-AuditAnalyzer-trunk
# File:  UtilityTest.java
# Description: TODO
#
# -----------------------------------------------------------------
# 
*/
package util;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class UtilityTest {

	@Test
	public void testCountLineNum() {
		/* actual countLineNum() returns the same result of command "wc -l filename"
		 * becaue countLineNum() also counts the newLine character.  
		 */
		File configFile = new File("config.properties");
		
		try {
			int lineNum = Utility.countLineNum(configFile);
			
			assertEquals(4, lineNum);// config file provided has 4 lines
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testConvertDate2UTC() {
		
		String input_dateStr = "2013-03-01";
		
		String utcStr = Utility.convertDate2UTC(input_dateStr);
		
		assertTrue(utcStr.startsWith(input_dateStr+"T"));
		assertTrue(utcStr.endsWith("Z"));
	}

}
