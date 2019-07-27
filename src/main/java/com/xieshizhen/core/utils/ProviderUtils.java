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
package com.xieshizhen.core.utils;


import com.xieshizhen.core.target.Column;
import com.xieshizhen.core.target.Table;
import org.apache.ibatis.annotations.Param;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * the common core curd operation for Mapper provider
 * support basic operation method
 *
 * @author Kitetop <1363215999@qq.com>
 * @version Release: v1.0
 * Date: 2019/07/20
 */
@SuppressWarnings("unchecked")
public class ProviderUtils {


    public <T> String save(T entity) throws Exception {
        Map<String, Object> result = this.parseEntity(entity);
        Integer id = (Integer) entity.getClass().getMethod("getId").invoke(entity);
        SqlBuildUtils build = SqlBuildUtils.getInstance();
        if(id == null) {
            return build.insert((String) result.get("table"), (Map<String, String>) result.get("message"));
        }
        return build.update((String) result.get("table"), (Map<String, String>) result.get("message"));
    }

    public <T, S> String findById(@Param("entity") Class<T> Entity) {
        String table = this.getTable(Entity);
        SqlBuildUtils build = SqlBuildUtils.getInstance();
        return build.selectById(table);
    }


    private <T> Map<String, Object> parseEntity(T entity) throws Exception {
        Map<String, Object> result = new HashMap<>();
        Class<T> Entity = (Class<T>) entity.getClass();
        // set the table name
        result.put("table", this.getTable(Entity));
        // get all member variables
        result.put("message", this.getMessage(Entity));
        return result;
    }

    /**
     * get the table name,if have not use the table target
     * The table name will be set to entity's class simple name
     * this name will be removed the string of Entity.
     *
     * @param Entity
     * @param <T>
     * @return
     */
    private <T> String getTable(Class<T> Entity) {
        String table = Entity.getSimpleName().replace("Entity", "");
        if (Entity.isAnnotationPresent(Table.class)) {
            table = Entity.getAnnotation(Table.class).value();
        }
        return table;
    }

    /**
     * Get the mapping relationship between entity class attributes and data table fields
     * pass the static members, So you can safely define constants in entity classes
     *
     * @param Entity
     * @param <T>
     * @return
     */
    private <T> Map<String, String> getMessage(Class<T> Entity) {
        Map<String, String> message = new HashMap<>();
        Field[] members = Entity.getDeclaredFields();
        String name;
        for (Field member : members) {
            //pass static variable
            if (Modifier.isStatic(member.getModifiers())) {
                continue;
            }
            name = member.getName();
            if (member.isAnnotationPresent(Column.class)) {
                message.put(member.getAnnotation(Column.class).name(), name);
                continue;
            }
            message.put(name, name);
        }
        return message;
    }
}
