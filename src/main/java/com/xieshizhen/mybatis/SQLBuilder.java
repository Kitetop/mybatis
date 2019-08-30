package com.xieshizhen.mybatis;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * @author Kitetop <1363215999@qq.com>
 * @version Release: v1.0
 * Date: 2019/07/22
 * Construct the SQL statement
 */
public class SQLBuilder {

    private static SQLBuilder build = null;

    private SQLBuilder() {}

    /**
     * add data to database
     *
     * @param table
     * @param values
     * @return
     */
    public String insert(String table, Map<String, String> values) {
        SQL sql = new SQL();
        sql.INSERT_INTO(table);
        for (Map.Entry<String, String> entry : values.entrySet()) {
            sql.VALUES(entry.getKey(), "#{" + entry.getValue() + "}");
        }
        return sql.toString();
    }

    /**
     * Construct pretreatment for inserting multiple SQL statements
     *
     * @param table
     * @param values
     * @return
     */
    public String insertAll(String table, Map<String, String> values) {
        SQL sql = new SQL();
        sql.INSERT_INTO(table);
        for(Map.Entry<String, String> entry : values.entrySet()) {
            sql.VALUES(entry.getKey(), "#{item." + entry.getValue() + "}");
        }
        return sql.toString();
    }

    /**
     * update the data by primary key
     * id is default primary key
     * @param table
     * @param values
     * @return
     */
    public String update(String table, Map<String, String> values) {
        SQL sql = new SQL();
        sql.UPDATE(table);
        for(Map.Entry<String, String> entry : values.entrySet()) {
            sql.SET(entry.getKey() + "=#{" + entry.getValue() + "}");
        }
        sql.WHERE("id = #{id}");
        return sql.toString();
    }

    /**
     * find a row according the number of id
     * @param table
     * @return
     */
    public String selectById(String table) {
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM(table);
        sql.WHERE("id = #{id}");
        return sql.toString();
    }

    /**
     * delete one row according to the value of id
     *
     * @param table
     * @return
     */
    String deleteById(String table) {
        SQL sql = new SQL();
        sql.DELETE_FROM(table);
        sql.WHERE("id = #{id}");
        return sql.toString();
    }

    /**
     * use single model get this class object
     * @return
     */
    public static SQLBuilder getInstance() {
        if(build == null) {
            build = new SQLBuilder();
        }
        return build;
    }
}
