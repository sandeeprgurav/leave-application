<%@page errorPage="ErrorHandler.jsp"%>
<%@page import="java.io.*,java.sql.*"%>
<%@page import=" java.util.Hashtable"%>
<%@page import="java.util.Vector"%>
<%@page import="com.leave.LeaveBean"%>

<script language="javascript" SRC="../leave/Calender/calendar.js"></script>
<script language="javascript">

			function ValidateFunct()
			{
					/* if(document.form2.name1.value!="")
					{
							// var empcode11=new String(document.form2.name1.value);
							var myregexpr=/^[a-zA-Z][a-z0-9]*[0-9]*[a-z]*$/;			  
							if(!myregexpr.test(document.form2.name1.value))
							{
									alert("  Incorrect Employee name  ");
									document.form2.name1.value="";
							}
					}
					if(document.form2.name1.value=="")
					{
							alert(" Please Enter  Name  ");
					}
					else if(document.form2.source6.value=="SELECT")
					{
							alert(" Please Select Designation  ");
					}

					//System.out.println("forward to search_for_approve.jsp page");
					if(document.form2.name1.value!="" && document.form2.source6.value!="SELECT")
					{     */
							document.form2.mode.value="Searchapp";
							document.form2.action="/LeaveApplication/servlet/leave.SearchController";
							document.form2.submit();
					//}
			}
			function Submit()
			{
					document.form2.mode.value="updateleave";
					document.form2.action="/LeaveApplication/servlet/leave.SearchController";
					document.form2.submit();
					alert("Application Approved Successfully");
					alert("Mail is send to HR successfully");
			}
			function  backfunc()
			{
					document.form2.action="../leave/first_page.jsp";
					document.form2.submit();
			}

			function detail1(no_row)
			{
				 if(no_row<=1)
				  { 
								   if(document.form2.select.checked==true)
									{    
										   document.getElementById("fileflg1").style.visibility="visible";
										   document.form2.reject.checked=false;
									}
									if(document.form2.select.checked==false)
									{
										 document.getElementById("fileflg1").style.visibility="hidden";

									}				  
				   } else {
						 for(var i=0;i<no_row;i++)
						   {
									if(document.form2.select[i].checked==true)
									{    
										   document.getElementById("fileflg1").style.visibility="visible";
										   document.form2.reject[i].checked=false;
									}
									if(document.form2.select[i].checked==false)
									{
										// document.getElementById("fileflg1").style.visibility="hidden";
									}
							}	 
					}
			}

			function detail2(no_row)
			{
			  if(no_row<=1)
			   {
		           if(document.form2.reject.checked==true) {    
					   document.getElementById("fileflg1").style.visibility="visible";
					   document.form2.select.checked=false;
					}
					if(document.form2.reject.checked==false) {
					 document.getElementById("fileflg1").style.visibility="hidden";
					}
				} else {
					for(var i=0;i<no_row;i++) {
						if(document.form2.reject[i].checked==true) {
							document.getElementById("fileflg1").style.visibility="visible";
							document.form2.select[i].checked=false;
						} if(document.form2.reject[i].checked==false) {
							// document.getElementById("fileflg1").style.visibility="hidden";
						}
					}
				}
			}

			function xyz(rid) {
				window.open("../leave/leaveapp_display.jsp?rid="+rid+"","", "location=0,status=1,menubar=1,scrollbars=1,resizable=1,width=900,height=420");	
				return true;
			}
    </script>
<%
	LeaveBean lebean1 = null;
	lebean1 = (LeaveBean) session.getAttribute("foundData1");
