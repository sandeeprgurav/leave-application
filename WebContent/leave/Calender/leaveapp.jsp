<%@page errorPage="ErrorHandler.jsp"%>
<%@ page language="java"%>
<%@ page
	import="java.sql.*,java.io.*,java.util.*,java.text.*,com.leave.*"%>
<%@page import="java.io.*,java.sql.*"%>
<%@page import="com.leave.LeaveBean"%>
<%@page import="com.leave.sendMailFile1"%>


<script language="javascript" SRC="../leave/Calender/calendar.js"></script>
<script language="javascript">
	function Checkdetails() {

		if (document.form1.empcode.value != "") {
			//System.out.println("value=="+document.form1.empcode.value);     
			// var empcode11=new String(document.form1.empcode.value);
			var myregexpr = /^[a-zA-Z]{1}[a-z0-9]*[0-9]+[a-z]*$$/;
			//alert("Invalid Employee code");

			if (!myregexpr.test(document.form1.empcode.value)) {
				alert("Employee code must be Alphanumeric");
				document.form1.empcode.value = "";
			}

		}

		if (document.form1.empname.value != "")

		{
			// var empcode11=new String(document.form1.empname.value);
			var myregexpr = /^[a-zA-Z][a-z0-9]*[0-9]*[a-z]*$/;

			if (!myregexpr.test(document.form1.empname.value))

			{

				alert("  Incorrect Employee name  ");
				document.form1.empname.value = "";
				//document.form1.submit(); 
			}
		}

		if (document.form1.contact.value != "")

		{

			var empcode11 = new String(document.form1.contact.value);
			var myregexpr = /^[0-9]*$/;

			if (!myregexpr.test(empcode11))

			{
				alert("Incorrect contact no");
				document.form1.contact.value = "";
			}

			if (empcode11.length > 10)

			{
				alert(" contact no could not be greater than 10 digits "
						+ empcode11);
				document.form1.contact.value = "";
			}

		}

		if (document.form1.empname.value == "")

		{
			alert("Please enter Employee name");
		}

		else if (document.form1.date.value == "")

		{
			alert("Please enter date");
		}

		else if (document.form1.empcode.value == "")

		{
			alert("Please enter employee code");
		}

		else if (document.form1.source1.value == "SELECT") {
			alert("Please select designation");
		}

		else if (document.form1.source2.value == "SELECT") {
			alert("Please select emplocation");
		}

		else if (document.form1.source3.value == "SELECT") {
			alert("Please select department");
		}

		else if (document.form1.reason.value == "") {
			alert("Please enter reason for leave");
		} else if (document.form1.fromdate.value == "") {
			alert("Please enter from date");
		}

		else if (document.form1.todate.value == "") {
			alert("Please enter todate");
		} else if (document.form1.contact.value == "") {
			alert("Please enter contact");
		}

		else if (document.form1.empsign.value == "") {
			alert("Please enter Employee sign");
		} else if (document.form1.namebackup.value == "") {
			alert("Please enter name backup if  leave is for than 2 days or more");
		} else if (document.form1.signbackup.value == "") {
			alert("Please enter Signature of Back up");
		}

		else if (document.form1.leaveappvby.value == "") {
			alert("Please enter Leave Approved By");
		}

		else if (document.form1.appsign.value == "") {
			alert("Please enter Approvers Signature");
		}

		else if (document.form1.source4.value == "SELECT") {
			alert("Please select Approvers Department");
		}

		else if (document.form1.source5.value == "SELECT") {
			alert("Please select Approvers Designation");
		}

		else if ((document.form1.source5.value != "")
				&& (document.form1.source4.value != "")
				&& (document.form1.appsign.value != "")
				&& (document.form1.leaveappvby.value != "")
				&& (document.form1.signbackup.value != "")
				&& (document.form1.namebackup.value != "")
				&& (document.form1.empsign.value != "")
				&& (document.form1.contact.value != "")
				&& (document.form1.fromdate.value != "")
				&& (document.form1.reason.value != "")
				&& (document.form1.source3.value != "")
				&& (document.form1.source2.value != "")
				&& (document.form1.source1.value != "")
				&& (document.form1.empcode.value != "")
				&& (document.form1.empname.value != "")) {

			document.form1.mode.value = "connect";
			document.form1.action = "/LeaveApplication/servlet/leave.SearchController";
			document.form1.submit();

			alert("data successfully submited");
			alert("Mail send successfully");

		}

	}

	function backfunc() {
		//alert("back to menu page");
		document.form1.action = "../leave/menu_page.jsp";
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
		if ((document.form1.C11.checked)) {
			//alert("Please esavgfhhs");

			document.form1.C12.checked = false;
			document.form1.C13.checked = false;
			document.form1.C21.checked = false;
			document.form1.C22.checked = false;
		}
	}

	function Checkbox2() {
		if ((document.form1.C12.checked)) {
			//alert("Please esavgfhhs");

			document.form1.C11.checked = false;
			document.form1.C13.checked = false;
			document.form1.C21.checked = false;
			document.form1.C22.checked = false;
		}

	}

	function Checkbox3() {
		if ((document.form1.C13.checked)) {
			//alert("Please esavgfhhs");

			document.form1.C11.checked = false;
			document.form1.C12.checked = false;
			document.form1.C21.checked = false;
			document.form1.C22.checked = false;
		}

	}
	function Checkbox4() {
		if ((document.form1.C21.checked)) {
			//alert("Please esavgfhhs");

			document.form1.C11.checked = false;
			document.form1.C13.checked = false;
			document.form1.C12.checked = false;
			document.form1.C22.checked = false;
		}

	}

	function Checkbox5() {
		if ((document.form1.C22.checked)) {
			//alert("Please esavgfhhs");

			document.form1.C11.checked = false;
			document.form1.C13.checked = false;
			document.form1.C12.checked = false;
			document.form1.C21.checked = false;
		}
	}
