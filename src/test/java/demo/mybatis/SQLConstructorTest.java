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
package demo.mybatis;

import demo.entity.DepartEntity;
import demo.service.DepartService;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kitetop <1363215999@qq.com>
 * @version Release: v1.0
 * Date: 2019/08/30
 */
@Component
public class SQLConstructorTest {

    private DepartService departService;
    private List<DepartEntity> departs = new ArrayList<>();

    /**
     * Entry test method
     */
    public void entry() {
        this.init();
        this.findByName();
        this.findPage();
        this.findOrder();
        this.updateByName();
        this.deleteByName();
    }

    private void findByName() {
        String where_null = "depart = 233";
        List<DepartEntity> result = departService.findByName(where_null);
        Assert.assertEquals(0, result.size());
        String where_like = "depart like 'SQL_Con%'";
        result = departService.findByName(where_like);
        Assert.assertEquals(5, result.size());
    }

    private void findPage() {
        String where_like = "depart like 'SQL_Con%'";
        List<DepartEntity> result = departService.findByPage(where_like, 1, 2);
        Assert.assertEquals(2, result.size());
        result = departService.findByPage(where_like, 3, 2);
        Assert.assertEquals(1, result.size());
    }

    private void findOrder() {
        String order = "depart DESC";
        List<DepartEntity> result = departService.findByOrder("depart like 'SQL_Con%'", order);
        Assert.assertTrue(result.size() >= 5);
        Integer ber_id = result.get(0).getId();
        Integer aft_id = result.get(4).getId();
        Assert.assertTrue(ber_id > aft_id);
        order = "depart ASC";
        result = departService.findByOrder("depart like 'SQL_Con%'", order);
        ber_id = result.get(0).getId();
        aft_id = result.get(4).getId();
        Assert.assertTrue(ber_id <  aft_id);
    }

    private void updateByName() {
        String where = "depart = 'SQL_Con1'";
        int affect = departService.updateByName("change_sql", where);
        Assert.assertEquals(1, affect);
    }

    private void deleteByName() {
        String where = "depart = 'change_sql'";
        List<DepartEntity> entities = departService.findByName("depart = 'change_sql'");
        Assert.assertTrue(entities.size() >= 1);
        int affect = departService.deleteByName(where);
        Assert.assertEquals(1, affect);
        entities = departService.findByName("depart = 'change'");
        Assert.assertEquals(0, entities.size());
    }

    /**
     * Init, provider test data
     */
    private void init() {
        for (int i = 0; i < 5; i++) {
            DepartEntity entity = new DepartEntity();
            entity.setDepart("SQL_Con" + i);
            this.departs.add(i, entity);
        }
        int affect = departService.saveAll(departs);
        Assert.assertEquals(1, affect);
    }

    @Autowired
    public void setDepartService(DepartService departService) {
        this.departService = departService;
    }
}
