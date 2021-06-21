/**
 *  @author Carlos Henrique Barreto Mareco
 */
public abstract class Funcionario {
    private String nome;
    private String cpf;
    private String senha;
    protected double salario;
    protected boolean atividade;
    
    public Funcionario(String nome, String cpf, String senha, double salario) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.salario = salario;
    }
    public String getNome() {
        return this.nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return this.cpf;
    }
    public String getCpfFormatado() {
        String cpf = this.getCpf();
        if(cpf.length() == 12){
            cpf = this.cpf.substring(0, 3)+"."+this.cpf.substring(3, 6)+"."+this.cpf.substring(6, 9)+"-"+this.cpf.substring(9,11);
        }
        return cpf;
    }
    public double getSalario() {
        return this.salario;
    }
    public boolean verificaSenha(String senha) {
        if(this.senha.equals(senha))
            return true;
        else
            return false;
    }
    public boolean trocarSenha(String senhaAntiga, String senhaNova) {
        if(verificaSenha(senhaAntiga)){
            if(senhaNova.equals("")){
                throw new IllegalArgumentException("A nova senha não pode ser vazia.");
            } else {
                this.senha = senhaNova;
                this.atividade = true;
            }
            return true;
        } else {
            return false;
        }
    }
    public boolean getAtividade() {
        return atividade;
    }
    public abstract void menu(Loja loja);
    public abstract String verContraCheque(Loja loja);
}