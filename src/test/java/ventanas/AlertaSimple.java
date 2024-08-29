package ventanas;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class AlertaSimple {
    public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        // INSTANCIAMOS CHROME
        System.setProperty("webdriver.chrome.driver",
                System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // DIRIGIRSE A LA PAGINA
        driver.get("https://demoqa.com/alerts");

        // PANTALLA COMPLETA
        driver.manage().window().maximize();

        // CAPTURAR EL ELEMENTO y HACER CLIC EN EL BOTON
        WebElement btnAlerta = driver.findElement(By.id("alertButton"));
        btnAlerta.click();

        // CAMBIAMOS EL ENFOQUE A LA VENTANA DE ALERTA
        Alert alert = driver.switchTo().alert();
        // OBTENER EL TEXTO DE LA VENTANA DE ALERTA
        String alertaTexto = alert.getText();
        System.out.println("El texto de alerta es: " + alertaTexto);
        // ACEPTAMOS LA ALERTA
        alert.accept();
    }
}
