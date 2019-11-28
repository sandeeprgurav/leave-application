<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" isErrorPage="true"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script language="javascript">

</script>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="pageStyle.css" rel="stylesheet" type="text/css">
</head>
<body>
<table width="100%"  border="0" cellspacing="3" cellpadding="2">
  <tr>
    <th class="subTitle" scope="col">Error Occurred :<%=exception.getMessage()%></th>
    	    
  </tr>
<%

StackTraceElement eles[]=exception.getStackTrace();
StackTraceElement ele=null;
if (exception instanceof NullPointerException)
	{
	for(int i=0;i<eles.length;i++)
	{
	ele=eles[i];
	%>
     <tr>
	<td class="others style2">
	File:<%=ele.getFileName()%></td>
	</tr>
	<tr>
	<td class="others style2">
	Class:<%=ele.getClassName()%></td>
	</tr>
	<tr>
	<td class="others style2">
	Method:<%=ele.getMethodName()%></td>
	</tr>
	<tr>
	<td class="others style2">
	Line No:<%=ele.getLineNumber()%></td>
	</tr>
	
		<%
		}
	}
else if (exception  instanceof SQLException )	
	{
	for(int i=0;i<eles.length;i++)
	{
	ele=eles[i];
	%>
	<tr>
		<td class="others style2">
		File:<%=ele.getFileName()%></td>
  </tr>
		<tr>
		<td class="others style2">
		Class:<%=ele.getClassName()%></td>
		</tr>
		<tr>
		<td class="others style2">
		Method:<%=ele.getMethodName()%></td>
		</tr>
		<tr>
		<td class="others style2">
		Line No:<%=ele.getLineNumber()%></td>
	</tr>
		
	<%
		}
	}
	 
	else 
		{
		for(int i=0;i<eles.length;i++)
		{
		ele=eles[i];
		%>
		
		
  <tr>
  <tr>
		<td class="others style2">
		File:<%=ele.getFileName()%> <%=exception.getMessage()%></td>
  </tr>
		<tr>
		<td class="others style2">
		Class:<%=ele.getClassName()%></td>
		</tr>
		<tr>
		<td class="others style2">
		Method:<%=ele.getMethodName()%></td>
		</tr>
		<tr>
		<td class="others style2">
		Line No:<%=ele.getLineNumber()%></td>
	</tr>
	<%
		}
	}
%>
<tr>
	  <td class="others"><input name="Button" type="button" class="buttonStyle" value="Logout" onClick="window.navigate('<%=(String)application.getAttribute("LogOff")%>')"></td>
	  
  </tr>
</table>
</body>
</html>
