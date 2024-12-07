package exc6;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class exc6lab {
    public static void main(String[] args) {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "Z:/ProgramFiles/chromedriver-win64/chromedriver-win64/chromedriver.exe"); // Change this to your chromedriver path

        // Initialize the Chrome driver
        WebDriver driver = new ChromeDriver();

        try {
            // Step 1: Launch the browser and navigate to Amazon's website
            driver.get("https://www.amazon.in");

            // Step 2: Measure page load time
            long startTime = System.currentTimeMillis();

            // Wait until the search box is clickable
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.elementToBeClickable(By.id("twotabsearchtextbox")));

            // Enter a search keyword and submit the search
            String keyword = "smart watch";
            driver.findElement(By.id("twotabsearchtextbox")).sendKeys(keyword);
            driver.findElement(By.id("nav-search-submit-button")).click();

            // Page load completed
            long endTime = System.currentTimeMillis();

            // Calculate the page load time in milliseconds
            long pageLoadTime = endTime - startTime;

            System.out.printf("Page Load Time: %.2f milliseconds%n", (double) pageLoadTime);

        } finally {
            // Step 3: Close the browser
            driver.quit();
        }
    }
}
