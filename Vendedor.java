/**
 *  @author Carlos Henrique Barreto Mareco
 */
public class Vendedor extends Funcionario {
    private double comissao;

    public Vendedor(String nome, String cpf,String senha) {
        super(nome, cpf, senha, 1600);
        super.atividade = false;
    }
    public double getComissao() {
        return Math.round(this.comissao*100.0)/100.0;
    }
    public boolean venderProduto(Produto produto, int unidades) {
        if(produto != null && unidades != 0){
            this.comissao += produto.getPreco()*0.05*unidades;
            return true;
        } else {
            return false;
        }
    }
    public void menu(Loja loja) {
        Menu.funcionario(this, loja);
    }
    public String verContraCheque(Loja loja) {
        String contracheque = loja.getNome() + ".\nCNPJ: " + loja.getCnpjFormatado() + ".\n\nNome: " + super.getNome();
        contracheque += ".\nCPF: " + super.getCpfFormatado() + ".\n\nSalário base: R$ " + super.getSalario();
        contracheque += ".\nComissão: R$ " + this.getComissao() + ".\n\nSalário total: R$ " + (super.getSalario() + this.getComissao());
        return contracheque;
    }
}