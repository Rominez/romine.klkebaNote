## Bean
```
@Configuration
public class WebsocketAutoConfig {

    @Bean
    public ServerEndpointExporter endpointExporter(){
        return new ServerEndpointExporter();
    }
}
```

---
## Server Endpoint

```
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
@ServerEndpoint("/message/{id}")
public class WebsocketServerEndpoint{

    private String id;

    private Session session;

    public static void sendMessage(String id,String message) throws NullPointerException,IOException {
        WebSocketServerEndpointSet.getById(id).send(message);
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("id") String id){
        this.id = id;
        this.session = session;
        WebSocketServerEndpointSet.add(this);
    }

    @OnClose
    public void onClose(){
        WebSocketServerEndpointSet.remove(this);
    }

    @OnMessage
    public void onMessage(String message){
        System.out.println(message);
    }

    @OnError
    public void onError(Throwable e){
        e.printStackTrace();
    }

    private void send(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public boolean equals(String id){
        if(this.id.equals(id)){
            return true;
        }else{
            return false;
        }
    }
}

class WebSocketServerEndpointSet{

    private WebSocketServerEndpointSet(){
        this.endpoints = new HashSet<>();
    }

    private static WebSocketServerEndpointSet endpointSet;

    private static void construct(){
        if(endpointSet == null){
            endpointSet = new WebSocketServerEndpointSet();
        }
    }

    public static void add(WebsocketServerEndpoint endpoint){
        construct();
        endpointSet.endpoints.add(endpoint);
    }

    public static void remove(WebsocketServerEndpoint endpoint){
        construct();
        endpointSet.endpoints.remove(endpoint);
    }

    public static int size(){
        construct();
        return endpointSet.endpoints.size();
    }

    public static WebsocketServerEndpoint getById(String id) throws NullPointerException{
        construct();
        for( WebsocketServerEndpoint item : endpointSet.endpoints ){
            if(item.equals(id)){
                return item;
            }
        }
        throw new NullPointerException("该连接不存在");
    }

    private Set<WebsocketServerEndpoint> endpoints;
}
```

---
## JS
```
new WebSocket('ws://127.0.0.1:8080/message/id')
```