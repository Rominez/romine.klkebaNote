package priv.test.testcloud;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAspectJAutoProxy(proxyTargetClass = false)
public class TestCloudApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println(1);
        new TestBean().getName();
        System.out.println(2);
    }

}
