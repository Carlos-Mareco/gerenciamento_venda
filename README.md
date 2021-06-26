# Gerenciamento de venda

Simulador de um gerenciador de vendas e gestão de funcionários.

## Compilação e execução (linux)

Usando o compilador java, contudo ele deve estar configurado no path do seu sistema.
Certifique-se de estar no diretório do programa.

```bash
mkdir bin && javac -d bin *.java
```
E para executar, ainda no mesmo diretório do programa, use :

```bash
java -cp .:bin Main
```
## Entendendo o código

### [Main.java](./Main.java)

É utilizado para inicializar o programa, além de instanciar um objeto da classe [Loja](./Loja.java) para poder utilizar
as funções da loja.

### [Loja.java](./Loja.java)

É onde fica todas as ações da interface do funcionário da loja.

### [Estoque.java](./Estoque.java) e [Produto.java](./Produto.java)

A classe [Estoque](./Estoque.java) é responsável por gerenciar os produtos da classe [Produto](./Produto.java).

### [Funcionario.java](./Funcionario.java), [Vendedor.java](./Vendedor.java) e [Gerente.java](./Gerente.java)

A classe abstrata [Funcionario](./Funcionario.java) é utilizada para fornecer os métodos que todos os funcionários da loja devem ter. 
Enquanto que as classes [Vendedor](./Vendedor.java) e [Gerente](./Gerente.java) são classes filhas de [Funcionario](./Funcionario.java), cada classe filha é responsável pelos métodos de um tipo de funcionário.

### [Menu.java](./Menu.java)

É reponsável pela GUI (Graphical User Interface, ou seja, Interface Gráfica do Usuário) da loja.

![Menu_Inicial](/screenshots/menu_inicial.png)

![Menu_Vendedor](/screenshots/menu_vendedor.png)

![Menu_Gerente](/screenshots/menu_gerente.png)

## Ambiente de criação e teste

**Informação do OS:** Linux Mint 20.1 Cinnamon.
**Kernel do Linux:** 5.4.0-77-generic.  
**Processador:** Intel© Core™ i7-7500U CPU @ 2.70GHz × 2.  
**Memória:** 7.7 GiB.  
**Editor/IDE:** [Visual Studio Code 1.57.1](https://code.visualstudio.com/).                                     
**Java:** [Oracle Java SE JDK 11.0.11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).

## Considerações finais

Este programa faz parte de um dos componentes avaliativos da disciplina de Programação III da Universidade Federal do Amapá do curso de Ciência da Computação.

Este programa não tem intuito de uso comercial.