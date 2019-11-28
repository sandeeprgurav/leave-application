<%@page errorPage="ErrorHandler.jsp"%>
<%@ page language="java"%>
<%@ page import="java.sql.*,java.io.*,java.util.*,java.text.*,com.leave.*"%>
<%@page import="java.io.*,java.sql.*"%>
<%@page import="com.leave.LeaveBean"%>
<%@page import="com.leave.sendMailFile1"%>
<%@page import="java.util.Vector"%>
<script language="javascript" SRC="../leave/Calender/calendar.js"></script>
<script language="javascript" SRC="../js/Ajaxcontent.js"></script>
<script language="javascript">
	function Checkdetails()

	{

		var match = 0;
		var strArgument

		/* if(document.form1.contact.value!="")

		{

					var empcode11=new String(document.form1.contact.value);
					var myregexpr=/^[0-9]*$/;			  

					if(!myregexpr.test(empcode11))

						   {
								  alert("Incorrect contact no");	
								  document.form1.contact.focus();
						   }

					if(empcode11.length>10)

						   {
								  alert(" contact no could not be greater than 10 digits "+empcode11);		 
								  document.form1.contact.focus();
						   }


		} */

		if ((document.form1.C11.checked == true && document.form1.casual_day.value == "0")) {

			alert("Please enter number of casual days");

		}

		else if ((document.form1.C12.checked == true && document.form1.sick_day.value == "0")) {

			alert("Please enter number of sick days");

		}

		else if ((document.form1.C22.checked == true && document.form1.privilege_day.value == "0")) {

			alert("Please enter number of privilege days");
		}

		else if ((document.form1.C21.checked == true && document.form1.half_day_date.value == "")) {

			alert("Please select half day date");

		}

		else if ((document.form1.C13.checked == true && document.form1.comp_off_date.value == "")) {

			alert("Please select  comp off date");

		}

		else if (document.form1.C13.checked == false
				&& document.form1.C12.checked == false
				&& document.form1.C11.checked == false
				&& document.form1.C21.checked == false
				&& document.form1.C22.checked == false) {
			alert("Please select  type of leave");

		} else if (document.form1.reason.value == "") {
			alert("Please enter reason for leave");
		}
		/*  else if(document.form1.fromdate.value=="")
		{
		alert("Please enter from date");
		}

		else if(document.form1.todate.value=="")
		{
		alert("Please enter todate");
		}
		 */
		else if (document.form1.contact.value == "") {
			alert("Please enter contact");
		}

		/*    else   if(document.form1.namebackup.value=="") 
		{  
		alert("Please enter name backup if  leave is for than 2 days or more");
		}
		 */

		else if (document.form1.leaveappvby.value == "SELECT") {
			alert("Please select Leave Approved By");
		}

		else if (document.form1.source4.value == "SELECT") {
			alert("Please select Approvers Department");
		}

		else if (document.form1.source5.value == "SELECT") {
			alert("Please select Approvers Designation");
		}

		if (document.form1.fromdate.value != ""
				&& document.form1.todate.value != "") {
			var days = parseInt(form1.casual_day.value)
					+ parseInt(form1.sick_day.value)
					+ parseInt(form1.privilege_day.value);
			var ONE_HOUR = 60 * 60 * 1000;
			var ONE_DAY = 86400000;
			var date1 = null;
			var date2 = null;
			date1 = new String(document.form1.fromdate.value);

			date2 = new String(document.form1.todate.value);

			var iDate = null;
			var year = null;
			var sMonth = null;

			iDate = date1.substring(0, 2);
			year = date1.substring(7, 11);
			sMonth = (date1.substring(3, 6));

			var iMonth = 0;
			if (sMonth == "Jan") {
				iMonth = 1;
			}
			if (sMonth == "Feb") {
				iMonth = 2;
			}
			if (sMonth == "Mar") {
				iMonth = 3;
			}
			if (sMonth == "Apr") {
				iMonth = 4;
			}
			if (sMonth == "May") {
				iMonth = 5;
			}
			if (sMonth == "Jun") {
				iMonth = 6;
			}
			if (sMonth == "Jul") {
				iMonth = 7;
			}
			if (sMonth == "Aug") {
				iMonth = 8;
			}
			if (sMonth == "Sep") {
				iMonth = 9;
			}
			if (sMonth == "Oct") {
				iMonth = 10;
			}
			if (sMonth == "Nov") {
				iMonth = 11;
			}
			if (sMonth == "Dec") {
				iMonth = 12;
			}

			var iDate1 = null;
			var year1 = null;
			var sMonth1 = null;
			iDate1 = date2.substring(0, 2);
			year1 = date2.substring(7, 11);
			sMonth1 = (date2.substring(3, 6));

			var iMonth1 = 0;

			if (sMonth1 == "Jan") {
				iMonth1 = 1;
			}
			if (sMonth1 == "Feb") {
				iMonth1 = 2;
			}
			if (sMonth1 == "Mar") {
				iMonth1 = 3;
			}
			if (sMonth1 == "Apr") {
				iMonth1 = 4;
			}
			if (sMonth1 == "May") {
				iMonth1 = 5;
			}
			if (sMonth1 == "Jun") {
				iMonth1 = 6;
			}
			if (sMonth1 == "Jul") {
				iMonth1 = 7;
			}
			if (sMonth1 == "Aug") {
				iMonth1 = 8;
			}
			if (sMonth1 == "Sep") {
				iMonth1 = 9;
			}
			if (sMonth1 == "Oct") {
				iMonth1 = 10;
			}
			if (sMonth1 == "Nov") {
				iMonth1 = 11;
			}
			if (sMonth1 == "Dec") {
				iMonth1 = 12;
			}
			var startDate = null;
			var startDate1 = null;

			startDate = new Date(year, iMonth, iDate);
			startDate1 = new Date(year1, iMonth1, iDate1);

			var started = null;
			started = startDate.getTime();

			var started1 = null;
			started1 = startDate1.getTime();

			var day = parseInt((started1 + ONE_DAY - started + ONE_HOUR)
					/ (ONE_HOUR * 24));

			if (day != days && false) {
				match = 1;
				alert("Mismatch between select no of days and days between fromdate and todate. \n Please reenter data.");

				document.form1.casual_day.value = "0";
				document.form1.sick_day.value = "0";
				document.form1.privilege_day.value = "0";
				document.form1.fromdate.value = "";
				document.form1.todate.value = "";
				document.form1.C11.checked = false;
				document.form1.C12.checked = false;
				document.form1.C22.checked = false;

			}

		}

		if ((document.form1.source5.value != "")
				&& (document.form1.source4.value != "")
				&& (document.form1.leaveappvby.value != "")
				&& (document.form1.contact.value != "")
				&& (document.form1.reason.value != "")
				&& (document.form1.source3.value != "")
				&& (document.form1.source2.value != "")
				&& (document.form1.source1.value != "")
				&& (document.form1.empcode.value != "")
				&& (document.form1.empname.value != "") && (match == 0)) {

			document.form1.mode.value = "connect";
			document.form1.action = "/LeaveApplication/servlet/leave.SearchController";
			document.form1.submit();

			alert("data successfully submited");
			alert("Mail send successfully");

		}

	}

	function backfunc() {
		//alert("back to menu page");
		document.form1.action = "../leave/first_page.jsp";
		document.form1.submit();

	}

	function openCalendar(ctrlID, pFormat) {
		//entry.moveTo(10,10);

		var autoclose = true;
		var windowW = 220 // wide
		var windowH = 220 // high
		var windowX = event.screenX + document.body.scrollLeft + 20; // from left
		var windowY = event.screenY + document.body.scrollTop - 20; // from top
		var urlPop = "../leave/Calender/calendar.htm"
		var title = "Calendar";
		SetDateFormat(pFormat);
		s = "width=" + windowW + ",height=" + windowH + ",left=" + windowX
				+ ",top=" + windowY;		
		setDateField(ctrlID, true);
		top.newWin = window.open(urlPop, "popFrameless",
				"resizable=no,title=no,fullscreen=no," + s);
		top.newWin.blur();
		top.newWin.focus();
		// top.newWin.resizeTo(windowW,windowH);
		//top.newWin.moveTo(windowX,windowY);
		top.newWin.focus();

		if (autoclose) {

			window.onunload = function() {
				top.newWin.close();
			}

		}

	}

	function Checkbox1() {
		//alert ("Hi");   
		if (document.form1.C11.checked == true) {
			//alert("Please esavgfhhs");

			document.form1.C13.checked = false;
			document.form1.C21.checked = false;
			document.getElementById("halfdateflag11").style.display = "none";
			document.getElementById("compdateflag11").style.display = "none";
		} else if (document.form1.C11.checked == false) {
			document.getElementById("halfdateflag11").style.display = "block";
			document.getElementById("compdateflag11").style.display = "block";
		}

	}

	function Checkbox2() {
		if (document.form1.C12.checked == true) {

			document.form1.C13.checked = false;
			document.form1.C21.checked = false;
			document.getElementById("halfdateflag11").style.display = "none";
			document.getElementById("compdateflag11").style.display = "none";

		} else if (document.form1.C12.checked == false) {
			document.getElementById("halfdateflag11").style.display = "block";
			document.getElementById("compdateflag11").style.display = "block";
		}

	}

	function Checkbox3() {

		if (document.form1.C22.checked == true) {

			document.form1.C13.checked = false;
			document.form1.C21.checked = false;
			document.getElementById("halfdateflag11").style.display = "none";
			document.getElementById("compdateflag11").style.display = "none";

		} else if (document.form1.C22.checked == false) {
			document.getElementById("halfdateflag11").style.display = "block";
			document.getElementById("compdateflag11").style.display = "block";
		}

	}

	function Checkbox4() {
		if (document.form1.C21.checked == true) {

			document.form1.C11.checked = false;
			document.form1.C12.checked = false;
			document.form1.C22.checked = false;
			document.form1.C13.checked = false;
			document.getElementById("fromdateflag").style.display = "none";
			document.getElementById("todateflag").style.display = "none";
			document.getElementById("fromdateflag1").style.display = "none";
			document.getElementById("todateflag1").style.display = "none";
			document.getElementById("namebackupflag").style.display = "none";
			document.getElementById("compdateflag11").style.display = "none";
		} else if (document.form1.C21.checked == false) {
			document.getElementById("fromdateflag").style.display = "block";
			document.getElementById("todateflag").style.display = "block";
			document.getElementById("fromdateflag1").style.display = "block";
			document.getElementById("todateflag1").style.display = "block";
			document.getElementById("namebackupflag").style.display = "block";
			document.getElementById("compdateflag11").style.display = "block";
		}

	}

	function Checkbox5() {

		if (document.form1.C13.checked == true) {

			document.form1.C11.checked = false;
			document.form1.C12.checked = false;
			document.form1.C22.checked = false;
			document.form1.C21.checked = false;
			document.getElementById("fromdateflag").style.display = "none";
			document.getElementById("todateflag").style.display = "none";
			document.getElementById("fromdateflag1").style.display = "none";
			document.getElementById("todateflag1").style.display = "none";
			document.getElementById("namebackupflag").style.display = "none";
			document.getElementById("halfdateflag11").style.display = "none";

		} else if (document.form1.C13.checked == false) {
			document.getElementById("fromdateflag").style.display = "block";
			document.getElementById("todateflag").style.display = "block";
			document.getElementById("fromdateflag1").style.display = "block";
			document.getElementById("todateflag1").style.display = "block";
			document.getElementById("namebackupflag").style.display = "block";
			document.getElementById("halfdateflag11").style.display = "block";
		}
	}

	function go() {
		strArgument = document.form1.source4.value;

		if (strArgument != "SELECT") {
			//alert("department before=="+strArgument);
			showContentAjax("../DesignationSearch", "strArgument="
					+ strArgument);
			//alert("department after=="+strArgument);

		}
	}

	function go1() {
		strArgument = document.form1.source5.value;

		if (strArgument != "SELECT") {

			//alert("designation before=="+strArgument);
			showContentAjax("../DesignationSearch", "strArgument="
					+ strArgument);
			//alert("designation after=="+strArgument);

		}

	}
