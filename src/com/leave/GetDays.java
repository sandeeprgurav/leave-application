
package com.leave;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class GetDays {

	static final long ONE_HOUR = 60 * 60 * 1000L;
	static final long ONE_DAY = 86400000;

	public long Getdays1(String fromdate, String todate) throws Exception
	{
		int iDate = Integer.parseInt(fromdate.substring(0, 2));
		int iYear = Integer.parseInt(fromdate.substring(7, 11));
		String sMonth = fromdate.substring(3, 6);
		int iMonth = 0;

		if (sMonth.trim().equalsIgnoreCase("JAN"))
			iMonth = 1;
		if (sMonth.trim().equalsIgnoreCase("FEB"))
			iMonth = 2;
		if (sMonth.trim().equalsIgnoreCase("MAR"))
			iMonth = 3;
		if (sMonth.trim().equalsIgnoreCase("APR"))
			iMonth = 4;
		if (sMonth.trim().equalsIgnoreCase("MAY"))
			iMonth = 5;
		if (sMonth.trim().equalsIgnoreCase("JUN"))
			iMonth = 6;
		if (sMonth.trim().equalsIgnoreCase("JUL"))
			iMonth = 7;
		if (sMonth.trim().equalsIgnoreCase("AUG"))
			iMonth = 8;
		if (sMonth.trim().equalsIgnoreCase("SEP"))
			iMonth = 9;
		if (sMonth.trim().equalsIgnoreCase("OCT"))
			iMonth = 10;
		if (sMonth.trim().equalsIgnoreCase("NOV"))
			iMonth = 11;
		if (sMonth.trim().equalsIgnoreCase("DEC"))
			iMonth = 12;

		int iDate1 = Integer.parseInt(todate.substring(0, 2));
		int iYear1 = Integer.parseInt(todate.substring(7, 11));
		String sMonth1 = todate.substring(3, 6);
		int iMonth1 = 0;

		if (sMonth1.trim().equalsIgnoreCase("JAN"))
			iMonth1 = 1;
		if (sMonth1.trim().equalsIgnoreCase("FEB"))
			iMonth1 = 2;
		if (sMonth1.trim().equalsIgnoreCase("MAR"))
			iMonth1 = 3;
		if (sMonth1.trim().equalsIgnoreCase("APR"))
			iMonth1 = 4;
		if (sMonth1.trim().equalsIgnoreCase("MAY"))
			iMonth1 = 5;
		if (sMonth1.trim().equalsIgnoreCase("JUN"))
			iMonth1 = 6;
		if (sMonth1.trim().equalsIgnoreCase("JUL"))
			iMonth1 = 7;
		if (sMonth1.trim().equalsIgnoreCase("AUG"))
			iMonth1 = 8;
		if (sMonth1.trim().equalsIgnoreCase("SEP"))
			iMonth1 = 9;
		if (sMonth1.trim().equalsIgnoreCase("OCT"))
			iMonth1 = 10;
		if (sMonth1.trim().equalsIgnoreCase("NOV"))
			iMonth1 = 11;
		if (sMonth1.trim().equalsIgnoreCase("DEC"))
			iMonth1 = 12;

		Calendar c1 = new GregorianCalendar();
		Calendar c2 = new GregorianCalendar();

		c1.set(iYear, iMonth, iDate, 0, 0, 0);
		c2.set(iYear1, iMonth1, iDate1, 0, 0, 0);

		Date d1 = c1.getTime();
		Date d2 = c2.getTime();

		return ((d2.getTime() + ONE_DAY - d1.getTime() + ONE_HOUR) / (ONE_HOUR * 24));
	}

	public static void main(String args[]) {
		GetDays testdate = new GetDays();
		try {
			long days = testdate.Getdays1("05-JUN-2008", "25-JUN-2008");
			System.out.println("days between dates===>>" + days);
		}

		catch (Exception w) {
			System.out.println("Exception++++>>>" + w);
		}
	}

}