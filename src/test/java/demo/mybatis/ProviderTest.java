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

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kitetop <1363215999@qq.com>
 * @version Release: v1.0
 * Date: 2019/08/29
 */

public class ProviderTest {

    private DepartEntity departEntity;
    private List<DepartEntity> departs = new ArrayList<>();
    private DepartService departService;

    public ProviderTest(DepartService service) {
        this.init();
        this.departService = service;
    }

    /**
     * Entry test method
     */
    public void entry() {
        this.saveTest();
        this.saveAllTest();
        this.findById();
        this.deleteById();
    }

    /**
     * Test save method
     *
     * @return
     */
    private void saveTest() {
        int affect =  this.departService.save(this.departEntity);
        Assert.assertEquals(1, affect);
        affect = this.departService.save(this.departEntity);
        Assert.assertEquals(0, affect);
    }

    /**
     * Test saveAll method
     *
     * @return
     */
    private void saveAllTest() {
        int affect = this.departService.saveAll(this.departs);
        Assert.assertEquals(1, affect);
    }

    private void deleteById() {
        int id = this.departEntity.getId();
        int affect = this.departService.deleteById(id);
        Assert.assertEquals(1, affect);
        DepartEntity entity = this.departService.findById(id);
        Assert.assertNull(entity);

    }

    private void findById() {
        int id = this.departEntity.getId();
        DepartEntity entity = this.departService.findById(id);
        Assert.assertEquals(id, (int)entity.getId());
        entity = this.departService.findById(-1);
        Assert.assertNull(entity);
    }

    /**
     * Init, provider test data
     */
    private void init() {
        this.departEntity = new DepartEntity();
        departEntity.setDepart("SAVE_TEST");
        DepartEntity entity = new DepartEntity();
        for(int i = 0; i < 5; i++) {
            entity.setDepart("SAVE_ALL" + i);
            this.departs.add(i, entity);
        }
    }
}
