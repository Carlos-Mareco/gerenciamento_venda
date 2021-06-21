/**
 *  @author Carlos Henrique Barreto Mareco
 */
public class Estoque {
    private Produto[] produtos;
    private int quant_produtos;
    private String[] categorias;
    private int quant_categorias;
    private String cont_codigo;

    public Estoque(int tamanho_inicial, int tam_cod) {
        produtos = new Produto[tamanho_inicial];
        this.quant_categorias = 0;
        this.quant_produtos = 0;
        this.categorias = new String[20];
        this.cont_codigo = "";
        for(int i = 0; i < tam_cod; i++){
            this.cont_codigo += "0";
        }
    }
    public Estoque() {
        this(20, 4);
    }
    public int getQuantProdutos() {
        return quant_produtos;
    }
    public int getQuantCategorias() {
        return quant_categorias;
    }
    public void adicionarCategoria(String categoria) {
        if(categoria.equals("")){
            throw new IllegalArgumentException("Nome da categoria é inválido.");
        } else {
            if(this.procuraCategoria(categoria) == null){
                if(this.categorias.length > quant_categorias){
                    this.categorias[quant_categorias] = categoria;
                } else {
                    String[] novoTamanho = new String[quant_categorias+1];
                    for(int i = 0; i < quant_categorias; i++){
                        novoTamanho[i] = this.categorias[i];
                    }
                    this.categorias = novoTamanho;
                    this.categorias[quant_categorias] = categoria;
                }
                this.quant_categorias++;
            } else {
                throw new IllegalArgumentException("Essa categoria já existe.");
            }
        }
    }
    public String procuraCategoria(String categoria) {
        String encontrado = null;
        for(int i = 0; i < quant_categorias; i++){
            if(this.categorias[i].equalsIgnoreCase(categoria)){
                encontrado = this.categorias[i];
            }
        }
        return encontrado;
    }
    public String gerarCodigo() {
        int codigo = Integer.parseInt(this.cont_codigo) + 1;
        int tam = this.cont_codigo.length()-1-((int)Math.log10(codigo));
        this.cont_codigo = "";
        for(int i = 0; i < tam; i++){
            cont_codigo += "0";
        }
        this.cont_codigo += "" + codigo;
        return this.cont_codigo;
    }
    public Produto procuraProduto(String codigo) {
        Produto encontrado = null;
        for(int i = 0; i < this.quant_produtos; i++){
            if(this.produtos[i].getCodigo().equals(codigo)){
                encontrado = this.produtos[i];
            }
        }
        return encontrado;
    }
    public int quantProdutosCategoria(String categoria) {
        int encontrados = 0;
        for(int i = 0; i < this.quant_produtos; i++){
            if(produtos[i].getCategoria().equalsIgnoreCase(categoria)){
                encontrados++;
            }
        }
        return encontrados;
    }
    public void expandirEstoque(int quantidade) {
        Produto[] novoTamanho = new Produto[produtos.length+quantidade];
        for(int i = 0; i < quant_produtos; i++) {
            novoTamanho[i] = produtos[i];
        }
        produtos = novoTamanho;
    }
    public void adicionarProduto(Produto novo) {
        if(produtos.length <= quant_produtos){
            this.expandirEstoque(1);
        }
        produtos[quant_produtos] = novo;
        quant_produtos++;
    }
    public void novoProduto(String nome, double preco, String categoria, int unidades) {
        if(procuraCategoria(categoria ) != null){
            if(unidades > -1) {
                this.adicionarProduto(new Produto(nome, preco, categoria, this.gerarCodigo(), unidades));
            } else {
                throw new IllegalArgumentException("Quantidade de unidades inválida.");
            }
        } else {
            throw new IllegalArgumentException("Categoria inválida.");
        }
    }
    public void adicionarUnidades(String codigo, int unidades) {
        if(unidades > 0){
            Produto produto = procuraProduto(codigo);
            if(produto != null){
                produto.adicionarUnidades(unidades);
            } else {
                throw new IllegalArgumentException("Produto não encontrado.");
            }
        } else {
            throw new IllegalArgumentException("Quantidade de unidades inválida.");
        }
    }
    public void removerUnidades(String codigo, int unidades) {
        if(unidades > 0){
            Produto produto = procuraProduto(codigo);
            if(produto != null){
                produto.removerUnidades(unidades);
            } else {
                throw new IllegalArgumentException("Produto não encontrado.");
            }
        } else {
            throw new IllegalArgumentException("Quantidade de unidades inválida.");
        }
    }
    public void alterarValorProduto(String codigo, double valor) {
        if(valor > 0){
            Produto produto = procuraProduto(codigo);
            if(produto != null){
                produto.setPreco(valor);;
            } else {
                throw new IllegalArgumentException("Produto não encontrado.");
            }
        }
    }
    public String exibirEstoque(int de_pagina, int ate_pagina) {
        String retornar = "";
        for(int j = de_pagina; j < ate_pagina && j > -1 && j < this.quant_produtos; j++) {
            retornar += this.produtos[j].getInformacoes() + "\n\n";
        }
        return retornar;
    }
    public Produto getProduto(int indice) {
        return this.produtos[indice];
    }
}
