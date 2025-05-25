
import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaFisicaDAO;
import cadastrobd.model.PessoaJuridica;
import cadastrobd.model.PessoaJuridicaDAO;
import java.io.IOException;
import java.util.List;


/**
 * @author Altair
 */
public class CadastroBDTeste {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException, Exception {
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
