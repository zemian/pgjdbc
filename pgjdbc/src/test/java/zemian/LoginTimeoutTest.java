package zemian;

import org.junit.Test;
import org.postgresql.Driver;
import org.postgresql.PGProperty;
import org.postgresql.test.TestUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Issue: https://github.com/pgjdbc/pgjdbc/issues/879
 * PR: https://github.com/pgjdbc/pgjdbc/pull/900
 *
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

    @Test
    public void testDriverManager3ConfusingCase() throws Exception {
        DriverManager.setLoginTimeout(5);
        String url = "jdbc:postgresql://localhost/test";
        Properties info = new Properties();
        info.setProperty("loginTimeout", "13");
        // Due to way info props is loaded as Properties#defauts, the PGProperty.isPresent will return false!
        // Hence, this test case will use DriverManager.loginTimeout value instead of info props!
        // See testPGPropertyIsPresent()

        Driver driver = new Driver();
        try(Connection conn = driver.connect(url, info)) {
            System.out.println(conn);
        }
    }

    @Test
    public void testPGPropertyIsPresent() throws Exception {
        // PGProperty.isPresent can be very misleading!!!
        // The properties info set as object will only be use as Properties#defaults, which is not the same
        // as populating the properties!
        Properties info = new Properties();
        info.setProperty("loginTimeout", "11");
        System.out.println(PGProperty.LOGIN_TIMEOUT.isPresent(info));
        System.out.println(PGProperty.LOGIN_TIMEOUT.get(info));

        Properties info2 = new Properties(info);
        System.out.println(PGProperty.LOGIN_TIMEOUT.isPresent(info2));
        System.out.println(PGProperty.LOGIN_TIMEOUT.get(info2));
    }
}
