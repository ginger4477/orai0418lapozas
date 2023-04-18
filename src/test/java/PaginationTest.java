import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.PagnitationPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class PaginationTest {

    WebDriver driver;

    @BeforeEach
    public void setWebDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        options.addArguments("ignore-certificate-errors");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-extensions");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--headless");
        //options.addArguments("--window-size=1920,1080");
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }



    @Test
    public void getNumberFromTable() {
        List<Integer> resultList = new ArrayList<>();

        PagnitationPage pagnitationPage = new PagnitationPage(driver);
        pagnitationPage.navigateTo();

        do {
            pagnitationPage.getNumbersFromTable(resultList);
            try {
                pagnitationPage.clickOnNextButton();
            } catch (ElementNotInteractableException e) {
                break;
            }
        }
        while (true);

        for(int i = 0; i < resultList.size()-1; i++) {
            Assertions.assertEquals(resultList.get(i)+1, resultList.get(i+1));
        }

    }



}
