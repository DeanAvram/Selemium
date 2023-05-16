package tests;

import org.apache.logging.log4j.*;
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


public class PlaceOrderTest {
	private static WebDriver driver;
	private JSONArray users;
	private MenuPage menuPage;
	private PlaceOrderPage placeOrderPage;
	Logger logger = LogManager.getLogger(PlaceOrderTest.class);
	
	

	JavascriptExecutor js;

	@Before
	public void setUp() throws IOException {
		driver = BaseTestClass.initializeDriver();
		js = (JavascriptExecutor) driver;
		menuPage = new MenuPage(driver);
		placeOrderPage = new PlaceOrderPage(driver);

		try {
			JSONParser jsonParser = new JSONParser();
			FileReader reader;
			reader = new FileReader("CartUsers.json");
			// Read JSON file
			users = (JSONArray) jsonParser.parse(reader);
		} catch (/*FileNotFoundException |*/ ParseException e) {
			e.printStackTrace();
		}

	}

	public static String getMethodName() {
		return Thread.currentThread().getStackTrace()[2].getMethodName();
	}


	@After
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void SuccessfulPurchaseOperation() throws InterruptedException {
		logger.info("Starting " + getMethodName());
		for (int i = 0; i < users.size(); i++) {
			JSONObject obj = (JSONObject) users.get(i);
			driver.get("https://atid.store/");
			logger.info("opening website");
			driver.manage().window().setSize(new Dimension(1052, 666));
			logger.info("increase window size");
			menuPage.goToMenu();
			logger.debug("go to main menu");
			menuPage.goToProduct();
			logger.debug("go to specific product");
			menuPage.addToCart();
			logger.debug("add product to cart");
			menuPage.goToToCart();
			logger.debug("go to cart");
			menuPage.proceedToCheckOut();
			logger.debug("proceed to checkout");
			Thread.sleep(1000);
			
			placeOrderPage.enterFirstName((String) obj.get("firstName"));
			logger.debug("send first name as key");
			placeOrderPage.enterLastName((String) obj.get("lastName"));
			logger.debug("send last name as key");
			placeOrderPage.enterAddress((String) obj.get("address"));
			logger.debug("send address as key");
			placeOrderPage.enterPostcode((String) obj.get("postcode"));
			logger.debug("send postcode as key");
			placeOrderPage.enterCity((String) obj.get("city"));
			logger.debug("send city as key");
			placeOrderPage.enterPhone((String) obj.get("phone"));
			logger.debug("send phone as key");
			placeOrderPage.enterEmail((String) obj.get("email"));
			logger.debug("send email as key");
			Thread.sleep(1000);
			menuPage.placeOrder();
			List<WebElement> errors = driver.findElements(By.className("woocommerce-error"));
			Thread.sleep(3000);
			if (errors.size() == 0) {
				//did not find an error message error
				logger.info("Successful Purchase Operation test finished successfuly");
			}else {
				//found an error message error
				logger.error("Successful Purchase Operation test finished with errors");
			}
		}

	}

	@Test
	public void UnsuccessfulPurchaseOperation() throws InterruptedException {
		logger.info("Starting " + getMethodName());
		driver.get("https://atid.store/");
		logger.info("opening website");
		driver.manage().window().setSize(new Dimension(1052, 666));
		logger.info("increase window size");
		menuPage.goToMenu();
		logger.debug("go to main menu");
		menuPage.goToProduct();
		logger.debug("go to specific product");
		menuPage.addToCart();
		logger.debug("add product to cart");
		menuPage.goToToCart();
		logger.debug("go to cart");
		menuPage.proceedToCheckOut();
		logger.debug("proceed to checkout");
		Thread.sleep(1000);
		menuPage.placeOrder();
		logger.debug("place order");
		Thread.sleep(3000);
		if (!driver.findElements(By.className("woocommerce-error")).isEmpty()) {
			// found at least one error (we wanted to see those error)
			logger.info("Unsuccessful Purchase Operation test finished successfuly");
		}
		else {
			logger.error("Unsuccessful Purchase Operation test finished with errors");
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
			System.exit(0);
		}

		System.out.println("All tests finished successfully");
		System.exit(0);
		

	}
}