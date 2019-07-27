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
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.Optional;

/**
 * @author Kitetop <1363215999@qq.com>
 * @version Release: v1.0
 * Date: 2019/07/24
 * Customized repository, provider common basic function
 */
public interface RepositoryMapper<T, S> {

    @InsertProvider(value = ProviderUtils.class, method = "save")
    void save(T entity);

    @SelectProvider(value = ProviderUtils.class, method = "findById")
    Optional<T> findById(@Param("id") S id, @Param("entity") Class<T> Entity);
}
