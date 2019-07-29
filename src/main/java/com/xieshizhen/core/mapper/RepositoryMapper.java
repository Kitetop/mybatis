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
package com.xieshizhen.core.mapper;

import com.xieshizhen.core.utils.ProviderUtils;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Kitetop <1363215999@qq.com>
 * @version Release: v1.0
 * Date: 2019/07/24
 * Customized repository, provider common basic function
 */
public interface RepositoryMapper<T, S> {

    /**
     * if the entity's primary key not null, this method will update this row
     * (find this row by primary key) so set primary by yourself is not support.
     * after run this method, the primary key id will be add in the entity object
     * It is recommended to open the database to return the number of affected rows configuration
     * such as: jdbc:mysql:xxx?useAffectedRows=true
     *
     * @param entity
     * @return
     */
    @InsertProvider(value = ProviderUtils.class, method = "save")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Integer save(T entity);

    /**
     * If you want to use this method, you must turn on the option to allow multiple
     * SQL statement to be executed
     * such as: jdbc:mysql:xxx?allowMultiQueries=true
     * @param entities
     * @return
     */
    @InsertProvider(value = ProviderUtils.class, method = "saveAll")
    Integer saveAll(@Param("entities") List<T> entities);

    /**
     * This method is only suitable for the case where the field name of the data
     * table is consistent with the property of the entity class,
     * If inconsistent, rewrite this method, add @Results Target
     * @param id
     * @param Entity
     * @return
     */
    @SelectProvider(value = ProviderUtils.class, method = "findById")
    Optional<T> findById(@Param("id") S id, @Param("entity") Class<T> Entity);

    /**
     * Delete one row according to the primary key
     *
     * @param id
     * @param Entity
     * @return
     */
    @DeleteProvider(type = ProviderUtils.class, method = "deleteById")
    Integer deleteById(@Param("id") S id, @Param("entity") Class<T> Entity);
}
