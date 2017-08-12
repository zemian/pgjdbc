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
        String url = "jdbc:postgresql://localhost/test";
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
            rs.close();
            System.out.println(count + " found.");
        }
    }
}
