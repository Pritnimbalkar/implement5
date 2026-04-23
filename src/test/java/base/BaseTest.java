package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {

    protected static WebDriver driver;

    public static WebDriver initializeDriver() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
// package base;

// import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.remote.RemoteWebDriver;
// import org.openqa.selenium.chrome.ChromeOptions;

// import java.net.URL;

// public class BaseTest {

//     protected static WebDriver driver;

//     public static WebDriver initializeDriver() {

//         try {
//             ChromeOptions options = new ChromeOptions();

//             driver = new RemoteWebDriver(
//                     new URL("http://localhost:4444"),
//                     options
//             );

//         } catch (Exception e) {
//             e.printStackTrace();
//         }

//         return driver;
//     }

//     public static void quitDriver() {
//         if (driver != null) {
//             driver.quit();
//         }
//     }
// }
