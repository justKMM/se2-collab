package SeleniumTest;

import hbrs.se2.collhbrs.CollhbrsApplication;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

@SpringBootTest(classes = CollhbrsApplication.class)
public class LogInSeleniumTest {

    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {

        //Setze den Pfad zum Driver deiner Wahl
        //System.setProperty(richtigen driver auswählen, richtigen Pfad auswählen);
        //System.setProperty(".driver", "C:\\");
        System.setProperty("webdriver.gecko.driver", "E:\\geckodriver\\geckodriver.exe");

        driver = new FirefoxDriver();

        driver.manage().window().maximize();

        // Öffne die Registrierungsseite
        driver.get("http://localhost:8080/login");

    }

    @AfterAll
    public static void tearDown() {
        // Schließe den Browser nach dem Test
        driver.quit();
    }

    @Test
    void testLogIn() {

        //Username Feld auswählen
        driver.findElement(By.xpath("//*[@id=\"input-vaadin-text-field-6\"]")).sendKeys("selenium");
        //Password Feld auswählen
        driver.findElement(By.xpath("//*[@id=\"input-vaadin-password-field-7\"]")).sendKeys("Wasdwasd123");
        //SignIn Button klicken
        driver.findElement(By.xpath("/html/body/div[1]/flow-container-root-2521314/vaadin-vertical-layout/vaadin-vertical-layout/vaadin-login-form/vaadin-login-form-wrapper/vaadin-button[1]")).click();
        //Logout Button klicken
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Wait mit 10sec timer
        //Warten bis clickable ist
        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/flow-container-root-2521314/vaadin-app-layout/vaadin-horizontal-layout/vaadin-horizontal-layout/vaadin-menu-bar/vaadin-menu-bar-button[1]")));
        //Button klicken
        logoutButton.click();

        //Logout Button path
        //driver.findElement(By.xpath("/html/body/div[1]/flow-container-root-2521314/vaadin-app-layout/vaadin-horizontal-layout/vaadin-horizontal-layout/vaadin-menu-bar/vaadin-menu-bar-button[1]")).click();

    }
}
