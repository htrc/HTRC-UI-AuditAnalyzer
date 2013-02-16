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
# File:  HTRCSeriesChartDrawer.java
# Description: TODO
#
# -----------------------------------------------------------------
# 
*/
package edu.indiana.htrc.visual;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import edu.indiana.htrc.global.HTRCDateFormat;

public class HTRCSeriesChartDrawer implements HTRCChartDrawer{

	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"); // UTC without milliseconds format
	private Calendar cal = new GregorianCalendar();
	private Map<String, Integer> input_map;
	private String dataset_label;
	
	private String chart_name ;
	private String y_axis_label;
	private String x_axis_label;
	private boolean is3D = false; // series chart has no 3D option :)
	
	public String getDataset_label() {
		return dataset_label;
	}

	public void setDataset_label(String dataset_label) {
		this.dataset_label = dataset_label;
	}

	public String getY_axis_label() {
		return y_axis_label;
	}

	public void setY_axis_label(String y_axis_label) {
		this.y_axis_label = y_axis_label;
	}

	public String getX_axis_label() {
		return x_axis_label;
	}

	public void setX_axis_label(String x_axis_label) {
		this.x_axis_label = x_axis_label;
	}

	public String getChart_name() {
		return chart_name;
	}

	public void setChart_name(String chart_name) {
		this.chart_name = chart_name;
	}
	
	public Map<String, Integer> getInput_map() {
		return input_map;
	}

	public void setInput_map(Map<String, Integer> input_map) {
		this.input_map = input_map;
	}

	@Override
	public File draw() {
		
		Set<String> key_set = input_map.keySet();
		Iterator<String> iter = key_set.iterator();
		TimeSeries accessSeries = new TimeSeries(dataset_label, Day.class);
		while(iter.hasNext()){
			String dateStr = iter.next(); // yyyy-MM-dd
			int value = input_map.get(dateStr);
			
			Date date = null;
			try {
				date = format.parse(dateStr);
				System.out.println(date);
				
				cal.setTime(date);
				
				accessSeries.add(new Day(cal.get(Calendar.DATE),cal.get(Calendar.MONTH)+1,cal.get(Calendar.YEAR)), value);
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}
		
		TimeSeriesCollection series_dataset = new TimeSeriesCollection();
		
		series_dataset.addSeries(accessSeries);
		
		JFreeChart series_chart = ChartFactory.createTimeSeriesChart(chart_name, x_axis_label, y_axis_label, series_dataset, true, true, false);
		
		File img = new File("../webapps/HTRC-UI-AuditAnalyzer/images/" + System.currentTimeMillis() + ".jpg");
		
		try {
				ChartUtilities.saveChartAsJPEG(img, series_chart, 1400, 600);		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return img;
	}
}
