package zemian;

import org.junit.Test;
import org.postgresql.PGResultSetMetaData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by xbbj5x6 on 8/9/2017.
 */
public class ResultSetDataTest {
    @Test
    public void test() throws Exception {
        String url = "jdbc:postgresql://localhost/test?user=test";
        try (Connection conn = DriverManager.getConnection(url)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select 1+1, a.name, b.name, b.link from menu a, menu_item b");
            System.out.println("Table " + rs.getMetaData().getTableName(1) + "<<<END");
            System.out.println("Table " + rs.getMetaData().getTableName(2));
            System.out.println("Table " + rs.getMetaData().getTableName(3));
            System.out.println("Table " + rs.getMetaData().getTableName(4));

            System.out.println("Catalog " + rs.getMetaData().getCatalogName(4) + "<<<END");

            System.out.println("Format " + ((PGResultSetMetaData)rs.getMetaData()).getFormat(1));
            System.out.println("Format " + ((PGResultSetMetaData)rs.getMetaData()).getFormat(2));
            rs.close();
            stmt.close();
        }
    }

    @Test
    public void test2() throws Exception {
        String url = "jdbc:postgresql://localhost/test?user=test";
        try (Connection conn = DriverManager.getConnection(url)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from tictactoe");
            System.out.println("Table " + rs.getMetaData().getTableName(1) + "<<<END");
            System.out.println("Catalog " + rs.getMetaData().getCatalogName(1) + "<<<END");
            System.out.println("Format " + ((PGResultSetMetaData)rs.getMetaData()).getFormat(1));
            rs.close();
            stmt.close();
        }
    }

    @Test
    public void test3() throws Exception {
        String url = "jdbc:postgresql://localhost/test?user=test";
        try (Connection conn = DriverManager.getConnection(url)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from bdata");
            System.out.println("Table " + rs.getMetaData().getTableName(1) + "<<<END");
            System.out.println("Catalog " + rs.getMetaData().getCatalogName(1) + "<<<END");
            System.out.println("Format " + ((PGResultSetMetaData)rs.getMetaData()).getFormat(1));
            rs.close();
            stmt.close();
        }
    }
}
