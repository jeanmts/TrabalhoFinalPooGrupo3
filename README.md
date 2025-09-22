# Empresa Poo


## Participantes

- Amanda Lisboa Ramos da Cunha  
- (Espaço para o Nome do Participante 2)  
- (Espaço para o Nome do Participante 3)  
- (Espaço para o Nome do Participante 4)  
- (Espaço para o Nome do Participante 5)  

---

## Objetivo do Projeto

O projeto **Empresa Poo** tem como objetivo principal realizar o **cálculo e a gestão da folha de pagamento** de funcionários de uma empresa.  
A aplicação permite cadastrar funcionários e seus dependentes, calcular descontos obrigatórios (INSS e IRRF), aplicar abatimento por dependentes e gerar a folha de pagamento com salário líquido. Além disso, o sistema lê dados de entrada a partir de arquivos CSV, realiza persistência em banco de dados e exporta o resultado processado em arquivos de saída também no formato CSV.

---

## Estrutura do Projeto

O projeto foi desenvolvido em **Java**, organizado em pacotes para garantir clareza e separação de responsabilidades:

- **Model**  
  Contém as classes de domínio (entidades do sistema):  
  - `Pessoa` (classe abstrata)  
  - `Funcionario`  
  - `Dependente`  
  - `FolhaPagamento`

- **Repository**  
  Classes de persistência responsáveis pela comunicação com o banco de dados (DAO).  
  - Exemplo: `FuncionarioDao`, `DependenteDao`.

- **Service**  
  - Este pacote também contém a classe `ConnectionFactory` para gerenciar a conexão com o banco de dados.

- **Enum**  
  Enumeradores do sistema, como:  
  - `Parentesco` (FILHO, SOBRINHO, OUTROS).

- **Exception**  
  Exceções personalizadas, como:  
  - `DependenteException`, que trata casos de dependentes inválidos (maiores de 18 anos ou CPF duplicado).

- **Interface**  
  - Interfaces que definem contratos para as regras de negócio, como a interface `CalculosPagamento`, que padroniza os métodos para o cálculo de INSS, IR e salário líquido.

- **Main**  
  Ponto de entrada da aplicação:  
  - `Aplicacao.java`

- **sql**  
  Arquivo `schema.sql` contendo a definição das tabelas necessárias:  
  - `funcionario`  
  - `dependente`  
  - `folha_pagamento`

**Principais classes e relações:**
- `Pessoa` é abstrata e serve de base para `Funcionario` e `Dependente`.  
- `Funcionario` agrega uma lista de `Dependente`.  
- `FolhaPagamento` está associada a um único `Funcionario`, contendo atributos como `dataPagamento`, `descontoINSS`, `descontoIR` e `salarioLiquido`.
---

## Diagrama de Classes (UML) 

<img width="786" height="841" alt="DiagramaUML" src="https://github.com/user-attachments/assets/853b17aa-0dc2-4634-87a4-38f541472761" />

---

## Como Baixar e Executar

### Requisitos

- **Java Development Kit (JDK)** 17 ou superior.  
- **PostgreSQL** 12+ instalado e rodando.  
- Opcional: **Maven** ou **Gradle** se quiser automatizar build e execução.  
- Uma IDE como IntelliJ IDEA, Eclipse ou VS Code com plugin Java.  

### Passo a passo

1. **Clonar o repositório**
```
 git clone <URL-do-repositorio> 
  cd EmpresaPoo
```

2. **Criar o banco de dados**
```
psql -U postgres -c "CREATE DATABASE empresa_poo;"

```

3. **Rodar o script de tabelas**
```
psql -U <usuario> -d empresa_poo -f src/sql/schema.sql
```

4. **Configurar a conexão**
Ajustar ```ConnectionFactory``` com URL, usuário e senha do PostgreSQL:
```
private static final String URL = "jdbc:postgresql://localhost:5432/empresa_poo";
private static final String USER = "seu_usuario";
private static final String PASSWORD = "sua_senha";
```

5. **Executar a aplicação**
Compile e rode a classe principal:
```
javac -d out $(find src -name "*.java")
java -cp out main.Aplicacao
```

6. **Fluxo de funcionamento**
* O programa pedirá o nome do arquivo CSV ou TXT de entrada.
* Lerá os funcionários e dependentes, inserindo-os no banco.
* Processará os cálculos de folha.
* Gerará um arquivo CSV de saída com resultados ```<Nome>;<CPF>;<DescontoINSS>;<DescontoIR>;<SalarioLiquido>```
---
## Melhorias Futuras

1. **Recálculo Automático da Folha de Pagamento**  
* Atualizar a lógica para que modificações em Funcionario (como mudança no salário ou dependentes) acionem automaticamente o recálculo de FolhaPagamento.

2. **Validação de Dados Aprimorada**
* Garantir formatação de CPF (11 dígitos válidos).
* Impedir datas de nascimento inválidas (futuras ou inconsistentes).
* Validar salários e campos numéricos antes da persistência.

3. **Testes Unitários**
Implementar testes para:
* Fórmulas de cálculo de INSS e IRRF.
* Validação de dependentes.

