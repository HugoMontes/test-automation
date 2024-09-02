package testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Practica02 {

    private WebDriver driver;

    private final Integer MENU_USER_ROLE = 0;
    private final Integer MENU_STATUS = 1;

    @BeforeClass //ANTES
    public void setup() {
        //CONFIGURAR EL WEBDRIVER
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void LoginTest() throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        Thread.sleep(3000);

        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("Admin");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("admin123");
        WebElement btnLogin = driver.findElement(By.xpath("//button[@type='submit']"));
        btnLogin.click();

        Thread.sleep(3000);

        List<WebElement> optionsMenu = driver.findElements(By.cssSelector(".oxd-main-menu-item"));
        optionsMenu.get(0).click();

        Thread.sleep(3000);

        // LOCALIZAR LOS ELEMENTOS H6 QUE CONTIENEN EL TEXTO
        List<WebElement> breadcrumbItems = driver.findElements(By.cssSelector(".oxd-topbar-header-breadcrumb h6"));

        // CONCATENAR EL TEXTO CON UN " / " ENTRE ELLOS
        String completeText = getCompleteText(breadcrumbItems);

        // VALIDAR TEXTO
        Assert.assertTrue(completeText.contains("Admin / User Management"), "Header is not correct");

        // IMPRIMIR EL TEXTO
        System.out.println("Texto obtenido: " + completeText.toString());

        // CLIC BOTON ADD
        WebElement btnAdd = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[1]/button"));
        btnAdd.click();

        Thread.sleep(3000);

        selectFormOption(MENU_USER_ROLE, "Admin");
        selectFormOption(MENU_STATUS, "Enabled");
    }

    @AfterClass //DESPUES
    public void cerrar() {
//        driver.quit();
    }

    private String getCompleteText(List<WebElement> breadcrumbItems) {
        StringBuilder textoCompleto = new StringBuilder();
        for (int i = 0; i < breadcrumbItems.size(); i++) {
            textoCompleto.append(breadcrumbItems.get(i).getText());
            if (i < breadcrumbItems.size() - 1) {
                textoCompleto.append(" / ");
            }
        }
        return textoCompleto.toString();
    }

    private void selectFormOption(Integer selectedMenu, String selectedOption){
        // ESPERAR HASTA QUE EL ELEMENTO ESTÉ PRESENTE Y VISIBLE
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // HACER CLIC EN EL SEGUNDO SELECTOR (Status)
        List<WebElement> selectUserRole = driver.findElements(By.cssSelector(".oxd-select-text"));
        selectUserRole.get(selectedMenu).click();

        // ESPERAR HASTA QUE LAS OPCIONES SEAN VISIBLES
        List<WebElement> opcionesStatus = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".oxd-select-dropdown .oxd-select-option")));

        // SELECCIONAR LA OPCIÓN DESEADA
        for (WebElement opcion : opcionesStatus) {
            if (opcion.getText().equals(selectedOption)) {
                opcion.click();
                break;
            }
        }
    }
}
