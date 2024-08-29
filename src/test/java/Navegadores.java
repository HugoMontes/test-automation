import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Navegadores {
    public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        System.setProperty("webdriver.chrome.driver",
                System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://winstoncastillo.com/robot-selenium");

        // ABRIR EN PANTALLA COMPLETA LA VENTANA DEL NAVEGADOR
        driver.manage().window().maximize();
        // HACER CLIC EN LA OPCION CAMARA
        driver.findElement(By.xpath("//*[@id=\"narbar-menu\"]/ul/li[7]/a")).click();
    }
}
