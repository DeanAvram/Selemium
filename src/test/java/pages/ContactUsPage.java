package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactUsPage {
	
private WebDriver driver;
	
	public ContactUsPage(WebDriver driver) {
		this.driver = driver;
	}
	
	
	By buttonByXpath = By.xpath("//*[@id=\"menu-item-829\"]/a");
	
	public void ClickContactUs() {
		driver.findElement(buttonByXpath).click();;
	}

}
