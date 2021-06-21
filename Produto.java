/**
 *  @author Carlos Henrique Barreto Mareco
 */
public class Produto {
    private String nome;
    private double preco;
    private String categoria;
    private String codigo;
    private int unidades;

    public Produto(String nome, double preco, String categoria, String codigo, int unidades) {
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
        this.codigo = codigo;
        this.unidades = unidades;
    }
    public String getNome() {
        return this.nome;
    }
    public double getPreco() {
        return this.preco;
    }
    public String getCategoria() {
        return this.categoria;
    }
    public String getCodigo() {
        return this.codigo;
    }
    public int getUnidades() {
        return this.unidades;
    }
    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }
    public void adicionarUnidades(int unidades) {
        this.unidades = getUnidades() + unidades;
    }
    public boolean removerUnidades(int unidades) throws IllegalArgumentException{
        boolean ok = false;
        if(unidades <= this.unidades){
            this.unidades -= unidades;
            ok = true;
        } else {
            throw new IllegalArgumentException("Quantidade de unidades inválida.");
        }
        return ok;
    }
    public String getInformacoes() {
        String info = "Código: "+this.getCodigo()+ "\nNome: "+this.getNome()+"\nCategoria: "+this.getCategoria();
        info += "\nPreço: R$"+this.getPreco()+"\nUnidades disponiveis: "+this.getUnidades();
        return info;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
}
