package zemian;

import org.junit.Test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Types;

/**
 * Created by xbbj5x6 on 8/13/2017.
 */
public class FuncCallTest {

    @Test
    public void test() throws Exception {
        String url = "jdbc:postgresql://localhost/employees?user=test";
        try (Connection conn = DriverManager.getConnection(url)) {
            CallableStatement upperProc = conn.prepareCall("{? = call upper( ? ) }");
            upperProc.registerOutParameter(1, Types.VARCHAR);
            upperProc.setString(2, "lowercase to uppercase");
            upperProc.execute();
            String upperCased = upperProc.getString(1);
            upperProc.close();
            System.out.println("RESULT " + upperCased);
        }
    }

    @Test
    public void test2() throws Exception {
        String url = "jdbc:postgresql://localhost/employees?user=test";
        try (Connection conn = DriverManager.getConnection(url)) {
            // We must be inside a transaction for cursors to work.
            conn.setAutoCommit(false);
            CallableStatement stmt = conn.prepareCall("{? = call my_get_emp(?)}");
            stmt.registerOutParameter(1, Types.OTHER);
            stmt.setInt(2, 10001);
            stmt.execute();
            ResultSet rs = (ResultSet)stmt.getObject(1);
            while (rs.next()) {
                System.out.println(rs.getObject(1));
            }
        }
    }
}
