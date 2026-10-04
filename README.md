# Testes End-to-End com Selenium

Atividade de automação de testes utilizando Java, Selenium, JUnit e Maven.

Site utilizado:  
https://automationexercise.com/

## Cenários automatizados

### Test Case 1 - Register User

O teste realiza o cadastro completo de um usuário, preenchendo os dados solicitados, verificando a criação da conta, validando que o usuário está logado e excluindo a conta ao final.

Foram utilizadas 5 entradas diferentes para esse fluxo, variando principalmente o tamanho do nome e o dia de nascimento.

### Test Case 3 - Login User with incorrect email and password

O teste tenta realizar login utilizando e-mail e senha incorretos e verifica se a mensagem:

`Your email or password is incorrect!`

é exibida corretamente.

Foram utilizadas 5 entradas diferentes para esse fluxo, variando usuários inexistentes e diferentes tamanhos de senha.

## Técnicas de teste

Foram utilizados os critérios de particionamento de classes de equivalência e análise de valor limite para definir as entradas dos testes.

Os dois fluxos principais foram automatizados com Selenium utilizando testes parametrizados com JUnit 5.

Ao todo, são executados 10 testes:

- 5 testes para Login com e-mail e senha incorretos
- 5 testes para Registro de Usuário

## Casos considerados

| Caso | Funcionalidade | Entrada | Técnica | Resultado esperado |
|---|---|---|---|---|
| CT01 | Login | Usuário inexistente + senha com 1 caractere | Análise de valor limite | Exibir mensagem de erro |
| CT02 | Login | Usuário inexistente + senha com 2 caracteres | Análise de valor limite | Exibir mensagem de erro |
| CT03 | Login | Usuário inexistente + senha comum | Classe de equivalência inválida | Exibir mensagem de erro |
| CT04 | Login | Usuário inexistente + senha diferente | Classe de equivalência inválida | Exibir mensagem de erro |
| CT05 | Login | Usuário inexistente + senha longa | Análise de valor limite | Exibir mensagem de erro |
| CT06 | Cadastro | Nome com 1 caractere + dia 1 | Análise de valor limite | Cadastro realizado com sucesso |
| CT07 | Cadastro | Nome curto + dia 2 | Análise de valor limite | Cadastro realizado com sucesso |
| CT08 | Cadastro | Nome comum + dia 15 | Classe de equivalência válida | Cadastro realizado com sucesso |
| CT09 | Cadastro | Nome maior + dia 30 | Análise de valor limite | Cadastro realizado com sucesso |
| CT10 | Cadastro | Nome longo + dia 31 | Análise de valor limite | Cadastro realizado com sucesso |

## Tecnologias utilizadas

- Java 11
- Selenium
- JUnit 5
- Maven
- WebDriverManager

## Execução dos testes

Para executar todos os testes:

```bash
mvn clean test
```

Resultado esperado:

```text
Tests run: 10, Failures: 0, Errors: 0, Skipped: 0

BUILD SUCCESS
```

## Estrutura do projeto

```text
src
└── test
    └── java
        ├── LoginIncorretoTest.java
        └── RegistrarUsuarioTest.java
```