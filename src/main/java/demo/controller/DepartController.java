package demo.controller;

import demo.entity.DepartEntity;
import demo.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
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
    public Integer add(DepartEntity depart) {
        departService.save(depart);
        String name = depart.getDepart();
        List<DepartEntity> entity = departService.findByName("depart = '" + name + "'");
        return entity.get(0).getId();
    }
}
