package com.leave;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DesignationSearch extends HttpServlet {

	public DesignationSearch() {
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/xml");
		response.setHeader("Cache-Control", "no-cache");
		String strArgumentRetn = request.getParameter("strArgument") != null ? request.getParameter("strArgument") : "";
		System.out.println("strArgumentRetn :" + strArgumentRetn);
		PrintWriter out = response.getWriter();
		int i = 0, j = 0;

		String Query = null;
		if (strArgumentRetn.equalsIgnoreCase("IT") || strArgumentRetn.equalsIgnoreCase("HR")
				|| strArgumentRetn.equalsIgnoreCase("BD") || strArgumentRetn.equalsIgnoreCase("PRODUCT DEVELOPMENT")
				|| strArgumentRetn.equalsIgnoreCase("SYSTEM ADMIN")) {
			Query = "select DISTINCT designation  from employee_isg where department='" + strArgumentRetn
					+ "'order by designation ";
			++i;
		} else if (strArgumentRetn != "SELECT") {
			String strArgumentRetn1 = request.getParameter("dept") != null ? request.getParameter("dept") : "";
			Query = "select DISTINCT employee_name  from employee_isg where designation='" + strArgumentRetn
					+ "' and department='" + strArgumentRetn1 + "' order by employee_name ";
			++j;
		}

		try {

			Connection connection = null;
			PreparedStatement pStmt = null;
			ResultSet rs = null;
			connection = DBCon.getConnection();

			StringBuffer sb = null;
			sb = new StringBuffer();

			/*
			 * String sqlString = "select designation  from employee_isg where department='"
			 * +dept1+"' order by designation"; connection.prepareStatement(sqlString);
			 * ResultSet resultSet = pStmt.executeQuery();
			 * 
			 * while (resultSet.next()) { sb.append("<option>" + resultSet.getString(3) +
			 * "</option>\n"); }
			 * 
			 * 
			 */
			System.out.println("designatuion===" + strArgumentRetn);
			System.out.println("Query ===" + Query);

			pStmt = connection.prepareStatement(Query);
			rs = pStmt.executeQuery();

			while (rs.next()) {
				if (i > 0) {
					String dept2 = rs.getString("designation");
					System.out.println("Designation==>>" + dept2);
					sb.append(dept2);
				} else if (j > 0) {
					String empname = rs.getString("employee_name");
					System.out.println("employee_name==>>" + empname);
					sb.append(empname);
				}

				sb.append("~");

			}
			if(sb.lastIndexOf("~")!=-1) {
				sb.deleteCharAt(sb.lastIndexOf("~"));
			}
			System.out.println("designation==>>" + sb);

			int v = 0;

			while (rs.next()) {
				v = rs.getInt(1);
			}
			if (v == 0) {
				out.write(sb.toString());
			} else {
				out.write("false");
			}

			// resultSet.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init() throws ServletException {
	}
}
