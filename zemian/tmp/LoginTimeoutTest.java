package tmp;

import org.junit.Test;
import org.postgresql.Driver;
import org.postgresql.test.TestUtil;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by xbbj5x6 on 8/4/2017.
 */
public class LoginTimeoutTest {

    @Test
    public void testConn() throws Exception {
        String url = "jdbc:postgresql://localhost/test";
        Driver driver = new Driver();
        try(Connection conn = driver.connect(url, null)) {
            System.out.println(conn);
        }
    }

    @Test
    public void testConn2() throws Exception {
        String url = "jdbc:postgresql://localhost/test?loginTimeout=2.5XXX";
        Driver driver = new Driver();
        try(Connection conn = driver.connect(url, null)) {
            System.out.println(conn);
        }
    }

    @Test
    public void testDriverManager() throws Exception {
        DriverManager.setLoginTimeout(5);
        String url = "jdbc:postgresql://localhost/test";

        Driver driver = new Driver();
        try(Connection conn = driver.connect(url, null)) {
            System.out.println(conn);
        }
    }

    @Test
    public void testDriverManager2() throws Exception {
        DriverManager.setLoginTimeout(5);
        String url = "jdbc:postgresql://localhost/test?loginTimeout=0";
        Driver driver = new Driver();
        try(Connection conn = driver.connect(url, null)) {
            System.out.println(conn);
        }
    }
}
