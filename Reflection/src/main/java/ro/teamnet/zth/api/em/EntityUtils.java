package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aimandis on 7/7/2016.
 */
public class EntityUtils {


    private EntityUtils() throws UnsupportedOperationException {
    }

    public static String getTableName(Class entity) {
        Table table = (Table) entity.getAnnotation(Table.class);
        if (table != null)
            return table.name();
        else
            return entity.getName();
    }

    public static ArrayList<ColumnInfo> getColumn(Class entity) {
        Field[] fields = entity.getDeclaredFields();
        ArrayList<ColumnInfo> arrayList = new ArrayList<>();
        ColumnInfo columnInfo;
        for (Field field : fields) {
            columnInfo = new ColumnInfo();
            Column column = field.getAnnotation(Column.class);
            Id id = field.getAnnotation(Id.class);
            if (id != null) {
                columnInfo.setIsId(true);
                columnInfo.setDbName(id.name());
                columnInfo.setColumnType(field.getType());
                columnInfo.setColumnName(field.getName());
            } else {
                columnInfo.setIsId(false);
                columnInfo.setColumnType(field.getType());
                columnInfo.setColumnName(field.getName());
                columnInfo.setDbName(column.name());
            }
            arrayList.add(columnInfo);
        }
        return arrayList;
    }

    public static Object castFromSqlType(Object value, Class wantedType) {
        if (value instanceof BigDecimal && wantedType.equals(Integer.class)) return ((BigDecimal)value).intValue();
        if (value instanceof BigDecimal && wantedType.equals(Long.class)) return ((BigDecimal)value).longValue();
        if (value instanceof BigDecimal && wantedType.equals(Float.class)) return ((BigDecimal)value).floatValue();
        if (value instanceof BigDecimal && wantedType.equals(Double.class)) return ((BigDecimal)value).doubleValue();

        return value;
    }

    public static List<Field> getFieldsByAnnotations(Class clazz, Class annotation) {
        Field[] fields = clazz.getDeclaredFields();
        ArrayList<Field> list = new ArrayList<>();
        for (Field field : fields) {
            if (field.getAnnotation(annotation) != null)
                list.add(field);
        }
        return list;
    }

    public static Object getSqlValue(Object object) {
        if (object.getClass().getAnnotation(Table.class) != null) {
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.getAnnotation(Id.class) != null){
                    field.setAccessible(true);
                    return field;
                }
            }
        }
        return object;
    }
}


