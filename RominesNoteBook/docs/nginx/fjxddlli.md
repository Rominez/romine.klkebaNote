## 反向代理

不暴露真实 ip 地址  
nginx 部署在独立服务器上，后台服务部署在其他服务器上，由 nginx 接收请求并转发到后台服务器，不暴露后台服务的 ip 地址  

```
http {
    server {
        # 监听的端口
        listen  80;
        # 监听的域名
        server_name  localhost;

        # localhost:80/
        location / {
            root    html;
            # 默认跳转的页面
            index   index.html index.htm;
        }

        # localhost:80/tomcat
        location /tomcat {
            proxy_pass  http://127.0.0.1:8080;
            index   index.html;
        }
    }
}
```