# Testes End-to-End com Selenium

Atividade de automação de testes utilizando Java, Selenium, JUnit e Maven.

Site utilizado:  
https://automationexercise.com/

## Cenários automatizados

### Test Case 1 - Register User

O teste realiza o cadastro completo de um usuário, preenchendo os dados solicitados, verificando a criação da conta, validando que o usuário está logado e excluindo a conta ao final.

### Test Case 3 - Login User with incorrect email and password

O teste tenta realizar login utilizando e-mail e senha incorretos e verifica se a mensagem:

`Your email or password is incorrect!`

é exibida corretamente.

## Técnicas de teste

Foram utilizados os critérios de particionamento de classes de equivalência e análise de valor limite para definir diferentes entradas de teste.

Os dois fluxos principais foram automatizados com Selenium, e foram considerados pelo menos 5 casos de entrada durante o planejamento dos testes.

### Casos considerados

| Caso | Entrada | Técnica | Resultado esperado |
|---|---|---|---|
| CT01 | E-mail sintaticamente válido, mas usuário inexistente + senha incorreta | Classe de equivalência inválida | Exibir mensagem de erro no login |
| CT02 | E-mail sintaticamente válido + senha com 1 caractere | Análise de valor limite | Login não realizado |
| CT03 | E-mail sintaticamente válido + senha com vários caracteres | Classe de equivalência inválida | Login não realizado |
| CT04 | Data de nascimento com dia 1 | Valor limite inferior | Cadastro realizado com sucesso |
| CT05 | Data de nascimento com dia 31 | Valor limite superior | Cadastro realizado com sucesso |

## Tecnologias utilizadas

- Java 11
- Selenium
- JUnit 5
- Maven
- WebDriverManager

## Estrutura do projeto

```text
src
└── test
    └── java
        ├── LoginIncorretoTest.java
        └── RegistrarUsuarioTest.java