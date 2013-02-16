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
# File:  HTRCBarChartDrawer.java
# Description: TODO
#
# -----------------------------------------------------------------
# 
*/
package edu.indiana.htrc.visual;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

public class HTRCBarChartDrawer implements HTRCChartDrawer{

	private Map<String, Integer> input_map; // this map contains the dataset
	// make it into a list of maps so that multiple datasets can be displayed in the same chart
	private String dataset_label; // the name of this dataset, make it to a list also. 
	
	private String y_axis_label;
	private String x_axis_label;
	
	private String chart_name;
	
	//private String image_path;
	
	private boolean is3D = true;
	
	/*public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}*/
	
	public String getDataset_label() {
		return dataset_label;
	}

	public void setDataset_label(String dataset_label) {
		this.dataset_label = dataset_label;
	}
	
	public Map getInput_map() {
		return input_map;
	}

	public void setInput_map(Map input_map) {
		this.input_map = input_map;
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

	public boolean isIs3D() {
		return is3D;
	}

	public void setIs3D(boolean is3d) {
		is3D = is3d;
	}
	
	@Override
	public File draw() {
		
		System.out.println("draw bar!!!!!!!!!!!!!");
		
		DefaultCategoryDataset bar_dataset = new DefaultCategoryDataset();
		/*dataset.setValue(6, "Profit", "Jane");
		dataset.setValue(7, "Profit", "Tom");
		dataset.setValue(8, "Profit", "Jill");
		dataset.setValue(5, "Profit", "John");
		dataset.setValue(12, "Profit", "Fred");*/
		Set<String> key_set = input_map.keySet();
		Iterator<String> iter = key_set.iterator();
		
		while(iter.hasNext()){
			String key = iter.next();
			int value = input_map.get(key);
			bar_dataset.setValue(value, dataset_label, key);
		}
		
		JFreeChart chart = null;
		if(is3D){
			 chart = ChartFactory.createBarChart3D(
					chart_name, x_axis_label, y_axis_label, bar_dataset,
					PlotOrientation.VERTICAL, true, true, false);
		}else{
			 chart = ChartFactory.createBarChart(
					chart_name, x_axis_label, y_axis_label, bar_dataset,
					PlotOrientation.VERTICAL, true, true, false);
		}
		
		CategoryPlot p = chart.getCategoryPlot(); 
		/*  NumberAxis rangeAxis = (NumberAxis) p.getRangeAxis();
		  rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());*/
		  BarRenderer renderer = (BarRenderer) p.getRenderer();
		  DecimalFormat decimalformat1 = new DecimalFormat("##");
		  
		  StandardCategoryItemLabelGenerator label_generator = new StandardCategoryItemLabelGenerator("{2}", decimalformat1);
		  
		  renderer.setItemLabelGenerator(label_generator);
		 
		  final ItemLabelPosition pos = new ItemLabelPosition(
		            ItemLabelAnchor.OUTSIDE1, TextAnchor.CENTER_RIGHT, 
		            TextAnchor.CENTER_RIGHT, 0/* -Math.PI / 2.0*/
		        );
		 renderer.setPositiveItemLabelPosition(pos);
		 final CategoryAxis domainAxis = p.getDomainAxis();
	     domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
	     renderer.setMaximumBarWidth(.15); 
		  renderer.setItemLabelsVisible(true);
		  chart.getCategoryPlot().setRenderer(renderer);
		File img = new File("../webapps/HTRC-UI-AuditAnalyzer/images/" + System.currentTimeMillis() + ".jpg");
		
		try {
			ChartUtilities.saveChartAsJPEG(img, chart,
					1400, 600);
		} catch (IOException e) {
			System.err.println("Problem occurred creating chart.");
		}
		return img;
	}

}
