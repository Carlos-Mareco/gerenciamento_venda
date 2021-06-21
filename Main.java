/**
 *  @author Carlos Henrique Barreto Mareco
 */
public class Main {
    public static void main(String[] args) {
        Loja loja = new Loja("Loja genérica", "12345678000101");

        loja.novoFuncionario("Carlos Henrique", "012345678910", "123", 2);

        Menu.principal(loja);
        
    }
}