/**
 *  @author Carlos Henrique Barreto Mareco
 */
import javax.swing.*;

public class Menu {
    //Menu principal de opções do programa.
    public static void principal(Loja loja){
        boolean sair = false;
        while(!sair){
            try{
                switch(JOptionPane.showInputDialog(null, "1 - Fazer login.\n2 - Sair.", "Menu inicial", -1)){
                    case "1":
                        loja.login();
                        break;
                    case "2":
                        sair = true;
                        break;
                    default:
                        Menu.opcaoInvalida();
                        break;
                }
            }catch(NullPointerException e){
                int op = JOptionPane.showConfirmDialog(null, "Deseja realmente sair?", "Fechar menu", 1);
                    if(op == 0){
                        sair = true;
                    }
            }catch(IllegalArgumentException e){
                JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage(), "Mensagem de erro", 0);
            }
        }
    }
    //Menu de opções do Vendedor.
    public static void funcionario(Vendedor f1, Loja loja){
        boolean logout = false;
        String menu = "Funcionario: " + f1.getNome() + ".\nLogado como vendedor.\n\n";
        menu += "1 - Realizar venda.\n2 - Ver contracheque.\n3 - Ver estoque.\n4 - Trocar senha.\n5 - Fazer logout.";
        while(!logout){
            try{
                switch(JOptionPane.showInputDialog(null, menu, "Menu do vendedor", -1)){
                    //Realizar venda.
                    case "1":
                        Produto produto = loja.procuraProduto(JOptionPane.showInputDialog(null, "Código do produto: ", "Realizar venda", -1));
                        if(produto != null) {
                            JOptionPane.showMessageDialog(null, produto.getInformacoes(), "Informações do estoque", -1);
                            int unidades = Integer.parseInt(JOptionPane.showInputDialog(null, "Quantidade de unidades: ", "Realizar venda", -1));
                            if(unidades <= produto.getUnidades()){
                                String senha = JOptionPane.showInputDialog(null, "Digite a sua senha: ", "Realizar venda", -1);
                                if(f1.verificaSenha(senha)){
                                    JOptionPane.showMessageDialog(null, loja.venderProduto(f1, produto, unidades, senha), "Recibo", 1);
                                } else {
                                    Menu.senhaErrada();
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "ERRO: Sem unidades suficiente!", "Mensagem de erro", 0);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "ERRO: Produto não cadastrado!", "Mensagem de erro", 0);
                        }
                        break;
                    //Exibir contracheque.
                    case "2":
                        Menu.exibirContracheque(f1, loja);
                        break;
                    case "3":
                        Menu.exibirEstoque(loja);
                        break;
                    //Trocar senha.
                    case "4":
                        Menu.trocarSenha(f1);
                        break;
                    //Fazer logout.
                    case "5":
                        int op = JOptionPane.showConfirmDialog(null, "Deseja continuar com o logout?", "Logout", 1);
                            if(op == 0)
                                logout = true;
                        break;
                    default:
                        Menu.opcaoInvalida();
                        break;
                }
            } catch(NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "ERRO: Use apenas números inteiros!", "Mensagem de erro", 0);
            } catch(IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage(), "Mensagem de erro", 0);
            } catch(NullPointerException e){
                int op = JOptionPane.showConfirmDialog(null, "Deseja realmente fazer logout?", "Logout", 1);
                    if(op == 0){
                        logout = true;
                    }
            }
        }
    }
    //Menu de opções do Gerente.
    public static void funcionario(Gerente f1, Loja loja){
        boolean logout = false;
        String menu = "Funcionario: " + f1.getNome() + "\nLogado como gerente\n\n";
        menu += "1 - Gerenciar produtos.\n2 - Gerenciar estoque.\n3 - Contratar vendedor.\n4 - Ver contracheque.\n5 - Trocar senha.\n6 - Fazer logout.";
        while(!logout){
            try{
                switch(JOptionPane.showInputDialog(null, menu, "Menu do gerente", -1)){
                    case "1":
                        while(Menu.gerenciarProduto(f1, loja)){};
                        break;
                    case "2":
                        while(Menu.gerenciarEstoque(f1, loja)){};
                        break;
                    case "3":
                        String nome = JOptionPane.showInputDialog(null, "Nome do funcionário:", "Contratar vendedor", -1);
                        String cpf = JOptionPane.showInputDialog(null, "CPF do funcionário:", "Contratar vendedor", -1);
                        String senha_temporaria = JOptionPane.showInputDialog(null, "Senha temporária do funcionário: ", "Contratar vendedor", -1);
                        String senha = JOptionPane.showInputDialog(null, "Digite sua senha: ", "Contratar vendedor", -1);
                        if(f1.verificaSenha(senha)){
                            loja.contratarVendedores(f1, nome, cpf, senha_temporaria, senha);
                            JOptionPane.showMessageDialog(null, "Vendedor adicionado ao sistema.", "Aviso", 1);
                        } else {
                            Menu.senhaErrada();
                        }
                        break;
                    case "4":
                        Menu.exibirContracheque(f1, loja);
                        break;
                    case "5":
                        Menu.trocarSenha(f1);
                        break;
                    case "6":
                        int op = JOptionPane.showConfirmDialog(null, "Deseja continuar com o logout?", "Logout", 1);
                            if(op == 0)
                                logout = true;
                        break;
                    default:
                        Menu.opcaoInvalida();
                        break;
                }
            } catch(NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "ERRO: Use apenas números inteiros!", "Mensagem de erro", 0);
            } catch(IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage(), "Mensagem de erro", 0);
            } catch(NullPointerException e){
                int op = JOptionPane.showConfirmDialog(null, "Deseja realmente fazer logout?", "Logout", 1);
                    if(op == 0){
                        logout = true;
                    }
            }
        }
    }
    //Janela de login dos funcionários.
    public static void login(Loja loja) {
        Funcionario usuario = loja.procuraFuncionario(JOptionPane.showInputDialog(null, "Digite o seu CPF: ", "Login", -1));
        if(usuario != null){
            if(usuario.atividade){
                if(usuario.verificaSenha(JOptionPane.showInputDialog(null, "Digite a senha", "Login", -1))){
                    usuario.menu(loja);
                } else {
                    Menu.senhaErrada();
                }
            } else {
                Menu.primeiroAcesso(usuario);
            }
        } else {
            JOptionPane.showMessageDialog(null, "ERRO: CPF não cadastrado!", "Mensagem de erro", 0);
        }
    }
    //Exibir janela com o contracheque.
    private static void exibirContracheque(Funcionario f1, Loja loja) {
        JOptionPane.showMessageDialog(null, f1.verContraCheque(loja), "Contracheque", -1);
    }
    //Exibir janela de erro de senha incorreta.
    private static void senhaErrada() {
        JOptionPane.showMessageDialog(null, "ERRO: Senha incorreta!", "Mensagem de erro", 0);
    }
    //Exibir janela de erro de opção inválida.
    private static void opcaoInvalida() {
        JOptionPane.showMessageDialog(null, "ERRO: Opção inválida!", "Mensagem de erro", 0);
    }
    //Exibir janela de trocar senha.
    private static void trocarSenha(Funcionario f1) {
        String senha = JOptionPane.showInputDialog(null, "Digite a senha atual", "Trocar senha", -1);
        if(f1.verificaSenha(senha)){
            if(f1.trocarSenha(senha, JOptionPane.showInputDialog(null, "Digite a nova senha", "Trocar senha", -1))){
                JOptionPane.showMessageDialog(null, "Senha alterada com sucesso!", "Aviso", 1);
            }
        } else {
            Menu.senhaErrada();
        }
    }
    //Gerenciamento de produto.
    private static boolean gerenciarProduto(Gerente f1, Loja loja) {
        boolean continuar = true;
        String opcoes = "1 - Adicionar produtos.\n2 - Adicionar categoria.\n3 - Alterar valor de produto.\n4 - Voltar ao menu anterior.";
        try{
            switch (JOptionPane.showInputDialog(null, opcoes, "Gerenciamento de produtos.", -1)) {
                case "1":
                    String categoria = loja.procuraCategoria(JOptionPane.showInputDialog(null, "Categoria do produto: ", "Adicionar produto", -1));
                    if(categoria != null){
                        String nome = JOptionPane.showInputDialog(null, "Nome do produto: ", "Adicionar produto", -1);
                        Double preco = Double.parseDouble(JOptionPane.showInputDialog(null, "Preço do produto:", "Adicionar produto", -1));
                        int unidades = Integer.parseInt(JOptionPane.showInputDialog(null, "Unidades do produto: ", "Adicionar produto", -1));
                        String senha = JOptionPane.showInputDialog(null, "Digite a sua senha: ", "Adicionar produto", -1);
                        if(f1.verificaSenha(senha)){
                            f1.adicionarProduto(loja, nome, preco, categoria, unidades, senha);
                            JOptionPane.showMessageDialog(null, "Produto adicionado!", "Aviso", 1);
                        } else {
                            Menu.senhaErrada();
                        }
                    } else {
                        throw new IllegalArgumentException("Essa categoria é inválida.");
                    }
                    break;
                case "2":
                    String nome = JOptionPane.showInputDialog(null, "Nome da categoria: ", "Adicionar categoria", -1);
                    String senha = JOptionPane.showInputDialog(null, "Digite a sua senha: ", "Adicionar categoria", -1);
                    if(f1.verificaSenha(senha)){
                        f1.adicionarCategoria(loja, nome, senha);
                        JOptionPane.showMessageDialog(null, "Categoria adicionada!", "Aviso", 1);
                    } else {
                        Menu.senhaErrada();
                    }
                    break;
                case "3":
                    Produto produto = loja.procuraProduto(JOptionPane.showInputDialog(null, "Código do produto: ", "Alterar preço produto", -1));
                    if(produto != null){
                        Double valor = Double.parseDouble(JOptionPane.showInputDialog(null, "Preço do produto:", "Alterar preço produto", -1));
                        String senha1 = JOptionPane.showInputDialog(null, "Digite a sua senha: ", "Adicionar categoria", -1);
                        if(f1.verificaSenha(senha1)){
                            f1.alterarValorProduto(loja, produto.getCodigo(), valor, senha1);
                            JOptionPane.showMessageDialog(null, "Preço do produto alterado!", "Aviso", 1);
                        }
                    } else {
                        throw new IllegalArgumentException("Produto não encontrado.");
                    }
                    break;
                case "4":
                    continuar = false;
                    break;
                default:
                    Menu.opcaoInvalida();
                    break;
            }
        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ERRO: Use apenas números válidos.", "Mensagem de erro", 0);
        } catch(IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage(), "Mensagem de erro", 0);
        } catch(NullPointerException e){
            int op = JOptionPane.showConfirmDialog(null, "Deseja realmente sair do menu?", "Sair do menu", 1);
                if(op == 0){
                    continuar = false;
                }
        } finally {
            return continuar;
        }
    }
    //Gerenciamento de estoque.
    private static boolean gerenciarEstoque(Gerente f1, Loja loja) {
        Boolean continuar = true;
        String opcoes = "1 - Adicionar unidades ao estoque.\n2 - Remover unidades do estoque.\n3 - Ver estoque\n4 - Voltar ao menu anterior.";
        try{
            switch(JOptionPane.showInputDialog(null, opcoes, "Gerenciamento de estoque.", -1)){
                case "1":
                    Produto produto1 = loja.procuraProduto(JOptionPane.showInputDialog(null, "Código do produto: ", "Adicionar ao estoque", -1));
                    if(produto1 != null){
                        int unidades = Integer.parseInt(JOptionPane.showInputDialog(null, "Unidades do produto:", "Adicionar ao estoque", -1));
                        String senha1 = JOptionPane.showInputDialog(null, "Digite a sua senha: ", "Adicionar ao estoque", -1);
                        if(f1.verificaSenha(senha1)){
                            f1.adicionarUnidadesEstoque(loja, produto1.getCodigo(), unidades, senha1);
                            JOptionPane.showMessageDialog(null, "Unidades adicionadas ao estoque!", "Aviso", 1);
                        }
                    } else {
                        throw new IllegalArgumentException("Produto não encontrado.");
                    }
                    break;
                case "2":
                    Produto produto2 = loja.procuraProduto(JOptionPane.showInputDialog(null, "Código do produto: ", "Remover do estoque", -1));
                    if(produto2 != null){
                        int unidades = Integer.parseInt(JOptionPane.showInputDialog(null, "Unidades do produto:", "Remover do estoque", -1));
                        String senha1 = JOptionPane.showInputDialog(null, "Digite a sua senha: ", "Remover do estoque", -1);
                        if(f1.verificaSenha(senha1)){
                            f1.removerUnidadesEstoque(loja, produto2.getCodigo(), unidades, senha1);
                            JOptionPane.showMessageDialog(null, "Unidades removidas do estoque!", "Aviso", 1);
                        }
                    } else {
                        throw new IllegalArgumentException("Produto não encontrado.");
                    }
                    break;
                case "3":
                    Menu.exibirEstoque(loja);
                    break;
                case "4":
                    continuar = false;
                    break;
                default:
                    Menu.opcaoInvalida();
                    break;
            }
        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ERRO: Use apenas números válidos.", "Mensagem de erro", 0);
        } catch(IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage(), "Mensagem de erro", 0);
        } catch(NullPointerException e){
            int op = JOptionPane.showConfirmDialog(null, "Deseja realmente sair do menu?", "Sair do menu", 1);
                if(op == 0){
                    continuar = false;
                }
        } finally {
            return continuar;
        }
    }
    //Mostrar o estoque
    private static void exibirEstoque(Loja loja) {
        int itens = loja.getQuantProdutos();
        int paginas = (int) Math.ceil(((double)itens)/5);
        if(itens != 0)
            for(int i = 0; i < paginas; i++){
                int de_pagina = 0 + 5*i;
                int ate_pagina = 5 + 5*i;
                String exibir_produtos = loja.exibirEstoque(de_pagina, ate_pagina);
                if(i == 0 && paginas > 1){
                    String[] botoes = {"Voltar ao menu",">>"};
                    int op = JOptionPane.showOptionDialog(null, "Página "+(i+1)+" de "+paginas+"\n\n"+exibir_produtos, "Estoque", 1, -1, null, botoes, null);
                    if(op != 1){
                        i = paginas;
                    }
                } else if(i == paginas-1 && paginas > 1){
                    String[] botoes = {"<<", "Voltar ao menu"};
                    int op = JOptionPane.showOptionDialog(null, "Página "+(i+1)+" de "+paginas+"\n\n"+exibir_produtos, "Estoque", 1, -1, null, botoes, null);
                    if(op == 0){
                        i -= 2;
                    }
                } else if (paginas > 1){
                    String[] botoes = {"<<", "Voltar ao menu", ">>"};
                    int op = JOptionPane.showOptionDialog(null, "Página "+(i+1)+" de "+paginas+"\n\n"+exibir_produtos, "Estoque", 1, -1, null, botoes, null);
                    if(op == 0){
                        i -= 2;
                    } else if (op != 2) {
                        i = paginas;
                    }
                } else {
                    String[] botoes = {"Voltar ao menu"};
                    JOptionPane.showOptionDialog(null, "Página "+(i+1)+" de "+paginas+"\n\n"+exibir_produtos, "Estoque", 1, -1, null, botoes, null);

                }
            }
        else {
            JOptionPane.showMessageDialog(null, "Estoque está vazio!" , "Estoque", 1);
        }
    }
    //Tela de primeiro acesso
    private static void primeiroAcesso(Funcionario f1) {
        if(!f1.getAtividade()){
            String senha = JOptionPane.showInputDialog(null, "Digite a senha temporária:", "Primeiro acesso", -1);
            if(f1.verificaSenha(senha)){
                if(f1.trocarSenha(senha, JOptionPane.showInputDialog(null, "Digite a nova senha:", "Primeiro acesso", -1))){
                    JOptionPane.showMessageDialog(null, "Senha alterada com sucesso!", "Aviso", 1);
                }
            } else {
                Menu.senhaErrada();
            }
        }
    }
}