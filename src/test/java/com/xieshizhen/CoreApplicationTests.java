package com.xieshizhen;

import com.xieshizhen.mybatis.ProviderTest;
import demo.CoreApplication;
import demo.service.DepartService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CoreApplication.class)
public class CoreApplicationTests {
    private DepartService departService;
    private ProviderTest providerTest;


    @Before
    public void init() {
        this.providerTest = new ProviderTest(this.departService);
    }

    @Test
    @Transactional
    public void providerTest() {
        this.providerTest.entry();
    }

    @Autowired
    public void setDepartService(DepartService departService) {
        this.departService = departService;
    }
}
