package SeleniumProject;

//Generated by Selenium IDE
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
import java.util.*;
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
	private WebDriver driver;
	private Map<String, Object> vars;
	JavascriptExecutor js;

	@Before

	public void setUp() throws IOException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\dean6\\Documents\\Installers\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
		vars = new HashMap<String, Object>();

	}

	@After
	public void tearDown() {
		//driver.quit();
	}

	@Test
	public void SuccessfulPurchaseOperation() {
		driver.get("https://atid.store/product/anchor-bracelet/");
		driver.manage().window().setSize(new Dimension(1052, 666));
		driver.findElement(By.name("add-to-cart")).click();
		driver.findElement(By.className("ast-site-header-cart")).click();
		driver.findElement(By.className("wc-proceed-to-checkout")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("place_order")).click();
		
		
		//check if the price changed
		
	}
	/*
	@Test
	public void UnsuccessfulPurchaseOperation() {
		// Test name: t1
		// Step # | name | target | value
		// 1 | open | / |
		driver.get("https://www.google.com/");
		// 2 | setWindowSize | 1052x666 |
		driver.manage().window().setSize(new Dimension(1052, 666));
		// 3 | type | name=q | hello
		driver.findElement(By.name("q")).sendKeys("hello");
		// 4 | sendKeys | name=q | ${KEY_ENTER}
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		// 5 | runScript | window.scrollTo(0,1500) |
		js.executeScript("window.scrollTo(0,1500)");
	}*/

	public static void main(String args[]) {
		JUnitCore junit = new JUnitCore();
		junit.addListener(new TextListener(System.out));
		org.junit.runner.Result result = junit.run(SanityTests.class); // Replace "SampleTest" with the name of your
																		// class
		if (result.getFailureCount() > 0) {
			System.out.println("Test failed.");
			System.exit(1);
		} else {
			System.out.println("Test finished successfully.");
			System.exit(0);
		}
	}
}