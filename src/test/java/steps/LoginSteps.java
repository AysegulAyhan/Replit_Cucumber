package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import utils.BaseDriver;

public class LoginSteps {
    public  WebDriver driver;
    public WebDriverWait wait;
    @Given("^I navigate to AutomationPractice$")
    public void i_navigate_to_basqar()  {
        driver= BaseDriver.getDriver();
        driver.get("http://automationpractice.com/index.php");
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.findElement(By.cssSelector(".login")).click();

    }

    @When("^I try to login using username and password$")
    public void i_try_to_login_using_username_and_password()  {
      wait=new WebDriverWait(driver,10);
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#email")));
        emailInput.sendKeys("umarekkurt@gmail.com");
        driver.findElement(By.cssSelector("#passwd")).sendKeys("ASDASD123123");
    }

    @Then("^I am logged in$")
    public void i_am_logged_in() {
        driver.findElement(By.cssSelector("#SubmitLogin")).click();
        String text = driver.findElement(By.cssSelector("#center_column>h1")).getText().toLowerCase();
        Assert.assertEquals("my account",text);
//        JavascriptExecutor ee=(JavascriptExecutor) driver;
//        ee.executeScript("scroll(0,400)");
    }
    @Given("^Click on the My personal information$")
    public void click_on_the_My_personal_information() throws InterruptedException {
        Thread.sleep(3000);
        WebElement element = driver.findElement(By.xpath("//span[text()='My personal information']"));
        Thread.sleep(2000);
        element.click();
    }

    @When("^I try to Change the first name \"([^\"]*)\",currentPassword, and the new password$")
    public void i_try_to_Change_the_first_name_currentPassword_and_the_new_password(String arg1) throws Exception {
        WebElement firstNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#firstname")));
        firstNameInput.clear();
        firstNameInput.sendKeys(arg1);
        driver.findElement(By.id("lastname")).click();
        driver.findElement(By.id("email")).click();
        driver.findElement(By.cssSelector("#old_passwd")).sendKeys("ASDASD123123");
        driver.findElement(By.cssSelector("#passwd")).sendKeys("ASDASD123123");
        WebElement element = driver.findElement(By.cssSelector("#confirmation"));
        element.sendKeys("ASDASD123123");
        element.sendKeys(Keys.ENTER);
        Thread.sleep(4000);

    }

    @Then("^Verify the name \"([^\"]*)\" on the top right is updated as your first name$")
    public void verify_the_name_on_the_top_right_is_updated_as_your_first_name(String arg1) throws Exception {
        SoftAssert softAssert=new SoftAssert();
        String alertSuccess = driver.findElement(By.cssSelector(".alert.alert-success")).getText();
        softAssert.assertEquals("Your personal information has been successfully updated.",alertSuccess);
        String accountName = driver.findElement(By.cssSelector(".header_user_info span")).getText();
        softAssert.assertEquals(arg1 ,accountName);

    }



}
