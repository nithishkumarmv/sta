package exc8;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class exc8lab {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "Z:/ProgramFiles/chromedriver-win64/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.amazon.in");
    }

    @Test
    public void searchForProduct() {
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("iPhone 15");

        WebElement searchButton = driver.findElement(By.id("nav-search-submit-button"));
        searchButton.click();

        WebElement firstResultPrice = driver.findElement(By.xpath(
            "(//span[contains(@class, 'a-price-whole')])[1]"));
        System.out.println("Price of first result: " + firstResultPrice.getText());
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
