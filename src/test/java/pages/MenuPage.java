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
	
	public void goToMenu() {
		driver.findElement(goToMenu).click();
	}
	
	public void goToProduct() {
		driver.findElement(goToProduct).click();
	}
	
	public void addToCart() {
		driver.findElement(addToCart).click();
	}
	

}
