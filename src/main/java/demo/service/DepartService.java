package demo.service;

import demo.entity.DepartEntity;
import demo.mapper.DepartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public int save(DepartEntity entity) {
        return departMapper.save(entity);
    }

    public Integer saveAll(List<DepartEntity> departs) {
        return departMapper.saveAll(departs);
    }

    public Integer deleteById(Integer id) {
        return departMapper.deleteById(id, DepartEntity.class);
    }

    public DepartEntity findById(Integer id) {
        return departMapper.findById(id, DepartEntity.class).orElse(null);
    }

    public List<DepartEntity> findByName(String where) {
        return departMapper.findByName(where);
    }

    public List<DepartEntity> findByPage(String where, Integer page, Integer limit) {
        return departMapper.findPage(where, (page - 1) * limit , limit);
    }

    public List<DepartEntity> findByOrder(String where, String order) {
        return departMapper.findOrder(where, order);
    }

    public Integer updateByName(String depart, String where) {
        return departMapper.updateByName(depart, where);
    }

    public Integer deleteByName(String where) {
        return departMapper.deleteByName(where);
    }
}
