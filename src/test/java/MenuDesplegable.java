import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class MenuDesplegable {
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

        // LOCALIZAR EL MENU POR SU ID
        WebElement menuDesplegable = driver.findElement(By.id("searchLanguage"));

        // CREAR UN OBJETO SELECT PARA INTERACTUAR
        Select seleccionarLenguaje = new Select(menuDesplegable);

        // SELECCIONAR UN IDIOMA POR EJEMPLO INGLES
        seleccionarLenguaje.selectByValue("en");

        // VERIFICAR QUE EL LENGUAJE SELECCIONADO ES CORRECTO
        String seleccionOp = seleccionarLenguaje.getFirstSelectedOption().getText();
        System.out.println("Idioma seleccionado: " + seleccionOp);
    }
}
