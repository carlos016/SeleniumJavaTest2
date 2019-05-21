import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
//import org.openqa.selenium.support.ui.ExpectedCondition;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import java.util.concurrent.TimeUnit;
import static org.junit.gen5.api.Assertions.assertEquals;

public class TestPrueba {

    private static WebDriver driver;

    @BeforeClass
    public static void setUp (){
        System.setProperty("webdriver.chrome.driver","C:/Users/Carlos_ADM/AppData/Roaming/npm/node_modules/chromedriver/lib/chromedriver/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:4503/content/we-retail/us/en.html");
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
    }

    @Test
    public void test_tittle (){
        //driver.navigate().refresh();
        // Should see: "cheese! - Google Search"
        System.out.println("Page title is: " + driver.getTitle());
        assertEquals("English", driver.getTitle());
    }

    @Test
    public void test_search ()/* throws InterruptedException*/{
        driver.get("http://localhost:4503/content/we-retail/us/en.html");
        //driver.navigate().refresh();
        WebElement searchButton = driver.findElement(By.cssSelector(".navbar-right-outside [data-toggle]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //Thread thread = new Thread("hola");
        //thread.sleep(3000);

        //js.executeScript("arguments[0].scrollIntoView();", searchButton);
        js.executeScript("window.scrollTo(0, -document.body.scrollHeigth);");

        //WebElement searchButton = driver.findElement(By.className("navbar-right-outside"));

        //thread.sleep(3000);
        searchButton.click();
        //thread.sleep(3000);
        WebElement searchField = driver.findElement(By.className("cmp-search__input"));
        searchField.click();
        //thread.sleep(3000);
        searchField.sendKeys("CAMISA");
        searchField.clear();
        searchField.sendKeys("GUANTES");
        //thread.sleep(3000);
        searchField.submit();
        //thread.sleep(3000);
        driver.navigate().back();
        //thread.sleep(3000);
        driver.findElement(By.className("navbar-right-outside")).click();
        //thread.sleep(3000);
        driver.findElement(By.className("modal-header")).findElement(By.className("close")).click();
        //thread.sleep(3000);

        //driver.findElement(By.className("cmp-carousel__action--next")).click();
        //driver.findElement(By.cssSelector("[data-cmp-hook-carousel='next']")).click();
        //driver.findElement(By.xpath("/html/body/div[@class='container']/div//div[@class='cmp-carousel']//div[@class='cmp-carousel__actions']/button[2]")).click();

        //className("cmp-search__input"));
        /*(new WebDriverWait(driver, 100
        )).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                WebElement searchField = d.findElement(By.className("close"));
                return true;
            }
        });*/
    }

    @Test
    public void test_carousel () /*throws InterruptedException*/ {
        driver.get("http://localhost:4503/content/we-retail/us/en.html");
        WebElement carousel = driver.findElement(By.className("cmp-carousel__content"));
        //Thread thread = new Thread("hola");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //thread.sleep(3000);
        js.executeScript("arguments[0].scrollIntoView();", carousel);
        //thread.sleep(3000);

        Actions build = new Actions(driver);
        build.moveToElement(carousel).build().perform();
        //thread.sleep(3000);
        driver.findElement(By.className("cmp-carousel__action--next")).click();
        //thread.sleep(3000);
        driver.findElement(By.className("cmp-carousel__action--next")).click();
        //thread.sleep(3000);
        driver.findElement(By.className("cmp-carousel__action--next")).click();
        //thread.sleep(3000);
    }

    @Test
    public void test_product () /*throws InterruptedException*/ {
        //driver.findElement(By.cssSelector("[class] [class='we-productgrid aem-GridColumn aem-GridColumn--default--12']:nth-of-type(7) .foundation-list-item:nth-of-type(1) .we-ProductsGrid-item-link")).click();
        //Thread thread = new Thread("hola");
        //thread.sleep(3000);
        driver.findElement(By.linkText("Community")).click();
        //thread.sleep(3000);
    }

    @AfterClass
    public static void tearDown (){
        driver.quit();
    }

}
