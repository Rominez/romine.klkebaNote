# websocket
## constructor
```
new WebSocket(String url [, protocols])
```

-----
## callback
连接成功 `onopen = function()`  
连接失败 `onerror = function()`  
从服务器接收到消息 `onmessage = function( MessageEvent )`  
关闭连接 `onclose = function()`

----
## properties
### 连接状态 `readyState`  
| 值 | 状态 | 说明 |
|-|-|-|
| 0 | CONNECTING | 未连接 |
| 1 | OPEN | 正常的连接状态 |
| 2 | CLOSING | 正在关闭 |
| 3 | CLOSED | 关闭状态 |

### 地址 `url`
以 `ws:\\` 或 `wss:\\` 开头的地址

----
## methods
### 关闭Socket `close( [ code [, reason ]] )`
### 发送消息 `send( data )`

---
## see
[MessageEvent](MessageEvent.md)

---
#### 抄作业
```
var wsocket = new WebSocket("ws://127.0.0.1:8080/message/aa");

wsocket.onopen = function(){
    console.log("message open")
}

wsocket.onerror = function(){
    console.log("message error")
}

wsocket.onmessage = function( event ){
    console.log(event)
}

wsocket.onclose = function(){
    console.log("message close");
}

function send() {
    wsocket.send(document.getElementById("msg_input").value);
}
```