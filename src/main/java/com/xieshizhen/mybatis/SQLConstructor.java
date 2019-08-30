/**
 * Copyright © 2019 XieShiZhen(Kitetop). All rights reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xieshizhen.mybatis;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * @author Kitetop <1363215999@qq.com>
 * @version Release: v1.0
 * Date: 2019/08/29
 */
public class SQLConstructor {
    private static SQLConstructor constructor = null;

    private SQL sql = null;

    private SQLConstructor() {
    }

    /**
     * Single table selection query
     *
     * @param columns
     * @param table
     * @return
     */
    public SQLConstructor select(String columns, String table) {
        this.sql.SELECT(columns);
        this.sql.FROM(table);
        return this;
    }

    /**
     * Overload method, multi-table selection columns query
     *
     * @param columns
     * @param tables
     * @return
     */
    public SQLConstructor select(String columns, String... tables) {
        this.sql.SELECT(columns);
        this.sql.FROM(tables);
        return this;
    }

    /**
     * Overload method, single table selection all columns query
     *
     * @param table
     * @return
     */
    public SQLConstructor select(String table) {
        this.sql.SELECT("*");
        this.sql.FROM(table);
        return this;
    }

    /**
     * This method is intended to provide a method this is not limited
     * updating based on primary keys.
     * NOTICE: WHEN YOU ARE USE THIS METHOD, IT IS MORE APPROPRIATE TO
     * USE WHERE METHOD AT THE SAME TIME
     *
     * @param table
     * @param values
     * @return
     */
    public SQLConstructor update(String table, Map<String, String> values) {
        this.sql.UPDATE(table);
        for (Map.Entry<String, String> entry : values.entrySet()) {
            sql.SELECT(entry.getKey() + "=#{" + entry.getValue() + "}");
        }
        return this;
    }

    /**
     * This method is intended to provide a method this is not limited
     * deleting based on primary keys.
     * NOTICE: WHEN YOU ARE USE THIS METHOD, IT IS MORE APPROPRIATE TO
     * USE WHERE METHOD AT THE SAME TIME
     *
     * @param table
     * @return
     */
    public SQLConstructor delete(String table) {
        this.sql.DELETE_FROM(table);
        return this;
    }

    /**
     * Setting conditions
     *
     * @param query
     * @return
     */
    public SQLConstructor where(String query) {
        this.sql.WHERE(query);
        return this;
    }

    /**
     * If you want the result in order, you can use this method
     * The default is ascending order，you can also add DES in the end of each column
     *
     * @param columns
     * @return
     */
    public SQLConstructor order(String... columns) {
        this.sql.ORDER_BY(columns);
        return this;
    }

    /**
     * This method will get the SQL statement right now
     * The result will be paged
     *
     * @return
     */
    public String page() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.sql);
        builder.append("LIMIT #{page}, #{limit}");
        return builder.toString();
    }

    private void init() {
        this.sql = new SQL();
    }

    /**
     * Get the SQL statement, you can not use with page() at the same time
     *
     * @return
     */
    public String execute() {
        return this.sql.toString();
    }

    /**
     * Single patter, get an Object which is instance of this class
     *
     * @return
     */
    public static SQLConstructor getInstance() {
        if(constructor == null) {
            constructor = new SQLConstructor();
        }
        constructor.init();
        return constructor;
    }
}
