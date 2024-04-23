package maven.PruebaSelenium;

import java.time.Duration;
import java.util.Random;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BingSearch {
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
		//flujo del test
		int numberOfSearches = 30;
		String searchText = generateRandomString();
		clickOnLogin();
		for (int i = 1; i<=numberOfSearches; i++) {
			 searchText = generateRandomString();
			 performSearch (searchText);
			 clickOnLogo();
		}
    }
	
    private void performSearch(String searchText) {
    	try {
    		Thread.sleep(2000); // Esperar 2 segundos antes de hacer clic en el logo
    	} catch (InterruptedException e) {
    	     e.printStackTrace();
    	}
        WebElement searchbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
        	searchbox.clear();
        	searchbox.sendKeys(searchText);
        	searchbox.submit();
        
    }  
    private void clickOnLogo() {
    	try {
    		Thread.sleep(2000); // Esperar 2 segundos antes de hacer clic en el logo
    	} catch (InterruptedException e) {
    	     e.printStackTrace();
    	}
    	
        WebElement logo = wait.until(ExpectedConditions.elementToBeClickable(By.className("b_logoArea")));
        	logo.click();
    } 
    private void clickOnLogin() {
    	// Flujo del login
        WebElement login = wait.until(ExpectedConditions.elementToBeClickable(By.id("id_s")));
        	login.click();
        WebElement sublogin = wait.until(ExpectedConditions.elementToBeClickable(By.className("id_text_signin")));
        	sublogin.click();
        
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("i0116")));
        	String email = Credentials.getEmail();
        	emailInput.sendKeys(email); 
        	emailInput.submit();
        
        WebElement passInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("i0118")));
        	String pass = Credentials.getPass();
        	passInput.sendKeys(pass); 
        	passInput.submit();
        
        
        WebElement declineButtom = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("declineButton")));
        	declineButtom.submit();
        
        WebElement cookiesPopUp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("bnp_container")));
        
        if (cookiesPopUp.isDisplayed() ){
        	WebElement acceptButton = cookiesPopUp.findElement(By.id("bnp_btn_accept"));
            	acceptButton.click();
        }
        
        
    } 
    private String generateRandomString() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        
        //Generar una cadena aleatoria de longitud 7
        for (int i = 0; i < 7; i++) {
            int index = random.nextInt(alphabet.length());
            sb.append(alphabet.charAt(index));
        	} 
        return sb.toString();
       }
    
	@AfterEach
	public void tearDown() {
		driver.quit();
		
	}

}
