package com.xieshizhen.core.utils;



import java.util.Map;

/**
 * @author Kitetop <1363215999@qq.com>
 * @version Release: v1.0
 * Date: 2019/07/20
 * @Description the common core curd operation for Mapper provider
 */
public class ProviderUtils {

    public  <T> String save(T entity) throws  Exception {
        String table = entity.getClass().getSimpleName().replace("Entity", "");
        try {
            @SuppressWarnings("unckecked")
            Map<String, String> message = (Map<String, String>) entity.getClass().getMethod("getInfo").invoke(entity);
            Integer id = (Integer) entity.getClass().getMethod("getId").invoke(entity);
            SqlBuildUtils build = SqlBuildUtils.getInstance();
            System.out.println(build);
            if(id == null) {
               return build.insert(table, message);
            }
            return build.update(table, message);
        } catch (Exception e) {
            throw new ExceptionUtils(500, e.getMessage());
        }
    }
}
