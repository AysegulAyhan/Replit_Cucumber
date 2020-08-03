package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.BaseDriver;

import java.util.List;
import java.util.Random;

public class AddCart {
   public WebDriverWait wait;
   public WebDriver driver= BaseDriver.getDriver();
   public JavascriptExecutor js;
    @Given("^navigate to dresses and add to cart$")
    public void navigate_to_dresses_and_add_to_cart() throws InterruptedException {
      wait  =new WebDriverWait(driver,10);
      JavascriptExecutor js=(JavascriptExecutor) driver;
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#block_top_menu>ul>li:nth-child(2)>a")));
      driver.findElement(By.cssSelector("#block_top_menu>ul>li:nth-child(2)>a")).click();
        js.executeScript("scroll(0,700)");
        Thread.sleep(1000);
        List<WebElement> dresslist = driver.findElements(By.cssSelector(".grid.row>li"));
        dresslist.get(new Random().nextInt(dresslist.size())).click();
       // js.executeScript("scroll(0,500)");
        WebElement addCartButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.exclusive")));
        addCartButton.click();
        WebElement proceedCheckOut= wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[title='Proceed to checkout']")));
        proceedCheckOut.click();
        WebElement productPrice=driver.findElement(By.cssSelector("#total_product"));
        WebElement shippingPrice=driver.findElement(By.cssSelector("#total_shipping"));
        WebElement total=driver.findElement(By.cssSelector("#total_price"));
        Double product = converter(productPrice);
        Double shipping = converter(shippingPrice);
        Double totalEnd = converter(total);
        Assert.assertEquals(totalEnd,total(product,shipping));
        driver.findElement(By.cssSelector(".clearfix>a[title='Proceed to checkout']")).click();
        WebElement proceed2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[name='processAddress']")));
        proceed2.click();
    }

    public Double converter(WebElement e){
       String number=e.getText().replaceAll("[^\\d.]","");
       Double number1=Double.parseDouble(number);
       return number1;
    }
    public Double total(double num1, double num2){
        return num1+num2;
    }

    @When("^select shipping and payment$")
    public void selectShippingAndPayment() {
        WebElement shippingButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#uniform-cgv")));
        shippingButton.click();
        driver.findElement(By.name("processCarrier")).click();
        WebElement bankwire = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".bankwire")));
        bankwire.click();
        WebElement confirm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#cart_navigation>button")));
        confirm.click();
        String confirmtext = driver.findElement(By.cssSelector(".cheque-indent>strong")).getText();
        Assert.assertEquals(confirmtext,"Your order on My Store is complete.");

    }
}
