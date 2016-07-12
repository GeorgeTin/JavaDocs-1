package ro.teamnet.zth.api.em;

import javax.management.Query;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Aimandis on 7/7/2016.
 */
public class QueryBuilder {

    private Object tableName;
    private ArrayList<ColumnInfo> queryColumns;
    private ArrayList<Condition> conditions;
    private QueryType queryType;

    public QueryBuilder addCondition(Condition condition){
        if(this.conditions == null) this.conditions = new ArrayList<>();
        this.conditions.add(condition);
        return this;
    }

    public QueryBuilder setTableName(Object tableName){
        this.tableName = tableName;
        return this;
    }

    public QueryBuilder addQueryColumns(List<ColumnInfo> queryColumns){
        if(this.queryColumns == null) this.queryColumns = new ArrayList<>();
        this.queryColumns.addAll(queryColumns);
        return this;
    }

    public QueryBuilder setQueryType(QueryType queryType){
        this.queryType = queryType;
        return this;
    }

    public String getValueForQuery(Object value){
        if(value instanceof String)
            return (String) value;
        else {
            DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
            return "TO_DATE('" + dateFormat.format((Date) value) + "','mm-dd-YYYY'";
        }
    }

    private StringBuilder createSelectQuery(){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT" + " ");

        for(ColumnInfo col : queryColumns)
            sb.append(col.getColumnName() + ", ");

        sb.deleteCharAt(sb.length()-1);
        sb.append("FROM " + (String) tableName + " ");

        if(conditions.isEmpty()) {
            sb = addConditions(sb);
        }

        sb.append(";");
        return sb;
    }

    private StringBuilder createDeleteQuery(){
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append((String) tableName + " ");
        sb.append("WHERE ");
        for (Condition cond : conditions)
            sb.append(cond.getColumnName() + "=" + getValueForQuery(cond.getValue()) + ",");
        sb.deleteCharAt(sb.length() - 1);
       
        return sb;
    }

    private StringBuilder createUpdateQuery(){
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE " + tableName + " ");
        sb.append("SET ");
        for(ColumnInfo column : queryColumns){
            sb.append(column.getColumnName()+ "=" + column.getValue() + ",");
        }
        sb.deleteCharAt(sb.length()-1);

        if(conditions.isEmpty()) {
           sb = addConditions(sb);
        }
        sb.append(";");
        return sb;
    }

    private StringBuilder addConditions(StringBuilder sb){
        sb.append("WHERE ");
        for (Condition cond : conditions)
            sb.append(cond.getColumnName() + " = " + getValueForQuery(cond.getValue()) + ",");
        sb.deleteCharAt(sb.length() - 1);
        return sb;
    }

    private StringBuilder createInsertQuery(){
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO " + tableName + " (");
        for (Condition cond : conditions)
            sb.append(cond.getColumnName() + ",");
        sb.deleteCharAt(sb.length() - 1);

        sb.append("VALUES (");

        for(ColumnInfo column : queryColumns){
            sb.append(column.getValue() + ",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(");");
        return sb;
    }

    public String createQuery(){
        switch (queryType){
            case INSERT:
                return createInsertQuery().toString();
            case SELECT:
                return createSelectQuery().toString();
            case DELETE:
                return createDeleteQuery().toString();
            case UPDATE:
                return createUpdateQuery().toString();
            default:
                return "Wrong query";
        }
    }

}
