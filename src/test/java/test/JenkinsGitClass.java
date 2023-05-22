package test;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JenkinsGitClass {
	
	public static void main(String[] args) throws InterruptedException {
		String url = "https://testautomationpractice.blogspot.com/";

		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
		Thread.sleep(3000);
		// maximize the browser
		driver.manage().window().maximize();		
		driver.get(url);
		
		//Step 1. Retrieve the current window alphanumberic id and store this in an object
		// NOTE: the current window will be considered as the parentWindow 
		
		String parentWindow = driver.getWindowHandle();
		
		System.out.println("Parent WIndow ID: " + parentWindow);
 

		WebElement searchField = driver.findElement(By.id("Wikipedia1_wikipedia-search-input"));
		WebElement searchIcon = driver.findElement(By.xpath("//input[@class='wikipedia-search-button']"));

		//Step 2. click on link to open new tab/window
		
		//Nike
		searchField.sendKeys("Nike, Inc.");
		searchIcon.click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Nike, Inc.")).click();
		Thread.sleep(1000);		
		
		//Puma
		searchField.clear();
		searchField.sendKeys("Puma (brand)");
		searchIcon.click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Puma (brand)")).click();
		Thread.sleep(1000);		
		
		//adidas
		searchField.clear();
		searchField.sendKeys("Adidas");
		searchIcon.click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Adidas")).click();
		Thread.sleep(1000);		
		
		
		//Step 3. Once new tabs are opened, now it is time to retrieve all the window ids that are opened by the SELENIUm 
		// ( when we retrieve all the windows it will return the PARENT WINDOW ID and the child windowIDS)
		
		Set<String> windowSet = driver.getWindowHandles();
		System.out.println("all the window IDS: ");
		System.out.println(windowSet);
		
		
		//Step 4. Once we have all the window IDS, then we can iterate through the set of windows 
		
		for( String windowID: windowSet) {
			
			System.out.println("ID of each window Selenium Assigned: " + windowID);
			
			//Step 5. Inside loop body, each iteartion we will have different window ID, we need to use that id and switch to that window
			driver.switchTo().window(windowID);
			
			/**
			 * NOTE: Keep in mind, set interface does not keep the insertion order & we donot konw which window/tab it switched
			 *  To handles this, we need to retrieve the page title and check if it is what we are lookign for 
			 */
			
			//Step 6. Retrieve the page title 
			String pageTitle = driver.getTitle();
			
			System.out.println("Page Title: " + pageTitle);
			
			if(pageTitle.equals("Nike, Inc. - Wikipedia")) {
				System.out.println("We are inside Nike Page");
				
				driver.findElement(By.xpath("//a[@href='https://www.nike.com']")).click();
				Thread.sleep(1000);
				
				String tempPageTitle = driver.getTitle();
				System.out.println(tempPageTitle);
				
				//once we are done interacting with the child window/tab --> then CLOSE the window
				
				driver.close();
											
			}else if ( pageTitle.equals("Puma (brand) - Wikipedia")) {
				System.out.println("We are inside Puma Page");
				driver.findElement(By.xpath("//a[@href='https://eu.puma.com/']")).click();
				Thread.sleep(1000);
				String tempPageTitle = driver.getTitle();
				System.out.println(tempPageTitle);
				driver.close();
			} else if ( pageTitle.equals("Adidas - Wikipedia")) {
				System.out.println("We are inside Adidas Page");
				driver.findElement(By.xpath("//a[@href='https://www.adidas.com/']")).click();
				Thread.sleep(1000);
				String tempPageTitle = driver.getTitle();
				System.out.println(tempPageTitle);
				driver.close();
			}  
			Thread.sleep(2000);
		}
		//Step 7. Outside the loop --> we need to switch back to the parent window
		
		driver.switchTo().window(parentWindow);
		
		System.out.println(driver.getTitle());
		
		Thread.sleep(3000);
		driver.quit();
	}
	
	
	
	
	

}


