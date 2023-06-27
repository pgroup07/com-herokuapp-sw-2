package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class LoginTest extends BaseTest {
    String baseUrl = "http://the-internet.herokuapp.com/login";

    @Before
    public void setup() {
        openBrowser(baseUrl);
    }

    @Test
    public void userSholdLoginSuccessfullyWithValidCredentials() {
        // Enter “tomsmith” username
        driver.findElement(By.xpath("//input[@id='username']")).sendKeys("tomsmith");
        // Enter “SuperSecretPassword!” password
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("SuperSecretPassword!");
        // Click on ‘LOGIN’ button
        driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();

        String actualMessage = "Secure Area";
        String expectedMessage = driver.findElement(By.xpath("//h2")).getText();
        Assert.assertEquals("Error Message is not Displayed", expectedMessage, actualMessage);
    }

    @Test
    public void verifyTheUsernameErrorMessage() {
        //  Enter “tomsmith1” username
        driver.findElement(By.xpath("//input[@id='username']")).sendKeys("tomsmith1");
        // Enter “SuperSecretPassword!” password
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("SuperSecretPassword!");
        // Click on ‘LOGIN’ button
        driver.findElement(By.xpath("//button[@class='radius']")).click();
//
        String actualMessage = "Your username is invalid";
        String expectedMessage = driver.findElement(By.xpath("//div[@id='flash']")).getText();
        String[] exp = expectedMessage.split("!");
        Assert.assertEquals("Error Message is not Displayed", exp[0], actualMessage);

    }

    @Test
    public void verifyThePasswordErrorMessage() {
        //  Enter “tomsmith” username
        driver.findElement(By.xpath("//input[@id='username']")).sendKeys("tomsmith");
        // Enter “SuperSecretPassword!” password
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("SuperSecretPassword");
        // Click on ‘LOGIN’ button
        driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();

        String actualMessage = "Your password is invalid";
        String expectedMessage = driver.findElement(By.xpath("//div[@class='flash error']")).getText();
        String[] exp = expectedMessage.split("!");
        Assert.assertEquals("Error Message is not Displayed", exp[0], actualMessage);
    }

    @After
    public void tearDown() {
        closerBrowser();
    }

}
