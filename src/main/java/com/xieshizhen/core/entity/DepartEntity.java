package com.xieshizhen.core.entity;

import com.xieshizhen.core.target.Table;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kitetop <1363215999@qq.com>
 * @version Release: v1.0
 * Date: 2019/07/20
 * @Description the depart entity
 */
@Table(value = "manage_default")
public class DepartEntity {
    private Integer id;
    @NotNull(message = "the depart name can not be null")
    private String depart;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart.trim();
    }

    public Map<String, String> getInfo() {
        Map<String, String> info = new HashMap<>();
        info.put("depart", "depart");
        return info;
    }
}