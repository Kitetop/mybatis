package com.xieshizhen.core.service;

import com.xieshizhen.core.entity.DepartEntity;
import com.xieshizhen.core.mapper.DepartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        departMapper.save(entity);
    }
}
