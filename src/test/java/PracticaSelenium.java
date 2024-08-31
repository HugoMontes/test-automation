import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class PracticaSelenium {
    public static void main(String[] args) throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        System.setProperty("webdriver.chrome.driver",
                System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // Definir una espera explicita
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.get("https://opencart.abstracta.us/");

        driver.manage().window().maximize();

        WebElement btnItems = driver.findElement(By.id("cart-total"));
        String itemValue = btnItems.getText();
        String expectedValueItem = "0 item(s) - $0.00";
        Assert.assertEquals(itemValue, expectedValueItem, "Antes de iniciar tiene items y su saldo es diferente de cero");
        System.out.println("Valor del item: " + itemValue);

        WebElement firstElement = driver.findElement(By.cssSelector("button[data-original-title='Add to Wish List']"));
        firstElement.click();

        Thread.sleep((2000));

        WebElement alertMessage = driver.findElement(By.xpath("//*[@id=\"common-home\"]/div[1]"));
        String message = alertMessage.getText();
        System.out.println("El texto es : " + message);

        List<WebElement> addCarButtons = driver.findElements(By.cssSelector(".button-group button:not([data-original-title])"));
        for (int i = 0; i <= 3; i++) {
            addCarButtons.get(0).click();
        }

        Thread.sleep((2000));

        WebElement newBtnItems = driver.findElement(By.id("cart-total"));
        String newItemValue = newBtnItems.getText();
        System.out.println("Valor del item : " + newItemValue);
    }
}
