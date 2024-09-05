package com.ecommerce.steps;

import com.ecommerce.pages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class LoginSteps {
    WebDriver driver;
    LoginPage loginPage;
    Properties props = new Properties();

    @Before
    public void setUp() throws IOException {
        FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
        props.load(fis);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
    }

    @Given("the user is on the login page")
    public void user_on_login_page() {
        driver.get(props.getProperty("baseUrl") + "/login");
    }

    @When("the user enters valid username and password")
    public void enter_valid_credentials() {
        loginPage.enterUsername(props.getProperty("validUsername"));
        loginPage.enterPassword(props.getProperty("validPassword"));
        loginPage.clickLogin();
    }

    @When("the user enters invalid username and password")
    public void enter_invalid_credentials() {
        loginPage.enterUsername(props.getProperty("invalidUsername"));
        loginPage.enterPassword(props.getProperty("invalidPassword"));
        loginPage.clickLogin();
    }

    @Then("the user should be redirected to the dashboard")
    public void verify_dashboard_redirection(){
        String expectedUrl = "https://ecommerce.com/login";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl, actualUrl);
    }
    @Then("the user should see an error message")
    public void verify_error_message() {
        String expectedMessage = "Invalid credentials";
        String actualMessage = loginPage.getErrorMessage();
        Assert.assertEquals(expectedMessage, actualMessage);
    }
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

