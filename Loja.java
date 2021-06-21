/**
 *  @author Carlos Henrique Barreto Mareco
 */
public class Loja {
    private String nome;
    private String cnpj;
    private Funcionario[] funcionarios;
    private int quant_funcionarios;
    private Estoque estoque;

    public Loja(String nome, String cnpj, int quant_f) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.quant_funcionarios = 0;
        this.funcionarios = new Funcionario[quant_f];
        this.estoque = new Estoque();
    }
    public Loja(String nome, String cnpj) {
        this(nome, cnpj, 10);
    }
    public Loja(String nome, String cnpj, int quant_f, int quant_produtos, int tam_cod) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.quant_funcionarios = 0;
        this.funcionarios = new Funcionario[quant_f];
        this.estoque = new Estoque(quant_produtos, tam_cod);
    }
    public String getNome() {
        if(this.nome.equals("")){
            return "<LOJA SEM NOME>";
        } else {
            return this.nome;
        }
    }
    public String getCnpj() {
        if(this.nome.equals("")){
            return "<LOJA SEM CNPJ>";
        } else {
            return this.cnpj;
        }
    }
    public String getCnpjFormatado() {
        String cnpj = this.cnpj;
        if(cnpj.length() == 14){
            cnpj = this.cnpj.substring(0, 2)+"."+this.cnpj.substring(2, 5)+"."+this.cnpj.substring(5, 8)+"/";
            cnpj += this.cnpj.substring(8, 12)+"-"+this.cnpj.substring(12, 14);
        } else {
            cnpj = this.getCnpj();
        }
        return cnpj;
    }
    public void login() {
        Menu.login(this);
    }
    public Funcionario procuraFuncionario(String cpf) {
        Funcionario encontrado = null;

        for(int i = 0; i < this.quant_funcionarios; i++){
            if(this.funcionarios[i].getCpf().equals(cpf)){
                encontrado = this.funcionarios[i];
            }
        }
        return encontrado;
    }
    private void adicionarFuncionario(Funcionario novo) {
        if(this.procuraFuncionario(novo.getCpf()) == null) {
            if(this.funcionarios.length > this.quant_funcionarios) {
                this.funcionarios[this.quant_funcionarios] = novo;
            } else {
                maisFuncionarios(1);
                this.funcionarios[this.quant_funcionarios] = novo;
            }
            this.quant_funcionarios++;
        } else {
            throw new IllegalArgumentException("Funcionário já cadastrado.");
        }
    }
    public void maisFuncionarios(int quantidade) {
        Funcionario[] novoTamanho = new Funcionario[this.funcionarios.length + quantidade];
        for (int i = 0; i < this.funcionarios.length; i++){
            novoTamanho[i] = this.funcionarios[i];
        }
        this.funcionarios = novoTamanho;
    }
    public void novoFuncionario(String nome, String cpf, String senha, int tipoFuncionario) {
        if(nome.equals("")){
            throw new IllegalArgumentException("O nome do funcionário não pode ser vazio.");
        } else {
            if(cpf.length() == 12) {
                if(tipoFuncionario == 1){
                    adicionarFuncionario(new Vendedor(nome, cpf, senha));
                } else if(tipoFuncionario == 2){
                    adicionarFuncionario(new Gerente(nome, cpf, senha));
                } else {
                    throw new IllegalArgumentException("Tipo de funcionário inválido.");
                }
            } else {
                throw new IllegalArgumentException("CPF inválido.");
            }
        }
    }
    public void novoProduto(String nome, double preco, String categoria, int unidades) {
        this.estoque.novoProduto(nome, preco, categoria, unidades);
    }
    public Produto procuraProduto(String codigo) {
        return this.estoque.procuraProduto(codigo);
    }
    public String procuraCategoria(String categoria) {
        return estoque.procuraCategoria(categoria);
    }
    public String venderProduto(Vendedor f1, Produto produto, int unidades, String senha) {
        if(f1.verificaSenha(senha)) {
            if(produto.removerUnidades(unidades)) {
                f1.venderProduto(produto, unidades);
                return "Produto: " + produto.getNome() + "\nUnidades: " + unidades + "\nPreço unitário: R$" + produto.getPreco() + "\nPreço total: R$" + produto.getPreco()*unidades;
            } else {
                return "ERRO: Não foi possível vender o produto!";
            }
        } else {
            return "ERRO: Senha incorreta!";
        }
    }
    public void adicionarProduto(Gerente f1, String nome, double preco, String categoria, int unidades, String senha) {
        if(f1.verificaSenha(senha)) {
            if(nome.equals("")){
                throw new IllegalArgumentException("O nome do produto não pode ser vazio.");
            } else {
                if(this.procuraCategoria(categoria) != null){
                    if(unidades > -1) {
                        this.novoProduto(nome, preco, categoria, unidades);
                    } else {
                        throw new IllegalArgumentException("Quantidade de unidades inválida.");
                    }
                } else {
                    throw new IllegalArgumentException("Categoria inválida.");
                }
            }
        } else {
            throw new IllegalArgumentException("Senha incorreta.");
        }
    }
    public void adicionarCategoria(Gerente f1, String categoria, String senha) {
        if(f1.verificaSenha(senha)) {
            this.estoque.adicionarCategoria(categoria);
        } else {
            throw new IllegalArgumentException("Senha incorreta.");
        }
    }
    public void alterarValorProduto(Gerente f1, String codigo, double valor, String senha) {
        if(f1.verificaSenha(senha)) {
            Produto produto = procuraProduto(codigo);
            if(produto != null){
                this.estoque.alterarValorProduto(codigo, valor);
            } else {
                throw new IllegalArgumentException("Produto não encontrado.");
            }
        } else {
            throw new IllegalArgumentException("Senha incorreta.");
        }
    }
    public void adicionarUnidadesEstoque(Gerente f1, String codigo, int unidades, String senha) {
        if(f1.verificaSenha(senha)) {
            Produto produto = procuraProduto(codigo);
            if(produto != null){
                this.estoque.adicionarUnidades(codigo, unidades);
            } else {
                throw new IllegalArgumentException("Produto não encontrado.");
            }
        } else {
            throw new IllegalArgumentException("Senha incorreta.");
        }
    }
    public int getQuantProdutos(){
        return estoque.getQuantProdutos();
    }
    public void removerUnidadesEstoque(Gerente f1, String codigo, int unidades, String senha) {
        if(f1.verificaSenha(senha)) {
            Produto produto = procuraProduto(codigo);
            if(produto != null){
                this.estoque.removerUnidades(codigo, unidades);
            } else {
                throw new IllegalArgumentException("Produto não encontrado.");
            }
        } else {
            throw new IllegalArgumentException("Senha incorreta.");
        }
    }
    public String exibirEstoque(int de_pagina, int ate_pagina){
        String retornar = "";
        for(int j = de_pagina; j < ate_pagina && j > -1 && j < this.estoque.getQuantProdutos(); j++) {
            retornar += this.estoque.getProduto(j).getInformacoes() + "\n\n";
        }
        return retornar;
    }
    public void contratarVendedores(Gerente f1, String nome, String cpf, String senha_temporaria, String senha) {
        if(f1.verificaSenha(senha)){
            this.novoFuncionario(nome, cpf, senha_temporaria, 1);
        } else {
            throw new IllegalArgumentException("Senha incorreta.");
        }
    }
}
