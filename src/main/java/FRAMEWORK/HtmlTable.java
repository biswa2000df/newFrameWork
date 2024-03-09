package FRAMEWORK;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;


import com.aventstack.extentreports.Status;

public class HtmlTable {

	/**
	 * @param args
	 * @throws IOException
	 * @throws MessagingException 
	 * @throws AddressException 
	 */
	public static void main(String[] args) throws IOException, AddressException, MessagingException {
		/*
		 * <table> <tr> <th>Company</th> <th>Contact</th> <th>Country</th> </tr> <tr>
		 * <td>Alfreds Futterkiste</td> <td>Maria Anders</td> <td>Germany</td> </tr>
		 * <tr> <td>Centro comercial Moctezuma</td> <td>Francisco Chang</td>
		 * <td>Mexico</td> </tr> </table>
		 * 
		 * 
		 * 
		 * test.log(Status.INFO,
		 * "<h6><br><font color=\"Red\"><b> Expected Result is - </b></font></h6>" +
		 * "true" +
		 * " <h6><br> <font color=\"Red\"><b>Actual Result is - </b></font><h6>" +
		 * "False");
		 */

//		System.out.println(
//				"<!DOCTYPE html><html><body><table border=\"1\"><tr><td>Row 1, Column 1</td><td>Row 1, Column 2</td><td>Row 1, Column 3</td> </tr></table></body></html>");
//
//		  <tr>
//		    <th>Header 1</th>
//		    <th>Header 2</th>
//		    <th>Header 3</th>
//		  </tr>
		
		

		String filename = "table.html";
		int numRows = 2;
		int numCols = 4;

		FileWriter writer = new FileWriter(filename);

		writer.write("<!DOCTYPE html>\n<html>\n<head>\n");

		writer.write("<style> table { border-collapse: collapse; width: 50%; margin: auto; margin-top: 20px; }");

		writer.write(" th, td { border: 1px solid black; padding: 8px; text-align: center; }");

		writer.write("th {  background-color: #f2f2f2; } </style>");

		writer.write("</head>\n<body>\n");

		writer.write("<table border=\"1\">\n");

		writer.write(
				"<tr> <th><font color=\"Green\">Project</font></th><th><font color=\"Blue\">Total TCs</font></th><th><font color=\"Green\">Passed TCs</font></th><th><font color=\"Red\">Failed TCs</font></th><th>Report</th></tr>");

		String s="C:\\Users\\biswa\\eclipse-workspace\\BiswajitFramework\\RESULT\\2024\\January\\21\\Report\\19_59_36_students.html";
		
		writer.write(
				"<td>Row 2</td><td>Row 2</td><td>Row 2</td><td>Row 2</td><td><a href="+s+">View Report</a></td>");

//	            for (int i = 0; i < numRows; i++) {
//	                writer.write("<tr>\n");
//	                for (int j = 0; j < numCols; j++) {
//	                    writer.write("<td>Row " + (i + 1) + ", Column " + (j + 1) + "</td>\n");
//	                }
//	                writer.write("</tr>\n");
//	            }

		writer.write("</table>\n");
		writer.write("</body>\n</html>");

		writer.close();
		System.out.println("HTML table has been generated in " + filename);
		
		 String from = "biswajitkanha11@gmail.com";
		 String to = "biswajitsahookanha11@gmail.com";
		 String cc = "kanhabiswajitsahoo11@gmail.com,biswajitkanha316@gmail.com,biswajit.sahoo@apmosys.com";
	       
	        final String username = "biswajitkanha11@gmail.com";
	        final String password = "zfbaqijmtutiejyd";
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
	            message.setFrom(new InternetAddress(from));
	            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
	            message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
	            message.setSubject("Automation Framework Report");

	            // Create MimeMultipart for the message content
	            Multipart multipart = new MimeMultipart("mixed");
	            
	        String kanha = "hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh";

	            // HTML Body Part
	            BodyPart htmlPart = new MimeBodyPart();
	            String htmlContent = "<html><body><h2>Automation Test Report</h2>"
	                    + "<p>Hi Sir, </p><p>Please find the Automation Test Report Below Table : </p>"
	                    + "<TABLE style=\"border: 1px solid black;  background-color:#E4E5E5\"  width=\"100%\">"  //border-collapse: collapse; ae line re lekhile au table re border au rahibani
						+ "<tr><th  style=\"text-align:center; border: 1px solid black;\">Project</th>"
						+ "<th style=\"text-align:center; border: 1px solid black;\">Total TCs</th>"
						+ "<th style=\"text-align:center; border: 1px solid black;\">Passed TCs</th>"
						+ "<th style=\"text-align:center; border: 1px solid black;\">Failed TCs</th>"
						+ "<th style=\"text-align:center; border: 1px solid black;\">Total validations in all the TCs</th>"
						+ "<th style=\"text-align:center; border: 1px solid black;\">Passed validations</th>"
						+ "<th style=\"text-align:center; border: 1px solid black;\">Failed validations</th>"
						+ "<th style=\"text-align:center; border: 1px solid black;\">ExecutionTime</th>"
	                    + "</table><br><br></body></html>";
	            htmlPart.setContent(htmlContent, "text/html");
	            multipart.addBodyPart(htmlPart);

	         // Attachment Body Message
	            BodyPart attachmentBodyPart = new MimeBodyPart();
	            String attachmentBodyMessage = "Please find the attachment for detailed report.";
	            attachmentBodyPart.setText(attachmentBodyMessage);
	            multipart.addBodyPart(attachmentBodyPart);
	            

	            // Attachment Part
	            BodyPart attachmentPart = new MimeBodyPart();
	            String fileName = "C:\\Users\\biswa\\eclipse-workspace\\BiswajitFramework\\DYNAMIC_RESULT\\SummaryTable.html"; // Specify the path to your attachment file
	            FileDataSource source = new FileDataSource(fileName);
	            attachmentPart.setDataHandler(new DataHandler(source));
	            attachmentPart.setFileName(new File(fileName).getName());
	            multipart.addBodyPart(attachmentPart);
	            
	            
	            // Set the content of the message
	            message.setContent(multipart);

	            // Send the email message
	            Transport.send(message);

	            System.out.println("Sent message successfully....");

	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	}

}
