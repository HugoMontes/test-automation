import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Navegadores {
    public static void main(String[] args) throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        System.setProperty("webdriver.chrome.driver",
                System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://winstoncastillo.com/robot-selenium");

        // ABRIR EN PANTALLA COMPLETA LA VENTANA DEL NAVEGADOR
        driver.manage().window().maximize();

        // Antes de continuar se espera 5 segundos
//        try {
//            Thread.sleep((5000));
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        // ESPERA IMPLICITA
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Capturar un elemento por su XPath
        // HACER CLIC EN LA OPCION CAMARA
        driver.findElement(By.xpath("//*[@id=\"narbar-menu\"]/ul/li[7]/a")).click();
    }
}
