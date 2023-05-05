package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MenuPage {
	
	private WebDriver driver;
	
	public MenuPage(WebDriver driver) {
		this.driver = driver;
	}
	
	By goToMenu = By.id("menu-item-45");
	By goToProduct = By.xpath("//*[@id=\"main\"]/div/ul/li[1]/div[1]/a/img");
	By addToCart =  By.name("add-to-cart");
	By goToCart =  By.className("ast-site-header-cart");
	By productPrice = By.xpath("//*[@id=\"product-160\"]/div[2]/p/span/bdi");
	By cartAmount = By.xpath("//*[@id=\"ast-site-header-cart\"]/div[1]/a/span/span/span/bdi");
	By proceedToCheckOut =  By.className("wc-proceed-to-checkout");
	By placeOrder =  By.id("place_order");

	String pattern = "[^\\d.]";
	
	public void goToMenu() {
		driver.findElement(goToMenu).click();
	}
	
	public void goToProduct() {
		driver.findElement(goToProduct).click();
	}
	
	public void addToCart() {
		driver.findElement(addToCart).click();
	}
	
	public void goToToCart() {
		driver.findElement(goToCart).click();
	}
	
	public double getProductPrice() {
		return Double.parseDouble(driver
				.findElement(productPrice).getText().replaceAll(pattern, ""));
	}
	
	public double getCartAmount() {
		return Double.parseDouble(driver
				.findElement(cartAmount).getText().replaceAll(pattern, ""));
	}

	public void proceedToCheckOut() {
		driver.findElement(proceedToCheckOut).click();
	}
	
	public boolean isProceedToCheckoutVisible () {
		return driver.findElement(proceedToCheckOut).isDisplayed();
	}
	
	public void placeOrder() {
		driver.findElement(placeOrder).click();
	}
	

}
