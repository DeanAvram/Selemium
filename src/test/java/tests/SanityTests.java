package tests;

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

import pages.MenuPage;

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

public class SanityTests {
	private static WebDriver driver;
	private Map<String, Object> vars;
	private JSONArray users;

	private static String pattern = "[^\\d.]"; // everything part of numbers and dots
	public static int countSuccessfulPurchaseOperation = 0;
	public static int countUnsuccessfulPurchaseOperation = 0;
	public static int countSuccessfulAddToCart = 0;
	public static int countSuccessfulMultipleAddToCart = 0;
	public static int countSuccessfulProccedToCheckout = 0;
	public static int countSuccessfulCantProccedToCheckout = 0;
	JavascriptExecutor js;

	@Before

	public void setUp() throws IOException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\dean6\\Documents\\Installers\\chromedriver_win32\\chromedriver.exe");
		// System.setProperty("webdriver.chrome.driver",
		// "C:\\Users\\avila\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
		vars = new HashMap<String, Object>();

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

	public static double getCartAmount() {
		String s = driver.findElement(By.xpath("//*[@id=\"ast-site-header-cart\"]/div[1]/a/span/span/span/bdi"))
				.getText();
		return Double.parseDouble(s.replaceAll(pattern, ""));
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	//@Test
	public void SuccessfulPurchaseOperation() throws InterruptedException {
		printMethodName();
		for (int i = 0; i < users.size(); i++) {
			JSONObject obj = (JSONObject) users.get(i);
			driver.get("https://atid.store/");
			driver.manage().window().setSize(new Dimension(1052, 666));
			driver.findElement(By.id("menu-item-45")).click();
			driver.findElement(By.xpath("//*[@id=\"main\"]/div/ul/li[1]/div[1]/a/img")).click();
			driver.findElement(By.name("add-to-cart")).click();
			driver.findElement(By.className("ast-site-header-cart")).click();
			driver.findElement(By.className("wc-proceed-to-checkout")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("billing_first_name")).sendKeys((String) obj.get("firstName"));
			driver.findElement(By.id("billing_last_name")).sendKeys((String) obj.get("lastName"));
			driver.findElement(By.id("billing_address_1")).sendKeys((String) obj.get("address"));
			driver.findElement(By.id("billing_postcode")).sendKeys((String) obj.get("postcode"));
			driver.findElement(By.id("billing_city")).sendKeys((String) obj.get("city"));
			driver.findElement(By.id("billing_phone")).sendKeys((String) obj.get("phone"));
			driver.findElement(By.id("billing_email")).sendKeys((String) obj.get("email"));
			driver.findElement(By.id("place_order")).click();

			Thread.sleep(3000);
			if (driver.findElements(By.className("woocommerce-error")).isEmpty()) {
				// did not find an error message error
				countSuccessfulPurchaseOperation++;
			}
		}

	}

	//@Test
	public void UnsuccessfulPurchaseOperation() throws InterruptedException {
		printMethodName();
		driver.get("https://atid.store/");
		driver.manage().window().setSize(new Dimension(1052, 666));
		
		MenuPage menuPage = new MenuPage(driver);
		
		//driver.findElement(By.id("menu-item-45")).click();
		menuPage.goToMenu();
		//driver.findElement(By.xpath("//*[@id=\"main\"]/div/ul/li[1]/div[1]/a/img")).click();
		menuPage.goToProduct();
		//driver.findElement(By.name("add-to-cart")).click();
		menuPage.addToCart();
		driver.findElement(By.className("ast-site-header-cart")).click();
		driver.findElement(By.className("wc-proceed-to-checkout")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("place_order")).click();
		Thread.sleep(3000);
		// List<WebElement> l = driver.findElements(By.className("woocommerce-error"));
		if (!driver.findElements(By.className("woocommerce-error")).isEmpty()) {
			// found at least one error (we wanted to see those error)
			countUnsuccessfulPurchaseOperation++;
		}
	}

	@Test
	public void SuccessfulAddToCartOperation() throws InterruptedException {
		printMethodName();
		driver.get("https://atid.store/");
		driver.manage().window().setSize(new Dimension(1052, 666));
		double cartAmountBeforeAdd = getCartAmount();
		driver.findElement(By.id("menu-item-45")).click();
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/ul/li[1]/div[1]/a/img")).click();
		driver.findElement(By.name("add-to-cart")).click();
		double productPrice = Double.parseDouble(driver
				.findElement(By.xpath("//*[@id=\"product-160\"]/div[2]/p/span/bdi")).getText().replaceAll(pattern, ""));
		double cartAmountAfterAdd = getCartAmount();
		// System.out.println("Product price: " + productPrice);
		// System.out.println("Cart before: " + cartAmountBeforeAdd);
		// System.out.println("Cart after: " + cartAmountAfterAdd);
		if (cartAmountBeforeAdd == 0 && cartAmountAfterAdd == productPrice)
			countSuccessfulAddToCart++;

	}

	//@Test
	public void SuccessfulMultipleAddToCartOperation() throws InterruptedException {
		printMethodName();
		int count = 2;
		driver.get("https://atid.store/");
		driver.manage().window().setSize(new Dimension(1052, 666));
		double cartAmountBeforeAdd = getCartAmount();
		for (int i = 0; i < count; i++) {
			// add the same product two times to the cart
			driver.findElement(By.id("menu-item-45")).click();
			driver.findElement(By.xpath("//*[@id=\"main\"]/div/ul/li[1]/div[1]/a/img")).click();
			driver.findElement(By.name("add-to-cart")).click();
			Thread.sleep(1000);
		}
		double productPrice = Double.parseDouble(driver
				.findElement(By.xpath("//*[@id=\"product-160\"]/div[2]/p/span/bdi")).getText().replaceAll(pattern, ""));
		double cartAmountAfterAdd = getCartAmount();
		// System.out.println("Product price: " + productPrice);
		// System.out.println("Cart before: " + cartAmountBeforeAdd);
		// System.out.println("Cart after: " + cartAmountAfterAdd);
		if (cartAmountBeforeAdd == 0 && cartAmountAfterAdd == productPrice * count)
			countSuccessfulAddToCart++;

	}

	//@Test
	public void SuccessfulProccedToCheckout() throws InterruptedException {
		printMethodName();
		driver.get("https://atid.store/");
		driver.manage().window().setSize(new Dimension(1052, 666));
		driver.findElement(By.id("menu-item-45")).click();
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/ul/li[1]/div[1]/a/img")).click();
		driver.findElement(By.name("add-to-cart")).click();
		Thread.sleep(1000);
		driver.findElement(By.className("ast-site-header-cart")).click();
		if (driver.findElement(By.className("wc-proceed-to-checkout")).isDisplayed())
			countSuccessfulProccedToCheckout++;

	}

	//@Test
	public void SuccessfulCantProccedToCheckout() throws InterruptedException {
		printMethodName();
		driver.get("https://atid.store/");
		driver.manage().window().setSize(new Dimension(1052, 666));
		driver.findElement(By.id("menu-item-45")).click();
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/ul/li[1]/div[1]/a/img")).click();
		Thread.sleep(1000);
		driver.findElement(By.className("ast-site-header-cart")).click();
		try {
			driver.findElement(By.className("wc-proceed-to-checkout"));
		} catch (NoSuchElementException e) {
			// if can't find that element -> the test completed successfully
			countSuccessfulCantProccedToCheckout++;
		}

	}

	public static void main(String args[]) {
		JUnitCore junit = new JUnitCore();
		junit.addListener(new TextListener(System.out));
		org.junit.runner.Result result = junit.run(SanityTests.class);

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

		if (countSuccessfulAddToCart == 0) {
			System.out.println("Test of successful add to card failed");
			System.exit(1);
		}

		if (countSuccessfulMultipleAddToCart == 0) {
			System.out.println("Test of successful multiple add to card failed");
			System.exit(1);
		}

		if (countSuccessfulProccedToCheckout == 0) {
			System.out.println("Test of successful proceed to checkout failed");
			System.exit(1);
		}

		if (countSuccessfulCantProccedToCheckout == 0) {
			System.out.println("Test of cant proceed to checkout failed");
			System.exit(1);
		}

		System.out.println("All tests finished successfully");
		System.exit(0);

	}
}