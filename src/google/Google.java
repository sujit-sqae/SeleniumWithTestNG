package google;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Google {
	private WebDriver driver;

    @BeforeClass
    public void setUp() {
    	// Set the path to the GeckoDriver executable
	 	String projectPath = System.getProperty("user.dir");
        System.setProperty("webdriver.gecko.driver", projectPath+"/gekodriver/geckodriver");

        // Launch Firefox browser
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        // Quit the browser
        driver.quit();
    }

    @Test
    public void testGooglePageTitle() {
        // Open Google.com
        driver.get("https://www.google.com");

        // Verify the page title
        String expectedTitle = "Google";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Page title mismatch");
    }

	@Test
    public void testGoogleSearch() throws InterruptedException {
        // Open Google.com
        driver.get("https://www.google.com");

        // Perform search
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("TestNG");
        searchBox.submit();

        Thread.sleep(10000);
        
        // Verify the current page title
        String expectedTitle = "TestNG - Google Search";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Page title mismatch on Images page");
        
        // Verify search results
        WebElement searchResult = driver.findElement(By.cssSelector("#search .g"));
        Assert.assertTrue(searchResult.isDisplayed(), "Search result not found");
    }

	@Test
    	public void testGoogleImagesLink() {
        // Open Google.com
        driver.get("https://www.google.com");

        // Click on "Images" link
        WebElement imagesLink = driver.findElement(By.linkText("Images"));
        imagesLink.click();

        // Verify the current page title
        String expectedTitle = "Google Images";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Page title mismatch on Images page");
    }

}