</script>

<html>
<head>
</head>
<body bgcolor="pink">
	<form name="form1" method="post">

		<b><p align="center">LEAVE APPLICATION FORM</p></b>

		<%
			LeaveBean founData = (LeaveBean) request.getAttribute("foundData1");
			/*  String name3=
			  String empcode3= 
			  String designation3=
			  String emp_work_loc3=
			  String department3=
			  */
			// Date d = new Date();
			//DateFormat df = new DateFormat();
		%>

		<p align="center">
			Please ensure sufficient balance in your account before availing of
			leave. <br>Please submit the form to respective location
			incharge.
		</p>


		<table
			style="border: 1px solid #000000; padding: 1px; background-color: #E1E1E1; font-family: Arial; font-size: 10pt"
			width="800" align="center" border="1" id="table1" cellpadding="0"
			bgcolor="#FFFFFF" cellspacing="0">
			<tr>
				<td width="25%" bgcolor="#E1E1E1">Name of the Employee</td>

				<td width="3%">:</td>

				<td width="25%"><input type="text" size="30" name="empname"
					value="<%=founData.getName()%>"></td>

				<td width="20%">Date</td>

				<td width="25%"><input type="text" size="15" name="date"
					value=""> <input type="button" value=".."
					onclick="openCalendar(date,'DD-Mon-yyyy')"></td>
			</tr>
			<tr>
				<td width="25%" bgcolor="#E1E1E1">Employee Code</td>
				<td width="3%">:</td>
				<td width="25%"><input type="text" size="30" name="empcode"
					value="<%=founData.getEmpcode()%>"></td>
				<td width="20%">Designation</td>

				<td width="25%"><select size="1" name="source1">
						<option value="<%=founData.getDesignation()%>" selected><%=founData.getDesignation()%></option>
						<option value="JAVA PROGRAMMER">JAVA PROGRAMMER</option>
						<option value="PL/SQL PROGRAMMER">PL/SQL PROGRAMMER&nbsp;
							&nbsp;&nbsp; &nbsp;&nbsp;</option>
						<option value="SYSTEM ADMIN">SYSTEM ADMIN</option>
						<option value="TEAM LEADER">TEAM LEADER</option>
						<option value="PROJECT MANAGER">PROJECT MANAGER</option>
				</select></td>
			</tr>
			<tr>
				<td width="20%" bgcolor="#E1E1E1">Employees work Location</td>
				<td width="3%">:</td>
				<td width="20%"><select size="1" name="source2">
						<option value="<%=founData.getEmpworkloc()%>" selected><%=founData.getEmpworkloc()%></option>
						<option value="MALAD">MALAD
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;</option>
						<option value="ANDHERI">ANDHERI</option>
						<option value="CULABA">CULABA</option>
						<option value="BORIVALI">BORIVALI</option>
						<option value="CST">CST</option>
				</select></td>
				<td width="20%">Department</td>

				<td width="20%"><select size="1" name="source3">
						<option value="<%=founData.getDepartment()%>" selected>
							<%=founData.getDepartment()%>
						</option>
						<option value="IT">IT</option>
						<option value="PRODUCT DEVELOPMENT">PRODUCT DEVELOPMENT</option>
						<option value="HR">HR</option>
						<option value="BD">BD</option>
						<option value="SYSTEM ADMIN">SYSTEM ADMIN</option>
				</select></td>
			</tr>


			<tr>
				<td width="25%" bgcolor="#E1E1E1">Type of Leave Applied</td>
				<td width="3%">:</td>

				<td width="25%">
					<table
						style="border: 1px solid #000000; padding: 1px; font-family: Arial; font-size: 10pt"
						width="100%" align="center" border="1" id="table1" cellpadding="0"
						bgcolor="#FFFFFF" cellspacing="0">
						<tr>
							<td width="175%" height="133%">Casual Leave</td>
							<td width="75%" height="133%"><center>
									<input type="checkbox" name="C11" onClick="Checkbox1()">
								</center></td>
						</tr>
						<tr>
							<td width="175%" height="133%">Sick Leave</td>
							<td width="75%" height="133%"><center>
									<input type="checkbox" name="C12" onClick="Checkbox2()">
								</center></td>
						</tr>
						<tr>
							<td width="175%" height="133%">Comp Off*
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
								&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
								&nbsp;&nbsp;</td>
							<td width="75%" height="133%"><center>
									<input type="checkbox" name="C13" onClick="Checkbox3()">
								</center></td>
						</tr>
					</table>
				</td>

				<td width="25%">
					<table
						style="border: 1px solid #000000; padding: 1px; font-family: Arial; font-size: 10pt"
						width="100%" align="center" border="1" id="table1" cellpadding="0"
						bgcolor="#FFFFFF" cellspacing="0">
						<tr>
							<td>Half Day</td>
							<td><center>
									<input type="checkbox" name="C21" onClick="Checkbox4()">
								</center></td>
						</tr>
						<tr>
							<td>Privilege Leave</td>
							<td><center>
									<input type="checkbox" name="C22" onClick="Checkbox5()">
								</center></td>
						</tr>
						<tr>
							<td>Comp off taken for working on Date</td>
							<td>:</td>
						</tr>
					</table>
				</td>

				<td width="25%">
					<table style="border: 1px solid #000000; padding: 1px;"
						100%" font-family: Arial; font-size: 10pt" align="center"
						border="1" id="table1" cellpadding="0" bgcolor="#FFFFFF"
						cellspacing="0">
						<tr>
							<td width="175%"><input type="text" disabled size="30">
							</td>
						</tr>
						<tr>
							<td width="175%"><input type="text" disabled size="30"></td>
						</tr>
						<tr>
							<td width="175%"><input type="text" name="commoff" size="15">
								<input type="button" value=".."
								onclick="openCalendar(commoff,'DD-Mon-yyyy');"></td>
						</tr>
					</table>
				</td>

			</tr>

			<tr>
				<td width="25%" bgcolor="#E1E1E1">Reason for leave</td>
				<td width="3%">:</td>
				<td width="25%"><input type="text" size="30" name="reason"></td>
			</tr>


			<tr>
				<td width="25%" bgcolor="#E1E1E1">From Date</td>
				<td width="3%">:</td>
				<td width="25%"><input type="text" size="15" name="fromdate"><input
					type="button" value=".."
					onclick="openCalendar(fromdate,'DD-Mon-yyyy');"></td>
				<td width="20%">To Date</td>

				<td width="25%"><input type="text" size="15" name="todate">
					<input type="button" value=".."
					onclick="openCalendar(todate,'DD-Mon-yyyy');"></td>
				</td>
			</tr>

			<tr>
				<td width="25%" bgcolor="#E1E1E1">Contact No**</td>
				<td width="3%">:</td>
				<td width="25%"><input type="text" size="30" name="contact"></td>
				<td width="20%">Signature of Employee</td>

				<td width="25%"><input type="text" size="30" name="empsign"></td>
			</tr>

			<tr>
				<td width="25%" bgcolor="#E1E1E1">Name of Back-Up if leave is
					for than 2 days or more</td>
				<td width="3%">:</td>
				<td width="25%"><input type="text" size="30" name="namebackup"></td>
				<td width="20%">Signature of Back up</td>
				<td width="25%"><input type="text" size="30" name="signbackup"></td>
			</tr>


			<tr>
				<td width="25%" bgcolor="#E1E1E1">Leave Approved By</td>
				<td width="3%">:</td>
				<td width="25%"><input type="text" size="30"
					name="leaveappvby"></td>
				<td width="20%">Approvers Signature</td>
				<td width="25%"><input type="text" size="30" name="appsign">
				</td>
			</tr>


			<tr>
				<td width="25%" bgcolor="#E1E1E1">Approvers Department</td>
				<td width="3%">:</td>
				<td width="25%"><select size="1" name="source4">
						<option value="SELECT" selected>--SELECT--</option>
						<option value="IT">IT</option>
						<option value="PRODUCT DEVELOPMENT">PRODUCT DEVELOPMENT</option>
						<option value="HR">HR</option>
						<option value="BD">BD</option>
						<option value="SYSTEM ADMIN">SYSTEM ADMIN</option>
				</select></td>
				<td width="20%">Approvers Designation</td>
				<td width="25%"><select size="1" name="source5">
						<option value="SELECT" selected>--SELECT--</option>
						<option value="TEAM LEADER">TEAM LEADER</option>
						<option value="PROJECT MANAGER">PROJECT MANAGER</option>
						<option value="DIRECTOR">DIRECTOR</option>

				</select></td>
			</tr>

		</table>

		<p align="center">
			*Comp Off should be taken within two months; it will lapse after two
			months<br> ** Please provide contact no of the outside location
			if travelling outstation. If local<br> mobile no is provided, it
			should have the roaming facility, so that contact can be made if
			required.<br>
		</p>

		<center>
			<input type="button" value="Submit" onClick=" Checkdetails()" /> <input
				type="button" value="Back" onClick="backfunc()" />
		</center>

		<input type="hidden" name="mode">

	</form>
</body>
</html>