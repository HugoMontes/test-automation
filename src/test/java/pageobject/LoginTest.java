package pageobject;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest {
    // DECLARAR UNA VARIABLE PRIVADA DE TIPO WEBDRIVER PARA MANEJAR EL NAVEGADOR
    private WebDriver driver;

    // DECLARAR UNA INSTANCIA DE LA CLASE LOGIN
    Login login;

    // METODO QUE SE EJECUTA ANTES DE LAS PRUEBAS
    // INICIALIZA EL ENTORNO DE PRUEBA
    @BeforeClass
    public void setup() {
        // INICIALIZAR LA INSTANCIA LOGIN
        login = new Login(driver);
        // CONFIGURAR LA CONEXION CON EL CHROMDRIVER
        driver = login.chromeDriverConnection();
        // NAVEGAR A LA URL DE LA PAGINA
        login.visit("https://www.saucedemo.com/");
    }

    @AfterClass
    public void close() {
        driver.quit();
    }

    @Test
    public void test() {
        // LLAMAR AL METODO LOGIN PARA REALIZAR EL INICIO DE SESION
        login.loginUser();
    }
}
