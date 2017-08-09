package tmp;

import org.junit.Test;
import org.postgresql.Driver;
import org.postgresql.PGProperty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Properties;

/**
 * Created by xbbj5x6 on 8/4/2017.
 */
public class DriverTest {
    @Test
    public void test() throws Exception {
        String url = "jdbc:postgresql://localhost/test?user=test";
        try(Connection conn = DriverManager.getConnection(url)) {
            System.out.println(conn);
        }
    }

    @Test
    public void testPassword() throws Exception {
        String url = "jdbc:postgresql://localhost/test?user=test&password=password";
        try(Connection conn = DriverManager.getConnection(url)) {
            System.out.println(conn);
        }
    }

    @Test
    public void testConnProps() throws Exception {
        String url = "jdbc:postgresql://localhost/test";
        Properties info = new Properties();
        info.setProperty("user", "test");
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
        info.setProperty("user", "test");
        info.setProperty("assumeMinServerVersion", "9.0");
        try(Connection conn = driver.connect(url, info)) {
            System.out.println(conn);
        }
    }

    @Test
    public void testDriverPropertyInfo() throws Exception {
        Driver driver = new Driver();
        String url = "jdbc:postgresql://localhost/test";
        Properties info = new Properties();
        DriverPropertyInfo[] dinfoArray = driver.getPropertyInfo(url, info);
        Arrays.sort(dinfoArray, new Comparator<DriverPropertyInfo>() {
            @Override
            public int compare(DriverPropertyInfo o1, DriverPropertyInfo o2) {
                return o1.name.compareTo(o2.name);
            }
        });
        for (DriverPropertyInfo dinfo : dinfoArray) {
            System.out.println(dinfo.name + "=" + dinfo.value + " required=" + dinfo.required +
                    (dinfo.choices != null ? " choices=" + Arrays.asList(dinfo.choices) : ""));
            System.out.println("  description=" + dinfo.description);
        }
        System.out.println("Count " + dinfoArray.length);
    }
}
