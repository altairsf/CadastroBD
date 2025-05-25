//Conexão com o Banco de Dados
package cadastro.model.util;
import java.sql.*;
/**
 *
 * @author Altair
 */
public class ConectorBD {
    public static Connection getConnection () throws SQLException, ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=loja;encrypt=true;trustServerCertificate=true", "loja", "loja");
    }
    
    public static PreparedStatement getPrepared (Connection c1, String sql) throws SQLException {
        return c1.prepareStatement(sql);
    }
    
    public static ResultSet getSelect (Connection c1, String sql) throws SQLException {
        Statement st = c1.createStatement();
        return st.executeQuery(sql);
    }
    
    public void close(Connection c1) throws Exception {
        c1.close();
    }
    
    public void close(Statement st) throws Exception {
        st.getConnection().close();
    }
    
    public void close(ResultSet rs) throws Exception {
        rs.close();
    }
}