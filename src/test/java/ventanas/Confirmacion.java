package ventanas;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Confirmacion {
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

        // HACER CLIC EN EL BOTON PARA MOSTRAR CONFIRMACION
        WebElement confirmarBoton = driver.findElement(By.id("confirmButton"));

        // INTERACCION CON EL SCROLL
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Hacer scroll hasta llegar al elemento confirmarBoton
        js.executeScript("arguments[0].scrollIntoView({block: 'nearest', inline: 'center'});", confirmarBoton);

        // HACER CLIC SOBRE EL BOTON
        confirmarBoton.click();

        // CAMBIAR EL ENFOQUE
        Alert confirmacionAlert = driver.switchTo().alert();

        // OBTENER EL TEXTO DE CONFIRMACION
        String textoAlerta = confirmacionAlert.getText();
        System.out.println("Texto de la confirmacion: " + textoAlerta);
    }
}
