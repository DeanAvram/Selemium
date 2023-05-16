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
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import pages.MenuPage;


public class ProccedToCheckOutTest {
	private static WebDriver driver;
	private MenuPage menuPage;
	
	Logger logger = LogManager.getLogger(ProccedToCheckOutTest.class);

	
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
	public void SuccessfulProccedToCheckout() throws InterruptedException {
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
		Thread.sleep(1000);
		menuPage.goToToCart();
		logger.debug("go to cart");
		boolean isVisible = menuPage.isProceedToCheckoutVisible();
		logger.debug("checking if procced to checkout is visible");
		if (isVisible) 
			logger.info("Procced to checkout Operation test finished successfuly");
		else {
			logger.error("Procced to checkout Operation test finished with errors");
		}

	}

	@Test
	public void SuccessfulCantProccedToCheckout() throws InterruptedException {
		logger.info("Starting " + getMethodName());
		driver.get("https://atid.store/");
		logger.info("opening website");
		driver.manage().window().setSize(new Dimension(1052, 666));
		logger.info("increase window size");
		menuPage.goToMenu();
		logger.debug("go to main menu");
		menuPage.goToProduct();
		logger.debug("go to specific product");
		Thread.sleep(1000);
		menuPage.goToToCart();
		logger.debug("add product to cart");
		try {
			menuPage.proceedToCheckOut();
			logger.debug("trying to procced to checkout");
		} catch (NoSuchElementException e) {
			// if can't find that element -> the test completed successfully
			logger.info("Cant procced to checkout Operation test finished successfuly");
		}

	}

	public static void main(String args[]) {
		JUnitCore junit = new JUnitCore();
		junit.addListener(new TextListener(System.out));
		org.junit.runner.Result result = junit.run(ProccedToCheckOutTest.class);

		if (result.getFailureCount() > 0) {
			System.out.println("Test failed.");
			System.exit(1);
		} else 
			System.out.println("Test finished without errors.");
		
		System.out.println("All tests finished successfully");
		System.exit(0);

	}
}