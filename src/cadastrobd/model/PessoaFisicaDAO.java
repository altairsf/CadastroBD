package cadastrobd.model;

import java.sql.*;
import cadastro.model.util.ConectorBD;
import cadastro.model.util.SequenceManager;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Altair
 */
public class PessoaFisicaDAO {
    public PessoaFisica getPessoa(int id) throws Exception {
        Connection c1 = ConectorBD.getConnection ();
        String sql = "SELECT * FROM Pessoa p JOIN PessoaFisica pf ON p.idPessoa = pf.idPessoaFisica WHERE p.idPessoa = ?";
        
        PreparedStatement ps = ConectorBD.getPrepared(c1,sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        
        PessoaFisica pf = null;
        while (rs.next()) {
            pf = new PessoaFisica (
                rs.getInt("idPessoa"),
                rs.getString("nome_razaoSocial"),
                rs.getString("logradouro"),
                rs.getString("cidade"),
                rs.getString("estado"),
                rs.getString("telefone"),
                rs.getString("email"),
                rs.getString("cpf")   
            );  
        }
        
        rs.close();
        ps.close();
        c1.close();
        
        return pf;
    }
    
    public List<PessoaFisica> getPessoas() throws Exception {
        Connection c1 = ConectorBD.getConnection ();
        String sql = "SELECT * FROM Pessoa p JOIN PessoaFisica pf ON p.idPessoa = pf.idPessoaFisica";
        ResultSet rs = ConectorBD.getSelect(c1, sql);
        
        List<PessoaFisica> lista = new ArrayList<>();
        while (rs.next()) {
            PessoaFisica pf = new PessoaFisica(
                rs.getInt("idPessoa"),
                rs.getString("nome_razaoSocial"),
                rs.getString("logradouro"),
                rs.getString("cidade"),
                rs.getString("estado"),
                rs.getString("telefone"),
                rs.getString("email"),
                rs.getString("cpf")
            );
            lista.add(pf);
        }
        
        rs.close();
        c1.close();
        
        return lista;
    }
    
    public void incluir(PessoaFisica pf) throws Exception {
        Connection c1 = ConectorBD.getConnection();
        String sql1 = "INSERT INTO Pessoa (idPessoa, nome_razaoSocial, logradouro, "
                + "cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps1 = ConectorBD.getPrepared(c1, sql1);
        pf.setId(SequenceManager.getValue("Seq_Id_TablePessoa"));
        
        ps1.setInt(1, pf.getId());
        ps1.setString(2, pf.getNome());
        ps1.setString(3, pf.getLogradouro());
        ps1.setString(4, pf.getCidade());
        ps1.setString(5, pf.getEstado());
        ps1.setString(6, pf.getTelefone());
        ps1.setString(7, pf.getEmail());
        ps1.executeUpdate();
        
        String sql2 = "INSERT INTO PessoaFisica(idPessoaFisica, cpf) VALUES (?, ?)";
        PreparedStatement ps2 = ConectorBD.getPrepared(c1, sql2);
        ps2.setInt(1, pf.getId());
        ps2.setString(2, pf.getCpf());
        ps2.executeUpdate();

        ps1.close();
        ps2.close();
        c1.close();
    }
    
    public void alterar (PessoaFisica pf) throws Exception {
        Connection c1 = ConectorBD.getConnection ();
        
        String sql1 = "UPDATE Pessoa SET nome_razaoSocial = ?, logradouro = ?, cidade = ?,"
                + " estado = ?, telefone = ?, email = ? WHERE idPessoa = ?";
        PreparedStatement ps1 = ConectorBD.getPrepared(c1, sql1);
        ps1.setString(1, pf.getNome());
        ps1.setString(2, pf.getLogradouro());
        ps1.setString(3, pf.getCidade());
        ps1.setString(4, pf.getEstado());
        ps1.setString(5, pf.getTelefone());
        ps1.setString(6, pf.getEmail());
        ps1.setInt(7, pf.getId());
        ps1.executeUpdate();
        
        String sql2 = "UPDATE PessoaFisica SET cpf=? WHERE idPessoaFisica=?";
        PreparedStatement ps2 = ConectorBD.getPrepared(c1, sql2);
        ps2.setString(1, pf.getCpf());
        ps2.setInt(2, pf.getId());
        ps2.executeUpdate();

        ps1.close();
        ps2.close();
        c1.close();
    }
    
    public void excluir (int id) throws Exception {
        Connection c1 = ConectorBD.getConnection ();
        String sql1 = "DELETE FROM PessoaFisica WHERE idPessoaFisica=?";
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
