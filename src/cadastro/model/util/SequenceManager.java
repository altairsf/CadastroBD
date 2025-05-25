package cadastro.model.util;

import java.sql.SQLException;
import java.sql.*;

/**
 * @author Altair
 */
public class SequenceManager {
    public static int getValue(String sequenceName) throws ClassNotFoundException, SQLException {
        int valor = -1;
        
        Connection c1 = ConectorBD.getConnection();
        PreparedStatement ps = c1.prepareStatement("SELECT NEXT VALUE FOR " + sequenceName);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            valor = rs.getInt(1);
        }
        rs.close();
        ps.close();
        c1.close();
        
        return valor;
    }
}
