package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import hooks.Hooks;



public class LoginSteps {

    WebDriver driver = Hooks.driver;

    @Given("user is on login page")
    public void user_is_on_login_page() {
        driver = Hooks.driver;   // ✅ get AFTER initialization
        driver.get("https://demoqa.com/login");
    }

    @When("user enters username {string} and password {string}")
    public void user_enters_credentials(String username, String password) {

        driver.findElement(By.id("userName")).clear();
        driver.findElement(By.id("userName")).sendKeys(username);

        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @And("clicks on login button")
    public void clicks_on_login_button() {
        driver.findElement(By.id("login")).click();
    }

@Then("login should be {string}")
public void login_should_be(String result) {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    if (result.equalsIgnoreCase("success")) {

        try {
            wait.until(ExpectedConditions.urlContains("profile"));
        } catch (Exception e) {
            throw new AssertionError("Expected SUCCESS but failed");
        }

        // logout after success
        driver.findElement(By.id("submit")).click();

    } else {

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        } catch (Exception e) {
            throw new AssertionError("Expected FAILURE but passed");
        }
    }
}
}
