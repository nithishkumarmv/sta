import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.NoSuchElementException;

import java.util.List;

public class clientserver {
    public static void main(String[] args) throws InterruptedException {
        // Set up ChromeDriver (Ensure the correct path to chromedriver is set)
        System.setProperty("webdriver.chrome.driver", "Z:/ProgramFiles/chromedriver-win64/chromedriver-win64/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        
        try {
            // Open the local HTML file
            String filePath = "file:///Z:/summa/index.html"; // Use 'file:///' for local file paths
            driver.get(filePath);
            Thread.sleep(3000); // Wait for the page to load

            // Find all products on the page
            List<WebElement> products = driver.findElements(By.className("product"));
            System.out.println("Total products found: " + products.size());

            // Loop through each product
            for (int i = 0; i < products.size(); i++) {
                WebElement product = products.get(i);
                int productNumber = i + 1;
                System.out.println("\nChecking Product " + productNumber + "...");

                // Check for product name
                try {
                    WebElement productName = product.findElement(By.className("product-name"));
                    if (productName.getText().trim().isEmpty()) {
                        System.out.println("Product " + productNumber + " Failed: Name is missing.");
                    } else {
                        System.out.println("Product " + productNumber + " Passed: Name is present.");
                    }
                } catch (NoSuchElementException e) {
                    System.out.println("Product " + productNumber + " Failed: Could not find the product name.");
                }

                // Check for product price
                try {
                    WebElement productPrice = product.findElement(By.className("product-price"));
                    if (productPrice.getText().trim().isEmpty()) {
                        System.out.println("Product " + productNumber + " Failed: Price is missing.");
                        if (productNumber == 3) {
                            System.out.println("Product " + productNumber + ": Custom alert for missing price.");
                            // Trigger a custom alert for the third product
                            JavascriptExecutor js = (JavascriptExecutor) driver;
                            js.executeScript("alert('Cannot proceed to buy because price not found');");
                            Thread.sleep(2000); // Wait for the alert to display
                            driver.switchTo().alert().accept(); // Close the alert
                        }
                    } else {
                        System.out.println("Product " + productNumber + " Passed: Price is present.");
                    }
                } catch (NoSuchElementException e) {
                    System.out.println("Product " + productNumber + " Failed: Could not find the product price.");
                }

                // Check for broken image
                try {
                    WebElement productImg = product.findElement(By.className("product-img"));
                    String naturalWidth = productImg.getAttribute("naturalWidth");
                    if (naturalWidth.equals("0")) {
                        System.out.println("Product " + productNumber + " Failed: Image is broken.");
                    } else {
                        System.out.println("Product " + productNumber + " Passed: Image is present.");
                    }
                } catch (NoSuchElementException e) {
                    System.out.println("Product " + productNumber + " Failed: Could not find the product image.");
                }
            }

            // Keep the browser open for manual inspection
            Thread.sleep(20000); // Adjust this time as needed
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
