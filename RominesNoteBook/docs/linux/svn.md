# install svn on ubuntu

```
: apt-get install subversion
```

创建版本库

```
: svnadmin create ./
```

配置
```
: cd conf/
: vim svnserve.conf

......
[general]
......
anon-access = read
auth-access = write
......
password-db = passwd
......
realm = My First Repository

: vim passwd
[user]
......
用户名 = 密码
```

启动服务

```
: svnserve -d -r ./
```

访问地址为 svn://ip

关闭服务  
```
: ps -ef|grep svnserve
root     24709     1  0 02:41 ?        00:00:00 svnserve -d -r ./
: kill -9 24709
```

### 操作  
checkout:

```
svn checkout svn://... ./ --username NAME
```

add:  

```
svn add ./ --force --no-ignore
```  
commit:  

```
svn commit ./ -m "commit logs ..."
```
update:  

```
svn update ./
```

ignore: 
```
svn propset svn:ignore IGNOREFILESNAME ./
svn propdel svn:ignore ./
```

clean up

```
svn cleanup --remove-unversioned ./
```

---

## Version Control with Subversion

### 6. Server Configuration
#### [Overview](svn/1.md)
#### Choosing a Server Configuration
#### svnserve, a Custom Server 自定义服务器
#### httpd, the Apache HTTP Server 
#### Path-Based Authorization
#### High-level Logging
#### Server Optimization 服务最佳化
#### Supporting Multiple Repository Access Methods 支持多种存储库的访问方法  

---

## 配置 http

- 为 httpd 加载 mod_dav，并启动 httpd
- 安装 mod_dav_svn，它将使用 Subversion 的库函数来访问仓库
- 修改 httpd.conf，以便导出 Subversion 仓库 

前两步可以通过安装它们的二进制报完成。如何编译 Subversion，以便支持 Apache HTTP Server，以及如何配置 Apache，见 Subversion 源代码顶层目录下的 [INSTALL 文件](svn/2.md)

Subversion INSTALL 文件内容总结如下  

获取最新的 httpd 2.2+ 源码  
在源码根目录执行：  

```
$ ./buildconf
$ ./configure --enable-dav --enable-so --enable-maintainer-mode
```

`--enable-dav` 构建 mod_dav  
`--enable-so` 启用共享模块支持，用于 mod_dav_svn 的典型编译  
`--enable-maintainer-mode` 包含调试信息  

如果系统里安装了多个 db 版本，则加入 `--with-dbm=db4` 和 `--with-berkely-db=/usr/local/BerkeleyDB.4.2` 来指向 Subversion 使用的 db。  

其他模块：  
`--enable-ssl` SSL 支持模块  
`--enable-deflate` 压缩支持模块  

编译和安装  

```
$ make && make install
```

