## Start Name Server  
```
: nohub sh bin/mqnamesrv &
: tail -f ~/logs/rocketmqlogs/namesrv.log
The Name Server boot success...
```
## Start Broker  
```
: nohub sh bin/mqbroker -n localhost:9876 &
: tail -f ~/logs/rocketmqlogs/broker.log
The broker[%s,172.30.30.233:10911] boot success...
```
## Send & Receive Messages
使用客户端需要在环境变量里设置 name servers 的地址 `NAMESRV_ADDR`  
```
: export NAMESRV_ADDR=localhost:9876
: sh bin/tools.sh org.apache.rocketmq.example.quickstart.Producer
SendResult [sendStatus=SEND_OK,msgId=...

: sh bin/tools.sh org.apache.rocketmq.example.quickstart.Consumer
ConsumeMessageThread_%d Receive New Message: [MessageExt...
```

### Send ued Java  
Maven Denpendency  
```
<dependency>
    <groupId></groupId>
    <artifactId>rocketmq-client</artifactId>
    <version>...</version>
</dependency>
```
Send Message Synchronously 发送同步消息  
消息包含了 主题,标签和消息体  
标签似乎可以设置多个  
```
public class SyncProducer {
    public static void main(String[] args){
        //Instance with a producer group name. 定义一个使用了组名称的实例  
        DefaultMQProducer producer = new 
            DefaultMQProducer("group_name");
        producer.setNamesrvAddr("localhost:9876");
        //Launch the instance
        for(int i=0;i<100;i++){
            //Create a message instance, specifying topic,tag and message body. 定义消息实例 
            Message msg = new Message("TopicTest","TagA",("Hello RocketMQ " + i ).getBytes(RemotingHelper.DEFAULT_CHARSET));
            //Call send message to deliver message to one of brokers.  发送消息  
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n",sendResult);
        }
        //Shut down once the producer instance is no longer in use. 关闭的 producer 实例将不再被使用
        producer.shutdown();
    }
}
```
Send Message Asynchronously 发送异步消息  
```
public class SyncProducer {
    public static void main(String[] args){
        //创建 producer 实例如上
        producer.start();
        producer.setRetryTimeWhenSendAsyncFaild(0);//设置发送失败重试次数  
        ...
        //创建消息实例如上
        producer.send(msg,new SendCallback(){
            @Override
            public void onSuccess(SendResult sendResult){
                System.out.printf("%-10d OK %s %n",index,sendResult.getMsgId());
            }
            @Override
            public void onException(Throuble e){
                System.out.printf("%-10d Exception %s %n",index,e);
                e.printStackTrace();
            }
        });
        producer.shutdown();
    }
}
```
## Shutdown Servers
```
: sh bin/mqshutdown broker
The mqbroker(36695) is running... 
Send shutdown request to mqbroker(36695) OK

: sh bin/mqshutdown namesrv
The mqnamesrv(36664) is running...
Send shutdown request to mqnamesrv(36664) OK
```
