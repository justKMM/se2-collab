/*

package SeleniumTest;


import hbrs.se2.collhbrs.CollhbrsApplication;
import org.junit.AfterClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest//(classes = CollhbrsApplication.class)
public class LogInSeleniumTest {

    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        // Setze den Pfad zum ChromeDriver
        //System.setProperty(richtigen driver auswählen, richtigen Pfad auswählen);
        //System.setProperty(".driver", "C:\\");
        System.setProperty("webdriver.gecko.driver", "E:\\geckodriver\\geckodriver.exe");

        driver = new FirefoxDriver();

        driver.manage().window().maximize();

        // Öffne die Registrierungsseite
        driver.get("http://localhost:8080/login");

    }

    @Test
    public void testLogIn() {

        //Username
        driver.findElement(By.xpath("//*[@id=\"input-vaadin-text-field-6\"]/input")).sendKeys("test");
        //Email
        driver.findElement(By.xpath("//*[@id=\"input-vaadin-password-field-7\"]/input")).sendKeys("test");
        //SignUp Button
        driver.findElement(By.xpath("//*[@id=\"vaadin-button-container\"]")).click();

        // Überprüfe, ob die Registrierung erfolgreich war
        //WebElement successMessage = driver.findElement(By.id("successMessage"));
        //assertTrue(successMessage.isDisplayed(), "Die Registrierung war nicht erfolgreich.");


    }

    @AfterClass
    public static void tearDown () {
        // Schließe den Browser nach dem Test
        driver.quit();
    }


        //Dependency für pom file
          /*<dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>4.4.0</version>
        </dependency>*/
//}
