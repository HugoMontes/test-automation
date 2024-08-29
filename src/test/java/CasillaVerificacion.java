import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

public class CasillaVerificacion {
    public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        // INSTANCIAMOS CHROME
        System.setProperty("webdriver.chrome.driver",
                System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // DIRIGIRSE A LA PAGINA
        driver.get("https://www.w3schools.com/html/html_forms.asp");

        // PANTALLA COMPLETA
        driver.manage().window().maximize();

        // LOCALIZAR EL ELEMENTO CHECKBOX
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));

        // SELECCIONAR LAS CASILLAS VERIFICAR SI TIENE "BIKE" O "BOAT"
        for (WebElement checkbox : checkboxes) {
            // OBTENER EL VALOR DEL ATRIBUTO
            String value = checkbox.getAttribute("value");
            // VERIFICAR EL VALOR
            if (value.equals("Bike") || value.equals("Boat")) {
                // VERIFICAR QUE LA CASILLA NO ESTE SELECCIONADA
                if (!checkbox.isSelected()) {
                    // HACEMOS CLIC EN LA CASILLA DE VERIFICACION
                    checkbox.click();
                }
            }
        }
    }
}
