package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Page {

    private static final String URL = "http://demo.seleniumeasy.com/table-pagination-demo.html";

    private static final By FIND_ROWS = By.xpath("//*[@id='myTable']/tr[@style='display: table-row;']");

    private static final By BUTTON_NEXT = By.xpath("//*[@class='next_link']");

    public WebDriver driver;

    public Page(WebDriver driver) {
        this.driver = driver;
    }


    public void navigateTo() {
        driver.navigate().to(URL);
    }


    public void clickOnNextButton() {
        driver.findElement(BUTTON_NEXT).click();
    }


    public boolean isClickAbleNextButton() {
        return driver.findElement(BUTTON_NEXT).getAttribute("style").equals("display: block;");
    }


    public void getNumbersFromTable(List<Integer> numberList) {
        List<WebElement> tableRows = driver.findElements(FIND_ROWS);
        for(WebElement tableRow : tableRows) {
            WebElement td = tableRow.findElement(By.xpath("./td[1]"));
            Integer value = Integer.valueOf(td.getText());
            numberList.add(value);
        }
    }


}
