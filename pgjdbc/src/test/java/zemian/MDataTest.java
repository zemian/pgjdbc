package zemian;

import org.junit.Test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Types;

import static org.junit.Assert.assertThat;

/**
 * DatabaseMetaData (mdata) tests
 * Created by xbbj5x6 on 8/13/2017.
 */
public class MDataTest {
    @Test
    public void test() throws Exception {
        String url = "jdbc:postgresql://localhost/test?user=test";
        try (Connection conn = DriverManager.getConnection(url)) {
            DatabaseMetaData mdata = conn.getMetaData();
            System.out.println("getProcedureTerm " + mdata.getProcedureTerm());
            System.out.println("getSearchStringEscape " + mdata.getSearchStringEscape());
            System.out.println("getTimeDateFunctions " + mdata.getTimeDateFunctions());
        }
    }
}
