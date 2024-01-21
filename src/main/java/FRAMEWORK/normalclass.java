package FRAMEWORK;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class normalclass {

	public static void main(String[] args) throws AddressException, MessagingException, InterruptedException {
//
//		Fillo fillo = new Fillo();
//		Connection conn = fillo.getConnection(System.getProperty("user.dir") + "\\Ishine.xlsx");
//		String selectQuery = "SELECT * FROM Sheet2";
//		Recordset r = conn.executeQuery(selectQuery);
//
////		List<Object> al = new ArrayList<Object>();
////
////		while (r.next()) {
////			List<String> columns = r.getFieldNames();
////			for (String column : columns) {
////				al.add(r.getField(column));
////			}
////		}
////
////		for (Object o : al) {
////			System.out.println(o);
////		}
//		
//		List<Object> rowsList = new ArrayList<>();
//
//		while (r.next()) {
//		    List<String> columns = r.getFieldNames();
//		    List<Object> rowValues = new ArrayList<>();
//		    for (String column : columns) {
//		        rowValues.add(r.getField(column));
//		    }
//		    rowsList.add(rowValues);	    
//		}
//		
//		
//		
////		for (List<Object> row : rowsList) {
////		    for (Object value : row) {
////		        System.out.print(value + "\t");
////		    }
////		    System.out.println();
////		}
//		
//		
//		int size=rowsList.size();
//		
//		for(int i=0;i<size;i++)
//		{
//			 List<Object> row = (List<Object>) rowsList.get(i);
//		
//		
////		for (List<Object> row :  rowsList) {
//		    // Create a StringBuilder to build the string representation of the row
//		    StringBuilder rowString = new StringBuilder();
//
//		    for( int j=1;j<row.size();j++)
//			 {
//		        // Convert each column value to string and append it to the rowString
//		        rowString.append(row.get(j).toString()).append(", "); // You can use any delimiter you prefer (e.g., ", ", "\t", "|", etc.)
//		    }
//
//		    // Remove the last delimiter (comma and space) from the end of the rowString
//		    if (rowString.length() > 0) {
//		        rowString.setLength(rowString.length() - 2);
//		    }
//		    
//		    String[] columnValues = rowString.toString().split(", ");
//
//		    // Process each column value individually
//		    for (String columnValue : columnValues) {
//		        // Store each column value in another variable or perform any other processing
//		        // For example, you can add them to a list or use them directly in your code
//		        System.out.println("Column Value: " + columnValue);
//		    }
//
//		    // Now, the rowString contains the string representation of the current row
////		    System.out.println(rowString.toString());
//		}
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
////		int size=rowsList.size();
////		
////		for(int i=0;i<size;i++)
////		{
////			 List<Object> row = (List<Object>) rowsList.get(i);
////			 for(Object value : row)
////			 {
////				 System.out.print(value+"\t");
////			 }
////			 System.out.println();
////		}
////		
//		
//
//		
////		for (Object o : rowsList) {
////			System.out.println(o);
////		}

//		Fillo fillo = new Fillo();
//        Connection conn = fillo.getConnection(System.getProperty("user.dir") + "\\ISHINE.xlsx");
//        String moduleName = "ISHINE"; // Replace this with the desired module name
//        String query = "SELECT * FROM Sheet1 WHERE RUNSTATUS='Y' and MODULE='" + moduleName + "'";
//        
//        List<Object> rowsList = new ArrayList<>();
//
//        
//        if ("ISHINE".equalsIgnoreCase(moduleName)) {
//            Recordset recordset = conn.executeQuery(query);
//            // Process the recordset or perform other operations with the data
//            
//        	
//    		while (recordset.next()) {
//    			List<String> columns = recordset.getFieldNames();
//    			List<Object> rowValues = new ArrayList<>();
//    			for (String column : columns) {
//    				rowValues.add(recordset.getField(column));
//    			}
//    			rowsList.add(rowValues);
//    		}
//    		
//    		for(Object val :rowsList)
//    			System.out.println(val);
//            
//            recordset.close();
//        } else {
//            System.out.println("Module name is not 'ISHINE'. Stopping execution.");
//        }
//
//        conn.close();

//		String filePath = "C:\\Users\\biswa\\eclipse-workspace\\BiswajitFramework\\DataSheet\\ICICIPruAmcSip_PostLogin.xlsx";
//
//		System.out.println(filePath);
//
//		File file = new File(filePath);
//		try {
//
//			if (file.isFile()) {
//				System.out.println("  File is present");
//
//			} else {
//				System.out.println("  File is not present");
//			}
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			System.err.println("An error occurred: " + e.getMessage());
//		}

		/*
		 * Fillo fillo = new Fillo(); Connection conn =
		 * fillo.getConnection(System.getProperty("user.dir") + "\\Subscription.xlsx");
		 * String selectQuery = "SELECT * FROM Sheet6"; Recordset r =
		 * conn.executeQuery(selectQuery);
		 * 
		 * List<Object> rowList = new ArrayList<Object>(); /* tale jou logic lekhichi
		 * First re sabu column name get kariki list re store kariba List<String>
		 * columnName = r.getFieldNames();
		 * 
		 * Then row value paei gote object type array list kariba gote row ku gote
		 * object type array list
		 * 
		 * Then gote row ra total column value ku get kariki first object type arraylist
		 * re store kariba //rowValue.add(r.getField(s));
		 * 
		 * Then sabu jaka object type array list ku gote object type array list re store
		 * kariba //rowList.add(rowValue); [Srno, Module, PageName, RunStatus, Control,
		 * ObjectType] this is a single object type array list in a single row ,This
		 * type we get the all the row and addd a particular object type array list
		 * 
		 * [[Srno, Module, PageName, RunStatus, Control, ObjectType],[Srno, Module,
		 * PageName, RunStatus, Control, ObjectType],[Srno, Module, PageName, RunStatus,
		 * Control, ObjectType]]
		 * 
		 * this way to store all the row value in the object type to store the
		 * particular objecttype arraylist ...To check it the two square bracket lisk
		 * as[[]] object type array list
		 */

		/*
		 * while (r.next()) { List<String> columnName = r.getFieldNames(); //
		 * System.out.println(columnName); // [Srno, Module, PageName, RunStatus,
		 * Control, ObjectType, PropertyName, // PropertyValue, Datafield, Action,
		 * DataField_Type, Action_Type, Test_Case, // TestCase_Type, Description,
		 * Scenario_ID, Scenario_Description, COLUMN_17] List<Object> rowValue = new
		 * ArrayList<Object>(); for (String s : columnName) {
		 * rowValue.add(r.getField(s)); } // System.out.println(rowValue); // [2,
		 * Subscription, Home Page, Y, C, , , , , deleteAllCookies, , , TC_01_01, //
		 * Positive, Redirects To 5 paisa Home page, SC_01, Ledger purchase flow, ]
		 * rowList.add(rowValue); }
		 * 
		 * System.out.println(rowList);
		 * 
		 * System.out.println(rowList.size());
		 * 
		 * for (int i = 0; i < rowList.size(); i++) { //
		 * System.out.println(rowList.get(i)); List<Object> row = (List<Object>)
		 * rowList.get(i);
		 * 
		 * 
		 * }
		 */
		// Replace with your Gmail credentials
		
		/*
		 * File f = new File(
		 * "C:\\Users\\biswa\\eclipse-workspace\\BiswajitFramework\\RESULT\\2024\\January\\21\\HtmlTable\\SummaryTable.html"
		 * ); String to = "biswajitsahookanha11@gmail.com";// pass = lveshguhuyjmvglw
		 * String from = "biswajitkanha11@gmail.com"; final String username =
		 * "biswajitkanha11@gmail.com"; final String password = "zfbaqijmtutiejyd"; //
		 * pass=zfbaqijmtutiejyd
		 * 
		 * String host = "smtp.gmail.com";
		 * 
		 * Properties props = new Properties(); props.put("mail.smtp.auth", "true");
		 * props.put("mail.smtp.starttls.enable", "true"); props.put("mail.smtp.host",
		 * host); props.put("mail.smtp.port", "587");
		 * 
		 * Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		 * protected PasswordAuthentication getPasswordAuthentication() { return new
		 * PasswordAuthentication(username, password); } });
		 * 
		 * try {
		 * 
		 * Message message = new MimeMessage(session); message.setFrom(new
		 * InternetAddress(from)); message.setRecipients(Message.RecipientType.TO,
		 * InternetAddress.parse(to));
		 * message.setSubject("Automation Framework Report"); // Create MimeMultipart
		 * Multipart multipart = new MimeMultipart();
		 * 
		 * // Add HTML part (replace htmlContent with your actual HTML content) BodyPart
		 * htmlPart = new MimeBodyPart(); String htmlContent =
		 * "<html><body><h2>Automation Test Report</h2>" +
		 * "<p>Hi Sir, </p><p>Please find the Automation Test Report Below Table:</p>" +
		 * "<table border='1'><tr><th>Column1</th><th>Column2</th></tr>" +
		 * "<tr><td>Value1</td><td>Value2</td></tr></table></body></html>";
		 * htmlPart.setContent(htmlContent, "text/html");
		 * 
		 * // Add HTML part to MimeMultipart multipart.addBodyPart(htmlPart);
		 * 
		 * // Set the content of the message message.setContent(multipart);
		 * 
		 * // Send the email message Transport.send(message);
		 * 
		 * System.out.println("Sent message successfully....");
		 * 
		 * } catch (AuthenticationFailedException e) {
		 * System.out.println("Authentication failed. Please check your credentials.");
		 * e.printStackTrace(); }
		 */
		
//		Date dt = new Date();
//		SimpleDateFormat tm1 = new SimpleDateFormat("HH:mm:ss");
//		String time = tm1.format(dt);
//		System.out.println(dt);
//		System.out.println(time);
//		Thread.sleep(60000);
//		SimpleDateFormat tm2 = new SimpleDateFormat("HH:mm:ss");
//		String time1 = tm2.format(dt);
//		System.out.println(dt);
//		System.out.println(time1);
		
		long startTime = System.nanoTime();

        // Some code to be timed
        for (int i = 0; i < 100000; i++) {
            Math.sqrt(i);
        }
        Thread.sleep(70000);

        long endTime = System.nanoTime();

        // Calculate the execution time in milliseconds
        long executionTimeInMilliseconds = (endTime - startTime) / 1_000_000;
        
        /*The use of underscores in numeric literals is a feature introduced in Java 7 to improve readability. In Java, underscores in numeric literals have no effect on the actual value; they are used purely for visual separation of digits to make large numbers more readable.*/

        // Convert milliseconds to hours, minutes, and seconds
        long hours = executionTimeInMilliseconds / (60 * 60 * 1000);
        long minutes = (executionTimeInMilliseconds % (60 * 60 * 1000)) / (60 * 1000);
        long seconds = (executionTimeInMilliseconds % (60 * 1000)) / 1000;

        System.out.println("Execution time: " + hours + " hours, " + minutes + " minutes, " + seconds + " seconds," + executionTimeInMilliseconds + " Milliseconds");
    }

}
