package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTestClass {

	public static WebDriver initializeDriver() {

	//	System.setProperty("webdriver.chrome.driver",
		//		"C:\\Users\\dean6\\Documents\\Installers\\chromedriver_win32\\chromedriver.exe");
		 System.setProperty("webdriver.chrome.driver",
		 "C:\\Users\\avila\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		return driver;

	}
}
