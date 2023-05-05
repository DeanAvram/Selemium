package tests;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

//Generated by Selenium IDE
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pages.MenuPage;
import pages.PlaceOrderPage;

/*import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;*/

public class PlaceOrderTest {
	private static WebDriver driver;
	//private Map<String, Object> vars;
	private JSONArray users;
	private MenuPage menuPage;
	private PlaceOrderPage placeOrderPage;

	public static int countSuccessfulPurchaseOperation = 0;
	public static int countUnsuccessfulPurchaseOperation = 0;
	JavascriptExecutor js;

	@Before

	public void setUp() throws IOException {
		driver = BaseTestClass.initializeDriver();
		js = (JavascriptExecutor) driver;
		//vars = new HashMap<String, Object>();
		menuPage = new MenuPage(driver);
		placeOrderPage = new PlaceOrderPage(driver);

		try {
			JSONParser jsonParser = new JSONParser();
			FileReader reader;
			reader = new FileReader("CartUsers.json");
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
		driver.quit();
	}

	@Test
	public void SuccessfulPurchaseOperation() throws InterruptedException {
		printMethodName();
		for (int i = 0; i < users.size(); i++) {
			JSONObject obj = (JSONObject) users.get(i);
			driver.get("https://atid.store/");
			driver.manage().window().setSize(new Dimension(1052, 666));
			menuPage.goToMenu();
			menuPage.goToProduct();
			menuPage.addToCart();
			menuPage.goToToCart();
			menuPage.proceedToCheckOut();
			Thread.sleep(1000);
			
			placeOrderPage.enterFirstName((String) obj.get("firstName"));
			placeOrderPage.enterLastName((String) obj.get("lastName"));
			placeOrderPage.enterAddress((String) obj.get("address"));
			placeOrderPage.enterPostcode((String) obj.get("postcode"));
			placeOrderPage.enterCity((String) obj.get("city"));
			placeOrderPage.enterPhone((String) obj.get("phone"));
			placeOrderPage.enterEmail((String) obj.get("email"));
			Thread.sleep(1000);
			menuPage.placeOrder();
			List<WebElement> errors = driver.findElements(By.className("woocommerce-error"));
			Thread.sleep(3000);
			if (errors.size() == 0) {
				System.out.println("xx");
				// did not find an error message error
				countSuccessfulPurchaseOperation++;
			}
		}

	}

	@Test
	public void UnsuccessfulPurchaseOperation() throws InterruptedException {
		printMethodName();
		driver.get("https://atid.store/");
		driver.manage().window().setSize(new Dimension(1052, 666));
		menuPage.goToMenu();
		menuPage.goToProduct();
		menuPage.addToCart();
		menuPage.goToToCart();
		menuPage.proceedToCheckOut();
		Thread.sleep(1000);
		menuPage.placeOrder();
		Thread.sleep(3000);
		if (!driver.findElements(By.className("woocommerce-error")).isEmpty()) {
			// found at least one error (we wanted to see those error)
			countUnsuccessfulPurchaseOperation++;
		}
	}


	public static void main(String args[]) {
		JUnitCore junit = new JUnitCore();
		junit.addListener(new TextListener(System.out));
		org.junit.runner.Result result = junit.run(PlaceOrderTest.class);

		if (result.getFailureCount() > 0) {
			System.out.println("Test failed.");
			System.exit(1);
		} else {
			System.out.println("Test finished without errors.");
			// System.exit(0);
		}

		if (countSuccessfulPurchaseOperation == 0) {
			System.out.println("Test of successful purchase failed");
			System.exit(1);
		}

		if (countUnsuccessfulPurchaseOperation == 0) {
			System.out.println("Test of unsuccessful purchase failed");
			System.exit(1);
		}


		System.out.println("All tests finished successfully");
		System.exit(0);

	}
}