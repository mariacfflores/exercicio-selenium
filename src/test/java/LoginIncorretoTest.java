import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;


public class LoginIncorretoTest {

    protected WebDriver driver;
    protected WebDriverWait wait;


    @BeforeEach
    public void abrirNavegador() {

        driver = WebDriverManager.chromedriver().create();

        driver.manage().window().maximize();

        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(15)
        );

        driver.get("https://automationexercise.com/");
    }


    /*
     * 5 entradas diferentes.
     *
     * São testadas diferentes combinações de
     * usuários inexistentes e tamanhos de senha.
     *
     * Técnicas:
     * - Classes de equivalência
     * - Análise de valor limite
     */
    @ParameterizedTest(
            name = "Login inválido {index}: email={0}, senha={1}"
    )
    @CsvSource({
            "'usuarioinexistente1@email.com', 'a'",
            "'usuarioinexistente2@email.com', 'ab'",
            "'usuarioinexistente3@email.com', 'senha123'",
            "'usuarioinexistente4@email.com', 'senhaErrada123'",
            "'usuarioinexistente5@email.com', '12345678901234567890'"
    })
    public void loginComEmailESenhaIncorretos(
            String email,
            String senha) {


        // 1 - Verifica se a página inicial carregou
        assertTrue(
                driver.getTitle() != null
                && !driver.getTitle().isEmpty()
        );


        // 2 - Clica em Signup / Login
        WebElement botaoLogin =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.cssSelector(
                                        "a[href='/login']"
                                )
                        )
                );

        botaoLogin.click();


        // 3 - Verifica Login to your account
        WebElement tituloLogin =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.xpath(
                                        "//h2[contains(text(),'Login to your account')]"
                                )
                        )
                );

        assertTrue(tituloLogin.isDisplayed());


        // 4 - Digita o e-mail
        driver.findElement(
                By.cssSelector(
                        "[data-qa='login-email']"
                )
        ).sendKeys(email);


        // 5 - Digita a senha
        driver.findElement(
                By.cssSelector(
                        "[data-qa='login-password']"
                )
        ).sendKeys(senha);


        // 6 - Clica em Login
        driver.findElement(
                By.cssSelector(
                        "[data-qa='login-button']"
                )
        ).click();


        // 7 - Verifica a mensagem de erro
        WebElement mensagemErro =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.xpath(
                                        "//p[contains(text(),'Your email or password is incorrect!')]"
                                )
                        )
                );


        assertTrue(mensagemErro.isDisplayed());
    }


    @AfterEach
    public void fecharNavegador() {

        if (driver != null) {
            driver.quit();
        }
    }
}