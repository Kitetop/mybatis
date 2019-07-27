package com.xieshizhen.core.utils;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * @author Kitetop <1363215999@qq.com>
 * @version Release: v1.0
 * Date: 2019/07/22
 * @Description construct the sql query
 */
public class SqlBuildUtils {

    private static SqlBuildUtils build = null;

    private SqlBuildUtils() {}

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
        System.out.println(sql.toString());
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
        System.out.println(sql.toString());
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
        System.out.println(sql.toString());
        return sql.toString();
    }

    /**
     * use single model get this class object
     * @return
     */
    public static SqlBuildUtils getInstance() {
        if(build == null) {
            build = new SqlBuildUtils();
        }
        return build;
    }
}
