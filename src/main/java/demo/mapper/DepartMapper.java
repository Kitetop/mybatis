package demo.mapper;

import com.xieshizhen.mybatis.RepositoryMapper;
import demo.entity.DepartEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Kitetop <1363215999@qq.com>
 * @version Release: v1.0
 * Date: 2019/07/20
 * @Description
 */
@Mapper
@Repository
public interface DepartMapper extends RepositoryMapper<DepartEntity, Integer> {
}
