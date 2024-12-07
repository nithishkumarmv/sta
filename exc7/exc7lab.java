package exc7;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class exc7lab {
    public static void main(String[] args) {
        // Set the path to your chromedriver executable
        System.setProperty("webdriver.chrome.driver", "Z:/ProgramFiles/chromedriver-win64/chromedriver-win64/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        try {
            // Open the Amazon India website
            driver.get("http://www.amazon.in/");

            
            // Wait for the search button to be clickable
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-search-submit-button")));

            
            // Enter the search keyword
            String keyword = "iphone 15";
            WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
            searchBox.sendKeys(keyword);

            
            // Click the search button
            WebElement searchButton = driver.findElement(By.id("nav-search-submit-button"));
            searchButton.click();

            
            // Get the price of the first search result
            WebElement firstResultPrice = driver.findElement(By.xpath("(//span[@class='a-price-whole'])[1]"));
            System.out.println("Price of the iPhone 15: " + firstResultPrice.getText());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the driver
            driver.quit();
        }
    }
}

