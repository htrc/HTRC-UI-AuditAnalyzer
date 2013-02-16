<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Data API Analysis</title>
</head>
<body>
<table width="1895" border="1">
  <tr>
    <td width="405" height="537" align="center" valign="top"><h1>Data API General Analysis
        </h1>
      <form name="f1" action="">
      <p>
          <label for="name">Please enter your interested time period:</label>
          <br/>
          <label for="from">Enter start time:</label>
        <input type="date" name="from"/>
        <br/>
        <label for="to">Enter end time:</label>
        <input type="date" name="to"/>
      </p>
<p>check the following boxes for your interested information:</p>
        <p>
          <input name="fieldName" type="checkbox" value="responseStatus">
          response status statistics</p>
        <p>
          <input name="fieldName" type="checkbox" value="userName">
          user access statistics</p>
        <p>
          <input name="fieldName" type="checkbox" value="sourceIP">
          source IP access statistics</p>
        <p>
          <input name="fieldName" type="checkbox" value="date">
          access statistics by date </p>
        <p>
          <input type="button" value="view info" onClick="javascript:document.f1.action='dataAPIGeneralInfo'; document.f1.submit();"/>
        </p>
    </form></td>
    <td width="509" align="center" valign="top"><h1>Data API User Behavior Analysis</h1>
      <form name="f2" action="">
        <p>
          <label for="name">Please enter the user name you want to monitor:</label>
          <input type="text" name="userName"/>
        </p>
        <p>          <br/>
          <label for="name">Please enter your interested time peroid:</label>
          <br/>
          <label for="from">Enter start time:</label>
          <input type="date" name="from"/>
          <br/>
          <label for="to">Enter end time:</label>
          <input type="date" name="to"/>
        </p>
<p>check the following boxes for your interested information:</p>
        <p>
          <input name="fieldName" type="checkbox" value="date">
          access statistics</p>
        <p>
          <input name="fieldName" type="checkbox" value="sourceIP">
          statistics of used IP by this particular user</p>
        <p>
          <input name="fieldName" type="checkbox" value="responseStatus">
          response status statistics</p>
        <p>
          <input type="button" value="view info" onClick="javascript:document.f2.action='dataAPIUserInfo'; document.f2.submit();"/>
        </p>
    </form></td>
    <td width="498" align="center" valign="top"><h1>Data API Source IP Analysis</h1>
      <form name="f3" action="">
        <p>
          <label for="name">Please enter the user source IP you want to monitor:</label>
          <input type="text" name="sourceIP"/>
        </p>
        <p> <br/>
          <label for="name">Please enter your interested time period:</label>
          <br/>
          <label for="from">Enter start time:</label>
          <input type="date" name="from"/>
          <br/>
          <label for="to">Enter end time:</label>
          <input type="date" name="to"/>
        </p>
        <p>check the following boxes for your interested information:</p>
        <p>
          <input name="fieldName" type="checkbox" value="date">
          access statistics</p>
        <p>
          <input name="fieldName" type="checkbox" value="userName">
          statistics of users who used this IP</p>
        <p>
          <input name="fieldName" type="checkbox" value="responseStatus">
          response status statistics</p>
        <p>
          <input type="button" value="view info" onClick="javascript:document.f3.action='dataAPIIPInfo'; document.f3.submit();"/>
        </p>
    </form></td>
    <td width="455" align="center" valign="top"><h1>Data API Real Time Access Monitor</h1>
      <p>&nbsp;</p>
      <p>&nbsp;</p>
       <form action="" name="f4">
         <input type="button" value="view realtime" onClick="javascript:document.f4.action='pass2DataAPIRealTime'; document.f4.submit();"/>
      </form>
    </td>
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