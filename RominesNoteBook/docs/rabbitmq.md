# rabbitmq    
### 版本  
rabbitmq-server-3.8.1.exe  
otp_win64_22.2.exe(Erlang)  

### 安装  
新建环境变量`RABBITMQ_BASE`指向一个不含中文的路径  
执行安装包  
rabbitmq 安装好后会自动注册一个`RabbitMQ`服务  
在`path`中添加rabbitmq安装路径`\sbin`  

### 管理  
通过 web 进行管理  
执行  
```
$ rabbitmq-plugins enable rabbitmq_management
```
访问 `http://127.0.0.1:15672`，默认用户`guest`密码`guest`  

### exchange 消息交换机  
消息第一个到达的地方  
direct exchange: 将消息直接发送到指定的Queue里  
topics exchange: 将消息发送给根据topic匹配到的Queue里  


### spring-boot 整合  
###### maven:  
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-amqp</artifactId>
</dependency>
```
###### application.yml:  
```
spring:
  rabbitmq:
    host: 127.0.0.1
    port: 5672
#   用户名和密码在rabbitmq管理页面的admin标签页下面创建  
    username: producer
    password: producer
#   给用户分配的虚拟主机
    virtual-host: /producer
```
###### 配置一个队列  
```
package com.example.producter.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Bean
    public Queue testQueue(){
        return new Queue("test");
    }
}
```

topic 类型的 exchange 配置  
```
package com.example.producter.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Bean(name = "q1")
    public Queue q1(){
        return new Queue("q1");
    }

    @Bean(name = "q2")
    public Queue q2(){
        return new Queue("q2");
    }

    @Bean(name = "q3")
    public Queue q3(){
        return new Queue("q3");
    }

    @Bean(name = "topic1")
    public TopicExchange topicExchange(){
        return new TopicExchange("topic1");
    }

    @Bean
    public Binding binding1(@Qualifier("q1") Queue queue,@Qualifier("topic1")TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with("topic1.q1");
    }

    @Bean
    public Binding binding2(@Qualifier("q2") Queue queue,@Qualifier("topic1")TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with("topic1.q2");
    }

    @Bean
    public Binding binding3(@Qualifier("q3") Queue queue,@Qualifier("topic1")TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with("topic1.*");
    }
}
```
###### 发送消息  
```
package com.example.producter.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //普通
    public void send(){
        //test 为 exchange 名称
        //topic1.q1.123 是消息发送的路由，exchange 会根据这个将消息发送到匹配的 Queue
        //将 Object 转换为 Message 然后发送
        amqpTemplate.convertAndSend("test","topic1.q1.123","this is a test message");
    }

    //指定消息过期时间
    public void send2(){
        Message message = new Message("this is a test message".getBytes(),new MessageProperties(){{
            //单位为毫秒
            setExpiration("10000");
        }});
        rabbitTemplate.convertAndSend("test","this is a test message");
    }
}

```
###### 接收消息  
```
package com.example.producter.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "test")
public class Receiver {

    @RabbitHandler
    public void process(String message){
        System.out.println("get message:  "+message);
    }
}
```
消息的发送和返回  
返回消息需要用`@SendTo`指定一个新的 Queue  
```
package com.example.producter.receiver;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @RabbitListener(queues = "test")
    @RabbitHandler
    @SendTo("test2")
    public String process(Message message){
        System.out.println("test get message:  "+new String(message.getBody()));
        return "ok";
    }

    @RabbitListener(queues = "test2")
    @RabbitHandler
    public void process2(Message message){
        System.out.println("test get message:  "+new String(message.getBody()));
    }

}

```

###### 测试  
```
package com.example.producter;

import com.example.producter.sender.Sender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProducterApplicationTests {

    @Autowired
    private Sender sender;

    @Test
    void contextLoads() {
        sender.send();
    }

}
```