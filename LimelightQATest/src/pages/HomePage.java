package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
	WebDriver driver;
	public HomePage(WebDriver driver){
		this.driver = driver;
	}
	
	By searchBox = By.xpath("//*[@id='twotabsearchtextbox']");
	By searchClick = By.xpath("//*[@id='nav-search']/form/div[2]/div/input");

	public WebElement searchField() {
		return driver.findElement(searchBox);

	}
	public WebElement searchClick() {
		return driver.findElement(searchClick);

	}
}
