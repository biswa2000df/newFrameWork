package FRAMEWORK;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class MAILSEND {

	public static String SR_NO;
	public static String Process;
	public static String HOST;
	public static String Password;
	public static String MAIL_TO;
	public static String MAIL_CC;
	public static String SUBJECT;
	public static String BODY_MESSAGE;
	public static String ATTACHMENT_FLAG;
	public static String BODY_MESSAGE_For_ATTACHMENT;
	public static String ATTACHMENT_File_Location;

	public static void MailSend() throws Exception {

		String MainControler = System.getProperty("user.dir") + File.separator + "Main_Controller.xlsx";
		File file = new File(MainControler);
		try {
			if (file.exists()) {

				Fillo fillo = new Fillo();
				Connection conn = fillo.getConnection(MainControler);
				String query = "SELECT * FROM MAIL_SEND Where RunStatus='Y'";

				List<Object> rowlist = new ArrayList<Object>();

				Recordset recordset = null;
				try {
					recordset = conn.executeQuery(query);

				} catch (Exception e) {
					System.out.println("MailSend Sheet is not Present");
//					System.exit(0);

				}

				while (recordset.next()) {
					List<String> columns = recordset.getFieldNames();
					List<Object> rowvalues = new ArrayList<Object>();

					for (String column : columns) {
						rowvalues.add(recordset.getField(column));
					}
					rowlist.add(rowvalues);
//					System.out.println(rowvalues);
				}

				for (int i = 0; i < rowlist.size(); i++) {

					List<Object> row = (List<Object>) rowlist.get(i);

					SR_NO = (String) row.get(0);
					Process = (String) row.get(2);
					HOST = (String) row.get(3);
					Password = (String) row.get(4);
					MAIL_TO = (String) row.get(5);
					MAIL_CC = (String) row.get(6);
					SUBJECT = (String) row.get(7);
					BODY_MESSAGE = (String) row.get(8);
					ATTACHMENT_FLAG = (String) row.get(9);
					BODY_MESSAGE_For_ATTACHMENT = (String) row.get(10);
					ATTACHMENT_File_Location = (String) row.get(11);

					if (SR_NO != null && !SR_NO.isEmpty() && Process != null && !Process.isEmpty() && HOST != null
							&& !HOST.isEmpty() && Password != null && !Password.isEmpty() && MAIL_TO != null
							&& !MAIL_TO.isEmpty() && MAIL_CC != null && !MAIL_CC.isEmpty() && SUBJECT != null
							&& !SUBJECT.isEmpty() && BODY_MESSAGE != null && !BODY_MESSAGE.isEmpty()
							&& ATTACHMENT_FLAG != null && !ATTACHMENT_FLAG.isEmpty()
							&& BODY_MESSAGE_For_ATTACHMENT != null && !BODY_MESSAGE_For_ATTACHMENT.isEmpty()
							&& ATTACHMENT_File_Location != null && !ATTACHMENT_File_Location.isEmpty()) {

						if (Process.equalsIgnoreCase(ConnectToMainController.process)) {
							MailSend_WithoutAnd_WithAttachment();
						} else {
							System.out.println("SORRY!!! Process Name Not Same to the Mail_Send Sheet Process Name");
						}
					} else {
						System.out.println("Please Fill the details to the MailSendSheet");
					}

				}

			}
		} catch (Exception e) {
			System.out.println("Main_Controller File is Not Present");
//			System.exit(0);
		}
	}

	public static void MailSend_WithoutAnd_WithAttachment() {

		final String username = HOST; // "biswajitkanha11@gmail.com";
		final String password = Password;// "zfbaqijmtutiejyd";
		String host = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(HOST));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(MAIL_TO));
			message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(MAIL_CC));
			message.setSubject(SUBJECT);// "Automation Framework Report"

			// Create MimeMultipart for the message content
			Multipart multipart = new MimeMultipart("mixed");

			// HTML Body Part
			BodyPart htmlPart = new MimeBodyPart();
			String htmlContent = "<html><body><h2>Automation Test Report</h2>" + "<p>Hi Sir, </p><p> " + BODY_MESSAGE
					+ ": </p>" // Please find the Automation Test Report Below Table
					+ "<table border=\"1\">\n"
					+ "<tr> <th><font color=\"Green\">Project</font></th><th><font color=\"Blue\">Total TCs</font></th><th><font color=\"Green\">Passed TCs</font></th><th><font color=\"Red\">Failed TCs</font></th><th>ExecutionTime</th></tr>"
					+ "<td>"+ConnectToMainController.Module+"</td><td>"+ConnectDataSheet.totalTest+"</td><td>"+ConnectDataSheet.pass+"</td><td>"+ConnectDataSheet.fail+"</td><td>"+UtilScreenshotAndReport.TotalExecutionTime+"</td>"
					+ "</table><br><br></body></html>";
			htmlPart.setContent(htmlContent, "text/html");
			multipart.addBodyPart(htmlPart);

			
			
			if (ATTACHMENT_FLAG.equalsIgnoreCase("Y")) {

				// Attachment Body Message
				BodyPart attachmentBodyPart = new MimeBodyPart();
				String attachmentBodyMessage = BODY_MESSAGE_For_ATTACHMENT;
				attachmentBodyPart.setText(attachmentBodyMessage);
				multipart.addBodyPart(attachmentBodyPart);

				// Attachment Part
				BodyPart attachmentPart = new MimeBodyPart();
				// Specify the path to your attachment file
				String fileName = ATTACHMENT_File_Location;
				FileDataSource source = new FileDataSource(fileName);
				attachmentPart.setDataHandler(new DataHandler(source));
				attachmentPart.setFileName(new File(fileName).getName());
				multipart.addBodyPart(attachmentPart);
			}

			// Set the content of the message
			message.setContent(multipart);

			// Send the email message
			Transport.send(message);

//			System.out.println("Sent message successfully....");

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
}
