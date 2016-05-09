package com.excilys.computerdatabase.integration;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AddWithoutDates {
  private WebDriver driver;
  private String baseUrl;
  private StringBuffer verificationErrors = new StringBuffer();

  /**
   * Launching firefox and the webapp.
   */
  @Before
  public void setUp() {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testAddWithoutDates() throws InterruptedException {
    driver.get(baseUrl + "/computer-database-webapp/dashboard");
    driver.findElement(By.linkText("MacBook Pro 15.4 inch")).click();
    driver.findElement(By.linkText("Cancel")).click();
    driver.findElement(By.linkText("CM-5e")).click();
    // asserts (message d'erreur si message erreur, page de retour
    // correcte...)
  }

  /**
   * Leave firefox after running all tests.
   */
  @After
  public void tearDown() {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
}
