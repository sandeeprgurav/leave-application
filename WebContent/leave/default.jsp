<%@page errorPage="ErrorHandler.jsp"%>
<%@ page language="java"%>
<%@ page import="java.sql.*,java.io.*,java.util.*,java.text.*,com.leave.*"%>
<%@page import="java.io.*,java.sql.*"%>
<%@page import="com.leave.LeaveBean"%>
<%@page import="com.leave.sendMailFile1"%>
<%@page import="java.util.Vector"%>
<%
	String session_check_username = (String) session.getAttribute("login_name1");
	String session_check_password = (String) session.getAttribute("login_password1");
	if (session_check_username == null || session_check_password == null) {
%>

<script>
	alert('Session is completed  \n Please Login again')
</script>
<script>
	window.navigate('http://localhost:8080/LeaveApplication/leave/loginpage_app.jsp')
</script>
<%
	}
%>
<html>
<head>
<title>LEAVE APPLICATION</title>
</head>
<%
	String mode_value = null;
	mode_value = request.getParameter("mode2") == null ? "zero" : request.getParameter("mode2");
	System.out.println("Mode in defualt page===>>" + mode_value);
	String path = null;

	if (session_check_username != null || session_check_password != null) {
		LeaveBean beanValues = (LeaveBean) session.getAttribute("foundData1");
	}

	if (mode_value.equalsIgnoreCase("first")) {
		path = "../leave/leaveapp.jsp";
	}

	if (mode_value.equalsIgnoreCase("second")) {
		path = "../leave/leaveapp1.jsp";
	}

	if (mode_value.equalsIgnoreCase("third")) {
		path = "../leave/leaveapp2.jsp";
	}

	if (mode_value.equalsIgnoreCase("fourth")) {
		path = "../leave/leaveapp3.jsp";
	}

	if (mode_value.equalsIgnoreCase("zero")) {
		path = "../leave/first_page.jsp";
	}
%>
<input type="hidden" name="mode">
<frameset rows="80,*" framespacing="0" border="0" frameborder="0">
	<frame name="banner" noresize src="../leave/top_page.jsp">
	<frameset cols="220,*">
		<frame name="left" noresize src="../leave/left_page.jsp">
		<frame name="content" src="<%=path%>">
	</frameset>
	<noframes>
		<body>
			<p>This page uses frames, but your browser doesn't support them.</p>
		</body>
	</noframes>
</frameset>
</html>
