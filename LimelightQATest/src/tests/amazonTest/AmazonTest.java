package tests.amazonTest;

import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchResultsPage;
import selenium.DriverSetup;

public class AmazonTest {
	WebDriver driver;

	@BeforeClass(alwaysRun = true)
	public void setupClass() {

	}

	@BeforeMethod(alwaysRun = true)
	public void setupTest() {

	}

	@Parameters()
	@Test(description = "Test Description")
	public void groupSetup() throws Exception {
		driver = DriverSetup.setupDriver(DriverSetup.Browser.Chrome, "chromedriver");
		driver.get("https://www.amazon.com");
		HomePage hp = new HomePage(driver);
		hp.searchField().sendKeys("ipad air 2 case");
		hp.searchClick().click();
		SearchResultsPage srp = new SearchResultsPage(driver);
		srp.plasticCheckbox().click();
		Thread.sleep(2000);
		srp.priceMinRange().sendKeys("20");
		Thread.sleep(2000);
		srp.priceMaxRange().sendKeys("100");
		Thread.sleep(4000);
		srp.priceRangeGo().click();
	    HashMap firstFiveItems = srp.firstFive();
		srp.sortinglistName(firstFiveItems);
		srp.sortinglistPrice(firstFiveItems);
		srp.sortinglistRating(firstFiveItems);
		int x = 1,y= 3, r;
		r= x+y;
		system.out.println(r);
	
		

	}

	@AfterMethod(alwaysRun = true)
	public void tearDownTest() {

	}

	@AfterClass(alwaysRun = true)
	public void tearDownClass() {
		driver.quit();
	}
}
