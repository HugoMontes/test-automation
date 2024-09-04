package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login extends Base {

    // DEFINIR LOCALIZADORES
    // Definir el localizador para el campo de nombre de usuario
    By username = By.xpath("//input[@name='user-name']");
    // Password
    By password = By.xpath("//input[@name='password']");
    // Definimos de boton Login
    By btnLogin = By.xpath("//input[@name='login-button']");

    // LLAMAR AL CONSTRUCTOR DE LA CLASE BASE CON EL DRIVER RECIBIDO
    public Login(WebDriver driver) {
        super(driver);
    }

    // METODO PARA REALIZAR EL INICIO DE SESION
    public void loginUser() {
        // ENVIAMOS EL TEXTO "standard_user" AL CAMPO DE NOMBRE DE USUARIO
        type("standard_user", username);
        // ENVIAMOS EL TEXTO "secret_sauce" AL CAMPO CONTRASEÑA O PASSWORD
        type("secret_sauce", password);
        // HACEMOS CLIC EN EL BOTON DE INICIO DE SESION
        click(btnLogin);
    }
}
