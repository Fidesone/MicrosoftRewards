package maven.PruebaSelenium;

import java.time.Duration;
import java.util.concurrent.TimeUnit;



import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSearch {

	private WebDriver driver;
	private WebDriverWait wait;	
	@BeforeEach
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Dev\\Chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.get("https://www.bing.com/");
	}
	
	@Test 
    public void testBingPage() {
        performSearch("hola");
        clickOnLogo();
        performSearch("Adios");
    }
	
    private void performSearch(String searchText) {
        WebElement searchbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
        searchbox.clear();
        searchbox.sendKeys(searchText);
        searchbox.submit();
    }
    
    private void clickOnLogo() {
        WebElement logo = wait.until(ExpectedConditions.elementToBeClickable(By.className("b_logoArea")));
        logo.click();
    }
	
	@AfterEach
	public void tearDown() {
		driver.quit();
		
	}
}
