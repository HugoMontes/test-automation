package testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Practica01 {

    private WebDriver driver;

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

        //VERIFICAMOS QUE EL LOGIN FUE EXITOSO
        WebElement dashboard = driver.findElement(By.xpath("//h6[contains(.,'Dashboard')]"));
        Assert.assertTrue(dashboard.getText().contains("Dashboard"), "Login");
    }

    @AfterClass //DESPUES
    public void cerrar() {
        driver.quit();
    }
}