package com.leave;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Leavedbconnector1 extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		selectApproveData(request, response);

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		selectApproveData(request, response);
	}

	public void selectApproveData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		LeaveBean founData = (LeaveBean) request.getAttribute("LeaveBean1");
		String smode = founData.getMode();
		String login_name = "";
		String login_password = "";
		System.out.println("value of smode inside dbconnector1========>>" + smode);

		if (smode.equals("Searchapp")) {

			Connection connection = null;
			PreparedStatement pStmt = null;
			ResultSet rs = null;

			Vector data = null;
			data = new Vector();
			Vector calhead = null;
			calhead = new Vector();

			response.setContentType("text/html");
			RequestDispatcher requestDispatcher = null;

			String query = "";

			String desig = founData.getDesignation();
			String name = founData.getName();
			System.out.println("Approver Designation====>>" + name);

			System.out.println("Approver Designation====>>" + founData.getDesignation());
			// query="SELECT * from LEAVEAPP " ,;
			query = "SELECT (DATE_FORMAT(ENTRY_DATE, \" %Y-%m-%d\"))ENTRY_DATE,EMPLOYEE_NAME,EMPLOYEE_CODE,DESIGNATION,REASON_FOR_LEAVE,(DATE_FORMAT(FROM_DATE, \" %Y-%m-%d\") )FROM_DATE,(DATE_FORMAT(TO_DATE, \" %Y-%m-%d\"))TO_DATE, type,NAME_OF_BACKUP, (DATE_FORMAT(comp_off_date, \" %Y-%m-%d\"))comp_off_date,rid,(DATE_FORMAT(half_day_date, \" %Y-%m-%d\"))half_day_date,privilege_day,casual_day,sick_day  from LEAVEAPP1  where LEAVE_APPROVED_BY='"
					+ (name) + "' and APPROVERS_DESIGNATION='" + desig
					+ "' and approve_by is null and approve_date is null ";
			System.out.println("i m inside leavedbconnector" + query);

			try

			{

				connection = DBCon.getConnection();
				pStmt = connection.prepareStatement(query);
				rs = pStmt.executeQuery();

				ResultSetMetaData rsmd = rs.getMetaData();

				int col = rsmd.getColumnCount();

				Vector v;
				System.out.println(col);

				for (int i = 1; i <= col; i++)

				{

					System.out.println("cols of table==========>>>>" + rs.getMetaData().getColumnName(i));
					calhead.addElement(rs.getMetaData().getColumnName(i));

				}
				System.out.println(calhead);
				while (rs.next())

				{
					v = new Vector();

					for (int i = 1; i <= col; i++)

					{
						String got = rs.getString(i);
						System.out.println("data of table=====>>>>>>" + got);
						v.addElement(got);

					}

					data.addElement(v);
					System.out.println(data);

				}

				System.out.println("****************i m inside asdas*************");

				/*
				 * if(rs.next())
				 * 
				 * {
				 * 
				 * 
				 * founData.setName(rs.getString("EMPLOYEE_NAME1"));
				 * founData.setEmpcode(rs.getString("EMPLOYEE_CODE1"));
				 * founData.setDesignation(rs.getString("DESIGNATION"));
				 * founData.setDepartment(rs.getString("DEPARTMENT"));
				 * founData.setReason(rs.getString("REASON_FOR_LEAVE"));
				 * founData.setFromdate(rs.getString("FROM_DATE"));
				 * founData.setTodate(rs.getString("TO_DATE"));
				 * 
				 * }
				 */

				connection.close();
			} catch (Exception e) {
				System.out.println("Exception occured=========>>" + e);
			}

			/*
			 * finally {
			 * 
			 * if(pStmt!=null) { pStmt.close(); }
			 * 
			 * if(rs!=null) { rs.close(); }
			 * 
			 * 
			 * if(connection!=null) { connection.close(); }
			 * 
			 * 
			 * }
			 */

			request.setAttribute("data1", data);
			request.setAttribute("calhead1", calhead);

			if (data.size() > 0 && calhead.size() > 0)

			{

				founData.setMode("view");
				request.getSession().setAttribute("foundData1", founData);
				System.out.println("<<<<<<<<=============data are retrive successfully=======>>" + founData.getMode());
				requestDispatcher = request.getRequestDispatcher("../leave/leaveapp1.jsp");
				requestDispatcher.forward(request, response);
			} else {

				response.getWriter().println("<script>alert('Data for  employee is not available')</script>");
			}

		}

		else if (smode.equals("Searchhr"))

		{
			Connection connection = null;
			PreparedStatement pStmt = null;
			ResultSet rs = null;

			Vector data = null;
			data = new Vector();
			Vector calhead = null;
			calhead = new Vector();

			response.setContentType("text/html");
			RequestDispatcher requestDispatcher = null;

			String query = "";

			String desig = founData.getDesignation();
			String name = founData.getName();
			String empcode = founData.getEmpcode();
			System.out.println("Approver Designation====>>" + name);

			System.out.println("Approver Designation====>>" + founData.getEmpcode());
			// query="SELECT * from LEAVEAPP " ;
			query = "SELECT (DATE_FORMAT(l.ENTRY_DATE, \" %Y-%m-%d\"))ENTRY_DATE,l.EMPLOYEE_NAME,l.EMPLOYEE_CODE,l.DESIGNATION ,l.approve_by , (DATE_FORMAT(l.approve_date, \" %Y-%m-%d\"))approve_date,m.balance_leave,l.rid  from LEAVEAPP1 l,leaveinfo m where l.EMPLOYEE_CODE=m.EMPLOYEE_CODE and l.EMPLOYEE_CODE='"
					+ empcode + "'  and l.approve_by is not null and l.approve_date is not null ";
			// and l.approve_by is null and l.approve_date is null

			System.out.println("i m inside leavedbconnector" + query);

			try

			{

				connection = DBCon.getConnection();
				pStmt = connection.prepareStatement(query);
				rs = pStmt.executeQuery();

				ResultSetMetaData rsmd = rs.getMetaData();

				int col = rsmd.getColumnCount();

				Vector v;
				System.out.println(col);

				for (int i = 1; i <= col; i++)

				{

					System.out.println("cols of table==========>>>>" + rs.getMetaData().getColumnName(i));
					calhead.addElement(rs.getMetaData().getColumnName(i));

				}
				System.out.println(calhead);

				while (rs.next())

				{
					v = new Vector();

					for (int i = 1; i <= col; i++)

					{
						String got = rs.getString(i);
						System.out.println("data of table=====>>>>>>" + got);
						v.addElement(got);

					}

					data.addElement(v);
					System.out.println(data);

				}

				System.out.println("****************i m inside asdas*************");
				connection.close();

			}

			catch (Exception e) {
				System.out.println("Exception occured=========>>" + e);
			}

			/*
			 * finally {
			 * 
			 * if(pStmt!=null) { pStmt.close(); }
			 * 
			 * if(rs!=null) { rs.close(); }
			 * 
			 * 
			 * if(connection!=null) { connection.close(); }
			 * 
			 * 
			 * }
			 */

			request.setAttribute("data1", data);
			request.setAttribute("calhead1", calhead);

			if (data.size() > 0 && calhead.size() > 0)

			{

				founData.setMode("view");
				request.getSession().setAttribute("foundData1", founData);
				System.out.println("<<<<<<<<=============data are retrive successfully=======>>" + founData.getMode());
				requestDispatcher = request.getRequestDispatcher("../leave/leaveapp2.jsp");
				requestDispatcher.forward(request, response);
			} else {

				response.getWriter().println("<script>alert('Data for  employee is not available')</script>");
			}

		}

		else if (smode.equals("leave_detail"))

		{

			Vector v;

			Vector data = null;
			data = new Vector();
			Vector calhead = null;
			calhead = new Vector();

			Connection connection = null;
			PreparedStatement pStmt = null;
			ResultSet rs = null;

			response.setContentType("text/html");
			RequestDispatcher requestDispatcher = null;

			// String desig=founData.getDesignation();
			String name = founData.getName();
			String empcode = founData.getEmpcode();

			String query = "select employee_name,designation,employee_code,privilege_leave,sick_leave,casual_leave,half_day,comp_off,extra_leave,Balance_leave from leaveinfo  where employee_code='"
					+ empcode + "' ";
			System.out.println(" Query for retriving leave detail====>>>" + query);

			try {

				connection = DBCon.getConnection();
				pStmt = connection.prepareStatement(query);
				rs = pStmt.executeQuery();

				ResultSetMetaData rsmd = rs.getMetaData();

				int col = rsmd.getColumnCount();
				System.out.println(col);

				for (int i = 1; i <= col; i++)

				{

					System.out.println("cols of table==========>>>>" + rs.getMetaData().getColumnName(i));
					calhead.addElement(rs.getMetaData().getColumnName(i));

				}
				System.out.println(calhead);

				while (rs.next())

				{
					v = new Vector();

					for (int i = 1; i <= col; i++)

					{
						String got = rs.getString(i);
						System.out.println("data of table=====>>>>>>" + got);
						v.addElement(got);

					}

					data.addElement(v);
					System.out.println(data);

				}

				System.out.println("****************i m inside asdas*************");

				connection.close();

			} catch (Exception e) {
				System.out.println("Exception occured in retriving of leaveinfo=====>>>>" + e);

			}

			request.setAttribute("data1", data);
			request.setAttribute("calhead1", calhead);

			if (data.size() > 0 && calhead.size() > 0)

			{

				founData.setMode("view");
				request.setAttribute("foundData1", founData);
				System.out.println("<<<<<<<<=============data are retrive successfully=======>>" + founData.getMode());
				requestDispatcher = request.getRequestDispatcher("../leave/leaveapp3.jsp");
				requestDispatcher.forward(request, response);
			}

		}

		else if (smode.equals("login_applicant"))

		{
			Vector v;

			// Vector calhead=null;
			// calhead=new Vector();

			Connection connection = null;
			PreparedStatement pStmt = null;
			ResultSet rs = null;
			PreparedStatement pStmt1 = null;
			ResultSet rs1 = null;
			PreparedStatement pStmt3 = null;
			ResultSet rs3 = null;
			response.setContentType("text/html");
			RequestDispatcher requestDispatcher = null;

			// String desig=founData.getDesignation();
			String name = founData.getName();
			String password = founData.getEmpcode();
			String empcode1 = null;
			String date = null;
			String query1 = "SELECT DATE_FORMAT(now(), \" %Y-%m-%d\")sysdate1";
			System.out.println(" Query for retriving leave detail====>>>" + query1);
			String query = "select  employee_name , employee_code,designation , emp_work_loc ,department,contact_no from leaveinfo where password='"
					+ password + "' ";
			System.out.println(" Query for retriving leave detail====>>>" + query);
			String query2 = "select employee_name,password from leaveinfo where password='" + password + "' ";
			System.out.println(" Query for retriving leave detail====>>>" + query2);

			try {
				connection = DBCon.getConnection();
				pStmt1 = connection.prepareStatement(query1);
				rs1 = pStmt1.executeQuery();

				pStmt = connection.prepareStatement(query);
				rs = pStmt.executeQuery();

				pStmt3 = connection.prepareStatement(query2);
				rs3 = pStmt3.executeQuery();

				while (rs1.next()) {
					date = rs1.getString("sysdate1");
				}

				while (rs.next()) {
					String empname = rs.getString("employee_name");
					empcode1 = rs.getString("employee_code");
					String designation = rs.getString("designation");
					String emp_work_loc = rs.getString("emp_work_loc");
					String department = rs.getString("department");
					String contact_no = rs.getString("contact_no");

					System.out.println("empname==>>" + empname);
					System.out.println("empcode1==>>" + empcode1);
					System.out.println("designation==>>" + designation);
					System.out.println("emp_work_loc==>>" + emp_work_loc);
					System.out.println("department==>>" + department);
					System.out.println("contact_no==>>" + contact_no);

					founData.setName(empname);
					founData.setDate(date);
					founData.setEmpcode(empcode1);
					founData.setDesignation(designation);
					founData.setEmpworkloc(emp_work_loc);
					founData.setDepartment(department);
					founData.setContact(contact_no);

				}

				// ResultSetMetaData rsmd=rs3.getMetaData();

				// int col=rsmd.getColumnCount();

				while (rs3.next())

				{

					login_name = rs3.getString("employee_name");
					login_password = rs3.getString("password");
					System.out.println("data of table=====>>>>>>" + login_name);

				}

				if (empcode1 != null)

				{

					founData.setMode("connect");
					response.getWriter().println(" <html><body> <input type='hidden' name='mode2'/></body></html>");
					request.getSession(true);
					request.getSession().setAttribute("foundData1", founData);
					request.getSession().setAttribute("login_name1", login_name);
					request.getSession().setAttribute("login_password1", login_password);
					System.out.println(
							"<<<<<<<<=============data are retrive successfully=======>>" + founData.getMode());
					requestDispatcher = request.getRequestDispatcher("../leave/default.jsp");
					requestDispatcher.forward(request, response);

				}

				else {

					response.getWriter().println("<script>alert('Invalid Password')</script>");
					response.getWriter().println("<script>window.navigate('../leave/loginpage_app.jsp')</script>");

				}

			}

			catch (Exception e) {

				System.out.println("Exception occured where modelogin_applicant=" + e);
			}

		}

		else if (smode.equals("login_approver"))

		{

			Connection connection = null;
			PreparedStatement pStmt = null;
			ResultSet rs = null;

			response.setContentType("text/html");
			RequestDispatcher requestDispatcher = null;

			// String desig=founData.getDesignation();
			String name = founData.getName();
			String password = founData.getEmpcode();
			String designation = null;
			String name1 = null;

			String query = "select  employee_name,designation from leaveinfo where password='" + password + "' ";
			System.out.println(" Query for retriving leave detail====>>>" + query);

			try {

				connection = DBCon.getConnection();
				pStmt = connection.prepareStatement(query);
				rs = pStmt.executeQuery();
				while (rs.next()) {
					name1 = rs.getString("employee_name");
					designation = rs.getString("designation");
					System.out.println("name1==>>" + name1);
					System.out.println("designation==>>" + designation);

				}

				if (name1 != null && designation != null) {

					founData.setMode("");
					founData.setName(name1);
					founData.setDesignation(designation);

					request.setAttribute("foundData1", founData);
					System.out.println(
							"<<<<<<<<=============data are retrive successfully=======>>" + founData.getMode());
					requestDispatcher = request.getRequestDispatcher("../leave/leaveapp1.jsp");
					requestDispatcher.forward(request, response);

				} else {

					response.getWriter().println("<script>alert('Invalid Password')</script>");

				}

			}

			catch (Exception e) {

				System.out.println("Exceptio0n occureed in verifying login approver===>>" + e);

			}

		}

		else if (smode.equals("connect"))

		{
			response.setContentType("text/html");
			RequestDispatcher requestDispatcher = null;

			System.out.println("i m inside leavedbconnector where smode=connect");

			String empname = founData.getName();
			String empcode = founData.getEmpcode();
			String entry_date = founData.getDate();
			String designation = founData.getDesignation();
			String empWorkLoc = founData.getEmpworkloc();
			String department = founData.getDepartment();
			String privledgeLeaveChx = founData.getPrivilegechx() != null ? founData.getPrivilegechx() : "off";
			String Privilege_day = founData.getPrivilege_day();
			String casualLeaveChx = founData.getCasualchx() != null ? founData.getCasualchx() : "off";
			String Casual_day = founData.getCasual_day();
			String SickLeaveChx = founData.getSickchx() != null ? founData.getSickchx() : "off";
			String Sick_day = founData.getSick_day();
			String halfLeaveChx = founData.getHalfchx() != null ? founData.getHalfchx() : "off";
			String Half_day_date = founData.getHalf_day_date();
			String compOffChx = founData.getCompchx() != null ? founData.getCompchx() : "off";
			String compoff_date = founData.getCompoff_date();
			String type = null;
			String reason = founData.getReason();
			String fromdate = founData.getFromdate();
			String todate = founData.getTodate();
			String contact = founData.getContact();
			String namebackup = founData.getNamebackup();
			String leaveappvby = founData.getLeaveappvby();
			String appDept = founData.getAppdept();
			String appDesig = founData.getAppdesig();
			String approveby = null;
			String approve_date = null;
			String rejectby = null;
			String reject_date = null;
			String balanceleave = null;

			System.out.println("empcode1===>>>" + empcode);
			System.out.println("Designation===>>>" + designation);
			System.out.println("Empworkloc===>>>" + empWorkLoc);
			System.out.println("Privilege===>>>" + privledgeLeaveChx);

			StringBuffer type_leave = null;
			type_leave = new StringBuffer();

			if (casualLeaveChx.equalsIgnoreCase("on")) {
				type_leave.append("casual_leave/");
			} else {
				type_leave.append("casual_off/");
			}

			if (SickLeaveChx.equalsIgnoreCase("on")) {
				type_leave.append("sick_leave/");
			} else {
				type_leave.append("sick_off/");
			}

			if (compOffChx.equalsIgnoreCase("on")) {
				type_leave.append("comp_off/");
			} else {
				type_leave.append("comp_ffo/");
			}
			if (halfLeaveChx.equalsIgnoreCase("on")) {
				type_leave.append("half_day/");
			} else {
				type_leave.append("half_off/");
			}
			if (privledgeLeaveChx.equalsIgnoreCase("on")) {
				type_leave.append("privilege_leave");
			} else {
				type_leave.append("privilege_off");
			}

			type = type_leave.toString();

			try

			{

				Connection connection = null;
				connection = DBCon.getConnection();
				// con.setAutoCommit(false);
				System.out.println("connect=====>>>>data connection successfully done" + connection);
				/*
				 * java.sql.Statement smt = null; java.sql.ResultSet rs =null; String
				 * sql="insert into dept (deptno,dname,loc) values (empno,empname,source2)";
				 * smt=con.createStatement(); rs=smt.executeQuery(sql);
				 */

				System.out.println("samdf=====end>>>>data connection successfully done");

				String str1 = "insert into LEAVEAPP1 values(?,?,?,STR_TO_DATE(?, '%Y-%m-%d'),?,?,?,?,?,?,?,?,?,?,STR_TO_DATE(?, '%Y-%m-%d'),?,STR_TO_DATE(?, '%Y-%m-%d'),?,?,STR_TO_DATE(?, '%Y-%m-%d'),STR_TO_DATE(?, '%Y-%m-%d'),?,?,?,?,?,?,STR_TO_DATE(?, '%Y-%m-%d'),?,STR_TO_DATE(?, '%Y-%m-%d'),?)";
				System.out.println("insert query===>>" + str1);
				PreparedStatement st = connection.prepareStatement(str1);

				st.setString(1, IdUtil.generateId());
				st.setString(2, empname);
				st.setString(3, empcode);
				st.setString(4, entry_date);
				st.setString(5, designation);
				st.setString(6, empWorkLoc);
				st.setString(7, department);
				st.setString(8, privledgeLeaveChx);
				st.setString(9, Privilege_day);
				st.setString(10, casualLeaveChx);
				st.setString(11, Casual_day);
				st.setString(12, SickLeaveChx);
				st.setString(13, Sick_day);
				st.setString(14, halfLeaveChx);
				st.setString(15, Half_day_date);
				st.setString(16, compOffChx);
				st.setString(17, compoff_date);
				st.setString(18, type);
				st.setString(19, reason);
				st.setString(20, fromdate);
				st.setString(21, todate);
				st.setString(22, contact);
				st.setString(23, namebackup);
				st.setString(24, leaveappvby);
				st.setString(25, appDept);
				st.setString(26, appDesig);
				st.setString(27, approveby);
				st.setString(28, approve_date);
				st.setString(29, rejectby);
				st.setString(30, reject_date);
				
				

				PreparedStatement pSt1 = null;
				ResultSet rss = null;

				String updatequery1 = "select  Balance_leave from leaveinfo  where employee_code='" + empcode + "' ";
				pSt1 = connection.prepareStatement(updatequery1);
				rss = pSt1.executeQuery();

				while (rss.next()) {
					balanceleave = rss.getString("Balance_leave");
					System.out.println("balanceleave===>>>" + balanceleave);
				}

				st.setString(31, balanceleave);

				st.executeUpdate();
				// con.commit();
				/*
				 * String str1="select * from dept "; PreparedStatement
				 * st=con.prepareStatement(str1); ResultSet rs1=st.executeQuery();
				 * 
				 * while(rs1.next()) { String plsrno1=rs1.getString("deptno"); String
				 * dg_code1=rs1.getString("dname"); String br_code1=rs1.getString("loc");
				 * System.out.println("samdf=====end123>>>>data connection successfully done");
				 * System.out.println("dg_code1=="+dg_code1);
				 * System.out.println("loc=="+br_code1); }
				 * 
				 */

				// con.setAutoCommit(true);

				System.out.println("i m inside if loop where smode  = mail");
				String message = null;
				String to1 = null;
				String from1 = null;
				String host1 = null;
				message = "Please approve my apllied  Leave Application. \n\n Thanking You.  ";
				String email_from = null;

				String sql = "select email_id from leaveinfo where employee_code='" + empcode + "'";
				PreparedStatement st1 = connection.prepareStatement(sql);
				ResultSet rs2 = st1.executeQuery();

				while (rs2.next()) {

					email_from = rs2.getString("email_id");
					System.out.println("email_from ==>>>>>>>" + email_from);

				}

				try {
					// String aprover_name=founData.getleaveappvby1
					String email_to = null;
					String sqlto = "select email_id from leaveinfo where employee_name='" + leaveappvby
							+ "' and designation='" + appDesig + "' and department='" + appDept + "'  ";
					System.out.println("email_to query==>>" + sqlto);
					Statement st2 = connection.createStatement();
					ResultSet rs3 = st2.executeQuery(sqlto);

					while (rs3.next()) {
						email_to = rs3.getString(1);
						System.out.println("email_to after xchange==>>" + email_to);

					}

					to1 = email_to;
					from1 = email_from;
					host1 = "192.168.25.5";

					//sendMailFile1 s2 = new sendMailFile1();
					//s2.sendMail(to1, from1, host1, message);

				} catch (Exception es) {
					System.out.println("Exception==>>" + es);
					es.printStackTrace();
				}

			}

			catch (Exception e2) {
				System.out.println("Exception occured in insert  query=====>>>>" + e2);
			}

			request.setAttribute("foundData1", founData);
			requestDispatcher = request.getRequestDispatcher("../leave/leaveapp.jsp");
			requestDispatcher.forward(request, response);

		}

	}

}