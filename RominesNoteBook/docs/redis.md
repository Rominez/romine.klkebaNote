# redis

[常用命令](redis/1.md)

基于缓存的高速 key-value 数据库

##### windows 安装：https://github.com/MicrosoftArchive/redis/releases

	cmd
    redis-server.exe redis.windows.conf
可在 path 中添加 redis-server.exe 的安装路径，启动时则不需要 redis.windows.conf
##### 运行
另启一个 cmd 窗口

    redis-cli.exe -h 127.0.0.1 -p 6379
  设置和获取 key

    set myKey abc
    get myKey

##### Linux 安装：http://redis.io/download

	$ wget http://download.redis.io/releases/redis-2.8.17.tar.gz
	$ tar xzf redis-2.8.17.tar.gz
	$ cd redis-2.8.17
	$ make

  make 完后 redis-2.8.17 目录下会出现编译后的 redis 服务程序 redis-server， 还有用户测试的客户端程序 redis-cli， 两个程序位于安装目录 src 下
  启动：
  
	$ src/redis-server (默认配置启动)
	$ src/redis-server redis.conf (使用指定配置文件启动)
  
##### Redis 配置
  redis.conf
    （指令格式：redis 127.0.0.1:6379> CONFIG GET CONFIG_SETTING_NAME ）

	redis 127.0.0.1:6379> CONFIG get loglevel
    
  1) "loglevel"
  2) "notice"
  (使用*可获取所有配置项)
  
修改配置：
    可修改配置文件
    用 CONFIG set 命令修改配置
      (指令格式 redis 127.0.0.1:6739> CONFIG SET CONFIG_SETTING_NAME NEW_CONFIG_VALUE )
  参数说明

| | | |
| :--: | :--: | :-- |
| daemonize | yes/no |Redis是否以守护线程的方式运行 |
| pidfile| /var/run/redis.pid | 当Reids以守护线程的方式运行时，Redis默认吧pid写入/var/run/redis.pid文件,可以通过pidfile指定 |
| port | 6379 | 配置监听端口，默认端口为6379 |
| bind |127.0.0.1 | 绑定的主机地址，改成 0.0.0.0 即可在局域网中访问 |
| timeout | 300 | 客户端闲置多久后关闭。如果是0则关闭该功能 |
| loglevel | verbose | 指定日志记录级别，默认verbose。 debug\verbose\notice\warning |
| logfile| stdout | 日志记录方式，默认为标准输出。如果Redis为守护进程，日志方式又为标准输出，则日志会发送给/dev/null |
| databases | 16 | 设置数据库的数量，默认数据库为0，可以使用 `select <dbid>` 命令在连接上指定数据库 id |

    ....................................
    
##### Redis 数据类型
String 字符串  key/value
    二进制安全，可包含任何数据，如jpg图片或者序列化的对象
    一个键最大存储521MB

Hash 哈希
    
