package FRAMEWORK;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionClass extends ConnectDataSheet {

	// webwebElement, hello, Action, driver

	static WebElement FROM;
	static String GetText;
	static int Scroll;
	static UtilScreenshotAndReport utilClass;
	

	public static void actrds()throws InterruptedException, IOException {
		
		/*String TestCase_No, WebwebElement webElement, List<WebwebElement> webElements, String DataSheet2Value,
			String Action, String Description, String Neg_Description, WebDriver driver*/

		utilClass = new UtilScreenshotAndReport();

		if (Action.equalsIgnoreCase("SendKeys")) {
			webElement.sendKeys(DataSheet2Value);
		}

		if (Action.equalsIgnoreCase("click")) {
			webElement.click();
		}

		if (Action.contains("wait")) {
			String digit = null;
			String string = Action;
			Pattern pattern = Pattern.compile("\\((\\d+)\\)"); // Matches digits enclosed in parentheses
			Matcher matcher = pattern.matcher(string);

			if (matcher.find()) {
				digit = matcher.group(1); // Extracts the digit(s) within the parentheses

			}
			
			Long l = Long.parseLong(digit);
			Thread.sleep(l);
		}

		if (Action.equalsIgnoreCase("STARTBROWSER")) {
			Initialisation(ConnectToMainController.Browser);
		}

		if (Action.equalsIgnoreCase("QUIT")) {

			driver.quit();
		}
		
		if (Action.equalsIgnoreCase("Close")) {
			driver.close();
		}

		if (Action.equalsIgnoreCase("BROWSERURL")) {
			driver.get(DataSheet2Value);
		}
		
		if (Action.equalsIgnoreCase("NavigateBrowser")) {
			driver.navigate().to(DataSheet2Value);
		}
		
		if(Action.equalsIgnoreCase("NewTabOpen")) {
			
//			driver.switchTo().newWindow(WindowType.TAB);//This is the anotherway to open a tab
			
			((JavascriptExecutor) driver).executeScript("window.open();");//use to open new tab

		}
		
		if(Action.contains("WindowHandelByIndex")) {
			
			String digit = getOnlyDigit(Action); // call the getdigit method to get the data
			int Scroll = Integer.parseInt(digit);
			 ArrayList<String> windowHandles = new ArrayList<>(driver.getWindowHandles());	
			 System.out.println("Total window are ==============================> "+windowHandles.size());
		        driver.switchTo().window(windowHandles.get(Scroll));
		}

		if (Action.equalsIgnoreCase("MOUSEHOVER")) {
			Actions act = new Actions(driver);
			act.moveToElement(webElement).build().perform();

		}
		if (Action.equalsIgnoreCase("DOUBLECLICK")) {
			Actions act = new Actions(driver);
			act.doubleClick(webElement).perform();

		}

		if (Action.equalsIgnoreCase("ACTIONCLICK")) {
			Actions act = new Actions(driver);
			act.moveToElement(webElement).click().build().perform();

		}
		if (Action.equalsIgnoreCase("RIGHTCLICK")) {
			Actions act = new Actions(driver);
			act.contextClick(webElement).click().build().perform();

		}
		if (Action.equalsIgnoreCase("MOUSEDRAG")) {
			FROM = webElement;
			System.out.println(FROM);
			
		}
		
		if (Action.equalsIgnoreCase("MOUSEDROP")) {
			System.out.println(webElement);
			Actions act = new Actions(driver);
			act.dragAndDrop(FROM, webElement).perform();
		}

		if (Action.equalsIgnoreCase("MOUSECLICKSENDKEY")) {
			Actions act = new Actions(driver);
			act.moveToElement(webElement).click().sendKeys(DataSheet2Value).perform();
		}

		/////////////// ******************IFRAME*************************//////////////////////////

		if (Action.contains("FRAMEINDEX")) {
			
			String digit = getOnlyDigit(Action); // call the getdigit method to get the data
			int index = Integer.parseInt(digit);
			
			driver.switchTo().frame(index);
			System.out.println("Frame Switch Successfully Using Index");
		}

		if (Action.equalsIgnoreCase("FRAMELOCATOR")) {
			driver.switchTo().frame(webElement);
			System.out.println("Frame Switch Successfully Using Locator");
		}

		if (Action.equalsIgnoreCase("DEFAULTCONTENT")) {
			driver.switchTo().defaultContent();
		}
		if (Action.equalsIgnoreCase("PARENTFRAME")) {
			driver.switchTo().parentFrame();
		}

		if (Action.equalsIgnoreCase("FRAMECOUNT")) {
			List<WebElement> count = webElements;
			System.out.println("Iframe size are   =====================>" + count.size());
		}

		if (Action.equalsIgnoreCase("gettext")) {
			GetText = webElement.getText();
			System.out.println(GetText);
		}
		
		if (Action.equalsIgnoreCase("GetIshineOTP")) {
			String otp=GetText.substring(21,27);
			webElement.sendKeys(otp);
		}
		
		

		if (Action.equalsIgnoreCase("SelectVisibleText")) {
			System.out.println(webElement);
			Select select = new Select(webElement);
			select.selectByVisibleText(DataSheet2Value);
		}

		if (Action.equalsIgnoreCase("SelectByValue")) {
			Select select = new Select(webElement);
			Thread.sleep(3000);
			select.selectByValue(DataSheet2Value);
		}

		if (Action.equalsIgnoreCase("SelectByIndex")) {
			Select select = new Select(webElement);
			select.selectByIndex(Integer.parseInt(DataSheet2Value));
		}

		if (Action.equalsIgnoreCase("AlertAccept")) {
			driver.switchTo().alert().accept();
		}

		if (Action.equalsIgnoreCase("AlertDismiss")) {
			driver.switchTo().alert().dismiss();
		}

		if (Action.equalsIgnoreCase("AlertSendkeys")) {
			driver.switchTo().alert().sendKeys(DataSheet2Value);
		}

		if (Action.contains("ScrollDown")) {

			String digit = getOnlyDigit(Action); // call the getdigit method to get the data
			int Scroll = Integer.parseInt(digit);

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0, " + Scroll + ")", "");
		}

		if (Action.contains("ScrollUp")) {

			String digit = getOnlyDigit(Action); // call the getdigit method to get the data
			int Scroll = Integer.parseInt(digit);

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0, " + -Scroll + ")", "");
		}
		
		if (Action.contains("ScrollwebElementUntilVisible")) {     // Scrolling down the page till the webElement is found	

			JavascriptExecutor js = (JavascriptExecutor) driver;		
	        js.executeScript("arguments[0].scrollIntoView();", webElement);
		}
		
		
		
		if (Action.equalsIgnoreCase("CheckVisibility")) {

			Boolean Verify = Boolean.parseBoolean(DataSheet2Value);

			if (Verify) {
				try {
					System.out.println(webElement);
					if (webElement.isDisplayed()) {
						ConnectDataSheet.status="PASS";
//						utilClass.testCaseCreate(TestCase_No);
						utilClass.passTestCase(driver, TestCase_No, Description);
						System.out.println("webElement is Display");

					}
				} catch (Exception e) {
					ConnectDataSheet.status="FAIL";
//					utilClass.testCaseCreate(TestCase_No);
					utilClass.failTestCase(driver, TestCase_No, Neg_Description);
					System.out.println("webElement is not Display");
				}
			}

		}

	}

	public static String getOnlyDigit(String Action) { ///////// inside the bracket get only the digit

		String digit = null;
		String string = Action;
		Pattern pattern = Pattern.compile("\\((\\d+)\\)"); // Matches digits enclosed in parentheses
		Matcher matcher = pattern.matcher(string);

		if (matcher.find()) {
			digit = matcher.group(1); // Extracts the digit(s) within the parentheses

		}
		return digit;

	}

}
