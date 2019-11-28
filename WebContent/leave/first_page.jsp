
<HTML>
<HEAD>
<TITLE> New Document </TITLE>
</HEAD>
<%
          String session_check_username=(String)session.getAttribute("login_name1");
          String session_check_password=(String)session.getAttribute("login_password1");
    if(session_check_username==null ||  session_check_password==null)
      {%>
      
	  <script>alert('Session is completed  \n Please Login again')</script>
      <script>window.navigate('http://localhost:8080//LeaveApplication/leave/loginpage_app.jsp')</script>
	  <%
	  }
	 %>
<BODY bgcolor="#FFFFFF"   width="600" height="600">
 


<center><p><h1><FONT COLOR="#FFFFFF"></h1></p></center>
</BODY>
</HTML>
