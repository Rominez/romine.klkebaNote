package priv.test.testcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

//@EnableEurekaServer
@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = false)
public class TestCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestCloudApplication.class, args);
    }

}
