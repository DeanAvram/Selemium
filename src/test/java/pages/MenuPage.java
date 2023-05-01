package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MenuPage {
	
	private WebDriver driver;
	
	public MenuPage(WebDriver driver) {
		this.driver = driver;
	}
	
	By productbyId = By.id("menu-item-45");
	By addToCartByXpath = By.xpath("//*[@id=\"main\"]/div/ul/li[1]/div[1]/a/img");
	
	public void clickProduct() {
		driver.findElement(productbyId).click();;
	}
	
	public void addToCart() {
		
		
	}

}
