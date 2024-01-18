package FRAMEWORK;

import java.io.File;
import java.util.concurrent.TimeUnit;

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

	public static void Initialisation(String browser) {

		if (browser.equalsIgnoreCase("chrome")) {
			BrowserDriverFolder(browser);
//			WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.driver", browserDriverPath);
			ChromeOptions option = new ChromeOptions();
			option.addArguments("--remote-allow-origins=*");
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

		if (BrowserDriverFolderPath.exists()) {

			OS = System.getProperty("os.name");
			System.out.println(OS); // Windows 11

			if (OS.substring(0, 7).equalsIgnoreCase("Windows")) {// here i done the substring because it is showing
																	// "Windows 11" thats why i done the substring.
				WindowBrowserDriver(Browser);// calling to the windowsbrowser .exe file
			} else {
				LinuxAndUbuntuBrowserDriver(Browser); // calling to the linux and ubuntu browser file
				System.out.println("ubuntu");
			}

			File BrowserDriverPath = new File(browserDriverPath);
			if (BrowserDriverPath.exists()) {

			} else {
				System.out.println("SORRY!!!  " + Browser + "Driver.exe File is Not Present");
				System.exit(1);

			}
		} else {
			System.out.println("SORRY!!!  'BrowserDriver' folder is Not Present");
			System.exit(1);
		}
	}

	public static void WindowBrowserDriver(String BrowserEXEFile) {
		switch (BrowserEXEFile) {
		case "Chrome":
			browserDriverPath = System.getProperty("user.dir") + File.separator + "BrowserDriver" + File.separator
					+ "chromedriver.exe";
			break;
		case "edge":
			browserDriverPath = System.getProperty("user.dir") + File.separator + "BrowserDriver" + File.separator
					+ "edgedriver.exe";
			break;
		default:
			System.out.println("SORRY!!! You select Invalid Driver");

		}
	}

	public static void LinuxAndUbuntuBrowserDriver(String BrowserEXEFile) {
		switch (BrowserEXEFile) {
		case "Chrome":
			browserDriverPath = System.getProperty("user.dir") + File.separator + "BrowserDriver" + File.separator
					+ "chromedriver";
			break;
		case "edge":
			browserDriverPath = System.getProperty("user.dir") + File.separator + "BrowserDriver" + File.separator
					+ "edgedriver";
			break;
		default:
			System.out.println("SORRY!!! You select Invalid Driver");

		}
	}

}
