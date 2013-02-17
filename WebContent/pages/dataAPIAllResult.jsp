<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!--Load the AJAX API-->
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">

      // Load the Visualization API and the piechart package.
      google.load('visualization', '1.0', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      
      var time_series_data = [
          /* ['Mushrooms', 3],
          ['Onions', 1],
          ['Olives', 1],
          ['Zucchini', 1],
          ['Pepperoni', 2] */
          <s:iterator value="timeIntervalMap">
     		 ['<s:property value="key"/>',<s:property value="value"/>],
     	  </s:iterator>
        ];
      
      var daily_access_data = [
                              <s:iterator value="dateMap">
                         		 ['<s:property value="key"/>',<s:property value="value"/>],
                         	  </s:iterator>
                            ];
      var ip_access_data = [
                              <s:iterator value="ipMap">
                         		 ['<s:property value="key"/>',<s:property value="value"/>],
                         	  </s:iterator>
                            ];
      var user_data =[
                                 <s:iterator value="userMap">
                         		 ['<s:property value="key"/>',<s:property value="value"/>],
                         	  </s:iterator>
                            ];
      var response_status_data =[
                                   <s:iterator value="responseStatusMap">
                           		 ['<s:property value="key"/>',<s:property value="value"/>],
                           	  </s:iterator>
                              ];
      
      var data_array = new Array();
      
      data_array[0]=time_series_data;
      data_array[1]=daily_access_data;
      data_array[2]=ip_access_data;
      data_array[3]=user_data;
      data_array[4]=response_status_data;
      
  /*    var i = 0;
      for(; i<data_array.length; i++){
    	//  document.write(i + "..............."+ "<br/>");
    	  if(data_array[i].length !=0 ){
    		  
    		// document.write(data_array[i].length+ "<br/>");
    		  google.setOnLoadCallback(function(){drawColumnChart(i, "COLUMN", data_array[i]);});
    	  }
      }
    */  
 
	if (data_array[0].length != 0) {
		google.setOnLoadCallback(function() {
			drawColumnChart(1, "LINE", data_array[0], "Zulu Time Series");
		});
	}
/*	if (data_array[1].length != 0) {
		google.setOnLoadCallback(function() {
			drawColumnChart(2, "COLUMN", data_array[1], "DATE");
		});
	} 
*/
	if (data_array[2].length != 0) {
		google.setOnLoadCallback(function() {
			drawColumnChart(2, "COLUMN", data_array[2], "SOURCE_IP");
		});
	}
	if (data_array[3].length != 0) {
		google.setOnLoadCallback(function() {
			drawColumnChart(3, "COLUMN", data_array[3], "USER_NAME");
		});
	}
	if (data_array[4].length != 0) {
		google.setOnLoadCallback(function() {
			drawColumnChart(4, "COLUMN", data_array[4], "RESPONSE_STATUS");
		});
	}

	// Callback that creates and populates a data table,
	// instantiates the pie chart, passes in the data and
	// draws it.
	function drawColumnChart(id, chartType, dataSet, info) {
		//document.write(id + "here");
		//  document.write(var1 + "</br>");
		// Create the data table.
		var data = new google.visualization.DataTable();
		data.addColumn('string', 'Time');
		data.addColumn('number', 'Request Count');
		data.addRows(dataSet);

		
		var chartTitle;
		if(info == "Zulu Time Series"){
			chartTitle='Data API Daily Request Count Information from <s:property value="from"/> to <s:property value="to"/>';
		}else if(info == "SOURCE_IP"){
			chartTitle = 'Data API Request Count Information for Source IPs from <s:property value="from"/> to <s:property value="to"/>';
		}else if(info=="USER_NAME"){
			chartTitle = 'Data API Request Count Information for Users from <s:property value="from"/> to <s:property value="to"/>';
		}else if(info == "RESPONSE_STATUS"){
			chartTitle='Response Status information for Data API Requests from <s:property value="from"/> to <s:property value="to"/>';
		}
		
		// Set chart options
		var options = { is3D:true,
			'title' : chartTitle /*info+' ACCESS INFO of <s:property value="userName"/><s:property value="sourceIP"/> from <s:property value="from"/> to <s:property value="to"/>'*/,
			'width' : 1500,
			'height' : 600,
			'titleTextStyle':{
				color: 'black', fontName:'Arial', fontSize: 26
			},
			hAxis : {
				title : info
			},
			vAxis:{
				viewWindow: {min: 0},
				title : "Data API Request Count",
				'titleTextStyle':{
					color: 'black', fontName:'Arial', fontSize: 24
				}
			}
		};
		// Instantiate and draw our chart, passing in some options.
		var chart;
		if (chartType == "COLUMN") {
			chart = new google.visualization.ColumnChart(document
					.getElementById('chart_div' + id));
		}else if(chartType == "LINE"){
			chart = new google.visualization.LineChart(document
					.getElementById('chart_div' + id));
		}
		chart.draw(data, options);
	}
</script>
</head>

<body>
	<!--Div that will hold the pie chart-->
	<div id="chart_div1"></div>
	<div id="chart_div2"></div>
	<div id="chart_div3"></div>
	<div id="chart_div4"></div>
	
	<a href="http://localhost:8080/HTRC-UI-AuditAnalyzer/">go back</a>
	<s:debug />
</body>
</html>