package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        //Try with selenium
        tryLogin(driver);
        tryLoginWithWrongPassword(driver);
        tryLoginWithUserThatDoesntExist(driver);
        tryToCreateANewUser(driver);
        tryToCreateANewUserAndLogOutAfter(driver);
        
        driver.quit();
    }
    
    private static void tryLogin(WebDriver driver) {
        driver.get("http://localhost:4567");
        
        sleep(2);
        
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        
        sleep(2);
        element.submit();

        sleep(3);
    }
    
    private static void tryLoginWithWrongPassword(WebDriver driver) {
        driver.get("http://localhost:4567");
        
        sleep(1);
        
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(1);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("wrooong!");
        element = driver.findElement(By.name("login"));
        
        sleep(2);
        element.submit();

        sleep(2);
    }
    
    private static void tryLoginWithUserThatDoesntExist(WebDriver driver) {
        driver.get("http://localhost:4567");
        
        sleep(1);
        
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(1);

        element = driver.findElement(By.name("username"));
        element.sendKeys("Not a registered user");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        
        sleep(2);
        element.submit();

        sleep(2);
    }
    
    private static void tryToCreateANewUser(WebDriver driver) {
        driver.get("http://localhost:4567");
        
        sleep(1);
        
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        sleep(1);

        element = driver.findElement(By.name("username"));
        element.sendKeys("superuser");
        element = driver.findElement(By.name("password"));
        element.sendKeys("supersecret1");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("supersecret1");
        element = driver.findElement(By.name("signup"));
        
        sleep(2);
        element.submit();

        sleep(2);
    }
    
    private static void tryToCreateANewUserAndLogOutAfter(WebDriver driver) {
        driver.get("http://localhost:4567");
        
        sleep(1);
        
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        sleep(1);

        element = driver.findElement(By.name("username"));
        element.sendKeys("newuser");
        element = driver.findElement(By.name("password"));
        element.sendKeys("secret1word");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("secret1word");
        element = driver.findElement(By.name("signup"));
        
        sleep(1);
        element.submit();

        sleep(1);
        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        
        sleep(1);
        element = driver.findElement(By.linkText("logout"));
        element.click();
        sleep(1);
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
