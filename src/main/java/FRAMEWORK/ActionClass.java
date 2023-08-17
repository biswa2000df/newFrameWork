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

public class ActionClass extends BrowserClass {

	// webElement, hello, Action, driver

	static WebElement FROM;
	static String GetText;
	static int Scroll;
	static UtilScreenshotAndReport utilClass;
	

	public static void actrds(String TestCase_No, WebElement element, List<WebElement> elements, String dataField,
			String actiontype, String Description, String Neg_Description, WebDriver driver)
			throws InterruptedException, IOException {

		utilClass = new UtilScreenshotAndReport();

		if (actiontype.equalsIgnoreCase("SendKeys")) {
			element.sendKeys(dataField);
		}

		if (actiontype.equalsIgnoreCase("click")) {
			element.click();
		}

		if (actiontype.contains("wait")) {
			String digit = null;
			String string = actiontype;
			Pattern pattern = Pattern.compile("\\((\\d+)\\)"); // Matches digits enclosed in parentheses
			Matcher matcher = pattern.matcher(string);

			if (matcher.find()) {
				digit = matcher.group(1); // Extracts the digit(s) within the parentheses

			}
			
			Long l = Long.parseLong(digit);
			Thread.sleep(l);
		}

		if (actiontype.equalsIgnoreCase("STARTBROWSER")) {
			Initialisation(ConnectToMainController.Browser);
		}

		if (actiontype.equalsIgnoreCase("QUIT")) {

			driver.quit();
		}
		
		if (actiontype.equalsIgnoreCase("Close")) {
			driver.close();
		}

		if (actiontype.equalsIgnoreCase("BROWSERURL")) {
			driver.get(dataField);
		}
		
		if (actiontype.equalsIgnoreCase("NavigateBrowser")) {
			driver.navigate().to(dataField);
		}
		
		if(actiontype.equalsIgnoreCase("NewTabOpen")) {
			
//			driver.switchTo().newWindow(WindowType.TAB);//This is the anotherway to open a tab
			
			((JavascriptExecutor) driver).executeScript("window.open();");//use to open new tab

		}
		
		if(actiontype.contains("WindowHandelByIndex")) {
			
			String digit = getOnlyDigit(actiontype); // call the getdigit method to get the data
			int Scroll = Integer.parseInt(digit);
			 ArrayList<String> windowHandles = new ArrayList<>(driver.getWindowHandles());	
			 System.out.println("Total window are ==============================> "+windowHandles.size());
		        driver.switchTo().window(windowHandles.get(Scroll));
		}

		if (actiontype.equalsIgnoreCase("MOUSEHOVER")) {
			Actions act = new Actions(driver);
			act.moveToElement(element).build().perform();

		}
		if (actiontype.equalsIgnoreCase("DOUBLECLICK")) {
			Actions act = new Actions(driver);
			act.doubleClick(element).perform();

		}

		if (actiontype.equalsIgnoreCase("ACTIONCLICK")) {
			Actions act = new Actions(driver);
			act.moveToElement(element).click().build().perform();

		}
		if (actiontype.equalsIgnoreCase("RIGHTCLICK")) {
			Actions act = new Actions(driver);
			act.contextClick(element).click().build().perform();

		}
		if (actiontype.equalsIgnoreCase("MOUSEDRAG")) {
			FROM = element;
			System.out.println(FROM);
			
		}
		
		if (actiontype.equalsIgnoreCase("MOUSEDROP")) {
			System.out.println(element);
			Actions act = new Actions(driver);
			act.dragAndDrop(FROM, element).perform();
		}

		if (actiontype.equalsIgnoreCase("MOUSECLICKSENDKEY")) {
			Actions act = new Actions(driver);
			act.moveToElement(element).click().sendKeys(dataField).perform();
		}

		/////////////// ******************IFRAME*************************//////////////////////////

		if (actiontype.contains("FRAMEINDEX")) {
			
			String digit = getOnlyDigit(actiontype); // call the getdigit method to get the data
			int index = Integer.parseInt(digit);
			
			driver.switchTo().frame(index);
			System.out.println("Frame Switch Successfully Using Index");
		}

		if (actiontype.equalsIgnoreCase("FRAMELOCATOR")) {
			driver.switchTo().frame(element);
			System.out.println("Frame Switch Successfully Using Locator");
		}

		if (actiontype.equalsIgnoreCase("DEFAULTCONTENT")) {
			driver.switchTo().defaultContent();
		}
		if (actiontype.equalsIgnoreCase("PARENTFRAME")) {
			driver.switchTo().parentFrame();
		}

		if (actiontype.equalsIgnoreCase("FRAMECOUNT")) {
			List<WebElement> count = elements;
			System.out.println("Iframe size are   =====================>" + count.size());
		}

		if (actiontype.equalsIgnoreCase("gettext")) {
			GetText = element.getText();
			System.out.println(GetText);
		}
		
		if (actiontype.equalsIgnoreCase("GetIshineOTP")) {
			String otp=GetText.substring(21,27);
			element.sendKeys(otp);
		}
		
		

		if (actiontype.equalsIgnoreCase("SelectVisibleText")) {
			System.out.println(element);
			Select select = new Select(element);
			select.selectByVisibleText(dataField);
		}

		if (actiontype.equalsIgnoreCase("SelectByValue")) {
			Select select = new Select(element);
			Thread.sleep(3000);
			select.selectByValue(dataField);
		}

		if (actiontype.equalsIgnoreCase("SelectByIndex")) {
			Select select = new Select(element);
			select.selectByIndex(Integer.parseInt(dataField));
		}

		if (actiontype.equalsIgnoreCase("AlertAccept")) {
			driver.switchTo().alert().accept();
		}

		if (actiontype.equalsIgnoreCase("AlertDismiss")) {
			driver.switchTo().alert().dismiss();
		}

		if (actiontype.equalsIgnoreCase("AlertSendkeys")) {
			driver.switchTo().alert().sendKeys(dataField);
		}

		if (actiontype.contains("ScrollDown")) {

			String digit = getOnlyDigit(actiontype); // call the getdigit method to get the data
			int Scroll = Integer.parseInt(digit);

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0, " + Scroll + ")", "");
		}

