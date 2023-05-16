package tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import pages.MenuPage;

public class AddToCartTest {
	private static WebDriver driver;
	private MenuPage menuPage;
	Logger logger=LogManager.getLogger(AddToCartTest.class);

	JavascriptExecutor js;

	@Before
	public void setUp() throws IOException {
		driver = BaseTestClass.initializeDriver();
		js = (JavascriptExecutor) driver;
		menuPage = new MenuPage(driver);
	}

	public static String getMethodName() {
		return Thread.currentThread().getStackTrace()[2].getMethodName();
	}

	@After
	public void tearDown() {
		driver.quit();
	}


	@Test
	public void SuccessfulAddToCartOperation() throws InterruptedException {
		logger.info("Starting " + getMethodName());
		driver.get("https://atid.store/");
		logger.info("opening website");
		driver.manage().window().setSize(new Dimension(1052, 666));
		logger.info("increase window size");
		double cartAmountBeforeAdd = menuPage.getCartAmount();
		logger.debug("calculate cart amount before add");
		menuPage.goToMenu();
		logger.debug("go to main menu");
		menuPage.goToProduct();
		logger.debug("go to specific product");
		menuPage.addToCart();
		logger.debug("add product to cart");
		double productPrice = menuPage.getProductPrice();
		logger.debug("calculate product price");
		double cartAmountAfterAdd = menuPage.getCartAmount();
		logger.debug("calculate cart amount after add");
		if (cartAmountBeforeAdd == 0 && cartAmountAfterAdd == productPrice)
			logger.info("Add To Cart Operation test finished successfuly");
		else
			logger.error("Add To Cart Operation test with errors");

	}

	@Test
	public void SuccessfulMultipleAddToCartOperation() throws InterruptedException {
		logger.info("Starting " + getMethodName());
		int count = 2;
		driver.get("https://atid.store/");
		logger.info("opening website");
		driver.manage().window().setSize(new Dimension(1052, 666));
		logger.info("increase window size");
		double cartAmountBeforeAdd = menuPage.getCartAmount();
		logger.debug("calculate cart amount before add");
		for (int i = 0; i < count; i++) {
			// add the same product twice to the cart
			logger.debug("starting adding product number" + (i+1) + " to cart" );
			menuPage.goToMenu();
			logger.debug("go to main menu");
			menuPage.goToProduct();
			logger.debug("go to specific product");
			menuPage.addToCart();
			logger.debug("add product to cart");
			Thread.sleep(1000);
		}
		double productPrice = menuPage.getProductPrice();
		logger.debug("calculate product price");
		double cartAmountAfterAdd = menuPage.getCartAmount();
		logger.debug("calculate cart amount after add");
		if (cartAmountBeforeAdd == 0 && cartAmountAfterAdd == productPrice * count)
			logger.info("Add Multiple Products To Cart Operation test finished successfuly");
		else
			logger.error("Add Multiple Products To Cart Operation test finished with errors");
	}


	public static void main(String args[]) {
		JUnitCore junit = new JUnitCore();
		junit.addListener(new TextListener(System.out));
		org.junit.runner.Result result = junit.run(AddToCartTest.class);

		if (result.getFailureCount() > 0) {
			System.out.println("Test failed.");
			System.exit(1);
		} else 
			System.out.println("Test finished without errors.");
		System.out.println("All tests finished successfully");
		System.exit(0);

	}
}