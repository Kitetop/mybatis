package demo;

import demo.mybatis.ProviderTest;
import demo.mybatis.SQLConstructorTest;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Properties;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CoreApplicationTests {

    private ProviderTest providerTest;
    private SQLConstructorTest sqlConstructorTest;

    /**
     * Choose which profiles to use
     */
    @BeforeClass
    public static void setSystemProperty() {
        Properties properties =System.getProperties();
        properties.setProperty("spring.profiles.active", "test");
    }

    @Test
    @Transactional
    public void providerTest() {
        this.providerTest.entry();
    }

    @Test
    @Transactional
    public void SQLConstructorTest() {
        this.sqlConstructorTest.entry();
    }

    @Autowired
    public void setProviderTest(ProviderTest providerTest) {
        this.providerTest = providerTest;
    }

    @Autowired
    public void setSqlConstructorTest(SQLConstructorTest sqlConstructorTest) {
        this.sqlConstructorTest = sqlConstructorTest;
    }
}