		if (actiontype.contains("ScrollUp")) {

			String digit = getOnlyDigit(actiontype); // call the getdigit method to get the data
			int Scroll = Integer.parseInt(digit);

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0, " + -Scroll + ")", "");
		}
		
		if (actiontype.contains("ScrollElementUntilVisible")) {     // Scrolling down the page till the element is found	

			JavascriptExecutor js = (JavascriptExecutor) driver;		
	        js.executeScript("arguments[0].scrollIntoView();", element);
		}
		
		
		
		if (actiontype.equalsIgnoreCase("CheckVisibility")) {

			Boolean Verify = Boolean.parseBoolean(dataField);

			if (Verify) {
				try {
					System.out.println(element);
					if (element.isDisplayed()) {
						ConnectDataSheet.status="PASS";
//						utilClass.testCaseCreate(TestCase_No);
						utilClass.passTestCase(driver, TestCase_No, Description);
						System.out.println("Element is Display");

					}
				} catch (Exception e) {
					ConnectDataSheet.status="FAIL";
//					utilClass.testCaseCreate(TestCase_No);
					utilClass.failTestCase(driver, TestCase_No, Neg_Description);
					System.out.println("Element is not Display");
				}
			}

		}

	}

	public static String getOnlyDigit(String actiontype) { ///////// inside the bracket get only the digit

		String digit = null;
		String string = actiontype;
		Pattern pattern = Pattern.compile("\\((\\d+)\\)"); // Matches digits enclosed in parentheses
		Matcher matcher = pattern.matcher(string);

		if (matcher.find()) {
			digit = matcher.group(1); // Extracts the digit(s) within the parentheses

		}
		return digit;

	}

}
