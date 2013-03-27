<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>HTRC Log Analysis</title>
</head>
<body>
 <h1>Welcome to HTRC Audit Analyzer</h1>
   <form action="analysis/chooseComponent">
     <p>
       <label for="name">Please choose your interested component:</label>
     </p>
     <p><br/>
       <select name="component">
         <option selected="selected" value="0">DATA_API</option>
         <option value="1">SOLR_PROXY</option>
         <option value="2">AGENT</option>
         <option value="3">OAUTH2</option>
       </select>
       <input type="submit" value="Submit">
     </p>
 </form>
 <%-- <s:debug /> --%>
</body>
</html>