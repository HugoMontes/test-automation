import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.asserts.SoftAssert;

public class SoftAssertions {
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

        // CREAR UNA INSTANCIA DE SOFTASSET
        SoftAssert softAssert = new SoftAssert();
        // Buscar el termino "Selenium"
        WebElement searchBox = driver.findElement(By.name("search"));
        // Ingresar el texto "Selenium"
        searchBox.sendKeys("Selenium");
        // Presionar en el boton buscar
        WebElement searchButton = driver.findElement(By.xpath("//button[@type='submit']"));
        searchButton.click();

        // VALIDAR QUE EL TITULO DE LA PAGINA CONTENGA "Selenium"
        String pageTitle = driver.getTitle();
        softAssert.assertTrue(pageTitle.contains("Selenium"), "El titulo de la pagina NO contiene selenium");

        // VALIDAR QUE EL ENCABEZADO CONTENGA "Selenium"
        WebElement header = driver.findElement(By.id("firstHeading"));
        String headerText = header.getText();
        softAssert.assertTrue(headerText.contains("Selenium"), "El encabezado NO contiene selenium");
    }
}
