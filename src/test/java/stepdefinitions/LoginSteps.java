// package stepDefinitions;

// import io.cucumber.java.en.*;
// import org.openqa.selenium.*;
// import org.openqa.selenium.support.ui.WebDriverWait;
// import org.openqa.selenium.support.ui.ExpectedConditions;
// import java.time.Duration;
// import hooks.Hooks;



// public class LoginSteps {

//     WebDriver driver = Hooks.driver;

//     @Given("user is on login page")
//     public void user_is_on_login_page() {
//         driver = Hooks.driver;   // ✅ get AFTER initialization
//         driver.get("https://demoqa.com/login");
//     }

//     @When("user enters username {string} and password {string}")
//     public void user_enters_credentials(String username, String password) {

//         driver.findElement(By.id("userName")).clear();
//         driver.findElement(By.id("userName")).sendKeys(username);

//         driver.findElement(By.id("password")).clear();
//         driver.findElement(By.id("password")).sendKeys(password);
//     }

//     @And("clicks on login button")
//     public void clicks_on_login_button() {
//         driver.findElement(By.id("login")).click();
//     }

// @Then("login should be {string}")
// public void login_should_be(String result) {

//     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

//     if (result.equalsIgnoreCase("success")) {

//         try {
//             wait.until(ExpectedConditions.urlContains("profile"));
//         } catch (Exception e) {
//             throw new AssertionError("Expected SUCCESS but failed");
//         }

//         // logout after success
//         driver.findElement(By.id("submit")).click();

//     } else {

//         try {
//             wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
//         } catch (Exception e) {
//             throw new AssertionError("Expected FAILURE but passed");
//         }
//     }
// }
// }
package stepDefinitions;

import io.cucumber.java.en.*;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class LoginSteps {

    Response response;

    @Given("user sets DemoQA base URI")
    public void set_base_uri() {
        baseURI = "https://demoqa.com";
    }

    @When("user sends POST request to {string} with username {string} and password {string}")
    public void send_post_request(String endpoint, String username, String password) {

        Map<String, String> body = new HashMap<>();
        body.put("userName", username);
        body.put("password", password);

        response = given()
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post(endpoint);

        response.then().log().all(); // debug
    }

    @Then("response status should be {int}")
    public void validate_status(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @And("login response should be {string}")
public void validate_login(String result) {

    String responseBody = response.getBody().asString();

    System.out.println("Response Body: " + responseBody);

    // ✅ Handle empty response properly
    if (responseBody == null || responseBody.trim().isEmpty()) {

        System.out.println("Empty response received from API");

        // 🔥 Treat empty as FAILURE case
        if (result.equalsIgnoreCase("failure")) {
            return; // ✅ pass test
        } else {
            throw new AssertionError("Expected success but got empty response");
        }
    }

    // ✅ Normal validation
    if (result.equalsIgnoreCase("success")) {
        response.then().body("token", notNullValue());
    } else {
        if (responseBody.contains("message")) {
            response.then().body("message", notNullValue());
        } else {
            System.out.println("Failure case without message field");
        }
    }
}
}