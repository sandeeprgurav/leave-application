package com.leave;

//import common.JndiConnection;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

public class CreateCTLFinal {
	static BufferedReader br = null;

	static FileWriter fw = null;
	static BufferedWriter bw = null;

	public int CreateCTLFinalMethod() throws Exception {
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

			int intStart = 0;

			PreparedStatement pStmt = null;
			ResultSet rs = null;
			query = "select * from ctltab1 order by TABLE_CODE";
			pStmt = connection.prepareStatement(query);
			rs = pStmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			System.out.println("No of cols........" + cols);

			while (rs.next()) {
				String add = rs.getString("include");

				String tableCode = rs.getString("TABLE_CODE");
				if (add.equalsIgnoreCase("Y")) {
					if (intStart == 0) {
						bw.write("load data  ");
						String sManipl = rs.getString("truncate") != null ? rs.getString("truncate") : "";
						if (sManipl.equalsIgnoreCase("truncate")) {
							bw.write("truncate");
						} else if (sManipl.equalsIgnoreCase("append")) {
							bw.write("append");
						} else if (sManipl.equalsIgnoreCase("replace")) {
							bw.write("replace");
						}
						bw.newLine();
						intStart++;
					}
					bw.write("insert into");
					String tableName = rs.getString("table_name");
					bw.write("  " + tableName);

					String delimiter = rs.getString("DELIMITER") != null ? rs.getString("DELIMITER") : "";
					if (delimiter != null) {
						bw.write(delimiter.trim());
						bw.newLine();

					}

					String when = rs.getString("when") != null ? rs.getString("when") : "";
					if (when != null) {
						bw.write("when   " + when.trim());
						bw.newLine();

					}
					bw.write("(");
					bw.newLine();

					try {

						Connection conCol = null;
						conCol = DBCon.getConnection();
						String queryCol = null;
						PreparedStatement pStmtCol = null;
						ResultSet rsCol = null;
						queryCol = "select TABLE_CODE,COLUMN_NAME,nvl(START_POS,0) START_POS1,nvl(END_POS,0) END_POS1,nvl(DATA_TYPE,'-') DATA_TYPE1 ,nvl(LENGTH,0) LENGTH1,nvl(TERM_CD,'-') TERM_CD1,nvl(NULLIF_CD,'-') NULLIF_CD1,nvl(COL_NAME,'-') COL_NAME1,nvl(VALUE,'-') VALUE1,nvl(CONDITION,'-') CONDITION1,SEQNO,ADD_COL from ctlcol where  TABLE_CODE='"
								+ tableCode + "'  order by seqno";
						System.out.println("query inner....." + queryCol);
						pStmtCol = conCol.prepareStatement(queryCol, ResultSet.TYPE_SCROLL_INSENSITIVE,
								ResultSet.CONCUR_READ_ONLY);
						rsCol = pStmtCol.executeQuery();
						ResultSetMetaData rsmdCol = rsCol.getMetaData();
						int colCount = rsmdCol.getColumnCount();
						System.out.println("no of cols in table" + colCount);
						int a = 0;
						a = rs.getRow();
						System.out.println("no of rows.....>>>>>" + a);
						while (rsCol.next()) {

							String addCol = rsCol.getString("add_col");

							if (addCol.equalsIgnoreCase("Y")) {
								String columnName = rsCol.getString("COLUMN_NAME");
								System.out.println("col name....." + columnName);
								if (columnName != null) {
									bw.write(columnName + "");

									int sStartPos = rsCol.getInt("START_POS1");
									if (sStartPos != 0) {
										bw.write("   position (" + sStartPos + "");
									}
									int sEndPos = rsCol.getInt("END_POS1");
									if (sEndPos != 0) {
										bw.write(":" + sEndPos + ")");
									} else {
										bw.write(")");
									}

									String sDataType = rsCol.getString("DATA_TYPE1");
									if (!sDataType.equalsIgnoreCase("-")) {
										if (sDataType.equalsIgnoreCase("Integer"))
											bw.write("   " + sDataType + " External");
										else
											bw.write("  " + sDataType + "");

									}

									int intLength = rsCol.getInt("LENGTH1");
									if (intLength != 0) {
										bw.write("(" + intLength + ")");

									}

									String sTerm_cond = rsCol.getString("TERM_CD1");
									if (!sTerm_cond.equalsIgnoreCase("-")) {
										bw.write("   " + sTerm_cond + "");

									}

									String sNullIfCd = rsCol.getString("NULLIF_CD1");
									if (sNullIfCd.equalsIgnoreCase("Y")) {
										bw.write("   NullIF");
										String sColName1 = rsCol.getString("COL_NAME1");
										if (!sColName1.equalsIgnoreCase("-")) {
											bw.write("  " + sColName1 + "=");

										}
										String svalue = rsCol.getString("VALUE1");
										if (!svalue.equalsIgnoreCase("-")) {
											bw.write("" + svalue + "");

										}
										String sCondition = rsCol.getString("CONDITION1");
										if (!sCondition.equalsIgnoreCase("-")) {
											bw.write("   " + sCondition);
										}

									}
									if (!rsCol.isLast())
										bw.write(",");
									bw.newLine();

								}

							}

						}

						bw.write(")");
						bw.newLine();
						bw.newLine();

					} catch (Exception e1) {

						System.out.println("Exception occured insid inner loop....." + e1);
					} finally {

					}

				}

			}

		} catch (Exception e) {
			System.out.println("Exception occured.........." + e);
		}

		finally {

			bw.close();
			fw.close();
		}
		return 1;
	}
}