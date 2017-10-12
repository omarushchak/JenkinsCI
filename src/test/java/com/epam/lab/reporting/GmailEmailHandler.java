package com.epam.lab.reporting;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class GmailEmailHandler {
    public static final Logger LOGGER = Logger.getLogger(GmailEmailHandler.class);
    WebDriver driver;

    public GmailEmailHandler(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    public void goToGmailLoginPage(){
        String url = "https://accounts.google.com/signin";
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void enterEmail(String email){
        // Type login
        WebElement emailPhone = driver.findElement(By.xpath("//input[@id='identifierId']"));
        emailPhone.sendKeys(email);
        driver.findElement(By.id("identifierNext")).click();
    }

    public void enterPassword(String password){
        WebElement passwordElement = driver.findElement(By.xpath("//input[@name='password']"));
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(passwordElement));
        passwordElement.sendKeys(password);

        driver.findElement(By.id("passwordNext")).click();
        driver.findElement(By.className("WaidBe")).click();
    }

    public boolean checkSuccessLogin(String email){
        String loadingEmailText = driver
                .findElement(By.xpath("//*[@id=\"loading\"]/div[1]/div[1]"))
                .getAttribute("innerHTML");

        return loadingEmailText.contains(email);
    }

    public void quit(){
        driver.quit();
    }
}
