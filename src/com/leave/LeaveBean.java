package com.leave;

import java.util.Vector;

public class LeaveBean implements java.io.Serializable

{
	private String mode;
	private String Name;
	private String Designation;
	private String Empcode;
	private String Department;
	private String Reason;
	private String Fromdate;
	private String Todate;
	private String Empworkloc;
	private String date;
	private String Contact;
	private String Namebackup;
	private String Leaveappvby;
	private String Appdept;
	private String Appdesig;
	private String Privilegechx;
	private String Casualchx;
	private String Sickchx;
	private String Compchx;
	private String Halfchx;
	private String Privilege_day;
	private String Casual_day;
	private String Sick_day;
	private String Half_day_date;
	private String Compoff_date;
	private Vector data;
	private Vector col;

	/* setter's methods */

	public void setData(Vector param) {
		this.data = param;
	}

	public void setCol(Vector param) {
		this.col = param;
	}

	public void setName(String param) {
		this.Name = param;
	}

	public void setDesignation(String param) {
		this.Designation = param;
	}

	public void setEmpcode(String param) {
		this.Empcode = param;
	}

	public void setDate(String param) {
		this.date = param;
	}

	public void setDepartment(String param) {
		this.Department = param;
	}

	public void setReason(String param) {
		this.Reason = param;
	}

	public void setFromdate(String param) {
		this.Fromdate = param;
	}

	public void setTodate(String param) {
		this.Todate = param;
	}

	public void setEmpworkloc(String param) {
		this.Empworkloc = param;
	}

	public void setCompoff_date(String param) {
		this.Compoff_date = param;
	}

	public void setMode(String param) {
		this.mode = param;
	}

	public void setContact(String param) {
		this.Contact = param;
	}

	public void setNamebackup(String param) {
		this.Namebackup = param;
	}

	public void setLeaveappvby(String param) {
		this.Leaveappvby = param;
	}

	public void setAppdept(String param) {
		this.Appdept = param;
	}

	public void setAppdesig(String param) {
		this.Appdesig = param;
	}

	public void setCasualchx(String param) {
		this.Casualchx = param;
	}

	public void setSickchx(String param) {
		this.Sickchx = param;
	}

	public void setCompchx(String param) {
		this.Compchx = param;
	}

	public void setHalfchx(String param) {
		this.Halfchx = param;
	}

	public void setPrivilegechx(String param) {
		this.Privilegechx = param;
	}

	public void setCasual_day(String param) {
		this.Casual_day = param;
	}

	public void setSick_day(String param) {
		this.Sick_day = param;
	}

	public void setPrivilege_day(String param) {
		this.Privilege_day = param;
	}

	public void setHalf_day_date(String param) {
		this.Half_day_date = param;
	}

	/* getter's methods */
	public Vector getData() {
		return data;
	}

	public Vector getCol() {
		return col;
	}

	public String getName() {
		return Name;
	}

	public String getDesignation() {
		return Designation;
	}

	public String getEmpcode() {
		return Empcode;
	}

	public String getDate() {
		return date;
	}

	public String getDepartment() {
		return Department;
	}

	public String getReason() {
		return Reason;
	}

	public String getFromdate() {
		return Fromdate;
	}

	public String getTodate() {
		return Todate;
	}

	public String getMode() {
		return mode;
	}

	public String getEmpworkloc() {
		return Empworkloc;
	}

	public String getCompoff_date() {
		return Compoff_date;
	}

	public String getContact() {
		return Contact;
	}

	public String getNamebackup() {
		return Namebackup;
	}

	public String getLeaveappvby() {
		return Leaveappvby;
	}

	public String getAppdept() {
		return Appdept;
	}

	public String getAppdesig() {
		return Appdesig;
	}

	public String getCasualchx() {
		return Casualchx;
	}

	public String getSickchx() {
		return Sickchx;
	}

	public String getCompchx() {
		return Compchx;
	}

	public String getHalfchx() {
		return Halfchx;
	}

	public String getPrivilegechx() {
		return Privilegechx;
	}

	public String getCasual_day() {
		return Casual_day;
	}

	public String getSick_day() {
		return Sick_day;
	}

	public String getPrivilege_day() {
		return Privilege_day;
	}

	public String getHalf_day_date() {
		return Half_day_date;
	}

}