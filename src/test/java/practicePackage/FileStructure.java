package practicePackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.prefs.Preferences;

public class FileStructure {

	
	
	 private final String rootFolderPath = ".\\STATIC_RESULT\\"; // Path to your Extent Report folder

	   
	    public Map<String, Object> getFolderStructure() {
	        Map<String, Object> folderStructure = new HashMap<>();
	        traverseFolder(new File(rootFolderPath), folderStructure);
	        return folderStructure;
	    }

	    private void traverseFolder(File folder, Map<String, Object> folderStructure) {
	        if (!folder.isDirectory()) {
	            return;
	        }

	        Map<String, Object> subFolders = new HashMap<>();
	        File[] files = folder.listFiles();
	        if (files != null) {
	            for (File file : files) {
	                if (file.isDirectory()) {
	                    Map<String, Object> subFolderStructure = new HashMap<>();
	                    traverseFolder(file, subFolderStructure);
	                    subFolders.putAll(subFolderStructure); // Add sub-folders directly
	                } else {
	                    // Add files to the parent folder
	                    subFolders.put(file.getName(), null);
	                }
	            }
	        }

	        // Don't add the folder itself, just add its sub-folders
	        folderStructure.put(folder.getName(), subFolders);
	    }
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//		FileStructure structure = new FileStructure();
//		System.out.println(structure.getFolderStructure());
		
		   Preferences userRoot = Preferences.userRoot();
	        String email = userRoot.get("Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings", "Email");
	        System.out.println("Registered Email: " + email);
	    }


}
