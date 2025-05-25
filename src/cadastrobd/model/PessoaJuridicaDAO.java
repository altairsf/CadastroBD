package cadastrobd.model;
import java.sql.*;
import cadastro.model.util.ConectorBD;
import cadastro.model.util.SequenceManager;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Altair
 */
public class PessoaJuridicaDAO {
    public PessoaJuridica getPessoa(int id) throws Exception {
        Connection c1 = ConectorBD.getConnection ();
        String sql = "SELECT * FROM Pessoa p JOIN PessoaJuridica pj ON p.idPessoa = pj.idPessoaJuridica WHERE p.idPessoa = ?";
        
        PreparedStatement ps = ConectorBD.getPrepared(c1,sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        
        PessoaJuridica pj = null;
        while (rs.next()) {
            pj = new PessoaJuridica (
                rs.getInt("idPessoa"),
                rs.getString("nome_razaoSocial"),
                rs.getString("logradouro"),
                rs.getString("cidade"),
                rs.getString("estado"),
                rs.getString("telefone"),
                rs.getString("email"),
                rs.getString("cnpj")   
            );  
        }
        
        rs.close();
        ps.close();
        c1.close();
        
        return pj;
    }
    
    public List<PessoaJuridica> getPessoas() throws Exception {
        Connection c1 = ConectorBD.getConnection ();
        String sql = "SELECT * FROM Pessoa p JOIN PessoaJuridica pj ON p.idPessoa = pj.idPessoaJuridica";
        ResultSet rs = ConectorBD.getSelect(c1, sql);
        
        List<PessoaJuridica> lista = new ArrayList<>();
        while (rs.next()) {
            PessoaJuridica pj = new PessoaJuridica(
                rs.getInt("idPessoa"),
                rs.getString("nome_razaoSocial"),
                rs.getString("logradouro"),
                rs.getString("cidade"),
                rs.getString("estado"),
                rs.getString("telefone"),
                rs.getString("email"),
                rs.getString("cnpj")
            );
            lista.add(pj);
        }
        
        rs.close();
        c1.close();
        
        return lista;
    }
    
    public void incluir(PessoaJuridica pj) throws Exception {
        Connection c1 = ConectorBD.getConnection();
        String sql1 = "INSERT INTO Pessoa (idPessoa, nome_razaoSocial, logradouro, "
                + "cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps1 = ConectorBD.getPrepared(c1, sql1);
        pj.setId(SequenceManager.getValue("Seq_Id_TablePessoa"));
        
        
        ps1.setInt(1, pj.getId());
        ps1.setString(2, pj.getNome());
        ps1.setString(3, pj.getLogradouro());
        ps1.setString(4, pj.getCidade());
        ps1.setString(5, pj.getEstado());
        ps1.setString(6, pj.getTelefone());
        ps1.setString(7, pj.getEmail());
        ps1.executeUpdate();
        
        String sql2 = "INSERT INTO PessoaJuridica(idPessoaJuridica, cnpj) VALUES (?, ?)";
        PreparedStatement ps2 = ConectorBD.getPrepared(c1, sql2);
        ps2.setInt(1, pj.getId());
        ps2.setString(2, pj.getCnpj());
        ps2.executeUpdate();

        ps1.close();
        ps2.close();
        c1.close();
    }
    
    public void alterar (PessoaJuridica pj) throws Exception {
        Connection c1 = ConectorBD.getConnection ();
        
         String sql1 = "UPDATE Pessoa SET nome_razaoSocial=?, logradouro=?, cidade=?, estado=?,"
                 + " telefone=?, email=? WHERE idPessoa=?";
        PreparedStatement ps1 = ConectorBD.getPrepared(c1, sql1);
        ps1.setString(1, pj.getNome());
        ps1.setString(2, pj.getLogradouro());
        ps1.setString(3, pj.getCidade());
        ps1.setString(4, pj.getEstado());
        ps1.setString(5, pj.getTelefone());
        ps1.setString(6, pj.getEmail());
        ps1.setInt(7, pj.getId());
        ps1.executeUpdate();
        
        String sql2 = "UPDATE PessoaJuridica SET cnpj=? WHERE idPessoaJuridica=?";
        PreparedStatement ps2 = ConectorBD.getPrepared(c1, sql2);
        ps2.setString(1, pj.getCnpj());
        ps2.setInt(2, pj.getId());
        ps2.executeUpdate();

        ps1.close();
        ps2.close();
        c1.close();
    }
    
    public void excluir (int id) throws Exception {
        Connection c1 = ConectorBD.getConnection ();
        String sql1 = "DELETE FROM PessoaJuridica WHERE idPessoaJuridica=?";
        PreparedStatement ps1 = ConectorBD.getPrepared(c1, sql1);
        ps1.setInt(1, id);
        ps1.executeUpdate();
        
        String sql2 = "DELETE FROM Pessoa WHERE idPessoa=?";
        PreparedStatement ps2 = ConectorBD.getPrepared(c1, sql2);
        ps2.setInt(1, id);
        ps2.executeUpdate();
        
        ps2.close();
        ps1.close();
        c1.close();
    }
}
