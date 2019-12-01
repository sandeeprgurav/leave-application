package com.leave;

import java.io.BufferedWriter;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class sendMailFile1 {
	private PreparedStatement psmt = null;
	private ResultSet rst = null;
	private File fdir = null;
	private BufferedWriter fo = null;
	private StringBuffer buffer = new StringBuffer();
	private Connection con = null;
	private String filename = null;

	public boolean sendMail(String to2, String from2, String host2, String message) throws Exception {

		String to = "";
		String from = "";
		String host = "";
		java.util.Properties prop = new java.util.Properties();
		to = to2;
		from = from2;
		host = host2;

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yyyy");
		java.util.Date todayDate = new java.util.Date();
		boolean debug = Boolean.valueOf("true").booleanValue();
		String subject = "Leave  file for  " + sdf.format(todayDate);
		String msgText1 = message;
		Properties props = System.getProperties();
		props.put("mail.smtp.host", host);
		Session session = Session.getInstance(props, null);
		session.setDebug(debug);
		try {
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			System.out.println(" Multipart Message2");
			InternetAddress[] address = { new InternetAddress(to) };
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject(subject);
			MimeBodyPart mbp1 = new MimeBodyPart();
			mbp1.setText(msgText1);
			MimeBodyPart mbp2 = new MimeBodyPart();
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(mbp1);
			msg.setContent(mp);
			msg.setSentDate(new Date());
			Transport.send(msg);
		} catch (MessagingException mex) {
			mex.printStackTrace();
			Exception ex = null;
			if ((ex = mex.getNextException()) != null) {
				ex.printStackTrace();
			}
		}
		return true;
	}
}
