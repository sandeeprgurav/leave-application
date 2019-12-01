<%@page import="java.io.*,java.sql.*"%>
<%@page import=" java.util.Hashtable"%>
<%@page import="java.util.Vector"%>
<%@page import="com.leave.LeaveBean"%>
<script type="text/javascript" language="javascript" src="js/common.js"></script>
<script language="javascript">
	var lgexp = /^[a-z][a-z_0-9\.]*$/i;
	var space = /^[\s]*$/;

	function submitPage() {
		if (window.event.keyCode == 13) {
			submitvalidate();
		}
	}

	function submitvalidate() {
		if (document.loginform.login_name.value == "") {
			alert("Please Enter Name");
			document.loginform.login_name.focus();
		} else if (document.loginform.password.value == "") {
			alert("Please Enter password");
			document.loginform.password.focus();
		} if (document.loginform.login_name.value != ""
				&& document.loginform.password.value != "") {
			document.loginform.mode.value = "login_applicant";
			document.loginform.action = "/LeaveApplication/servlet/leave.SearchController";
			document.loginform.submit();
		}
	}

	function setFocus() {
		document.loginform.login_name.focus();
	}

	function clear1() {
		loginform.login_name.value = "";
		loginform.password.value = "";
	}
</script>
<HTML>
<HEAD>
<style type="text/css">
body {
	background-image: url(images/1bg_login.gif);
	margin-left: 0px;
	margin-top: 0px;
}
.text {
	font-family: Verdana;
	color: #FFFFFF;
	font-size: 18px;
}
.formbutt {
	background-image: url(../images/1bg_login.gif);
	font-family: Arial, Verdana, Helvetica, sans-serif;
	font-size: 11px;
	font-weight: bold;
	color: #FFFFFF border: 0px solid #FFFFFF padding: 1px;
}
</style>
<TITLE></TITLE>
</HEAD>
<body class="body" width="100%" height="100%" onload="setFocus()">
	<form name="loginform" method="post" action="">
		<center>
			<br> <br> <br>
			<table>
				<tr>
					<td height="10" valign="bottom"><img src="leave/isglogo.gif"
						width="250" height="100"></td>
				</tr>
				<br>
				<br>
				<br>
				<br>
				<tr>
					<td class="text"><font height="310" valign="bottom">USER'S LOGIN </font> <br></td>
				</tr>
			</table>
			<table width="350" border="0">
				<tr>
					<td valign="center" align="center"><b>Login ID :</b></td>
					<td valign="center" align="center"><input type="text"
						size="20" name="login_name" onkeypress="submitPage()" /> <br>
					</td>
				</tr>
				<tr>
					<td valign="center" align="center"><b>Password :</b></td>
					<td valign="center" align="center"><input type="password"
						size="20" name="password" onkeypress="submitPage()" /> <br>
						<br></td>
				</tr>
			</table>

			<input type="button" class="formbutt" value="Login"
				onClick="submitvalidate()" onKeyPress="submitvalidate()" /> <input
				type="button" class="formbutt" value="Clear" onClick="clear1()" />
		</center>
		<input type='hidden' name='mode' value=""/ >
	</form>
</body>
</html>