package com.owen;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertTrue;

public class FirstTest extends BaseTest {

    @Test
    public void googleSearch() {

        String strToSearchFor = "google";
        WebDriver d = driver;
        d.get("http://www.google.com");
        WebElement element = d.findElement(By.name("q"));
        element.sendKeys(strToSearchFor);
        element.submit();
        assertTrue(d.getTitle().toLowerCase().contains(strToSearchFor.toLowerCase()));
    }


}
