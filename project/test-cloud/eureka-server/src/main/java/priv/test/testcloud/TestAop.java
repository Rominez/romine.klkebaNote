package priv.test.testcloud;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TestAop {

    @Before("execution(* priv.test.testcloud.*.get*(..))")
    public void before(){
        System.out.println("before");
    }
}
