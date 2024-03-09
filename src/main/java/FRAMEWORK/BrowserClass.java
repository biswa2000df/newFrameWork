package FRAMEWORK;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserClass {

	static WebDriver driver;
	public static String browserDriverFolderPath;
	public static String browserDriverPath;
	public static String OS;
	
	final static Logger logger = LogManager.getLogger(BrowserClass.class.getName());

	public static void Initialisation(String browser) {

		if (browser.equalsIgnoreCase("chrome")) {
			BrowserDriverFolder(browser);
//			WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.driver", browserDriverPath);
			ChromeOptions option = new ChromeOptions();
			option.addArguments("--remote-allow-origins=*");
			option.addArguments("--no-sandbox");
//			option.addArguments("--incognito");
			option.addArguments("--disable-notifications");
			option.addArguments("--disable-cache");

			
			option.setPageLoadStrategy(PageLoadStrategy.NORMAL);
//			option.addArguments("--headless");

			driver = new ChromeDriver(option);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		}

		else if (browser.equalsIgnoreCase("edge")) {
			BrowserDriverFolder(browser);
			WebDriverManager.edgedriver().setup();
			EdgeOptions option = new EdgeOptions();
			option.addArguments("--remote-allow-origins=*");
			driver = new EdgeDriver(option);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		}

		// HeadlessMode
		else if (browser.equalsIgnoreCase("HtmlUnitDriver")) {
			driver = new HtmlUnitDriver();
			System.out.println("HtmlUnitDriver");
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		} else {
			System.out.println("SORRY!!! U Choose InvalidBrowser");
			System.exit(0);
		}

	}

//		driver.get("https://mail.apmosys.com/webmail/#sign-in");

	public static void BrowserDriverFolder(String Browser) {

		browserDriverFolderPath = System.getProperty("user.dir") + File.separator + "BrowserDriver";
		File BrowserDriverFolderPath = new File(browserDriverFolderPath);

		try {
			//check browser driver folder exist or not
			BrowserDriverFolderPath.exists();

			OS = System.getProperty("os.name");
			System.out.println("OS = " + OS); // Windows 11

			if (OS.equalsIgnoreCase("Linux")) {
				// calling to the linux and ubuntu browser file
				LinuxAndUbuntuBrowserDriver(Browser); 
			} else {
				// calling to the windowsbrowser .exe file
				WindowBrowserDriver(Browser);
			}

			File BrowserDriverPath = new File(browserDriverPath);
			try {
				//check driver path are exist or not
				BrowserDriverPath.exists();
			} catch (Exception e) {
				System.out.println("SORRY!!!  " + Browser + "Driver.exe File is Not Present");
				logger.info("Info Message :  SORRY!!!  " + Browser + " Driver.exe File is Not Present ");
				logger.error("Error Message :  " + e);
				System.exit(1);
			}

		} catch (Exception e) {
			System.out.println("SORRY!!!  'BrowserDriver' folder is Not Present");
			logger.info("Info Message :  SORRY!!!  'BrowserDriver' folder is Not Present ");
			logger.error("Error Message :  " + e);
			System.exit(1);
		}
	}

	public static void WindowBrowserDriver(String BrowserEXEFile) {
		switch (BrowserEXEFile) {
		case "CHROME":
			browserDriverPath = System.getProperty("user.dir") + File.separator + "BrowserDriver" + File.separator
					+ "chromedriver.exe";
			break;
		case "EDGE":
			browserDriverPath = System.getProperty("user.dir") + File.separator + "BrowserDriver" + File.separator
					+ "edgedriver.exe";
			break;
		case "chrome":
			browserDriverPath = System.getProperty("user.dir") + File.separator + "BrowserDriver" + File.separator
					+ "chromedriver.exe";
			break;
		case "edge":
			browserDriverPath = System.getProperty("user.dir") + File.separator + "BrowserDriver" + File.separator
					+ "edgedriver.exe";
			break;
		case "Chrome":
			browserDriverPath = System.getProperty("user.dir") + File.separator + "BrowserDriver" + File.separator
					+ "chromedriver.exe";
			break;
		case "Edge":
			browserDriverPath = System.getProperty("user.dir") + File.separator + "BrowserDriver" + File.separator
					+ "edgedriver.exe";
			break;
		default:
			System.out.println("SORRY!!! Your OS = " + OS + ", But You select Invalid Driver");
			System.exit(0);

		}
	}

	public static void LinuxAndUbuntuBrowserDriver(String BrowserEXEFile) {
		switch (BrowserEXEFile) {
		case "CHROME":
			browserDriverPath = System.getProperty("user.dir") + File.separator + "BrowserDriver" + File.separator
					+ "chromedriver";
			break;
		case "EDGE":
			browserDriverPath = System.getProperty("user.dir") + File.separator + "BrowserDriver" + File.separator
					+ "edgedriver";
			break;
		case "chrome":
			browserDriverPath = System.getProperty("user.dir") + File.separator + "BrowserDriver" + File.separator
					+ "chromedriver";
			break;
		case "edge":
			browserDriverPath = System.getProperty("user.dir") + File.separator + "BrowserDriver" + File.separator
					+ "edgedriver";
			break;
		case "Chrome":
			browserDriverPath = System.getProperty("user.dir") + File.separator + "BrowserDriver" + File.separator
					+ "chromedriver";
			break;
		case "Edge":
			browserDriverPath = System.getProperty("user.dir") + File.separator + "BrowserDriver" + File.separator
					+ "edgedriver";
			break;
		default:
			System.out.println("SORRY!!! Your OS  = " + OS + ", But You select Invalid Driver");
			System.exit(0);

		}
	}

}
