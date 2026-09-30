import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RegistrarUsuarioTest {

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


    @Test
    public void registrarUsuario() {

        String nome = "Maria Teste";

        // Gera um email diferente em cada execução
        String email =
                "mariateste"
                + System.currentTimeMillis()
                + "@email.com";


        // 1 - Clica em Signup / Login
        driver.findElement(
                By.cssSelector("a[href='/login']")
        ).click();


        // 2 - Verifica New User Signup!
        WebElement tituloCadastro =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.xpath(
                                        "//h2[contains(text(),'New User Signup!')]"
                                )
                        )
                );

        assertTrue(tituloCadastro.isDisplayed());


        // 3 - Preenche nome
        driver.findElement(
                By.cssSelector("[data-qa='signup-name']")
        ).sendKeys(nome);


        // 4 - Preenche email
        driver.findElement(
                By.cssSelector("[data-qa='signup-email']")
        ).sendKeys(email);


        // 5 - Clica em Signup
        driver.findElement(
                By.cssSelector("[data-qa='signup-button']")
        ).click();


        // 6 - Verifica ENTER ACCOUNT INFORMATION
        WebElement tituloInformacoes =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.xpath(
                                        "//*[contains(text(),'Enter Account Information')]"
                                )
                        )
                );

        assertTrue(tituloInformacoes.isDisplayed());


        // 7 - Seleciona Mrs.
        driver.findElement(
                By.id("id_gender2")
        ).click();


        // 8 - Preenche senha
        driver.findElement(
                By.id("password")
        ).sendKeys("Senha123");


        // 9 - Data de nascimento
        Select dia =
                new Select(
                        driver.findElement(By.id("days"))
                );

        dia.selectByValue("1");


        Select mes =
                new Select(
                        driver.findElement(By.id("months"))
                );

        mes.selectByValue("1");


        Select ano =
                new Select(
                        driver.findElement(By.id("years"))
                );

        ano.selectByValue("2000");


        // 10 - Newsletter
        driver.findElement(
                By.id("newsletter")
        ).click();


        // 11 - Ofertas de parceiros
        driver.findElement(
                By.id("optin")
        ).click();


        // 12 - Primeiro nome
        driver.findElement(
                By.id("first_name")
        ).sendKeys("Maria");


        // 13 - Sobrenome
        driver.findElement(
                By.id("last_name")
        ).sendKeys("Teste");


        // 14 - Empresa
        driver.findElement(
                By.id("company")
        ).sendKeys("Empresa Teste");


        // 15 - Endereço
        driver.findElement(
                By.id("address1")
        ).sendKeys("Rua Teste, 123");


        // 16 - Segundo endereço
        driver.findElement(
                By.id("address2")
        ).sendKeys("Apartamento 101");


        // 17 - País
        Select pais =
                new Select(
                        driver.findElement(By.id("country"))
                );

        pais.selectByVisibleText("Canada");


        // 18 - Estado
        driver.findElement(
                By.id("state")
        ).sendKeys("Ontario");


        // 19 - Cidade
        driver.findElement(
                By.id("city")
        ).sendKeys("Toronto");


        // 20 - CEP
        driver.findElement(
                By.id("zipcode")
        ).sendKeys("12345");


        // 21 - Celular
        driver.findElement(
                By.id("mobile_number")
        ).sendKeys("21999999999");


        // 22 - Clica em Create Account
        driver.findElement(
                By.cssSelector("[data-qa='create-account']")
        ).click();


        // 23 - Verifica ACCOUNT CREATED!
        WebElement contaCriada =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector(
                                        "[data-qa='account-created']"
                                )
                        )
                );

        assertTrue(contaCriada.isDisplayed());


        // 24 - Clica em Continue
        driver.findElement(
                By.cssSelector("[data-qa='continue-button']")
        ).click();


        // Dá um pequeno tempo para verificar
        // se o anúncio do Google apareceu
        try {

            Thread.sleep(2000);

        } catch (InterruptedException e) {

            e.printStackTrace();
        }


        // Se o anúncio Google Vignette aparecer,
        // acessa a página inicial diretamente
        if (driver.getCurrentUrl().contains("#google_vignette")) {

            System.out.println(
                    "Anúncio do Google detectado."
            );

            driver.get(
                    "https://automationexercise.com/"
            );
        }


        // 25 - Verifica Logged in as
        WebElement usuarioLogado =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.xpath(
                                        "//a[contains(.,'Logged in as')]"
                                )
                        )
                );

        assertTrue(usuarioLogado.isDisplayed());


        // 26 - Clica em Delete Account
        driver.findElement(
                By.cssSelector(
                        "a[href='/delete_account']"
                )
        ).click();


        // 27 - Verifica ACCOUNT DELETED!
        WebElement contaExcluida =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector(
                                        "[data-qa='account-deleted']"
                                )
                        )
                );

        assertTrue(contaExcluida.isDisplayed());


        // 28 - Clica em Continue
        driver.findElement(
                By.cssSelector(
                        "[data-qa='continue-button']"
                )
        ).click();

    }


    @AfterEach
    public void fecharNavegador() {

        driver.quit();
    }
}