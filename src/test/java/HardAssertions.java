import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

public class HardAssertions {
    public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        // INSTANCIAMOS CHROME
        System.setProperty("webdriver.chrome.driver",
                System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // DIRIGIRSE A LA PAGINA DE GOOGLE
        driver.get("https://www.google.com.bo");

        // PANTALLA COMPLETA
        driver.manage().window().maximize();

        // VERIFICAR QUE EL TITULO DE LA PAGINA SEA GOOGLE
        String expectedTitle = "Google";
        // Obtener el titulo actual desde la pagina
        String actualTitle = driver.getTitle();
        // Verificar que el titulo sea igual a "Google"
        Assert.assertEquals(actualTitle, expectedTitle, "El titulo del pagina NO es Google");

        // VERIFICAR QUE EL CAMPO DE BUSQUEDA ESTE PRESENTE
        WebElement searchBox = driver.findElement(By.name("q"));
        Assert.assertTrue(searchBox.isDisplayed(), "Campo de busqueda NO esta presente");
    }
}
