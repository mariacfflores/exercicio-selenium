import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginIncorretoTest {

    protected WebDriver driver;

    @BeforeEach
    public void abrirNavegador() {

        driver = WebDriverManager.chromedriver().create();

        driver.manage().window().maximize();

        driver.get("https://automationexercise.com/");
    }

    @Test
    public void loginComEmailESenhaIncorretos() {

        // Clica em Signup / Login
        driver.findElement(By.cssSelector("a[href='/login']")).click();

        // Verifica se "Login to your account" apareceu
        WebElement tituloLogin =
                driver.findElement(By.xpath("//h2[contains(text(),'Login to your account')]"));

        assertTrue(tituloLogin.isDisplayed());

        // Digita email incorreto
        driver.findElement(By.cssSelector("[data-qa='login-email']"))
                .sendKeys("usuarioinexistente@email.com");

        // Digita senha incorreta
        driver.findElement(By.cssSelector("[data-qa='login-password']"))
                .sendKeys("senhaErrada123");

        // Clica no botão Login
        driver.findElement(By.cssSelector("[data-qa='login-button']")).click();

        // Procura a mensagem de erro
        WebElement mensagemErro =
                driver.findElement(
                        By.xpath("//p[contains(text(),'Your email or password is incorrect!')]")
                );

        // Verifica se apareceu
        assertTrue(mensagemErro.isDisplayed());
    }

    @AfterEach
    public void fecharNavegador() {
        driver.quit();
    }
}