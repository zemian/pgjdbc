package tmp;

import org.junit.Test;
import org.postgresql.Driver;
import org.postgresql.PGProperty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Created by xbbj5x6 on 8/4/2017.
 */
public class DriverTest {

    @Test
    public void test() throws Exception {
        String url = "jdbc:postgresql://localhost/test";
        try(Connection conn = DriverManager.getConnection(url)) {
            System.out.println(conn);
        }
    }

    @Test
    public void testConnProps() throws Exception {
        String url = "jdbc:postgresql://localhost/test";
        Properties info = new Properties();
        info.setProperty("assumeMinServerVersion", "9.0");
        try(Connection conn = DriverManager.getConnection(url, info)) {
            System.out.println(conn);
        }
    }

    @Test
    public void testDriverConnect() throws Exception {
        Driver driver = new Driver();
        String url = "jdbc:postgresql://localhost/test";
        Properties info = new Properties();
        info.setProperty("assumeMinServerVersion", "9.0");
        try(Connection conn = driver.connect(url, info)) {
            System.out.println(conn);
        }
    }
}
