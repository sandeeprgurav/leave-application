package com.leave;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

class checkbox

{

	public void insert(String select[]) {

		String approveby = null;
		String fromdate = null;
		String todate = null;
		String type[] = null;
		type = new String[1];
		String empcode = null;
		String entry_date = null;
		String comp_off_date = null;
		String half_day_date = null;
		int privilege_day = 0;
		int casual_day = 0;
		int sick_day = 0;
		String rid = null;

		String to1 = null;
		String from1 = null;
		String host1 = null;
		String strTemp[] = null;
		strTemp = new String[12];
		String strTemp1[] = null;
		strTemp1 = new String[5];

		String type1 = null;
		String type2 = null;
		String type3 = null;
		String type4 = null;
		String type5 = null;

		if (select != null)

		{

			for (int index = 0; index < select.length; index++)

			{
				strTemp = select[index].split("~");
				approveby = strTemp[0];
				fromdate = strTemp[1];
				todate = strTemp[2];
				type[0] = strTemp[3];
				empcode = strTemp[4];
				entry_date = strTemp[5];
				comp_off_date = strTemp[6];
				half_day_date = strTemp[7];
				privilege_day = Integer.parseInt(strTemp[8].toString());
				casual_day = Integer.parseInt(strTemp[9].toString());
				sick_day = Integer.parseInt(strTemp[10].toString());
				rid = strTemp[11];

				for (int index1 = 0; index1 < type.length; index1++) {
					strTemp1 = type[index1].split("/");
					type1 = strTemp1[0];
					type2 = strTemp1[1];
					type3 = strTemp1[2];
					type4 = strTemp1[3];
					type5 = strTemp1[4];
				}

				try {
					Connection connection = null;
					PreparedStatement pStmt = null;
					ResultSet rs = null;
					connection = DBCon.getConnection();

					String query = " update LEAVEAPP1 set approve_by='" + approveby
							+ "',approve_date=now() where employee_code='" + empcode + "' and rid='" + rid
							+ "' and entry_date in('" + entry_date + "')  ";

					System.out.println("Updation query======>>>>" + query);

					pStmt = connection.prepareStatement(query);
					pStmt.executeUpdate();

					float balance_leave = 0;
					PreparedStatement pt = null;
					ResultSet rs11 = null;
					String query11 = "select  Balance_leave from leaveinfo where employee_code='" + empcode + "' ";

					pt = connection.prepareStatement(query11);
					rs11 = pt.executeQuery();
					while (rs11.next()) {
						balance_leave = (int) rs11.getInt("Balance_leave");
						System.out.println("balance_leave===>>>" + balance_leave);
					}

					int casualmax = 0;
					int casualmax1 = 0;
					int sickmax = 0;
					int sickmax1 = 0;
					int privilegemax = 0;
					int privilegemax1 = 0;
					int extraleavemax = 0;
					int halfmax = 0;
					int halfmax1 = 0;
					int compmax = 0;
					int compmax1 = 0;
					int privilege = 0;
					int sick = 0;
					int casual = 0;
					int extraleave = 0;

					if (type1.equalsIgnoreCase("casual_leave")) {
						try {
							PreparedStatement pStmt1 = null;
							ResultSet rs1 = null;
							String query1 = "select  casual_leave,extra_leave  from leaveinfo where employee_code='"
									+ empcode + "' ";
							System.out.println("select query of casual_leave===>>" + query1);

							pStmt1 = connection.prepareStatement(query1);
							rs1 = pStmt1.executeQuery();

							while (rs1.next()) {
								casualmax = (int) rs1.getInt("casual_leave");
								extraleavemax = (int) rs1.getInt("extra_leave");
							}

							if ((casualmax + casual_day) <= 15) {
								casualmax1 = casualmax + casual_day;
							} else {
								extraleave = (casualmax + casual_day) - 15;
								extraleave = extraleave + extraleavemax;

								PreparedStatement pStmt4 = null;
								String query4 = "update leaveinfo set extra_leave='" + extraleave
										+ "' where employee_code='" + empcode + "'  ";
								System.out.println("select query of casual_leave===>>" + query4);

								pStmt4 = connection.prepareStatement(query4);
								pStmt4.executeQuery();

								casual = casual_day - extraleave;
								casualmax1 = casualmax + casual;

							}

							balance_leave -= casual_day;
							PreparedStatement pStmt2 = null;
							ResultSet rs2 = null;

							String query2 = "update leaveinfo set casual_leave='" + casualmax1
									+ "' where employee_code='" + empcode + "'  ";
							System.out.println("select query of casual_leave===>>" + query2);

							pStmt2 = connection.prepareStatement(query2);
							pStmt2.executeQuery();

						} catch (Exception e5) {
							System.out.println("Exception in casual leave query===>>>" + e5);
						}

					}

					if (type2.equalsIgnoreCase("sick_leave"))
					{
						PreparedStatement pStmt1 = null;
						ResultSet rs1 = null;
						String query1 = "select  sick_leave,extra_leave  from leaveinfo where employee_code='" + empcode
								+ "' ";

						pStmt1 = connection.prepareStatement(query1);
						rs1 = pStmt1.executeQuery();
						balance_leave -= sick_day;
						while (rs1.next()) {
							sickmax = (int) rs1.getInt("sick_leave");
							extraleavemax = (int) rs1.getInt("extra_leave");
							System.out.println("sickmax===>>>" + sickmax);
							System.out.println("extraleavemax===>>>" + extraleavemax);
						}

						if ((sickmax + sick_day) <= 15) {
							sickmax1 = sickmax + sick_day;
						} else {
							extraleave = (sickmax + sick_day) - 15;
							extraleave = extraleave + extraleavemax;
							PreparedStatement pStmt4 = null;
							String query4 = "update leaveinfo set extra_leave='" + extraleave
									+ "' where employee_code='" + empcode + "'  ";
							pStmt4 = connection.prepareStatement(query4);
							pStmt4.executeQuery();

							sick = sick_day - extraleave;
							sickmax1 = sickmax + sick;
						}

						PreparedStatement pStmt2 = null;
						ResultSet rs2 = null;

						String query2 = "  update leaveinfo set sick_leave='" + sickmax1 + "' where employee_code='"
								+ empcode + "' ";
						System.out.println("update query for sickleave===>>>" + query2);
						pStmt2 = connection.prepareStatement(query2);
						pStmt2.executeQuery();

					}

					if (type5.equalsIgnoreCase("privilege_leave"))
					{
						PreparedStatement pStmt1 = null;
						ResultSet rs1 = null;
						String query1 = "select  privilege_leave,extra_leave from leaveinfo where employee_code='"
								+ empcode + "' ";

						pStmt1 = connection.prepareStatement(query1);
						rs1 = pStmt1.executeQuery();
						balance_leave -= privilege_day;
						while (rs1.next()) {
							privilegemax = (int) rs1.getInt("privilege_leave");
							extraleavemax = (int) rs1.getInt("extra_leave");
							System.out.println("privilegemax===>>>" + privilegemax);
							System.out.println("extraleavemax===>>>" + extraleavemax);
						}

						if ((privilegemax + privilege_day) <= 15) {
							privilegemax1 = privilegemax + privilege_day;
						} else {

							extraleave = (privilegemax + privilege_day) - 15;
							extraleave = extraleave + extraleavemax;

							PreparedStatement pStmt4 = null;
							String query4 = "update leaveinfo set extra_leave='" + extraleave
									+ "' where employee_code='" + empcode + "'  ";
							pStmt4 = connection.prepareStatement(query4);
							pStmt4.executeQuery();

							privilege = privilege_day - extraleave;
							privilegemax1 = privilegemax + privilege;
						}

						PreparedStatement pStmt2 = null;
						ResultSet rs2 = null;

						String query2 = "update leaveinfo set privilege_leave='" + privilegemax1
								+ "' where employee_code='" + empcode + "'  ";
						pStmt2 = connection.prepareStatement(query2);
						pStmt2.executeQuery();
					}

					if (type4.equalsIgnoreCase("half_day"))	{
						PreparedStatement pStmt1 = null;
						ResultSet rs1 = null;
						String query1 = "select  half_day from leaveinfo where employee_code='" + empcode + "' ";

						pStmt1 = connection.prepareStatement(query1);
						rs1 = pStmt1.executeQuery();

						while (rs1.next()) {
							halfmax = (int) rs1.getInt("half_day");
							System.out.println("halfmax===>>>" + halfmax);
						}

						halfmax1 = halfmax + 1;
						balance_leave -= 0.5;
						PreparedStatement pStmt2 = null;
						ResultSet rs2 = null;

						String query2 = "update leaveinfo set half_day='" + halfmax1 + "' where employee_code='"
								+ empcode + "'  ";
						pStmt2 = connection.prepareStatement(query2);
						pStmt2.executeQuery();
					}

					if (type3.equalsIgnoreCase("comp_off"))	{
						PreparedStatement pStmt1 = null;
						ResultSet rs1 = null;
						String query1 = "select  comp_off from leaveinfo where employee_code='" + empcode + "' ";

						pStmt1 = connection.prepareStatement(query1);
						rs1 = pStmt1.executeQuery();

						while (rs1.next()) {
							compmax = (int) rs1.getInt("comp_off");
							System.out.println("compmax===>>>" + compmax);
						}

						compmax1 = compmax + 1;
						PreparedStatement pStmt2 = null;
						ResultSet rs2 = null;

						String query2 = "update leaveinfo set comp_off='" + compmax1 + "' where employee_code='"
								+ empcode + "'  ";
						pStmt2 = connection.prepareStatement(query2);
						pStmt2.executeQuery();

					}

					System.out.println("balance_leave after manipulation===>>>" + balance_leave);
					PreparedStatement pSt = null;
					String updatequery = "update leaveinfo set Balance_leave='" + balance_leave
							+ "' where employee_code='" + empcode + "'  ";
					pSt = connection.prepareStatement(updatequery);
					pSt.executeQuery();

					PreparedStatement pSt1 = null;

					String updatequery1 = "update LEAVEAPP1 set Balance_leave1='" + balance_leave
							+ "' where employee_code='" + empcode + "' and rid='" + rid + "' ";
					pSt1 = connection.prepareStatement(updatequery1);
					pSt1.executeQuery();

					String to2 = null;
					String from2 = null;
					String host2 = null;
					String message = null;
					message = "Leave application has been approved. ";

					String email_to = null;
					String sql_email_to = "select email_id from leaveinfo where employee_code='" + empcode + "'";
					System.out.println("sql_email_to==+++" + sql_email_to);
					PreparedStatement st1_email_to = connection.prepareStatement(sql_email_to);
					ResultSet rs_email_to = st1_email_to.executeQuery();

					while (rs_email_to.next()) {

						email_to = rs_email_to.getString("email_id");
						System.out.println("email_to ==>>>>>>>" + email_to);

					}

					approveby = approveby.trim();

					String email_from = null;
					String sql_email_from = "select email_id from leaveinfo where employee_name='" + approveby + "'";
					System.out.println("email_from query==>>" + sql_email_from);
					PreparedStatement st_email_from = connection.prepareStatement(sql_email_from);
					ResultSet rs_email_from = st_email_from.executeQuery();

					while (rs_email_from.next()) {

						email_from = rs_email_from.getString("email_id");
						System.out.println("email_from ==>>>>>>>" + email_from);

					}

					to2 = email_to;
					from2 = email_from;
					host2 = "192.168.25.5";

					// sendMailFile1 s3 = new sendMailFile1();
					// s3.sendMail(to2, from2, host2, message);

					String message1 = "Leave Application of Employee code=" + empcode
							+ " has been approved.\n \n Please check this.";
					to1 = "agoramurthy@insolutionsglobal.com";
					from1 = email_from;
					host1 = "192.168.25.5";

					sendMailFile1 s2 = new sendMailFile1();
					s2.sendMail(to1, from1, host1, message1);
				} catch (Exception e) {
					System.out.println("exception==========>" + e);
				}
			}
		} else {
			System.out.println("value of select" + select);
		}
	}

	public void insert1(String reject[]) {
		String rejectby = null;
		String empcode = null;
		String entry_date = null;
		String strTemp[] = null;
		strTemp = new String[3];

		if (reject != null)	{
			for (int index = 0; index < reject.length; index++)	{
				strTemp = reject[index].split("~");
				rejectby = strTemp[0];
				System.out.println("rejectby===>" + rejectby);
				empcode = strTemp[1];
				System.out.println("empcode===>" + empcode);
				entry_date = strTemp[2];
				System.out.println("entry_date===>" + entry_date);
				try {
					Connection connection = null;
					PreparedStatement pStmt1 = null;
					ResultSet rs1 = null;
					connection = DBCon.getConnection();
					String query1 = "update leaveapp set reject_by='" + rejectby
							+ "',reject_date=now() where employee_code='" + empcode + "' and  entry_date='" + entry_date
							+ "'";

					pStmt1 = connection.prepareStatement(query1);
					pStmt1.executeUpdate();
				} catch (Exception e) {
					System.out.println("exception==========>" + e);
				}
			}
		} else {
			System.out.println("value of reject" + reject);
		}
	}

}
