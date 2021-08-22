package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

/**
 Utility class that follows the Singleton pattern to initialize and to quit the WebDriver instance
 */
public class Driver {


    private static ThreadLocal<WebDriver> drivers = new ThreadLocal<>(); // driver pool

    private Driver() {
    }


    public static synchronized WebDriver getDriver() {

        return getDriver(null);
    }


    public static synchronized WebDriver getDriver(String browser) {

        if (drivers.get() == null) {

            if (browser == null) {
                browser = ConfigReader.getProperty("browser");
            }

            switch (browser) {

                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    drivers.set(new ChromeDriver());
                    break;
                case "headlessChrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--headless");
                    chromeOptions.addArguments("--disable-gpu");
                    WebDriverManager.chromedriver().setup();
                    drivers.set(new ChromeDriver(chromeOptions));
                    break;
                case "headlessFirefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("--headless");
                    WebDriverManager.firefoxdriver().setup();
                    drivers.set(new FirefoxDriver(firefoxOptions));
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    drivers.set(new FirefoxDriver());
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    drivers.set(new EdgeDriver());
                    break;
                case "ieexplorer":
                    WebDriverManager.iedriver().setup();
                    drivers.set(new InternetExplorerDriver());
                    break;
                case "safari":
                    drivers.set(new SafariDriver());
                    break;
                default:
                    System.out.println("Invalid browser");
                    break;
            }
        }
        return drivers.get();
    }

    public static synchronized void quitDriver() {
        if (drivers.get() != null) {
            drivers.get().quit();
            drivers.remove();
        }
    }
}