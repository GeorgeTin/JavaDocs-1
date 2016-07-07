package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.appl.domain.Department;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Aimandis on 7/7/2016.
 */
public class EntityUtilsTest {

    @Test
    public void testGetTableNameMethod() {
        String tableName = EntityUtils.getTableName(Department.class);
        assertEquals("Table name should be departments!", "departments", tableName);
    }

    /*@Test
    public void testGetColumnMethod(){
        ArrayList<ColumnInfo> columns = EntityUtils.getColumn(Department.class);
        ArrayList<ColumnInfo> expected = new ArrayList<>();
        Field[] field = Department.class.getDeclaredFields();
        for()
        expected.add(Department.class.getDeclaredFields())
        assertEquals("Table name should be departments!", "departments", columns);
    }*/
    @Test
    public void testCastFromSqlTypeMethod() {
        assertEquals("Should return Integer!", Integer.class, EntityUtils.castFromSqlType(new BigDecimal(1231), Integer.class).getClass());
    }

    @Test
    public void testGetSqlValueMethod() {
        try {
            assertEquals("Should return Locations!", Department.class.getField("id"), EntityUtils.getSqlValue(Department.class));
        }
        catch (Exception e){

        }
    }

}
