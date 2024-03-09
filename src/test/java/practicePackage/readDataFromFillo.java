package practicePackage;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class readDataFromFillo {

	public static void main(String[] args) throws Exception{

		String MainControler = System.getProperty("user.dir") + File.separator + "Main_Controller.xlsx";

		String process = "IshinePortal";

			File file = new File(MainControler);
			

				Fillo fillo = new Fillo();
				Connection conn = fillo.getConnection(MainControler);

				String query = "SELECT * FROM DataSheet";
				String queryForProcessName = "SELECT * FROM DataSheet Where Process='" + process + "'";
				Recordset recordset = null;

				try {
					recordset = conn.executeQuery(query);
					if (recordset != null) {
						System.out.println("'DataSheet' sheet are present...");
						
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
							System.out.println("All the columnName are present");
							recordset.close();
						} else {
							System.out.println("SORRY!!! " + notPresentColumn + " columns are not present");
							System.exit(0);
						}
					} try {
						recordset = conn.executeQuery(queryForProcessName);
						if (recordset != null) {
							
							
							
							System.out.println("'DataSheet' sheet are present And process are same");
							recordset.close();
						}
					} catch (Exception e) {
						System.out.println(
								"SORRY!!! 'DataSheet' sheet are present BUT problem on Process column");
					}

				} catch (Exception e) {
					System.out.println("SORRY!!!  'DataSheet' sheet are not present...");
//					System.err.println(e.getMessage());
//					e.printStackTrace();
					System.exit(0);
				}

	
	}

}
