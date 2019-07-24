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


import com.xieshizhen.core.target.Table;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kitetop <1363215999@qq.com>
 * @version Release: v1.0
 * Date: 2019/07/20
 * @Description the common core curd operation for Mapper provider
 */
public class ProviderUtils {

    public <T> String save(T entity) throws Exception {
        String table = entity.getClass().getSimpleName().replace("Entity", "");
        try {
            @SuppressWarnings("unchecked")
            Map<String, String> message = (Map<String, String>) entity.getClass().getMethod("getInfo").invoke(entity);
            Integer id = (Integer) entity.getClass().getMethod("getId").invoke(entity);
            SqlBuildUtils build = SqlBuildUtils.getInstance();
            System.out.println(build);
            if (id == null) {
                return build.insert(table, message);
            }
            return build.update(table, message);
        } catch (Exception e) {
            throw new ExceptionUtils(500, e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private <T> Map<String, Object> parseEntity(T entity) throws Exception {
        Map<String, Object> result = new HashMap<>();
        Class<T> Entity = (Class<T>) entity.getClass();
        result.put("id", Entity.getMethod("getId").invoke(entity));
        // set the table name
        result.put("table", this.getTable(Entity));
        return result;
    }

    /**
     * get the table name,if have not use the table target
     * The table name will be set to entity's class simple name
     * this name will be removed the string of Entity.
     * @param Entity
     * @param <T>
     * @return
     */
    private <T> String getTable(Class<T> Entity) {
        String table = Entity.getSimpleName().replace("Entity", "");
        if(Entity.isAnnotationPresent(Table.class)) {
            table = Entity.getAnnotation(Table.class).value();
        }
        return table;
    }
}
