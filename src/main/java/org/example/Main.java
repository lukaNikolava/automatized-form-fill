package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Main {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Desktop\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testFormFill(){
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        WebElement textInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("my-text-id")));
        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("my-password")));
        WebElement textArea = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("my-textarea")));
        WebElement dropDown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("my-select")));
        Select select = new Select(dropDown);


        textInput.sendKeys("Phantom");
        password.sendKeys("strongPassword123");
        textArea.sendKeys("Phantom started to learn automatization... lets see");
        select.selectByVisibleText("Two");

        WebElement selectedOption = select.getFirstSelectedOption();
        Assert.assertEquals(selectedOption.getText(), "Two");


        WebElement checkedCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("my-check-1")));
        boolean checked = checkedCheckbox.isSelected();
        if(checked){
            checkedCheckbox.click();
        }


        WebElement defoultChekbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("my-check-2")));
        boolean defoult = defoultChekbox.isSelected();
        if(!defoult){
            defoultChekbox.click();
        }

        WebElement submitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[type='submit']")));
        submitButton.click();

        WebElement submitText = driver.findElement(By.xpath("//*[contains(text(), 'Form submitted')]"));
        Assert.assertEquals(submitText.getText(), "Form submitted");

    }

    @AfterMethod
    public void tearDown(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.quit();
    }
}

