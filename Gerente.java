/**
 *  @author Carlos Henrique Barreto Mareco
 */
public class Gerente extends Funcionario{
    
    public Gerente(String nome, String cpf,String senha) {
        super(nome, cpf, senha, 3100);
        super.atividade = true;
    }
    public void menu(Loja loja) {
        Menu.funcionario(this, loja);
    }
    public String verContraCheque(Loja loja) {
        String contracheque = loja.getNome() + ".\nCNPJ: " + loja.getCnpjFormatado() + ".\n\nNome: " + super.getNome();
        contracheque += ".\nCPF: " + super.getCpfFormatado() + ".\n\nSalário base: R$ " + super.getSalario();
        contracheque += ".\n\nSalário total: R$ " + super.getSalario();
        return contracheque;
    }
    public void adicionarProduto(Loja loja, String nome, double preco, String categoria, int unidades, String senha) {
        loja.adicionarProduto(this, nome, preco, categoria, unidades, senha);
    }
    public void adicionarCategoria(Loja loja, String nome, String senha) {
        loja.adicionarCategoria(this, nome, senha);
    }
    public void alterarValorProduto(Loja loja, String codigo, double valor, String senha) {
        loja.alterarValorProduto(this, codigo, valor, senha);
    }
    public void adicionarUnidadesEstoque(Loja loja, String codigo, int unidades, String senha) {
        loja.adicionarUnidadesEstoque(this, codigo, unidades, senha);
    }
    public void removerUnidadesEstoque(Loja loja, String codigo, int unidades, String senha) {
        loja.removerUnidadesEstoque(this, codigo, unidades, senha);
    }
    public void contratarVendedor(Loja loja, String nome, String cpf, String senha_temporaria, String senha) {
        loja.contratarVendedores(this, nome, cpf, senha_temporaria, senha);
    }
}