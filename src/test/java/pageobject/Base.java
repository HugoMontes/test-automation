package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Base {
    // DECLARAR UNA VARIABLE PRIVADA DE TIPO WEBDRIVER PARA MANEJAR EL NAVEGADOR
    private WebDriver driver;

    // CONSTRUCTOR DE LA CLASE BASE QUE RECIBE UN WEBDRIVER Y LO ASIGNA A LA VARIABLE 'driver'
    public Base(WebDriver driver) {
        this.driver = driver;
    }

    // METODO PARA CONFIGURAR LA CONEXION CON CHROMEDRIVER Y DEVOLVER EL WEBDRIVER INICIALIZADO
    public WebDriver chromeDriverConnection() {
        // CONFIGURAMOS LA PROPIEDAD DEL SISTEMA PARA USAR CHROMDRIVER
        System.setProperty("webdriver.chrome.driver",
                System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        // INICILIZAR UNA INSTANCIA DE CHROMEDRIVER
        driver = new ChromeDriver();
        // DEVOLVEMOS EL WEBDRIVER CONFIGURADO
        return driver;
    }

    // METODO PARA ENCONTRAR UN UNICO ELEMENTO EN LA PAGINA
    public WebElement findElement(By locator) {
        // USAMOS EL WEBDRIVER PARA ENCONTRAR Y DEVOLVER EL ELEMENTO WEB
        return driver.findElement(locator);
    }

    // METODO PARA ENCONTRAR MULTIPLES ELEMENTOS USANDO LOCALIZADOR BY
    public List<WebElement> findElementos(By locator) {
        // USAMOS EL WEBDRIVER PARA ENCONTRAR Y DEVOLVER UNA LISTA DE ELEMENTOS WEB
        return driver.findElements(locator);
    }

    // METODO PARA OBTENER EL TEXTO DE UN ELEMENTO
    public String getText(WebElement element) {
        return element.getText();
    }

    // METODO PARA ENVIAR TEXTO EN UN CAMPO
    public void type(String inputText, By locator) {
        // ENCONTRAMOS EL CAMPO DE ENTRADA USANDO LOCALIZADOR Y ENVIAMOS EL TEXTO
        driver.findElement(locator).sendKeys(inputText);
    }

    // METODO PARA HACER CLIC EN UN ELEMENTO
    public void click(By locator) {
        // ENCONTRAMOS EL ELEMENTO USANDO EL LOCALIZADOR Y HACEMOS CLIC
        driver.findElement(locator).click();
    }

    // METODO PARA NAVEGAR A UNA URL ESPECIFICA
    public void visit(String url) {
        // USAR EL WEBDRIVER PARA ABRIR LA PAGINA INDICADA
        driver.get(url);
    }
}
