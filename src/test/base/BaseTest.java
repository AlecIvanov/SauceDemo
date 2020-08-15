package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    // this class runs our test
    private static final ThreadLocal<WebDriver> drivers = new ThreadLocal<>(); //opredelyaet kakoi driver k kakomu
      private static String propertyPath = "src/main/resources/config/configuration.properties";                                                                        // testu  otnositsya
    @BeforeMethod
    public void setUp(){
        initializeDriver(ConfigReader.readProperty("browser", propertyPath));
        getDriver().get(ConfigReader.readProperty("url", propertyPath));
    }
    @AfterMethod
    public void tearDown(){
        getDriver().quit();
    }


    private void initializeDriver(String browser){
        WebDriver driver = null;

        switch (browser){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                System.out.println("Invalid browser type");
        }
        drivers.set(driver);// dobavlyaem eti drivery v colletions of drivers ThreadLocal<WebDriver> drivers
        getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    public  WebDriver getDriver(){
        WebDriver driver = drivers.get(); // poluchenie drivera iz collections
        if(driver == null){
            System.out.println("driver object was null");
        }
        return driver;
    }

}