import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EsperaExplicita {
    public static void main(String[] args) throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        System.setProperty("webdriver.chrome.driver",
                System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://winstoncastillo.com/robot-selenium");

        // ABRIR EN PANTALLA COMPLETA LA VENTANA DEL NAVEGADOR
        driver.manage().window().maximize();

        // INGRESAR AL LOGIN
        // Hacer clic en "My Account"
        driver.findElement(By.xpath("//*[@id=\"top\"]/div/div[2]/ul/li[2]/div/a/span")).click();
        // Hacer clic en "Login"
        driver.findElement(By.xpath("//*[@id=\"top\"]/div/div[2]/ul/li[2]/div/ul/li[2]/a")).click();

        // ESPERA EXPLICITA
        // Esperar 10 segundos o hasta que el campo de "E-mail address" este visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        // Cuando este visible hacemos clic sobre el campo "email"
        driver.findElement(By.name("email")).click();
        // Esperamos 5 segundos
        Thread.sleep((5000));
        // Ingresamos el texto "correo@test.com"
        driver.findElement(By.name("email")).sendKeys("correo@test.com");
    }
}
