package tests;

import java.io.IOException;
import org.apache.logging.log4j.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import tests.BaseTestClass;

import pages.MenuPage;

/*import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;*/

public class AddToCartTest {
	private static WebDriver driver;
	//private Map<String, Object> vars;
	private MenuPage menuPage;
	Logger logger=LogManager.getLogger(AddToCartTest.class);

	//private static String pattern = "[^\\d.]"; // everything part of numbers and dots
	public static int countSuccessfulAddToCart = 0;
	public static int countSuccessfulMultipleAddToCart = 0;
	
	JavascriptExecutor js;

	@Before

	public void setUp() throws IOException {
		driver = BaseTestClass.initializeDriver();
		js = (JavascriptExecutor) driver;
		//vars = new HashMap<String, Object>();
		menuPage = new MenuPage(driver);
	}

	public static void printMethodName() {
		System.out.println("Starting " + Thread.currentThread().getStackTrace()[2].getMethodName());
	}


	@After
	public void tearDown() {
		driver.quit();
	}


	@Test
	public void SuccessfulAddToCartOperation() throws InterruptedException {
		printMethodName();
		driver.get("https://atid.store/");
		driver.manage().window().setSize(new Dimension(1052, 666));
		double cartAmountBeforeAdd = menuPage.getCartAmount();
		
		menuPage.goToMenu();
		menuPage.goToProduct();
		menuPage.addToCart();
		
		double productPrice = menuPage.getProductPrice();
		double cartAmountAfterAdd = menuPage.getCartAmount();
		//System.out.println("Product price: " + productPrice);
		//System.out.println("Cart before: " + cartAmountBeforeAdd);
		//System.out.println("Cart after: " + cartAmountAfterAdd);
		if (cartAmountBeforeAdd == 0 && cartAmountAfterAdd == productPrice)
			countSuccessfulAddToCart++;

	}

	@Test
	public void SuccessfulMultipleAddToCartOperation() throws InterruptedException {
		printMethodName();
		int count = 2;
		driver.get("https://atid.store/");
		driver.manage().window().setSize(new Dimension(1052, 666));
		double cartAmountBeforeAdd = menuPage.getCartAmount();
		for (int i = 0; i < count; i++) {
			// add the same product two times to the cart
			menuPage.goToMenu();
			menuPage.goToProduct();
			menuPage.addToCart();
			Thread.sleep(1000);
		}
		double productPrice = menuPage.getProductPrice();
		double cartAmountAfterAdd = menuPage.getCartAmount();
		System.out.println("Product price: " + productPrice);
		System.out.println("Cart before: " + cartAmountBeforeAdd);
		System.out.println("Cart after: " + cartAmountAfterAdd);
		if (cartAmountBeforeAdd == 0 && cartAmountAfterAdd == productPrice * count)
			countSuccessfulMultipleAddToCart++;
		

	}


	public static void main(String args[]) {
		JUnitCore junit = new JUnitCore();
		junit.addListener(new TextListener(System.out));
		org.junit.runner.Result result = junit.run(AddToCartTest.class);

		if (result.getFailureCount() > 0) {
			System.out.println("Test failed.");
			System.exit(1);
		} else {
			System.out.println("Test finished without errors.");
			// System.exit(0);
		}

		if (countSuccessfulAddToCart == 0) {
			System.out.println("Test of successful add to card failed");
			System.exit(1);
		}

		if (countSuccessfulMultipleAddToCart == 0) {
			System.out.println("Test of successful multiple add to card failed");
			System.exit(1);
		}

		System.out.println("All tests finished successfully");
		System.exit(0);

	}
}