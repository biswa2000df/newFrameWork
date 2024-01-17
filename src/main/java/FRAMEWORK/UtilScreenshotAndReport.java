package FRAMEWORK;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.opencsv.CSVWriter;

public class UtilScreenshotAndReport extends ConnectDataSheet {

	ExtentHtmlReporter htmlReport;
	ExtentReports extent;
	static ExtentTest test;
	static String yearFormat;
	static String time;
	public static String Extent_ReportFile;
	public static String CSV_ReportFile;
	public static String ssDatafield = null;
	public static String ssDataSheet2Value = null;
	static String IP = null;
	static String HostName = "";
	static String ZoneName = null;

	final static Logger logger = LogManager.getLogger(UtilScreenshotAndReport.class);

	public String takeScreenShot(WebDriver driver, String TestCase_No) throws IOException {

		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destPath = getFormat("YYYY", "MMMM", "dd", "Screenshot");
		ConnectDataSheet.destFileScrnshot = destPath + File.separator + time + "_" + TestCase_No + ".png";

//		ScreenshotPathFormat();		
//		ConnectDataSheet.destFileScrnshot = System.getProperty("user.dir") + File.separator + "Screenshot"
//				+ File.separator + year + File.separator + time + File.separator + TestCase_No + ".png";

		FileUtils.copyFile(srcFile, new File(ConnectDataSheet.destFileScrnshot));
		return ConnectDataSheet.destFileScrnshot;

	}

	public void ScreenshotPathFormat() {
		Date date = new Date();
		SimpleDateFormat tm = new SimpleDateFormat("yyyy-MM-dd");
		yearFormat = tm.format(date);
		SimpleDateFormat tm1 = new SimpleDateFormat("HH_mm_ss");
		time = tm1.format(date);
	}

	// Modification on file format ======today date is 12-aug-2023

	public static String getFormat(String Year, String Month, String Date, String Type) {

		Date date = new Date();
		SimpleDateFormat yer = new SimpleDateFormat(Year);
		SimpleDateFormat mnt = new SimpleDateFormat(Month);
		SimpleDateFormat dat = new SimpleDateFormat(Date);
		SimpleDateFormat tm = new SimpleDateFormat("HH_mm_ss");
		SimpleDateFormat fullyer = new SimpleDateFormat("yyyy-MM-dd");
		yearFormat = fullyer.format(date);

		String year = yer.format(date);
		String Mnth = mnt.format(date);
		String Dt = dat.format(date);
		time = tm.format(date);

//		System.out.println(year);
//		System.out.println(Mnth);
//		System.out.println(Dt);

		String f = System.getProperty("user.dir") + File.separator + "RESULT" + File.separator + year + File.separator
				+ Mnth + File.separator + Dt + File.separator + Type;
		new File(f).mkdirs();
//		System.out.println(f);
		return f;
	}

	public void extentReport() throws IOException {

		String htmlFile = "students.html";

		/*
		 * // ScreenshotPathFormat(); // String destFile =
		 * System.getProperty("user.dir") + File.separator + "ExtentReport" +
		 * File.separator + year // + File.separator + time; // new
		 * File(destFile).mkdirs();
		 */

		String destFile = getFormat("YYYY", "MMMM", "dd", "Report");
		Extent_ReportFile = destFile + File.separator + time + "_" + htmlFile;
		htmlReport = new ExtentHtmlReporter(Extent_ReportFile);
		extent = new ExtentReports();
		extent.attachReporter(htmlReport);

		htmlReport.config().setDocumentTitle("Biswajit FrameWork Report");// Title of the report
		htmlReport.config().setReportName("Automation Report");// Name of the report
		htmlReport.config().setTestViewChartLocation(ChartLocation.BOTTOM);
		htmlReport.config().setChartVisibilityOnOpen(false);
		htmlReport.config().setTheme(Theme.DARK);

		extent.setSystemInfo("Comapny Name", "APMOSYS");
		extent.setSystemInfo("FrameWork", "Biswajit Framework");
		extent.setSystemInfo("Project Name", "ISHINE");
		extent.setSystemInfo("Test Lead", "Prabhat Padhy");
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Tester Name", "Biswajit");
		extent.setSystemInfo("Browser", ConnectToMainController.Browser);
		extent.setSystemInfo("Application URL", "https://www.google.com");

	}

