<%@page  errorPage="ErrorHandler.jsp"%>
<%@page import= "java.io.*,java.sql.*"%>
<%@page import=" java.util.Hashtable"%>
<%@page import="java.util.Vector"%>
<%@page import="com.leave.LeaveBean"%>

<script language="javascript" SRC="../leave/Calender/calendar.js"></script>
<script language="javascript">
		function ValidateFunct() {
				/*
				if(document.form3.name.value!="")
				{
						// var empcode11=new String(document.form2.name1.value);
						var myregexpr=/^[a-zA-Z][a-z0-9]*[0-9]*[a-z]*$/;			  
						if(!myregexpr.test(document.form3.name.value))
						{
								alert("  Incorrect Employee name  ");
								document.form3.name.value="";
						}
				}

				if(document.form3.empcode.value!="")
				{
						var myregexpr=/^[a-zA-Z]{1}[a-z0-9]*[0-9]+[a-z]*$$/;
						if(!myregexpr.test(document.form3.empcode.value))
						{
							alert("Employee code must be Alphanumeric");
							document.form3.empcode.value="";
						}
				}


				if(document.form3.name.value=="")
				{
						alert(" Please Enter  Name  ");
				}

				else if(document.form3.empcode.value=="")
				{
						alert(" Please Enter Employee code  ");
				}

				*/
						document.form3.mode.value="leave_detail";
						document.form3.action="/LeaveApplication/servlet/leave.SearchController";
						document.form3.submit();
		}

		function  backfunc()
		{
			document.form3.action="../leave/first_page.jsp";
			document.form3.submit();
		}


		function detail1() {
			if(document.form3.select.checked)
			{
					document.form3.reject.checked=false;
			}
		}

		function detail2() {
			if(document.form3.reject.checked)
			{
					document.form3.select.checked=false;
			}
		}
	</script>
    <%
		Hashtable errors=null;
		Hashtable values=null;

		errors=(Hashtable)request.getAttribute("LeaveErrors1");
		values=(Hashtable)request.getAttribute("LeaveValues1");
		System.out.println("LeaveErrors===>"+errors);
     %>
<html>
<head>
</head>

<BODY bgcolor="#FFFFFF" width="600" height="600">
<form name="form3" method="post" action="">
<center>
<table align="center" class= 'TableBorder'  border="1" width="525" bordercolor="#003163">
	  <tr>
          <td width="515" bgcolor="">
            <p align="center"><font color="black" face="Verdana" ><b>LEAVE SUMMERY SCREEN</b></font>
          </td>
      </tr>
       <tr>
          <td width="515" bgcolor=""><center><b>
                 <font color="black" face="Verdana" size="2">&nbsp;EMPLOYEE NAME&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></b>

                <input type="text" name="name3" size="22" value="<%=values!=null && values.get("Name1")!=null?values.get("Name1"):""%>"maxlength="16" >
               <%=errors!=null&&errors.get("Name1")!=null?errors.get("Name1"):""%>
                     </center>
		  </td>
		</tr>
        <tr>
           <td width="515" bgcolor="">
                <p align="center"><b><font color="black" face="Verdana" size="2">&nbsp;EMPLOYEE CODE &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></b>
                 <input type="text" name="empcode3" value="<%=values!=null&&values.get("Empcode1")!=null?values.get("Empcode1"):""%>" size="22" maxlength="16" >
				<%=errors!=null&&errors.get("Empcode1")!=null?errors.get("Empcode1"):""%>
				 </td>
         </tr>
          <tr>
               <TD  align = "center" width="515">
                     <center> <input type ="Submit"   value="Submit" onClick ="ValidateFunct()">
					 <input  type="button" value="Back" bgcolor="white" onClick="backfunc()" /></center>
               </TD>
          </tr>
</table>
 <% 
				String smode=null;
				LeaveBean lebean1=null;
				lebean1= (LeaveBean)request.getAttribute("LeaveBean");
				if(lebean1!=null)
				{
						smode =lebean1.getMode();
				}
				if(smode==null)
				{
						System.out.println("smode====>>>>"+smode);
				}
				Vector  data=null;
				Vector calhead=null;
				data=(Vector)request.getAttribute("data1");
				calhead=(Vector)request.getAttribute("calhead1");
				
				if(smode!=null  && data!=null && calhead!=null )
	            if(smode.equals("view")) {
				int c=calhead.size();
				int d=data.size();
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
					<%for(int i=0;i<c;i++) {%>
					<td class="th_style"><b><center><%=calhead.get(i)%>
								<%
								 if(i==3){%>
									(max=15)
								<% }
									if(i==4){%>
								(max=15)
								<% } if(i==5) {%>
								(max=15)
								<% } %>
							</center></b></td>
					<%}%>
				</TR>
				<%for(int j=0;j<d;j++) {%>
				<%  Vector v1=null;
					v1=(Vector)data.get(j);  
    	    	    int vsize= v1.size()>0 ? v1.size():0;
					 %>
				<tr>
					<%
		               for(int i=0;i<vsize;i++) {%>
						<td><center><%=v1.get(i)!=null ? v1.get(i):"" %></center></td>
					<%}%>
				</tr>
				<%}%>
			</table>
			<% }%>
  <input type="hidden" name="mode" >
</center>
</form>
</body>
</html>