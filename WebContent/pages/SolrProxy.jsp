<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Solr Proxy API Analysis</title>
<link href="../pages/flot/calendar.css" rel="stylesheet" type="text/css">
 
<table class="ds_box" cellpadding="0" cellspacing="0" id="ds_conclass" style="display: none;">
<tr><td id="ds_calclass">
</td></tr>
</table>
<script language="javascript" type="text/javascript" src="../pages/flot/calendar.js"></script>
</head>
<body>
<table width="1895" border="1">
  <tr>
    <td width="405" height="537" align="center" valign="top"><h1>Solr Proxy API General Analysis
        </h1>
      <form name="f1" action="">
      <p>
          <label for="name">Please enter your interested time period:</label>
          <br/>
          <label for="from">Enter start time:</label>
       <!--  <input type="date" name="from"/> -->
         <input onclick="ds_sh(this);" name="from" readonly="readonly" style="cursor: text" />
        <br/>
        <label for="to">Enter end time:</label>
        <!-- <input type="date" name="to"/> -->
         <input onclick="ds_sh(this);" name="to" readonly="readonly" style="cursor: text" />
      </p>
<p>check the following boxes for your interested information:</p>
        <p>
          <input name="fieldName" type="checkbox" value="responseStatus">
          response status statistics        </p>
        <p>
          <input name="fieldName" type="checkbox" value="sourceIP">
          direct source IP access statistics</p>
        <p> <input name="fieldName" type="checkbox" value="XForward_For">XForward-For original IP access statistics</p>
        <p>
          <input name="fieldName" type="checkbox" value="date">
          access statistics by date </p>
        <p>
          <input type="button" value="view info" onClick="javascript:document.f1.action='solrProxyAPIGeneralInfo'; document.f1.submit();"/>
        </p>
    </form></td>
    <td width="498" align="center" valign="top"><h1>Solr Proxy API Source IP Analysis</h1>
      <form name="f3" action="">
        <p>
          <label for="name">Please enter the user source IP you want to monitor:</label>
          <input type="text" name="sourceIP"/>
        </p>
        <p> <br/>
          <label for="name">Please enter your interested time period:</label>
          <br/>
          <label for="from">Enter start time:</label>
         <!--  <input type="date" name="from"/> -->
          <input onclick="ds_sh(this);" name="from" readonly="readonly" style="cursor: text" />
          <br/>
          <label for="to">Enter end time:</label>
        <!--   <input type="date" name="to"/> -->
          <input onclick="ds_sh(this);" name="to" readonly="readonly" style="cursor: text" />
        </p>
        <p>check the following boxes for your interested information:</p>
        <p>
          <input name="fieldName" type="checkbox" value="date">
          access statistics</p>
       <!--  <p>
          <input name="fieldName" type="checkbox" value="userName">
          statistics of users who used this IP</p> -->
        <p>
          <input name="fieldName" type="checkbox" value="responseStatus">
          response status statistics</p>
        <p>
          <input type="button" value="view info" onClick="javascript:document.f3.action='SolrProxySourceIPInfo'; document.f3.submit();"/>
          <!-- SolrProxyAPIIPInfo.action -->
        </p>
    </form></td>
    <td width="455" align="center" valign="top"><h1>Solr Proxy API Real Time Access Monitor</h1>
      <p>&nbsp;</p>
      <p>&nbsp;</p>
      
       <form action="" name="f4">
        <input type="button" value="view realtime" onClick="javascript:document.f4.action='pass2SolrProxyRealTime'; document.f4.submit();"/>
       </form>  </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
<s:debug />
</body>
</html>