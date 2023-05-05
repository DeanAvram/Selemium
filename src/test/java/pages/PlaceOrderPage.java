package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PlaceOrderPage {

private WebDriver driver;
	
	public PlaceOrderPage(WebDriver driver) {
		this.driver = driver;
	}
	
	By firstName = By.id("billing_first_name");
	By lastName = By.id("billing_last_name");
	By address = By.id("billing_address_1");
	By postcode = By.id("billing_postcode");
	By city = By.id("billing_city");
	By phone = By.id("billing_phone");
	By email = By.id("billing_email");
	
	public void enterFirstName(String firstName) {
		driver.findElement(this.firstName).sendKeys(firstName);;
	}
	
	public void enterLastName(String lastName) {
		driver.findElement(this.lastName).sendKeys(lastName);;
	}
	
	public void enterAddress(String address) {
		driver.findElement(this.address).sendKeys(address);;
	}
	
	public void enterPostcode(String postcode) {
		driver.findElement(this.postcode).sendKeys(postcode);;
	}
	
	public void enterCity(String city) {
		driver.findElement(this.city).sendKeys(city);;
	}
	
	public void enterPhone(String phone) {
		driver.findElement(this.phone).sendKeys(phone);;
	}
	
	public void enterEmail(String email) {
		driver.findElement(this.email).sendKeys(email);;
	}
	
	
	

}
