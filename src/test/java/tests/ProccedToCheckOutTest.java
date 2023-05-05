package tests;

import java.io.IOException;

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

/*import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;*/

public class ProccedToCheckOutTest {
	private static WebDriver driver;
	//private Map<String, Object> vars;
	private MenuPage menuPage;

	
	public static int countSuccessfulProccedToCheckout = 0;
	public static int countSuccessfulCantProccedToCheckout = 0;
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
	public void SuccessfulProccedToCheckout() throws InterruptedException {
		printMethodName();
		driver.get("https://atid.store/");
		driver.manage().window().setSize(new Dimension(1052, 666));
		menuPage.goToMenu();
		menuPage.goToProduct();
		menuPage.addToCart();
		Thread.sleep(1000);
		menuPage.goToToCart();
		if (menuPage.isProceedToCheckoutVisible())
			countSuccessfulProccedToCheckout++;

	}

	@Test
	public void SuccessfulCantProccedToCheckout() throws InterruptedException {
		printMethodName();
		driver.get("https://atid.store/");
		driver.manage().window().setSize(new Dimension(1052, 666));
		menuPage.goToMenu();
		menuPage.goToProduct();
		Thread.sleep(1000);
		menuPage.goToToCart();
		try {
			menuPage.proceedToCheckOut();
		} catch (NoSuchElementException e) {
			// if can't find that element -> the test completed successfully
			countSuccessfulCantProccedToCheckout++;
		}

	}

	public static void main(String args[]) {
		JUnitCore junit = new JUnitCore();
		junit.addListener(new TextListener(System.out));
		org.junit.runner.Result result = junit.run(ProccedToCheckOutTest.class);

		if (result.getFailureCount() > 0) {
			System.out.println("Test failed.");
			System.exit(1);
		} else {
			System.out.println("Test finished without errors.");
			// System.exit(0);
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