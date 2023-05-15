package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactUsPage {
	
private WebDriver driver;
	
	public ContactUsPage(WebDriver driver) {
		this.driver = driver;
	}
	
	
	By buttonByXpath = By.xpath("//*[@id=\"menu-item-829\"]/a");
	By name = By.id("wpforms-15-field_0");
	By subject = By.id("wpforms-15-field_5");
	By email = By.id("wpforms-15-field_4");
	By message = By.id("wpforms-15-field_2");
	By sendMessage = By.id("wpforms-submit-15");
	
	By errMsg1 = By.id("wpforms-15-field_0-error");
	By errMsg2 = By.id("wpforms-15-field_4-error");
	By errMsg3 = By.id("wpforms-15-field_2-error");
	
	public void ClickContactUs() {
		driver.findElement(buttonByXpath).click();
	}
	
	public void enterName (String name) {
		driver.findElement(this.name).sendKeys(name);;
	}
	
	public void enterSubject (String subject) {
		driver.findElement(this.subject).sendKeys(subject);;
	}
	
	public void enterEmail (String email) {
		driver.findElement(this.email).sendKeys(email);;
	}
	
	public void enterMessage (String message) {
		driver.findElement(this.message).sendKeys(message);;
	}
	
	public void sendMessage () {
		driver.findElement(sendMessage).click();
	}
	
	public boolean isFoundErr () {
		int errors = 0;
		try {
			driver.findElement(this.errMsg1);
			errors++;
		}
		catch (Exception e) {
		}
		try {
			driver.findElement(this.errMsg2);
			errors++;
		}
		catch (Exception e) {
		}
		try {
			driver.findElement(this.errMsg3);
			errors++;
		}
		catch (Exception e) {
		}
		return errors > 0;
		
	}

}
