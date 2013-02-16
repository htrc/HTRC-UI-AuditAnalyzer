<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<script>
function xxx(){

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
	  var xyz = xmlhttp.responseText;
    document.getElementById("chart_div1").innerHTML=xyz;
    }
  }
xmlhttp.open("GET","ajaxRealTime.action?",true);
xmlhttp.send();
}
</script>

<h2>AJAX</h2>
<button type="button" onclick="xxx()">Request data</button>
<br/>
<div id="chart_div1"></div>
<div id="chart_div2"></div>
<s:debug/>
</body>
</html>