	public static void testcaseInfoWithDataField() {

		try {

			ssDatafield = Datafield;
			ssDataSheet2Value = DataSheet2Value;
//		test.log(Status.INFO, Description);

//		if (Datafield != null && !Datafield.isEmpty())
//		
//		if (ssDatafield != null || ssDatafield.isEmpty() && ssDataSheet2Value != null || ssDataSheet2Value.isEmpty()) {
//			ssDatafield = " ";
//			ssDataSheet2Value = " ";
//		}

			test.log(Status.INFO,
					"<font color=\"Blue\"><b>Module - </b></font>" + MODULE + " "
							+ "<font color=\"Lime\"><b>Step - </b></font>" + Si_No + " "
							+ "<font color=\"Red\"><b>Data Field - </b></font>" + ssDatafield.toUpperCase() + " "
							+ "<font color=\"Lime\"><b>Test Data - </b></font>" + ssDataSheet2Value);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("Debug Message : " + e);
			logger.info("Info Message :  " + e);
			logger.warn("Warn Message :  " + e);
			logger.error("Error Message :  " + e);
			logger.fatal("Fatal Message : " + e);
		}

	}

	public static void testcaseInfoWithoutDataField() {

		try {

			test.log(Status.INFO, "<font color=\"Blue\"><b>Module - </b></font>" + MODULE + " "
					+ "<font color=\"Lime\"><b>Step - </b></font>" + Si_No);

		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("Debug Message : " + e);
			logger.info("Info Message :  " + e);
			logger.warn("Warn Message :  " + e);
			logger.error("Error Message :  " + e);
			logger.fatal("Fatal Message : " + e);
		}

	}

	public void testCaseCreate() {
		try {
			test = extent
					.createTest(
							"<font color=\"Blue\"><b>" + Scenario_ID + "</b></font> - <font color=\"Brown\"><b>"
									+ MODULE + "</b></font> - <font color=\"Green\"><b>" + Test_Case + "</b></font> ( "
									+ Description + " )",
							"</br><h4><font color=\"Lime\"><b>" + MODULE.toUpperCase() + "</b></font></h4>")
					.createNode("<h5><b>" + Description + "</b></h5>").assignCategory("BISWAJIT");
		} catch (Exception e) {

		}
	}