</script>

<html>
<head>
</head>
<BODY background="images20.jpeg" width="600" height="600">
	<form name="form1" method="post">

		<b><p align="center">LEAVE APPLICATION FORM</p></b>

		<%
			String session_check_username = (String) session.getAttribute("login_name1");
			String session_check_password = (String) session.getAttribute("login_password1");
			if (session_check_username == null || session_check_password == null) {
		%>

		<script>
			alert('Session is completed  \n Please Login again')
		</script>
		<script>
			window.navigate(
					'http://localhost:8080/LeaveApplication/leave/loginpage_app.jsp')
		</script>
		<%
			}
		%>


		<%
			LeaveBean founData = null;
			if (session_check_username != null || session_check_password != null) {
				Vector approvers = null;
				founData = (LeaveBean) session.getAttribute("foundData1");
				System.out.println("appovers data==>>" + approvers);
			}
		%>



		<table
			style="border: 1px solid #f7f1f1; padding: 1px; background-color: #E1E1E1; font-family: Arial; font-size: 10pt"
			width="80%" align="center" border="1" id="table1" cellpadding="0"
			bgcolor="#FFFFFF" cellspacing="0">
			<tr>
				<td width="25%" bgcolor="#E1E1E1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Name
					of the Employee</td>
				<td width="3%"><b> :</b></td>
				<td width="25%"><input type="text" readonly size="20"
					name="empname" value="<%=founData.getName()%>"></td>
				<td width="20%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Date <b>
						:</b>
				</td>
				<td width="25%"><input type="text" size="15" name="date"
					value="<%=founData.getDate()%>"> <input type="button"
					value=".." onclick="openCalendar(date,'yyyy-mm-dd')"></td>
			</tr>

			<tr>
				<td width="25%" bgcolor="#E1E1E1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Employee
					Code</td>
				<td width="3%"><b> :</b></td>
				<td width="25%"><input type="text" readonly ize="20"
					name="empcode" value="<%=founData.getEmpcode()%>"></td>
				<td width="20%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Designation
					<b> :</b>
				</td>
				<td width="25%"><input type="text" readonly name="source1"
					size="20" value="<%=founData.getDesignation()%>"></td>
			</tr>

			<tr>
				<td width="20%" bgcolor="#E1E1E1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Employees
					work Location</td>
				<td width="3%"><b> :</b></td>
				<td width="20%"><input type="text" readonly name="source2"
					size="20" value="<%=founData.getEmpworkloc()%>"></td>
				<td width="20%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Department
					<b> :</b>
				</td>

				<td width="20%"><input type="text" readonly name="source3"
					size="20" value="<%=founData.getDepartment()%>"></td>
			</tr>


			<tr>
				<td width="15%" bgcolor="#E1E1E1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td width="3%"><b> :</b></td>
				<td width="25%">Casual Leave
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="checkbox" name="C11" onClick="Checkbox1()">
				</td>
				<td width="25%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;NO.of days
					<b> :</b>
				</td>
				<td width="25%"><select size="1" name="casual_day">
						<option value="0" selected>--0--</option>
						<option value="1">1</option>
						<option value="2 ">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5 ">5</option>
						<option value="6">6</option>
						<option value="7 ">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10 ">10</option>
						<option value="11 ">11</option>
						<option value="12">12</option>
						<option value="13 ">13</option>
						<option value="14">14</option>
						<option value="15">15</option>
				</select></td>
				</td>

			</tr>

			<tr>
				<td width="15%" bgcolor="#E1E1E1"></td>
				<td width="3%"><b> :</b></td>
				<td width="25%">Sick
					Leave&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="checkbox" name="C12" onClick="Checkbox2()">
				</td>
				<td width="25%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;NO.of days
					<b> :</b>
				</td>
				<td width="25%"><select size="1" name="sick_day">
						<option value="0" selected>--0--</option>
						<option value="1">1</option>
						<option value="2 ">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5 ">5</option>
						<option value="6">6</option>
						<option value="7 ">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10 ">10</option>
						<option value="11 ">11</option>
						<option value="12">12</option>
						<option value="13 ">13</option>
						<option value="14">14</option>
						<option value="15">15</option>
				</select></td>
				</td>

			</tr>

			<tr>
				<td width="15%" bgcolor="#E1E1E1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Type
					of Leave Applied</td>
				<td width="3%"><b> :</b></td>
				<td width="25%">Privilege
					leave&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="checkbox" name="C22" onClick="Checkbox3()">
				</td>
				<td width="25%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;NO.of days
					<b> :</b>
				</td>
				<td width="25%"><select size="1" name="privilege_day">
						<option value="0" selected>--0--</option>
						<option value="1">1</option>
						<option value="2 ">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5 ">5</option>
						<option value="6">6</option>
						<option value="7 ">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10 ">10</option>
						<option value="11 ">11</option>
						<option value="12">12</option>
						<option value="13 ">13</option>
						<option value="14">14</option>
						<option value="15">15</option>
				</select></td>
				</td>
				</td>

			</tr>

			<tr id="halfdateflag11" style="display: block">
				<td width="15%" bgcolor="#E1E1E1"></td>
				<td width="3%"><b> :</b></td>
				<td width="25%">half day
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="checkbox" name="C21" onClick="Checkbox4()">
				</td>
				<td width="25%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Date <b>
						:</b>
				</td>
				<td><input type="text" size="15" name="half_day_date">
					<input type="button" value=".."
					onclick="openCalendar(half_day_date,'yyyy-mm-dd')"></td>

			</tr>

			<tr id="compdateflag11" style="display: block">
				<td width="15%" bgcolor="#E1E1E1"></td>
				<td width="3%"><b> :</b></td>
				<td width="25%">comp off
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="checkbox" name="C13" onClick="Checkbox5()">
				</td>
				<td width="25%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Date <b>
						:</b>
				</td>
				<td><input type="text" size="15" name="comp_off_date">
					<input type="button" value=".."
					onclick="openCalendar(comp_off_date,'yyyy-mm-dd')"></td>

			</tr>


			<tr>
				<td width="25%" bgcolor="#E1E1E1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Reason
					for leave</td>
				<td width="3%"><b> :</b></td>
				<td width="25%"><input type="text" size="30" name="reason"></td>
			</tr>


			<tr>
				<td width="25%" bgcolor="#E1E1E1" id="fromdateflag1"
					style="display: block">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;From
					Date <b> :</b>
				</td>
				<td width="3%"></td>
				<td width="25%" id="fromdateflag" style="display: block"><input
					type="text" size="15" name="fromdate"><input type="button"
					value=".." onclick="openCalendar(fromdate,'yyyy-mm-dd');">
				</td>
				<td width="20%" id="todateflag1" style="display: block">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;To
					Date<b> :</b>
				</td>
				<td width="25%" id="todateflag" style="display: block"><input
					type="text" size="15" name="todate" value=""> <input type="button"
					value=".." onclick="openCalendar(todate,'yyyy-mm-dd');"></td>
			</tr>

			<tr>
				<td width="25%" bgcolor="#E1E1E1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Contact
					No**</td>
				<td width="3%"><b> :</b></td>
				<td width="25%"><input type="text" readonly size="20"
					name="contact" value="<%=founData.getContact()%>"></td>

			</tr>

			<tr id="namebackupflag" style="display: block">
				<td width="25%" bgcolor="#E1E1E1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Name
					of Back-Up if leave &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;is for 2
					days or more</td>
				<td width="3%"><b> :</b></td>
				<td><input type="text" size="20" name="namebackup"></td>

			</tr>


			<tr>
				<td width="25%" bgcolor="#E1E1E1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Approvers
					Department</td>
				<td width="3%"><b> :</b></td>
				<td width="25%"><select size="1" name="source4"
					onchange="go()" autocomplete="off">
						<option value="SELECT" selected>--SELECT--</option>
						<option value="IT">IT</option>
						<option value="PRODUCT DEVELOPMENT">PRODUCT DEVELOPMENT</option>
						<option value="HR">HR</option>
						<option value="BD">BD</option>
						<option value="SYSTEM ADMIN">SYSTEM ADMIN</option>
				</select></td>

			</tr>


			<tr>




				<td width="20%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Approvers
					Designation<b> :</b>
				</td>
				<td width="3%"><b> :</b></td>
				<td width="25%"><select size="1" name="source5"
					onchange="go1()" autocomplete="off">
						<option value="SELECT" selected>--SELECT--</option>
						<option value="1">TEAM LEADER</option>
				</select></td>


				<td width="25%" bgcolor="#E1E1E1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Leave
					Approved By</td>

				<td width="25%"><select size="1" name="leaveappvby">
						<option value="SELECT" selected>--SELECT--</option>
						<option value="1">satyajit</option>
				</select></td>
			</tr>



		</table>


        <br/>
        <br/>
		<center>
			<input type="button" value="Submit" onClick=" Checkdetails()" /> <input
				type="button" value="Back" onClick="backfunc()" />
		</center>

		<input type="hidden" name="mode">

	</form>
</body>
</html>