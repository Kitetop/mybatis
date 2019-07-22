package com.xieshizhen.core.controller;

import com.xieshizhen.core.entity.DepartEntity;
import com.xieshizhen.core.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kitetop <1363215999@qq.com>
 * @version Release: v1.0
 * Date: 2019/07/20
 * @Description define the router, provide entry function
 */
@RestController
@CrossOrigin
@RequestMapping("/depart")
public class DepartController {

    private DepartService departService;
    @Autowired
    public DepartController(DepartService departService) {
        this.departService = departService;
    }
    @GetMapping("/add")
    public void add(DepartEntity depart) {
        departService.save(depart);
    }
}
