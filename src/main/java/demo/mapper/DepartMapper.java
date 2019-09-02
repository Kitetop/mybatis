package demo.mapper;

import com.xieshizhen.mybatis.RepositoryMapper;
import demo.entity.DepartEntity;
import demo.provider.DepartProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author Kitetop <1363215999@qq.com>
 * @version Release: v1.0
 * Date: 2019/07/20
 * @Description
 */
@Mapper
@Repository
public interface DepartMapper extends RepositoryMapper<DepartEntity, Integer> {

    @SelectProvider(value = DepartProvider.class, method = "findByName")
    List<DepartEntity> findByName(@Param("where") String where);

    @SelectProvider(value = DepartProvider.class, method = "findPage")
    List<DepartEntity> findPage(@Param("where") String where,
                                @Param("offset") Integer offset,
                                @Param("limit") Integer limit);

    @SelectProvider(value = DepartProvider.class, method = "findOrder")
    List<DepartEntity> findOrder(@Param("where") String where, @Param("order") String order);

    @UpdateProvider(value = DepartProvider.class, method = "updateByName")
    Integer updateByName(@Param("depart") String depart, @Param("where") String where);

    @DeleteProvider(value = DepartProvider.class, method = "deleteByName")
    Integer deleteByName(@Param("where") String where);
}
