package SeleniumProject;

//Generated by Selenium IDE
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;

import java.util.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/*import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;*/

public class ContactUsTests {
	private static WebDriver driver;
	private Map<String, Object> vars;
	private JSONArray users;

	private static String pattern = "[^\\d.]"; // everything part of numbers and dots
	JavascriptExecutor js;

	@Before

	public void setUp() throws IOException {
		//System.setProperty("webdriver.chrome.driver",
			//	"C:\\Users\\dean6\\Documents\\Installers\\chromedriver_win32\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver",
		 "C:\\Users\\avila\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
		vars = new HashMap<String, Object>();

		try {
			JSONParser jsonParser = new JSONParser();
			FileReader reader;
			reader = new FileReader("ContactUsUsers.json");
			// Read JSON file
			users = (JSONArray) jsonParser.parse(reader);
		} catch (FileNotFoundException | ParseException e) {
			e.printStackTrace();
		}

	}

	public static void printMethodName() {
		System.out.println("Starting " + Thread.currentThread().getStackTrace()[2].getMethodName());
	}

	@After
	public void tearDown() {
//		driver.quit();
	}

	@Test
	public void SuccessfulMessageSent() throws InterruptedException {
		printMethodName();
		for (int i = 0; i < users.size(); i++) {
			JSONObject obj = (JSONObject) users.get(i);
			driver.get("https://atid.store/");
			driver.manage().window().setSize(new Dimension(1052, 666));
			driver.findElement(By.xpath("//*[@id=\"menu-item-829\"]/a")).click();
			driver.findElement(By.id("wpforms-submit-15")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("wpforms-15-field_0")).sendKeys((String) obj.get("name"));
			driver.findElement(By.id("wpforms-15-field_5")).sendKeys((String) obj.get("subject"));
			driver.findElement(By.id("wpforms-15-field_4")).sendKeys((String) obj.get("email"));
			driver.findElement(By.id("wpforms-15-field_2")).sendKeys((String) obj.get("message"));

			Thread.sleep(3000);
			
			driver.findElement(By.id("wpforms-submit-15")).click();
		}

	}

	@Test
	public void UnsuccessfulMessageSent() throws InterruptedException {
		printMethodName();
		for (int i = 0; i < users.size(); i++) {
			JSONObject obj = (JSONObject) users.get(i);
			driver.get("https://atid.store/");
			driver.manage().window().setSize(new Dimension(1052, 666));
			driver.findElement(By.xpath("//*[@id=\"menu-item-829\"]/a")).click();
			driver.findElement(By.id("wpforms-submit-15")).click();
			Thread.sleep(3000);
			
			driver.findElement(By.id("wpforms-submit-15")).click();
		}

		// List<WebElement> l = driver.findElements(By.className("woocommerce-error"));
	}

	

	
	public static void main(String args[]) {
		JUnitCore junit = new JUnitCore();
		junit.addListener(new TextListener(System.out));
		org.junit.runner.Result result = junit.run(ContactUsTests.class);

		if (result.getFailureCount() > 0) {
			System.out.println("Test failed.");
			System.exit(1);
		} else {
			System.out.println("Test finished without errors.");
			// System.exit(0);
		}

		System.out.println("All tests finished successfully");
		System.exit(0);

	}
}