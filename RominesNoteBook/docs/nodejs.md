# nodejs
CommonJS 标准  
V8JavaScript脚本语言  
通过单线程实现异步处理环境  事件环和非阻塞型I/O机制实现服务器端的异步处理  

## 解决方案  
简单的、用于创建高性能服务器及可在该服务器中运行的各种应用程序的开发工具  
修改了客户端到服务端的连接方法（为每个客户端连接创造一个新线程），为每个客户端连接触发一个在nodejs内部进行处理的事件  
V8JavaScript : 用 C++ 开发，不局限于浏览器中运行。使用全新的编译技术，使 JavaScript 脚本代码与 C 语言具有相近的执行效率  
Nodejs 将 V8JavaScript 用在服务器中，并提供了附加的API  

### 非阻塞型 I/O 及 事件环  
阻塞型 I/O ： 访问数据库后，拿到数据之前线程处于阻塞状态  
非阻塞型 I/O ： 访问数据库后立即执行后续代码，处理数据库返回结果的代码放在回调函数中执行  
事件环： 一个时刻只能执行一个事件回调函数，但是执行中途可以转而处理其他事件，然后返回继续执行原事件回调函数  

[Node.js 模块](nodejs/1.md)  
[REPL 交互式运行环境](nodejs/2.md)  

## NPM 

node package manager  

[CLI Documentation](nodejs/3.md)