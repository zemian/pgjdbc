package zemian;

import org.junit.Test;
import org.postgresql.Driver;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xbbj5x6 on 8/6/2017.
 */
public class DataTypeTest {

    public static Map<Integer, String> SQL_TYPE_NAMES = getSqlTypeNames();
    public static Map<Integer, String> getSqlTypeNames() {
        Map<Integer, String> result = new HashMap<>();
        try {
            Field[] fields = Types.class.getFields();
            for (Field field : fields) {
                Integer value = (Integer)field.get(null);
                String name = field.getName();
                result.put(value, name);
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to get sql type names", e);
        }
        return result;
    }

    @Test
    public void testDataTypes() throws Exception {
        Driver driver = new Driver();
        String url = "jdbc:postgresql://localhost/test?user=test";
        try(Connection conn = driver.connect(url, null)) {
            ResultSet rs = conn.getMetaData().getTypeInfo();
            int count = 0;
            while(rs.next()) {
                String name = rs.getString("TYPE_NAME");
                int type = rs.getInt("DATA_TYPE");
                String typeName = SQL_TYPE_NAMES.get(type);

                System.out.println(
                        "TYPE_NAME=" + name +
                        ", DATA_TYPE=" + type +
                        ", SQL_TYPE=" + typeName);
                count ++;
            }
            System.out.println(count + " found.");
            rs.close();
        }
    }

    @Test
    public void tetsGetFunctions() throws Exception {
        Driver driver = new Driver();
        String url = "jdbc:postgresql://localhost/test?user=test";
        try(Connection conn = driver.connect(url, null)) {
            ResultSet rs = conn.getMetaData().getFunctions(null, null, null);
            int maxCols = rs.getMetaData().getColumnCount();
            for (int i =1; i <= maxCols; i++) {
                System.out.println("Idx#" + i + " " + rs.getMetaData().getColumnName(i));
            }

//            int count = 0;
//            while(rs.next()) {
////                String cat = rs.getString("FUNCTION_CAT");
////                String schem = rs.getString("FUNCTION_SCHEM");
////                String name = rs.getString("FUNCTION_NAME");
////                String remarks = rs.getString("REMARKS");
////                int type = rs.getShort("FUNCTION_TYPE");
//
//                String cat = rs.getString(1);
//                String schem = rs.getString(2);
//                String name = rs.getString(3);
//                String remarks = rs.getString(4);
//                int type = rs.getShort(5);
//
//                System.out.println(
//                        "FUNCTION_CAT=" + cat +
//                        ", FUNCTION_SCHEM=" + schem +
//                        ", FUNCTION_NAME=" + name +
//                        ", REMARKS=" + remarks +
//                        ", FUNCTION_TYPE=" + type);
//                count ++;
//            }
//            System.out.println(count + " found.");
            rs.close();
        }
    }

    @Test
    public void testGetProcedures() throws Exception {
        Driver driver = new Driver();
        String url = "jdbc:postgresql://localhost/test?user=test";
        try(Connection conn = driver.connect(url, null)) {
            ResultSet rs = conn.getMetaData().getProcedures(null, null, null);
            int maxCols = rs.getMetaData().getColumnCount();
            for (int i =1; i <= maxCols; i++) {
                System.out.println("Idx#" + i + " " + rs.getMetaData().getColumnName(i));
            }

//            int count = 0;
//            while(rs.next()) {
////                String cat = rs.getString("FUNCTION_CAT");
////                String schem = rs.getString("FUNCTION_SCHEM");
////                String name = rs.getString("FUNCTION_NAME");
////                String remarks = rs.getString("REMARKS");
////                int type = rs.getShort("FUNCTION_TYPE");
//
//                String cat = rs.getString(1);
//                String schem = rs.getString(2);
//                String name = rs.getString(3);
//                String remarks = rs.getString(4);
//                int type = rs.getShort(5);
//
//                System.out.println(
//                        "FUNCTION_CAT=" + cat +
//                        ", FUNCTION_SCHEM=" + schem +
//                        ", FUNCTION_NAME=" + name +
//                        ", REMARKS=" + remarks +
//                        ", FUNCTION_TYPE=" + type);
//                count ++;
//            }
//            System.out.println(count + " found.");
            rs.close();
        }
    }

    @Test
    public void tetsGetFunctions2() throws Exception {
        Driver driver = new Driver();
        String url = "jdbc:postgresql://localhost/test?user=test";
        try(Connection conn = driver.connect(url, null)) {
            ResultSet rs = conn.getMetaData().getFunctions(null, null, null);
            while (rs.next() ) {
                System.out.println(rs.getString("FUNCTION_NAME"));
            }
            rs.close();
        }
    }
}
