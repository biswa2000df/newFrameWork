package practicePackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class FileStructure {

	
//	public static int i =1;
//	 private final String rootFolderPath = ".\\STATIC_RESULT\\"; // Path to your Extent Report folder
//
//	   
//	 public Map<String, Object> getFolderStructure() {
//	        return traverseFolder(new File(rootFolderPath), rootFolderPath);
//	    }
//
//	    private Map<String, Object> traverseFolder(File folder, String rootFolderName) {
//	        Map<String, Object> folderStructure = new HashMap<>();
//	        // Put the root folder first
//	        folderStructure.put(folder.getName(), traverseFolderContent(folder));
//
//	        return folderStructure;
//	    }
//
//	    private Map<String, Object> traverseFolderContent(File folder) {
//	        Map<String, Object> folderContent = new HashMap<>();
//
//	        File[] files = folder.listFiles();
//	        if (files != null) {
//	            for (File file : files) {
//	                if (file.isDirectory()) {
//	                    // Print subdirectories and files recursively
//	                    folderContent.put(file.getName(), traverseFolderContent(file));
//	                } else {
//	                    folderContent.put(file.getName(), "file://" + file.getAbsolutePath());
//	                }
//	            }
//	        }
//
//	        return folderContent;
//	    }
//
//	    public static void main(String[] args) throws IOException {
//	        FileStructure structure = new FileStructure();
//	        System.out.println(structure.getFolderStructure());
//	    }
	
	
	
	public static void main(String[] args) {
//		 String filePath = "D:\\Intellij_IDE_Project\\AutomationSpringBoot\\STATIC_RESULT\\2024\\February\\25\\HtmlTable\\SummaryTable.html";
//	        
//		 try {
//	            // Parse the HTML file using Jsoup
//	            File input = new File(filePath);
//	            Document doc = Jsoup.parse(input, "UTF-8");
//
//	            // Select all <td> and <th> elements inside the <table>
//	            Elements cellsTh = doc.select("table th");
//
//	            List<String> tableHeading = new ArrayList<String>();
//	            List<String> tableData = new ArrayList<String>();
//	            
//	            // Iterate over the selected elements and print their text
//	            for (Element cell : cellsTh) {
//	            	tableHeading.add(cell.text());
//	            }
//	            System.out.println("Table Heading = " + tableHeading);
//	           
//	            Elements cellsTd = doc.select("table td");
//	               
//	            // Iterate over the selected elements and print their text
//	            for (Element cell : cellsTd) {
//	                tableData.add(cell.text());
//	            }
//	            System.out.println("Table Data = " + tableData);
//	            
//	        } catch (IOException e) {
//	            // Handle file reading errors
//	            e.printStackTrace();
//	        }
//	    
		 String filePath = "C:\\Users\\biswa\\eclipse-workspace\\BiswajitFramework\\STATIC_RESULT\\2024\\February\\08\\HtmlTable\\16_01_57_students.html";

	        try {
	            // Parse the HTML file using Jsoup
	            File input = new File(filePath);
	            Document doc = Jsoup.parse(input, "UTF-8");

	            // Get the HTML content including tags
	            String htmlContent = doc.outerHtml();

	            // Print the HTML content
	            System.out.println(htmlContent);
	        } catch (IOException e) {
	            // Handle file reading errors
	            e.printStackTrace();
	        }
	    
	}
	
	
	
	
//	public static void main(String[] args) throws CsvException {
////		Random random = new Random();
////
////	
////			int randomNumber = random.nextInt(9000) + 1001; // Generates a random number between 1000 (inclusive) and 9999 (inclusive)
////			System.out.println(String.format("%04d", randomNumber));
//
//		
////        String otp = String.format("%06d", random.nextInt(1000000));
////        System.out.println(otp);
//		
//		
////		String email = "kanha11g.com";
////		String emailRegex = "^[a-z0-9_+&*-]+(?:\\.[a-z0-9_+&*-]+)*@(?:[a-z0-9-]+\\.)+[a-z]{2,7}$";
////		String domainRegex = "^(?:[a-z0-9]+\\.)+[a-z]{2,7}$";
////
////		if (email.matches(emailRegex)) {
////		    String[] parts = email.split("@");
////		    String domain = parts[1];
////		    if (domain.matches(domainRegex)) {
////		        System.out.println("Valid email address.");
////		    } else {
////		        System.out.println("Invalid domain in the email address.");
////		    }
////		} else {
////		    System.out.println("Invalid email address.");
////		}
//
//		
////      String filePath =  System.getProperty("user.dir") + File.separator + "STATIC_RESULT" + File.separator + "2024" + File.separator + "February" + File.separator + "08" + File.separator + "HtmlTable" + File.separator + "SummaryTable.html";
//
//		String filePath = "C:\\Users\\biswa\\eclipse-workspace\\BiswajitFramework\\STATIC_RESULT\\2024\\February\\08\\Logs\\Framework.log";
//		
//      String[] pathParts = filePath.split("\\\\");
////      System.out.println(pathParts.length );
////      System.out.println(pathParts[pathParts.length - 2]);
////
////      for (String part : pathParts) {
////          System.out.println(part);
////      }
//      
//      List<String> logData = new ArrayList<>();
//
//      try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//          String line;
//          while ((line = reader.readLine()) != null) {
//              logData.add(line);
//          }
//      } catch (IOException e) {
//          e.printStackTrace();
//      }
//
////      return logData;
//
//      System.out.println( logData);
//
//
//	}

}
