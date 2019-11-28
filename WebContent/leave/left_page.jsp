<%@page errorPage="ErrorHandler.jsp"%>
<%@ page language="java"%>
<%@ page
	import="java.sql.*,java.io.*,java.util.*,java.text.*,com.leave.*"%>
<%@page import="java.io.*,java.sql.*"%>
<%@page import="com.leave.LeaveBean"%>
<%@page import="com.leave.sendMailFile1"%>
<%@page import="java.util.Vector"%>
<HTML>
<HEAD>
<TITLE>New Document</TITLE>
</HEAD>

<BODY bgcolor="#032C95">
	<center>
		<br> <br> <br>
		<%
			String session_check_username = (String) session.getAttribute("login_name1");
			String session_check_password = (String) session.getAttribute("login_password1");
			if (session_check_username == null || session_check_password == null) {
		%>

		<script>
			alert('Session is completed  \n Please Login again')
		</script>
		<script>
			window
					.navigate('http://192.168.20.91:8081/LeaveApplication/leave/loginpage_app.jsp')
		</script>
		<%
			}
		%>
		<%
			LeaveBean foundData1 = null;

			foundData1 = new LeaveBean();
		%>
		<table border="0" width="100%">
			<tr style="cursor: hand">
				<td>
					<p>
						<a href="default.jsp?mode2=first " target="_top"><FONT
							COLOR="#FFFF33">APPLICATION SCREEN</a>
					</p> <br> <br>
				</td>
			</tr>
			<tr style="cursor: hand">
				<td>
					<p>
						<a href="default.jsp?mode2=second&foundData1="
							+beanvalues+" " target="_top"><FONT COLOR="#FFFF33">APPROVE_SCREEN</a>
					</p> <br> <br>
				</td>
			</tr>
			<tr style="cursor: hand">
				<td>
					<p>
						<a href="default.jsp?mode2=third" target="_top"><FONT
							COLOR="#FFFF33">LEAVE_DETAIL_SCREEN</a>
					</p> <br> <br>
				</td>
			</tr>
			<tr style="cursor: hand">
				<td>
					<p>
						<a href="default.jsp?mode2=fourth" target="_top"><FONT
							COLOR="#FFFF33">LEAVE_SUMMERY_SCREEN</a>
					</p> <br> <br>
				</td>
			</tr>
			<tr style="cursor: hand">
				<td>
					<p>
						<a href="../loginpage_app.jsp?mode2=fourth" target="_top"><FONT
							COLOR="#FFFF33">LOGOUT</a>
					</p>
				</td>
			</tr>
		</table>
		<input type="hidden" name="mode2">
	</center>

</BODY>
</HTML>
