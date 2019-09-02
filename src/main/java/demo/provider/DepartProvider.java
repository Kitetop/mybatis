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
package demo.provider;

import com.xieshizhen.mybatis.SQLConstructor;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kitetop <1363215999@qq.com>
 * @version Release: v1.0
 * Date: 2019/08/30
 */
public class DepartProvider {

    public String findByName(@Param("where") String where) {
        SQLConstructor constructor = SQLConstructor.getInstance();
        String statement = constructor.select("manage_depart").where(where).execute();
        return statement;
    }

    public String findPage(@Param("where") String where) {
        SQLConstructor constructor = SQLConstructor.getInstance();
        String statement = constructor.select("manage_depart").where(where).page();
        return statement;
    }

    public String findOrder(@Param("order") String order, @Param("where") String where) {
        SQLConstructor constructor = SQLConstructor.getInstance();
        String statement = constructor.select("manage_depart").where(where).order(order).execute();
        return statement;
    }

    public String updateByName(@Param("where") String where) {
        Map<String, String> values = new HashMap<>();
        values.put("depart", "depart");
        SQLConstructor constructor = SQLConstructor.getInstance();
        String statement = constructor.update("manage_depart", values).where(where).execute();
        return statement;
    }

    public String deleteByName(@Param("where") String where) {
        SQLConstructor constructor = SQLConstructor.getInstance();
        String statement = constructor.delete("manage_depart").where(where).execute();
        return statement;
    }
}
