package pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

/**
 * @author komalpatel
 *
 */
public class SearchResultsPage {
	WebDriver driver;

	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
	}

	By plasticCheckbox = By.xpath("//*[@id='leftNavContainer']/ul[9]/div/li[1]/span/span/div/label/input");
	By priceMinRange = By.xpath("//*[@id='low-price']");
	By priceMaxRange = By.xpath("//*[@id='high-price']");
	By priceRangeGo = By.xpath("//span[@class='a-list-item']/form/span/span");
	By firstFive = By.xpath("//ul[@id='s-results-list-atf']/li");

	public WebElement plasticCheckbox() {
		return driver.findElement(plasticCheckbox);
	}

	public WebElement priceMinRange() {
		return driver.findElement(priceMinRange);
	}

	public WebElement priceMaxRange() {
		return driver.findElement(priceMaxRange);
	}

	public WebElement priceRangeGo() {
		return driver.findElement(priceRangeGo);

	}

	/**
	 * Get Name, price, ratings from search result of first five items.
	 * Collect each list and add it to the Map for later processing.
	 * @return HashMap of lists by name, price, rating
	 */
	public HashMap firstFive() throws InterruptedException {
		HashMap<String, List> map = new HashMap<>();
		List listPrice = new ArrayList();
		List listRating = new ArrayList();
		List listName = new ArrayList();
		List<WebElement> allLinks = driver.findElements(firstFive);

		for (int i = 0; i < 5; i++) {
			WebElement name = allLinks.get(i).findElement(By.xpath(
					".//a[contains(@class,'a-link-normal s-access-detail-page  s-color-twister-title-link a-text-normal')]"));
			System.out.println(name.getText());
			listName.add(name.getText());
			WebElement price = allLinks.get(i).findElement(By.xpath("./div/div[4]/div[1]/a/span[1]"));
			System.out.println(price.getAttribute("textContent"));
			listPrice.add(price.getAttribute("textContent").substring(1));
			WebElement rating = allLinks.get(i).findElement(By.xpath(".//*[@class='a-popover-trigger a-declarative']"));
			System.out.println(rating.getAttribute("textContent"));
			System.out.println("-------------------");
			listRating.add(rating.getAttribute("textContent"));
		}
		
		map.put("listName", listName);
		map.put("listPrice", listPrice);
		map.put("listRating", listRating);

		return map;
	}

	/**
	 * Get the name list from the HashMap then sort and print it.
	 * @param listToSort
	 */
	public void sortinglistName(HashMap listToSort) {
		List listName = (List) listToSort.get("listName");
		listName.sort(String.CASE_INSENSITIVE_ORDER);
		System.out.println(Arrays.toString(listName.toArray()));
	}

	/**
	 * Get the price list from the HashMap then sort and print it.
	 * Also, assert that prices are within range i.e between (20-100)
	 * and they are sorted in ascending order.
	 * @param listToSort
	 */
	public void sortinglistPrice(HashMap listToSort) {
		List listPrice = (List) listToSort.get("listPrice");
		Collections.sort(listPrice);
		System.out.println(Arrays.toString(listPrice.toArray()));
		double price;
		double price2;
		for (int i = 0; i < (listPrice.size() - 1); i++) {
			price = Double.parseDouble((String) listPrice.get(i));
			price2 = Double.parseDouble((String) listPrice.get(i + 1));
			Assert.assertTrue(price >= 20.00 && price <= 100.00, "Within Range");
			Assert.assertTrue(price < price2, "sorted price");
		}
	}

	/**
	 * Get the rating list from the HashMap then sort and print it.
	 * @param listToSort
	 */
	public void sortinglistRating(HashMap listToSort) {
		List listRating = (List) listToSort.get("listRating");
		listRating.sort(String.CASE_INSENSITIVE_ORDER);
		System.out.println(Arrays.toString(listRating.toArray()));

	}
}
