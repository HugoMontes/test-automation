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
import java.util.Random;

public class Practica02 {

    private WebDriver driver;
    private final Integer MENU_USER_ROLE = 0;
    private final Integer MENU_STATUS = 1;
    private final String NEW_PASSWORD = "Password123";
    private Random random = new Random();

    @BeforeClass //ANTES
    public void setup() {
        //CONFIGURAR WEBDRIVER
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void LoginTest() throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        Thread.sleep(8000);

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

//        // CLIC BOTON ADD
//        clickAddButton();
//        Thread.sleep(3000);
//
//        // AÑADIR USUARIO "Admin"
//        String adminUserName = addNewUser("Admin");
//        Thread.sleep(5000);
//
//        // CLIC BOTON ADD
//        clickAddButton();
//        Thread.sleep(3000);
//
//        // AÑADIR USUARIO "ESS"
//        String essUserName =addNewUser("ESS");
//        Thread.sleep(5000);


        String adminUserName = "test7051";
        String essUserName = "test8821";


        // BUSCAR USUARIO ADMIN
        findUserName(adminUserName);
//        WebElement inputFindUsername = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/input"));
//        inputFindUsername.sendKeys(adminUserName);
//        WebElement btnSearch = driver.findElement(By.xpath("//button[@type='submit']"));
//        btnSearch.click();
        Thread.sleep(3000);

        // EDITAR USUARIO ADMIN
        clickEditButton();
        Thread.sleep(3000);
        editRoleAndStatusUser("ESS", "Disabled");
        Thread.sleep(10000);

        // BUSCAR USUARIO ESS
        findUserName(essUserName);
        Thread.sleep(3000);

        // EDITAR USUARIO ESS
        clickEditButton();
        Thread.sleep(3000);
        editRoleAndStatusUser("Admin", "Disabled");

    }

    @AfterClass //DESPUES
    public void cerrar() {
//        driver.quit();
    }

    private String addNewUser(String userRole) throws InterruptedException {
        selectFormOption(MENU_USER_ROLE, userRole);
        selectFormOption(MENU_STATUS, "Enabled");

        selectFormEmployeeName("Ravi");

        WebElement formUserName = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[4]/div/div[2]/input"));
        String formUserNameStr = "test" + random.nextInt(1000) + 1;
        formUserName.sendKeys(formUserNameStr);

        WebElement formPassword = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[1]/div/div[2]/input"));
        formPassword.sendKeys(NEW_PASSWORD);

        WebElement formConfirmPassword = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/div[2]/input"));
        formConfirmPassword.sendKeys(NEW_PASSWORD);

        System.out.println("-----------------------------------------");
        System.out.println("Nombre Usuario: " + formUserNameStr);
        System.out.println("Rol : " + userRole);
        System.out.println("Password: " + NEW_PASSWORD);

        Thread.sleep(3000);

        WebElement btnSave = driver.findElement(By.xpath("//button[@type='submit']"));
        btnSave.click();

        return formUserNameStr;
    }

    private void editRoleAndStatusUser(String newRole, String newStatus) {
        selectFormOption(MENU_USER_ROLE, "ESS");
        selectFormOption(MENU_STATUS, "Disabled");
        WebElement btnSave = driver.findElement(By.xpath("//button[@type='submit']"));
        btnSave.click();
    }

    private void clickAddButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        // ESPERAR HASTA QUE EL BOTON SEA VISIBLE
        WebElement btnAdd = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[1]/button")));
        btnAdd.click();
    }

    private void clickEditButton() {
        List<WebElement> editButtons = driver.findElements(By.cssSelector(".bi-pencil-fill"));
        editButtons.get(0).click();
    }

    private void findUserName(String username) {
        WebElement inputFindUsername = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/input"));
        inputFindUsername.sendKeys(username);
        WebElement btnSearch = driver.findElement(By.xpath("//button[@type='submit']"));
        btnSearch.click();
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

    private void selectFormOption(Integer selectedMenu, String selectedOption) {
        // ESPERAR HASTA QUE EL ELEMENTO ESTÉ PRESENTE Y VISIBLE // fhms
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

    private void selectFormEmployeeName(String employeeName) throws InterruptedException {
        // INGRESAR EL NOMBRE A BUSCAR // fhms
        WebElement formEmployeeName = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/div/div[2]/div/div/input"));
        formEmployeeName.sendKeys(employeeName);

        Thread.sleep(5000);

        // ESPERAR HASTA QUE EL ELEMENTO ESTÉ PRESENTE Y VISIBLE
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // ESPERAR HASTA QUE LAS OPCIONES SEAN VISIBLES
        List<WebElement> opcionesStatus = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".oxd-autocomplete-dropdown")));

        // SELECCIONAR LA PRIMERA OPCION
        opcionesStatus.get(0).click();
    }
}