%>
<html>
<head>
</head>
<BODY bgcolor="#FFFFFF" width="600" height="600">
	<form name="form2" method="post" action="">
		<center>
			<table align="center" class='TableBorder' border="1" width="525"
				bordercolor="#003163">
				<tr>
					<td width="515" bgcolor="">
						<p align="center">
							<font color="black" face="Verdana"><b>APPROVE SCREEN</b></font>
					</td>
				</tr>
				<tr>
					<td width="515" bgcolor=""><center>
							<b> <font color="black" face="Verdana" size="2">&nbsp;NAME&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></b>

							<input type="text" readonly name="name1" size="22" maxlength="16"
								value="<%=lebean1.getName() != null ? lebean1.getName() : ""%>">
						</center></td>
				</tr>
				<tr>
					<td width="515" bgcolor="">
						<p align="center">
							<b><font color="black" face="Verdana" size="2">&nbsp;DESIGNATION&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></b>

							<input type="text" readonly name="source6" size="22"
								value="<%=lebean1.getDesignation() != null ? lebean1.getDesignation() : ""%> ">
					</td>
				</tr>
				<tr>
					<TD align="center" width="515">
						<center>
							<input type="button" value="Submit" onClick="ValidateFunct()">
							<input type="button" value="Back" onClick="backfunc()" />
						</center>
					</TD>
				</tr>
			</table>
			<%
				String smode = null;
				Vector v1 = null;
				String approveby = null;
				int datasize = 0;

				if (lebean1 != null) {
					approveby = lebean1.getName();
					smode = lebean1.getMode();
				}

				if (smode == null) {
					// System.out.println("smode====>>>>"+smode);
				}

				Vector data = null;
				Vector calhead = null;
				int vsize = 0;

				data = (Vector) request.getAttribute("data1");
				calhead = (Vector) request.getAttribute("calhead1");
				System.out.println("data in approve page====>>>" + calhead);
				System.out.println("data in approve page====>>>" + data);

				if (smode != null && data != null && calhead != null)
					if (smode.equals("view")) {

						lebean1.setMode("show_detail");
						int c = calhead.size();
						datasize = data.size();
						System.out.println("cols count========>>>>" + c);
						System.out.println("cols count========>>>>" + datasize);
			%>
			<TABLE class="TableBorder" bordercolor="#003163" bgcolor="white"
				border="0">
				<tr width="600">
					<td></td>
				</tr>
				<tr width="600">
					<td></td>
				</tr>
				<tr width="600">
					<td></td>
				</tr>
				<tr width="600">
					<td></td>
				</tr>
				<tr width="600">
					<td></td>
				</tr>
				<tr width="600">
					<td></td>
				</tr>
			</table>
			<TABLE class="TableBorder" bordercolor="#003163" bgcolor="white"
				border="1">
				<TR align="center" bgcolor="#E1E1E1">
					<%
						for (int i = 0; i < c - 11; i++) {
					%>
					<td class="th_style"><b><center><%=calhead.get(i)%></center></b></td>
					<%
						}
					%>
					<td class="th_style"><b>APPROVE</b></td>
					<td class="th_style"><b>REJECT</b></td>
				</TR>

				<%
					for (int j = 0; j < datasize; j++) {
				%>
				<%
					v1 = (Vector) data.get(j);
								vsize = v1.size() > 0 ? v1.size() : 0;
				%>
				<tr>
					<%
						String fromdate = null;
						String todate = null;
						String type = null;
						String empcode = null;
						String entrydate = null;
						String comp_off_date = null;
						String half_day_date = null;
						String privilege_day = null;
						String casual_day = null;
						String sick_day = null;
						String rid = null;

					for (int i = 0; i < vsize; i++) {
					%>
					<%
						if (i == 1) {
					%>
					<td><center>
							<a onClick="xyz(<%=v1.get(vsize - 5)%>);"
								href="javascript:dummy();"> <%=v1.get(i) != null ? v1.get(i) : ""%></a>
						</center></td>
					<%
						} else {
					%>
					<%
						if (i == 4 || i == 5 || i == 6 || i == 7 || i == 8 || i == 9 || i == 10 || i == 11
													|| i == 12 || i == 13 || i == 14) {
					%>
					<%
						} else {
					%>
					<td><center><%=v1.get(i) != null ? v1.get(i) : ""%></center></td>
					<%
						}
					%>
					<%
						}
					%>
					<%
						if (i == 0) {
							entrydate = (String) v1.get(i);
								}
								if (i == 2) {
									empcode = (String) v1.get(i);
								}
								if (i == 5) {
									fromdate = (String) v1.get(i);
								}

								if (i == 6) {
									todate = (String) v1.get(i);
								}
								if (i == 7) {
									type = (String) v1.get(i);
								}

								if (i == 9) {
									comp_off_date = (String) v1.get(i);
								}
								if (i == 11) {
									half_day_date = (String) v1.get(i);
								}

								if (i == 12) {
									privilege_day = (String) v1.get(i);
								}
								if (i == 13) {
									casual_day = (String) v1.get(i);
								}
								if (i == 14) {
									sick_day = (String) v1.get(i);
								}
								if (i == 10) {
									rid = (String) v1.get(i);
								}
					%>
					<%
						}
					%>
					<td><center>
							<input type="checkbox" name="select"
								value=" <%=approveby%>~<%=fromdate%>~<%=todate%>~<%=type%>~<%=empcode%>~<%=entrydate%>~<%=comp_off_date%>~<%=half_day_date%>~<%=privilege_day%>~<%=casual_day%>~<%=sick_day%>~<%=rid%>"
								onClick="detail1('<%=datasize%>')">
						</center></td>
					</center>
					<td><center>
							<input type="checkbox" name="reject"
								value="<%=approveby%>~<%=empcode%>~<%=entrydate%>"
								onClick="detail2('<%=datasize%>')">
						</center></td>
					</center>
				</tr>
				<%
					}
				%>
			</table>
			<tr>
				<center>
					<table id="fileflg1" style="visibility: hidden" class='TableBorder'
						border="1">
						<tr>
							<td><input type="button" value="SUBMIT" name="approve"
								onClick="Submit()"></td>
						</tr>
					</table>
					<input type="button" value="Back" onClick="backfunc()" />
				</center>
			</tr>
			<%
				}
			%>
			<input type="hidden" name="mode" value="">
		</center>
	</form>
</body>
</html>