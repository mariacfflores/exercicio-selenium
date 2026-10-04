import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
                Duration.ofSeconds(20)
        );

        driver.get(
                "https://automationexercise.com/"
        );
    }


    /*
     * 5 entradas diferentes para cadastro.
     *
     * São utilizados nomes de diferentes tamanhos
     * e valores distintos para o dia de nascimento.
     *
     * Os dias 1 e 31 representam os valores
     * de limite inferior e superior do campo.
     */
    @ParameterizedTest(
            name = "Cadastro {index}: nome={0}, dia={1}"
    )
    @CsvSource({
            "'M', '1'",
            "'Ana', '2'",
            "'Maria Teste', '15'",
            "'Maria Clara Flores', '30'",
            "'Nome Muito Longo Para Teste Selenium', '31'"
    })
    public void registrarUsuario(
            String nome,
            String diaNascimento) {


        /*
         * Gera um e-mail diferente para cada execução.
         */
        String email =
                "teste"
                + System.nanoTime()
                + "@email.com";


        // 1 - Verifica página inicial
        assertTrue(
                driver.getTitle() != null
                && !driver.getTitle().isEmpty()
        );


        // 2 - Clica em Signup / Login
        WebElement botaoSignup =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.cssSelector(
                                        "a[href='/login']"
                                )
                        )
                );

        botaoSignup.click();


        // 3 - Verifica New User Signup!
        WebElement tituloCadastro =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.xpath(
                                        "//h2[contains(text(),'New User Signup!')]"
                                )
                        )
                );

        assertTrue(tituloCadastro.isDisplayed());


        // 4 - Nome
        driver.findElement(
                By.cssSelector(
                        "[data-qa='signup-name']"
                )
        ).sendKeys(nome);


        // 5 - E-mail
        driver.findElement(
                By.cssSelector(
                        "[data-qa='signup-email']"
                )
        ).sendKeys(email);


        // 6 - Signup
        driver.findElement(
                By.cssSelector(
                        "[data-qa='signup-button']"
                )
        ).click();


        // 7 - Verifica ENTER ACCOUNT INFORMATION
        WebElement tituloInformacoes =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.xpath(
                                        "//*[contains(text(),'Enter Account Information')]"
                                )
                        )
                );

        assertTrue(tituloInformacoes.isDisplayed());


        // 8 - Seleciona Mrs.
        clicarComJavaScript(
                driver.findElement(
                        By.id("id_gender2")
                )
        );


        // 9 - Senha
        driver.findElement(
                By.id("password")
        ).sendKeys("Senha123");


        // 10 - Dia de nascimento
        Select dia =
                new Select(
                        driver.findElement(
                                By.id("days")
                        )
                );

        dia.selectByValue(
                diaNascimento
        );


        // 11 - Mês
        Select mes =
                new Select(
                        driver.findElement(
                                By.id("months")
                        )
                );

        mes.selectByValue("1");


        // 12 - Ano
        Select ano =
                new Select(
                        driver.findElement(
                                By.id("years")
                        )
                );

        ano.selectByValue("2000");


        // 13 - Newsletter
        WebElement newsletter =
                wait.until(
                        ExpectedConditions.presenceOfElementLocated(
                                By.id("newsletter")
                        )
                );

        clicarComJavaScript(
                newsletter
        );


        // 14 - Ofertas de parceiros
        WebElement ofertas =
                wait.until(
                        ExpectedConditions.presenceOfElementLocated(
                                By.id("optin")
                        )
                );

        clicarComJavaScript(
                ofertas
        );


        // 15 - Primeiro nome
        driver.findElement(
                By.id("first_name")
        ).sendKeys(nome);


        // 16 - Sobrenome
        driver.findElement(
                By.id("last_name")
        ).sendKeys("Teste");


        // 17 - Empresa
        driver.findElement(
                By.id("company")
        ).sendKeys(
                "Empresa Teste"
        );


        // 18 - Endereço
        driver.findElement(
                By.id("address1")
        ).sendKeys(
                "Rua Teste, 123"
        );


        // 19 - Endereço 2
        driver.findElement(
                By.id("address2")
        ).sendKeys(
                "Apartamento 101"
        );


        // 20 - País
        Select pais =
                new Select(
                        driver.findElement(
                                By.id("country")
                        )
                );

        pais.selectByVisibleText(
                "Canada"
        );


        // 21 - Estado
        driver.findElement(
                By.id("state")
        ).sendKeys(
                "Ontario"
        );


        // 22 - Cidade
        driver.findElement(
                By.id("city")
        ).sendKeys(
                "Toronto"
        );


        // 23 - CEP
        driver.findElement(
                By.id("zipcode")
        ).sendKeys(
                "12345"
        );


        // 24 - Celular
        driver.findElement(
                By.id("mobile_number")
        ).sendKeys(
                "21999999999"
        );


        // 25 - Create Account
        WebElement criarConta =
                wait.until(
                        ExpectedConditions.presenceOfElementLocated(
                                By.cssSelector(
                                        "[data-qa='create-account']"
                                )
                        )
                );

        clicarComJavaScript(
                criarConta
        );


        // 26 - Verifica ACCOUNT CREATED!
        WebElement contaCriada =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector(
                                        "[data-qa='account-created']"
                                )
                        )
                );

        assertTrue(
                contaCriada.isDisplayed()
        );


        // 27 - Continue
        WebElement botaoContinuar =
                wait.until(
                        ExpectedConditions.presenceOfElementLocated(
                                By.cssSelector(
                                        "[data-qa='continue-button']"
                                )
                        )
                );

        clicarComJavaScript(
                botaoContinuar
        );


        tratarGoogleVignette();


        // 28 - Verifica Logged in as
        WebElement usuarioLogado =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.xpath(
                                        "//a[contains(.,'Logged in as')]"
                                )
                        )
                );

        assertTrue(
                usuarioLogado.isDisplayed()
        );


        // 29 - Delete Account
        tratarGoogleVignette();

        WebElement deletarConta =
                wait.until(
                        ExpectedConditions.presenceOfElementLocated(
                                By.cssSelector(
                                        "a[href='/delete_account']"
                                )
                        )
                );

        clicarComJavaScript(
                deletarConta
        );


        tratarGoogleVignette();


        // 30 - Verifica ACCOUNT DELETED!
        WebElement contaExcluida =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector(
                                        "[data-qa='account-deleted']"
                                )
                        )
                );

        assertTrue(
                contaExcluida.isDisplayed()
        );


        // 31 - Continue
        WebElement continuarFinal =
                wait.until(
                        ExpectedConditions.presenceOfElementLocated(
                                By.cssSelector(
                                        "[data-qa='continue-button']"
                                )
                        )
                );

        clicarComJavaScript(
                continuarFinal
        );
    }


    /*
     * Realiza o clique via JavaScript.
     *
     * Isso ajuda quando anúncios ou iframes
     * ficam visualmente por cima dos elementos.
     */
    private void clicarComJavaScript(
            WebElement elemento) {

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                elemento
        );

        js.executeScript(
                "arguments[0].click();",
                elemento
        );
    }


    /*
     * Alguns anúncios do Google adicionam
     * #google_vignette à URL.
     *
     * Quando isso acontecer, acessamos novamente
     * a mesma URL sem o fragmento do anúncio.
     */
    private void tratarGoogleVignette() {

        String urlAtual =
                driver.getCurrentUrl();

        if (
                urlAtual.contains(
                        "#google_vignette"
                )
        ) {

            System.out.println(
                    "Google Vignette detectado."
            );

            String urlSemAnuncio =
                    urlAtual.replace(
                            "#google_vignette",
                            ""
                    );

            driver.get(
                    urlSemAnuncio
            );
        }
    }


    @AfterEach
    public void fecharNavegador() {

        if (driver != null) {

            driver.quit();
        }
    }
}