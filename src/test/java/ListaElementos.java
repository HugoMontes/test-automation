import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

public class ListaElementos {
    public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        // INSTANCIAMOS CHROME
        System.setProperty("webdriver.chrome.driver",
                System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // DIRIGIRSE A LA PAGINA DE GOOGLE
        driver.get("https://www.wikipedia.org/");

        // PANTALLA COMPLETA
        driver.manage().window().maximize();

        // NAVEGAR A OTRA PAGINA
        driver.get("https://en.wikipedia.org/wiki/Main_Page");

        // LOCALIZAR LA LISTA DE ARTICULOS
        List<WebElement> articulos = driver.findElements(By.cssSelector("#mp-upper .mp-h2"));

        // IMPRIMIR LOS TITULOS DE LOS ARTICULOS
        for (WebElement articulo : articulos) {
            System.out.println("Articulos destacados: " + articulo.getText());
        }
    }
}
