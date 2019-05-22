import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import java.util.concurrent.TimeUnit;
import static org.junit.gen5.api.Assertions.assertEquals;

public class TestPrueba {

    private static WebDriver driver;

    // Annotation that indicates JUnit to execute this function before all the others
    // in this class. It initiate the webdriver with the necessary parameters
    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Carlos_ADM/AppData/Roaming/npm/node_modules/chromedriver/lib/chromedriver/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:4503/content/we-retail/us/en.html");

        // Implicit wait, the webdriver will wait for all elements and pages to load 1 second maximum
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
    }

    // Annotation that indicates JUnit that this function is a test. It test if the
    // title of the page is 'English'
    @Test
    public void test_tittle() {
        // Should see: "English"
        System.out.println("Page title is: " + driver.getTitle());
        assertEquals("English", driver.getTitle());
    }

    // Annotation that indicates JUnit that this function is a test. It test the
    // search button, search field and search close button.
    @Test
    public void test_search() {
        // We came back to the original page because the tests that are executed
        // before could change the page we are in
        driver.get("http://localhost:4503/content/we-retail/us/en.html");

        // Look for the search button using CSS Selector
        WebElement searchButton = driver.findElement(By.cssSelector(".navbar-right-outside [data-toggle]"));

        // Instantiated the object that will allow us to execute JavaScript code in the webdriver
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Call a function that will allow us to execute a function in JavaScript,
        // the function between the quotations marks scroll through the page to the top
        // of it. That is because the webdriver control the browser simulating a user,
        // so you can search for any element but you only can interact with the elements
        // that are already enabled, displayed or visible.
        js.executeScript("window.scrollTo(0, -document.body.scrollHeigth);");

        // Now that the webdriver is on the top of the page, it can see the button
        // and can click it
        searchButton.click();

        // Search for the text field for testing if its works and click on it
        WebElement searchField = driver.findElement(By.className("cmp-search__input"));
        searchField.click();

        // It writes in the text field 'CAMISA', removes it and then writes 'GUANTES'
        searchField.sendKeys("CAMISA");
        searchField.clear();
        searchField.sendKeys("GUANTES");

        // Search 'GUANTES' in the text fielc
        searchField.submit();

        // It returns to the home page after the search result
        driver.navigate().back();

        // It look for the search button again and click it
        driver.findElement(By.className("navbar-right-outside")).click();

        // Search for the close button in the search field and click it
        driver.findElement(By.className("modal-header")).findElement(By.className("close")).click();
    }

    // Annotation that indicates JUnit that this function is a test
    @Test
    public void test_carousel() {
        driver.get("http://localhost:4503/content/we-retail/us/en.html");

        // Search for the carousel in the page
        WebElement carousel = driver.findElement(By.className("cmp-carousel__content"));

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Call a function that will allow us to execute a function in JavaScript,
        // the function between the quotations marks scroll through the page to the
        // carousel that we searched before
        js.executeScript("arguments[0].scrollIntoView();", carousel);

        // For click in the carousel's buttons we have to set the focus in the carousel
        // because their are not displayed or visible. For do this we use the class
        // Actions which purpose is to emulate the actions of a user
        Actions build = new Actions(driver);

        // In this case it only move to the carousel, construct the action and then execute it
        build.moveToElement(carousel).build().perform();

        // Click the right button of the carousel three times for test if it works and if it
        // display all the images
        driver.findElement(By.className("cmp-carousel__action--next")).click();
        driver.findElement(By.className("cmp-carousel__action--next")).click();
        driver.findElement(By.className("cmp-carousel__action--next")).click();
    }

    // Annotation that indicates JUnit that this function is a test
    @Test
    public void test_product() {
        driver.findElement(By.linkText("Community")).click();
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}