package com.excilys.computerdatabase.webapp;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class AddWithoutDates {
  private WebDriver driver;
  private String baseUrl;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  
  @Ignore
  @Test
  public void testAddWithoutDates() throws Exception {
    driver.get(baseUrl + "/computer-database/dashboard");
    driver.findElement(By.id("addComputer")).click();
    driver.findElement(By.id("computerName")).clear();
    driver.findElement(By.id("computerName")).sendKeys("Test");
    new Select(driver.findElement(By.id("companyId")))
        .selectByVisibleText("Commodore International");
    driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
    // asserts (message d'erreur si message erreur, page de retour correcte...)
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
}
