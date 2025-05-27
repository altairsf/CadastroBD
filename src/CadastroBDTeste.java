
import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaFisicaDAO;
import cadastrobd.model.PessoaJuridica;
import cadastrobd.model.PessoaJuridicaDAO;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


/**
 * @author Altair
 */
public class CadastroBDTeste {
    //O código abaixo é para o primeiro procedimento. Para usá-lo, descomentar.
   /* public static void main(String[] args) throws IOException, Exception {
        //a. Instanciar pessoa física no banco
        PessoaFisica pf = new PessoaFisica(0, "João Riacho", "Rua 11", "Riacho do Sul",
                "PA", "123456789", "joao@riacho.com", "11100022233");
        PessoaFisicaDAO pfDAO = new PessoaFisicaDAO ();
        pfDAO.incluir(pf);
        System.out.println("Pessoa Física incluída:");
        pf.exibir();
        
        // b. Alterar os dados da pessoa física no banco
        pf.setNome("João do Riacho");
        pf.setEmail("joaodoriacho@email.com");
        pfDAO.alterar(pf);
        System.out.println("Pessoa Física alterada:");
        pf.exibir();
            
        // c. Consultar todas as pessoas físicas
        List<PessoaFisica> pessoasFisicas = pfDAO.getPessoas();
        System.out.println("Lista de Pessoas Físicas:");
        for (PessoaFisica pessoa : pessoasFisicas) {
            pessoa.exibir();
            }
        
        // d. Excluir a pessoa física
        pfDAO.excluir(pf.getId());
        System.out.println("Pessoa Física excluída.");
        
        // e. Instanciar e incluir uma pessoa jurídica
        PessoaJuridica pj = new PessoaJuridica(0, "Empresa JJC ", "Av. 12", "Belém",
                "PA", "987654321", "contato@jjc.com", "12345678000199");
        PessoaJuridicaDAO pjDAO = new PessoaJuridicaDAO();
        pjDAO.incluir(pj);
        System.out.println("Pessoa Jurídica incluída:");
        pj.exibir();
        
        // f. Alterar os dados da pessoa jurídica
        pj.setNome("Empresa JJA Ltda.");
        pj.setEmail("contato@jjaltda.com");
        pjDAO.alterar(pj);
        System.out.println("Pessoa Jurídica alterada:");
        pj.exibir();
        
        // g. Consultar todas as pessoas jurídicas
        List<PessoaJuridica> pessoasJuridicas = pjDAO.getPessoas();
        System.out.println("Lista de Pessoas Jurídicas:");
            for (PessoaJuridica pessoa : pessoasJuridicas) {
                pessoa.exibir();
            }
            
        // h. Excluir a pessoa jurídica criada
        pjDAO.excluir(pj.getId());
        System.out.println("Pessoa Jurídica excluída.");    
    }
}
*/
// Fim do primeiro procedimento.Para usá-, descomentar o bloco anterior e comentar
// a partir da próxima linha
    public static void main(String[] args) throws IOException, Exception {
        Scanner scanner = new Scanner(System.in);
        
        PessoaFisicaDAO pfDAO = new PessoaFisicaDAO();
        PessoaJuridicaDAO pjDAO = new PessoaJuridicaDAO();
        
        int opcao;
    
        while (true) {
            System.out.println("===============================");
            System.out.println("1 - Incluir");
            System.out.println("2 - Alterar Pessoa");
            System.out.println("3 - Excluir Pessoa");
            System.out.println("4 - Buscar pelo ID");
            System.out.println("5 - Exibir todos");
            System.out.println("0 - Finalizar Programa");
            System.out.println("===============================");
            System.out.print("Escolha uma opção: ");
            
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            }
            catch (NumberFormatException e){
                System.out.println("Entrada inválida! Digite um número.");
                opcao = -1;
            }
            
            switch(opcao) {
                case 1 -> { //Incluir
                    System.out.print("F - Física | J - Jurídica\n");
                    String tipo = scanner.nextLine().toUpperCase();
                    if (tipo.equals("F")) {
                        System.out.print("Insira os dados...\n");
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("Logradouro: ");
                        String logradouro = scanner.nextLine();
                        System.out.print("Cidade: ");
                        String cidade = scanner.nextLine();
                        System.out.print("Estado: ");
                        String estado = scanner.nextLine();
                        System.out.print("Telefone: ");
                        String telefone = scanner.nextLine();
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        System.out.print("CPF: ");
                        String cpf = scanner.nextLine();
 
                        pfDAO.incluir(new PessoaFisica(0, nome, logradouro, cidade, estado,
                        telefone, email, cpf));
                        System.out.println("Pessoa Física incluída com sucesso.");
                    }
                    else if (tipo.equals("J")) {
                        System.out.print("Insira os dados...\n");
                        System.out.print("Razão Social: ");
                        String nome = scanner.nextLine();
                        System.out.print("Logradouro: ");
                        String logradouro = scanner.nextLine();
                        System.out.print("Cidade: ");
                        String cidade = scanner.nextLine();
                        System.out.print("Estado: ");
                        String estado = scanner.nextLine();
                        System.out.print("Telefone: ");
                        String telefone = scanner.nextLine();
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        System.out.print("CNPJ: ");
                        String cnpj = scanner.nextLine();

                        pjDAO.incluir(new PessoaJuridica(0, nome, logradouro, cidade, estado,
                            telefone, email, cnpj));
                        System.out.println("Pessoa Jurídica incluída com sucesso."); 
                    }                  
                }
                
            case 2 -> { //Alterar
                    System.out.print("F - Física | J - Jurídica\n");
                    String tipo = scanner.nextLine().toUpperCase();
                    System.out.print("Digite o ID da pessoa: ");
                    int idAlt = Integer.parseInt(scanner.nextLine());
            
                    if (tipo.equals("F")) {
                        PessoaFisica pf = pfDAO.getPessoa(idAlt);
                        if (pf != null) {
                            System.out.println("Dados atuais:");
                            pf.exibir();
            
                            System.out.print("Altere os dados...\n");
                            System.out.print("Novo Nome: ");
                            pf.setNome(scanner.nextLine());
                            System.out.print("Novo Logradouro: ");
                            pf.setLogradouro(scanner.nextLine());
                            System.out.print("Nova Cidade: ");
                            pf.setCidade(scanner.nextLine());
                            System.out.print("Novo Estado: ");
                            pf.setEstado(scanner.nextLine());
                            System.out.print("Novo Telefone: ");
                            pf.setTelefone(scanner.nextLine());
                            System.out.print("Novo email: ");
                            pf.setEmail(scanner.nextLine());
                            System.out.print("Novo CPF: ");
                            pf.setCpf(scanner.nextLine());
                           
                            pfDAO.alterar(pf);
                            System.out.println("Pessoa Física alterada.");
                        }   
                       else {
                        System.out.println("Pessoa Física não encontrada.");
                        }
                    }           
                    else if(tipo.equals("J")) {
                        PessoaJuridica pj = pjDAO.getPessoa(idAlt);
                        if (pj != null) {
                        System.out.println("Dados atuais:");
                        pj.exibir();
                            
                            System.out.print("Altere os dados...\n");
                            System.out.print("Nova Razão Social: ");
                            pj.setNome(scanner.nextLine());
                            System.out.print("Novo Logradouro: ");
                            pj.setLogradouro(scanner.nextLine());
                            System.out.print("Nova Cidade: ");
                            pj.setCidade(scanner.nextLine());
                            System.out.print("Novo Estado: ");
                            pj.setEstado(scanner.nextLine());
                            System.out.print("Novo Telefone: ");
                            pj.setTelefone(scanner.nextLine());
                            System.out.print("Novo email: ");
                            pj.setEmail(scanner.nextLine());
                            System.out.print("Novo CNPJ: ");
                            pj.setCnpj(scanner.nextLine());

                            pjDAO.alterar(pj);
                            System.out.println("Pessoa Jurídica alterada.");
                        } 
                        else {
                            System.out.println("Pessoa Jurídica não encontrada.");
                        }
                    }
                }
            
            case 3 -> { //Excluir
                    System.out.print("F - Física | J - Jurídica\n");
                    String tipo = scanner.nextLine().toUpperCase();
                    System.out.print("Digite o ID da pessoa: ");
                    int idExc = Integer.parseInt(scanner.nextLine());

                    if (tipo.equals("F")) {
                        pfDAO.excluir(idExc);
                        System.out.println("Pessoa Física excluída.");
                    } 
                    else if (tipo.equals("J")) {
                        pfDAO.excluir(idExc);
                        System.out.println("Pessoa Jurídica excluída.");
                    }
                }
            
            case 4 -> { //Buscar pelo ID
                    System.out.print("F - Física | J - Jurídica\n");
                    String tipo = scanner.nextLine().toUpperCase();
                    System.out.print("Digite o ID da pessoa: ");
                    int idBus = Integer.parseInt(scanner.nextLine());

                    if (tipo.equals("F")) {
                        PessoaFisica pf = pfDAO.getPessoa(idBus);
                        if (pf != null) {
                            pf.exibir();
                        } 
                        else {
                            System.out.println("Pessoa Física não encontrada.");
                        }
                    } 
                    else if (tipo.equals("J")) {
                        PessoaJuridica pj = pjDAO.getPessoa(idBus);
                        if (pj != null) {
                            pj.exibir();
                        } 
                        else {
                            System.out.println("Pessoa Jurídica não encontrada.");
                        }
                    }
                }
            
            case 5 -> { // Exibir todos
                    System.out.print("F - Física | J - Jurídica\n");
                    String tipo = scanner.nextLine().toUpperCase();

                    if (tipo.equals("F")) {
                        pfDAO.getPessoas().forEach(PessoaFisica::exibir);

                    } 
                    else if (tipo.equals("J")) {
                        pjDAO.getPessoas().forEach(PessoaJuridica::exibir); 
                    }
                }
            
            case 0 -> {
                System.out.println("Finalizando o programa...");
                scanner.close();
                System.out.println("Sistema finalizado");
                return;
            }
                default -> System.out.println("Opção inválida!");            
            }
        }
    }
}