	public void passTestCase() throws IOException {
		try {

			test.log(Status.PASS,
					"<h6><br><font color=\"Red\"><b> Expected Result is - </b></font></h6>" + ssDataSheet2Value
							+ "	 <h6><br> <font color=\"Red\"><b>Actual Result is - </b></font><h6>"
							+ ActionClass.ActualResult + "<br>",
					MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(driver, Scenario_ID)).build());
		} catch (Exception e) {

			e.printStackTrace();
			logger.debug("Debug Message : " + e);
			logger.info("Info Message :  " + e);
			logger.warn("Warn Message :  " + e);
			logger.error("Error Message :  " + e);
			logger.fatal("Fatal Message : " + e);
		}
	}

	public void failTestCase() throws IOException {
		try {
//		test.log(Status.INFO, MarkupHelper.createLabel(Neg_Description, ExtentColor.RED));
//		test.log(Status.FAIL, "",
//				MediaEntityBuilder.createScreenCaptureFromPath(
//						"<h6><br><font color=\"Red\"><b> Expected Result is - </b></font></h6>" + "True"
//								+ "	 <h6><br> <font color=\"Red\"><b>Actual Result is - </b></font><h6>" + "False"
//								+ "<br>" , takeScreenShot(driver, Scenario_ID))
//						.build());

			test.log(Status.FAIL,
					"<h6><br><font color=\"Red\"><b> Expected Result is - </b></font></h6>" + ssDataSheet2Value
							+ "	 <h6><br> <font color=\"Red\"><b>Actual Result is - </b></font><h6>"
							+ ActionClass.ActualResult + "<br>",
					MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(driver, Scenario_ID)).build());
		} catch (Exception e) {

			e.printStackTrace();
			logger.debug("Debug Message : " + e);
			logger.info("Info Message :  " + e);
			logger.warn("Warn Message :  " + e);
			logger.error("Error Message :  " + e);
			logger.fatal("Fatal Message : " + e);
		}

	}

	public void ExtentFlush() {
		extent.flush();
	}

	/////// CSV FILE CREATE///////////////////////////

	String csvFile = "students.csv";
	String destFile;

	public void CsvFileCreate() throws IOException {

		/*
		 * Date date = new Date(); SimpleDateFormat tm = new
		 * SimpleDateFormat("yyyy-MM-dd"); String year = tm.format(date);
		 * SimpleDateFormat tm1 = new SimpleDateFormat("HH_mm_ss"); String time =
		 * tm1.format(date); destFile = System.getProperty("user.dir") + File.separator
		 * + "CSVFile" + File.separator + year + File.separator + time; new
		 * File(destFile).mkdirs();
		 */

		destFile = getFormat("YYYY", "MMMM", "dd", "CSVFile");

	}

	static FileWriter fileWriter;

	public void WriteCSVFileHeading(String Test_Case, String Description, String ExpectedResult, String ActualResult,
			String Status, String Date, String Time, String Screenshot_File_Location, String BrowserType, String IP,
			String HOST, String ZONE) throws IOException {

		try {

			CSV_ReportFile = destFile + File.separator + csvFile;
			fileWriter = new FileWriter(CSV_ReportFile, true);
			CSVWriter cw = new CSVWriter(fileWriter);
			String line1[] = { Test_Case, Description, ExpectedResult, ActualResult, Status, Date, Time,
					Screenshot_File_Location, BrowserType, IP, HOST, ZONE };

			// Writing data to the csv file
			cw.writeNext(line1);
			// close the file
			cw.close();

//		DataBaseConnection.DataBase(Si_No, TestCase_No, Status, Screenshot_Path);

		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("Debug Message : " + e);
			logger.info("Info Message :  " + e);
			logger.warn("Warn Message :  " + e);
			logger.error("Error Message :  " + e);
			logger.fatal("Fatal Message : " + e);
		}
	}

	public void WriteCSVFileData(String Test_Case, String Description, String ExpectedResult, String ActualResult,
			String Status, String Date, String Time, String Screenshot_File_Location, String BrowserType, String IP,
			String HOST, String ZONE) throws IOException {

		try {

			CSV_ReportFile = destFile + File.separator + csvFile;
			fileWriter = new FileWriter(CSV_ReportFile, true);
			CSVWriter cw = new CSVWriter(fileWriter);
			String line1[] = { Test_Case, Description, ExpectedResult, ActualResult, Status, Date, Time,
					"=HYPERLINK(\"" + Screenshot_File_Location + "\")", BrowserType, IP, HOST, ZONE };

//		String line2[] = { Si_No, TestCase_No, Status, "=HYPERLINK(\"" + Screenshot_Path + "\")" };

			// Writing data to the csv file
			cw.writeNext(line1);
			// close the file
			cw.close();

//		DataBaseConnection.DataBase(Si_No, TestCase_No, Status, Screenshot_Path);

		} catch (Exception e) {

			e.printStackTrace();
			logger.debug("Debug Message : " + e);
			logger.info("Info Message :  " + e);
			logger.warn("Warn Message :  " + e);
			logger.error("Error Message :  " + e);
			logger.fatal("Fatal Message : " + e);
		}
	}

	// Create html Table For Mail Sent

	public static void CreateHtmlTable() throws IOException {

		try {

			String htmlTable = getFormat("YYYY", "MMMM", "dd", "HtmlTable");
			String filename = htmlTable + File.separator + "SummaryTable.html";

			FileWriter writer = new FileWriter(filename);

			writer.write("<!DOCTYPE html>\n<html>\n<head>\n");

			writer.write("<style> table { border-collapse: collapse; width: 50%; margin: auto; margin-top: 20px; }");
			writer.write(" th, td { border: 1px solid black; padding: 8px; text-align: center; }");
			writer.write("th {  background-color: #f2f2f2; } </style>");
			writer.write("</head>\n<body>\n");

			writer.write("<table border=\"1\">\n");
			writer.write(
					"<tr> <th><font color=\"Lime\">Project</font></th><th><font color=\"Blue\">Total TCs</font></th><th><font color=\"Green\">Passed TCs</font></th><th><font color=\"Red\">Failed TCs</font></th><th>Report</th><th>CSV_File</th></tr>");

			writer.write("<td>" + ConnectToMainController.Module + "</td><td>" + totalTest + "</td><td>" + pass
					+ "</td><td>" + fail + "</td><td><a href=" + Extent_ReportFile
					+ " target=_blank>View Report</a></td><td><a href=" + CSV_ReportFile
					+ " target=_blank>CSV</a></td>");

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
//		System.out.println("HTML table has been generated in " + filename);

		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("Debug Message : " + e);
			logger.info("Info Message :  " + e);
			logger.warn("Warn Message :  " + e);
			logger.error("Error Message :  " + e);
			logger.fatal("Fatal Message : " + e);
		}

	}

	public void IP_HOST() {
		String IP_HOST_ZONE = "";
		try {
			InetAddress address = InetAddress.getLocalHost();
			IP = address.getHostAddress(); // 172.18.32.1
			HostName = address.getHostName(); // BISWAJIT2000
		} catch (UnknownHostException e) {

			e.printStackTrace();
		}
		// TIME ZONE
		try {
			Calendar cal = Calendar.getInstance();
			long milliDiff = cal.get(Calendar.ZONE_OFFSET); // milliDiff=offset=19800000
			// Got local offset, now loop through available timezone id(s).
			String[] zoneName = TimeZone.getAvailableIDs(); // get all the zone name using the array

			for (String id : zoneName) {
				TimeZone tz = TimeZone.getTimeZone(id); // Example =
														// sun.util.calendar.ZoneInfo[id="Africa/Addis_Ababa",offset=10800000,dstSavings=0,useDaylight=false,transitions=7,lastRule=null]
				if (tz.getRawOffset() == milliDiff) { // check the offset is matched
					// Found a match.
					ZoneName = id;
					break;
				}
			}
			IP_HOST_ZONE = IP + "," + HostName + "," + ZoneName;
			/// System.out.println("IP="+ip+"--------HOSTNAME= "+hostname+"--------ZoneName=
			/// "+name);

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			logger.debug("Debug Message : " + e);
			logger.info("Info Message :  " + e);
			logger.warn("Warn Message :  " + e);
			logger.error("Error Message :  " + e);
			logger.fatal("Fatal Message : " + e);
		}

	}
	
	public static void configureLog4j() {
        // Get the current LoggerContext
        LoggerContext loggerContext = (LoggerContext) LogManager.getContext(false);

        // Get the current Log4j configuration
        Configuration config = loggerContext.getConfiguration();
        
        // Set the root logger level to DEBUG
        LoggerConfig rootLoggerConfig = config.getRootLogger();
        rootLoggerConfig.setLevel(org.apache.logging.log4j.Level.INFO);  //HERE ALSO TO CHANGE THE DEBUG OR INFO OR ALL OR ERROR OR WARN ETC MODE,

        // Create a FileAppender with a dynamically generated file name
        String logFileName = generateLogFileName();
        	
        FileAppender appender = FileAppender.newBuilder()
                .setName("File")
                .withFileName(logFileName)
//                .withAppend(false)//here  write the append false me u can not append the new run logs only overwrite if u want then make false to true
                .withAppend(true)
                .setLayout(PatternLayout.newBuilder().withPattern("%d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n").build())
                .build();

        // Add the appender to the configuration
        appender.start();
        config.addAppender(appender);

        // Update the LoggerConfig to use the new appender
        LoggerConfig loggerConfig = config.getLoggerConfig(LogManager.ROOT_LOGGER_NAME);
        loggerConfig.addAppender(appender, null, null);

        // Update the configuration
        config.getRootLogger().addAppender(appender, null, null);

        // Update the Log4j context
        loggerContext.updateLoggers();

        // Log a message indicating the new log file
//        logger.info("Logging to dynamically created file: {}", logFileName);
    }

    private static String generateLogFileName() {
    	String LogsFilePath = getFormat("YYYY", "MMMM", "dd", "Logs");
    	return LogsFilePath + File.separator + "Framework.log";
    }

}
