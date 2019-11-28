package com.leave;

import java.sql.*;
import java.io.*;

import java.util.Vector;
import java.util.*;

public class Showdetail {

	public Vector getDetail(String rid1)

	{

		String rid = rid1;

		Connection connection = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;

		Vector data = null;
		data = new Vector();
		Vector calhead = null;
		calhead = new Vector();

		String query = "";

		query = "SELECT EMPLOYEE_NAME ,(DATE_FORMAT(ENTRY_DATE, \" %d-%b-%Y\"))ENTRY_DATE , EMPLOYEE_CODE , DESIGNATION ,EMP_WORK_LOC , DEPARTMENT,CASUAL_LEAVE_chx,CASUAL_DAY,SICK_LEAVE_chx,SICK_DAY,PRIVILEGE_LEAVE_chx,PRIVILEGE_DAY,HALF_DAY_chx,(DATE_FORMAT(HALF_DAY_DATE, \" %d-%b-%Y\"))HALF_DAY_DATE,COMP_OFF_chx, (DATE_FORMAT(comp_off_date, \" %d-%b-%Y\"))comp_off_date, REASON_FOR_LEAVE , (DATE_FORMAT(FROM_DATE, \" %d-%b-%Y\"))FROM_DATE,(DATE_FORMAT(TO_DATE, \" %d-%b-%Y\"))TO_DATE,contact_no,NAME_OF_BACKUP,leave_APPROVEd_BY,APPROVERS_DEPARTMENT,APPROVERS_DESIGNATION,balance_leave1  from LEAVEAPP1  where RID='"
				+ rid + "' and approve_by is null and approve_date is null ";
		System.out.println("i m inside show details==>>" + query);

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

		catch (Exception e)

		{
			System.out.println("Exception occured=========>>" + e);
		}

		return data;

	}

	public Vector getDetail1(String rid1)

	{

		String rid = rid1;

		Connection connection = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;

		Vector data = null;
		data = new Vector();
		Vector calhead = null;
		calhead = new Vector();

		String query = "";

		query = "SELECT EMPLOYEE_NAME ,(DATE_FORMAT(ENTRY_DATE, \" %d-%b-%Y\"))ENTRY_DATE , EMPLOYEE_CODE , DESIGNATION ,EMP_WORK_LOC , DEPARTMENT,CASUAL_LEAVE_chx,CASUAL_DAY,SICK_LEAVE_chx,SICK_DAY,PRIVILEGE_LEAVE_chx,PRIVILEGE_DAY,HALF_DAY_chx,(DATE_FORMAT(HALF_DAY_DATE, \" %d-%b-%Y\"))HALF_DAY_DATE,COMP_OFF_chx, (DATE_FORMAT(comp_off_date, \" %d-%b-%Y\"))comp_off_date, REASON_FOR_LEAVE , (DATE_FORMAT(FROM_DATE, \" %d-%b-%Y\"))FROM_DATE,(DATE_FORMAT(TO_DATE, \" %d-%b-%Y\"))TO_DATE,contact_no,NAME_OF_BACKUP,leave_APPROVEd_BY,APPROVERS_DEPARTMENT,APPROVERS_DESIGNATION,balance_leave1   from LEAVEAPP1  where RID='"
				+ rid + "' and approve_by is not null and approve_date is not null ";
		System.out.println("i m inside show details==>>" + query);

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

		catch (Exception e)

		{
			System.out.println("Exception occured=========>>" + e);
		}

		return data;

	}

	public static void main(String[] args) {
		System.out.println("Hello World!");
	}
}
