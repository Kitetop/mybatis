package com.xieshizhen.core.service;

import com.xieshizhen.core.entity.DepartEntity;
import com.xieshizhen.core.mapper.DepartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kitetop <1363215999@qq.com>
 * @version Release: v1.0
 * Date: 2019/07/22
 * @Description
 */
@Service
public class DepartService {
    private DepartMapper departMapper;

    @Autowired
    public DepartService(DepartMapper departMapper) {
        this.departMapper = departMapper;
    }

    public void save(DepartEntity entity) {
        DepartEntity departEntity = new DepartEntity();
        departEntity.setDepart("test1");
        DepartEntity departEntity1 = new DepartEntity();
        departEntity1.setDepart("test2");
        DepartEntity departEntity2 = new DepartEntity();
        departEntity2.setDepart("test3");
        List<DepartEntity> lists = new ArrayList<>();
        lists.add(departEntity);
        lists.add(departEntity1);
        lists.add(departEntity2);
        Integer rows;
        // test save function
        rows = departMapper.save(entity);
        System.out.println("save rows + " + rows);
        // test findById function
        DepartEntity depart = departMapper.findById(2, DepartEntity.class).orElse(null);
        System.out.println("findById method + " + depart);
        // test saveAll function
        rows = departMapper.saveAll(lists);
        System.out.println("save all rows + " + rows);
        // test deleteById function
        rows = departMapper.deleteById(5, DepartEntity.class);
        System.out.println("save all rows + " + rows);
        
    }
}
