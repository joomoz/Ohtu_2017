package ohtu;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Stepdefs {

    WebDriver driver = new ChromeDriver();
    String baseUrl = "http://localhost:4567";

    @Given("^login is selected$")
    public void login_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
    }

    @Given("^new user is selected$")
    public void new_user_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
    }
    
    @Given("^user with username \"([^\"]*)\" with password \"([^\"]*)\" is succesfully created")
    public void user_with_username_and_password_is_created(String username, String password) throws Throwable {
        new_user_selected();
        username_password_and_password_confirmation_are_given(username, password, password);
    } 
    
    @Given("^user with username \"([^\"]*)\" and password \"([^\"]*)\" is unsuccesfully created")
    public void user_with_username_and_password_is_unsuccesfully_created(String username, String password) throws Throwable {
        new_user_selected();
        username_password_and_password_confirmation_are_given(username, password, password);
    } 
    
    @When("^username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_and_password_are_given(String username, String password) throws Throwable {
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();
    }

    //Bad style!
    @Then("^system will respond \"([^\"]*)\"$")
    public void system_will_respond(String pageContent) throws Throwable {
        assertTrue(driver.getPageSource().contains(pageContent));
    }

    @When("^correct username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_correct_and_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^correct username \"([^\"]*)\" and incorrect password \"([^\"]*)\" are given$")
    public void username_and_incorrect_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^incorrect username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_incorrect_and_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^username \"([^\"]*)\" password \"([^\"]*)\" and password confirmation \"([^\"]*)\" are given$")
    public void username_password_and_password_confirmation_are_given(String username, String password, String passwordConfirmation) throws Throwable {
        signInWith(username, password, passwordConfirmation);
    }

    @When("^incorrect username \"([^\"]*)\" password \"([^\"]*)\" and password confirmation \"([^\"]*)\" are given$")
    public void username_incorrect_password_and_password_confirmation_are_given(String username, String password, String passwordConfirmation) throws Throwable {
        signInWith(username, password, passwordConfirmation);
    }

    @When("^username \"([^\"]*)\" incorrect password \"([^\"]*)\" and password confirmation \"([^\"]*)\" are given$")
    public void username_password_incorrect_and_password_confirmation_are_given(String username, String password, String passwordConfirmation) throws Throwable {
        signInWith(username, password, passwordConfirmation);
    }
    
    @When("^username \"([^\"]*)\" correct password \"([^\"]*)\" and incorrect password confirmation \"([^\"]*)\" are given$")
    public void username_correct_password_and_incorrect_password_confirmation_are_given(String username, String password, String passwordConfirmation) throws Throwable {
        signInWith(username, password, passwordConfirmation);
    }    

    @Then("^user is logged in$")
    public void user_is_logged_in() throws Throwable {
        pageHasContent("Ohtu Application main page");
    }

    @Then("^user is not logged in and error message is given$")
    public void user_is_not_logged_in_and_error_message_is_given() throws Throwable {
        //try{ Thread.sleep(50000); } catch(Exception e){}  // suoritus pysähtyy 50 sekunniksi
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }

    @Then("^user is signed in$")
    public void user_is_signed_in() throws Throwable {
        //try{ Thread.sleep(50000); } catch(Exception e){}
        pageHasContent("Welcome to Ohtu Application!");
    }

    @Then("^user is not created and error \"([^\"]*)\" is reported$")
    public void user_is_not_created_and_error_message_is_reported(String error) throws Throwable {
        //try{ Thread.sleep(5000); } catch(Exception e){}
        pageHasContent(error);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    /* helper methods */
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }

    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();
    }

    private void signInWith(String username, String password, String passwordConfirmation) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(passwordConfirmation);
        element = driver.findElement(By.name("signup"));
        //try{ Thread.sleep(5000); } catch(Exception e){}
        element.submit();
    }
}
