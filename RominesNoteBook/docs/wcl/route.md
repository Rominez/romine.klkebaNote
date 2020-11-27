# route
## show all route
```
route print -4
```
## add
```
route -p add [ip] mask [dns] [ip]
      永久保存
             对方网关
                  固定
                       对方的子网掩码
                             网卡的网关
```
补充 网段即 ip 和 dns 相与
0.0.0.0 mask 0.0.0.0 为默认
## remove
```
route delete [ns]
```
```
route -f
清除所有路由表
```