<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>HTRC DataAPI User Information</title>
</head>
<body>
	<%-- <br />
	<img src=<s:property value="imgPaths[0]"/> />
	<br />
	<img src=<s:property value="imgPaths[1]"/> />
	<br />
	<img src=<s:property value="imgPaths[2]"/> />
	<br />
	<img src=<s:property value="imgPaths[3]"/> />
	<br /> 
 	
   <s:property value="fieldName.length"/><br /> 
   1
   <s:property value="imgPaths.length"/>
   
   <s:if test="fieldName.length<3">less than 3</s:if>
   <s:else>good</s:else>
   
   <s:iterator value="{1,2,3,4,5,6}">
   		<s:property/> |
   </s:iterator> --%>
   
   <s:iterator value="imgPaths">
   		<img src=<s:property/> /> <br/>
   		<br />
   </s:iterator>
   
	<s:debug />
</body>
</html>