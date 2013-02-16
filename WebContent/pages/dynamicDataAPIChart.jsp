<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Flot Examples</title>
    <link href="../pages/flot/layout.css" rel="stylesheet" type="text/css">
    <!--[if lte IE 8]><script language="javascript" type="text/javascript" src="../excanvas.min.js"></script><![endif]-->
    <script language="javascript" type="text/javascript" src="../pages/flot/jquery.js"></script>
    <script language="javascript" type="text/javascript" src="../pages/flot/jquery.flot.js"></script>
 </head>
    <body>
    <h1>Data API Realtime Usage</h1>

    <div id="placeholder" style="width:600px;height:300px;"></div>

    <p>Data API Realtime Usage Monitor</p>

    <p>Time between updates: <input id="updateInterval"  type="text" value="" style="text-align: right; width:5em"> milliseconds</p>
	
	<!--  ///////
	y: <div id="y"></div>
	data: <div id="data"></div>
	res: <div id="res"></div>
	newValue: <div id="newValue"></div>
	data length: <div id="data_length"></div>
	res length: <div id="res_length"></div> -->
<script type="text/javascript">
$(function () {
    // we use an inline data source in the example, usually data would
    // be fetched from a server
    var data = [], totalPoints = 6;
   
    function loadNewData()
    {
    	var newValue;
    var xmlhttp;
    if (window.XMLHttpRequest)
      {// code for IE7+, Firefox, Chrome, Opera, Safari
      xmlhttp=new XMLHttpRequest();
      }
    else
      {// code for IE6, IE5
      xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
      }
   /* xmlhttp.onreadystatechange=function()
      {
      if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {
    	  newValue = xmlhttp.responseText;
        document.getElementById("newValue").innerHTML=newValue;
       		return newValue;
        }
      } */
    xmlhttp.open("GET","dataAPIRealTime.action?",false);
    xmlhttp.send();
    
    newValue = xmlhttp.responseText;
   // document.getElementById("newValue").innerHTML=newValue;
   		return newValue;
    }
    
    function getRealtimeData() {
        if (data.length > 0)
            data = data.slice(1);

      //  var y = loadNewData();
        
      //  data.push(y);
        
       
        
        // do a random walk
       while (data.length < totalPoints) {
           // var prev = data.length > 0 ? data[data.length - 1] : 50;
           // var y = prev + Math.random() * 10 - 5;
           var y = loadNewData();
            if (y < 0)
                y = 0;
            if (y > 6)
                y = 6;
            data.push(y);
            
       //     document.getElementById("y").innerHTML=y;
       //     document.getElementById("data").innerHTML=data;
        } 

        // zip the generated y values with the x values
        var res = [];
       
   //     document.getElementById("data_length").innerHTML=data.length;
        for (var i = 0; i < data.length; ++i)
            res.push([i, data[i]]);
            
     //        document.getElementById("res").innerHTML=res;
   //     document.getElementById("res_length").innerHTML=data.length;
        return res;
    }

    // setup control widget
    var updateInterval = 1000;
    $("#updateInterval").val(updateInterval).change(function () {
        var v = $(this).val();
        if (v && !isNaN(+v)) {
            updateInterval = +v;
            if (updateInterval < 1000)
                updateInterval = 2000;
            if (updateInterval > 6000)
                updateInterval = 6000;
            $(this).val("" + updateInterval);
        }
    });

    // setup plot
    var options = {
        series: { shadowSize: 0 }, // drawing is faster without shadows
        yaxis: { min: 0, max: 30 },
        xaxis: { show: false }
    };
    var plot = $.plot($("#placeholder"), [ getRealtimeData() ], options);

    function update() {
        plot.setData([ getRealtimeData() ]);
        // since the axes don't change, we don't need to call plot.setupGrid()
        plot.draw();
        
        setTimeout(update, updateInterval);
    }

    update();
});
</script>

 </body>
</html>
