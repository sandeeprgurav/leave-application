package com.leave;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

public class CreateCTL {
	static BufferedReader br = null;

	static FileWriter fw = null;
	static BufferedWriter bw = null;

	public int CreateCTLMethod() throws Exception {
		try {
			int i = 10;
			Vector data = null;
			data = new Vector();
			Vector calhead = null;
			String query = null;
			calhead = new Vector();
			String CTLFilePath = "F:/SANDEEP/IMP/CTL/controlFile.ctl";
			String tablename = "select_80";
			File f = new File(CTLFilePath);
			f.createNewFile();
			fw = new FileWriter(f);
			bw = new BufferedWriter(fw);
			Connection connection = null;
			connection = DBCon.getConnection();
			query = "select * from ctltab";

			// String rrn =(String)application.getInitParameter("ResourceRefName");
			// String dbusername =(String)application.getInitParameter("AccessDBUserName");
			// String password =(String)application.getInitParameter("AccessDBPassword");
			// JndiConnection Con=new JndiConnection();
			// Connection con =Con.getConnection(rrn,dbusername,password);

			// System.out.println("Connection object......."+connection);
			bw.write("load data truncate");
			bw.newLine();

			PreparedStatement pStmt = null;
			ResultSet rs = null;

			pStmt = connection.prepareStatement(query);
			rs = pStmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			System.out.println("No of cols........" + cols);

			while (rs.next()) {
				String sJoinCond = rs.getString("JOIN1");
				System.out.println("value of jion condition........" + sJoinCond);
				if (sJoinCond != null) {
					int intStartBrace = 0;
					int intEndBrace = 0;
					int i1 = 0;
					int i2 = 0;
					cols = 1;
					while (i1 >= 0) {
						// String ConditionVal=rs.getString(4);
						++intStartBrace;
						++cols;
						if (intStartBrace == 1) {
							String tableName = rs.getString(++i1);
							if (tableName != null)
								bw.write("into table " + tableName.trim());
							bw.newLine();
							String startPosition = rs.getString(++i1);
							String endPosition = rs.getString(++i1);
							String conditionVariable = rs.getString(++i1);
							if (startPosition != "" && endPosition != "" && conditionVariable != null)
								bw.write("when (" + startPosition + ":" + endPosition + ") ='"
										+ conditionVariable.trim() + "' ");
							bw.newLine();

							bw.write("{");
							bw.newLine();
						}
						int intFindLastCol = i1;
						intFindLastCol = intFindLastCol + 4;
						String sLast_col_name = rs.getString(intFindLastCol);

						String col1_name = rs.getString(++i1);
						String col1_StartVal = rs.getString(++i1);
						String col1_EndVal = rs.getString(++i1);

						if (sLast_col_name != null) {
							bw.write(col1_name.trim() + "   position(" + col1_StartVal + ":" + col1_EndVal + "),");
							bw.newLine();
						} else if (sLast_col_name == null && i2 == 0) {
							bw.write(col1_name.trim() + "   position(" + col1_StartVal + ":" + col1_EndVal + ")");
							bw.newLine();
							i2 = 1;
						}

						if (col1_name == null && intEndBrace == 0) {
							i1 = -1;
							intEndBrace++;
							bw.write("}");
							bw.newLine();
							bw.newLine();
							bw.newLine();
						}

					}
				}
			}

		} finally {
			bw.close();
			fw.close();
		}
		return 1;
	}
}
