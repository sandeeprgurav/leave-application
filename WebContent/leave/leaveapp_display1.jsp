<%@page errorPage="ErrorHandler.jsp"%>
<%@ page language="java"%>
<%@ page
	import="java.sql.*,java.io.*,java.util.*,java.text.*,com.leave.*"%>
<%@page import="java.io.*,java.sql.*"%>
<%@page import="com.leave.LeaveBean"%>
<%@page import="com.leave.Showdetail"%>
<%@page import="com.leave.sendMailFile1"%>

<script language="javascript">
	function backfunc() {
		document.form1.action = "../leave/leaveapp1.jsp";
		document.form1.submit();
	}
</script>
<html>
<head>
</head>
<BODY background="../leave/images5.jpg" width="600" height="600">
	<form name="form1" method="post">
		<b>
			<p align="center">LEAVE APPLICATION FORM</p>
		</b>
		<table
			style="border: 1px solid #000000; padding: 1px; background-color: #E1E1E1; font-family: Arial; font-size: 10pt"
			width="800" align="center" border="1" id="table1" cellpadding="0"
			bgcolor="#FFFFFF" cellspacing="0">
			<%
				int vsize = 0;
				Vector v1 = null;
				Vector v2 = null;
				v2 = new Vector();
				String rid = request.getParameter("rid") == null ? "" : request.getParameter("rid");
				System.out.println("rid====>" + rid);
				Showdetail detail = new Showdetail();
				Vector data = detail.getDetail1(rid);
				System.out.println("data===>>" + data);
				int d = data.size();
				for (int j = 0; j < d; j++)
				{
					v1 = (Vector) data.get(j);
					vsize = v1.size() > 0 ? v1.size() : 0;
					System.out.println("size of vector==" + vsize);
					for (int i = 0; i < vsize; i++)
					{
						String got = (String) v1.get(i);
						System.out.println("data of table=====>>>>>>" + got);
						v2.addElement(got);
					}
				}
			%>
			<tr>
				<td width="25%" bgcolor="#E1E1E1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Name
					of the Employee</td>
				<td width="3%"><b> :</b></td>
				<td width="25%"><b><%=v2.get(0) != null ? v2.get(0) : ""%> </b></td>
				<td width="20%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Date <b>
						:</b>
				</td>
				<td width="25%"><b> <%=v2.get(1) != null ? v2.get(1) : ""%></b></td>
			</tr>
			<tr>
				<td width="25%" bgcolor="#E1E1E1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Employee
					Code</td>
				<td width="3%"><b> :</b></td>
				<td width="25%"><b><%=v2.get(2) != null ? v2.get(2) : ""%></b></td>
				<td width="20%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Designation
					<b> :</b>
				</td>
				<td width="25%"><b> <%=v2.get(3) != null ? v2.get(3) : ""%></b></td>
			</tr>
			<tr>
				<td width="20%" bgcolor="#E1E1E1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Employees
					work Location</td>
				<td width="3%"><b> :</b></td>
				<td width="20%"><b> <%=v2.get(4) != null ? v2.get(4) : ""%></b></td>

				<td width="20%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Department
					<b> :</b>
				</td>

				<td width="20%"><b> <%=v2.get(5) != null ? v2.get(5) : ""%></b></td>
			</tr>
			<%
				if (v2.get(6) != null) {
			%>
			<tr>
				<td width="15%" bgcolor="#E1E1E1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Type
					of Leave Applied</td>
				<td width="3%"><b> :</b></td>
				<td width="25%"><b> Casual Leave </b>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<b></b></td>
				<td width="25%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;NO.of days
					<b> :</b>
				</td>
				<td width="25%"><b><%=v2.get(7) != null ? v2.get(7) : ""%></b></td>

			</tr>
			<%
				}
			%>
			<%
				if (v2.get(8) != null) {
			%>
			<tr>
				<td width="15%" bgcolor="#E1E1E1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Type
					of Leave Applied</td>
				<td width="3%"><b> :</b></td>
				<td width="25%"><b> Sick Leave&nbsp</b>;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<b></b></td>
				<td width="25%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;NO.of days
					<b> :</b>
				</td>
				<td width="25%"><b><%=v2.get(9) != null ? v2.get(9) : ""%></b></td>

			</tr>
			<%
				}
			%>
			<%
				if (v2.get(10) != null) {
			%>
			<tr>
				<td width="15%" bgcolor="#E1E1E1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Type
					of Leave Applied</td>
				<td width="3%"><b> :</b></td>
				<td width="25%"><b>Privilege leave</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<b> </b></td>
				<td width="25%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;NO.of days
					<b> :</b>
				</td>
				<td width="25%"><b> <%=v2.get(11) != null ? v2.get(11) : ""%>
				</b></td>
				<%
					}
				%>

				<%
					if (v2.get(12) != null && v2.get(13) != null) {
				%>
			</tr>
			<tr>
				<td width="15%" bgcolor="#E1E1E1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Type
					of Leave Applied</td>
				<td width="3%"><b> :</b></td>
				<td width="25%"><b> half day </b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<b> </b></td>
				<td width="25%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Date <b>
						:</b>
				</td>
				<td><b><%=v2.get(13) != null ? v2.get(13) : ""%></b></td>

			</tr>
			<%
				}
			%>
			<%
				if (v2.get(14) != null && v2.get(15) != null) {
			%>
			<tr>
				<td width="15%" bgcolor="#E1E1E1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Type
					of Leave Applied</td>
				<td width="3%"><b> :</b></td>
				<td width="25%"><b> comp off </b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</b></td>
				<td width="25%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Date <b>
						:</b>
				</td>
				<td><b> <%=v2.get(15) != null ? v2.get(15) : ""%>
				</b></td>
				</td>
			</tr>
			<%
				}
			%>
			<tr>
				<td width="25%" bgcolor="#E1E1E1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Reason
					for leave</td>
				<td width="3%"><b> :</b></td>
				<td width="25%"><b> <%=v2.get(16) != null ? v2.get(16) : ""%></b></td>
			</tr>
			<%
				if (v2.get(17) != null && v2.get(18) != null) {
			%>
			<tr>
				<td width="25%" bgcolor="#E1E1E1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;From
					Date<b> :</b>
				</td>
				<td width="3%"><b> :</b></td>
				<td width="25%"><b><%=v2.get(17) != null ? v2.get(17) : ""%> </b></td>
				<td width="20%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;To Date<b>
						:</b>
				</td>
				<td width="25%"><b> <%=v2.get(18) != null ? v2.get(18) : ""%>
				</b></td>
			</tr>
			<%
				}
			%>
			<tr>
				<td width="25%" bgcolor="#E1E1E1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Contact
					No**</td>
				<td width="3%"><b> :</b></td>
				<td width="25%"><b> <%=v2.get(19) != null ? v2.get(19) : ""%></b></td>

			</tr>
			<%
				if (v2.get(20) != null) {
			%>
			<tr>
				<td width="25%" bgcolor="#E1E1E1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Name
					of Back-Up if leave &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;is for 2
					days or more</td>
				<td width="3%"><b> :</b></td>
				<td width="25%"><b> <%=v2.get(20) != null ? v2.get(20) : ""%></b></td>

			</tr>
			<%
				}
			%>
			<tr>
				<td width="25%" bgcolor="#E1E1E1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Leave
					Approved By</td>
				<td width="3%"><b> :</b></td>
				<td width="25%"><b> <%=v2.get(21) != null ? v2.get(21) : ""%></b></td>
			</tr>
			<tr>
				<td width="25%" bgcolor="#E1E1E1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Approvers
					Department</td>
				<td width="3%"><b> :</b></td>
				<td width="25%"><b> <%=v2.get(22) != null ? v2.get(22) : ""%></b></td>
				<td width="20%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Approvers
					Designation<b> :</b>
				</td>
				<td width="25%"><b><%=v2.get(23) != null ? v2.get(23) : ""%></b></td>
			</tr>
			<tr>
				<td width="25%" bgcolor="#E1E1E1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Balance
					leave</td>
				<td width="3%"><b> :</b></td>
				<td width="25%"><b> <%=v2.get(24) != null ? v2.get(24) : ""%></b></td>
			</tr>
		</table>
		<input type="hidden" name="mode">
	</form>
</body>
</html>