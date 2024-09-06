package screenshot;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Screen {

    private WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        //CONFIGURAR EL WEBDRIVER
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Test
    public void LoginTest() throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Thread.sleep(4000);
        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("Admin");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("admin123");
        WebElement btnlogin = driver.findElement(By.xpath("//button[@type='submit']"));
        btnlogin.click();

        Thread.sleep(3000);
        //VERIFICAMOS QUE EL LOGIN FUE EXITOSO
        WebElement dashboard = driver.findElement(By.xpath("//h6[contains(.,'Dashboard')]"));
        Assert.assertTrue(dashboard.getText().contains("Dashboard"), "Login");
    }

    @Test
    public void menuAdmin() throws InterruptedException {

        WebElement btnAdmin = driver.findElement(By.xpath("//a[contains(.,'Admin')]"));
        btnAdmin.click();
        Thread.sleep(3000);
        WebElement btnAdd = driver.findElement(By.xpath("//button[@type='button'][contains(.,'Add')]"));
        btnAdd.click();
        Thread.sleep(3000);
        WebElement Rol = driver.findElement(By.xpath("(//div[contains(.,'-- Select --')])[13]"));
        Rol.click();
        Thread.sleep(3000);
        WebElement RolSeleccionado = driver.findElement(By.xpath("(//div[contains(.,'Admin')])[18]"));
        RolSeleccionado.click();
        Thread.sleep(3000);
        WebElement NombreEmpleado = driver.findElement(By.xpath("//input[@placeholder='Type for hints...']"));
        NombreEmpleado.sendKeys("Peter");
        WebElement NombreEmpleadoSeleccion = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[contains(.,'Peter Mac Anderson')])[14]")));
        //WebElement NombreEmpleadoSeleccion = driver.findElement(By.xpath("(//div[contains(.,'Peter Mac Anderson')])[14]"));
        NombreEmpleadoSeleccion.click();
        WebElement Status = driver.findElement(By.xpath("(//div[contains(.,'-- Select --')])[13]"));
        Status.click();
        Thread.sleep(3000);
        WebElement StatusSeleccion = driver.findElement(By.xpath("(//div[contains(.,'Enabled')])[14]"));
        StatusSeleccion.click();
        Thread.sleep(3000);
        WebElement Username = driver.findElement(By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]"));
        Username.sendKeys("admin");
        Thread.sleep(3000);

        // CAPTURA DE PANTALLA CUANDO SE TIENE UN USUARIO REGISTRADO
        // VERIFICAMOS QUE EXISTA EL MENSAJE "Already exists"
        try {
            WebElement alreadyExistsElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(.,'Already exists')]")));
            // VERIFICAR SI SE MUESTRA EL MENSAJE
            if (alreadyExistsElement.isDisplayed()) {
                System.out.println("El texto 'Already exists' esta visible.");
                takeScreenShot("Already exists");
            }

        } catch (TimeoutException ex) {
            // SI NO APARECE EL MENSAJE
            System.out.println("El texto 'Already exists' no es visible");
        }

        WebElement Password = driver.findElement(By.xpath("(//input[@type='password'])[1]"));
        Password.sendKeys("IQUATTRo.2024");
        Thread.sleep(3000);
        WebElement ConfiPass = driver.findElement(By.xpath("(//input[@type='password'])[2]"));
        ConfiPass.sendKeys("IQUATTRo.2024");
        Thread.sleep(3000);
        WebElement btnSave = driver.findElement(By.xpath("//button[contains(.,'Save')]"));
        btnSave.click();
        Thread.sleep(3000);
    }

    // METODO PARA SACAR CAPTURA DE PANTALLA
    private void takeScreenShot(String fileName) {
        try {
            File screenShotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            // GENERAR NOMBRE PARA UNA CAPTURA UNICA
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String screenShotName = fileName + "_" + timestamp + ".png";
            // DEFINIR LA RUTA DONDE SE GUARDARA EL ARCHIVO O CAPTURA DE PANTALLA
            Path destination = Paths.get("screenshots", screenShotName);
            // CREAR EL DIRECTORIO SI NO EXISTE
            Files.createDirectories(destination.getParent());
            // COPIAMOS EN EL DESTINO
            Files.copy(screenShotFile.toPath(), destination);
            System.out.println("ScreenShot save " + destination.toAbsolutePath());
        } catch (IOException ex) {
            // MANEJAMOS CUALQUIER ERROR QUE OCURRE
            System.err.println("Error al sacar la captura de pantalla " + ex.getMessage());
        }
    }

    @AfterClass
    public void cerrar() {
        driver.quit();
    }
}
