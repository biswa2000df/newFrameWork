package FRAMEWORK;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.codoid.products.exception.FilloException;

public class LocatorClass extends ConnectDataSheet {

	public LocatorClass() throws FilloException {
		super();
		// TODO Auto-generated constructor stub
	}

	// In this method xpathpick()
	// first check the PropertyName and PropertyValue it is null or not
	// if it is null then go to the else part and call to the datasheet() for the
	// sheet2 value.
	// if it is not null then check the PropertyName and PropertyValue
	// to get the webelement and webelements and store into the webelement variable
	// if the webelement is no present i have use in the the try catch block if it
	// is failed or
	// it goes to the catch block to call the datasheet method().

//	LocatorClass xget;
//	WebElement webElement;
//	List<WebElement> webElements;
//	ActionClass actClass;
//	ConnectDataSheet conSheet;

	/*
	 * String TestCase_No, String PropertyName, String PropertyValue, String
	 * Datafield, String Action, String Description, String Neg_Description,
	 * WebDriver driver
	 */

	public void xpathpick() throws FilloException, InterruptedException, IOException {

		webElement = null;
		webElements = null;

		locatorClass = new LocatorClass();
		actClass = new ActionClass();

		By by;

		if ((PropertyName != null && !PropertyName.isEmpty() && PropertyValue != null && !PropertyValue.isEmpty())) {

			if (PropertyName.equalsIgnoreCase("xpath")) {
				by = By.xpath(PropertyValue);
			} else if (PropertyName.equalsIgnoreCase("id")) {
				by = By.id(PropertyValue);
			} else if (PropertyName.equalsIgnoreCase("name")) {
				by = By.name(PropertyValue);
			} else if (PropertyName.equalsIgnoreCase("className")) {
				by = By.className(PropertyValue);
			} else if (PropertyName.equalsIgnoreCase("css")) {
				by = By.cssSelector(PropertyValue);
			} else if (PropertyName.equalsIgnoreCase("tagName")) {
				by = By.tagName(PropertyValue);
			} else if (PropertyName.equalsIgnoreCase("linkText")) {
				by = By.linkText(PropertyValue);
			} else if (PropertyName.equalsIgnoreCase("partialLinkText")) {
				by = By.partialLinkText(PropertyValue);
			} else {
				// Handle unsupported PropertyName type
				throw new IllegalArgumentException("Unsupported PropertyName type: " + PropertyName);
			}
			try {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
				wait.until(ExpectedConditions.visibilityOfElementLocated(by));
				webElement = driver.findElement(by);
				webElements = driver.findElements(by);
				ConnectDataSheet.DataFieldRead();
				
				
				/*
				 * TestCase_No, webElement, webElements, Datafield, Action, Description,
				 * Neg_Description, driver
				 */
			} catch (Exception e) {
				//i write again to the webelement becaause it print the erroe exceptioin the clear format thats why i write because the explicity wait it is showing only timeout exception so thats why.
				try {
					webElement = driver.findElement(by);
					webElements = driver.findElements(by);
				}
				catch(Exception E) {
//					System.err.println(E.getMessage());
					E.printStackTrace();
					logger.error("Error Message :  " + E);
				}
				
//				System.err.println(e.getMessage());
//				e.printStackTrace();
				if (Action.equalsIgnoreCase("CheckVisibility")) {
					ConnectDataSheet.DataFieldRead();
				}
				fail++;
				
				
				/*
				 * TestCase_No, webElement, webElements, Datafield, Action, Description,
				 * Neg_Description, driver
				 */

			}
		}

		else {
			ConnectDataSheet.DataFieldRead();
			/*
			 * TestCase_No, webElement, webElements, Datafield, Action, Description,
			 * Neg_Description, driver
			 */
		}

	}
}
