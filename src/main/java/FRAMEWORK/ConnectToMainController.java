package FRAMEWORK;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class ConnectToMainController {


	static String Sr_No = null;
	static String Browser = null;
	static String ApplicationID = null;
	static String Module = null;
	static String process1 = null;
	public static String ReportType = null;
	public static String process = null;
	static String TestFlow_Path = null;
	public static String mainControlerFilePath;
	public static String DataSheetFilePath;
	
	static ConnectDataSheet conDataSheet;
	final static Logger logger = LogManager.getLogger(ConnectToMainController.class);

	// Inside this method ----->MainContolerSheet()
	// In this method first check the file maincontroler file is present or not
	// then runstatus is y
	// check all the filled is filldup not empty if empty then execution is stop
	// then call this MainControlerDataSheet() and pass the process data

	public static void MainContolerSheet() throws FilloException, InterruptedException, IOException {

		conDataSheet = new ConnectDataSheet();

		mainControlerFilePath = System.getProperty("user.dir") + File.separator + "Main_Controller.xlsx";
		File file = new File(mainControlerFilePath);

		try {
			file.exists();
			Fillo fillo = new Fillo();
			Connection conn = fillo.getConnection(mainControlerFilePath);

			String query = "SELECT * FROM MainController";
			String queryForProcessName = "SELECT * FROM MainController Where RunStatus='Y'";
			Recordset recordset = null;

			try {
				recordset = conn.executeQuery(query);
				if (recordset != null) {
//						System.out.println("'MainController' sheet are present...");

					List<String> actualColumnName = recordset.getFieldNames();
					List<String> exceptedColumnName = new ArrayList<>();
					exceptedColumnName.addAll(Arrays.asList("Sr_No", "RunStatus", "Browser", "ApplicationID", "Module",
							"Process1", "ReportType"));

					List<String> notPresentColumn = new ArrayList<>();

					Boolean allColumnPresent = true;

					for (String columnName : actualColumnName) {
						if (!exceptedColumnName.contains(columnName)) {
							notPresentColumn.add(columnName);
							allColumnPresent = false;
						}
					}

					if (allColumnPresent) {
//							System.out.println("All the columnName are present");
						recordset.close();
					} else {
						System.out.println("SORRY!!! " + notPresentColumn + " columns are not present");
						System.exit(0);
					}
				}
				try {
					recordset = conn.executeQuery(queryForProcessName);
					if (recordset != null) {

						List<Object> rowlist = new ArrayList<Object>();

						while (recordset.next()) {
							List<String> columns = recordset.getFieldNames();
							List<Object> rowvalues = new ArrayList<Object>();

							for (String column : columns) {
								rowvalues.add(recordset.getField(column));
							}
							rowlist.add(rowvalues);
						}

//							System.out.println("MainController Row List "+rowlist);    // MainController Row List [[3, y, Chrome, IshinePortal, IshinePortal, IshinePortal, IshinePortal]]   this type it is print row list that is list of array type but here which row are "y" are print

						for (int i = 0; i < rowlist.size(); i++) {

							List<Object> row = (List<Object>) rowlist.get(i);

							Sr_No = (String) row.get(0);
							Browser = (String) row.get(2);
							ApplicationID = (String) row.get(3);
							Module = (String) row.get(4);
							process1 = (String) row.get(5);
							ReportType = (String) row.get(6);

//									try {

							if (Sr_No != null && !Sr_No.isEmpty() && Browser != null && !Browser.isEmpty()
									&& ApplicationID != null && !ApplicationID.isEmpty() && Module != null
									&& !Module.isEmpty() && process1 != null && !process1.isEmpty()
									&& ReportType != null && !ReportType.isEmpty())

							{
								UtilScreenshotAndReport.configureLog4j();// call to generate the logs
								MainControlerDataSheet(process1); // call this method with processname to
																					// the datasheet
							} else {
								System.out
										.println("Please Filled all the data Properly inside the MainController Sheet");
								System.exit(0);
							}

//									} catch (Exception e) {
//										System.err.println(e.getMessage());
//										e.printStackTrace();
//
//									}

//							System.out.println(process1);
						}

//							System.out.println("'MainController' sheet are present And RunStatus = 'Y'");
						recordset.close();
					}
				} catch (Exception e) {
					System.out.println("SORRY!!! 'MainController' sheet are present BUT problem may be on RunStatus");
					System.exit(0);
				}

			} catch (Exception e) {
				System.out.println("SORRY!!!  'MainControllerSheet' sheet are not present...");
				System.exit(0);
			}

		} catch (Exception e) {
			System.out.println("SORRY!!! 'Main_Controller' .xlsx file are not present...");
			System.exit(0);
		}

	}

	// Inside this method ------>MainControlerDataSheet(receive process)
	// first the maincontroler process is same as maincontrolerdatasheet process as
	// same
	// if the process is same then then read the all the rowvalue and column value
	// then get the TestFlow_Path or exceldatasheet name
	// then check the file is present or not in the datasheet folder

	public static void MainControlerDataSheet(String process1)
			throws FilloException, InterruptedException, IOException {// this method read the MainController datasheet

		Fillo fillo = new Fillo();
		Connection conn = fillo.getConnection(mainControlerFilePath);

		String query = "SELECT * FROM DataSheet";
		String queryForProcessName = "SELECT * FROM DataSheet Where Process='" + process1 + "'";
		Recordset recordset = null;

		try {
			recordset = conn.executeQuery(query);
			if (recordset != null) {
//				System.out.println("'DataSheet' sheet are present...");

				List<String> actualColumnName = recordset.getFieldNames();
				List<String> exceptedColumnName = new ArrayList<>();
				exceptedColumnName.addAll(Arrays.asList("Sr_No", "Process", "TestFlow_Path"));

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
					System.out.println("SORRY!!! " + notPresentColumn + " columns are not present in URs Sheet");
					System.exit(0);
				}
			}
			try {
				recordset = conn.executeQuery(queryForProcessName);
				if (recordset != null) {

					List<Object> rowLists = new ArrayList<Object>();
					while (recordset.next()) {
						List<String> columns = recordset.getFieldNames();
						List<Object> rowvalues = new ArrayList<Object>();

						for (String column : columns) {
							rowvalues.add(recordset.getField(column));
						}
						rowLists.add(rowvalues);
					}

					for (int i = 0; i < rowLists.size(); i++) {
						List<Object> row = (List<Object>) rowLists.get(i);
						process = (String) row.get(1);
						TestFlow_Path = (String) row.get(2);

//						System.out.println("DataSheetName========================> " + TestFlow_Path);// print the datasheet file name "IshinePortal.xlsx"

						if (TestFlow_Path != null && !TestFlow_Path.isEmpty()) {
							//Call this method sending the datasheet name with the .xlsx format which is check
							fileCheck(TestFlow_Path);
						} else {
							System.out.println("Please filled the data MainController DataSheet !!!");
							logger.info("Info Message :  Please filled the data MainController DataSheet !!!");
							System.exit(0);
						}

					}

//					System.out.println("'DataSheet' sheet are present And process are same");
					recordset.close();
				}
			} catch (Exception e) {
				System.out.println("SORRY!!! 'DataSheet' sheet are present But Process column value are not match");
				logger.info("Info Message :  SORRY!!! 'DataSheet' sheet are present But Process column value are not match");
				logger.error("Error Message :  " + e);
				System.exit(0);
			}

		} catch (Exception e) {
			System.out.println("SORRY!!!  'DataSheet' sheet are not present...");
			logger.info("Info Message :  SORRY!!!  'DataSheet' sheet are not present...");
			logger.error("Error Message :  " + e);
//			System.err.println(e.getMessage());
//			e.printStackTrace();
			System.exit(0);
		}

	}

	/// In this method =============fileCheck(String fileName)//excelsheet file name
	/// or datasheet name
	// check the datasheet folder excelsheet is present or not what the upper method
	/// is called fileCheck(TestFlow_Path) like this...
	// Then call the conDataSheet.DataSheetGet(fileName); to send the file name .

	public static void fileCheck(String DataSheetFileName) throws Exception {

		String DataSheetFolderPath = System.getProperty("user.dir") + File.separator + "DataSheet";
		DataSheetFilePath = System.getProperty("user.dir") + File.separator + "DataSheet" + File.separator
				+ DataSheetFileName;
//	System.out.println(DataSheetFolderPath);

		File DataSheetFolder = new File(DataSheetFolderPath);
		try {
			//check the folder exit or not
			DataSheetFolder.exists(); 

			File DataSheetFile = new File(DataSheetFilePath);
			try {
				//check the file are exist or not
				DataSheetFile.exists();
//			System.out.println(filePath + " DataSheet File is present");
				//this method to call the datasheet folder which is present in the DataSheet folder.
				conDataSheet.DataSheetGet(DataSheetFileName);
			} catch (Exception e) {
				System.out.println("SORRY!!! " + DataSheetFileName + " DataSheet File are not present inside the 'DataSheet' Folder");
				logger.info("Info Message :  SORRY!!! " + DataSheetFileName + " DataSheet File are not present inside the 'DataSheet' Folder");
				logger.error("Error Message :  " + e);
				System.exit(0);
			}
		} catch (Exception e) {
			System.out.println("SORRY!!! 'DataSheet' folder is not present");
			logger.info("Info Message :  SORRY!!! 'DataSheet' folder is not present");
			logger.error("Error Message :  " + e);
			System.exit(0);
		}
	}

}
