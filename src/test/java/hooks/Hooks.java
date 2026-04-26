package hooks;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.BeforeAll;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import utils.ExcelReader;  // your class

public class Hooks {

    public static WebDriver driver;

    // 🔥 RUN ONCE → generate feature file
    @BeforeAll
    public static void generateFeature() throws Exception {
        ExcelReader.generateFeatureFromExcel();
        System.out.println("Feature file generated from Excel");
    }

    // 🔹 Only UI tests
    @Before("@ui")
    public void setUp() {
        if (driver == null) {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
    }

    @After("@ui")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}