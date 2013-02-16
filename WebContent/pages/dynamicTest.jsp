<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script>
/*function xxx(){

setInterval(function(){loadXMLDoc()},2000);

}

function loadXMLDoc()
{
var xmlhttp;
if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
  xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }

xmlhttp.onreadystatechange=function()
{
if (xmlhttp.readyState==4 && xmlhttp.status==200)
  {
	var tmp = xmlhttp.responseText ;
	document.getElementById("chart_div1").innerHTML=tmp;  //xmlhttp.responseText;
	//document.getElementById("chart_div2").innerHTML=<s:property value="#session.random" />;
	//document.getElementById("chart_div3").innerHTML=array.length;
	//document.getElementById("chart_div4").innerHTML=array;
	//document.getElementById("chart_div4").innerHTML=data;

	//var xyz=['', tmp];
	//drawChart(array.push(xyz));
	
	data.addRow(['', tmp]);
	chart.draw(data, options);
	//document.write("draw called!!");
    
   }
}
xmlhttp.open("GET","ajaxRealTime.action?",true);
xmlhttp.send();

}*/

</script>
 
</head>
<body>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">

      // Load the Visualization API and the piechart package.
      google.load('visualization', '1.0', {'packages':['corechart']});
      google.setOnLoadCallback(function(){drawChart(0)});
      // Set a callback to run when the Google Visualization API is loaded.
      var array = [
                   ['', 3],
                   ['', 1],
                   ['', 1],
                   ['', 1],
                   ['', 2]
                 ];
      
      
     /* $(document).ready(function(){
    	  $("#xsc").click(function(){
    	    $("#test").hide();
    	  });
    	});*/
    	$("button").click(function(){               

    	    $.ajax({
    	        url: "ajaxRealTime.action?",
    	        data: "",
    	        success: function(resultData){
    	            google.setOnLoadCallback(drawChart(resultData));                                                   
    	        }
    	    });     
    	});
     function drawChart(rate) {
     /* var rate = $.ajax({
          url: "ajaxRealTime.action?",
          //dataType:"",
          async: true
          }).responseText; */
          
      // Create our data table out of JSON data loaded from server.
      var item = ['', parseFloat(rate)];
      array.push(item);
      var data = new google.visualizatio.DataTable();
      data.addColumn('string', 'Time');
	  data.addColumn('number', 'Access_count');
	  data.addRows(array);
	  var options = { is3D:true,
				'title' : "realtime",
				'width' : 1500,
				'height' : 600,
				hAxis : {
					title : "access rate"
				},
				vAxis:{viewWindow: {min: 0}}
			};

      // Instantiate and draw our chart, passing in some options.
      var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
      chart.draw(data, options);
    }

        
</script>

<h2>AJAX</h2>
<!--  <button type="button" onclick="xxx()">dynamic go</button>  -->
<button type="button" id="xsc" >Request data</button> 
<br/>
<div id="target"></div>
<div id="chart_div"></div>
<div id="chart_div1"></div>
<div id="chart_div2"></div>
<div id="chart_div3"></div>
<div id="chart_div4"></div>
<s:debug/>
</body>
</html>