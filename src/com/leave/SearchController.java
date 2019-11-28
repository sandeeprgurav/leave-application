
package com.leave;

import java.io.IOException;
import java.util.Hashtable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchController extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException

	{
		processRequest(request, response);

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException

	{
		processRequest(request, response);
	}

	public void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException

	{

		/*
		 * private boolean setSessionAttribute(String uername,HttpSession session)
		 * 
		 * {
		 * 
		 * LeaveBean lebean=new LeaveBean(); try{
		 * session.setAttribute("beanvalues",lebean); return true; }
		 * 
		 * catch(Exception ex) { ex.printStackTrace(); return false; }
		 * 
		 * }
		 */

		response.setContentType("text/html");
		RequestDispatcher requestDispatcher = null;
		String hdnMode = request.getParameter("mode") == null ? "" : request.getParameter("mode");
		System.out.println("hdnMode====>" + hdnMode);
		LeaveBean lebean = new LeaveBean();

		response.getWriter().println("<script>alert('i m inside servlet controller')</script>");

		lebean.setMode(hdnMode);

		if (hdnMode.equals("Searchhr"))

		{

			lebean.setName(request.getParameter("name") == null ? "" : request.getParameter("name").trim());
			lebean.setEmpcode(request.getParameter("empcode") == null ? "" : request.getParameter("empcode").trim());

			if (validateSearch1(lebean, request))

			{

				request.setAttribute("LeaveBean", lebean);
				requestDispatcher = request.getRequestDispatcher("/servlet/leave.Leavedbconnector");
				requestDispatcher.forward(request, response);
				System.out.println("i m inside searchcontroller hr");
			}

			else

			{
				request.setAttribute("LeaveBean", lebean);
				requestDispatcher = request.getRequestDispatcher("../leave/leaveapp2.jsp");
				requestDispatcher.forward(request, response);
			}

		}

		if (hdnMode.equals("Searchapp"))

		{

			lebean.setName(request.getParameter("name1") == null ? "" : request.getParameter("name1").trim());
			lebean.setDesignation(
					request.getParameter("source6") == null ? "" : request.getParameter("source6").trim());

			if (validateSearch(lebean, request))

			{

				request.setAttribute("LeaveBean", lebean);
				requestDispatcher = request.getRequestDispatcher("/servlet/leave.Leavedbconnector");
				requestDispatcher.forward(request, response);
			}

			else

			{

				request.setAttribute("LeaveBean", lebean);
				requestDispatcher = request.getRequestDispatcher("../leave/leaveapp1.jsp");
				requestDispatcher.forward(request, response);
			}

		}

		if (hdnMode.equals("updateleave")) {

			String[] checkValues = request.getParameterValues("select");
			String[] checkValues1 = request.getParameterValues("reject");
			System.out.println("Values of checkbox select ======>>>>" + checkValues);
			System.out.println("Values of checkbox reject ======>>>>" + checkValues1);

			/*
			 * request.setAttribute("checkValues11",checkValues);
			 * request.setAttribute("checkValues12",checkValues1);
			 * 
			 * requestDispatcher=request.getRequestDispatcher("../leave/leaveapp1.jsp");
			 * requestDispatcher.forward(request,response);
			 */

			checkbox chbox = new checkbox();

			if (checkValues != null) {
				chbox.insert(checkValues);
			}

			if (checkValues1 != null) {
				chbox.insert1(checkValues1);
			}

			System.out.println("data successfully updated for select and reject");
			lebean.setMode("update_successfully");
			request.setAttribute("foundData1", lebean);
			requestDispatcher = request.getRequestDispatcher("../leave/leaveapp1.jsp");
			requestDispatcher.forward(request, response);

		}

		if (hdnMode.equals("leave_detail")) {

			lebean.setMode("leave_detail");
			lebean.setName(request.getParameter("name3") == null ? "" : request.getParameter("name3").trim());
			lebean.setEmpcode(request.getParameter("empcode3") == null ? "" : request.getParameter("empcode3").trim());

			if (validateSearch2(lebean, request))

			{

				request.setAttribute("LeaveBean", lebean);
				requestDispatcher = request.getRequestDispatcher("/servlet/leave.Leavedbconnector");
				requestDispatcher.forward(request, response);
			}

			else

			{

				request.setAttribute("LeaveBean", lebean);
				requestDispatcher = request.getRequestDispatcher("../leave/leaveapp3.jsp");
				requestDispatcher.forward(request, response);
			}

		}

		if (hdnMode.equals("insert_app"))

		{

			lebean.setName(request.getParameter("name") == null ? "" : request.getParameter("name").trim());
			lebean.setEmpcode(request.getParameter("empcode") == null ? "" : request.getParameter("empcode").trim());

			if (validateSearch1(lebean, request))

			{

				request.setAttribute("LeaveBean", lebean);
				requestDispatcher = request.getRequestDispatcher("/servlet/leave.Leavedbconnector");
				requestDispatcher.forward(request, response);
				System.out.println("i m inside serchcontroller hr");
			}

			else

			{
				request.setAttribute("LeaveBean", lebean);
				requestDispatcher = request.getRequestDispatcher("../leave/leaveapp2.jsp");
				requestDispatcher.forward(request, response);
			}

		}

		if (hdnMode.equals("login_applicant"))

		{

			lebean.setName(request.getParameter("login_name") == null ? ""
					: request.getParameter("login_name").toLowerCase().trim());
			lebean.setEmpcode(request.getParameter("password") == null ? ""
					: request.getParameter("password").toLowerCase().trim());
			request.setAttribute("LeaveBean", lebean);
			requestDispatcher = request.getRequestDispatcher("/servlet/leave.Leavedbconnector");
			requestDispatcher.forward(request, response);

		}

		if (hdnMode.equals("login_approver"))

		{

			lebean.setName(request.getParameter("login_name") == null ? ""
					: request.getParameter("login_name").toLowerCase().trim());
			lebean.setEmpcode(request.getParameter("password") == null ? ""
					: request.getParameter("password").toLowerCase().trim());
			request.setAttribute("LeaveBean", lebean);
			requestDispatcher = request.getRequestDispatcher("/servlet/leave.Leavedbconnector");
			requestDispatcher.forward(request, response);

		}

		if (hdnMode.equals("show_detail"))

		{

			String rid = request.getParameter("rid") == null ? "" : request.getParameter("rid");
			request.setAttribute("rid1", rid);

			request.setAttribute("LeaveBean1", lebean);
			requestDispatcher = request.getRequestDispatcher("/servlet/leave.Leavedbconnector");
			requestDispatcher.forward(request, response);

		}

		// /*

		if (hdnMode.equals("connect"))

		{

			lebean.setName(request.getParameter("empname") == null ? "" : request.getParameter("empname").trim());
			lebean.setEmpcode(request.getParameter("empcode") == null ? "" : request.getParameter("empcode").trim());
			lebean.setDate(request.getParameter("date") == null ? "" : request.getParameter("date").trim());
			lebean.setDesignation(
					request.getParameter("source1") == null ? "" : request.getParameter("source1").trim());
			lebean.setEmpworkloc(request.getParameter("source2") == null ? "" : request.getParameter("source2").trim());
			lebean.setDepartment(request.getParameter("source3") == null ? "" : request.getParameter("source3").trim());
			lebean.setCompoff_date(
					request.getParameter("comp_off_date") == null ? "" : request.getParameter("comp_off_date").trim());
			lebean.setReason(request.getParameter("reason") == null ? "" : request.getParameter("reason").trim());
			lebean.setFromdate(request.getParameter("fromdate") == null ? "" : request.getParameter("fromdate").trim());
			lebean.setTodate(request.getParameter("todate") == null ? "" : request.getParameter("todate").trim());
			lebean.setContact(request.getParameter("contact") == null ? "" : request.getParameter("contact").trim());
			// lebean.setEmpsign(request.getParameter("empsign")==null?"":request.getParameter("empsign").trim());
			lebean.setNamebackup(
					request.getParameter("namebackup") == null ? "" : request.getParameter("namebackup").trim());
			// lebean.setSignbackup(request.getParameter("signbackup")==null?"":request.getParameter("signbackup").trim());
			lebean.setLeaveappvby(
					request.getParameter("leaveappvby") == null ? "" : request.getParameter("leaveappvby").trim());
			// lebean.setAppsign(request.getParameter("appsign")==null?"":request.getParameter("appsign").trim());
			lebean.setAppdept(request.getParameter("source4") == null ? "" : request.getParameter("source4").trim());
			lebean.setAppdesig(request.getParameter("source5") == null ? "" : request.getParameter("source5").trim());
			lebean.setCasualchx(request.getParameter("C11") == null ? "" : request.getParameter("C11").trim());
			lebean.setSickchx(request.getParameter("C12") == null ? "" : request.getParameter("C12").trim());
			lebean.setCompchx(request.getParameter("C13") == null ? "" : request.getParameter("C13").trim());
			lebean.setHalfchx(request.getParameter("C21") == null ? "" : request.getParameter("C21").trim());
			lebean.setPrivilegechx(request.getParameter("C22") == null ? "" : request.getParameter("C22").trim());

			lebean.setCasual_day(
					request.getParameter("casual_day") == null ? "" : request.getParameter("casual_day").trim());
			lebean.setSick_day(request.getParameter("sick_day") == null ? "" : request.getParameter("sick_day").trim());
			lebean.setPrivilege_day(
					request.getParameter("privilege_day") == null ? "" : request.getParameter("privilege_day").trim());
			lebean.setHalf_day_date(
					request.getParameter("half_day_date") == null ? "" : request.getParameter("half_day_date").trim());

			// if(validateleavedays(lebean,request))

			// {

			request.setAttribute("LeaveBean1", lebean);
			requestDispatcher = request.getRequestDispatcher("/servlet/leave.Leavedbconnector1");
			requestDispatcher.forward(request, response);
			System.out.println("i m inside serchcontroller hr");
			// }
			/*
			 * else
			 * 
			 * { request.setAttribute("LeaveBean1",lebean);
			 * requestDispatcher=request.getRequestDispatcher("../leave/leaveapp.jsp");
			 * requestDispatcher.forward(request,response); }
			 * 
			 */
		}
		// */

	}

	public boolean validateSearch(LeaveBean lebean, HttpServletRequest request)

	{

		Hashtable hashTableObjErr = null;
		hashTableObjErr = new Hashtable();
		Hashtable hashTableObjVal = null;
		hashTableObjVal = new Hashtable();
		boolean isValid = true;

		hashTableObjVal.put("Name", lebean.getName());
		hashTableObjVal.put("Designation", lebean.getDesignation());

		if ("".equals(lebean.getName()))

		{
			hashTableObjErr.put("Name", "<font color='red'>Please Enter Name</font>");
			isValid = false;
		}

		if ("SELECT".equals(lebean.getDesignation()))

		{
			hashTableObjErr.put("Designation", "<font color='red'>Please Select Designation</font>");
			isValid = false;
		}

		request.setAttribute("LeaveErrors", hashTableObjErr);
		request.setAttribute("LeaveValues", hashTableObjVal);
		return isValid;

	}

	public boolean validateSearch1(LeaveBean lebean, HttpServletRequest request)

	{

		Hashtable hashTableObjErr1 = null;
		hashTableObjErr1 = new Hashtable();
		Hashtable hashTableObjVal1 = null;
		hashTableObjVal1 = new Hashtable();
		boolean isValid = true;

		System.out.println("hi m inside validate search for hr");

		if (lebean != null) {
			String name = lebean.getName() != null ? lebean.getName() : "";
			String empcode = lebean.getEmpcode() != null ? lebean.getEmpcode() : "";
			System.out.println("name in hr" + name);
			System.out.println("empcode in hr" + empcode);

			hashTableObjVal1.put("Name1", name);
			hashTableObjVal1.put("Empcode1", empcode);
		}

		if ("".equals(lebean.getName()))

		{
			hashTableObjErr1.put("Name1", "<font color='red'>Please Enter Name</font>");
			isValid = false;
		}

		if ("".equals(lebean.getEmpcode()))

		{
			hashTableObjErr1.put("Empcode1", "<font color='red'>Please Enter Employee code</font>");
			isValid = false;
		}

		request.setAttribute("LeaveErrors1", hashTableObjErr1);
		request.setAttribute("LeaveValues1", hashTableObjVal1);
		return isValid;

	}

	public boolean validateSearch2(LeaveBean lebean, HttpServletRequest request)

	{

		Hashtable hashTableObjErr2 = null;
		hashTableObjErr2 = new Hashtable();
		Hashtable hashTableObjVal2 = null;
		hashTableObjVal2 = new Hashtable();
		boolean isValid = true;

		System.out.println("hi m inside validate search for hr");

		if (lebean != null) {
			String name = lebean.getName() != null ? lebean.getName() : "";
			String empcode = lebean.getEmpcode() != null ? lebean.getEmpcode() : "";
			System.out.println("name in hr" + name);
			System.out.println("empcode in hr" + empcode);

			hashTableObjVal2.put("Name1", name);
			hashTableObjVal2.put("Empcode1", empcode);
		}

		if ("".equals(lebean.getName()))

		{
			hashTableObjErr2.put("Name1", "<font color='red'>Please Enter Name</font>");
			isValid = false;
		}

		if ("".equals(lebean.getEmpcode()))

		{
			hashTableObjErr2.put("Empcode1", "<font color='red'>Please Enter Employee code</font>");
			isValid = false;
		}

		request.setAttribute("LeaveErrors1", hashTableObjErr2);
		request.setAttribute("LeaveValues1", hashTableObjVal2);
		return isValid;

	}

	/*
	 * public boolean validateleavedays(LeaveBean lebean,HttpServletRequest request)
	 * 
	 * {
	 * 
	 * Hashtable hashTableObjErr=null; hashTableObjErr= new Hashtable(); //
	 * Hashtable hashTableObjVal = null; // hashTableObjVal = new Hashtable();
	 * boolean isValid =true;
	 * 
	 * lebean.setNamebackup(request.getParameter("namebackup")==null?"":request.
	 * getParameter("namebackup").trim());
	 * //lebean.setSignbackup(request.getParameter("signbackup")==null?"":request.
	 * getParameter("signbackup").trim());
	 * lebean.setLeaveappvby(request.getParameter("leaveappvby")==null?"":request.
	 * getParameter("leaveappvby").trim()); //
	 * lebean.setAppsign(request.getParameter("appsign")==null?"":request.
	 * getParameter("appsign").trim());
	 * lebean.setAppdept(request.getParameter("source4")==null?"":request.
	 * getParameter("source4").trim());
	 * lebean.setAppdesig(request.getParameter("source5")==null?"":request.
	 * getParameter("source5").trim());
	 * lebean.setCasualchx(request.getParameter("C11")==null?"":request.getParameter
	 * ("C11").trim());
	 * lebean.setSickchx(request.getParameter("C12")==null?"":request.getParameter(
	 * "C12").trim());
	 * lebean.setCompchx(request.getParameter("C13")==null?"":request.getParameter(
	 * "C13").trim());
	 * lebean.setHalfchx(request.getParameter("C21")==null?"":request.getParameter(
	 * "C21").trim());
	 * lebean.setPrivilegechx(request.getParameter("C22")==null?"":request.
	 * getParameter("C22").trim());
	 * 
	 * 
	 * lebean.setCasual_day(request.getParameter("casual_day")==null?"":request.
	 * getParameter("casual_day").trim());
	 * lebean.setSick_day(request.getParameter("sick_day")==null?"":request.
	 * getParameter("sick_day").trim());
	 * lebean.setPrivilege_day(request.getParameter("privilege_day")==null?"":
	 * request.getParameter("privilege_day").trim());
	 * lebean.setHalf_day_date(request.getParameter("half_day_date")==null?"":
	 * request.getParameter("half_day_date").trim());
	 * lebean.setReason(request.getParameter("reason")==null?"":request.getParameter
	 * ("reason").trim());
	 * lebean.setFromdate(request.getParameter("fromdate")==null?"":request.
	 * getParameter("fromdate").trim());
	 * lebean.setTodate(request.getParameter("todate")==null?"":request.getParameter
	 * ("todate").trim());
	 * lebean.setCompoff_date(request.getParameter("comp_off_date")==null?"":request
	 * .getParameter("comp_off_date").trim());
	 * 
	 * 
	 * // hashTableObjVal.put("Name",lebean.getName()); //
	 * hashTableObjVal.put("Designation",lebean.getDesignation()); String
	 * fromdate1=lebean.getFromdate(); String todate1=lebean.getTodate(); int
	 * casualday1=Integer.parseInt(lebean.getCasual_day()); int
	 * sickday1=Integer.parseInt(lebean.getSick_day()); int
	 * privilegeday=Integer.parseInt(lebean.getPrivilege_day());
	 * 
	 * //GetDays days= new GetDays(); //int
	 * day=(int)days.Getdays1(fromdate1,todate1); int
	 * totalDays=casualday1+sickday1+privilegeday;
	 * 
	 * if(day==totalDays) {
	 * hashTableObjErr.put("Name1","<font color='red'>Please Enter Name</font>");
	 * isValid=false; } else { isValid=true; }
	 * 
	 * request.setAttribute("LeaveErrors",hashTableObjErr);
	 * 
	 * return isValid;
	 * 
	 * 
	 * }
	 * 
	 */

}