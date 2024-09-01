package FRAMEWORK;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class ConnectDataSheet extends BrowserClass {

//	public static void main(String[] args) throws FilloException {
//		System.out.println("DataSheet2Value");
//		m1();
//	}
	public static String Si_No;
	public static String MODULE;
	public static String PageName;
	public static String PropertyName;
	public static String PropertyValue;
	public static String Datafield;
	public static String Action;
	public static String Action_Type;
	public static String Test_Case;
	public static String Description;
	public static String Scenario_ID;

	public static LocatorClass locatorClass;
	public static ConnectDataSheet connectDatasheet;
	public static UtilScreenshotAndReport utilClass;
	public static String destFileScrnshot;
	public static String status; // here status are mention only for write the csv file status 'pass' or 'fail'

	public static WebElement webElement;
	public static List<WebElement> webElements;

	public static String DataSheet2Value;
	public static ActionClass actClass;

	public static String sTest_Case = null;

	public static int totalTest = 0, pass = 0, fail = 0;
	public static int totalValidations = 0, passValidations = 0, failedValidations = 0;

	final static Logger logger = LogManager.getLogger(ConnectDataSheet.class.getName());

	// In this method DataSheetGet(String fileName) recive the datasheet file name
	// check the maincontroler module is same as datasheet module name
	// then read all the datasheet and iterate all the row in the object type
	// use forloop and if the forloop of i==0 then call the extendreport
	// then iterate all the row value in the single single variable
	// then pass the testcase_id and the all the description to the extentreport to
	// print the info.
	// then call the this xpathpick() and pass the all value what i have retrived to
	// the row and column value.
	// after retrive the all the value the call to the extentflush() to stop the
	// generate report.

	public static void DataSheetGet(String fileName) throws Exception {
		totalTest = 0; pass = 0; fail = 0;
		totalValidations = 0; passValidations = 0; failedValidations = 0;
		
		connectDatasheet = new ConnectDataSheet();
		locatorClass = new LocatorClass();
		utilClass = new UtilScreenshotAndReport();

		Fillo fillo = new Fillo();
		Connection conn = fillo.getConnection(ConnectToMainController.DataSheetFilePath);
		Recordset recordset = null;
		String query = "SELECT * FROM Sheet1";
		String queryForModule = "SELECT * FROM Sheet1 WHERE RUNSTATUS='Y' and MODULE='" + ConnectToMainController.Module
				+ "'";
		try {
			recordset = conn.executeQuery(query);
			if (recordset != null) {
//				System.out.println("Inside " + fileName + " this file the Sheet1 DataSheet are present");

				List<String> actualColumnName = recordset.getFieldNames();
				List<String> exceptedColumnName = new ArrayList<>();
				exceptedColumnName.addAll(
						Arrays.asList("Srno", "Module", "PageName", "RunStatus", "PropertyName", "PropertyValue",
								"Datafield", "Action", "Action_Type", "Test_Case", "Description", "Scenario_ID"));

				List<String> notPresentColumn = new ArrayList<>();

				Boolean allColumnPresent = true;

				for (String columnName : actualColumnName) {
					if (!exceptedColumnName.contains(columnName)) {
						notPresentColumn.add(columnName);
						allColumnPresent = false;
					}
				}

				if (allColumnPresent) {
//					System.out.println("All the columnName are present");
					recordset.close();
				} else {
					System.out.println("SORRY!!! " + notPresentColumn + " columns are not present");
					System.exit(0);
				}
			}
			try {
				recordset = conn.executeQuery(queryForModule);// here to check the runstatus and module
				if (recordset != null) {

					// new type and logic explanation***************************************

					/*
					 * This is Most Important
					 * 
					 * 
					 * tale jou logic lekhichi First re sabu column name get kariki list re store
					 * kariba List<String> columnName = r.getFieldNames();
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

					List<Object> rowsList = new ArrayList<>();

					while (recordset.next()) {
						List<String> columns = recordset.getFieldNames();
						List<Object> rowValues = new ArrayList<>();
						for (String column : columns) {
							rowValues.add(recordset.getField(column));
						}
						rowsList.add(rowValues);
					}

					// this type is working properly****************************

//		List<String> al = new ArrayList<String>();
//		while (recordset.next()) {
//			LOCATOR=recordset.getField("LOCATOR");
//			PropertyValue = recordset.getField("PropertyValue");
//			Datafield = recordset.getField("Datafield");
//			Action = recordset.getField("Action");
//			al.add(LOCATOR);
//			al.add(XPATH);
//			al.add(Datafield);
//			al.add(Action);
//		}******************************************************

//		for (Object o : rowsList) {
//			System.out.println(o);
//		}

					// **************************************************

//		System.out.println(rowsList);
//		return rowsList;

					/*
					 * utilClass.CsvFileCreate(); utilClass.WriteCSVFile("Si_No", "TestCase_No",
					 * "Status", "Screenshot_Path"); utilClass.extentReport(); ///// call the extent
					 * report class
					 */

					utilClass.extentReport(); // call the extent report method
					utilClass.CsvFileCreate();
					utilClass.WriteCSVFileHeading("Test_Case", "Description", "ExpectedResult", "ActualResult",
							"Status", "Date", "Time", "Screenshot_File_Location", "BrowserType", "IP", "HOST", "ZONE");
					utilClass.IP_HOST();

					int i;
					for (i = 0; i < rowsList.size(); i++) {

//				if (i == 0) {
//					utilClass.extentReport(); ///// call the extent report class		
//				}

						destFileScrnshot = null;
						status = "PASS";

						List<Object> row = (List<Object>) rowsList.get(i);

						Si_No = (String) row.get(0);
						MODULE = (String) row.get(1);
						PageName = (String) row.get(2);
						PropertyName = (String) row.get(4);
						PropertyValue = (String) row.get(5);
						Datafield = (String) row.get(6);
						Action = (String) row.get(7);
						Action_Type = (String) row.get(8);
						Test_Case = (String) row.get(9);
						Description = (String) row.get(10);
						Scenario_ID = (String) row.get(11);

//			 System.out.println("LOCATOR NAME=========================>"+LOCATOR+"\n"+"PropertyValue====================>"+PropertyValue+"\n"+"Datafield=============>"+Datafield+"\n"+"ActionType========>"+Action);
						System.out.println();
						System.out.println("SI_No             ====================> " + Si_No);
						System.out.println("Scenario_ID       ====================> " + Scenario_ID);
						System.out.println("PropertyName      ====================> " + PropertyName);
						System.out.println("PropertyValue     ====================> " + PropertyValue);
						System.out.println("Datafield         ====================> " + Datafield);
						System.out.println("ActionType        ====================> " + Action);

						/*
						 * if (Description != null && !Description.isEmpty()) { String
						 * testCaseAndDescription = TestCase_No.concat(" " + Description);
						 * UtilScreenshotAndReport.testCaseCreate(testCaseAndDescription); if
						 * (!Action.equalsIgnoreCase("CheckVisibility")) {
						 * UtilScreenshotAndReport.testcaseInfo(Description); } }
						 */

						/*
						 * TestCase_No, PropertyName, PropertyValue, Datafield, Action, Description,
						 * Neg_Description, driver
						 */

						if (!Test_Case.equals(sTest_Case)) { // here we created the report to the testcase id
							utilClass.testCaseCreate();
						}
						sTest_Case = Test_Case;
						totalTest++;
						
						if(Action.equalsIgnoreCase("CheckVisibility"))
							totalValidations++;
						
						// only for htmlunit driver because the driver is headless driver
						if (ConnectToMainController.Browser.equalsIgnoreCase("HtmlUnitDriver")
								&& Action.equalsIgnoreCase("CheckVisibility")) {
						} else {
							// here to call the locator method ==========================================call to the locator class
							locatorClass.xpathpick();
						}

						pass = totalTest - fail;
						passValidations = totalValidations - failedValidations;
						
						System.out.println("TotalValidation = " + totalValidations + " PassedValidations = " + passValidations + " FailedValidations = " + failedValidations);
						System.out.println("TotalTest = " + totalTest + " Pass = " + pass + " Fail = " + fail);

						// Write the Data Inside the csv File
						if (Action.equalsIgnoreCase("CheckVisibility"))
							utilClass.WriteCSVFileData(Test_Case, Description, DataSheet2Value,
									String.valueOf(ActionClass.ActualResult), status, utilClass.yearFormat,
									utilClass.time, destFileScrnshot, ConnectToMainController.Browser, utilClass.IP,
									utilClass.HostName, utilClass.ZoneName);

						/*
						 * if (!Action.contains("wait") && Action.equalsIgnoreCase("CheckVisibility")) {
						 * utilClass.WriteCSVFile(Si_No, TestCase_No, status, destFileScrnshot); }
						 */

//				UtilScreenshotAndReport.test.log(Status.INFO, Description);
//			locatorClass.xpathpick((String) row.get(1), (String) row.get(2), (String) row.get(3), (String) row.get(4), driver);

//			 for( j=1;j<row.size();j++)
//			 {
//				 if(j==1)
//				 {
//					 LOCATOR=(String) row.get(j);
//				 }
//				 
//				 else if(j==2) {
//					 PropertyValue=(String) row.get(j);
//				 }
//				 
//				 else if(j==3) {
//					 Datafield=(String) row.get(j);
//				 }
//				 
//				 else if(j==4) {
//					 Action=(String) row.get(j);
//				 }
//
//			 }
//			System.out.println("LOCATOR NAME=========================>" + (String) row.get(1) + "\n"
//					+ "PropertyValue====================>" + (String) row.get(2) + "\n" + "Datafield=============>"
//					+ (String) row.get(3) + "\n" + "ActionType========>" + (String) row.get(4));

//			 locatorClass.xpathpick(LOCATOR, PropertyValue, Datafield, Action, driver);

					}
					if (i == rowsList.size()) {

						System.out.println("\n");

						System.out.println("TotalTest = " + totalTest + " Pass = " + pass + " Fail = " + fail);

						System.out.println("********************  Successfully Completed  ********************" + "\n");
					}

//					System.out.println("'DataSheet' sheet are present And RunStatus are 'Y' and modul name also same");
					recordset.close();
				}
			} catch (Exception e) {
				System.out.println(
						"SORRY!!! 'DataSheet' sheet are present BUT problem on RunStatus or ModulName column value");
				logger.info(
						"Info Message :  SORRY!!! 'DataSheet' sheet are present BUT problem on RunStatus or ModulName column value ");
				logger.error("Error Message :  " + e);
				System.exit(0);
			}
		} catch (Exception e) {
//			System.err.print(e.getMessage());
//			e.printStackTrace();
			System.out.println("SORRY!!! Inside " + fileName + " this file there are not Sheet1 DataSheet are present");
			logger.info("Info Message :  SORRY!!! Inside " + fileName
					+ " this file there are not Sheet1 DataSheet are present");
			logger.error("Error Message :  " + e);
			System.exit(0);
		}

		finally {
			utilClass.ExtentFlush();
			utilClass.ExecutionTime();
			UtilScreenshotAndReport.CreateHtmlTable();
			MAILSEND.MailSend();
		}

	}

	//////////////// DataField Read kariba sheet2 ////////////

	// In this method DataFieldRead(to receive the datasheet filed value to get the
	// sheet2 value)
	// first check the datafield if it is not null then get the datafield value and
	// pass the value to this method actrds();

	public static void DataFieldRead() throws FilloException, InterruptedException, IOException {

		/*
		 * String TestCase_No, WebElement webElement, List<WebElement> webElements,
		 * String Datafield, String Action, String Description, String Neg_Description,
		 * WebDriver driver
		 */
		try {
			actClass = new ActionClass();

			if (Datafield != null && !Datafield.isEmpty()) {

				Fillo fillo = new Fillo();
				Connection conn = fillo.getConnection(ConnectToMainController.DataSheetFilePath);
				String query = "SELECT * FROM Sheet2";
				Recordset recordset = null;
				try {
					recordset = conn.executeQuery(query);
					
					// Define a pattern to match any character that is not a letter or a number
			        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
			     // Create a matcher with the input string
			        Matcher matcher = pattern.matcher(Datafield);
					while (recordset.next()) {
						try {
							
							DataSheet2Value = recordset.getField(matcher.replaceAll("_"));
							System.out
									.print("DataFields For Sheet2==================================================== "
											+ DataSheet2Value + "\n");
						} catch (Exception e) {
							System.out.println("SORRY!!! Inside '" + ConnectToMainController.TestFlow_Path
									+ "' this file Sheet2 are present But this '" + Datafield + "' are not present");
							logger.info("Info Message :  SORRY!!! Inside '" + ConnectToMainController.TestFlow_Path
									+ "'  this file Sheet2 are present But this '" + Datafield + "' are not present");
							logger.error("Error Message :  " + e);
						}

//				ActionClass.actrds();

						// TestCase_No, webElement, webElements, DataSheet2Value, Action, Description,
						// Neg_Description,
						// driver
					}
				} catch (Exception e) {
					System.out.println("SORRY!!! Inside '" + ConnectToMainController.TestFlow_Path
							+ "' this file Sheet2 are not present");
					logger.info("Info Message :  SORRY!!! Inside '" + ConnectToMainController.TestFlow_Path
							+ "' this file Sheet2 are not present");
					logger.error("Error Message :  " + e);
					System.exit(0);
				}
				recordset.close();
				conn.close();

				// When the if block are execute - when the xpath are failed and action are not
				// the CheckVisibility then the if block are execute other wise else lock
				// execute and down also same
				if (PropertyValue != null && !PropertyValue.isEmpty() && webElement == null && webElements == null
						&& !Action.equals("CheckVisibility")) {
					// here 'Y' means with data_field
					UtilScreenshotAndReport.testcaseInfoWithDataFieldWithoutDataField_WithFailedWebElement("Y");

				} else {
					UtilScreenshotAndReport.testcaseInfoWithDataField();
					ActionClass.actrds();
				}
			}

			else {
				if (PropertyValue != null && !PropertyValue.isEmpty() && webElement == null && webElements == null) {
					// here 'N' means without data_field
					UtilScreenshotAndReport.testcaseInfoWithDataFieldWithoutDataField_WithFailedWebElement("N");
					ActionClass.actrds();//we call for this because to setwindowsize otherwise it is not required to call this method
				} else {
					UtilScreenshotAndReport.testcaseInfoWithoutDataField();
					ActionClass.actrds();
				}
				// TestCase_No, webElement, webElements, DataSheet2Value, Action, Description,
				// Neg_Description,
				// driver
			}
		} catch (Exception e) {
//			e.printStackTrace();  //after date sheet it print the exception if it is element is empty then the element not send the data or not click the element
			logger.debug("Debug Message : " + e);
			logger.info("Info Message :  " + e);
			logger.warn("Warn Message :  " + e);
			logger.error("Error Message :  " + e);
			logger.fatal("Fatal Message : " + e);

		}

	}

}
