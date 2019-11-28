package com.leave;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Leavedbconnector extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		retriveData(request, response);

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		retriveData(request, response);
	}

	public void retriveData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		RequestDispatcher requestDispatcher = null;

		try

		{

			Leavedbconnector1 leaveconn = new Leavedbconnector1();
			LeaveBean lebean1 = (LeaveBean) request.getAttribute("LeaveBean");

			if ("Searchapp".equals(lebean1.getMode()))

			{
				request.setAttribute("LeaveBean1", lebean1);
				requestDispatcher = request.getRequestDispatcher("../servlet/leave.Leavedbconnector1");
				requestDispatcher.forward(request, response);

			}

			if ("Searchhr".equals(lebean1.getMode()))

			{
				request.setAttribute("LeaveBean1", lebean1);
				requestDispatcher = request.getRequestDispatcher("../servlet/leave.Leavedbconnector1");
				requestDispatcher.forward(request, response);

			}

			if ("leave_detail".equals(lebean1.getMode()))

			{
				request.setAttribute("LeaveBean1", lebean1);
				requestDispatcher = request.getRequestDispatcher("../servlet/leave.Leavedbconnector1");
				requestDispatcher.forward(request, response);

			}

			if ("login_applicant".equals(lebean1.getMode()))

			{
				request.setAttribute("LeaveBean1", lebean1);
				requestDispatcher = request.getRequestDispatcher("../servlet/leave.Leavedbconnector1");
				requestDispatcher.forward(request, response);

			}

			if ("login_approver".equals(lebean1.getMode()))

			{
				request.setAttribute("LeaveBean1", lebean1);
				requestDispatcher = request.getRequestDispatcher("../servlet/leave.Leavedbconnector1");
				requestDispatcher.forward(request, response);

			}

		}

		catch (Exception e) {

		}

	}

}
